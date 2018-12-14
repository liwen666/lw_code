
package commons.inputcomponent.grid.fixgrid.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tjhq.commons.dict.util.DictDBConstants;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.basegrid.service.impl.BaseGridService;
import com.tjhq.commons.inputcomponent.grid.fixgrid.dao.IFixGridMapper;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixColumn;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.grid.fixgrid.service.IFixGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.dao.IUtilDAO;
import com.tjhq.commons.setting.external.po.DictTSetFixPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;

@Service
public class FixGridService extends BaseGridService implements IFixGridService {
    @Resource
    private IEntryOuterService entryOuterService;

    @Resource
    private IUtilDAO utilMapper;

    @Resource
    private IFixGridMapper fixGridMapper;
    
    private static List<String> controlFields = new ArrayList<String>();
    
    static {
        // 定义在dict_t_defaultcol表中的数据
        // 固定行列表默认字段
        controlFields.add("CELLSECU");
        controlFields.add("FDCODE");
        controlFields.add("ISDJ");
        controlFields.add("ISHZ");
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

    public IUtilDAO getUtilMapper() {
        return utilMapper;
    }

    public void setUtilMapper(IUtilDAO utilMapper) {
        this.utilMapper = utilMapper;
    }

    /**
     * @return controlFields
     */
    public static List<String> getControlFields() {
        return controlFields;
    }

    @Override
    protected Column getNewColumn() {
        return new FixColumn();
    }

    @Override
    public Table initStructure(Table table, String userID) throws ServiceException {
        FixGrid grid = (FixGrid) super.initStructure(table, userID);
        setFixColumn(grid);
        
        //隐藏表控制列
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        for (Column column : leafColumns) {
            if (getControlFields().contains(column.getColumnDBName().replaceAll("[_1,_2,_3]", ""))) {
                column.setVisible(false);
            }
        }
        
        return grid;
    }

    @Override
    public boolean saveData(Table table) throws ServiceException {
        super.setTableColumns(table);
        FixGrid grid = (FixGrid) table;
        List<Map<String, Object>> insertValues = grid.getInsertValues();
        for (int i = 0; i < insertValues.size(); i++) {
            Map<String, Object> vmap = (Map<String, Object>) insertValues.get(i);
            vmap.put("DATAKEY", null);
            vmap.put("ISTEMPLATE", "0");
        }
        super.saveData(grid);
        return true;
    }

    private void setFixColumn(FixGrid grid) throws ServiceException {
        List<DictTSetFixPO> fixFactorList = null;
        try {
            fixFactorList = entryOuterService.getDataFixList(grid.getTableID());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionCode.ERR_00000, "加载固定行列表固定列设置出错!", false);
        }
        List<Column> columns = TableUtil.getLeafColumnList(grid);
        Iterator<Column> iter = columns.iterator();
        FixColumn fixColumn = null;
        // 获取模板数据倒挤列 其中项
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("tableName", grid.getTableDBName());
        paramMap.put("tableNameTempl", grid.getTableDBName() + DictDBConstants.SUFFIX_CONFIG_VIEW);
        Map<String, Object> templateData = fixGridMapper.getTemplateSetData(paramMap).get(0);
        while (iter.hasNext()) {
            fixColumn = (FixColumn) iter.next();
            for (int i = 0; i < fixFactorList.size(); i++) {
                DictTSetFixPO dictFixPo = (DictTSetFixPO) fixFactorList.get(i);
                // 层次名称列
                if (dictFixPo.getLvlNanmeCol().equals(fixColumn.getColumnID())) {
                    fixColumn.setOwnerType(dictFixPo.getTypeID());
                    fixColumn.setIsCtrl(1);
                    fixColumn.setIsDj(0);
                    // 判断数据类型 为数值的为倒挤列
                    if (fixColumn.getDataType().equals("N")) {
                        fixColumn.setIsDj(1);
                    }
                    fixColumn.setIsQzx(!(templateData.get("ISQZX_" + dictFixPo.getTypeID()) != null && templateData
                            .get("ISQZX_" + dictFixPo.getTypeID()).toString().equals("0")) ? 1 : 0);
                }
                // 当前FDCODE对应的COLUMNID
                if (dictFixPo.getFdCodeToCols().indexOf(fixColumn.getColumnID()) > -1) {
                    fixColumn.setOwnerType(dictFixPo.getTypeID());
                    fixColumn.setIsCtrl(0);
                    fixColumn.setOwnerColumn(dictFixPo.getLvlNanmeCol());
                    if (fixColumn.getDataType().equals("N")) {
                        fixColumn.setIsDj(1);
                    }
                    fixColumn.setIsQzx(!(templateData.get("ISQZX_" + dictFixPo.getTypeID()) != null && templateData
                            .get("ISQZX_" + dictFixPo.getTypeID()).toString().equals("0")) ? 1 : 0);
                }
            }
        }
    }

    @Override
    public Object getData(Table table) throws ServiceException {
        FixGrid fixGrid = (FixGrid) table;
        super.setTableColumns(table);
        setFixColumn(fixGrid);

        Map<String, Object> paramMap = getQueryParamter(fixGrid);
        // 浮动表模板数据转换
        String selectElement = paramMap.get("selectElement").toString();
        paramMap.put("floatTemplateDataColumn", selectElement.replace("DATAKEY", "TEMPLATEID AS DATAKEY"));
        // 取得数据
        PageInfo pageInfo = fixGrid.getPageInfo();
        if (fixGrid.getSortColumnsList() != null) {
            String sortSQL = handleSortParam(table, setSort(fixGrid.getSortColumnsList()));
            if (!"".equals(sortSQL)) {
                paramMap.put("sortSQL", sortSQL);
            }
        }
        // 判断最全的FDCODE
        String isAllSuperId = fixGridMapper.getAllFdcode(paramMap);
        paramMap.put("superid", isAllSuperId);
        paramMap.put("tableNameTempl", paramMap.get("tableName").toString() + DictDBConstants.SUFFIX_CONFIG_VIEW);
        List<Map<String, Object>> resultList = fixGridMapper.getFixGridData(paramMap);
        
        clearData(fixGrid, resultList);
        
        this.setGridData(resultList, pageInfo);
        
        return pageInfo;
    }
    
    /**.
     * 清除结果集中多余的数据
     * @param fixGrid 固定行列表格对象
     * @param dataList 数据集
     * @throws
     */
    protected void clearData(FixGrid fixGrid, List<Map<String, Object>> dataList) {
        if (dataList == null || dataList.size() == 0) {
            return;
        }
        
        Map<String, List<String>> ctrlColMap = new HashMap<String, List<String>>();
        List<Column> columns = TableUtil.getLeafColumnList(fixGrid); 
        FixColumn fixColumn = null;
        List<String> ctrlColumnList = null;
        for (Column column : columns) {
            fixColumn = (FixColumn)column;
            if (fixColumn.getIsCtrl() != 1 && fixColumn.getOwnerColumn() == null) {
                continue;
            }
            if (fixColumn.getIsCtrl() == 1) {
                if (ctrlColMap.containsKey(fixColumn.getColumnID())) {
                    ctrlColumnList = ctrlColMap.get(fixColumn.getColumnID());
                } else {
                    ctrlColumnList = new ArrayList<String>();
                }
                ctrlColumnList.add(0, fixColumn.getColumnDBName());
                ctrlColMap.put(fixColumn.getColumnID(), ctrlColumnList);
                continue;
            }
            
            if (ctrlColMap.containsKey(fixColumn.getOwnerColumn())) {
                ctrlColumnList = ctrlColMap.get(fixColumn.getOwnerColumn());
            } else {
                ctrlColumnList = new ArrayList<String>();
            }
            ctrlColumnList.add(fixColumn.getColumnDBName());
            ctrlColMap.put(fixColumn.getOwnerColumn(), ctrlColumnList);
            
        }
        
        for (Map<String, Object> dataMap : dataList) {
            for (Map.Entry<String, List<String>> entry : ctrlColMap.entrySet()) {
                String ctrlName = entry.getValue().get(0);//控制列名
                for (int i = 1; i < entry.getValue().size(); i++) {
                    if (dataMap.get(ctrlName) == null) {
                        dataMap.put(entry.getValue().get(i), null);
                    }
                }
            }
        }
        
    }

    @Override
    @SuppressWarnings("all")
    protected void setTableFormula(Table table) throws ServiceException {
        super.setTableFormula(table);
        FixGrid grid = (FixGrid) table;
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("tableID", table.getTableID());
        fixGridMapper.getFormulaCell(parameterMap);
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
}
