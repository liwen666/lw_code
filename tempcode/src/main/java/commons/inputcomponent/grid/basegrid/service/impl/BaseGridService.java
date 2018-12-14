/**
 * @Title: IBaseGridService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入表格基础类
 * @Revision 6.0 2015-11-23  CAOK
 */
package commons.inputcomponent.grid.basegrid.service.impl;

import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.component.service.impl.BaseTableService;
import com.tjhq.commons.inputcomponent.constants.Constants;
import com.tjhq.commons.inputcomponent.grid.basegrid.dao.IBaseGridMapper;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.IBaseGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;

/**
 * @ClassName: BaseGridService
 * @Description: 通用录入表格基础类
 * @author: CAOK 2015-11-24 上午10:42:31
 */

public class BaseGridService extends BaseTableService implements IBaseGridService {
    
    /**
     * @Fields logger : 日志服务
     */
    Logger logger = Logger.getLogger(BaseGridService.class);

    /**
     * @Fields baseGridMapper : 数据据库交互服务
     */
    @Resource
    private IBaseGridMapper baseGridMapper;

    /**
     * @return baseGridMapper
     */
    public IBaseGridMapper getBaseGridMapper() {
        return baseGridMapper;
    }

    /**
     * @param baseGridMapper 要设置的 baseGridMapper
     */
    public void setBaseGridMapper(IBaseGridMapper baseGridMapper) {
        this.baseGridMapper = baseGridMapper;
    }

    /**.
     * <p>Title: getData</p>
     * <p>Description: 获取表格数据，支持查询，支持分页</p>
     * @param table 表格对象
     * @return PageInfo对象
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.component.service.impl.BaseTableService#getData
     *      (com.tjhq.commons.inputcomponent.po.Table)
     */
    public Object getData(Table table) throws ServiceException {
        super.getData(table);
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

    /**.
     * 需要取得数据总条数
     * @param paramMap 查询参数
     * @return 总条数
     * @throws ServiceException 业务异常
     */
    protected int getTotalCount(Map<String, Object> paramMap) throws ServiceException {
        Integer num = null;
        try {
            num = baseGridMapper.getRecordsCount(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "获取数据分页信息出错!", false);
        }
        if (num != null) {
            return num.intValue();
        } else {
            return 0;
        }
    }

    /**.
     * 获取查询参数
     * @param grid 表格对象
     * @return 参数对象
     * @throws ServiceException 业务异常
     */
    protected Map<String, Object> getQueryParamter(Grid grid) throws ServiceException {
        // super.setTableDefine(grid);// 是否要加权限过滤
        Map<String, Object> paramMap = getParameterMap(grid);

        paramMap.put("tableName", grid.getTableDBName());
        paramMap.put("leafColumnList", TableUtil.getLeafColumnList(grid));
        paramMap.put("selectElement", TableUtil.getSqlSelect(grid)); // 查找的元素
        paramMap.put("rowWriteSecu", grid.getRowWriteSecu());
        paramMap.put("rowVisibleSecu", grid.getRowVisibleSecu());
      
        String sqlWhere = getWhereParamter(grid);
        paramMap.put("sqlWhere", sqlWhere);
        return paramMap;
    }

    /**.
     * 获取一个空的参数对象
     * @param grid 表格
     * @return 参数对象
     * @throws
     */
    protected Map<String, Object> getParameterMap(Grid grid) {
        return new HashMap<String, Object>();
    }

    /**.
     * 查询表格数据
     * @param paramMap 查询参数对象
     * @return 数据集
     * @throws ServiceException 业务异常
     */
    protected List<Map<String, Object>> getGridRecords(Map<String, Object> paramMap) throws ServiceException {
        List<Map<String, Object>> resultList = null;
        try {
            resultList = baseGridMapper.getGridData(paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "查询数据出错!", false);
        }
        return resultList;
    }

    /**.
     * 对获取的数据进行格式转换
     * @param resultList 查询后的结果集
     * @param pageInfo 返回给表格的对象
     * @throws ServiceException 业务异常
     */
    protected void setGridData(List<Map<String, Object>> resultList, PageInfo pageInfo) throws ServiceException {

        if (resultList == null || resultList.size() == 0) {
            return;
        }

        // 取查询数据和列
        List<String> columns = new ArrayList<String>(resultList.get(0).keySet());
        List<Object> datas = new ArrayList<Object>();

        List<Object> rowData = null;
        Object rawValue = null;
        Clob clobValue = null;
        char[] clobBuffer = null;
        Reader clobStream = null;
        String strval = null;
        BigDecimal bigDecimalValue = null;
        
        for (Map<String, Object> mapedRecord : resultList) {
            rowData = new ArrayList<Object>();

            // 根据列定义拷贝数据
            for (String column : columns) {
                rawValue = mapedRecord.get(column);
                
                // 如果引用列有值，SNAME_没有替换到值 则用？号显示
                if (column.startsWith("SNAME_") && mapedRecord.get(column.substring(6)) != null
                        && !mapedRecord.get(column.substring(6)).equals("")
                        && (rawValue == null || rawValue.equals(""))) {
                    rawValue = " ??? ";
                }
                
                if (rawValue == null) {
                    rowData.add(null);
                    continue;
                }

                if (Clob.class.isAssignableFrom(rawValue.getClass())) { //CLOB
                    clobValue = (Clob) rawValue;
                    try {
                        clobStream = clobValue.getCharacterStream();
                        clobBuffer = new char[(int) clobValue.length()];
                        clobStream.read(clobBuffer);
                        clobStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServiceException(ExceptionCode.ERR_00000, "大文本数据类型转换出错!", false);
                    }
                    rowData.add(new String(clobBuffer));

                } else if (BigDecimal.class.isAssignableFrom(rawValue.getClass())) { //BigDecimal
                    try {
                        bigDecimalValue = (BigDecimal) rawValue;
                        strval = bigDecimalValue.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ServiceException(ExceptionCode.ERR_00000, "符点数据类型转换出错!", false);
                    }
                    rowData.add(strval);
                } else if (Constants.ORACLE_TIMESTAMP.equals(rawValue.getClass().getName())) { //TimeStamp
                    logger.debug("不被支持的数据类型(TIMESTAMP)，前台无法展现！");
                } else {
                    rowData.add(rawValue);
                }
            }
            datas.add(rowData);
        }
        pageInfo.setDataList(datas);
        pageInfo.setColumns(columns);
    }

    /**.
     * 获取合计行
     * @param paramMap 查询参数集
     * @return 合计行
     * @throws Exception 数据查询异常
     */
    @SuppressWarnings("unchecked")
    protected Map<String, Object> getSumData(Map<String, Object> paramMap) throws Exception {
        List<Column> columns = (List<Column>) paramMap.get("leafColumnList");
        List<String> sumColList = new ArrayList<String>();
        List<String> nosumColList = new ArrayList<String>();
        List<String> groupColList = new ArrayList<String>();
        boolean hasSum = false;
        boolean hasFirst = true;
        String sumCol = null;
        for (Column column : columns) {
            if (column.isSum() && column.getDataType().equals("N")) {
                sumColList.add(" sum(" + column.getColumnDBName() + ") as " + column.getColumnDBName());
                hasSum = true;
            } else {
                if (column.isVisible() && hasFirst) {
                    nosumColList.add(" ' 合计  ' as " + column.getColumnDBName());
                    sumCol = column.getColumnDBName();
                    if (column.getRefTableID() != null) {
                        sumCol = "SNAME_" + column.getColumnDBName();
                    }
                    hasFirst = false;
                } else {
                    // 合计行中dataKey的值为****
                    if (Constants.DATAKEY.equals(column.getColumnDBName())) {
                        nosumColList.add(" '****' as " + column.getColumnDBName());
                    } else {
                        nosumColList.add(" '' as " + column.getColumnDBName());
                    }
                    groupColList.add(column.getColumnDBName());
                }
            }
        }
        if (hasSum) {
            paramMap.put("sumColList", sumColList);
            paramMap.put("nosumColList", nosumColList);
            paramMap.put("groupColList", groupColList);
            Map<String, Object> sumData = baseGridMapper.getSumData(paramMap);
            sumData.put(sumCol, " 合计  ");
            sumData.put(Constants.DATAKEY, "****");
            return sumData;
        } else {
            return null;
        }
    }

    /**.
     * 分页参数处理
     * @param pageInfo 返回给表格的对象
     * @return 分布参数对象
     * @throws
     */
    protected Map<String, Object> getPageParamter(PageInfo pageInfo) {
        Map<String, Object> pageParamMap = new HashMap<String, Object>();
        if (null != pageInfo) {
            int startRow = pageInfo.getStart();
            int limit = pageInfo.getLimit();
            if (startRow >= pageInfo.getTotal()) { // 如果启始条数大于总条数，则取最后一页
                startRow = pageInfo.getTotal() - limit;
            }
            if (startRow < 0) {
                startRow = 0;
            }
            
            int endRow = startRow + limit;
            pageParamMap.put("isNeedPage", "1");
            pageParamMap.put("startRow", startRow);
            pageParamMap.put("endRow", endRow);

        }
        return pageParamMap;

    }

    /**.
     * 获取表格查询参数
     * @param grid 表格对象
     * @return 查询条件
     * @throws
     */
    protected String getWhereParamter(Grid grid) {
        return grid.convertConditionSQL();
    }

    /**.
     * 处理排序 不支持按引用Name排序(不建议使用)
     * @param sortColumns 排序的列
     * @return 排序sql
     * @throws
     */
    @Deprecated
    protected String handleSortParam(List<DictTSetSortPO> sortColumns) {
        String orderStr = "";
        for (int i = 0; i < sortColumns.size(); i++) {
            DictTSetSortPO sortColPo = (DictTSetSortPO) sortColumns.get(i);
            if ((sortColPo.getIsReserve() != null && sortColPo.getIsReserve().equals("1"))
                    || (sortColPo.getIsDefault() != null && sortColPo.getIsDefault().equals("1"))) { //判断是否是设置的默认排序字段
                String sortType = "ASC";
                if (sortColPo.getAscFlag() == null || sortColPo.getAscFlag().equals("1")) { //判断是否是升序
                    sortType = "ASC";
                } else {
                    sortType = "DESC";
                }

                orderStr += ", " + sortColPo.getDbColumnName() + " " + sortType;
            }
        }

        if (orderStr.length() > 0) {
            orderStr = orderStr.substring(1);
        }

        return orderStr;
    }

    /**.
     * 处理排序
     * @param table 表格对象
     * @param sortColumns 排序列
     * @return 排序SQL
     * @throws
     */
    protected String handleSortParam(Table table, List<DictTSetSortPO> sortColumns) {
        String orderStr = "";
        String orderDbName = "";
        Map<String, String> refColMap = new HashMap<String, String>();
        List<Column> leafColumnList = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumnList) {
            if (StringUtils.isEmpty(column.getRefTableID()) || StringUtils.isEmpty(column.getRefTableDBName())) {
                continue;
            }
            refColMap.put(column.getColumnDBName(), "1");
        }
        for (int i = 0; i < sortColumns.size(); i++) {
            DictTSetSortPO sortColPo = (DictTSetSortPO) sortColumns.get(i);
            if ((sortColPo.getIsReserve() != null && sortColPo.getIsReserve().equals("1"))
                    || (sortColPo.getIsDefault() != null && sortColPo.getIsDefault().equals("1"))) { //判断是否是设置的默认排序字段
                String sortType = "ASC";
                if (sortColPo.getAscFlag() == null || sortColPo.getAscFlag().equals("1")) { //判断是否是升序
                    sortType = "ASC";
                } else {
                    sortType = "DESC";
                }
                orderDbName = sortColPo.getDbColumnName();
                if (refColMap.containsKey(orderDbName)) {
                    orderDbName = Constants.SNAME_START + orderDbName;
                }
                orderStr += ", " + orderDbName + " " + sortType;
            }
        }

        if (orderStr.length() > 0) {
            orderStr = orderStr.substring(1);
        }

        return orderStr;
    }

    /**.
     * 获取前台可用排序
     * @param sortColumns 排序列
     * @return 前台可用的排序列
     * @throws
     */
    protected List<DictTSetSortPO> setSort(List<DictTSetSortPO> sortColumns) {
        List<DictTSetSortPO> sortPoList = new ArrayList<DictTSetSortPO>();
        for (DictTSetSortPO dictTSetSortPO : sortColumns) {
            String isReserve = dictTSetSortPO.getIsReserve();
            if (isReserve != null && "1".equals(isReserve)) {
                sortPoList.add(dictTSetSortPO);
            }
        }
        for (DictTSetSortPO dictTSetSortPO : sortColumns) {
            String isDefault = dictTSetSortPO.getIsDefault();
            String isReserve = dictTSetSortPO.getIsReserve();
            if ((isReserve == null || "0".equals(isReserve)) && "1".equals(isDefault)) {
                sortPoList.add(dictTSetSortPO);
            }
        }
        return sortPoList;
    }

}
