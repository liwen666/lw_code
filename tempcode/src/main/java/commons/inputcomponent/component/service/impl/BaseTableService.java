/**
 * @Title: BaseTableService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类
 * @Revision 6.0 2015-11-23  CAOK
 */

package commons.inputcomponent.component.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTFactorcodePO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTFactorcodeService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.checker.CheckColumn;
import com.tjhq.commons.inputcomponent.commonweb.service.ICommonwebService;
import com.tjhq.commons.inputcomponent.component.dao.IComponentMapper;
import com.tjhq.commons.inputcomponent.constants.DataType;
import com.tjhq.commons.inputcomponent.constants.ShowType;
import com.tjhq.commons.inputcomponent.extend.secu.ReplaceParamter;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.ColumnUtil;
import com.tjhq.commons.inputcomponent.utils.JSTypeUtils;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.secu.po.SecuTColPO;
import com.tjhq.commons.secu.po.SecuTTablePO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.commons.setting.external.service.IFormulaOuterService;

/**
 * @ClassName: BaseTableService
 * @Description: 此类完成表格的公共实现
 * @author: CAOK 2015-11-23 下午03:13:20
 */
@Service("baseTableService")
@Transactional(readOnly = true)
public class BaseTableService extends BaseAbstractCompService {

    /**
     * @Fields logger : Log4j日志记录
     */
    private static Logger logger = Logger.getLogger(BaseTableService.class);

    /**
     * @Fields dictTModelService : 业务表管理服务
     */
    @Resource
    private IDictTModelService dictTModelService;

    /**
     * @Fields dictTFactorService : 业务表列管理服务
     */
    @Resource
    private IDictTFactorService dictTFactorService;

    /**
     * @Fields dictTFactorcodeService : 代码表列管理服务
     */
    @Resource
    private IDictTFactorcodeService dictTFactorcodeService;

    /**
     * @Fields componentMapper : 数据库访问
     */
    @Resource
    private IComponentMapper componentMapper;

    /**
     * @Fields utilMapper : 数据库访问
     */
    @Resource
    private IUtilDAO utilMapper;

    /**
     * @Fields formulaOuterService : 公式服务
     */
    @Resource
    private IFormulaOuterService formulaOuterService;

    /**
     * @Fields entryOuterService : 录入界面定义服务
     */
    @Resource
    private IEntryOuterService entryOuterService;

    /**
     * @Fields dataAuthService : 权限服务
     */
    @Resource
    private IDataAuthService dataAuthService;

    /**
     * @Fields commonwebService : 录入通用服务
     */
    @Resource
    private ICommonwebService commonwebService;

    /**
     * @return dictTModelService
     */
    public IDictTModelService getDictTModelService() {
        return dictTModelService;
    }

    /**
     * @param dictTModelService 要设置的 dictTModelService
     */
    public void setDictTModelService(IDictTModelService dictTModelService) {
        this.dictTModelService = dictTModelService;
    }

    /**
     * @return dictTFactorService
     */
    public IDictTFactorService getDictTFactorService() {
        return dictTFactorService;
    }

    /**
     * @param dictTFactorService 要设置的 dictTFactorService
     */
    public void setDictTFactorService(IDictTFactorService dictTFactorService) {
        this.dictTFactorService = dictTFactorService;
    }

    /**
     * @return dictTFactorcodeService
     */
    public IDictTFactorcodeService getDictTFactorcodeService() {
        return dictTFactorcodeService;
    }

    /**
     * @param dictTFactorcodeService 要设置的 dictTFactorcodeService
     */
    public void setDictTFactorcodeService(IDictTFactorcodeService dictTFactorcodeService) {
        this.dictTFactorcodeService = dictTFactorcodeService;
    }

    /**
     * @return componentMapper
     */
    public IComponentMapper getComponentMapper() {
        return componentMapper;
    }

    /**
     * @param componentMapper 要设置的 componentMapper
     */
    public void setComponentMapper(IComponentMapper componentMapper) {
        this.componentMapper = componentMapper;
    }

    /**
     * @return utilMapper
     */
    public IUtilDAO getUtilMapper() {
        return utilMapper;
    }

    /**
     * @param utilMapper 要设置的 utilMapper
     */
    public void setUtilMapper(IUtilDAO utilMapper) {
        this.utilMapper = utilMapper;
    }

    /**
     * @return formulaOuterService
     */
    public IFormulaOuterService getFormulaOuterService() {
        return formulaOuterService;
    }

    /**
     * @param formulaOuterService 要设置的 formulaOuterService
     */
    public void setFormulaOuterService(IFormulaOuterService formulaOuterService) {
        this.formulaOuterService = formulaOuterService;
    }

    /**
     * @return entryOuterService
     */
    public IEntryOuterService getEntryOuterService() {
        return entryOuterService;
    }

    /**
     * @param entryOuterService 要设置的 entryOuterService
     */
    public void setEntryOuterService(IEntryOuterService entryOuterService) {
        this.entryOuterService = entryOuterService;
    }

    /**
     * @return dataAuthService
     */
    public IDataAuthService getDataAuthService() {
        return dataAuthService;
    }

    /**
     * @param dataAuthService 要设置的 dataAuthService
     */
    public void setDataAuthService(IDataAuthService dataAuthService) {
        this.dataAuthService = dataAuthService;
    }

    /**
     * @return commonwebService
     */
    public ICommonwebService getCommonwebService() {
        return commonwebService;
    }

    /**
     * @param commonwebService 要设置的 commonwebService
     */
    public void setCommonwebService(ICommonwebService commonwebService) {
        this.commonwebService = commonwebService;
    }

    /**
     * 创建一个新的实例 BaseTableService.
     */
    public BaseTableService() {

    }

    @Override
    public boolean saveData(Table table) throws ServiceException {
        if (table.getColumnList() == null || table.getColumnList().size() == 0) {
            setTableColumns(table);
        }
        logger.debug(table.getTableName().concat("[").concat(table.getTableDBName()).concat("]开始保存数据!"));
        return super.saveData(table);
    }

    /**
     * .
     * <p>
     * Title: setTableDefine
     * </p>
     * <p>
     * Description: 设置Table定义
     * </p>
     * @param table 表对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#setTableDefine (com.tjhq.commons.inputcomponent.po.Table)
     */
    protected void setTableDefine(Table table) throws ServiceException {
        // 获取Model
        logger.debug(table.getTableID().concat("开始加载表定义信息！"));
        DictTModelPO dictTModelPO = null;
        try {
            dictTModelPO = dictTModelService.getDictTModelByID(table.getTableID(), false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00001, table.getTableID(), true);
        }

        if (dictTModelPO == null) {
            throw new ServiceException(ExceptionCode.INP_00001, table.getTableID(), true);
        }

        table.setAppID(dictTModelPO.getAppid());
        table.setTableDBName(dictTModelPO.getDbtablename());
        table.setTableName(dictTModelPO.getName());
        table.setShortTitle(dictTModelPO.getShorttitle());
        table.setVisiable(ToolUtil.toBoolean(dictTModelPO.getIsshow()));
        table.setReadOnly(ToolUtil.toBoolean(dictTModelPO.getIsReadOnly()));
        table.setTotal(ToolUtil.toBoolean(dictTModelPO.getIssumtab()));

        // 设置表列信息
        logger.debug(table.getTableID().concat("开始加载列定义信息！"));
        setTableColumns(table);

        logger.debug(table.getTableID().concat("表列信息加载完成，信息如下：\n").concat(table.toString()));
    }

    /**
     * .
     * <p>
     * Title: setTableColumns
     * </p>
     * <p>
     * Description: 设置Table列定义
     * </p>
     * 获取表格列信息 此方法可单独调用，避免列信息在前后台来回传递，以提高前后台调用效率
     * @param table 表格对象
     * @throws ServiceException 业务异常
     */
    protected void setTableColumns(Table table) throws ServiceException {
        if (table.getColumnList() != null && table.getColumnList().size() > 0) {
            return;
        }
        // 表物理主键
        List<DictTFactorPO> primaryKeys = null;
        try {
            // 当前表处理类型在默认列中存在"DATAKEY"、"AGENCYID"，主键即为"DATAKEY"、"AGENCYID"，也可以是其中一个
            primaryKeys = dictTFactorService.getDictTFactorsPk(table.getTableID());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00003, "加载表" + table.getTableName() + "主键信息出错!", false);
        }
        // 如果没有主键信息，数据无法更新
        if (primaryKeys == null || primaryKeys.size() == 0) {
            throw new ServiceException(ExceptionCode.INP_00206, "表" + table.getTableName() + "没有设置主键信息!", false);
        }

        logger.debug("表" + table.getTableID() + "主键个数为" + primaryKeys.size());

        // 取树形结构的FACTOR
        List<DictTFactorPO> dictTFactorPOs = null;
        try {
            dictTFactorPOs = dictTFactorService.getDictTFactorsByTableIdForTree(table.getTableID());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00004, "加载表" + table.getTableName() + "列信息出错!", false);
        }

        if (dictTFactorPOs == null) {
            throw new ServiceException(ExceptionCode.INP_00207, "没有加载到表" + table.getTableName() + "列信息!", false);
        }

        List<Column> columnList = new ArrayList<Column>();
        StringBuffer defaultValueSql = new StringBuffer();
        for (DictTFactorPO parentPo : dictTFactorPOs) {
            // 中间级下面没有末级节点，则不解析
            if ("0".equals(parentPo.getIsleaf())
                    && (parentPo.getDictTFactorList() == null || parentPo.getDictTFactorList().size() == 0)) {
                continue;
            }
            createColumn(parentPo, null, columnList, primaryKeys);

            String defaltColumnValueSql = ColumnUtil.getDefaultValueSql(parentPo);
            if (StringUtils.isNotEmpty(defaltColumnValueSql)) {
                if (defaultValueSql.toString().length() > 0) {
                    defaultValueSql.append(" UNION ALL ").append(defaltColumnValueSql);
                } else {
                    defaultValueSql.append(defaltColumnValueSql);
                }
            }
        }

        table.setColumnList(columnList);

        Map<String, Map<String, String>> defaultValueMap = getDefaultValMap(defaultValueSql.toString());
        List<Column> leafColumnList = TableUtil.getLeafColumnList(table);
        Column col = null;
        Map<String, String> colValueMap = null;

        for (int i = 0; i < leafColumnList.size(); i++) {
            col = leafColumnList.get(i);
            colValueMap = defaultValueMap.get(col.getColumnDBName());
            if (colValueMap != null) {
                col.setDefaultShowValue(colValueMap.get("DEFAULTSHOWVALUE"));
                col.setDefaultValue(colValueMap.get("DEFAULTVALUE"));
            }
        }

    }

    /**
     * . 按树形组织列信息
     * @param po 当前列信息
     * @param parentColumn 父级列信息
     * @param colList 列集合
     * @param primaryKeys 表主键信息
     * @throws ServiceException 业务异常
     */
    private void createColumn(DictTFactorPO po, Column parentColumn, List<Column> colList,
                              List<DictTFactorPO> primaryKeys) throws ServiceException {
        Column column = getNewColumn();
        // 实现FACTOR 向COLUMN的转化
        copyColumnProperties(column, po, primaryKeys);

        // 如果父级不可见，则子级全部隐藏
        if (parentColumn != null && !parentColumn.isVisible()) {
            column.setVisible(false);
        }

        // 子节点
        if (parentColumn != null) {
            column.setParentColumn(parentColumn);
            parentColumn.getChildrenColumnList().add(column);
        } else {
            // 父节点
            colList.add(column);
        }

        if (po.getDictTFactorList() != null && po.getDictTFactorList().size() > 0) {
            // 创建子对象容器
            column.setChildrenColumnList(new ArrayList<Column>());
            boolean parentVisable = false;
            for (int i = 0; i < po.getDictTFactorList().size(); i++) {
                DictTFactorPO subPo = po.getDictTFactorList().get(i);
                if (ToolUtil.toBoolean(subPo.getIsvisible())) {
                    parentVisable = true;
                }
                if (i == po.getDictTFactorList().size()) {
                    column.setVisible(parentVisable);
                }
                createColumn(subPo, column, colList, primaryKeys);
            }
        }
    }

    /**
     * . 创建新列 如果是表格返加Column 如果了Form返回FormItem
     * @return 列对象
     * @throws
     */
    protected Column getNewColumn() {
        return new Column();
    }

    /**
     * . 实现FACTOR 向COLUMN的转化
     * @param column 列信息
     * @param dictFactorPo 列定义信息
     * @param primaryKeys 表主键信息
     * @throws ServiceException 业务异常
     */
    @SuppressWarnings("unchecked")
    private void copyColumnProperties(Column column, DictTFactorPO dictFactorPo, List<DictTFactorPO> primaryKeys)
            throws ServiceException {
        // 列ID
        column.setColumnID(dictFactorPo.getColumnid());
        // 级次码（1,2,3,4,5..）
        column.setLevelNo(dictFactorPo.getLevelno());
        // 是否为叶节点
        column.setLeaf(ToolUtil.toBoolean(dictFactorPo.getIsleaf()));
        // 设置逻辑主键
        column.setLogicKey(ToolUtil.toBoolean(dictFactorPo.getIskey()));
        // 是否物理主键
        column.setKey(getIsPKey(dictFactorPo, primaryKeys));
        // 是否可见
        column.setVisible(ToolUtil.toBoolean(dictFactorPo.getIsvisible()));
        column.setColumnDBName(dictFactorPo.getDbcolumnname());
        column.setColumnName(dictFactorPo.getName());
        if (dictFactorPo.getAlias() == null || dictFactorPo.getAlias().trim().length() == 0) {
            column.setAlias(dictFactorPo.getName());
        } else {
            column.setAlias(dictFactorPo.getAlias());
        }

        // 是否允许空
        column.setNullable(ToolUtil.toBoolean(dictFactorPo.getNullable()));
        // 是否允许编辑
        column.setReadOnly(!ToolUtil.toBoolean(dictFactorPo.getIsupdate()));
        // 列允许的最大长度
        if (dictFactorPo.getDatalength() == null) {
            column.setDataLength(0);
        } else {
            column.setDataLength(dictFactorPo.getDatalength());
        }
        // 精度
        if (dictFactorPo.getScale() == null) {
            column.setScale(0);
        } else {
            column.setScale(dictFactorPo.getScale());
        }
        // csId
        column.setRefTableID(dictFactorPo.getCsid());

        if (dictFactorPo.getDatatype().toString().equals(DataType.REF)
                && StringUtils.isEmpty(dictFactorPo.getCsid())) {
            logger.error("columnID:" + column.getColumnID());
            throw new ServiceException(ExceptionCode.INP_00208, "列 " + column.getAlias() + " 没有设置引用代码表!", false);
        }

        if (!StringUtils.isEmpty(dictFactorPo.getCsid()) && StringUtils.isEmpty(dictFactorPo.getCsdbtablename())) {
            logger.error("columnID:" + column.getColumnID());
            throw new ServiceException(ExceptionCode.INP_00209, "列 " + column.getAlias() + " 对应的引用代码表不存在!", false);
        }

        column.setRefTableDBName(dictFactorPo.getCsdbtablename());
        // 排序id
        if (dictFactorPo.getOrderid() == null) {
            column.setOrderID(0);
        } else {
            column.setOrderID(dictFactorPo.getOrderid());
        }
        // 列索引
        column.setColumnIndex(dictFactorPo.getColumnindex());
        // 宽度
        column.setWidth(ToolUtil.getInteger(dictFactorPo.getShowwidth()));
        // 数据源ID
        column.setDataSourceID(dictFactorPo.getDeid());
        // 数据类型
        // column.setDataType(String.valueOf((dictFactorPo.getDatatype())));
        column.setDataType(JSTypeUtils.getJSDateType(String.valueOf(dictFactorPo.getDatatype())));
        // 是否合计行
        column.setSum(ToolUtil.toBoolean(dictFactorPo.getIssum()));
        // 界面显示处理方式
        column.setMask(dictFactorPo.getColformat());
        column.setRegular(ToolUtil.toBoolean(dictFactorPo.getIsregex()));
        column.setRegExpression(dictFactorPo.getRegexpr());
        column.setRegInfo(dictFactorPo.getRegexprinfo());

        // 系统扩展属性
        column.setSysExtPro(dictFactorPo.getExtprop());

        if (dictFactorPo.getColtips() != null) {
            column.setTips(dictFactorPo.getColtips());
        }
        // xType
        column.setShowType(JSTypeUtils.getColumnJSXtype(dictFactorPo.getShowformat()));
        column.setHref(ToolUtil.toBoolean(dictFactorPo.getIshref()));
        // 设置超链
        if (column.isHref()) {
            column.setHrefUrl(dictFactorPo.getHrefloc());
            String paramID = dictFactorPo.getHrefparmid();
            column.setHrefParms(getHrefParam(paramID));
        }
        column.setCheckAll(BooleanUtils.toBoolean(dictFactorPo.getParentNodeCanCheck(), "1", "0"));
        // 显示掩码
        // column.setColFormat(dictFactorPo.getColformat());
        // 是否为虚列
        column.setVirtual(ToolUtil.toBoolean(dictFactorPo.getIsvirtual()));
        if (ShowType.SHOW_TYPE_RADIO.equals(column.getShowType())) {
            List<Map<String, String>> dataList = null;
            try {
                dataList = (List<Map<String, String>>) commonwebService.getReferenceData(column.getColumnID(),
                        null, null, "radio");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.INP_00210, "加载列 " + column.getAlias() + " 引用代码表数据出错!",
                        false);
            }
            column.setDataList(dataList);
        }
        if (ShowType.SHOW_TYPE_CHECK.equals(column.getShowType())) {
            List<Map<String, String>> dataList = null;
            try {
                dataList = (List<Map<String, String>>) commonwebService.getReferenceData(column.getColumnID(),
                        null, null, "checkbox");
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.INP_00210, "加载列 " + column.getAlias() + " 引用代码表数据出错!",
                        false);
            }
            column.setDataList(dataList);
        }
        if (ToolUtil.toBoolean(dictFactorPo.getIsbandcol())) {
            // 设定绑定列
            column.setBindcol(true);
            // 绑定列ID
            column.setBindRefColID(dictFactorPo.getBandcolumnid());
            // 绑定列字段
            String bandrefdwcol = dictFactorPo.getBandrefdwcol();
            DictTFactorcodePO bandCodePo = null;
            try {
                bandCodePo = dictTFactorcodeService.getDictTFactorcodePOByColumnId(bandrefdwcol);
                column.setBindRefCodeTableCol(bandCodePo.getDbcolumnname());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.INP_00219, "加载列 " + column.getAlias() + " 绑定信息出错!", false);
            }
            
        }

        // 虚拟列和绑定列默认不可编辑
        if (column.isBindcol() || column.isVirtual()) {
            column.setReadOnly(true);
        }

        if (logger.isDebugEnabled()) {
            CheckColumn.getInstance().check(column);
        }

    }

    /**
     * . 判断当前列是否物理主键
     * @param dictFactorPo 当前列
     * @param primaryKeys 主键列集合
     * @return 当前列是否为主键
     * @throws
     */
    private boolean getIsPKey(DictTFactorPO dictFactorPo, List<DictTFactorPO> primaryKeys) {
        if (primaryKeys == null) {
            return false;
        }
        for (DictTFactorPO keyPO : primaryKeys) {
            if (keyPO.getColumnid().equals(dictFactorPo.getColumnid())) {
                return true;
            }
        }

        return false;
    }

    /**
     * . 取得默认值，并将其封装在一个MAP中，以COLUMNNAME为KEY
     * @param sql 默认值查询SQL
     * @return 默认值
     * @throws ServiceException 业务异常
     */
    private Map<String, Map<String, String>> getDefaultValMap(String sql) throws ServiceException {
        Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
        if (!StringUtils.isEmpty(sql.trim())) {
            List<Map<String, Object>> list = null;
            try {
                logger.debug("加载表默认值：" + sql);
                list = utilMapper.queryForList(sql);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.INP_00211, "加载表默认值出错!", false);
            }
            Map<String, String> valueMap = null;
            for (Map<String, Object> map : list) {
                valueMap = new HashMap<String, String>();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    valueMap.put(entry.getKey(), (String) entry.getValue());
                }
                resultMap.put((String) map.get("COLUMNNAME"), valueMap);
            }
            return resultMap;
        }
        return resultMap;

    }

    /**
     * . 通过参数ID到超链参数表获取参数列表
     * @param paramID 超链参数ID
     * @return 参数MAP
     * @throws ServiceException 业务异常
     */
    private Map<String, String> getHrefParam(String paramID) throws ServiceException {
        List<Map<String, String>> parameterList = null;
        try {
            parameterList = componentMapper.getHrefParmList(paramID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00212, "加载超链参数列表出错!", false);
        }
        Map<String, String> parameterMap = new HashMap<String, String>();

        if (parameterList == null) {
            return parameterMap;
        }

        for (Map<String, String> map : parameterList) {
            if (map.get("PARMNAME") == null || map.get("PARMCON") == null) {
                continue;
            }
            parameterMap.put(map.get("PARMNAME"), map.get("PARMCON"));
        }
        return parameterMap;
    }

    /**
     * .
     * <p>
     * Title: setTableFormula
     * </p>
     * <p>
     * Description: 设置公式信息
     * </p>
     * @param table 表格对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#setTableFormula (com.tjhq.commons.inputcomponent.po.Table)
     */
    protected void setTableFormula(Table table) throws ServiceException {
        String tableId = table.getTableID();
        Map<String, List<String>> formulaSettingMap = null;
        try {
            formulaSettingMap = getFormulaSettingMap(tableId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00213, "加载表" + table.getTableName() + "公式信息出错!", false);
        }
        table.setFormulaList(formulaSettingMap);

    }

    /**
     * . 取得设置的公式列
     * @param tableID 业务表ID
     * @return 返回公式列及公式引用的列
     */
    private Map<String, List<String>> getFormulaSettingMap(String tableID) {
        Map<String, List<String>> formulaSettingMap = formulaOuterService.getForComCol_RefColumn(tableID);
        return formulaSettingMap;
    }

    /**
     * .
     * <p>
     * Title: setTableRelatedColumns
     * </p>
     * <p>
     * Description: 加载表级联关系
     * </p>
     * @param table 表对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#setTableRelatedColumns (com.tjhq.commons.inputcomponent.po.Table)
     */
    protected void setTableRelatedColumns(Table table) throws ServiceException {
        Map<String, List<String>> relatedColumnMap = null;
        try {
            relatedColumnMap = getRelatedColListMap(table.getTableID());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00214, "加载表" + table.getTableName() + "级联引用信息出错!", false);
        }
        table.setRelatedCoumnList(relatedColumnMap);

    }

    /**
     * . 将关联列设置为以关联列ID为KEY，被关联列的ID存入LIST
     * @param tableID 业务表ID
     * @return Map<colID, refIDs> 列对应引用列
     * @throws Exception 获取级联引用错误
     */
    private Map<String, List<String>> getRelatedColListMap(String tableID) throws Exception {
        List<DictTSetRefrelaPO> dictTSetRefrelaPOs = entryOuterService.getDataRefrelaList(tableID);
        Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
        for (DictTSetRefrelaPO po : dictTSetRefrelaPOs) {
            String columnID = po.getColumnID();
            List<String> refList = resultMap.get(columnID);
            List<String> list = null;
            // 如果没有存过该列的被关联列
            if (refList == null) {
                list = new ArrayList<String>();
            } else {
                list = refList;
            }
            list.add(po.getCondColumnID());
            resultMap.put(columnID, list);
        }
        return resultMap;
    }

    /**
     * .
     * <p>
     * Title: setTableSecu
     * </p>
     * <p>
     * Description: 设置表权限
     * </p>
     * @param table 表对象
     * @param userID 用户ID
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#setTableSecu (com.tjhq.commons.inputcomponent.po.Table, java.lang.String)
     */
    protected void setTableSecu(Table table, String userID) throws ServiceException {
        String tableID = table.getTableID();
        String appID = table.getAppID();

        logger.debug(table.getTableID().concat("开始设置表权限信息！"));

        SecuTTablePO secuTTablePO = null;
        if (table.getTaskID() != null && table.getTaskID().length() > 0) {
            // 如果当前流程不需要控制权限
            if ("0".equals(getDataAuthService().isContrTableSecu(tableID, table.getTaskID()))) {
                table.setRowWriteSecu("'3'");
                //table.setReadOnly(false);
                logger.debug(table.getTableID().concat("不需要控制表权限！"));
                return;
            }

        }

        try {
            secuTTablePO = getDataAuthService().getTableAuthByUser(tableID, userID, null, appID, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00002, tableID.concat(" ").concat(userID).concat(" ")
                    .concat(appID), true);
        }
        
        try {
            table.setColumnLimitList(getDataAuthService().getColumnLimitList(userID, tableID));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00005, tableID.concat(" ").concat(userID).concat(" ")
                    .concat(appID), true);
        }

        // 如果没有设置权限 则为最大权限可写
        if (null == secuTTablePO.getSecuTRowReadOnly() || "".equals(secuTTablePO.getSecuTRowReadOnly().trim())) {
            table.setRowWriteSecu("'3'");
        } else {
            // CK 修改于2016-5-13 把权限返回的条件拼接CASE WHEN语句
            table.setRowWriteSecu(MessageFormat.format(" CASE WHEN {0} THEN '''2''' ELSE '''3''' END ",
                    secuTTablePO.getSecuTRowReadOnly()));
        }
        
        // CK 修改于2016-10-09 替换行权限自定义参数
        table.setRowWriteSecu(ReplaceParamter.execute(table.getRowWriteSecu(), table.getExtProperties()));

        if (null != secuTTablePO.getSecuTRow() && !"".equals(secuTTablePO.getSecuTRow().trim())) {
            table.setRowVisibleSecu(MessageFormat.format(" ( NOT {0} ) ", secuTTablePO.getSecuTRow()));
        }
        
        // CK 修改于2016-10-09 替换行权限自定义参数
        table.setRowVisibleSecu(ReplaceParamter.execute(table.getRowVisibleSecu(), table.getExtProperties()));

        // 表权限: 3可写、2只读、1不可见
        String baseSecu = secuTTablePO.getBaseSecu();
        if ("1".equals(baseSecu)) {
            table.setVisiable(false);
        }
        if ("2".equals(baseSecu)) {
            table.setReadOnly(true);
        }
        //if ("3".equals(baseSecu)) {
        //    table.setReadOnly(false);
        //}
        
        String canAdd = secuTTablePO.getExtAdd();
        if (!table.isReadOnly() && "1".equals(canAdd)) {
            table.setCanAdd(true);
        } else {
            table.setCanAdd(false);
        }

        String canDel = secuTTablePO.getExtDel();
        if (!table.isReadOnly() && "1".equals(canDel)) {
            table.setCanDelete(true);
        } else {
            table.setCanDelete(false);
        }

        String canModify = secuTTablePO.getExtUpdate();
        if (!table.isReadOnly() && "1".equals(canModify)) {
            table.setCanModify(true);
        } else {
            table.setCanModify(false);
        }

        // 设置列权限
        setColumnsSecu(table, secuTTablePO.getSecuTCols(), userID);

        // 设置列上下级关系
        setColumnSecu(table.getColumnList());

        logger.debug(table.getTableID().concat("表权限设置完成，信息如下：\n").concat(table.toString()));

    }

    /**
     * . 设置表列权限
     * @param table 表格
     * @param columnAuthMap 列权限信息
     * @param userID 用户ID
     * @throws ServiceException 业务异常
     */
    protected void setColumnsSecu(Table table, Map<String, SecuTColPO> columnAuthMap, String userID)
            throws ServiceException {
        List<Column> columns = TableUtil.getTiledColumnList(table);
        for (Column column : columns) {
            SecuTColPO secuTcolPO = (SecuTColPO) columnAuthMap.get(column.getColumnID());
            // 若存在则在设置里对列进行过权限设置
            if (secuTcolPO != null) {
                String baseSecu = secuTcolPO.getBaseSecu();
                // 不可见
                if ("1".equals(baseSecu)) {
                    column.setVisible(false);
                }
                // 只读
                if ("2".equals(baseSecu)) {
                    column.setReadOnly(true);
                }
                // "3" 可写   默认

            }
        }

    }

    /**
     * . 设置列上下级显示隐藏关系
     * @param columnList 列集合
     * @throws
     */
    protected void setColumnSecu(List<Column> columnList) {
        for (Column column : columnList) {
            if (column.getChildrenColumnList() != null && column.getChildrenColumnList().size() > 0) {
                setColumnSecu(column, column.getChildrenColumnList());
            }
        }
    }

    /**
     * . 设置列上下级显示隐藏关系
     * @param parent 父级列
     * @param columnList 列集合
     */
    protected void setColumnSecu(Column parent, List<Column> columnList) {
        boolean isVisable = false;
        for (int i = 0; i < columnList.size(); i++) {
            Column column = columnList.get(i);
            if (!parent.isVisible()) {
                column.setVisible(false);
            }
            if (column.isVisible()) {
                isVisable = true;
            }
            if (i == columnList.size() - 1) {
                parent.setVisible(isVisable);
            }
            if (column.getChildrenColumnList() != null && column.getChildrenColumnList().size() > 0) {
                setColumnSecu(column, column.getChildrenColumnList());
            }
        }
    }

    /**
     * .
     * <p>
     * Title: validateNullAndReg
     * </p>
     * <p>
     * Description: 验证空和正则表达式
     * </p>
     * @param table 表格对象
     * @return 是否校验通过
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#validateNullAndReg (com.tjhq.commons.inputcomponent.po.Table)
     */
    protected boolean validateNullAndReg(Table table) throws ServiceException {
        List<Column> columnList = TableUtil.getLeafColumnList(table);
        Map<String, Column> colsMap = new HashMap<String, Column>();
        for (Column col : columnList) {
            colsMap.put(col.getColumnDBName(), col);
        }

        return validateList(table, table.getInsertValues(), colsMap, "insert")
                && validateList(table, table.getUpdateValues(), colsMap, "update");
    }

    /**
     * . 验证空和正则表达式
     * @param values 进行验证的值
     * @param colsMap 进行验证的列
     * @param type 插入和修改
     * @return 是否验证通过
     * @throws ServiceException 业务异常
     * @throws
     */
    private boolean validateList(Table table, List<Map<String, Object>> values, Map<String, Column> colsMap,
                                 String type) throws ServiceException {
        boolean result = true;
        Column column = null;
        Object value = null;
        for (Map<String, Object> dataMap : values) {
            for (Map.Entry<String, Column> columnEntry : colsMap.entrySet()) {
                column = columnEntry.getValue();
                value = null;
                if ("update".equals(type) && !dataMap.containsKey(column.getColumnDBName())) {
                    continue;
                }
                if (column.isVirtual() || column.isBindcol()) {
                	 continue;
                }
                if (dataMap.containsKey(column.getColumnDBName())) {
                    value = dataMap.get(column.getColumnDBName());
                }
                // 判断空值情况
                if (!column.isNullable()
                        && (column.getDefaultValue() == null || column.getDefaultValue().trim().equals(""))
                        && (value == null || ColumnUtil.replaceChar(value.toString()).trim().equals(""))) {
                    result = false;
                    throw new ServiceException(ExceptionCode.INP_00201, "表 " + table.getTableName() + " 列 "
                            + column.getColumnName() + "不可为空或格式不正确！", false);
                }
                // 判断值长度
                try {
                    if (value != null && value.toString().getBytes("GBK").length > column.getDataLength()) {
                        throw new ServiceException(ExceptionCode.INP_00202, "表 " + table.getTableName() + " 列 "
                                + column.getColumnName() + "最大长度为" + column.getDataLength() + "！", false);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 判断正则
                String regExpression = column.getRegExpression();
                if (column.isRegular() && value != null && regExpression != null && !regExpression.equals("")) {
                    Pattern pattern = Pattern.compile(regExpression);
                    Matcher match = pattern.matcher(value.toString());
                    result = match.matches();
                    if (!result) {
                        result = false;
                        String regInfo = column.getRegInfo();
                        if (regInfo != null && !"".equals(regInfo)) {
                            throw new ServiceException(ExceptionCode.INP_00203, regInfo, false);
                        } else {
                            throw new ServiceException(ExceptionCode.INP_00203, "表 " + table.getTableName() + " 列 "
                                    + column.getColumnName() + "与正则表达式不匹配！", false);
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * .
     * <p>
     * Title: validateLkRepeat
     * </p>
     * <p>
     * Description: 批量校验数据
     * </p>
     * @param table 表对象
     * @return 是否校验通过
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#validateLkRepeat (com.tjhq.commons.inputcomponent.po.Table)
     */
    @Deprecated
    protected boolean validateLkRepeat(Table table) throws ServiceException {
        boolean flag = true;
        List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
        values.addAll(table.getInsertValues());
        values.addAll(table.getUpdateValues());
        if (values.size() == 0) {
            return flag;
        }

        int pkCount = TableUtil.getPkList(table).size();
        int lkCount = TableUtil.getLkList(table).size();
        // 如果没有设置逻辑主键 则不进行校验
        if (lkCount == 0) {
            return flag;
        }

        Map<String, Object> paramMap = null;
        Map<String, Object> dataMap = null;
        List<Map<String, List<Map<String, String>>>> needverifyList = new ArrayList<Map<String, List<Map<String, String>>>>();
        List<Map<String, List<Map<String, String>>>> list = TableUtil.getKeySql(table, table.getUpdateValues());
        for (Map<String, List<Map<String, String>>> row : list) {

            // 物理主键不全 系统不校验
            if (pkCount > 0 && (row.get("pks") == null || row.get("pks").size() != pkCount)) {
                continue;
            }

            // 逻辑主键不全 系统根物理主键补全逻辑主键
            // 此次没有修改逻辑主键
            if (row.get("lks") == null) {
                continue;
            }
            if (row.get("lks").size() != lkCount) {
                paramMap = new HashMap<String, Object>();
                paramMap.put("tableName", table.getTableDBName());
                paramMap.put("logicKeys", TableUtil.getLkList(table));
                paramMap.put("primaryKes", row.get("pks"));
                dataMap = componentMapper.getDataLogicKeyByPk(paramMap);
                for (Map<String, String> lkMap : row.get("lks")) {
                    if (dataMap.containsKey(lkMap.get("name"))) {
                        dataMap.remove(lkMap.get("name"));
                    }
                }
                row.get("lks").addAll(TableUtil.getLkSql(table, dataMap));
            }

            needverifyList.add(row);
        }

        list = TableUtil.getKeySql(table, table.getInsertValues());
        for (Map<String, List<Map<String, String>>> row : list) {
            // 逻辑主键不全 系统不校验
            if (row.get("lks") == null || row.get("lks").size() != lkCount) {
                continue;
            }
            // 新增数据，不用校验物理主键
            row.remove("pks");
            needverifyList.add(row);
        }

        if (needverifyList.size() > 0) {
            paramMap = new HashMap<String, Object>();
            paramMap.put("tableName", table.getTableDBName());
            paramMap.put("list", needverifyList);
            Integer num = null;
            try {
                num = componentMapper.isHasRepeateDataAll(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.INP_00204, "逻辑主键" + TableUtil.getLkNames(table) + "查询错误!",
                        false);
            }
            if (num != null && num > 0) {
                flag = false;
                throw new ServiceException(ExceptionCode.INP_00204, "逻辑主键" + TableUtil.getLkNames(table) + "重复!",
                        false);
            }
        }
        return flag;
    }

    /**
     * .
     * <p>
     * Title: addData
     * </p>
     * <p>
     * Description: 新增数据保存
     * </p>
     * @param table 表对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#addData (com.tjhq.commons.inputcomponent.po.Table)
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected void addData(Table table) throws ServiceException {
        if (table.getInsertValues() == null || table.getInsertValues().size() == 0) {
            return;
        }

        if (logger.isDebugEnabled()) {
        	logger.debug(table.getTableID().concat("新增数据").concat(String.valueOf(table.getInsertValues().size()))
                    .concat("行!"));
        }

        validateNewData(table, table.getInsertValues());
        
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        for (Map<String, Object> row : table.getInsertValues()) {
            dataList.add(TableUtil.getInsertSql(table, row));
        }
        
        try {
            componentMapper.batchInsert(dataList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00101, "表 " + table.getTableName() + " 数据新增插入错误!",
                    false);
        }
    }

    /**
     * . 校验新增数据
     * @param table 表对象
     * @param row 行
     * @return 是否校验通过
     * @throws ServiceException 业务异常
     * @throws
     */
    protected boolean validateNewData(Table table, final List<Map<String, Object>> rows) throws ServiceException {
        boolean flag = true;

        int lkCount = TableUtil.getLkList(table).size();
        // 如果没有设置逻辑主键 则不进行校验
        if (lkCount == 0) {
            return flag;
        }
        
        List<Map<String, String>> logicKeyMapList = null;
        LinkedList<String> logicKeyList =  new LinkedList<String>(TableUtil.getLkList(table));
        List<String> rowLogicValues = new ArrayList<String>();
        List<Map<String, List<Map<String, String>>>> needverifyList = new ArrayList<Map<String, List<Map<String, String>>>>();
        Map<String, List<Map<String, String>>> tempMap = null;
        StringBuffer logicBuffer = null;
        for ( Map<String, Object> row : rows) {
        	logicKeyMapList = TableUtil.getLkSql(table, row);
        	// 逻辑主键不全 系统不校验
            if (logicKeyMapList.size() == 0 || logicKeyMapList.size() != lkCount) {
                continue;
            }
            
            logicBuffer = new StringBuffer();
            for (String logicKey : logicKeyList) {
        		logicBuffer.append(String.valueOf(row.get(logicKey))).append("||");
        	}
        	
        	if (rowLogicValues.contains(logicBuffer.toString())) {
        		throw new ServiceException(ExceptionCode.INP_00204, "表 " + table.getTableName() + " 逻辑主键"
                        + TableUtil.getLkNames(table) + "重复！", false);
        	}
        	
        	rowLogicValues.add(logicBuffer.toString());
        	
            tempMap = new HashMap<String, List<Map<String, String>>>();
            tempMap.put("lks", logicKeyMapList);
            needverifyList.add(tempMap);
            
        }
        
        if (needverifyList.size() == 0) {
        	return flag;
        }
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tableName", table.getTableDBName());
        paramMap.put("list", needverifyList);
        int num = 0;
        try {
            num = componentMapper.isHasRepeateDataAll(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00204, "表 " + table.getTableName() + " 逻辑主键"
                    + TableUtil.getLkNames(table) + "查询错误!", false);
        }
        if (num > 0) {
            flag = false;
            throw new ServiceException(ExceptionCode.INP_00204, "表 " + table.getTableName() + " 逻辑主键"
                    + TableUtil.getLkNames(table) + "重复！", false);
        }
        return flag;
    }

    /**
     * .
     * <p>
     * Title: updateData
     * </p>
     * <p>
     * Description: 修改数据 修改前需要根据逻辑主键做数据重复的验证
     * </p>
     * @param table 表格对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#updateData (com.tjhq.commons.inputcomponent.po.Table)
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    protected void updateData(Table table) throws ServiceException {
        if (table.getUpdateValues() == null || table.getUpdateValues().size() == 0) {
            return;
        }
        
        if (logger.isDebugEnabled()) {
        	logger.debug(table.getTableID().concat("修改数据").concat(String.valueOf(table.getUpdateValues().size()))
                    .concat("行！"));
        }

        // 检查数据物理主键，防止勿改
        validatePk(table, table.getUpdateValues());
        
        validateExistsData(table, table.getUpdateValues());

        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        for (Map<String, Object> row : table.getUpdateValues()) {
            dataList.add(TableUtil.getUpdateSql(table, row));
        }
        
        try {
            componentMapper.batchUpdate(dataList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00102, "表 " + table.getTableName() + " 数据修改更新错误!",
                    false);
        }
    }
    
    /**
     * . 校验数据主键是否重复
     * @param table 表对象
     * @param row 行
     * @return 是否重复
     * @throws ServiceException 业务异常
     */
    protected boolean validateExistsData(Table table, final List<Map<String, Object>> rows) throws ServiceException {
        boolean flag = true;

        int pkCount = TableUtil.getPkList(table).size();
        int lkCount = TableUtil.getLkList(table).size();
        // 如果没有设置逻辑主键 则不进行校验
        if (lkCount == 0) {
            return flag;
        }

        Map<String, Object> paramMap = null;
        Map<String, Object> dataMap = null;
        List<Map<String, List<Map<String, String>>>> needverifyList = new ArrayList<Map<String, List<Map<String, String>>>>();
        Map<String, List<Map<String, String>>> keyMap = null;
        LinkedList<String> logicKeyList =  new LinkedList<String>(TableUtil.getLkList(table));
        List<String> rowLogicValues = new ArrayList<String>();
        StringBuffer logicBuffer = null;
        Map<String, Object> newRow = null;
        for (Map<String, Object> row : rows) {
        	keyMap = TableUtil.getKeySql(table, row);
        	// 物理主键不全 系统不校验
            if (pkCount > 0 && (keyMap.get("pks") == null || keyMap.get("pks").size() != pkCount)) {
                continue;
            }
            
            // 此次没有修改逻辑主键
            if (keyMap.get("lks") == null) {
            	continue;
            }
            
            newRow = new HashMap<String, Object>();
            newRow.putAll(row);
            
            //根据物理主键补全逻辑主键
            if (keyMap.get("lks").size() != lkCount) {
                paramMap = new HashMap<String, Object>();
                paramMap.put("tableName", table.getTableDBName());
                paramMap.put("logicKeys", TableUtil.getLkList(table));
                paramMap.put("primaryKes", keyMap.get("pks"));
                dataMap = componentMapper.getDataLogicKeyByPk(paramMap);
                for (Map<String, String> lkMap : keyMap.get("lks")) {
                    if (dataMap.containsKey(lkMap.get("name"))) {
                        dataMap.remove(lkMap.get("name"));
                    }
                }
                newRow.putAll(dataMap);
                keyMap.get("lks").addAll(TableUtil.getLkSql(table, dataMap));
            }
            
            logicBuffer = new StringBuffer();
            for (String logicKey : logicKeyList) {
        		logicBuffer.append(String.valueOf(newRow.get(logicKey))).append("||");
        	}
        	
        	if (rowLogicValues.contains(logicBuffer.toString())) {
        		throw new ServiceException(ExceptionCode.INP_00204, "表 " + table.getTableName() + " 逻辑主键"
                        + TableUtil.getLkNames(table) + "重复！", false);
        	}
        	
        	rowLogicValues.add(logicBuffer.toString());

            needverifyList.add(keyMap);
        }

        if (needverifyList.size() == 0) {
        	return flag;
        }
        
        paramMap = new HashMap<String, Object>();
        paramMap.put("tableName", table.getTableDBName());
        paramMap.put("list", needverifyList);
        Integer num = null;
        try {
            num = componentMapper.isHasRepeateDataAll(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00204, "表 " + table.getTableName() + " 逻辑主键"
                    + TableUtil.getLkNames(table) + "查询错误!", false);
        }
        if (num != null && num > 0) {
            flag = false;
            throw new ServiceException(ExceptionCode.INP_00204, "表 " + table.getTableName() + " 逻辑主键"
                    + TableUtil.getLkNames(table) + "重复！", false);
        }
        
        return flag;
    }

    /**
     * .
     * <p>
     * Title: deleteData
     * </p>
     * <p>
     * Description: 删除数据
     * </p>
     * @param table 表ID
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService#deleteData (com.tjhq.commons.inputcomponent.po.Table)
     */
    protected void deleteData(Table table) throws ServiceException {
        if (table.getDeleteValues() == null || table.getDeleteValues().size() == 0) {
            return;
        }
        logger.debug(table.getTableID().concat("删除数据").concat(String.valueOf(table.getDeleteValues().size()))
                .concat("行！"));

        // 检查数据物理主键，防止勿删
        validatePk(table, table.getDeleteValues());

        List<Map<String, Object>> rows = TableUtil.getDeleteSql(table);
        try {
        	 componentMapper.batchDelete(rows);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.INP_00103, "表 " + table.getTableName() + " 数据删除错误!", false);
        }

    }

    /**
     * . 校验PK完整性
     * @param table 表格对象
     * @param rows 行数据
     * @throws ServiceException
     * @throws
     */
    protected void validatePk(Table table, List<Map<String, Object>> rows) throws ServiceException {
        int pkCount = TableUtil.getPkList(table).size();
        for (Map<String, Object> row : rows) {
            List<Map<String, String>> pkValue = TableUtil.getPkSql(table, row);
            if (pkCount != pkValue.size()) {
                throw new ServiceException(ExceptionCode.INP_00107, "表 " + table.getTableName() + " 数据中物理主键缺失!",
                        false);
            }
        }

    }

    @Override
    protected void afterSaveData(Table table) throws ServiceException {

    }

    @Override
    public Object getData(Table table) throws ServiceException {
        setTableColumns(table);
        return null;
    }

}
