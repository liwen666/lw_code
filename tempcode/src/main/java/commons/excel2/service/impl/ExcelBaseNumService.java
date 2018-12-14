package commons.excel2.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.excel2.service.IExcelBaseNumService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.propertygrid.po.PropertyGrid;
import com.tjhq.commons.inputcomponent.grid.propertygrid.service.impl.PropertyGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.exp.datainput.table.service.IPropertyGridInputService;
@Service
@Transactional
public class ExcelBaseNumService extends ExcelService implements IExcelBaseNumService {
	
	@Resource
	public IPropertyGridInputService propertyGridInputService;
	
	@Resource
	public PropertyGridService propertyGridService;
	
	@Override
	public PageInfo getExcelData(String grid, String noData, PageInfo pageInfo) throws ServiceException {
		try{
			PropertyGrid propertyGrid = (PropertyGrid) new ObjectMapper().readValue(grid, PropertyGrid.class);
			pageInfo = (PageInfo) this.propertyGridService.getData(propertyGrid);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
    @Override
	public List<Column> getGridColumnList(String tableID, String grid, String agencyIsLeaf) throws ServiceException{
    	PropertyGrid propertyGrid = new PropertyGrid();
    	propertyGrid.setTableID(tableID);
		String userID = SecureUtil.getCurrentUser().getGuid();
		propertyGridService.initStructure(propertyGrid, userID);
		List<Column> columnList = super.getAllColumnList(propertyGrid);
		return columnList;
    }
    
    @Override
    public void fillHead(DictTModelPO dictTModel, List<Column> columnList, SXSSFWorkbook workbook, int headRowNum) throws ServiceException {
		//表ID
    	String tableID = dictTModel.getTableid();
    	//表名
		String sheetName = dictTModel.getName();
		//表名的格式
		CellStyle titleStyle = this.getStyle(workbook, "黑体", Font.BOLDWEIGHT_BOLD, (short) 20, XSSFCellStyle.ALIGN_CENTER, false);
		//标题的格式
		CellStyle headStyle = this.getStyle(workbook, "宋体", Font.BOLDWEIGHT_BOLD, (short) 12, XSSFCellStyle.ALIGN_CENTER, true);
		//得到sheet
		Sheet sheet = workbook.createSheet(sheetName);
		//第一行为表名
		Row xssfrow = sheet.createRow((short) 0);
		Cell cell = xssfrow.createCell((short) 0);
		cell.setCellValue(sheetName);
		cell.setCellStyle(titleStyle);
		//表名占多列，单元格合并
		CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, 3);
		prepareCellmergeRegion(sheet, region1);
		sheet.addMergedRegion(region1);

		//第二行为标题
		//处理标题列
		headRowNum = 1;
		xssfrow = sheet.createRow(headRowNum);

		//第一列：序号
		int colNum = 0;
		cell = xssfrow.createCell(colNum);
		cell.setCellValue("序号");
		cell.setCellStyle(headStyle);
		
		//第二列：属性
		colNum += 1;
		cell = xssfrow.createCell(colNum);
		cell.setCellValue("属性");
		cell.setCellStyle(headStyle);
		
		//遍历财年
		List<HashMap<String, String>> mapList = this.propertyGridInputService.getBaseNumInitData(tableID);
		for(HashMap<String, String> map : mapList){
			colNum++;
			cell = xssfrow.createCell(colNum);
			cell.setCellValue(map.get("FINYEAR"));
			cell.setCellStyle(headStyle);
		}
		
		//遍历列
		int num = 1;//序号
		for(Column column : columnList){
			if (column.isVisible() == true) {
				headRowNum++;
				xssfrow = sheet.createRow(headRowNum);
				
				//序号
				cell = xssfrow.createCell(0);
				cell.setCellValue(num);
				num++;
				cell.setCellStyle(headStyle);
				
				//基本数字表，树型显示的标题
				cell = xssfrow.createCell(1);
				String preSpace = generateSpace(column.getLevelNo() * 3);
				String cellValue = column.getColumnName();
				if(column.isNullable() == false){
					cellValue = "*" + cellValue;
				}
				cellValue = preSpace + cellValue;
				cell.setCellValue(cellValue);
			}
		}
		//设置树型显示的标题列的宽度
		sheet.setColumnWidth(1, 30 * 256);
		
	}
    /**
	 * 查询并填充数据
	 */
    @Override
	public int getAndFillData(String grid, String noData, Sheet sheet, int headRowNum, HashMap<String, String> xlsxMap, List<Column> columnList) throws ServiceException {
		try {
			//查询数据
			PageInfo pageInfo = new PageInfo();
			pageInfo = this.getExcelData(grid, noData, pageInfo);
			//默认的开始填充起始行
			int startNum = headRowNum + 1;
			//最大列数
			int lastCellNum = sheet.getRow(headRowNum).getLastCellNum();
			//给EXCEL填充数据
			this.fillData(sheet, startNum, headRowNum, lastCellNum, pageInfo, xlsxMap, grid);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
    public void fillData(Sheet sheet, int startNum, int headRowNum, int lastCellNum, PageInfo pageInfo, HashMap<String, String> xlsxMap, String grid) throws ServiceException {
    	try{
	    	//得到pageInfo中的数据
			List<Object> dataList = pageInfo.getDataList();
			List<String> columns = pageInfo.getColumns();
	    	//处理数据
			if(dataList == null || dataList.size() == 0){
				return;
			}
			PropertyGrid propertyGrid = (PropertyGrid) new ObjectMapper().readValue(grid, PropertyGrid.class);
			List<Column> columnList = this.getGridColumnList(propertyGrid.getTableID(), null, "1");
			
			//从第三行开始遍历
			int xRow = 2;
			for (Column column : columnList) {
				if(column.isVisible()){
					Row row = sheet.getRow(xRow);
					if (row == null){
						row = sheet.createRow(xRow);
					}
					
					String columnDbName = column.getColumnDBName();
					
					//从第三列开始遍历
					int xCol = 2;
					for (Object object : dataList) {
						List<Object> data = (List<Object>) object;
						
						SXSSFCell cell = (SXSSFCell) row.getCell(xCol);
						if (cell == null) {
							cell = (SXSSFCell) row.createCell(xCol);
						}
						
						//若列中有SNAME_对应的列，则取SNAME_列对应的值
						int index1 = columns.indexOf(columnDbName);
						Object obj = data.get(index1);
						int index2 = columns.indexOf("SNAME_" + columnDbName);
						if(index2 != -1){
							obj = data.get(index2);
						}
						if(obj == null){
							obj = "";
						}
						setxCellValue(cell, obj, false);
						xCol++;
					}
					xRow++;
				}
			}
	    } catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
    
    //===========================================导入开始===========================================
    /**
	 * 获取开始读取数据的行数，从第二行标题上的财年开始
	 * @return
	 *//*
    @Override
	public int getStartRowNum(String tableID) throws ServiceException{
		return 1;
	}
	*//**
	 * 导入，处理并保存数据
	 * @throws ServiceException
	 *//*
	@Override
	public String handleAndSaveExcelData(String tableID, String agencyID, HttpServletRequest request, HashMap<String, LinkedHashMap<String, String>> codeNameMap, DictTModelPO dictTModel, XSSFWorkbook xssfWorkbook, int startRow, List<Column> displayLeafColumns, List<Column> hiddenLogicColumns, List<Column> displayAllColumns) throws ServiceException {
		try {
			String exportInfo = "";
			//获取第一个sheet
			Sheet sheet = xssfWorkbook.getSheetAt(0);
			if (sheet == null) {
				throw new ServiceException(ExceptionCode.ERR_00000, "Excel文件中未找到sheet工作表!", false);
			}
			ArrayList<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
			ArrayList<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
			
			int lastRowNum = displayAllColumns.size() + startRow;
			HashMap<String, Object> checkRepeatMap = new HashMap<String, Object>();
			int nowRowNum = 0;
			
			//遍历财年
			List<HashMap<String, String>> mapList = this.propertyGridInputService.getBaseNumInitData(tableID);
			
			//遍历列，从1开始遍历，跳过第一列序号
			int index = 2;
			for(HashMap<String, String> map : mapList){
				
				HashMap<String, Object> saveRowMap = new HashMap<String, Object>();
				String getDatakeySql = "";
				//遍历column的游标
				int zIndex = 0;
				for (int ir = startRow; ir <= lastRowNum; ir++) {
					Row row = sheet.getRow(ir);
					
					saveRowMap.put("STATUS", "1");
					saveRowMap.put("AGENCYID", agencyID);
					
					Column column = new Column();
					String columnDbName = "";
					
					//读第二行的时候，写死finyear
					if(ir == 1){
						columnDbName = "FINYEAR";
						column.setColumnDBName(columnDbName);
					}else{
						column = displayAllColumns.get(zIndex);
						if(zIndex >= displayAllColumns.size()){
							break;
						}
						columnDbName = column.getColumnDBName();
						zIndex++;
					}
					
					if("DATAKEY".equals(columnDbName)){
	                	saveRowMap.put("DATAKEY", ToolUtil.getUUID());
	                	continue;
	                }
					
					nowRowNum = ir + 1;
					XSSFCell cell = (XSSFCell) row.getCell(index);
					String v = "";
					if(cell == null){
	                	saveRowMap.put(columnDbName, v);
	                	continue;
	                }
	                
	                //处理某个单元格的数据
	                this.handleRowMap(saveRowMap, cell, column, v, codeNameMap);
					
				}
				index++;
				//处理
				this.handleDataList(dictTModel, agencyID, insertList, updateList, checkRepeatMap, nowRowNum, getDatakeySql, hiddenLogicColumns, saveRowMap);
			}
			
			//保存数据
			this.saveData(dictTModel, agencyID, request, insertList, updateList);
			return exportInfo;
		} catch (ServiceException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "导入失败:"+e.getErrorMessage(), false);
		}
	}
    *//**
	 * 导入，处理数据，覆盖
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 * @param checkRepeatMap
	 * @param nowRowNum
	 * @param getDatakeySql
	 * @param hiddenLogicKeyList
	 * @param saveRowMap
	 * @throws ServiceException
	 *//*
    @Override
	public void handleDataList(DictTModelPO dictTModel, String agencyID, ArrayList<Map<String, Object>> insertList, ArrayList<Map<String, Object>> updateList, HashMap<String, Object> checkRepeatMap, int nowRowNum, String getDatakeySql, List<Column> hiddenLogicKeyList, HashMap<String, Object> saveRowMap) throws ServiceException{
		insertList.add(saveRowMap);
	}
    *//**
	 * 导入，保存数据，覆盖
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 *//*
    @Override
	public void saveData(DictTModelPO dictTModel, String agencyID, HttpServletRequest request, ArrayList<Map<String, Object>> insertList, ArrayList<Map<String, Object>> updateList) throws ServiceException{
    	
    	String tableID = dictTModel.getTableid();
    	String dbTableName = dictTModel.getDbtablename();
    	String sql = "delete from " + dbTableName + " a where agencyid = '" + agencyID + "'";
		this.excelMapper.updateSql(sql);
		//保存数据
		FixGrid grid = new FixGrid();
		grid.setTableID(tableID);
		grid.setInsertValues(insertList);
		grid.setUpdateValues(updateList);
		grid.setTableDBName(dbTableName);
		grid.setTableName(dictTModel.getName());
		propertyGridService.saveData(grid);
	}*/
}
