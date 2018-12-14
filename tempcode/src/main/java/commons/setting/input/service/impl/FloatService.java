
package commons.setting.input.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.external.po.DictTDefaultcolPO;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.service.IDictTDefaultcolService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.impl.FloatGridService;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.external.service.IFormulaOuterService;
import com.tjhq.commons.setting.input.dao.FloatMapper;
import com.tjhq.commons.setting.input.po.DictTSetFddefPO;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.po.TreeNode;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.service.IFloatService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class FloatService extends FloatGridService implements IFloatService {
    @Resource
    public FloatMapper floatMapper;
    @Resource
    public IDictTModelService dictTModelService;
    @Resource
    public IDictTDefaultcolService dictTDefaultcolService;
    @Resource
    public IDictTFactorService dictTFactorService;
    @Resource
    public IEntryService entryService;
    @Resource
    private IEntryOuterService entryOuterService;
    @Resource
    public UtilsMapper utilsMapper;
    @Resource
    public IFormulaOuterService formulaOutService;

    private final static String CFG = "_CFG";

    /**
     * 根据表ID，where条件查询数据 没排序
     * @param tableID
     * @param whereCondition
     * @return
     */
    public List<Map<String, Object>> getFloatData(String tableID, String whereCondition) throws ServiceException {
        // 根据表ID获取表中 所有列
        DictTModelPO dictTModelPO = dictTModelService.getDictTModelOFID(tableID, true);
        // TODO:靠不靠谱啊
        String columnsql = this.getSqlSelect(dictTModelPO);
        String tableName = getTableDbName(tableID);

        Map<String, Object> query = new HashMap<String, Object>();
        query.put("dbTableName", tableName);
        query.put("columnsql", columnsql);
        query.put("whereCondition", whereCondition);

        return floatMapper.getFloatData(query);
    }

    private String getSqlSelect(DictTModelPO dictTModelPO) {
        StringBuffer sqlSelectBuffer = new StringBuffer();
        List<DictTFactorPO> factorList = dictTModelPO.getDictTFactorList();
        for (DictTFactorPO dictTFactorPO : factorList) {
            if (sqlSelectBuffer.length() == 0)
                sqlSelectBuffer.append(dictTFactorPO.getDbcolumnname());
            else
                sqlSelectBuffer.append("," + dictTFactorPO.getDbcolumnname());
        }
        String sqlSelect = sqlSelectBuffer.toString();
        return sqlSelect.toUpperCase();
    }

    /**
     * 根据TABLEID 初始化浮动表
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void initFloatData(String tableID,String opt) throws ServiceException {
        // 获取浮动表的名称
        String floatName = getTableDbName(tableID);

        // 首先判断该表内是否已经有数据，如果有先删除再重新生成一遍
        //  先删除
        if (opt.equals("init")) {
            deleteAllFloatData(tableID, floatName);
        }
        // 根据TableID 查询浮动表设置信息
        DictTSetFddefPO fddef = entryService.getDataFddefList(tableID);
        // 取出fdcode编码规则9
        String[] layerIndent = fddef.getLayerIndent().split(",");
        DictTFactorPO po = dictTFactorService.getDictTFactorByColumnId(fddef.getLvlNanmeCol());
        // 根据TABLEID查出LVLNNAMECOL 即COLUMNID -->>查出 csdbtablename 即dbtablename
        List<Map<String, Object>> datas = getCodeTableData(fddef.getLvlNanmeCol(), layerIndent.length);

       
        Map<Integer, String> lvlCodeMap = new HashMap<Integer, String>();
        int orderId = 0;
        int previousLvl = 0;
        //获取已经存在的模板数据
        List<Map<String, Object>> oldDatas = getFloatData(tableID, "");
        Map<String, Object> oldDataMap = new HashMap<String, Object>();
        for (Map<String, Object> oldData : oldDatas) {
            oldDataMap.put((String) oldData.get(po.getDbcolumnname()), oldData);
        }
        try {
            if (opt.equals("init")) {
                for (Map<String, Object> data : datas) {
                 // 默认taskID --*
                    data.put("TASKID", "*");
                    // 生成fdcode
                    Integer levelNo = Integer.parseInt(data.get("LEVELNO").toString());
                    if (levelNo < previousLvl) {
                        for (int j = previousLvl; j > levelNo; j--) {
                            lvlCodeMap.put(j, null);
                        }
                    }
                    previousLvl = levelNo;
                    String maxFdcode = lvlCodeMap.get(levelNo);
                    int previou = 0; // 上一次
                    if (null != maxFdcode && !"".equals(maxFdcode)) {// 不是第一次
                        previou = Integer.parseInt(maxFdcode);
                    }
                    String currentCode = getCurrentLvlNextCode(layerIndent[levelNo - 1], previou);
                    lvlCodeMap.put(levelNo, currentCode);
                    String fdCode = getFdCode(levelNo, lvlCodeMap);
                    data.put("FDCODE", fdCode);
                    
                    // 开始插入浮动表数据
                    setFloatData(data, floatName, layerIndent, po, previousLvl, lvlCodeMap, ++orderId);
                }
            } else {
                for (Map<String, Object> data : datas) {
                    String guid = (String) data.get("GUID");
                    // 默认taskID --*
                    data.put("TASKID", "*");
                    // 生成fdcode
                    Integer levelNo = Integer.parseInt(data.get("LEVELNO").toString());
                    if (levelNo < previousLvl) {
                        for (int j = previousLvl; j > levelNo; j--) {
                            lvlCodeMap.put(j, null);
                        }
                    }
                    previousLvl = levelNo;
                    String maxFdcode = lvlCodeMap.get(levelNo);
                    int previou = 0; // 上一次
                    if (null != maxFdcode && !"".equals(maxFdcode)) {// 不是第一次
                        previou = Integer.parseInt(maxFdcode);
                    }
                    String currentCode = getCurrentLvlNextCode(layerIndent[levelNo - 1], previou);
                    lvlCodeMap.put(levelNo, currentCode);
                    String fdCode = getFdCode(levelNo, lvlCodeMap);
                    data.put("FDCODE", fdCode);
                    
                    if (oldDataMap.containsKey(guid)) {
                        updateInitData(data, (Map<String, Object>) oldDataMap.get(guid), floatName, layerIndent, po,
                                previousLvl, lvlCodeMap, ++orderId);
                        continue;
                    }
                    // 开始插入浮动表数据
                    setFloatData(data, floatName, layerIndent, po, previousLvl, lvlCodeMap, ++orderId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_07201, "保存数据时出现异常！原因：" + e.getCause().getMessage(), false);
        }
        // 查询出浮动表的数据
        List<Map<String, Object>> floatData = getFloatData(tableID, "");
        // 设置表中单元格权限 更新levelno isleaf
        setSecuAndOther(tableID, floatName, floatData);
      //批量新增的数据需要  刷新单元格公式   刷新业务数据fdcode
        if (opt.equals("addInit")) {
            formulaOutService.refreshFormula(tableID, "1", "A2");
            floatMapper.refreshAllData(tableID);
        }
    }
    // 插入浮动表数据
    private void setFloatData(Map<String, Object> data, String floatName, String[] layerIndent, DictTFactorPO po,
            int previousLvl, Map<Integer, String> lvlCodeMap, int orderId) throws ServiceException {

        // 向该初始化浮动表中插入数据
        if (!data.containsKey("TEMPLATEID") || data.get("TEMPLATEID") == null || "".equals(data.get("TEMPLATEID"))) {
            data.put("TEMPLATEID", UUID.randomUUID().toString().replace("-", "").toUpperCase());
        }
        data.put("AGENCYID", "*");
        data.put("ORDERID", orderId);
        data.put("ISINSERT", "0");
        data.put("ISTEMPLATE", "1");
        data.put("ISQZX", "0");
        data.put("ISDJ", "0");
        data.put("ISHZ", "0");
        data.put("ISUPDATE", "1");// 默认可写
        data.put("colName", po.getDbcolumnname());
        data.put("ISHZ", "0");
        data.put("SYNSTATUS", "0");// 未发布
        data.put("floatName", floatName);
        floatMapper.setFloatData(data);
    }
 private void updateInitData(Map<String, Object> data,Map<String, Object> oldData, String floatName, String[] layerIndent, DictTFactorPO po,
         int previousLvl, Map<Integer, String> lvlCodeMap, int orderId) throws ServiceException {
    
     // 向该初始化浮动表中插入数据
     oldData.put("FDCODE", data.get("FDCODE"));
     oldData.put("ORDERID", orderId);
     oldData.put("floatName", floatName);
     floatMapper.updateInitFloatData(oldData);
 }
    // 获取代码表数据
    public List<Map<String, Object>> getCodeTableData(String colID, int levelno) throws ServiceException {
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> query = new HashMap<String, Object>();
        Map<String, String> codeTable = getCodeTableInfoByColID(colID);// 如果没有引用表，不需要初始化
        String dbTableName = codeTable.get("CSDBTABLENAME");
        String codeTableID = codeTable.get("CSID");
        if (dbTableName == null || codeTableID == null) {
            throw new ServiceException(ExceptionCode.ERR_00000, "没有定义浮动列或该浮动列的代码表异常！", false);
        }
        query.put("dbTableName", dbTableName);
        query.put("levelno", levelno);// 根据缩进规则查询数据
        // 若果代码表由status字段，需要筛选出等于1的结果集
        if (floatMapper.getStatusByTableID(codeTableID) > 0) {
            query.put("whereSTR", " status='1' ");
        }
        datas = floatMapper.getCodeTableData(query);
        if (datas.size() == 0) {
            throw new ServiceException(ExceptionCode.ERR_00000, "浮动列的代码表中没有数据！", false);
        }
        return datas;
    }

    // 根据浮动列的列id查询引用代码表信息
    public Map<String, String> getCodeTableInfoByColID(String colID) throws ServiceException {
        Map<String, String> codeTable = floatMapper.getCodeTableInfoByColID(colID);// 如果没有引用表，不需要初始化
        return codeTable;
    }

    // 首先判断该表内是否已经有数据，如果有先删除再重新生成一遍
    public void deleteAllFloatData(String tableID, String floatName) throws ServiceException {
        Map<String, Object> queryM = new HashMap<String, Object>();
        try {
            if (getFloatData(tableID, "") != null || getFloatData(tableID, "").size() != 0) {
                // 删除该表的相关表内单元格公式
                formulaOutService.deleteFormulaByTableID(tableID, "1");
                // 删除表内数据
                queryM.put("floatName", floatName);

                floatMapper.delAllFloatData(queryM);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }

    }

    private String getFdCode(int levelNo, Map<Integer, String> lvlCodeMap) {
        String fdCode = "";
        for (int i = 1; i <= levelNo; i++) {
            fdCode += lvlCodeMap.get(i);
        }
        return fdCode;
    }

    /**
     * @param count 总位数
     * @param num 当前数字
     * @return 当前数字+1
     */
    private String getCurrentLvlNextCode(String countStr, int num) {
        String code = "";
        int count = Integer.parseInt(countStr);
        int currentLen = String.valueOf(num + 1).length();
        for (int i = 0; i < (count - currentLen); i++) {
            code += "0";
        }
        code += (num + 1);
        return code;
    }

    // 整表设置
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String saveFloatWhole(DictTSetFddefPO fddef, String operator, String dealType) {
        String flag = "{\"error\":\"保存失败！\"}";
        try {
            Integer is_insert = 0;
            // 排序列
            fddef.setColOrder(this.getColOrder(fddef.getTableID(), dealType));

            if (operator.equals("insert"))
                is_insert = floatMapper.insertFloatWhole(fddef); // 插入

            if (operator.equals("update"))
                is_insert = floatMapper.updateFloatWhole(fddef); // 更新

            if (is_insert > 0)
                flag = "{\"succ\":\"" + fddef.getGuID() + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"保存数据库失败！\"}";
        }
        return flag;
    }

    // 获取排序列
    // 内容：在factor表中isLeaf 为 1
    public String getColOrder(String tableID, String dealType) {
        String colOrder = "";
        List<DictTFactorPO> factor = dictTFactorService.getDictTFactorByTableidAndType(tableID, "1");
        for (DictTFactorPO po : factor) {
            colOrder += po.getColumnid() + ",";
        }
        if (ConverTables.isNotNull(colOrder))
            colOrder = ConverTables.removeLastChar(colOrder, ",");
        return colOrder;
    }

    /**
     * 更新单元格权限 参数： floatData 数据库中数据 colOrder 排序列 tableID 表 dbTableName 物理表名 updateIsLeaf 处理后的是否末级
     */
    public void updateCellSecu(List<Map<String, Object>> floatData, String colOrder, String tableID,
            String dbTableName, List<Map<String, Object>> updateIsLeaf) {

        if (ConverTables.isNotNullList(floatData)) {
            Map<String, String> formula = this.getFormulaColumn(tableID);
            for (int i = 0; i < floatData.size(); i++) {
                Map<String, Object> data = (Map<String, Object>) floatData.get(i);
                if (!ConverTables.isNotNull(data.get("FDCODE")))
                    continue;

               String code = (String) data.get("FDCODE");
                String tempID = (String) data.get("TEMPLATEID");
                String stage = ""; // 末级
                String formulaCol = "";
                // 获取公式列     //TODO FDCODE 改为templateid   因为forwhere存为tempid了
                if (ConverTables.isNotNull(formula))
                    formulaCol = formula.get(tempID);

                for (Map<String, Object> process : updateIsLeaf) {
                    if (code.equals(process.get("FDCODE"))) {
                        String isLeaf_ = (String) process.get("ISLEAF");
                        // 判断末级编码是否发生改变
                        if (ConverTables.isNotNull(data.get("ISLEAF"))) {
                            stage = data.get("ISLEAF").equals(isLeaf_) ? isLeaf_ : "1";
                        } else {
                            stage = isLeaf_;
                        }
                    }
                }

                String cellSecu = "";
                // cellSecu 单元格权限，根据 是否末级 来判断
                if (ConverTables.isNotNull(colOrder) && ConverTables.isNotNull(stage)) {
                    for (String secu : colOrder.split(","))
                        cellSecu += stage;

                    // 如果存在公式列 、 公式列始终为 0
                    if (ConverTables.isNotNull(formulaCol)) {
                        String newCellSecu = "";
                        for (String secu : formulaCol.split(","))
                            newCellSecu += "0";
                        cellSecu = this.cellSecuByCols(colOrder, formulaCol, cellSecu, newCellSecu);
                    }
                }
                try {// 更新单元格 权限
                    if (ConverTables.isNotNull(cellSecu))
                        floatMapper.saveCellSecu((String) data.get("DATAKEY"), cellSecu, dbTableName);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 处理每组 fdCodeToCols 对应列 的单元格权限
    public String cellSecuByCols(String colOrder, String subColOrder, String cellSecu, String newCellSecu) {
        String newArray[] = colOrder.split(",");
        String oldArray[] = subColOrder.split(",");

        char[] newCell = cellSecu.toCharArray();
        char[] oldcell = newCellSecu.toCharArray();

        // 通过 columnId 将oldCellSecu 更新到 newCellSecu
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < oldArray.length; j++) {
                if (newArray[i].equals(oldArray[j])) {
                    // TODO:暂时不注释掉
                    // if(i<newCell.length)
                    newCell[i] = oldcell[j];
                }
            }
        }
        return new String(newCell);
    }

    // 把公式list转为map
    public Map<String, String> getFormulaColumn(String tableID) {
        List<Map<String, String>> formulaList = formulaOutService.selectFormulaColumn(tableID, "1");

        Map<String, String> columnMap = new HashMap<String, String>();
        // 公式列
        if (ConverTables.isNotNullList(formulaList)) {
            for (Map<String, String> formula : formulaList) {
                columnMap.put(formula.get("FDCODE"), formula.get("COLUMNID"));
            }
        }
        return columnMap;
    }

    // 获取层次名称列 与 名称层次
    public Map<String, Object> getLvlNanmeCol_1(String tableID) {
        String lvlNanme = "";
        String layerIndent = "";
        String colOrder = "";
        DictTSetFddefPO fddef = entryService.getDataFddefList(tableID);
        if (null != fddef) {
            DictTFactorPO factor = dictTFactorService.getDictTFactorByColumnId(fddef.getLvlNanmeCol());
            if (factor != null)
                lvlNanme = factor.getDbcolumnname().toUpperCase();
            layerIndent = fddef.getLayerIndent();
            colOrder = fddef.getColOrder();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("lvlNanme", lvlNanme);
        map.put("layerIndent", layerIndent);
        map.put("colOrder", colOrder);
        return map;
    }

    // 根据tableID 查询 物理表名
    private String getTableDbName(String tableID) {
        String dbTableName = "";
        DictTModelPO model = dictTModelService.getDictTModelByID(tableID, false);
        if (model != null) {
            dbTableName = model.getDbtablename();
        }
        if (dbTableName.indexOf(CFG) < 0) {
            dbTableName = dbTableName + CFG;
        }
        return dbTableName;
    }

    /**
     * 处理 浮动表级次 、是否末级 返回方式： Map<String,Object> {10_levelno=1,10_isleaf=1,10_superid=F4C798851FE64D56E040A8C020032117}
     */
    public Map<String, Object> dataToLeveNo(List<Map<String, Object>> floatData, Map<String, Object> keyMap,
            String layerIndent, String tableID, String mark) {
        List<String> data = new ArrayList<String>();
        Map<String, Object> dataV = new ConcurrentHashMap<String, Object>();
        // 页面 中所有的 FDCODE
        for (Map<String, Object> map : floatData) {
            String fdCode = (String) map.get("FDCODE" + mark); // 添加 FDCODE
            if (ConverTables.isNotNull(fdCode)) {
                data.add(fdCode);
            }
            String templateID = (String) map.get("TEMPLATEID"); // SUPERID 使用
            // TEMPLATEID
            if (ConverTables.isNotNull(templateID)) {
                keyMap.put(fdCode + "_superid", templateID);
            }
            String dataKey = (String) map.get("DATAKEY"); // 添加 DATAKEY
            if (ConverTables.isNotNull(dataKey)) {
                keyMap.put(fdCode + "_datakey", dataKey);
            }
        }
        if (data == null || data.size() == 0)
            return dataV;
        // 浮动层次码
        String indent[] = layerIndent.split(",");
        int layer = 0;
        int level = 0;
        // -------
        for (int i = 0; i < indent.length; i++) {
            layer += Integer.parseInt(indent[i].trim());
            if (data.size() > 0) {
                for (int j = 0; j < data.size(); j++) {
                    char dataArray[] = data.get(j).toString().toCharArray();
                    String fdCode = (String) data.get(j);
                    // 通过层次码 判断 是否末级
                    if (dataArray.length == layer) {
                        String levelCode = fdCode.substring(0, level);
                        // 末级编码
                        for (String code : data) {
                            if (levelCode.equals(code)) {
                                dataV.put(code + "_isleaf", "0");
                                dataV.put(fdCode + "_superid", keyMap.get(code + "_superid"));
                            }
                        }
                        // 级次
                        dataV.put(fdCode + "_levelno", new BigDecimal((i + 1)));
                        // 判断是否存在 fdCode ,不存在 is_leaf = 1
                        if (!dataV.containsKey(fdCode + "_isleaf"))
                            dataV.put(fdCode + "_isleaf", "1");
                        if (!dataV.containsKey(fdCode + "_superid"))
                            dataV.put(fdCode + "_superid", "0");
                        // DATAKEY
                        if (!dataV.containsKey(fdCode + "_datakey"))
                            dataV.put(fdCode + "_datakey", keyMap.get(fdCode + "_datakey"));
                    }
                }
            }
            level += Integer.parseInt(indent[i].trim());
        }
        return dataV;
    }

    /**
     * 处理 末级编码 及 单元格权限 返回方式： List<Map<String,Object>> [{FDCODE=10,ISLEAF=1,LEVELNO=1,SUPERID=F4C798851FE64D56E040A8C020032117}]
     */
    public List<Map<String, Object>> selIsLeaf(Map<String, Object> level, String colOrder, String mark) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Iterator<String> it = level.keySet().iterator();
        while (it.hasNext()) {
            Map<String, Object> merge = new HashMap<String, Object>();
            String key = (String) it.next();
            // 截取FDCODE
            String fdCode = key.substring(0, key.indexOf("_"));

            for (Map.Entry<String, Object> en : level.entrySet()) {
                String levelKey = en.getKey();
                // 获取 是否末级 节点
                if (levelKey.equalsIgnoreCase(fdCode + "_isleaf")) {
                    merge.put("FDCODE" + mark, fdCode);
                    merge.put("ISLEAF" + mark, en.getValue());
                    level.remove(levelKey);
                }
                // 获取 级次 节点
                if (levelKey.equalsIgnoreCase(fdCode + "_levelno")) {
                    merge.put("FDCODE" + mark, fdCode);
                    merge.put("LEVELNO" + mark, en.getValue());
                    level.remove(levelKey);
                }
                // 获取 superId dataKey
                if (levelKey.equalsIgnoreCase(fdCode + "_superid")) {
                    merge.put("FDCODE" + mark, fdCode);
                    merge.put("SUPERID" + mark, en.getValue());
                    level.remove(levelKey);
                }
                if (levelKey.equalsIgnoreCase(fdCode + "_datakey")) {
                    merge.put("FDCODE" + mark, fdCode);
                    merge.put("DATAKEY", en.getValue());
                    level.remove(levelKey);
                }
            }
            if (!merge.isEmpty())
                list.add(merge);
        }
        return list;
    }

    // 判断 级次、末级、上级编码是否发生改变
    public static String relaCol = "ISLEAF|LEVELNO|SUPERID";

    public List<Map<String, Object>> isLeafChange(List<Map<String, Object>> floatData,
            List<Map<String, Object>> updateIsLeaf) {
        Pattern is_leaf = Pattern.compile(relaCol, Pattern.CASE_INSENSITIVE);
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : floatData) {
            for (Map<String, Object> leaf : updateIsLeaf) {
                if (data.get("DATAKEY").equals(leaf.get("DATAKEY"))) {
                    Map<String, Object> data_ = ConverTables.processCanUpdate(data, is_leaf);
                    Map<String, Object> leaf_ = ConverTables.processCanUpdate(leaf, is_leaf);
                    // 是否发生改变
                    if (!ConverTables.compareMap(data_, leaf_)) {
                        lists.add(leaf);
                    }
                }
            }
        }
        return lists;
    }

    /**
     * 浮动表设置 需要获取要插入的 数据列 方式一： 只保存浮动表缺省列 column 方式二： 保存factor中所有列
     */
    // 浮动表缺省列 column
    public Map<String, Object> getFloatDefaultCol(String dealType, String operator, String lvlNanme) throws Exception {
        Map<String, Object> newMap = new HashMap<String, Object>();
        List<String> fieldColumn = new ArrayList<String>();

        Map<String, Map<String, Object>> optionMap = new HashMap<String, Map<String, Object>>();
        // 设置选项Map
        Map<String, Object> objOption = null;

        // 获取缺省列
        List<DictTDefaultcolPO> list = dictTDefaultcolService.getDictTDefaultcols(dealType);
        if (list != null && list.size() > 0 && !operator.equals("delete")) { // 删除操作
            // 不需要获取
            // 缺省列
            for (DictTDefaultcolPO col : list) {
                objOption = new HashMap<String, Object>();
                // 缺省列
                String columnName = col.getDbcolumnname().toUpperCase();
                fieldColumn.add(columnName);
                objOption.put("dataType", col.getDatatype()); // 数据列类型
                optionMap.put(columnName, objOption);
            }
        }
        if (fieldColumn.size() > 0) {
            if (lvlNanme != null)
                fieldColumn.add(lvlNanme);
        }
        // 删除 、 更新 主键
        List<String> primaryKeyColumn = new ArrayList<String>();
        primaryKeyColumn.add("DATAKEY");
        primaryKeyColumn.add("AGENCYID");

        fieldColumn.addAll(primaryKeyColumn);

        newMap.put("fieldColumn", fieldColumn);
        newMap.put("optionMap", optionMap);
        return newMap;
    }

    // 更新单元格权限
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveCellSecu(String dataKey, String cellSecu, String cIndex, String cSecu, String tableID) {
        String renew = "";
        if (ConverTables.isNotNull(cellSecu)) {
            char cell[] = cellSecu.toCharArray();
            cell[Integer.valueOf(cIndex)] = cSecu.toCharArray()[0];
            renew = new String(cell);
        }

        String DBTABLENAME = this.getTableDbName(tableID);
        floatMapper.saveCellSecu(dataKey, renew, DBTABLENAME);

    }

    // 刷新 浮动表
    @Transactional
    public String refreshFloatSet(String tableID, String dealType) throws ServiceException {
        // 获取 浮动表中数据
        List<Map<String, Object>> floatData = this.getFloatData(tableID, "");

        String colOrder = "";
        if (dealType.equals("A2")) { // float table
            DictTSetFddefPO fddef = entryService.getDataFddefList(tableID);
            if (fddef != null)
                colOrder = fddef.getColOrder();
        }
        if (dealType.equals("A1")) { // fixed table
            List<DictTSetFixPO> fixed = entryService.getDataFixList(tableID);
            if (ConverTables.isNotNullList(fixed))
                colOrder = fixed.get(0).getColOrder();
        }
        // 获取新的排序列
        String newColOrder = this.getColOrder(tableID, dealType);

        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        // 通过 newColOrder || colOrder 刷新单元格权限
        if (ConverTables.isNotNull(newColOrder) && ConverTables.isNotNull(colOrder)) {

            for (int i = 0; i < floatData.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) floatData.get(i);
                String cellSecu = (String) map.get("CELLSECU");
                String isLeaf = (String) map.get("ISLEAF");
                String newCellSecu = this.refreshCellSecu(newColOrder, colOrder, cellSecu, isLeaf);
                map.put("CELLSECU", newCellSecu);
                lists.add(map);
            }
        }

        if (ConverTables.isNotNullList(lists)) {
            // 物理表名
            String dbTableName = this.getTableDbName(tableID);

            // 刷新 AGENCYID 为 * 浮动数据
            floatMapper.refreshCellSecu(lists, dbTableName);
            // newColOrder 更新到 DICT_T_SETFDDEF 中
            if (dealType.equals("A2"))
                floatMapper.updateFloatColOrder("DICT_T_SETFDDEF", newColOrder, tableID);
            if (dealType.equals("A1"))
                floatMapper.updateFloatColOrder("DICT_T_SETFIX", newColOrder, tableID);

        }
        return "{\"flag\":\"true\"}";
    }

    // 刷新浮动表
    private String refreshCellSecu(String newColOrder, String colOrder, String cellSecu, String isLeaf) {
        String newArray[] = newColOrder.split(",");
        String oldArray[] = colOrder.split(",");
        String newCellSecu = "";
        // 通过 newColOrder 将单元格权限组合 1...1
        String supplement = "1";
        if (ConverTables.isNotNull(isLeaf))
            supplement = isLeaf; // 判断是补0 OR 补1
        for (String secu : newArray)
            newCellSecu += supplement;

        char[] oldcell = cellSecu.toCharArray();
        char[] newCell = newCellSecu.toCharArray();

        // 通过 columnId 将oldCellSecu 更新到 newCellSecu
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < oldArray.length; j++) {
                if (newArray[i].equals(oldArray[j])) {
                    newCell[i] = ConverTables.isNotNull(oldcell[j]) ? oldcell[j] : isLeaf.toCharArray()[0];
                }
            }
        }
        return new String(newCell);
    }
    @Override
    public void deleteAllTemplateData(String tableID) throws ServiceException {
        String floatName = dictTModelService.getDictTModelOFID(tableID, true).getDbtablename();
        //  删除全部数据  和公式
        deleteAllFloatData(tableID, floatName);
        
    }
    // 通过TEMPLATEID来获取浮动表的信息
    public List<Map<String, Object>> getFloatDataByTemplateID(String tableName, String templateID) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (tableName.indexOf(CFG) < 0) {
            tableName = tableName + CFG;
        }
        map.put("tableName", tableName);
        map.put("templateID", templateID);
        return floatMapper.getFloatDataByTemplateID(map);
    }

    @Override
    public Integer getResCount(String tableID) throws ServiceException {
        // 根据表ID获取表
        String tableName = getTableDbName(tableID);
        // 获取表中的数据
        String whereCondition = "SYNSTATUS = '1'  and rownum < 2";
        List<Map<String, Object>> dataList = null;
        try {
            dataList = this.getFloatData(tableID, whereCondition);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.SET_07200, "获取是否存在发布过的数据时出现异常！", false);
        }
        int count = floatMapper.getResCount(tableName);
        if (!dataList.isEmpty() && dataList.size() > 0) {
            // 存在发布过的数据
            return 1;
        }
        if (count > 0) {
            // 存在业务数据
            return 2;
        }
        return 0;
    }

    @Override
    protected void setTableSecu(Table table, String userID) throws ServiceException {
        table.setRowWriteSecu("'3'");
        return;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public boolean saveData(Table table) throws ServiceException {
        // 设置任务id
        utilsMapper.setTaskParam("*");
        // 保存表
        FloatGrid grid = (FloatGrid) table;
        // 查询浮动设置表
        String tableID = grid.getTableID();

        if (grid.getTableDBName().indexOf(CFG) < 0) {
            grid.setTableDBName(grid.getTableDBName() + CFG);
        }
        String dbTableName = grid.getTableDBName();
        super.setTableColumns(grid);
        if (grid.getColumnList() == null || grid.getColumnList().size() == 0) {
            setTableColumns(grid);
        }
        if (grid.getInsertValues() != null && grid.getInsertValues().size() > 0) {
            for (Map<String, Object> data : grid.getInsertValues()) {
                if (!data.containsKey("TEMPLATEID") || data.get("TEMPLATEID") == null
                        || "".equals(data.get("TEMPLATEID"))) {
                    data.put("TEMPLATEID", UUID.randomUUID().toString().replace("-", "").toUpperCase());
                }
                if (!data.containsKey("ISTEMPLATE") || data.get("ISTEMPLATE") == null
                        || "".equals(data.get("ISTEMPLATE"))) {
                    data.put("ISTEMPLATE", "1");
                }
                data.put("SYNSTATUS", "0");// 未发布
                data.put("ISUPDATE", "1");// 默认可写
            }
        }
        // 删除数据之前 先删除公式
        if ((grid.getDeleteValues() != null && grid.getDeleteValues().size() > 0)) {
            for (Map<String, Object> gridMap : grid.getDeleteValues()) {
                String datakey = (String) gridMap.get("DATAKEY");
                String templateID = floatMapper.getFloatDataByDatakey(dbTableName, datakey);
                // 是否存在公式
                int cell_1 = floatMapper.selectFormulaByFdCode(templateID, tableID);

                if (cell_1 > 0)
                    floatMapper.deleteFormulaByFdCode(templateID, tableID);
            }
        }
        // 校验数据正确性与完整性
        if (!validateData(grid))
            return false;
        // 删除数据
        deleteData(grid);

        // 修改数据
        updateData(grid);
        // 新增数据
        addData(table);
        // 有新增 更新 末级编码
        if ((table.getInsertValues() != null && table.getInsertValues().size() > 0)
                || (table.getUpdateValues() != null && table.getUpdateValues().size() > 0)) {
            // 查询出浮动表的数据
            List<Map<String, Object>> floatData = getFloatData(tableID, "");
            // 设置表中单元格权限 更新levelno isleaf
            setSecuAndOther(tableID, dbTableName, floatData);
            // 保存后执行方法
            afterSaveData(grid);// 校验FDCODE 是否书写正确
        }
        return true;
    }

    private void setSecuAndOther(String tableID, String dbTableName, List<Map<String, Object>> floatData)
            throws ServiceException {

        Map<String, Object> fddef = getLvlNanmeCol_1(tableID);
        String layerIndent = (String) fddef.get("layerIndent");
        String colOrder = (String) fddef.get("colOrder");

        Map<String, Object> keyMap = new HashMap<String, Object>();

        // 处理 级次、是否末级 、superId
        Map<String, Object> level = dataToLeveNo(floatData, keyMap, layerIndent, tableID, "");
        // 转为 List<Map<String, Object>>
        List<Map<String, Object>> updateIsLeaf = isLeafChange(floatData, selIsLeaf(level, colOrder, ""));

        if (ConverTables.isNotNullList(updateIsLeaf))
            floatMapper.updateIsLeaf(updateIsLeaf, dbTableName);
        // 更新单元格权限
        updateCellSecu(floatData, colOrder, tableID, dbTableName, updateIsLeaf);
    }

    private List<DictTSetSortPO> getSortColumns(String tableID) throws ServiceException {
        List<DictTSetSortPO> sortColumns = entryOuterService.getDataSortList(tableID);
        return sortColumns;
    }

    @Override
    public PageInfo getData(Table table) throws ServiceException {
        FloatGrid grid = (FloatGrid) table;
        if (grid.getTableDBName().indexOf(CFG) < 0) {
            grid.setTableDBName(grid.getTableDBName() + CFG);
        }
        super.setTableColumns(grid);
        // 设置任务id
        utilsMapper.setTaskParam("*");

        Map<String, Object> paramMap = null;
        List<Map<String, Object>> dataList = this.getGridData(paramMap, grid);
        // 取得数据
        PageInfo pageInfo = grid.getPageInfo();
        setGridData(dataList, pageInfo);

        if (grid.isPagination()) {// 如果表格有分页，需要取得数据总条数
            int total = getTotalCount(paramMap);
            pageInfo.setTotal(total);
        }
        return pageInfo;
    }

    private List<Map<String, Object>> getGridData(Map<String, Object> paramMap, CommonGrid grid)
            throws ServiceException {
        // 获取查询参数
        paramMap = getQueryParamter(grid);
        // 取得排序
        List<DictTSetSortPO> sortList = this.getSortColumns(grid.getTableID());
        DictTSetSortPO sortPo = new DictTSetSortPO();
        sortPo.setDbColumnName("FDCODE");
        sortPo.setIsDefault("1");
        sortList.add(sortPo);
        grid.setSortColumnsList(sortList);

        if (grid.getSortColumnsList() != null) {
            String sortSQL = handleSortParam(grid, setSort(sortList));
            if (sortSQL != null && sortSQL.trim().length() > 0) {
                paramMap.put("sortSQL", sortSQL);
            }
        }
        return getGridRecords(paramMap);
    }

    // 校验FDCODE 是否书写正确
    @Override
    protected void afterSaveData(Table table) throws ServiceException {

        super.afterSaveData(table);
        String tableID = table.getTableID();
        // 查询出浮动表的数据
        List<Map<String, Object>> floatData = getFloatData(tableID, "");
        Map<String, Object> fddefMap = getLvlNanmeCol_1(tableID);
        String layerIndentStr = (String) fddefMap.get("layerIndent");
        String layerIndent[] = layerIndentStr.split(",");
        for (Map<String, Object> data : floatData) {
            int len = 0;
            for (int i = 0; i < Integer.parseInt(data.get("LEVELNO").toString()); i++) {
                len += Integer.parseInt(layerIndent[i].toString());
            }
            if (data.get("FDCODE") == null
                    || (data.get("FDCODE") != null && len != data.get("FDCODE").toString().length())) {
                throw new ServiceException(ExceptionCode.ERR_00000,
                        "【排序序号为" + data.get("ORDERID") + "】的FDCODE没有按规则填写！", false);
            }
        }
    }

    @Override
    public Object getFactorTree(String tableID, String dealType) throws ServiceException {
        //
        List<DictTFactorPO> factor = new ArrayList<DictTFactorPO>();
        // 获取缺省列
        List<DictTDefaultcolPO> list = new ArrayList<DictTDefaultcolPO>();
        try {
            factor = dictTFactorService.getDictTFactorByTableidAndIsvisible(tableID, "1");
            list = dictTDefaultcolService.getDictTDefaultcols(dealType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "获取表格默认列出现异常！原因：" + e.getCause().getMessage(), false);
        }
        List<DictTFactorPO> remove = new ArrayList<DictTFactorPO>();

        for (DictTFactorPO po : factor) {
            for (DictTDefaultcolPO col : list) {
                if (ConverTables.isNotNull(po.getDbcolumnname())) {
                    if (po.getDbcolumnname().equalsIgnoreCase(col.getDbcolumnname())) {
                        remove.add(po);
                    }
                }
            }
        }
        // 移除缺省列
        factor.removeAll(remove);

        List<TreeNode> treeList = new ArrayList<TreeNode>();
        for (DictTFactorPO po : factor) {
            TreeNode tree = new TreeNode();
            tree.setId(po.getColumnid());
            tree.setName(po.getName());
            tree.setPId(po.getSuperid());
            tree.setIsLeaf(po.getIsleaf() == null ? "1" : po.getIsleaf());
            tree.setLevelNo(po.getLevelno() == null ? 1 : po.getLevelno());
            tree.setDbColumnName(po.getDbcolumnname());
            tree.setColumnName(po.getName());
            tree.setColumnId(po.getColumnid());
            tree.setCsid(po.getCsid());
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public boolean getCodeCount(String tableID) throws ServiceException {
        //获取已经存在的模板数据
        List<Map<String, Object>> oldDatas = getFloatData(tableID, "");
        DictTSetFddefPO fddef = entryService.getDataFddefList(tableID);
        String[] layerIndent = fddef.getLayerIndent().split(",");
        List<Map<String, Object>> datas = getCodeTableData(fddef.getLvlNanmeCol(), layerIndent.length);
        if(oldDatas.size() == datas.size()){
            return true;
        }
        return false;
    }
}
