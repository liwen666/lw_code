
package commons.inputcomponent.grid.floatgrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.floatgrid.dao.IFloatGridMapper;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.IFloatGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.setting.external.po.DictTSetFddefPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-09-2014 15:08:44
 */
@Service("floatGridService")
@Transactional(readOnly = true)
public class FloatGridService extends BaseGridService implements IFloatGridService {
    @Resource
    private IDictTFactorService dictTFactorService;

    @Resource
    private IEntryOuterService entryOuterService;

    @Resource
    private IDictTModelService dictTModelService;

    @Resource
    private IUtilDAO utilMapper;

    @Resource
    private IFloatGridMapper floatGridMapper;

    private static List<String> controlFields = new ArrayList<String>();

    static {
        // 定义在dict_t_defaultcol表中的数据
        // 浮动表默认字段
        controlFields.add("CELLSECU");
        controlFields.add("FDCODE");
        controlFields.add("ISDJ");
        controlFields.add("ISHZ");
        controlFields.add("ISINSERT");
        controlFields.add("ISLEAF");
        controlFields.add("ISQZX");
        controlFields.add("ISTEMPLATE");
        controlFields.add("ISUPDATE");
        controlFields.add("LEVELNO");
        controlFields.add("ORIGCODE");
        controlFields.add("SUPERID");
        controlFields.add("SYNSTATUS");
        controlFields.add("TEMPLATEID");
    }

    public IDictTModelService getDictTModelService() {
        return dictTModelService;
    }

    public void setDictTModelService(IDictTModelService dictTModelService) {
        this.dictTModelService = dictTModelService;
    }

    public IUtilDAO getUtilMapper() {
        return utilMapper;
    }

    public void setUtilMapper(IUtilDAO utilMapper) {
        this.utilMapper = utilMapper;
    }

    public static List<String> getControlFields() {
        return controlFields;
    }

    public IDictTFactorService getDictTFactorService() {
        return dictTFactorService;
    }

    public void setDictTFactorService(IDictTFactorService dictTFactorService) {
        this.dictTFactorService = dictTFactorService;
    }

    public IFloatGridMapper getFloatGridMapper() {
        return floatGridMapper;
    }

    public void setFloatGridMapper(IFloatGridMapper floatGridMapper) {
        this.floatGridMapper = floatGridMapper;
    }

    /**
     * 初始化表结构
     * @param table 表格对象
     * @return 设置完表格信息的对象
     * @throws Exception
     */
    @Override
    public Table initStructure(Table table, String userID) throws ServiceException {
        FloatGrid grid = (FloatGrid) super.initStructure(table, userID);
        DictTSetFddefPO dictTSetFddefPO = entryOuterService.getDataFddefList(table.getTableID());
        if (dictTSetFddefPO != null && dictTSetFddefPO.getIsFix() != null && dictTSetFddefPO.getLvlNanmeCol() != null) {
            DictTFactorPO levelNameColPO = dictTFactorService
                    .getDictTFactorByColumnId(dictTSetFddefPO.getLvlNanmeCol());
            grid.setLevelNameCol(levelNameColPO.getDbcolumnname());
            if (dictTSetFddefPO.getIsFix() != null && !dictTSetFddefPO.getIsFix().equalsIgnoreCase("1")) {
                addOperateCol(grid.getColumnList(), levelNameColPO);
            }
        }
        
        //隐藏表控制列
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            if (getControlFields().contains(column.getColumnDBName())) {
                column.setVisible(false);
            }
        }
        return grid;
    }

    private void addOperateCol(List<Column> columnList, DictTFactorPO levelNameColPO) {
        if (columnList == null) {
            return;
        }
        for (int i = 0; i < columnList.size(); i++) {
            Column col = columnList.get(i);
            if (col.isLeaf()) {
                if (col.getColumnDBName() != null
                        && col.getColumnDBName().equalsIgnoreCase(levelNameColPO.getDbcolumnname())) {
                    Column controlColumn = new Column();
                    controlColumn.setAlias("操作");
                    controlColumn.setColumnDBName("floatOperationColumn");
                    columnList.add(i + 1, controlColumn);
                }
            } else {
                addOperateCol(col.getChildrenColumnList(), levelNameColPO);
            }
        }
    }

    @Override
    @SuppressWarnings("all")
    protected void setTableFormula(Table table) throws ServiceException {
        super.setTableFormula(table);
        FloatGrid grid = (FloatGrid) table;
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("tableID", table.getTableID());
        getFloatGridMapper().getFormulaCell(parameterMap);
        if (!parameterMap.containsKey("result")) {
            return;
        }

        List<Map<String, String>> formulaCellList = (List<Map<String, String>>) parameterMap.get("result");
        String[] values = null;
        List<List<String>> cellList = null;
        List<String> cellFormulaList = null;
        List<String> cellRefFormulaList = null;

        // 0 公式列 1影响公式列
        for (Map<String, String> item : formulaCellList) {
            values = item.get("value").split("\\|");
            for (String value : values) {
                if (grid.getCellFormulaMap().containsKey(value)) {
                    cellList = grid.getCellFormulaMap().get(value);
                } else {
                    cellList = new ArrayList<List<String>>();
                }
                if (cellList.size() > 0) {
                    cellFormulaList = cellList.get(0);
                } else {
                    cellFormulaList = new ArrayList<String>();
                    cellList.add(cellFormulaList);
                }
                if (!cellFormulaList.contains(item.get("key"))) {
                    cellFormulaList.add(item.get("key"));
                }
                if (cellList.size() > 1) {
                    cellRefFormulaList = cellList.get(1);
                } else {
                    cellRefFormulaList = new ArrayList<String>();
                    cellList.add(cellRefFormulaList);
                }
                for (String v : values) {
                    if (!cellRefFormulaList.contains(v)) {
                        cellRefFormulaList.add(v);
                    }
                }
                grid.getCellFormulaMap().put(value, cellList);
            }
        }
    }

    @Override
    public PageInfo getData(Table table) throws ServiceException {
        FloatGrid floatGrid = (FloatGrid) table;
        super.setTableColumns(table);
        DictTSetFddefPO dictTSetFddefPO = entryOuterService.getDataFddefList(table.getTableID());
        if (dictTSetFddefPO != null && dictTSetFddefPO.getIsFix() != null && dictTSetFddefPO.getLvlNanmeCol() != null) {
            for (int i = 0; i < floatGrid.getColumnList().size(); i++) {
                Column column = floatGrid.getColumnList().get(i);
                if (column.getColumnDBName() != null
                        && column.getColumnDBName().equalsIgnoreCase("floatOperationColumn")) {
                    floatGrid.getColumnList().remove(column);
                }
            }
        }

        Map<String, Object> paramMap = getQueryParamter(floatGrid);
        // 浮动表模板数据转换
        // String selectElement = paramMap.get("selectElement").toString();
        // paramMap.put("floatTemplateDataColumn",selectElement.replace("DATAKEY", "TEMPLATEID AS DATAKEY"));
        // 取得数据
        PageInfo pageInfo = floatGrid.getPageInfo();
        if (floatGrid.getSortColumnsList() != null) {
            String sortSQL = handleSortParam(table, setSort(floatGrid.getSortColumnsList()));
            if (!"".equals(sortSQL)) {
                paramMap.put("sortSQL", sortSQL);
            }
        }
        //String floatSelectElement = getFloatSqlSelect(floatGrid);
        paramMap.put("floatSelectElement1", getEditSQL(table, (String)paramMap.get("rowWriteSecu")));
        paramMap.put("floatSelectElement2", getSumSQL(table, (String)paramMap.get("rowWriteSecu")));
        paramMap.put("floatSelectElement3", getDjSQL(table, (String)paramMap.get("rowWriteSecu")));
        List<Column> columns = TableUtil.getLeafColumnList(floatGrid);
        paramMap.put("leafColumnList", columns);
        // paramMap.put("sqlWhere", getWhereParamter(floatGrid));
        paramMap.put("tableNameTempl", paramMap.get("tableName").toString() + DictDBConstants.SUFFIX_CONFIG_VIEW);
        List<Map<String, Object>> resultList = floatGridMapper.getFloatGridData(paramMap);
        this.setGridData(resultList, pageInfo);
        
        return pageInfo;
    }
    
    public static String getEditSQL(Table table, String rowSecu) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();
        sql1.append(rowSecu).append(" AS ROWSECU,");
        sql1.append("CASE WHEN ISTEMPLATE= '1'  THEN '1' WHEN ISTEMPLATE= '0'  THEN '0' END C_STATUS,");
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            // 引用表
            if (column.getShowType() != null && column.getRefTableDBName() != null
                    && column.getShowType().equalsIgnoreCase("combo")) {
                sql1.append(column.getColumnDBName()).append(", ");
                sql1.append("(SELECT NAME FROM ").append(column.getRefTableDBName());
                sql1.append(" WHERE GUID = ").append(column.getColumnDBName()).append(" AND ROWNUM < 2 ) AS SNAME_");
                sql1.append(column.getColumnDBName()).append(" ,");
                // 附件表
            } else if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) {
                sql1.append(column.getColumnDBName()).append(" ,");
                sql1.append("(SELECT ATTACHNAME FROM PUB_T_ATTACH WHERE ATTACHID = ").append(column.getColumnDBName()).append(")");
                sql1.append(" AS SNAME_").append(column.getColumnDBName()).append(" ,");
            } else {
                if (column.isSum()) {
                    sql1.append(column.getColumnDBName()).append(" ,");
                } else if (column.getDataType().equals("N")) {
                    sql1.append(" CASE WHEN ISHZ <> '0' AND ISDJ = '1' THEN (SELECT NVL(").append(column.getColumnDBName());
                    sql1.append(",0) FROM TA WHERE TEMPLATEID = X.SUPERID)-(SELECT SUM(").append(column.getColumnDBName());
                    sql1.append(") FROM TA WHERE SUPERID = X.SUPERID AND DATAKEY <> X.DATAKEY) ELSE ").append(column.getColumnDBName());
                    sql1.append(" END ").append(column.getColumnDBName()).append(" ,");
                } else {
                    sql1.append(column.getColumnDBName()).append(" ,");
                }
            } 
        }
        
        sql.append("SELECT ").append(sql1.substring(0, sql1.length() - 1));
        sql.append(" FROM TA X WHERE ");
        sql.append("((X.ISUPDATE = '1' AND X.ISTEMPLATE = '1' AND X.ISINSERT <> '1') OR X.ISTEMPLATE = '0' OR (X.ISHZ = '0' AND X.ISDJ <> '1'))");
        
        return sql.toString();
    }
    
    public static String getSumSQL(Table table, String rowSecu) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();
        StringBuffer sql3 = new StringBuffer();
        StringBuffer sql4 = new StringBuffer();
        int index = 0;
        
        sql1.append(" ROWSECU,").append(" C_STATUS,");
        
        sql2.append(rowSecu).append(" AS ROWSECU,");
        sql2.append("CASE WHEN ISTEMPLATE= '1'  THEN '1' WHEN ISTEMPLATE= '0'  THEN '0' END C_STATUS,");
        
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            
            if (column.isSum()) {
                index ++;
                sql1.append("TO_NUMBER(SUBSTR(AA, INSTR(AA, '|', 1, "+index+") + 1, 29)) - TO_NUMBER(SUBSTR(BB, INSTR(BB, '|', 1, "+index+") + 1, 29)) ");
                sql1.append(column.getColumnDBName()).append(" ,");
            } else if (column.getShowType() != null && column.getRefTableDBName() != null
                    && column.getShowType().equalsIgnoreCase("combo")) {
                sql1.append(column.getColumnDBName()).append(", ");
                sql1.append("SNAME_").append(column.getColumnDBName()).append(" ,");
            } else if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) {
                sql1.append(column.getColumnDBName()).append(", ");
                sql1.append("SNAME_").append(column.getColumnDBName()).append(" ,");
            } else {
                sql1.append(column.getColumnDBName()).append(" ,");
            }
            
            if (column.getShowType() != null && column.getRefTableDBName() != null
                    && column.getShowType().equalsIgnoreCase("combo")) {
                sql2.append(column.getColumnDBName()).append(", ");
                sql2.append("(SELECT NAME FROM ").append(column.getRefTableDBName());
                sql2.append(" WHERE GUID = ").append(column.getColumnDBName()).append(" AND ROWNUM < 2 ) AS SNAME_");
                sql2.append(column.getColumnDBName()).append(" ,");
                // 附件表
            } else if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) {
                sql2.append(column.getColumnDBName()).append(" ,");
                sql2.append("(SELECT ATTACHNAME FROM PUB_T_ATTACH WHERE ATTACHID = ").append(column.getColumnDBName()).append(")");
                sql2.append(" AS SNAME_").append(column.getColumnDBName()).append(" ,");
            } else {
                if (column.isSum()) {
                    sql3.append("'|' || TO_CHAR(NVL(").append(column.getColumnDBName()).append(", 0), 'fm000000000000000000000000.0000')").append("||");
                    sql4.append("'|' || TO_CHAR(NVL(SUM(").append(column.getColumnDBName()).append("), 0),  'fm000000000000000000000000.0000')").append("||");
                } else if (column.getDataType().equals("N")) {
                    sql2.append(" CASE WHEN ISHZ <> '0' AND ISDJ = '1' THEN (SELECT NVL(").append(column.getColumnDBName());
                    sql2.append(",0) FROM TA WHERE TEMPLATEID = X.SUPERID)-(SELECT SUM(").append(column.getColumnDBName());
                    sql2.append(") FROM TA WHERE SUPERID = X.SUPERID AND DATAKEY <> X.DATAKEY) ELSE ").append(column.getColumnDBName());
                    sql2.append(" END ").append(column.getColumnDBName()).append(" ,");
                } else {
                    sql2.append(column.getColumnDBName()).append(" ,");
                }
            }
        }
        
        sql.append("SELECT ").append(sql1.substring(0, sql1.length() - 1)).append(" FROM (");
        sql.append("SELECT ").append(sql2.substring(0, sql2.length() - 1));
        if (sql3.length() > 0) {
            sql.append(",(SELECT ").append(sql3.subSequence(0, sql3.length() - 2));
            sql.append(" FROM TA WHERE TEMPLATEID = X.SUPERID) AA "); 
            sql.append(",(SELECT ").append(sql4.substring(0, sql4.length() - 2)).append(" FROM TA ");
            sql.append(" WHERE NVL(ISQZX, 0) <> 1 CONNECT BY SUPERID = PRIOR TEMPLATEID AND PRIOR ISHZ = '1' AND NVL(PRIOR ISQZX, 0) <> 1 START WITH SUPERID = X.SUPERID AND DATAKEY <> X.DATAKEY) BB");  
        }
        sql.append(" FROM TA X WHERE (X.ISDJ = '1' AND X.ISTEMPLATE = '1' AND X.ISHZ = '0'))");
        
        return sql.toString();
    }
    
    public static String getDjSQL(Table table, String rowSecu) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sql1 = new StringBuffer();
        StringBuffer sql2 = new StringBuffer();
        StringBuffer sql4 = new StringBuffer();
        int index = 0;
        
        sql1.append(" ROWSECU,").append(" C_STATUS,");
        
        sql2.append(rowSecu).append(" AS ROWSECU,");
        sql2.append("CASE WHEN ISTEMPLATE= '1'  THEN '1' WHEN ISTEMPLATE= '0'  THEN '0' END C_STATUS,");
        
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            
            if (column.isSum()) {
                index ++;
                sql1.append("TO_NUMBER(SUBSTR(AA, INSTR(AA, '|', 1, "+index+") + 1, 29)) ");
                sql1.append(column.getColumnDBName()).append(" ,");
            } else if (column.getShowType() != null && column.getRefTableDBName() != null
                    && column.getShowType().equalsIgnoreCase("combo")) {
                sql1.append(column.getColumnDBName()).append(", ");
                sql1.append("SNAME_").append(column.getColumnDBName()).append(" ,");
            } else if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) {
                sql1.append(column.getColumnDBName()).append(", ");
                sql1.append("SNAME_").append(column.getColumnDBName()).append(" ,");
            } else {
                sql1.append(column.getColumnDBName()).append(" ,");
            }
            
            if (column.getShowType() != null && column.getRefTableDBName() != null
                    && column.getShowType().equalsIgnoreCase("combo")) {
                sql2.append(column.getColumnDBName()).append(", ");
                sql2.append("(SELECT NAME FROM ").append(column.getRefTableDBName());
                sql2.append(" where guid = ").append(column.getColumnDBName()).append(" AND ROWNUM < 2 ) AS SNAME_");
                sql2.append(column.getColumnDBName()).append(" ,");
                // 附件表
            } else if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) {
                sql2.append(column.getColumnDBName()).append(" ,");
                sql2.append("(select attachname from pub_t_attach where attachid = ").append(column.getColumnDBName()).append(")");
                sql2.append(" AS SNAME_").append(column.getColumnDBName()).append(" ,");
            } else {
                if (column.isSum()) {
                    sql4.append("'|' || TO_CHAR(NVL(SUM(").append(column.getColumnDBName()).append("), 0),  'fm000000000000000000000000.0000')").append("||");
                } else if (column.getDataType().equals("N")) {
                    sql2.append(" CASE WHEN ISHZ <> '0' AND ISDJ = '1' THEN (SELECT NVL(").append(column.getColumnDBName());
                    sql2.append(",0) FROM TA WHERE TEMPLATEID = X.SUPERID)-(SELECT SUM(").append(column.getColumnDBName());
                    sql2.append(") FROM TA WHERE SUPERID = X.SUPERID AND DATAKEY <> X.DATAKEY) ELSE ").append(column.getColumnDBName());
                    sql2.append(" END ").append(column.getColumnDBName()).append(" ,");
                } else {
                    sql2.append(column.getColumnDBName()).append(" ,");
                }
            }
        }
        
        sql.append("SELECT ").append(sql1.substring(0, sql1.length() - 1)).append(" FROM (");
        sql.append("SELECT ").append(sql2.substring(0, sql2.length() - 1));
        if (sql4.length() > 0) {
            sql.append(",(SELECT ").append(sql4.substring(0, sql4.length() - 2)).append(" FROM TA ");
            sql.append(" WHERE NVL(ISQZX, 0) <> 1 CONNECT BY SUPERID = PRIOR TEMPLATEID AND PRIOR ISHZ = '1' AND NVL(PRIOR ISQZX, 0) <> 1 START WITH SUPERID = X.TEMPLATEID) AA");
        }
        sql.append(" FROM TA X WHERE NOT ((X.ISUPDATE = '1' AND X.ISTEMPLATE = '1' AND X.ISINSERT <> '1') OR X.ISTEMPLATE = '0' OR (X.ISHZ = '0' AND X.ISDJ <> '1'))");
        sql.append(" AND NOT (X.ISDJ = '1' AND X.ISTEMPLATE = '1' AND X.ISHZ = '0'))");
        
        return sql.toString();
    }

    @Override
    public boolean saveData(Table table) throws ServiceException {
        super.setTableColumns(table);
        FloatGrid grid = (FloatGrid) table;
        Iterator<Map<String, Object>> valueIter = null;
        Map<String, Object> vmap = null;
        
        //过滤模板数据
        if (grid.getInsertValues() != null && grid.getInsertValues().size() > 0) {
            valueIter = grid.getInsertValues().iterator();
            while(valueIter.hasNext()) {
                vmap = valueIter.next();
                // 如果是模板数据 且可改修改为业务数据
                if (vmap.get("ISTEMPLATE").toString().equals("1")
                        && (vmap.get("ISUPDATE") != null && vmap.get("ISUPDATE").toString().equals("1"))) {
                    vmap.put("DATAKEY", null);
                    vmap.put("ISTEMPLATE", "0");
                } else if (vmap.get("ISTEMPLATE").toString().equals("1")) {
                    valueIter.remove();
                }
            }
        }
        
        super.saveData(grid);
        return true;
    }


    @Override
    public String generateKey() {
        return this.floatGridMapper.generateKey();
    }

}
