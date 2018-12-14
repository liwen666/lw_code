
package commons.setting.input.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dao.UtilsMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.service.IDictTDefaultcolService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.grid.fixgrid.service.impl.FixGridService;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.input.dao.FixedMapper;
import com.tjhq.commons.setting.input.dao.FloatMapper;
import com.tjhq.commons.setting.input.po.DictTSetFixPO;
import com.tjhq.commons.setting.input.service.IEntryService;
import com.tjhq.commons.setting.input.service.IFixedService;
import com.tjhq.commons.setting.input.service.IFloatService;
import com.tjhq.commons.setting.input.web.ConverTables;

@Service
@Transactional(readOnly = true)
public class FixedService extends FixGridService implements IFixedService {
    @Resource
    public UtilsMapper utilsMapper;
    @Resource
    public FixedMapper fixedMapper;
    @Resource
    public FloatMapper floatMapper;

    @Resource
    public IFloatService floatService;
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
    
    private final static String CFG = "_CFG";

    // 整表设置
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String saveFixedWhole(DictTSetFixPO fixed, String operator, String dealType) throws Exception {
        String flag = "{\"error\":\"保存失败！\"}";
        Integer is_insert = 0;
        String tableID = fixed.getTableID(); // 表ID
        String typeID = fixed.getTypeID(); // 编码 1 、2 、3
        String dbTableName = getTableDbName(tableID);
        // 获取 新的colOrder 列
        String colOrder = floatService.getColOrder(fixed.getTableID(), dealType);
        // 获取 更新前数据
        DictTSetFixPO po = entryService.getDataFixByTypeList(tableID, fixed.getTypeID());

        // 排序列
        fixed.setColOrder(colOrder);

        if (operator.equals("insert"))
            is_insert = fixedMapper.insertFixedWhole(fixed); // 插入
        if (operator.equals("update"))
            is_insert = fixedMapper.updateFixedWhole(fixed); // 更新

        if (po != null) {
            String originalCol = po.getFdCodeToCols();
            String fdCodeToCols = fixed.getFdCodeToCols();

            if (ConverTables.isNotNull(originalCol) && ConverTables.isNotNull(fdCodeToCols)) {
                // 如果不相等 需要更新单元格权限
                if (!originalCol.equals(fdCodeToCols)) {
                    String newCellSecu_1 = ""; // 初始化 将原始的 编码对应列 设为 0
                    for (String secu : originalCol.split(","))
                        newCellSecu_1 += "1";

                    List<Map<String, Object>> floatData = floatService.getFloatData(tableID, ""); // 获取 表中数据

                    for (int i = 0; i < floatData.size(); i++) {
                        Map<String, Object> data = (Map<String, Object>) floatData.get(i);
                        String cellSecu = (String) data.get("CELLSECU");
                        String isLeaf = ConverTables.isNotNull(data.get("ISLEAF_" + typeID)) ? (String) data
                                .get("ISLEAF_" + typeID) : "0";

                        cellSecu = floatService.cellSecuByCols(colOrder, originalCol, cellSecu, newCellSecu_1);

                        String newCellSecu_2 = ""; // 根据 不同 级次 组合 0、1
                        for (String secu : fdCodeToCols.split(","))
                            newCellSecu_2 += isLeaf;
                        // 重新 计算 单元格权限
                        cellSecu = floatService.cellSecuByCols(colOrder, fdCodeToCols, cellSecu, newCellSecu_2);

                        // 更新单元格权限
                        if (ConverTables.isNotNull(cellSecu))
                            floatMapper.saveCellSecu((String) data.get("DATAKEY"), cellSecu, dbTableName);
                    }
                }
            }
        }

        if (is_insert > 0)
            flag = "{\"succ\":\"" + fixed.getGuID() + "\"}";
        return flag;
    }

    /**
     * 处理 固定行列表末级编码 返回方式： F6FB05F283EAED49E040A8C020032FA2={DATAKEY=F6FB05F283EAED49E040A8C020032FA2, ISLEAF_1=1,ISLEAF_2=1,ISLEAF_3=1 }
     */
    public Map<String, Object> dataToLeaf(List<Map<String, Object>> isLeafMap) {
        Map<String, Object> tempMap = new HashMap<String, Object>();
        for (Map<String, Object> data : isLeafMap) {
            // 取出DATAKEY
            Map<String, Object> temp = (Map<String, Object>) tempMap.get(data.get("DATAKEY"));
            if (ConverTables.isNotNull(temp)) {
                // 将Map 进行 合并
                Map<String, Object> mergeMap = ConverTables.mergeMap(temp, data);
                tempMap.put((String) data.get("DATAKEY"), mergeMap);
            } else {
                tempMap.put((String) data.get("DATAKEY"), data);
            }
        }
        return tempMap;
    }

    /**
     * 固定行列表 更新单元格权限 参数： floatData 数据库中数据 isLeaf 处理后的是否末级 fdCodeToColsMap 编码对应列 fdCode_len 编码个数 colOrder factor表中的所有显示列 dbTableName 物理表名 tableID 表ID
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void updateCellSecu(List<Map<String, Object>> floatData, Map<String, Object> isLeaf,
            Map<String, Object> fdCodeToColsMap, int fdCode_len, String colOrder, String dbTableName, String tableID) {
        if (ConverTables.isNotNullList(floatData)) {
            // 公式列
            Map<String, String> formula = floatService.getFormulaColumn(tableID);

            for (int i = 0; i < floatData.size(); i++) {
                Map<String, Object> data = (Map<String, Object>) floatData.get(i);
                // 取出 dataKey
                String datakey = (String) data.get("DATAKEY");
                Map<String, Object> leaf = (Map<String, Object>) isLeaf.get(datakey);

                // if(!ConverTables.isNotNull(leaf)) continue;
                String cellSecu = "";
                // 通过 colOrder 将单元格权限组合 0...0
                if (ConverTables.isNotNull(data.get("CELLSECU"))) {
                    cellSecu = (String) data.get("CELLSECU");
                } else {
                    if (ConverTables.isNotNull(colOrder))
                        for (String secu : colOrder.split(","))
                            cellSecu += "1";
                }
                // 获取行号 、 用于获取公式
                String formulaCol = "";
                if (ConverTables.isNotNull(data.get("ORDERID")))
                    formulaCol = formula.get(String.valueOf(data.get("ORDERID")));
                ;

                String stage = ""; // 末级
                for (int j = 1; j <= fdCode_len; j++) {
                    String isLeaf_ = ConverTables.isNotNull(data.get("ISLEAF_" + j)) ? (String) data.get("ISLEAF_" + j)
                            : "0";
                    // 如果 是否末级 为 空 则 CELLSECU 为 0
                    if (ConverTables.isNotNull(leaf)) {
                        if (ConverTables.isNotNull(leaf.get("ISLEAF_" + j)))
                            isLeaf_ = (String) leaf.get("ISLEAF_" + j);
                    }
                    // 获取 不同编码下 对应的列
                    String fdCodeToCols = ConverTables.isNotNull(fdCodeToColsMap.get("fdCodeToCols_" + j)) ? (String) fdCodeToColsMap
                            .get("fdCodeToCols_" + j)
                            : "";

                    // 判断末级编码是否发生改变
                    if (ConverTables.isNotNull(data.get("ISLEAF_" + j))) {
                        if (ConverTables.isNotNull(isLeaf_))
                            stage = data.get("ISLEAF_" + j).equals(isLeaf_) ? "" : isLeaf_;
                    } else {
                        stage = isLeaf_;
                    }

                    if (ConverTables.isNotNull(stage)) {
                        if (ConverTables.isNotNull(fdCodeToCols)) {
                            String newCellSecu = ""; // 每组FDCODE 不同的 单元格权限
                            for (String secu : fdCodeToCols.split(","))
                                newCellSecu += stage;
                            cellSecu = floatService.cellSecuByCols(colOrder, fdCodeToCols, cellSecu, newCellSecu);
                        }
                    }
                }
                // 更新 单元格权限
                if (ConverTables.isNotNull(cellSecu)) {
                    // 公式列始终为 0
                    if (ConverTables.isNotNull(formulaCol)) {
                        String newCellSecu = "";
                        for (String secu : formulaCol.split(","))
                            newCellSecu += "0";
                        cellSecu = floatService.cellSecuByCols(colOrder, formulaCol, cellSecu, newCellSecu);
                    }
                    floatMapper.saveCellSecu(datakey, cellSecu, dbTableName);
                }

            }
        }
    }

    // 获取 名称层次
    public Map<String, Object> getLvlNanmeCol_2(String tableID) {
        Map<String, Object> layerIndent = new HashMap<String, Object>();
        String colOrder = "";
        String lvlNanme = "";
        Map<String, Object> fdCodeToCols = new HashMap<String, Object>();

        List<DictTSetFixPO> fixed = entryService.getDataFixList(tableID);
        if (ConverTables.isNotNullList(fixed)) {
            for (DictTSetFixPO po : fixed) {
                DictTFactorPO factor = dictTFactorService.getDictTFactorByColumnId(po.getLvlNanmeCol());
                if (factor != null)
                    lvlNanme += factor.getDbcolumnname().toUpperCase() + ","; // 层次名称列

                layerIndent.put("layerIndent_" + po.getTypeID(), po.getLayerIndent()); // 层次
                fdCodeToCols.put("fdCodeToCols_" + po.getTypeID(), po.getFdCodeToCols()); // 编码对应列

                if (!ConverTables.isNotNull(colOrder))
                    colOrder = po.getColOrder();
            }
            //
            if (ConverTables.isNotNull(lvlNanme))
                lvlNanme = ConverTables.removeLastChar(lvlNanme, ",");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("layerIndent", layerIndent);
        map.put("colOrder", colOrder);
        map.put("fdCode_len", fixed.size());
        map.put("lvlNanme", lvlNanme);
        map.put("fdCodeToCols", fdCodeToCols);
        return map;
    }

    // 组合SQL 语句
    public String createIsLeafSql(List<Map<String, Object>> updateIsLeaf, String dbTableName) {
        StringBuffer sqlBuffer = new StringBuffer();
        for (int i = 0; i < updateIsLeaf.size(); i++) {
            String statement = "UPDATE " + dbTableName
                    + " SET SYNSTATUS = (CASE WHEN SYNSTATUS = \'2\' THEN \'3\' ELSE SYNSTATUS END),";
            String conditional = ""; // 条件语句
            Map<String, Object> fieldColumn = updateIsLeaf.get(i);
            //
            for (Entry<String, Object> en : fieldColumn.entrySet()) {
                String column = en.getKey();
                Object value = "'" + en.getValue() + "'";
                if (column.equals("DATAKEY")) {
                    conditional += " WHERE " + column + "=" + value + " AND AGENCYID = '*' ;";
                } else {
                    if (!column.contains("FDCODE")) // 不包含FDCODE
                        statement += column + "=" + value + ",";
                }
            }

            statement = ConverTables.removeLastChar(statement, ",");
            sqlBuffer.append(statement += conditional);
        }
        String sqlString = "BEGIN " + sqlBuffer.toString() + " END;";
        System.out.println("Floating table set statement parsed：" + sqlString);

        return sqlString;
    }

    // 发布 浮动表、固定行列表 2015.4.24
    @Override
    public void publishData(String tableID) throws ServiceException {
        String dbTableName = getTableDbName(tableID);
        try {
            fixedMapper.updateSynStatus(dbTableName);
            // 20160523 发布之后调用清除垃圾数据的存储过程
            fixedMapper.clearFloatErrData(tableID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "发布数据出现异常！原因：" + e.getCause().getMessage(), false);
        }
    }

    /**
     * 根据TABLEID 初始化固定行列表
     * @throws Exception
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void initFixData(String tableID) throws ServiceException {
        // 获取固定行列表的名称
        String fixName = getTableDbName(tableID);
        // 首先判断该表内是否已经有数据，如果有先删除再重新生成一遍
        floatService.deleteAllFloatData(tableID, fixName);

        // 根据TableID 查询固定行列表设置信息
        List<DictTSetFixPO> fixs = entryService.getDataFixList(tableID);
        // 取出fdcode编码规则
        List<List<Map<String, Object>>> allList = new ArrayList<List<Map<String, Object>>>();
        for (DictTSetFixPO fix : fixs) {
            String[] layerIndent = fix.getLayerIndent().split(",");
            String num = fix.getTypeID();
            DictTFactorPO po = dictTFactorService.getDictTFactorByColumnId(fix.getLvlNanmeCol());
            // 获取代码表数据
            List<Map<String, Object>> datas = floatService.getCodeTableData(fix.getLvlNanmeCol(), layerIndent.length);

            Map<Integer, String> lvlCodeMap = new HashMap<Integer, String>();

            int previousLvl = 0;
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
                data.put("FDCODE_" + num, fdCode);

                // 向该初始化固定行列表中插入数据
                data.put("ISQZX_" + num, "0");
                data.put("ISDJ_" + num, "0");
                data.put("ISHZ_" + num, "0");
                data.put("ISUPDATE_" + num, "1");
                data.put("GUID_" + num, data.get("GUID"));
                data.put("LEVELNO_" + num, data.get("LEVELNO"));
                data.put("ISLEAF_" + num, data.get("ISLEAF"));
                data.put("numStatus", num);
                // data.put("SUPERID_"+(i+1), data.get("SUPERGUID"));
                // data.put("ISUPDATE_"+(i+1), "1");//默认可写//
                data.put("colName_" + num, po.getDbcolumnname());
            }
            allList.add(datas);
        }

        int maxListOrder = 0;// 最大的list的顺序
        List<Map<String, Object>> maxList = null;// 行数最多的list
        int maxRows = 0;// 最大行数
        for (int zbOrder = 0; zbOrder < allList.size(); zbOrder++) {
            List<Map<String, Object>> list = allList.get(zbOrder);
            int line = list.size();
            if (line > maxRows) {
                maxListOrder = zbOrder;
                maxRows = line;// 取最大的行数 作为orderid
                maxList = list;
            }
        }
        // 用最大list生成TemplateID，以及superid_XX
        List<String> tplList = new ArrayList<String>(maxRows);
        HashMap<Integer, String> lvlTplMap = new HashMap<Integer, String>();// 级次对应tpl
        for (int m = 0; m < maxList.size(); m++) {
            Map<String, Object> tempRow = maxList.get(m);
            String tplID = utilsMapper.getDBUniqueID();
            tempRow.put("TEMPLATEID", tplID);
            tplList.add(tplID);
            //
            int levelNo = Integer.parseInt(tempRow.get("LEVELNO").toString());
            String superID = "0";
            if (levelNo != 1) {
                superID = lvlTplMap.get(levelNo - 1);
            }
            tempRow.put("SUPERID_" + tempRow.get("numStatus"), superID);
            lvlTplMap.put(levelNo, tplID);
        }
        // 给其余的list设置superID
        for (int i = 0; i < allList.size() && i != maxListOrder; i++) {
            List<Map<String, Object>> list = allList.get(i);

            for (int m = 0; m < list.size(); m++) {
                Map<String, Object> tempRow = maxList.get(m);
                String tplID = tplList.get(m);// 每行的TemplateID
                //
                int levelNo = Integer.parseInt(tempRow.get("LEVELNO").toString());
                String superID = "0";
                if (levelNo != 1) {
                    superID = lvlTplMap.get(levelNo - 1);
                }
                tempRow.put("SUPERID_" + tempRow.get("numStatus"), superID);
                lvlTplMap.put(levelNo, tplID);
            }

        }

        List<Map<String, Object>> fixDatas = new ArrayList<Map<String, Object>>();
        for (int j = 0; j < maxRows; j++) {
            Map<String, Object> row = new HashMap<String, Object>();
            row.put("ORDERID", j + 1);
            row.put("AGENCYID", "*");
            row.put("ISTEMPLATE", "1");
            row.put("SYNSTATUS", "0");// 未发布
            row.put("fixName", fixName);
            for (int index = 0; index < allList.size(); index++) {
                List<Map<String, Object>> list = allList.get(index);
                if (j < list.size()) {
                    Map<String, Object> map = list.get(j);
                    Iterator<Entry<String, Object>> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<String, Object> next = it.next();
                        row.put(next.getKey(), next.getValue());
                    }
                } else {
                    Map<String, Object> map = list.get(0);
                    Iterator<Entry<String, Object>> it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<String, Object> next = it.next();
                        row.put(next.getKey(), null);
                    }
                }
            }
            fixDatas.add(row);
        }
        // 向数据库插入数据
        if (null != fixDatas && fixDatas.size() > 0) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("fixName", fixDatas.get(0).get("fixName"));

            if (fixDatas.get(0).containsKey("colName_1")) {
                params.put("colName_1", fixDatas.get(0).get("colName_1"));
            }
            if (fixDatas.get(0).containsKey("colName_2")) {
                params.put("colName_2", fixDatas.get(0).get("colName_2"));
            }
            if (fixDatas.get(0).containsKey("colName_3")) {
                params.put("colName_3", fixDatas.get(0).get("colName_3"));
            }

            List<Map<String, Object>> tfixDatas = null;
            for (Map<String, Object> mm : fixDatas) {
                tfixDatas = new ArrayList<Map<String, Object>>();
                tfixDatas.add(mm);
                params.put("fixDatas", tfixDatas);
                fixedMapper.setFixData(params);
            }
        }
        // 查询出浮动表的数据
        List<Map<String, Object>> fixData = floatService.getFloatData(tableID, "");
        setSecuAndOther(tableID, fixName, fixData);
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
        FixGrid grid = (FixGrid) table;
        if( grid.getTableDBName().indexOf(CFG)< 0){
            grid.setTableDBName(grid.getTableDBName() + CFG);
        }
        super.setTableColumns(grid);
        String tableID = grid.getTableID();
        String dbTableName = grid.getTableDBName();
        if (grid.getColumnList() == null || grid.getColumnList().size() == 0) {
            setTableColumns(grid);
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
                data.put("SYNSTATUS", 0);
            }
        }
        // 新增数据
        addData(grid);
        // 查询出固定行列表表的数据
        List<Map<String, Object>> fixData = floatService.getFloatData(tableID, "");
        // 有新增 更新 末级编码
        if ((table.getInsertValues() != null && table.getInsertValues().size() > 0)
                || (table.getUpdateValues() != null && table.getUpdateValues().size() > 0)) {
            setSecuAndOther(tableID, dbTableName, fixData);
       
        // 保存后执行方法
        afterSaveData(grid);
        }
        return true;
    }

    // 设置更新单元格权限 levelno isleaf
    private void setSecuAndOther(String tableID, String dbTableName, List<Map<String, Object>> floatData)
            throws ServiceException {
        // 用于SUPERID
        Map<String, Object> keyMap = new HashMap<String, Object>();

        // 查询浮动设置表
        Map<String, Object> fixed = getLvlNanmeCol_2(tableID);
        int fdCode_len = (Integer) fixed.get("fdCode_len"); // FDCODE 数量
        String colOrder = (String) fixed.get("colOrder"); // 排序列
        Map<String, Object> fdCodeToColsMap = (Map<String, Object>) fixed.get("fdCodeToCols"); // 编码对应列

        List<Map<String, Object>> isLeafMap = new ArrayList<Map<String, Object>>();

        // 根据FDCODE的 个数 进行更新
        // 根据TableID 查询固定行列表设置信息
        List<DictTSetFixPO> fixs = entryService.getDataFixList(tableID);
        for (DictTSetFixPO po : fixs) {
            String typeID = po.getTypeID();

            // 处理 级次、是否末级 、superId
            Map<String, Object> level = floatService.dataToLeveNo(floatData, keyMap, po.getLayerIndent(), tableID, "_" + typeID);
            // 转为 List<Map<String, Object>>
            List<Map<String, Object>> updateIsLeaf = floatService.isLeafChange(floatData, floatService.selIsLeaf(level, colOrder, "_" + typeID));

            // 更新 级次 、 是否末级
            if (ConverTables.isNotNullList(updateIsLeaf)) {
                for (Map<String, Object> m : updateIsLeaf)
                    isLeafMap.add(m);

                String statement = createIsLeafSql(updateIsLeaf, dbTableName);
                floatMapper.updateIsLeafSql(statement);
            }

        }
        Map<String, Object> isLeaf = dataToLeaf(isLeafMap);
        // 单元格权限
        updateCellSecu(floatData, isLeaf, fdCodeToColsMap, fdCode_len, colOrder, dbTableName, tableID);
    }

    private List<Map<String, Object>> getGridData(Map<String, Object> paramMap, CommonGrid grid)
            throws ServiceException {
        // 获取查询参数
        paramMap = getQueryParamter(grid);
        // 取得排序
        List<DictTSetSortPO> sortList = this.getSortColumns(grid.getTableID());
        // 判断最全的FDCODE
        String isAllSuperId = fixedMapper.getMaxFdCol(paramMap);
        DictTSetSortPO sortPo = new DictTSetSortPO();
        sortPo.setDbColumnName("FDCODE_" + isAllSuperId);
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

    private List<DictTSetSortPO> getSortColumns(String tableID) throws ServiceException {
        List<DictTSetSortPO> sortColumns = entryOuterService.getDataSortList(tableID);
        return sortColumns;
    }

    @Override
    public PageInfo getData(Table table) throws ServiceException {
        FixGrid grid = (FixGrid) table;
        if( grid.getTableDBName().indexOf(CFG)< 0){
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

    // 校验FDCODE 是否书写正确
    @Override
    protected void afterSaveData(Table table) throws ServiceException {
        super.afterSaveData(table);
        String tableID = table.getTableID();
        // 查询出固定行列表表的数据
        List<Map<String, Object>> fixData = floatService.getFloatData(tableID, "");
     
        // 根据TableID 查询固定行列表设置信息
        List<DictTSetFixPO> fixs = entryService.getDataFixList(tableID);
        for (Map<String, Object> data : fixData) {
            for (DictTSetFixPO po : fixs) {
                String typeID = po.getTypeID();
                int len = 0;
                String layerIndent_typeID[] = po.getLayerIndent().split(",");
                String levelStr = "0";
                if (data.get("LEVELNO_" + typeID) != null) {
                    levelStr = data.get("LEVELNO_" + typeID).toString();
                }
                int levelno = Integer.parseInt(levelStr);
                for (int i = 0; i < levelno; i++) {
                    len += Integer.parseInt(layerIndent_typeID[i]);
                }
                if ((data.get("FDCODE_" + typeID) == null && data.get("LEVELNO_" + typeID) != null)
                        || (data.get("FDCODE_" + typeID) != null && len != data.get("FDCODE_" + typeID).toString()
                                .length())) {
                    throw new ServiceException(ExceptionCode.ERR_00000, "【排序序号为" + data.get("ORDERID") + "】的FDCODE_"
                            + typeID + "没有按规则填写！", false);
                }
            }
        }
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
}
