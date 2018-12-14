/**
 * @Title: CommonGridService.java
 * @Copyright (C) 2015 太极华青
 * @Description: 通用录入底层类
 * @Revision 6.0 2015-11-24  CAOK
 */
package commons.inputcomponent.grid.commonGrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.commonGrid.dao.ICommonGridMapper;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.setting.external.po.DictTSetGroupPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydDetPO;
import com.tjhq.commons.setting.external.po.DictTSetQuerydPO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;

/**
 * @ClassName: CommonGridService
 * @Description: 一般录入表实现类
 * @author: CAOK 2015-11-24 下午02:53:19
 */
@Service("commonGridService")
@Transactional(readOnly = true)
public class CommonGridService extends BaseGridService implements ICommonGridService {

    /**
     * @Fields entryOuterService : 录入界面定义服务
     */
    @Resource
    private IEntryOuterService entryOuterService;
    /**
     * @Fields commonGridMapper : 数据库访问服务
     */
    @Resource
    private ICommonGridMapper commonGridMapper;

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
     * @return commonGridMapper
     */
    public ICommonGridMapper getCommonGridMapper() {
        return commonGridMapper;
    }

    /**
     * @param commonGridMapper 要设置的 commonGridMapper
     */
    public void setCommonGridMapper(ICommonGridMapper commonGridMapper) {
        this.commonGridMapper = commonGridMapper;
    }

    @Override
    public Table initStructure(Table table, String userID) throws ServiceException {
        CommonGrid grid = (CommonGrid) table;
        super.initStructure(grid, userID);

        String tableID = grid.getTableID();
        List<DictTSetSortPO> sortList = null;
        try {
            sortList = getSortColumns(tableID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载表排序信息出错!", false);
        }
        grid.setSortColumnsList(sortList);
        List<DictTSetGroupPO> groupList = null;
        try {
            groupList = getGroupColumns(tableID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载表分组信息出错!", false);
        }
        grid.setGroupColumnsList(groupList);
        
        /*
         * 查询默认值
         */
        try {
            grid.setQueryPanelParamList(getTableDefaultConditionList(tableID));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载表默认查询信息出错!", false);
        }

        return grid;
    }

    /**.
     * 获取排序列
     * @param tableID 表ID
     * @return 排序列
     * @throws Exception 获取排序列异常
     */
    protected List<DictTSetSortPO> getSortColumns(String tableID) throws Exception {
        List<DictTSetSortPO> sortColumns = getEntryOuterService().getDataSortList(tableID);
        return sortColumns;
    }

    @Override
    public Object getData(Table table) throws ServiceException {
        super.setTableColumns(table);
        CommonGrid commonGrid = (CommonGrid) table;
        Map<String, Object> paramMap = getQueryParamter(commonGrid);
        
        // 取得数据
        PageInfo pageInfo = commonGrid.getPageInfo();
        // 如果表格有分页，需要取得数据总条数
        if (commonGrid.isPagination()) { 
            pageInfo.setTotal(getTotalCount(paramMap));
            paramMap.putAll(getPageParamter(pageInfo));
        }
        
        if (commonGrid.getSortColumnsList() != null) {
            String sortSQL = handleSortParam(table, setSort(commonGrid.getSortColumnsList()));
            if (sortSQL != null && sortSQL.trim().length() > 0) {
                paramMap.put("sortSQL", sortSQL);
            }
        }
        List<Map<String, Object>> resultList = null;
        
        if (commonGrid.getGroupColumnsList() != null && commonGrid.getGroupColumnsList().size() > 0) {
            try {
                resultList = getGroupGridData(commonGrid.getGroupColumnsList(), paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "加载表数据出错!", false);
            }
        } else {
            Map<String, Object> sumData = null;
            try {
                sumData = getSumData(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "加载表合计行信息出错!", false);
            }
            
            resultList = getGridRecords(paramMap);
            
            if (null != sumData) {
                resultList.add(0, sumData);
            }
        }
        
        
        setGridData(resultList, pageInfo);
        
        return pageInfo;
    }

    /**.
     * 获取设置界面定义的分组列
     * @param tableID 表ID
     * @return 分组列
     * @throws Exception 获取分组列异常
     */
    protected List<DictTSetGroupPO> getGroupColumns(String tableID) throws Exception {
        List<DictTSetGroupPO> resultList = getEntryOuterService().getDataGroupList(tableID);
        return resultList;
    }
    
    /**.
     * 获取表默认查询条件
     * @param tableID 表ID
     * @return 默认查询
     * @throws Exception
     */
    protected List<Condition> getTableDefaultConditionList(String tableID) throws Exception {
        List<Condition> conditionList = new ArrayList<Condition>();
        DictTSetQuerydPO queryPO = getEntryOuterService().getDataQuerydList(tableID);
        if (null == queryPO || null == queryPO.getQuerydDet() || queryPO.getQuerydDet().size() == 0) {
            return conditionList;
        }
        
        Condition condition = null;
        for (DictTSetQuerydDetPO queryItem : queryPO.getQuerydDet()) {
            if (null == queryItem.getDefvalue() || "".equals(queryItem.getDefvalue().trim())) {
                continue;
            }
            condition = new Condition();
            condition.setColumnID(queryItem.getCtrlID());
            condition.setColumnDbName(queryItem.getDbColumnName());
            condition.setOperator(queryItem.getOperator());
            condition.setQueryValue(queryItem.getDefvalue());
            conditionList.add(condition);
        }
        
        return conditionList;
    }

    /**.
     * 获取分组数据
     * @param groupDefineList 分组列定义
     * @param paramMap 数据查询参数
     * @return 数据
     * @throws Exception 查询异常
     */
    @SuppressWarnings("unchecked")
    protected List<Map<String, Object>> getGroupGridData(List<DictTSetGroupPO> groupDefineList,
            Map<String, Object> paramMap) throws Exception {
        String groupcols = "";
        String groupflag = "";
        String grouporder = "";
        List<Column> columns = (List<Column>) paramMap.get("leafColumnList");
        List<Column> groupColumns = new ArrayList<Column>();
        Map<String, Column> columnMap = TableUtil.getColumnMap("DbName", columns);
        Column tempColumn = null;
        for (int i = 0; i < groupDefineList.size(); i++) {
            tempColumn = columnMap.get(groupDefineList.get(i).getDbColumnName());
            if (tempColumn.getExtProperties() == null) {
                tempColumn.setExtProperties(new HashMap<String, Object>());
            }
            if ("0".equals(groupDefineList.get(i).getIsasc())) {
                tempColumn.getExtProperties().put("GROUPORDER_1", "DESC");
            } else {
                tempColumn.getExtProperties().put("GROUPORDER_1", "ASC");
            }
            groupColumns.add(tempColumn);
            groupcols = groupcols + "{" + groupDefineList.get(i).getDbColumnName() + "}";
            groupflag += "0";
            grouporder += "1";
        }

        String sumcols = "";
        for (Column column : columns) {
            if (column.isSum()) {
                sumcols = sumcols + "{" + column.getColumnDBName() + "}";
            }
        }
        paramMap.put("groupflag", groupflag);
        paramMap.put("grouporder", grouporder);
        paramMap.put("groupColumns", groupColumns);
        paramMap.put("groupcols", groupcols);
        paramMap.put("sumcols", sumcols);
        return getCommonGridMapper().getGroupGridData(paramMap);
    }

    /**.
     * <p>Title: getTotalCount</p>
     * <p>Description: 需要取得数据总条数</p>
     * @param paramMap 数据查询参数
     * @return 总条数
     * @throws ServiceException 业务异常
     * @see com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService#getTotalCount
     *          (java.util.Map)
     */
    protected int getTotalCount(Map<String, Object> paramMap) throws ServiceException {
        Integer num = null;
        if (paramMap.get("groupColumns") != null) {
            try {
                num = getCommonGridMapper().getGroupGridDataCount(paramMap);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.ERR_00000, "获取数据分页信息出错!", false);
            }
            if (num != null) {
                return num.intValue();
            } else {
                return 0;
            }
        } else {
            return super.getTotalCount(paramMap);
        }
    }

}
