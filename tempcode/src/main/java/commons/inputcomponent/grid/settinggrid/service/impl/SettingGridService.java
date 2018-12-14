
package commons.inputcomponent.grid.settinggrid.service.impl;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.component.dao.IComponentMapper;
import com.tjhq.commons.inputcomponent.component.service.impl.BaseAbstractCompService;
import com.tjhq.commons.inputcomponent.grid.basegrid.dao.IBaseGridMapper;
import com.tjhq.commons.inputcomponent.grid.settinggrid.service.ISettingGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;

/**
 * @author Administrator
 * @version 1.0
 * @created 11-����-2014 15:10:05
 */
@Service("settingGridService")
@Transactional(readOnly = true)
public class SettingGridService extends BaseAbstractCompService implements ISettingGridService {

    @Resource
    private IBaseGridMapper baseGridMapper;

    @Resource
    private IComponentMapper componentMapper;

    public SettingGridService() {

    }

    @Override
    protected void setTableDefine(Table table) throws ServiceException {
        checkTableDefine(table);
    }

    private void checkTableDefine(Table table) throws ServiceException {
        String tableID = table.getTableID();

        if (tableID == null || "".equals(tableID)) {
            throw new ServiceException(ExceptionCode.ERR_00000, "没有设置tableID，请检查设置信息！", false);
        }

        if (table.getAppID() == null || "".equals(table.getAppID())) {
            throw new ServiceException(ExceptionCode.ERR_00000, "没有设置appID，请检查设置信息！", false);
        }

        if (table.getTableDBName() == null || "".equals(table.getTableDBName())) {
            throw new ServiceException(ExceptionCode.ERR_00000, "没有设置tableDBName，请检查设置信息！", false);
        }

        if (table.getTableName() == null || "".equals(table.getTableName())) {
            throw new ServiceException(ExceptionCode.ERR_00000, "没有设置tableName，请检查设置信息！", false);
        }

        // table.setVisiable(ComponentUtil.toBoolean(dictTModelPO.getIsshow()));

        List<Column> columns = table.getColumnList();

        if (columns == null || columns.size() == 0) {
            throw new ServiceException(ExceptionCode.ERR_00000, "表中没有列定义，请检查设置columnList信息！", false);
        }
    }

    @Override
    protected void setTableFormula(Table table) throws ServiceException {
        // 在外面进行设置公式列

    }

    @Override
    protected void setTableRelatedColumns(Table table) throws ServiceException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setTableSecu(Table table, String userID) throws ServiceException {
    }

    @Override
    protected boolean validateLkRepeat(Table table) throws ServiceException {
        boolean flag = true;
        List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
        values.addAll(table.getInsertValues());
        values.addAll(table.getUpdateValues());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, String>> lkAll = new ArrayList<Map<String, String>>();
        List<Map<String, List<Map<String, String>>>> list = TableUtil.getKeySql(table, values);
        for (Map<String, List<Map<String, String>>> row : list) {
            List<Map<String, String>> logicalKeys = (List<Map<String, String>>) row.get("lks");
            if (null != logicalKeys) {
                lkAll.addAll(logicalKeys);
            }
        }
        if (lkAll.size() > 0) {
            paramMap.put("tableName", table.getTableDBName());
            paramMap.put("list", list);
            Integer num = null;
            try {
                num = componentMapper.isHasRepeateDataAll(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "逻辑主键查询错误!", false);
            }
            if (num != null && num > 0) {
                flag = false;
                throw new ServiceException(ExceptionCode.ERR_00000, "逻辑主键重复!", false);
            }
        }
        return flag;
    }

    @Override
    protected boolean validateNullAndReg(Table table) throws ServiceException {
        List<Column> columnList = TableUtil.getLeafColumnList(table);
        Map<String, Object> colsMap = new HashMap<String, Object>();
        for (Column col : columnList) {
            colsMap.put(col.getColumnDBName(), col);
        }
        List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
        values.addAll(table.getInsertValues());
        values.addAll(table.getUpdateValues());
        boolean result = true;
        for (Map<String, Object> map : values) {
            Set<String> set = map.keySet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String colname = it.next();
                Object value = map.get(colname);
                Column col = (Column) colsMap.get(colname);
                if (col == null)
                    continue;
                boolean nullable = col.isNullable();
                if (!nullable && (value == null || value.toString().equals(""))) {
                    result = false;
                    throw new ServiceException(colname + "不可为空！");
                }
                String regExpression = col.getRegExpression();
                if (regExpression != null && !regExpression.equals("")) {
                    Pattern pattern = Pattern.compile(regExpression);
                    Matcher match = pattern.matcher(value.toString());
                    result = match.matches();
                    if (!result) {
                        result = false;
                        String regInfo = col.getRegInfo();
                        if (regInfo != null && !"".equals(regInfo)) {
                            throw new ServiceException(regInfo);
                        } else {
                            throw new ServiceException(colname + "与正则表达式不匹配");
                        }
                    }
                }

            }
        }
        return result;
    }

    @Override
    protected void addData(Table table) throws ServiceException {
        // 检查表列定义是否存在
        checkTableDefine(table);
        if (table.getInsertValues() == null || table.getInsertValues().size() == 0)
            return;
        List<Map<String, Object>> rows = TableUtil.getInsertSql(table);
        for (Map<String, Object> paramMap : rows) {
            try {
                componentMapper.insert(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "保存数据错误!", false);
            }
        }

    }

    @Override
    protected void deleteData(Table table) throws ServiceException {
        // 检查表列定义是否存在
        checkTableDefine(table);
        if (table.getDeleteValues() == null || table.getDeleteValues().size() == 0)
            return;
        List<Map<String, Object>> rows = TableUtil.getDeleteSql(table);
        for (Map<String, Object> paramMap : rows) {
            try {
                componentMapper.delete(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "保存数据错误!", false);
            }
        }
    }

    @Override
    protected void updateData(Table table) throws ServiceException {
        // 检查表列定义是否存在
        checkTableDefine(table);
        if (table.getUpdateValues() == null || table.getUpdateValues().size() == 0)
            return;
        List<Map<String, Object>> rows = TableUtil.getUpdateSql(table);
        for (Map<String, Object> paramMap : rows) {
            try {
                componentMapper.update(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "保存数据错误!", false);
            }
        }
    }

    @Override
    protected void afterSaveData(Table table) throws ServiceException {

    }

    public Object getData(Table table) throws ServiceException {
        Grid grid = (Grid) table;
        // 取得数据
        PageInfo pageInfo = grid.getPageInfo();
        
        // 获取查询参数
        Map<String, Object> paramMap = getQueryParamter(grid);
        
        // 如果表格有分页，需要取得数据总条数
        if (grid.isPagination()) {
            pageInfo.setTotal(getTotalCount(paramMap));
            paramMap.putAll(getPageParamter(pageInfo));
        }
        
        //查询数据
        setGridData(getGridRecords(paramMap), pageInfo);
        
        return pageInfo;
    }

    /**
     * 需要取得数据总条数
     * @param paramMap
     * @return
     * @throws Exception
     */
    protected int getTotalCount(Map<String, Object> paramMap) throws ServiceException {
        Integer num = null;
        try {
            num = baseGridMapper.getRecordsCount(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载数据分页信息出错!", false);
        }
        if (num != null) {
            return num.intValue();
        } else {
            return 0;
        }
    }

    /**
     * 获取查询参数
     * @param grid
     * @return
     * @throws Exception
     */
    protected Map<String, Object> getQueryParamter(Grid grid) throws ServiceException {
        // super.setTableDefine(grid);// 是否要加权限过滤
        PageInfo pageInfo = grid.getPageInfo();
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("tableName", grid.getTableDBName());
        paramMap.put("leafColumnList", TableUtil.getLeafColumnList(grid));
        paramMap.put("selectElement", TableUtil.getSqlSelect(grid));// 查找的元素
        paramMap.put("rowWriteSecu", "'3'");
        Map<String, Object> extProperties = grid.getExtProperties();
        String sortSQL = "";
        if (extProperties != null) {
            Object obj = extProperties.get("sortSQL");
            if (obj != null) {
                sortSQL = obj.toString();
            }
        }
        if (sortSQL != null && sortSQL.length() > 0) {
            paramMap.put("sortSQL", sortSQL);
        }
        if (grid.isPagination()) {
            Map<String, Object> pageMap = getPageParamter(pageInfo);
            paramMap.putAll(pageMap);
        }
        String sqlWhere = getWhereParamter(grid);
        paramMap.put("sqlWhere", sqlWhere);
        return paramMap;
    }

    /**
     * 查询表格数据
     * @param paramMap
     * @return
     * @throws Exception
     */
    protected List<Map<String, Object>> getGridRecords(Map<String, Object> paramMap) throws ServiceException {
        List<Map<String, Object>> resultList = null;
        try {
            resultList = baseGridMapper.getGridData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载数据出错!", false);
        }
        return resultList;
    }

    /**
     * 对获取的数据进行格式转换
     * @param resultMap
     * @param pageInfo
     * @throws Exception
     */
    protected void setGridData(List<Map<String, Object>> resultList, PageInfo pageInfo) throws ServiceException {

        if (resultList == null || resultList.size() == 0)
            return;

        // 取查询数据和列
        List<String> columns = new ArrayList<String>(resultList.get(0).keySet());
        List<Object> datas = new ArrayList<Object>();

        List<Object> rowData = null;
        for (Map<String, Object> mapedRecord : resultList) {
            rowData = new ArrayList<Object>();

            // 根据列定义拷贝数据
            for (String column : columns) {
                Object rawValue = mapedRecord.get(column);
                if (rawValue == null) {
                    rowData.add(null);
                    continue;
                }

                if (Clob.class.isAssignableFrom(rawValue.getClass())) {// clob
                    Clob clobValue = (Clob) rawValue;
                    char[] clobBuffer = null;
                    try {
                        Reader clobStream = clobValue.getCharacterStream();
                        clobBuffer = new char[(int) clobValue.length()];
                        clobStream.read(clobBuffer);
                        clobStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServiceException(ExceptionCode.ERR_00000, "大文本数据转换错误!", false);
                    }
                    rowData.add(new String(clobBuffer));

                } else if (BigDecimal.class.isAssignableFrom(rawValue.getClass())) {// BigDecimal
                    BigDecimal bigDecimalValue = (BigDecimal) rawValue;
                    String strval = bigDecimalValue.toString();
                    rowData.add(strval);
                } else {
                    rowData.add(rawValue);
                }
            }
            datas.add(rowData);
        }
        pageInfo.setDataList(datas);
        pageInfo.setColumns(columns);
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getSumData(Map<String, Object> paramMap) throws Exception {
        List<Column> columns = (List<Column>) paramMap.get("leafColumnList");
        List<String> sumColList = new ArrayList<String>();
        List<String> nosumColList = new ArrayList<String>();
        List<String> groupColList = new ArrayList<String>();
        for (Column column : columns) {
            if (column.isSum()) {
                sumColList.add(" sum(" + column.getColumnDBName() + ") as " + column.getColumnDBName());
            } else {
                nosumColList.add(" '' as " + column.getColumnDBName());
                groupColList.add(column.getColumnDBName());
            }
        }

        paramMap.put("sumColList", sumColList);
        paramMap.put("nosumColList", nosumColList);
        paramMap.put("groupColList", groupColList);

        return baseGridMapper.getSumData(paramMap);
    }

    /**
     * @Title: handlePageParam
     * @Description: TODO(分页参数处理)
     * @param @param pageMap
     * @param @return 设定文件
     * @return Map 返回类型
     * @throws
     */
    protected Map<String, Object> getPageParamter(PageInfo pageInfo) {
        Map<String, Object> pageParamMap = new HashMap<String, Object>();
        if (null != pageInfo) {
            int startRow = pageInfo.getStart();
            int limit = pageInfo.getLimit();
            int endRow = startRow + limit;
            pageParamMap.put("isNeedPage", "1");
            pageParamMap.put("startRow", startRow);
            pageParamMap.put("endRow", endRow);

        }
        return pageParamMap;

    }

    protected String getWhereParamter(Grid grid) {
        return grid.convertConditionSQL();
    }

    @Override
    public void transferGridData(List<Map<String, Object>> resultList, PageInfo pageInfo) throws Exception {
        this.setGridData(resultList, pageInfo);
    }

}
