/**
 * @Title: IncrementGridService.java
 * @Copyright (C) 2016 太极华青
 * @Description:
 * @Revision 1.0 2016-1-4  CAOK
 */

package commons.inputcomponent.grid.incrementgrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.backup.service.ICommonDataBackupService;
import com.tjhq.commons.inputcomponent.constants.Constants;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.grid.incrementgrid.service.IIncrementGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.setting.external.po.DictTSetSortPO;

/**
 * @ClassName: IncrementGridService
 * @Description: Description of this class
 * @author: CAOK 2016-1-4 上午10:13:21
 */

@Service
@Transactional(readOnly = true)
public class IncrementGridService extends CommonGridService implements IIncrementGridService {

    Logger logger = Logger.getLogger(IncrementGridService.class);
    @Resource
    private IUtilDAO utilDAO;
    /**
     * @Fields commonDataBackupService : Description
     */
    @Resource
    private ICommonDataBackupService commonDataBackupService;

    /**
     * @return commonDataBackupService
     */
    public ICommonDataBackupService getCommonDataBackupService() {
        return commonDataBackupService;
    }

    /**
     * @param commonDataBackupService 要设置的 commonDataBackupService
     */
    public void setCommonDataBackupService(ICommonDataBackupService commonDataBackupService) {
        this.commonDataBackupService = commonDataBackupService;
    }

    /**
     * @return utilDAO
     */
    public IUtilDAO getUtilDAO() {
        return utilDAO;
    }

    /**
     * @param utilDAO 要设置的 utilDAO
     */
    public void setUtilDAO(IUtilDAO utilDAO) {
        this.utilDAO = utilDAO;
    }

    @Override
    public Table initStructure(Table table, String userID) throws ServiceException {
        super.initStructure(table, userID);
        CommonGrid grid = (CommonGrid) table;
        grid.setGroupColumnsList(null); // 增量表不支持分组
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            if (column.getColumnDBName().equals(Constants.INCREMENT_FLAG)) {
                column.setReadOnly(true);
                break;
            }
        }

        return table;
    }

    @Override
    public boolean saveData(Table table) throws ServiceException {
        List<Map<String, Object>> insertValues = table.getInsertValues();
        for (Map<String, Object> data : insertValues) {
            data.put(Constants.INCREMENT_FLAG, null);
        }
        return super.saveData(table);
    }

    @Override
    public Object getData(Table table) throws ServiceException {
        //Grid grid = (Grid) table;
        //setPageInfo(grid.getPageInfo());
        // 得到正式表数据
        PageInfo curPageInfo = (PageInfo) super.getData(table);
        
        // 设置备份表
        setBakTable(table);
        setBakCondition(table, "1");
        boolean isIncludeSumRow = removeBakDataSum(table);

        // 获取备份表数据
        PageInfo bakPageInfo = (PageInfo) super.getData(table);
        // 比较数据
        compare(curPageInfo, bakPageInfo, isIncludeSumRow);

        return curPageInfo;
    }
    
    protected void setPageInfo(PageInfo curPageInfo) {
        //修改分页数
        int pages = (int) Math.ceil(curPageInfo.getStart() / curPageInfo.getLimit());
        curPageInfo.setLimit(curPageInfo.getLimit()/2);
        if (curPageInfo.getStart() > 0) {
            curPageInfo.setStart(curPageInfo.getStart() - curPageInfo.getLimit()/2*pages);
        }
        
    }

    @SuppressWarnings("unchecked")
    protected void compare(PageInfo curPageInfo, PageInfo bakPageInfo, boolean isIncludeSumRow) {
        int dataKeyIndex = 0;
        int rowSecuIndex = 0;
        int incrFlagIndex = 0;
        if (curPageInfo.getColumns().size() > 0) {
            dataKeyIndex = getDataIndex(curPageInfo);
            rowSecuIndex = getRowSecuIndex(curPageInfo);
            incrFlagIndex = getIncrementFlagIndex(curPageInfo);
        } else if (bakPageInfo.getColumns().size() > 0) {
            dataKeyIndex = getDataIndex(bakPageInfo);
            rowSecuIndex = getRowSecuIndex(bakPageInfo);
            incrFlagIndex = getIncrementFlagIndex(bakPageInfo);
            curPageInfo.setColumns(bakPageInfo.getColumns());
            curPageInfo.setTotal(bakPageInfo.getTotal()); 
        } else {
            return;
        }
        
        Map<String, Object> bakDataMap = getBakData(bakPageInfo, dataKeyIndex);
        List<Object> tempDataList = null;
        List<Object> resultList = new ArrayList<Object>();
        String dataKey = null;
        boolean isModify = true;
        for (Object data : curPageInfo.getDataList()) {
            tempDataList = (List<Object>) data;
            if (isIncludeSumRow) {
                isIncludeSumRow = false;
                resultList.add(tempDataList);
                continue;
            }
            
            dataKey = (String) tempDataList.get(dataKeyIndex);
            if (!bakDataMap.containsKey(dataKey)) { // 如果在备份表中不存在，则说明是新增数据
                tempDataList.remove(incrFlagIndex);
                tempDataList.add(incrFlagIndex, Constants.INCREMENT_FLAG_ADD); // "新增"
                resultList.add(tempDataList);
                continue;
            }
            
            isModify = true;
            if (tempDataList.equals(bakDataMap.get(dataKey))) {
                isModify = false;
            }

            // 如果存在为修改数据
            tempDataList.remove(incrFlagIndex);
            tempDataList.add(incrFlagIndex, Constants.INCREMENT_FLAG_MODIFY); // "正式表修改"
            resultList.add(tempDataList);
            
            if (isModify) {
                tempDataList = (List<Object>) bakDataMap.get(dataKey);
                tempDataList.remove(incrFlagIndex);
                tempDataList.add(incrFlagIndex, Constants.INCREMENT_FLAG_BAK_MODIFY); // "备份表修改"
                tempDataList.remove(rowSecuIndex);
                tempDataList.add(rowSecuIndex, "2"); // 备份数据设置为只读
                resultList.add(tempDataList);
            }

            bakDataMap.remove(dataKey);

        }

        for (Map.Entry<String, Object> entry : bakDataMap.entrySet()) {
            tempDataList = (List<Object>) entry.getValue();
            tempDataList.remove(incrFlagIndex);
            tempDataList.add(incrFlagIndex, Constants.INCREMENT_FLAG_DELETE); // "删除数据"
            tempDataList.remove(rowSecuIndex);
            tempDataList.add(rowSecuIndex, "2"); // 备份数据设置为只读
            resultList.add(tempDataList);
        }
        curPageInfo.setDataList(resultList);
        curPageInfo.setTotal(curPageInfo.getTotal() + bakPageInfo.getTotal());
    }
    
    protected boolean isIncludeSumRow(Table table) {
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            if (column.isSum() && column.getDataType().equals("N")) {
                return true;
            }
        }
        
        return false;
    }

    private int getDataIndex(PageInfo pageInfo) {
        return pageInfo.getColumns().indexOf(Constants.BAKDATAKEY);
    }

    private int getRowSecuIndex(PageInfo pageInfo) {
        return pageInfo.getColumns().indexOf(Constants.ROWSECU);
    }

    private int getIncrementFlagIndex(PageInfo pageInfo) {
        return pageInfo.getColumns().indexOf(Constants.INCREMENT_FLAG);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getBakData(PageInfo bakPageInfo, int dataKeyIndex) {
        List<Object> dataList = bakPageInfo.getDataList();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        for (Object data : dataList) {
            dataMap.put((String) ((List<Object>) data).get(dataKeyIndex), data);
        }
        return dataMap;
    }

    /**
     * . 设置备份表查询条件
     * @param table
     * @throws
     */
    protected void setBakCondition(Table table, String type) {
        Grid grid = (Grid) table;
        Condition bakCon = new Condition();
        if ("1".equals(type)) { //查询对比数据
            bakCon.setExpression(" BAKVERSION = 0 AND BAKTYPE = '2' ");
        } else { //查询恢复数据
            bakCon.setExpression(" BAKTYPE = '2' ");
        }
        
        grid.getQueryPanelParamList().add(bakCon);

    }
    
    /**. 去掉备份表的合计功能
     * @param table
     * @throws
     */
    protected boolean removeBakDataSum(Table table) {
        boolean isIncludeSumRow = false;
        List<Column> list = TableUtil.getLeafColumnList(table);
        for (Column column : list) {
            if (column.isSum() && column.getDataType().equals("N")) {
                column.setSum(false);
                isIncludeSumRow = true;
            }
        }
        
        return isIncludeSumRow;
    }

    @Override
    public void backUpData(String tableID, String bakWhere) throws ServiceException {
        getCommonDataBackupService().backupData(tableID, bakWhere, "3");
    }

    @Override
    public Object getBackupData(Table table) throws ServiceException {
        Grid grid = (Grid) table;
        // 得到正式表数据
        PageInfo curPageInfo = (PageInfo) super.getData(table);
        
        setBakTable(table);
        setBakTableOrder(table);
        setBakCondition(table, "0");
        grid.setPagination(false);
        removeBakDataSum(table);

        // 获取备份表数据
        PageInfo bakPageInfo = (PageInfo) super.getData(table);
        
        mergeData(curPageInfo, bakPageInfo);
        return curPageInfo;
    }
    
    /**
     * . 设置备份表表名
     * @param table
     * @throws
     */
    protected void setBakTable(Table table) throws ServiceException {
        Grid grid = (Grid) table;
        table.setTableDBName(table.getTableDBName() + "_BAK");
        PageInfo bakPageInfo = new PageInfo();
        bakPageInfo.setLimit(grid.getPageInfo().getLimit());
        bakPageInfo.setStart(grid.getPageInfo().getStart());
        grid.setPageInfo(bakPageInfo);
        if (logger.isDebugEnabled()) {
            try {
                getUtilDAO().queryObject("SELECT * FROM " + table.getTableDBName() + " WHERE ROWNUM = 1");

            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException(ExceptionCode.INP_00104, table.getTableName() + "备份表不存在!", false);
            }
        }
    }
    
    protected void setBakTableOrder(Table table) throws ServiceException {
        CommonGrid grid = (CommonGrid) table;
        // 设置DataKey排序
        DictTSetSortPO dataKeySort = new DictTSetSortPO();
        dataKeySort.setIsReserve("1");
        dataKeySort.setAscFlag("1");
        dataKeySort.setDbColumnName(Constants.BAKDATAKEY);

        // 设置备份版本排序
        DictTSetSortPO bakVersionSort = new DictTSetSortPO();
        dataKeySort.setIsReserve("1");
        dataKeySort.setAscFlag("1");
        dataKeySort.setDbColumnName(Constants.BAKVERSION);

        if (grid.getSortColumnsList() == null) {
            grid.setSortColumnsList(new ArrayList<DictTSetSortPO>());
        }
        grid.getSortColumnsList().add(0, dataKeySort);
        grid.getSortColumnsList().add(1, bakVersionSort);
    }
    
    @SuppressWarnings("unchecked")
    protected void mergeData(PageInfo curPageInfo, PageInfo bakPageInfo) {
        int dataKeyIndex = 0;
        int incrFlagIndex = 0;
        int rowSecuIndex = 0;
        int tempDataSize = 0; 
        if (curPageInfo.getColumns().size() > 0) {
            dataKeyIndex = getDataIndex(curPageInfo);
            incrFlagIndex = getIncrementFlagIndex(curPageInfo);
            rowSecuIndex = getRowSecuIndex(curPageInfo);
        } else if (bakPageInfo.getColumns().size() > 0) {
            curPageInfo.setColumns(bakPageInfo.getColumns());
            dataKeyIndex = getDataIndex(bakPageInfo);
            incrFlagIndex = getIncrementFlagIndex(bakPageInfo);
            rowSecuIndex = getRowSecuIndex(bakPageInfo);
        } else {
            return;
        }
        
        if (incrFlagIndex > rowSecuIndex) {
            tempDataSize = incrFlagIndex;
        } else {
            tempDataSize = rowSecuIndex;
        }
        
        Map<String, List<Object>> bakDataMap = getBakDatas(bakPageInfo, dataKeyIndex);
        List<Object> tempDataList = null;
        List<Object> tempDatas = null;
        List<Object> resultList = new ArrayList<Object>();
        String dataKey = null;
        for (Object data : curPageInfo.getDataList()) {
            tempDataList = (List<Object>) data;
            dataKey = (String) tempDataList.get(dataKeyIndex);
            if (!bakDataMap.containsKey(dataKey)) { // 如果在备份表中不存在，则说明是新增数据
                continue;
            }

            // 如果存在为修改数据
            tempDataList.remove(incrFlagIndex);
            tempDataList.add(incrFlagIndex, "\u4fee\u6539\u6570\u636e"); // "修改数据"
            tempDataList.remove(rowSecuIndex);
            tempDataList.add(rowSecuIndex, "2"); // 正式表数据不可选
            resultList.add(tempDataList);

            tempDatas = (List<Object>) bakDataMap.get(dataKey);
            for (Object bakData : tempDatas) {
                tempDataList = (List<Object>) bakData;
                tempDataList.remove(incrFlagIndex);
                tempDataList.add(incrFlagIndex, "&nbsp;&nbsp;\u539f\u59cb\u6570\u636e"); // "原始数据"
                resultList.add(tempDataList);
            }

            bakDataMap.remove(dataKey);

        }
        
        // 处理删除的数据
        for (Map.Entry<String, List<Object>> entry : bakDataMap.entrySet()) {
            tempDatas = (List<Object>) entry.getValue();
            if (dataKey == null || !dataKey.equals(entry.getKey())) {
                tempDataList = new ArrayList<Object>();
                for (int i = 0; i <= tempDataSize; i++) {
                    if (i == incrFlagIndex) {
                        tempDataList.add("\u4fee\u6539\u6570\u636e");//修改数据
                    } else if (i == rowSecuIndex){
                        tempDataList.add(rowSecuIndex, "2"); // 正式表数据不可选
                    }else {
                        tempDataList.add(null);
                    }
                }
                // 如果是删除数据 插入一条空数据
                resultList.add(tempDataList);
            }
            dataKey = entry.getKey();
            for (Object bakData : tempDatas) {
                tempDataList = (List<Object>) bakData;
                tempDataList.remove(incrFlagIndex);
                tempDataList.add(incrFlagIndex, "&nbsp;&nbsp;\u539f\u59cb\u6570\u636e"); // "原始数据"
                resultList.add(tempDataList);
            }
        }

       
        curPageInfo.setDataList(resultList);
    }
    
    @SuppressWarnings("unchecked")
    private Map<String, List<Object>> getBakDatas(PageInfo bakPageInfo, int dataKeyIndex) {
        List<Object> dataList = bakPageInfo.getDataList();
        Map<String, List<Object>> dataMap = new HashMap<String, List<Object>>();
        String dataKey = null;
        List<Object> datas = null;
        for (Object data : dataList) {
            dataKey = (String) ((List<Object>) data).get(dataKeyIndex);
            if (dataMap.containsKey(dataKey)) {
                datas = dataMap.get(dataKey);
            } else {
                datas = new ArrayList<Object>();
                dataMap.put(dataKey, datas);
            }
            datas.add(data);
            dataMap.put(dataKey, datas);
        }
        return dataMap;
    }
}
