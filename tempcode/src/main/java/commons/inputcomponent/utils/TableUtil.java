
package commons.inputcomponent.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Table;

public class TableUtil {
    /**
     * 取得表物理主键
     * @return
     */
    public static List<String> getPkList(Table table) {
        List<Column> colList = getLeafColumnList(table);
        List<String> pkList = new ArrayList<String>();
        for (Column col : colList) {
            if (col.isKey()) {
                pkList.add(col.getColumnDBName());
            }
        }
        return pkList;
    }

    /**
     * 取得表逻辑主键
     * @return
     */
    public static List<String> getLkList(Table table) {
        List<Column> colList = getLeafColumnList(table);
        List<String> lkList = new ArrayList<String>();
        for (Column col : colList) {
            if (col.isLogicKey()) {
                lkList.add(col.getColumnDBName());
            }
        }
        return lkList;
    }

    /**
     * 取得表逻辑主键名称
     * @return
     */
    public static String getLkNames(Table table) {
        List<Column> colList = getLeafColumnList(table);
        List<String> lkList = new ArrayList<String>();
        for (Column col : colList) {
            if (col.isLogicKey()) {
                lkList.add(col.getAlias());
            }
        }

        if (lkList.size() > 0) {
            String[] str = new String[lkList.size()];
            lkList.toArray(str);
            return Arrays.toString(str);
        }
        return "";
    }

    /**
     * 取得叶子节点的列
     * @return
     */
    public static List<Column> getLeafColumnList(Table table) {
        List<Column> columnList = table.getColumnList();
        List<Column> leafColumnList = new ArrayList<Column>();
        getLeafColumnList(leafColumnList, columnList);
        return leafColumnList;
    }

    private static void getLeafColumnList(List<Column> leafColumnList, List<Column> columnList) {
        if (columnList == null) {
            return;
        }
        for (Column col : columnList) {
            if (col.isLeaf()) {
                leafColumnList.add(col);
            } else {
                List<Column> childColList = col.getChildrenColumnList();
                getLeafColumnList(leafColumnList, childColList);
            }
        }
    }
    
    public static Column getColumnBycolumnDBName(Table table, String columnDBName) {
        List<Column> columnList = getLeafColumnList(table);
        for (Column column : columnList) {
            if (column.getColumnDBName().equals(columnDBName)) {
                return column;
            }
        }
        return null;
    }

    /**
     * 获取平铺的节点列
     * @param table
     * @return
     */
    public static List<Column> getTiledColumnList(Table table) {
        List<Column> columnList = table.getColumnList();
        List<Column> tiledColumnList = new ArrayList<Column>();
        getTiledColumnList(tiledColumnList, columnList);
        return tiledColumnList;
    }

    private static void getTiledColumnList(List<Column> tiledColumnList, List<Column> columnList) {
        if (columnList == null) {
            return;
        }
        for (Column col : columnList) {
            tiledColumnList.add(col);
            if (!col.isLeaf()) {
                List<Column> childColList = col.getChildrenColumnList();
                getTiledColumnList(tiledColumnList, childColList);
            }
        }
    }

    /**
     * 以Map的方式存储Column Param:ID key: columnID value:Column对象 Param:DbName key: columnName value:Column对象
     * @return
     */
    public static Map<String, Column> getColumnMap(String keyType, Table table) {
        return getColumnMap(keyType, TableUtil.getLeafColumnList(table));
    }

    /**
     * 以Map的方式存储Column Param:ID key: columnID value:Column对象 Param:DbName key: columnName value:Column对象
     * @return
     */
    public static Map<String, Column> getColumnMap(String keyType, List<Column> columns) {
        Map<String, Column> columnMap = new HashMap<String, Column>();
        for (Column column : columns) {
            if ("ID".equals(keyType)) {
                columnMap.put(column.getColumnID(), column);
            } else if ("DbName".equals(keyType)) {
                columnMap.put(column.getColumnDBName(), column);
            }
        }
        return columnMap;
    }

    /**
     * 取得添加sql
     * @param table
     * @return
     */
    public static List<Map<String, Object>> getInsertSql(Table table) {
        List<Map<String, Object>> list = table.getInsertValues();
        List<Map<String, Object>> rowsList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : list) {
            rowsList.add(getInsertSql(table, data));
        }
        return rowsList;
    }

    /**
     * 取得添加sql
     * @param table
     * @return
     */
    public static Map<String, Object> getInsertSql(Table table, Map<String, Object> data) {
        Map<String, Column> columnMap = TableUtil.getColumnMap("DbName", table);
        Column column = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tableName", table.getTableDBName());
        List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
        for (String key : data.keySet()) {
            column = columnMap.get(key);
            if (column == null || column.isVirtual() || column.isBindcol())
                continue;
            if (data.get(key) != null && !"".equals(data.get(key).toString())) {
                Map<String, String> field = new HashMap<String, String>();
                field.put("name", key);
                field.put("value", ColumnUtil.getSqlValue(column, data.get(key)));
                rowList.add(field);
            }
        }
        paramMap.put("list", rowList);

        return paramMap;
    }

    /**
     * 取得删除sql
     * @param table
     * @return
     */
    public static List<Map<String, Object>> getDeleteSql(Table table) {
        List<Map<String, Object>> list = table.getDeleteValues();
        List<Map<String, Object>> rowsList = new ArrayList<Map<String, Object>>();
        Map<String, Column> columnMap = TableUtil.getColumnMap("DbName", table);
        Column column = null;
        for (Map<String, Object> data : list) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("tableName", table.getTableDBName());
            List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
            for (String key : data.keySet()) {
                column = columnMap.get(key);
                if (column == null || !column.isKey())
                    continue;
                Map<String, String> field = new HashMap<String, String>();
                field.put("name", key);
                field.put("value", ColumnUtil.getSqlValue(column, data.get(key)));
                rowList.add(field);
            }

            paramMap.put("list", rowList);
            rowsList.add(paramMap);
        }
        return rowsList;
    }

    /**
     * 取得修改sql
     * @param table
     * @return
     */
    public static List<Map<String, Object>> getUpdateSql(Table table) {
        List<Map<String, Object>> list = table.getUpdateValues();
        List<Map<String, Object>> rowsList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> data : list) {
            rowsList.add(getUpdateSql(table, data));
        }
        return rowsList;
    }

    /**
     * 取得修改sql
     * @param table
     * @return
     */
    public static Map<String, Object> getUpdateSql(Table table, Map<String, Object> data) {
        Map<String, Column> columnMap = TableUtil.getColumnMap("DbName", table);
        Column column = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tableName", table.getTableDBName());
        List<Map<String, String>> rowList = new ArrayList<Map<String, String>>();
        List<Map<String, String>> keyList = new ArrayList<Map<String, String>>();
        for (String key : data.keySet()) {
            column = columnMap.get(key);
            if (column == null || column.isVirtual() || column.isBindcol())
                continue;
            Map<String, String> field = new HashMap<String, String>();
            field.put("name", key);
            field.put("value", ColumnUtil.getSqlValue(column, data.get(key) == null ? "" : data.get(key)));
            if (column.isKey()) {
                keyList.add(field);
            } else {
                rowList.add(field);
            }
        }

        paramMap.put("list", rowList);
        paramMap.put("pks", keyList);

        return paramMap;
    }

    /**
     * 取得物理主键和逻辑主键sql
     * @param table
     * @param list
     * @return
     */
    public static List<Map<String, List<Map<String, String>>>> getKeySql(Table table, List<Map<String, Object>> list) {
        List<Map<String, List<Map<String, String>>>> rowsList = new ArrayList<Map<String, List<Map<String, String>>>>();
        for (Map<String, Object> data : list) {
            rowsList.add(getKeySql(table, data));
        }
        return rowsList;
    }

    /**
     * 取得物理主键和逻辑主键sql
     * @param table
     * @param list
     * @return
     */
    public static Map<String, List<Map<String, String>>> getKeySql(Table table, Map<String, Object> data) {
        Map<String, List<Map<String, String>>> paramMap = new HashMap<String, List<Map<String, String>>>();
        List<Map<String, String>> logicalKeys = getLkSql(table, data);
        List<Map<String, String>> primaryKeys = getPkSql(table, data);

        if (primaryKeys.size() > 0)
            paramMap.put("pks", primaryKeys);
        if (logicalKeys.size() > 0)
            paramMap.put("lks", logicalKeys);

        return paramMap;
    }

    /**
     * 取得逻辑主键sql
     * @param table
     * @param data
     * @return
     */
    public static List<Map<String, String>> getLkSql(Table table, Map<String, Object> data) {
        List<Map<String, String>> logicalKeys = new ArrayList<Map<String, String>>();
        List<Column> leafColumnList = getLeafColumnList(table);
        for (Column column : leafColumnList) {
            if (!column.isLogicKey())
                continue;
            if (data.containsKey(column.getColumnDBName())) {
                if (data.get(column.getColumnDBName()) != null
                        && !"".equals(data.get(column.getColumnDBName()).toString())) {
                    Map<String, String> logicalMap = new HashMap<String, String>();
                    logicalMap.put("name", column.getColumnDBName());
                    logicalMap.put("value", ColumnUtil.getSqlValue(column, data.get(column.getColumnDBName())));
                    logicalKeys.add(logicalMap);
                }

            }
        }

        return logicalKeys;
    }

    /**
     * 取得物理主键sql
     * @param table
     * @param data
     * @return
     */
    public static List<Map<String, String>> getPkSql(Table table, Map<String, Object> data) {
        List<Map<String, String>> primaryKeys = new ArrayList<Map<String, String>>();
        for (Column column : getLeafColumnList(table)) {
            if (!column.isKey())
                continue;
            if (data.containsKey(column.getColumnDBName())) {
                if (data.get(column.getColumnDBName()) != null
                        && !"".equals(data.get(column.getColumnDBName()).toString())) {
                    Map<String, String> logicalMap = new HashMap<String, String>();
                    logicalMap.put("name", column.getColumnDBName());
                    logicalMap.put("value", ColumnUtil.getSqlValue(column, data.get(column.getColumnDBName())));
                    primaryKeys.add(logicalMap);
                }

            }
        }

        return primaryKeys;
    }

    /**
     * 取得查询字段sql
     * @param table
     * @return
     */
    public static String getSqlSelect(Table table) {
        List<Column> leafColumns = getLeafColumnList(table);
        if (leafColumns == null || leafColumns.size() == 0)
            return null;

        StringBuffer sqlSelectBuffer = new StringBuffer();
        for (Column leafColumn : leafColumns) {
            if (sqlSelectBuffer.length() == 0)
                sqlSelectBuffer.append(ColumnUtil.getSqlName(leafColumn));
            else
                sqlSelectBuffer.append("," + ColumnUtil.getSqlName(leafColumn));
        }
        return sqlSelectBuffer.toString().toUpperCase();
    }

    /**
     * 获取查询表格数据的SQL
     * @param table
     * @return
     */
    public static String getTableCondition(Table table) {
        StringBuffer conditionBuffer = new StringBuffer();
        List<Column> colList = getLeafColumnList(table);
        for (Column col : colList) {
            if (col.isLogicKey()) {
                for (Entry<String, Object> entry : table.getExtProperties().entrySet()) {
                    if (entry.getKey().toUpperCase().equals(col.getColumnDBName().toUpperCase())) {
                        conditionBuffer.append(col.getColumnDBName()).append(" = ").append(
                                ColumnUtil.getSqlValue(col, entry.getValue())).append(" AND ");
                    }
                }

            }
        }

        if (conditionBuffer.length() > 0)
            return conditionBuffer.substring(0, conditionBuffer.length() - 4);
        return null;
    }

    public static void setColStepSecu(List<Column> columnList, List<Map<String, Object>> list) {
        // TODO Auto-generated method stub
        for (int i = 0; i < columnList.size(); i++) {
            Column col = columnList.get(i);
            if (col != null) {
                String columnId = col.getColumnID();
                for (int t = 0; t < list.size(); t++) {
                    if (list.get(t).get("COLUMNID").equals(columnId)) {
                        int baseSecu = Integer.parseInt(list.get(t).get("COLSECU").toString());
                        if (col.isVisible() && !col.isReadOnly()) {
                            if (baseSecu < 3) {
                                if (baseSecu == 2) {
                                    col.setReadOnly(true);
                                }
                                if (baseSecu == 1) {
                                    col.setVisible(false);
                                }
                            }
                        }
                        if (col.isVisible() && col.isReadOnly()) {
                            if (baseSecu < 2) {
                                col.setVisible(false);
                            }
                        }
                    }
                }
            }

        }
    }
}
