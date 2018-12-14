package commons.excel2.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.excel2.dao.ExcelMapper;
import com.tjhq.commons.excel2.service.IExcelService;
import com.tjhq.commons.excel2.util.ReadExcelUtils;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.impl.CommonGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.Grid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.po.Table;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.inputcomponent.utils.ToolUtil;
import com.tjhq.commons.jackson.ObjectMapper;
import com.tjhq.commons.mongodb.service.ILogService;
import com.tjhq.exp.common.utils.SystemConfUtils;
@Service
@Transactional
public class ExcelService extends CommonGridService implements IExcelService {
	
	//日志
	public Logger logger = Logger.getLogger(ExcelService.class);
	
	@Resource
	public ExcelMapper excelMapper;
	
	@Resource
	public IDictTModelService dictTModelService;
	
	@Resource
	public IDictTFactorService dictTFactorService;
	
	@Resource
	public ICommonGridService commonGridService;
	
    @Resource
    public ILogService logService;
    
	public SXSSFWorkbook exportData(String grid, String appID, DictTModelPO dictTModel, String agencyID, String taskID, String noData, String agencyIsLeaf) throws ServiceException {
		try {
			String tableID = dictTModel.getTableid();
			//创建XSSFWorkbook
			SXSSFWorkbook workbook = new SXSSFWorkbook(1200);
			//创建SHEET
			Sheet sheet = workbook.createSheet(dictTModel.getName());
			//获取表列与excel列对应信息，并设置列默认样式。
			HashMap<String, String>	xlsxMap = getDictTFactorxlsxInfo(tableID, sheet);
			//获取表列最大层级
			int headRowNum = this.getHeadRowNum(tableID);
			//列
			List<Column> columnList = getGridColumnList(tableID, grid, agencyIsLeaf);
			//给EXCEL填充表头
			this.fillHead(dictTModel, columnList, workbook, headRowNum);
			//查询并填充数据
			int total = this.getAndFillData(grid, noData, sheet, headRowNum, xlsxMap, columnList);
			//查询代码表用于制作下拉框
			HashMap<String, String[]> codeMap = getCodeNameMapExport(tableID, agencyID, agencyIsLeaf);
			if(codeMap != null && codeMap.size() > 0 ){
				if(total == 0){
					total = 1;
				}
				//制作下拉框
				fillSelect(workbook, sheet, xlsxMap, codeMap, headRowNum + 1, headRowNum + total);
			}
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
    /**
	 * 获取代码表信息，只获取表下显示列，非扩展列的代码表信息。
	 */
	public HashMap<String, String[]> getCodeNameMapExport(String tableID, String agencyID, String agencyIsLeaf) throws ServiceException {
		HashMap<String, String[]> codeMap = new HashMap<String, String[]>();
		
		String str = "";
		if("1".equals(agencyIsLeaf)){
			str = " and u.isvisible = '1' ";
		}else{
			str = " and (u.isvisible = '1' or u.dbcolumnname = 'AGENCYID') ";
		}
		String sql = "select distinct u.csid, u.dbcolumnname, t.dbtablename, t.dynamicwhere from dict_t_modelcode t,dict_t_factor u where t.tableID = u.csid and u.tableID = '" + tableID + "' and substr(u.extprop,2,1) <> '1' " + str;
		List<HashMap<String, Object>> modelCodeList = this.excelMapper.selectExcelInfo(sql);
		
		for (HashMap<String, Object> map1 : modelCodeList) {
			String codeTableName = map1.get("DBTABLENAME") == null ? "" : (String) map1.get("DBTABLENAME");//代码表 表名
			String dbcolumnname = map1.get("DBCOLUMNNAME") == null ? "" : (String) map1.get("DBCOLUMNNAME");//代码表 表名
			String dynamicwhere = map1.get("DYNAMICWHERE") == null ? "" : (String) map1.get("DYNAMICWHERE");// 代码表where条件
			dynamicwhere = StringUtils.isNotEmpty(dynamicwhere) ? " where " + dynamicwhere : "";
			dynamicwhere = dynamicwhere.replaceAll(Matcher.quoteReplacement("$AGENCYID$"), agencyID);
			sql = "select "+getCodeNameStr()+" codename, guid from " + codeTableName + " " + Constants.TABLE_NAME_ALIAS + " " + dynamicwhere + "";
			List<HashMap<String, Object>> codeNameList = this.excelMapper.selectExcelInfo(sql);
			if(codeNameList.size() > 0){
				String[] arr = new String[codeNameList.size()];
				int i = 0;
				for (HashMap<String, Object> rowMap : codeNameList) {
					String codeName = (String) rowMap.get("CODENAME");
					arr[i] = codeName;
					i++;
				}
				codeMap.put(dbcolumnname, arr);
			}
			
		}
		return codeMap;
		
	}
    
	/**
	 * 查询并填充数据
	 */
	public int getAndFillData(String grid, String noData, Sheet sheet, int headRowNum, HashMap<String, String> xlsxMap, List<Column> columnList) throws ServiceException {
		try {
			//查询数据
			PageInfo pageInfo = new PageInfo();
			int start = 0;//查询的起始行
			int limit = 5000;//每次查询的数量
			
			pageInfo.setStart(start);
			pageInfo.setLimit(limit);
			pageInfo = this.getExcelData(grid, noData, pageInfo);
			//默认的开始填充起始行
			int startNum = headRowNum + 1;
			//最大列数
			int lastCellNum = sheet.getRow(headRowNum).getLastCellNum();
			//给EXCEL填充数据
			fillData(sheet, startNum, headRowNum, lastCellNum, pageInfo, xlsxMap);
			//下一批数据的起始行
			if(pageInfo != null && pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0){
				startNum += pageInfo.getDataList().size();
			}
			//把数据NULL，释放内存
			pageInfo.setDataList(null);
			//总数
			int total = pageInfo.getTotal();
			if(total > 0 && limit > 0 && total > limit){
				float pageNum = (float)total/(float)limit;
				if(pageNum > 1){
					
					//判断是否有合计列
					boolean hasSum = false;
					for (Column column : columnList) {
			            if (column.isSum() && column.getDataType().equals("N")) {
			                hasSum = true;
			                break;
			            }
			        }
					
					for(int i = 1; i <= pageNum; i++){
						//查询的起始行
						start += limit;
						//查询的起始行
						pageInfo.setStart(start);
						//查询数据
						pageInfo = this.getExcelData(grid, noData, pageInfo);
						//如果第一行是合计行则去掉
						if(hasSum){
					        if (pageInfo != null && pageInfo.getColumns() != null && pageInfo.getColumns().size() > 0) {
					        	//取datakey的位置
					        	int datakeyIndex = pageInfo.getColumns().indexOf(Constants.COL_DBNAME_DATAKEY);
								if(pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0){
									//取第一行数据
									List<Object> tempDataList = (List<Object>) pageInfo.getDataList().get(0);
									//取datakey的值
									String datakey = (String) tempDataList.get(datakeyIndex);
									if("****".equals(datakey)){
										pageInfo.getDataList().remove(0);
									}
								}
					        }
						}
						
						//给EXCEL填充数据
						fillData(sheet, startNum, headRowNum, lastCellNum, pageInfo, xlsxMap);
						//下一批数据的起始行
						if(pageInfo != null && pageInfo.getDataList() != null && pageInfo.getDataList().size() > 0){
							startNum += pageInfo.getDataList().size();
						}
						//把数据NULL，释放内存
						pageInfo.setDataList(null);
					}
				}
			}
			return total;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
	/**
	 * 查询并填充数据
	 */
	public void fillSelect(SXSSFWorkbook workbook, Sheet sheet, HashMap<String, String> xlsxMap, HashMap<String, String[]> codeMap, int firstRow, int lastRow) throws ServiceException {
		if(firstRow > 0 && lastRow > 0){
			DataValidationHelper helper = sheet.getDataValidationHelper();
			int a = 0;
			for(String key : codeMap.keySet()){
				a++;
				String cellColumn = "";
				if(xlsxMap.containsKey(Constants.EXCEL_KEY_DBCOLUMNNAME+key)){
					cellColumn = xlsxMap.get(Constants.EXCEL_KEY_DBCOLUMNNAME+key);
				}else{
					continue;
				}
				if(StringUtils.isEmpty(cellColumn)){
					continue;
				}
				int columnIndex = CellReference.convertColStringToIndex(cellColumn);
		        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(firstRow, lastRow, columnIndex, columnIndex);
		        //数据  
		        String[] datas = codeMap.get(key);
		        
		        //创建隐藏sheet
		        Sheet hiddenSheet = workbook.createSheet(key);
		        workbook.setSheetHidden(a, true);
		        Row row = null;
				Cell cell = null;
				for (int i = 0, length = datas.length; i < length; i++) {
					row = hiddenSheet.createRow(i);
					cell = row.createCell(0);
					cell.setCellValue(datas[i]);
				}
		        /*Name namedCell = workbook.createName();
				namedCell.setNameName(key);
				namedCell.setRefersToFormula(key+"!A1:A" + datas.length);*/
		        
				//数据有效性
		        DataValidationConstraint dataValidationConstraint = helper.createFormulaListConstraint(key+"!A1:A" + datas.length);
		        DataValidation dataValidation = helper.createValidation(dataValidationConstraint, cellRangeAddressList);
		        //处理Excel兼容性问题  
		        if(dataValidation instanceof XSSFDataValidation) {
		            dataValidation.setSuppressDropDownArrow(true);
		            dataValidation.setShowErrorBox(true);
		        }else {
		            dataValidation.setSuppressDropDownArrow(false);
		        }
		        sheet.addValidationData(dataValidation);
			}
		}
	}
	/**
	 * 查询数据
	 */
	public PageInfo getExcelData(String grid, String noData, PageInfo pageInfo) throws ServiceException {
		try {
			//得到一般录入表的Grid对象
			CommonGrid commonGrid = (CommonGrid) new ObjectMapper().readValue(grid, CommonGrid.class);
			//去掉分组
			commonGrid.setGroupColumnsList(null);
			//分页查询
			commonGrid.setPagination(true);
			//放入分页对象
			commonGrid.setPageInfo(pageInfo);
			//空模板则放入一个查不到数据的条件，激活合计SQL
			if("1".equals(noData)){
				Condition condition = new Condition();
				condition.setExpression(" datakey = '123214356675765765876866543'");
				List<Condition> conditionList = commonGrid.getQueryPanelParamList();
				if (conditionList == null){
					conditionList = new ArrayList<Condition>();
				}
				conditionList.add(condition);
				commonGrid.setQueryPanelParamList(conditionList);
			}
			//查询数据
			pageInfo = (PageInfo) this.getData(commonGrid);
			return pageInfo;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
	/**
     * 覆盖父类CommonGridService，修改getQueryParamter，修改SNAME_获取方式：code-name
     */
	@Override
    public Object getData(Table table) throws ServiceException {
        super.setTableColumns(table);
        CommonGrid commonGrid = (CommonGrid) table;
        Map<String, Object> paramMap = this.getQueryParamter(commonGrid);
        
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
     * 获取查询参数
     * @param grid 表格对象
     * @return 参数对象
     * @throws ServiceException 业务异常
     */
    public Map<String, Object> getQueryParamter(Grid grid) throws ServiceException {
        // super.setTableDefine(grid);// 是否要加权限过滤
        Map<String, Object> paramMap = getParameterMap(grid);

        paramMap.put("tableName", grid.getTableDBName());
        paramMap.put("leafColumnList", TableUtil.getLeafColumnList(grid));
        paramMap.put("selectElement", getSqlSelect(grid)); // 查找的元素
        paramMap.put("rowWriteSecu", grid.getRowWriteSecu());
        paramMap.put("rowVisibleSecu", grid.getRowVisibleSecu());
      
        String sqlWhere = getWhereParamter(grid);
        paramMap.put("sqlWhere", sqlWhere);
        return paramMap;
    }
    
    /**
     * 取得查询字段sql
     * @param table
     * @return
     */
    public String getSqlSelect(Table table) {
        List<Column> leafColumns = TableUtil.getLeafColumnList(table);
        if (leafColumns == null || leafColumns.size() == 0)
            return null;

        StringBuffer sqlSelectBuffer = new StringBuffer();
        for (Column leafColumn : leafColumns) {
            if (sqlSelectBuffer.length() == 0)
                sqlSelectBuffer.append(getSqlName(leafColumn));
            else
                sqlSelectBuffer.append("," + getSqlName(leafColumn));
        }
        return sqlSelectBuffer.toString().toUpperCase();
    }
    
    /**
	 *  根据列属性转sql，修改SNAME_获取方式：code-name
	 * @param column
	 * @return
	 */
    public String getSqlName(Column column) {
        String fieldSqlName = "";
        if (column.getShowType() != null && column.getShowType().equalsIgnoreCase("fileuploadfield")) { // 附件表
            fieldSqlName = column.getColumnDBName() + ",(SELECT ATTACHNAME FROM PUB_T_ATTACH WHERE ATTACHID = "
                    + column.getColumnDBName() + " ) AS SNAME_" + column.getColumnDBName();
            column.setRefTableDBName("CODE_T_ATTACH");
        }
        else if (column.getRefTableDBName() != null) { // 引用表
            if (null != column.getSysExtPro() && column.getSysExtPro().length() > 1
                    && column.getSysExtPro().charAt(1) == '1') {
                fieldSqlName = column.getColumnDBName() + "," + column.getColumnDBName() + " AS SNAME_"
                        + column.getColumnDBName();
            } else {
                String newDbName = " (SELECT GUID,CODE,NAME FROM "+ column.getRefTableDBName() +" ) ";
                fieldSqlName = column.getColumnDBName() + ",(SELECT "+ this.getCodeNameStr() + " FROM " + newDbName
                        + " WHERE GUID = " + column.getColumnDBName() + " AND ROWNUM < 2 ) AS SNAME_"
                        + column.getColumnDBName();
            }
        }else if(column.getShowType()!=null && column.getRefTableDBName()!=null && column.getShowType().equalsIgnoreCase("multipleCombo")){//add by xl
            fieldSqlName = column.getColumnDBName() + ",(SELECT LISTAGG(NAME,',') WITHIN GROUP(ORDER BY GUID) FROM " + column.getRefTableDBName() + " WHERE " + column.getColumnDBName() + " like '%'||GUID||'%') AS SNAME_" + column.getColumnDBName();
        } else {
            fieldSqlName = column.getColumnDBName();
        }
        return fieldSqlName;
    }
	
    /**
     * 获取代码表时默认使用code-name，其他继承时业务系统显示代码表不同信息可覆盖此方法
     * @return
     */
	public String getCodeNameStr(){
		return " trim(code)||'-'||trim(name) ";
	}
	
	/**
     * 取得叶子节点的列
     * @return
     */
    public List<Column> getAllColumnList(Table table) {
        List<Column> columnList = table.getColumnList();
        List<Column> allColumnList = new ArrayList<Column>();
        getAllColumnList(allColumnList, columnList);
        return allColumnList;
    }

    /**
     * 取得包括标题列的所有列信息
     * @param allColumnList
     * @param columnList
     */
    public void getAllColumnList(List<Column> allColumnList, List<Column> columnList) {
        if (columnList == null) {
            return;
        }
        for (Column col : columnList) {
        	allColumnList.add(col);
            if (col.isLeaf() == false){
                List<Column> childColList = col.getChildrenColumnList();
                getAllColumnList(allColumnList, childColList);
            }
        }
    }
    
    /**
     * 获取表的列信息
     * @param tableID
     * @return
     * @throws ServiceException
     */
    public List<Column> getGridColumnList(String tableID, String grid, String agencyIsLeaf) throws ServiceException{
    	CommonGrid commonGrid = new CommonGrid();
		commonGrid.setTableID(tableID);
		String userID = SecureUtil.getCurrentUser().getGuid();
		commonGridService.initStructure(commonGrid, userID);
		List<Column> columnList = this.getAllColumnList(commonGrid);
		//如果选择中间级，则AGENCYID强制改为显示列
		if("0".equals(agencyIsLeaf)){
			for(Column column : columnList){
				if(Constants.COL_DBNAME_AGENCYID.equals(column.getColumnDBName())){
					column.setVisible(true);
				}
			}
		}
		return columnList;
    }
    
    /**
     * 查询表列最大层级
     * @param tableID
     * @return
     */
    public int getHeadRowNum(String tableID){
    	String sql = " select max(levelno) headrownum from dict_t_factor t where t.tableid ='" + tableID + "' and t.isvisible = '1'";
		List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(sql);
		HashMap<String, Object> tmpMap = tmpList.get(0);
		BigDecimal tmpBig = (BigDecimal) tmpMap.get("HEADROWNUM");
		int headrownum = tmpBig.intValue();
		return headrownum;
    }
    /**
     * 单元格样式
     * @param workbook
     * @param fontName
     * @param boldweight
     * @param fontHeightInPoints
     * @param alignment
     * @param warpText
     * @return
     */
    public CellStyle getStyle(SXSSFWorkbook workbook, String fontName, short boldweight, short fontHeightInPoints, short alignment, boolean warpText){
    	CellStyle style = workbook.createCellStyle();
    	//字体
    	Font font = workbook.createFont();
    	//字体样式
		font.setFontName(fontName);
		//粗体显示
		font.setBoldweight(boldweight);
		//字体大小
		font.setFontHeightInPoints(fontHeightInPoints);
		style.setFont(font);
		//文字水平对齐方式
		style.setAlignment(alignment);
		//文字垂直对齐方式
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		//是否自动换行
		style.setWrapText(warpText);
		//边框
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		return style;
    }
    /**
     * 填充表头
     */
	public void fillHead(DictTModelPO dictTModel, List<Column> columnList, SXSSFWorkbook workbook, int headRowNum) throws ServiceException {
		try{
			String tableID = dictTModel.getTableid();
			String sheetName = dictTModel.getName();
			
			//表名的格式
			CellStyle titleStyle = this.getStyle(workbook, "黑体", Font.BOLDWEIGHT_BOLD, (short) 20, XSSFCellStyle.ALIGN_CENTER, false);
			//标题的格式
			CellStyle headStyle = this.getStyle(workbook, "宋体", Font.BOLDWEIGHT_BOLD, (short) 12, XSSFCellStyle.ALIGN_CENTER, true);
			
			//显示列
			ArrayList<Column> displayColsList = new ArrayList<Column>();
			//隐藏列
			ArrayList<Column> hideColsList = new ArrayList<Column>();
			//SHEET
			Sheet sheet = workbook.getSheet(sheetName);
			Row xssfrow = sheet.createRow((short) 0);
			Cell cell = xssfrow.createCell((short) 0);
			cell.setCellValue(sheetName);
			cell.setCellStyle(titleStyle);
	
			xssfrow = sheet.createRow(headRowNum);
	
			//第一列:序号
			cell = xssfrow.createCell(0);
			cell.setCellValue("序号");
			cell.setCellStyle(headStyle);
			//标题列
			ArrayList<Column> titleList = new ArrayList<Column>();
			
			int colnum = 1;
			for(Column column : columnList){
				if (column.isVisible() == true) {
					if (column.isLeaf() == false) {
						titleList.add(column);
					}else{
						cell = xssfrow.createCell(colnum);
						cell.setCellValue(column.getAlias());
						if (column.isNullable() == false || column.isLogicKey() == true) {
							cell.setCellValue(column.getAlias() + "*");
						}
						cell.setCellStyle(headStyle);
		
						colnum++;
						displayColsList.add(column);
					}
				} else {
					hideColsList.add(column);
				}
			}
			String sql = "";
			for (Column column : titleList) {
				String columnid = column.getColumnID();
				String cellStr = column.getAlias();
				int levelHead = column.getLevelNo();
	
				sql = "select t2.xlsxcolumnid "
						+ "from dict_t_factor t, dict_t_factorxlsx t2 "
						+ "where t.tableid = t2.tableid(+) "
						+ " and t.columnid = t2.columnid(+) "
						+ " and t.tableid = '" + tableID + "' "
						+ " and t2.xlsxcolumnid is not null "
						+ " start with (t.superid = '" + columnid + "') "
						+ " connect by prior t.columnid = t.superid "
						+ " order siblings by t.orderid, t.columnid ";
				
				List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(sql);
				if (tmpList != null && tmpList.size() > 0) {
					HashMap<String, Object> tmpMap1 = tmpList.get(0);
					String startCell = (String) tmpMap1.get("XLSXCOLUMNID");
					int istartcol = CellReference.convertColStringToIndex(startCell);
					tmpMap1 = tmpList.get(tmpList.size() - 1);
					String endCell = (String) tmpMap1.get("XLSXCOLUMNID");
					int endcol = CellReference.convertColStringToIndex(endCell);
	
					Row row = sheet.getRow(levelHead);
					if (row == null)
						row = sheet.createRow(levelHead);
					Cell cell1 = row.getCell(istartcol);
					if (cell1 == null)
						cell1 = row.createCell(istartcol);
					cell1.setCellValue(cellStr);
					cell1.setCellStyle(headStyle);
					CellRangeAddress region1 = new CellRangeAddress(levelHead, levelHead, istartcol, endcol);
					prepareCellmergeRegion(sheet, region1);
					sheet.addMergedRegion(region1);
					RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
					RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
					RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
					RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
				}
			}
			if (titleList.size() > 0) {
	
				for (int i = 0; i < displayColsList.size() + 1; i++) {
					int headstartRow = 1;
					while (headstartRow < headRowNum) {
						Row row = sheet.getRow(headstartRow);
						Cell cell2 = row.getCell(i);
						if (cell2 == null) {
							cell2 = row.createCell(i);
							Cell cell3 = sheet.getRow(headRowNum).getCell(i);
							cell2.setCellValue(cell3.getStringCellValue());
							cell2.setCellStyle(headStyle);
							sheet.getRow(headRowNum).removeCell(cell3);
							CellRangeAddress region1 = new CellRangeAddress(headstartRow, headRowNum, i, i);
							prepareCellmergeRegion(sheet, region1);
							sheet.addMergedRegion(region1);
							RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN,region1, sheet, workbook);
							RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_THIN,region1, sheet, workbook);
							RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN,region1, sheet, workbook);
							RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN,region1, sheet, workbook);
	
							headstartRow = headRowNum;
						}
						headstartRow++;
					}
				}
			}
			int dataMaxColnum = displayColsList.size();
			CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, dataMaxColnum); // 准备单元格合并
			prepareCellmergeRegion(sheet, region1);
			sheet.addMergedRegion(region1);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
	public String generateSpace(int cc) {
		String retStr = "";
		for (int i = 0; i < cc; i++)
			retStr = retStr + " ";
		return retStr;
	}
	
	public void getLeafColumnList(List<DictTFactorPO> leafFactorList, List<DictTFactorPO> factorList) {
        if (factorList == null) {
            return;
        }
        for (DictTFactorPO col : factorList) {
            if ("1".equals(col.getIsleaf())) {
            	leafFactorList.add(col);
            } else {
                List<DictTFactorPO> childColList = col.getDictTFactorList();
                getLeafColumnList(leafFactorList, childColList);
            }
        }
    }
	
	public void prepareCellmergeRegion(Sheet sheet, CellRangeAddress region1) {
		for (int ir = region1.getFirstRow(); ir <= region1.getLastRow(); ir++) {
			Row rw = sheet.getRow(ir);
			if (rw == null) {
				throw new IllegalStateException(sheet.getSheetName()+ ":合并单元格的" + ir + "行为空！请进入菜单-录入表定义，检查列与上级标题列的可见选项，保持一致。");
			}
			for (int ic = region1.getFirstColumn(); ic <= region1.getLastColumn(); ic++) {
				Cell cell = rw.getCell(ic);
				if (cell == null) {
					cell = rw.createCell(ic);
					XSSFCellStyle regionCellStyle = (XSSFCellStyle) cell.getSheet().getWorkbook().createCellStyle();
					regionCellStyle.cloneStyleFrom(cell.getCellStyle());
					regionCellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
					regionCellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
					regionCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
					regionCellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
					cell.setCellStyle(regionCellStyle);
				}

			}
		}
	}
	
	/**
	 * 单元格赋值
	 * @param cell
	 * @param cellvalue
	 * @param isHead 是否表头
	 */
	public void setxCellValue(Cell cell, Object cellvalue, boolean isHead) {
		
		if (cellvalue == null){
			cell.setCellType(Cell.CELL_TYPE_BLANK);
		}else{
			String fieldtype = cellvalue.getClass().getName();
			if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")) {
				BigDecimal cv = new BigDecimal(0);
				if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")){
					cv = (BigDecimal) cellvalue;
				}
				cell.setCellValue(cv.doubleValue());
			} else {
				String cv = (String) cellvalue;
				cell.setCellValue(cv);
			}
		}
		//数据区域的单元格，取Column样式再设置一次。
		if(!isHead){
			XSSFCellStyle cellStyle = (XSSFCellStyle) cell.getSheet().getColumnStyle(cell.getColumnIndex());
			cell.setCellStyle(cellStyle);
		}
		
	}
	
	/**
	 * 获取合计列的总数
	 * @param tableID
	 * @return
	 */
	public int getSumFieldCount(String tableID) {
		String sql = "select count(1) NUM from dict_t_factor t where issum=1 and tableid='" + tableID + "'";
		List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(sql);
		HashMap<String, Object> tmpMap = tmpList.get(0);
		int num = ((BigDecimal) tmpMap.get("NUM")).intValue();
		return num;
	}
	
	/**
	 * 为Excel填充数据
	 */
	@SuppressWarnings("unchecked")
	public void fillData(Sheet sheet, int startNum, int headRowNum, int lastCellNum, PageInfo pageInfo, HashMap<String, String> xlsxMap) throws ServiceException {
		try{
			//得到pageInfo中的数据
			List<Object> dataList = pageInfo.getDataList();
			//得到pageInfo中的列信息
			List<String> columns = pageInfo.getColumns();
			int totalRows = dataList.size();
			int xRow;
			
			int i = 0;
			Row row = null;
			List<Object> rowData = null;
			for (xRow = startNum; xRow < startNum + totalRows ; xRow++) {
				rowData = (List<Object>) dataList.get(i);
				i++;
				row = sheet.getRow(xRow);
				if (row == null) {
					row = sheet.createRow(xRow);
				}
				SXSSFCell cell = null;
				for (int xCol = 0; xCol < lastCellNum; xCol++) {
					
					cell = (SXSSFCell) row.getCell(xCol);
					if (cell == null) {
						cell = (SXSSFCell) row.createCell(xCol);
					}
					
					//序号
					if (xCol == 0) {
						int sn = xRow - headRowNum;
						setxCellValue(cell, new BigDecimal(sn), false);
					//数据
					} else {
						//取得Excel列名
						String cellColumnName = CellReference.convertNumToColString(cell.getColumnIndex());
						String dbColumnName = xlsxMap.get(cellColumnName);
						String datatype = xlsxMap.get(Constants.EXCEL_KEY_DATATYPE + cellColumnName);
						
						//若列中有SNAME_对应的列，则取SNAME_列对应的值
						int index1 = columns.indexOf(dbColumnName);
						Object obj = rowData.get(index1);
						int index2 = columns.indexOf(Constants.SNAME_START + dbColumnName);
						if(index2 != -1){
							obj = rowData.get(index2);
						}
						if ("1".equals(datatype) || "2".equals(datatype)) {
							String valueVV = (String) obj;
							if (valueVV != null && isRealNumber(valueVV)){
								obj = BigDecimal.valueOf(Double.valueOf(valueVV));
							}
						}
						setxCellValue(cell, obj, false);
					}
					cell = null;
				}
				rowData = null;
				row = null;
			}
			while (xRow <= sheet.getLastRowNum()) {
				Row r = sheet.getRow(xRow);
				sheet.removeRow(r);
				xRow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		}
	}
	
	/**
	 * //获取表列与excel列对应信息，并设置列默认样式。
	 * @param tableID
	 * @param sheet
	 * @return
	 */
	public HashMap<String, String> getDictTFactorxlsxInfo(String tableID, Sheet sheet) {
		HashMap<String, String> colInfoMap = new HashMap<String, String>();
		String sql = " select t.dbcolumnname,t2.xlsxcolumnid,t.name,t.columnid,t.scale,t.datatype " +
				"from dict_t_factor t,dict_t_factorxlsx t2 " +
				"where t.tableID=t2.tableID and t.columnid=t2.columnid and t.tableID ='" + tableID
				+ "' and (t.isvisible=1 or t.dbcolumnname='AGENCYID' ) order by length(t2.xlsxcolumnid),t2.xlsxcolumnid";

		List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(sql);
		if (tmpList.size() == 0)
			return null;
		Iterator<HashMap<String, Object>> it1 = tmpList.iterator();
		while (it1.hasNext()) {
			HashMap<String, Object> map = it1.next();
			String DBCOLUMNNAME = (String) map.get("DBCOLUMNNAME");
			String XLSXCOLUMNID = (String) map.get("XLSXCOLUMNID");
			if (DBCOLUMNNAME == null || XLSXCOLUMNID == null){
				continue;
			}
			colInfoMap.put(XLSXCOLUMNID.toUpperCase(), DBCOLUMNNAME);
			int scale = ((BigDecimal) (map.get("SCALE")==null ? new BigDecimal(0):map.get("SCALE"))).intValue();
			int datatype = ((BigDecimal) (map.get("DATATYPE")==null ? new BigDecimal(0):map.get("DATATYPE"))).intValue();
			colInfoMap.put(Constants.EXCEL_KEY_DATATYPE + XLSXCOLUMNID.toUpperCase(), String.valueOf(datatype));
			colInfoMap.put(Constants.EXCEL_KEY_DBCOLUMNNAME + DBCOLUMNNAME, XLSXCOLUMNID.toUpperCase());
			//设置列默认样式
			if(sheet != null){
				XSSFCellStyle cellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
				cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
				cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
				int index = CellReference.convertColStringToIndex(XLSXCOLUMNID.toUpperCase());
				if(datatype == 1 || datatype == 2){
					//数字靠右
					cellStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
					//小数位数
					if(scale > 0){
						String scaleStr = fillString("0", scale);
						DataFormat df = sheet.getWorkbook().createDataFormat(); 
						cellStyle.setDataFormat(df.getFormat("#,#0."+scaleStr));
						//设置列宽
						sheet.setColumnWidth(index, 18*256);
					}
					
				}else{
					//字符靠左
					cellStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
				}
				//设置列默认样式
				sheet.setDefaultColumnStyle(index, cellStyle);
			}
			
		}
		//序号列：默认样式
		//设置列默认样式
		if(sheet != null){
			XSSFCellStyle cellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
			cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
			cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			sheet.setDefaultColumnStyle(0, cellStyle);
		}
		return colInfoMap;
	}
	
	public String fillString(String c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++){
			ret = ret + c;
		}
		return ret;
	}
	
	private static boolean isMatch(String regex, String orginal){  
        if (orginal == null || orginal.trim().equals("")) {  
            return false;  
        }  
        Pattern pattern = Pattern.compile(regex);  
        Matcher isNum = pattern.matcher(orginal);  
        return isNum.matches();  
    }  
  
	public static boolean isPositiveInteger(String orginal) {  
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);  
    }  
  
    public static boolean isNegativeInteger(String orginal) {  
        return isMatch("^-[1-9]\\d*", orginal);  
    }  
  
    public static boolean isWholeNumber(String orginal) {  
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);  
    }  
      
    public static boolean isPositiveDecimal(String orginal){  
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isNegativeDecimal(String orginal){  
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);  
    }  
      
    public static boolean isDecimal(String orginal){  
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);  
    }  
    
    public static boolean isRealNumber(String orginal){  
        return isWholeNumber(orginal) || isDecimal(orginal);  
    }  
    public String getGUID() {
		UUID uuid = UUID.randomUUID();
		String guid = uuid.toString();
		guid = guid.replaceAll("-", "").toUpperCase();
		return guid;
	}
    
    /**
	 * 处理dict_t_factorxlsx表信息
	 */
	public void saveDictTFactorxlsx(String tableID, String grid, String agencyIsLeaf) throws ServiceException {
		try {
			List<Column> columnList = this.getGridColumnList(tableID, grid, agencyIsLeaf);
			int colnum = 1;
			StringBuffer batsqlBUF = new StringBuffer();
			for(Column column : columnList){
				String dbcolumnname = column.getColumnDBName();
				String columnid = column.getColumnID();
				if (column.isVisible() == true) {
					if (column.isLeaf() == true) {
						String coluri = CellReference.convertNumToColString(colnum);
						if (dbcolumnname != null && !dbcolumnname.equalsIgnoreCase("")) {
							batsqlBUF.append("select '" + columnid + "' columnid,'" + tableID + "' tableid, '" + coluri.toUpperCase() + "' xlsxcolumnid from dual ");
							batsqlBUF.append("union all ");
						}
						colnum++;
					}
				} else {
					if (dbcolumnname != null && !dbcolumnname.equalsIgnoreCase("")) {
						batsqlBUF.append("select '" + columnid + "' columnid,'" + tableID + "' tableid, null xlsxcolumnid from dual ");
						batsqlBUF.append("union all ");
					}
				}
			}
			
			int lastPos = batsqlBUF.lastIndexOf("union all");
			batsqlBUF.replace(lastPos, lastPos + 9, "");
			
			String batsqlBufStr = batsqlBUF.toString();
			//先改后增
			String sql = "update dict_t_factorxlsx a set (columnid, tableid, xlsxcolumnid) = (select b.columnid, b.tableid, b.xlsxcolumnid from (" + batsqlBufStr + ") b where a.columnid = b.columnid and a.tableid = b.tableid) where exists (select 1 from ("+batsqlBufStr+") t where t.columnid = a.columnid and t.tableid = a.tableid)";
			this.excelMapper.updateSql(sql);
			sql = "insert into dict_t_factorxlsx a (columnid, tableid, xlsxcolumnid) (select b.columnid, b.tableid, b.xlsxcolumnid from (" + batsqlBufStr + ") b where not exists (select 1 from dict_t_factorxlsx t where t.columnid = b.columnid and t.tableid = b.tableid))";
			this.excelMapper.updateSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "处理dict_t_factorxlsx表信息出错", false);
		}
	}
	
	/**
	 * 导入
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public HashMap<String, Object> importData(HttpServletRequest request, InputStream inputStream) throws ServiceException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			
			String tableID = request.getParameter("tableID");
	
			//Excel中读取数据的开始行数
			int startRow = getStartRowNum(tableID);
			
			//读取excel到list中
			ReadExcelUtils readExcelUtils = new ReadExcelUtils();
			List<List<String>> list = readExcelUtils.processSAXReadSheet0(inputStream, startRow);
			
			//excel中数据总行数
			int total = list.size();
			
			if(total == 0){
				resultMap.put("success", true);
				resultMap.put("successInfo", "导入成功!");
				return resultMap; 
			}
			
			String agencyID = request.getParameter("agencyID");
			String agencyIsLeaf = request.getParameter("agencyIsLeaf");
			
			DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
			
			//所有列
			List<Column> columnList = getGridColumnList(tableID, null, agencyIsLeaf);
			//可见的末级列
			List<Column> displayLeafColumns = new ArrayList<Column>();
			//隐藏的逻辑主键列
			List<Column> hiddenLogicColumns = new ArrayList<Column>();
			//可见的所有列（包括标题）
			//List<Column> displayAllColumns = new ArrayList<Column>();
			HashMap<String, Column> columnMap = new HashMap<String, Column>();
			for(Column column : columnList){
				if(column.isVisible() == true) {
					//若显示列中存在AGENCYID，则必须为引用列
					//logger.debug(column.getColumnDBName());
					String columnDBName = (StringUtils.isNotEmpty(column.getColumnDBName()) ? column.getColumnDBName() : " ").toUpperCase();
					String refTableID = column.getRefTableID();
					if(Constants.COL_DBNAME_AGENCYID.equals(columnDBName)){
						//若显示列中存在AGENCYID，则预算单位必填。
						column.setNullable(false);
						//若要导入预算单位列AGENCYID则必须为引用列
						if(StringUtils.isEmpty(refTableID)){
							throw new ServiceException(ExceptionCode.ERR_00000, "若要导入预算单位列AGENCYID则必须为引用列，请在录入表定义中修改为引用列之后再导出导入!", false);
						}
					}
					//若列为逻辑主键，则必填
					if(column.isLogicKey()){
						column.setNullable(false);
					}
					//displayAllColumns.add(column);
				}
				if(column.isLeaf()) {
					if(column.isVisible() == true) {
						displayLeafColumns.add(column);
						columnMap.put(column.getColumnDBName(), column);
					}else{
						if(column.isLogicKey()){
							hiddenLogicColumns.add(column);
						}
					}
				}
			}
			//初始化代码表
			HashMap<String, LinkedHashMap<String, String>> codeNameMap = getCodeNameMap(tableID, agencyID, agencyIsLeaf);
			//获取表列与excel列对应信息
			HashMap<String, String>	xlsxMap = getDictTFactorxlsxInfo(tableID, null);
			
			//若数据校验有误，则提示信息放入exportInfo导出txt
			String exportInfo = null;
			
			//分批导入的数据量
			int limit = 3000;
			float pageNum = (float)total/(float)limit;
			//导入数据量太大，新建临时tempList，分批取出list中元素放入tempList用来导入。
			List<List<String>> tempList = new ArrayList<List<String>>();
			
			//日志数据集合
			ArrayList<Map<String, Object>> logList = new ArrayList<Map<String, Object>>();
			//导入的单位集合
			Set<String> agencyList = new HashSet<String>();
			//
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			for(int i = 0; i <= pageNum; i++){
				//logger.error("导入"+(i+1));
				//分批放入tempList
				for(int a = 0; a < limit ; a++){
					if(list.size() > 0){
						//将list中的元素放入tempList
						tempList.add(list.get(0));
						//释放内存
						list.remove(0);
					}
				}
				//处理并保存数据
				map = this.handleAndSaveExcelData(tableID, agencyID, request, codeNameMap, dictTModel, tempList, displayLeafColumns, hiddenLogicColumns, xlsxMap, columnMap, logList, agencyIsLeaf);
				if(map.containsKey("exportInfo")){
					exportInfo = (String) map.get("exportInfo");
				}
				if(map.containsKey("agencyList")){
					List<String> agencys = (List<String>) map.get("agencyList");
					agencyList.addAll(agencys);
				}
				//处理结果
				if(StringUtils.isNotEmpty(exportInfo)){
					resultMap.put("success", false);
					resultMap.put("errorInfo", "txt");
					resultMap.put("exportInfo", exportInfo);
					return resultMap;
				}
				//释放内存
				tempList.clear();
			}
			//logger.error("mongo");
			//保存日志
			String taskID = request.getParameter("taskID");
			logService.saveData(logList, tableID, Constants.MONGO_FUNCTION_IMPORT, taskID, "导入:"+dictTModel.getName());
			logList = null;
			resultMap.put("success", true);
			resultMap.put("errorInfo", "");
			resultMap.put("agencyList", agencyList);
			resultMap.put("successInfo", "导入成功!");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, e.getMessage(), false);
		} 
		return resultMap;
	}
	/**
	 * 导入，处理并保存数据
	 * @throws ServiceException
	 */
	public HashMap<String, Object> handleAndSaveExcelData(String tableID, String agencyID, HttpServletRequest request, HashMap<String, LinkedHashMap<String, String>> codeNameMap, DictTModelPO dictTModel, List<List<String>> list, List<Column> displayLeafColumns, List<Column> hiddenLogicColumns, HashMap<String, String> xlsxMap, HashMap<String, Column> columnMap, ArrayList<Map<String, Object>> logList, String agencyIsLeaf) throws ServiceException {
		try {
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			//错误提示信息
			String exportInfo = "";
			//错误信息行数
			int exportInfoColumnIndex = 0;
			//新增数据集合
			ArrayList<Map<String, Object>> insertList = new ArrayList<Map<String, Object>>();
			//修改数据集合
			ArrayList<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
			//文件中逻辑主键重复校验map
			HashMap<String, Object> checkRepeatMap = new HashMap<String, Object>();
			//
			HashMap<String, HashMap<String, Object> > dataMap = new HashMap<String, HashMap<String, Object> >();
			
			String selectElement = "";
			String inSql = "";
			
			
			//遍历行
			for(List<String> aList : list){
				//数据map
				HashMap<String, Object> saveRowMap = new HashMap<String, Object>();
				//STATUS=1
				saveRowMap.put(Constants.COL_DBNAME_STATUS, "1");
				//如果是末级则AGENCYID=agencyID
				if("1".equals(agencyIsLeaf)){
					saveRowMap.put(Constants.COL_DBNAME_AGENCYID, agencyID);
				}
				
				String selectElement0 = "";
				String key = "";
				
				selectElement = "";
				
				//当前行数
				String nowRowNum = "";
				//处理一行数据
				for(String string : aList){
					//单元格地址
					String cellAddr = string.substring(0, string.indexOf(":"));
					//xlsx列名
					String xlsxColumn = cellAddr.replaceAll("[0-9]", "");
					//当前行数
					nowRowNum = cellAddr.replaceAll("[A-Z]", "");
					//值
					String value = string.substring(string.indexOf(":")+1);
					//库里的列名
					String columnDbName = "";
					//列对象
					Column column = null;
					//根据xlsx列名取得库里的列名，进而取得列对象，都不能为空
					if(xlsxMap.containsKey(xlsxColumn)){
						columnDbName = xlsxMap.get(xlsxColumn);
						if(StringUtils.isEmpty(columnDbName)){
							continue;
						}
						if(columnMap.containsKey(columnDbName)){
							column = columnMap.get(columnDbName);
							if(column == null){
								continue;
							}
						}
					}else{
						continue;
					}
					//列datakey默认放入32位guid
					if(Constants.COL_DBNAME_DATAKEY.equals(columnDbName)){
	                	saveRowMap.put(Constants.COL_DBNAME_DATAKEY, ToolUtil.getUUID());
	                	continue;
	                }
					
					//跳过绑定列和虚列，不处理，跳过DBVERSION
	                if(column.isVirtual() || column.isBindcol() || Constants.COL_DBNAME_DBVERSION.equals(columnDbName)) {
	                	continue;
	                }
	                
					//检查并处理数据
	                String[] arr = this.checkValue(value, column, cellAddr, codeNameMap);
	                //取得处理过后的数据
	                value = arr[0];
	                String msg = arr[1];
	                //处理提示信息
	                if(StringUtils.isNotEmpty(msg)){
	                	exportInfo += msg;
	                	exportInfoColumnIndex += 1;
	                	//如果错误信息超过500行，则直接返回TXT
		                if(exportInfoColumnIndex >= 500){
		                	resultMap.put("exportInfo", exportInfo);
		                	return resultMap;
		                }
	                }
	                
	                if(StringUtils.isEmpty(exportInfo)){
	                	//放入map
		        		saveRowMap.put(column.getColumnDBName(), value);
	                	
		                //拼结果集2
		                String dataType = column.getDataType();
		                String formatValue = "";
		                if("N".equals(dataType)){
		                	formatValue = value;
		                }else if("D".equals(dataType)) {
		        			String colformat = column.getMask();//列格式
		        			if(StringUtils.isEmpty(colformat)){
		        				colformat = "yyyy-MM-dd";
		        			}
		        			formatValue = "to_char('" + value + "','" + colformat + "'";
		        		}else{
		        			formatValue = "'" + value + "'";
		                }
		        		if(column.isLogicKey() == true){
							selectElement += ","+column.getColumnDBName();
							selectElement0 += ","+formatValue;
							key += ","+value;
		        		}
	                }
				}
				
				if(StringUtils.isNotEmpty(selectElement)){
					
					selectElement0 = selectElement0.substring(1);
					key = key.substring(1);
					if(checkRepeatMap.containsKey(key)){
						exportInfo += "第" + checkRepeatMap.get(key) + "行与第" + nowRowNum + "行数据逻辑主键重复" + Constants.EXCEL_LINE;
					}else{
						checkRepeatMap.put(key, nowRowNum);
					}
					if(StringUtils.isEmpty(exportInfo)){
						dataMap.put(key, saveRowMap);
						inSql += selectElement0 + " from dual union select ";
					}
				}else{
					insertList.add(saveRowMap);
				}
			}
			
			HashMap<String, HashMap<String, Object>> dataMapUpdate = new HashMap<String, HashMap<String, Object>>();
			if(StringUtils.isNotEmpty(selectElement) && StringUtils.isEmpty(exportInfo)){
				
				String dbTableName = dictTModel.getDbtablename();
				if(StringUtils.isNotEmpty(inSql)){
					inSql = "select "+inSql.substring(0, inSql.length() -14);
				}
				
				//查询逻辑主键在数据库存在的DATAKEY
				selectElement = selectElement.substring(1);
				String datakeySql = "select "+Constants.COL_DBNAME_DATAKEY+", " + selectElement.replaceAll(",", "||','||") + " as key"
						+ " from " + dbTableName
						+ " where 1=1 "//agencyid = '" + agencyID + "'"
						+ " and (" + selectElement + ") in ("+inSql+")";
				
				List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(datakeySql);
				
				//根据逻辑主键从dataMap中取出数据放入dataMapUpdate
				for(HashMap<String, Object> map : tmpList){
					String key = (String) map.get("KEY");
					String datakey = (String) map.get(Constants.COL_DBNAME_DATAKEY);
					if(dataMap.containsKey(key)){
						HashMap<String, Object> saveRowMap = new HashMap<String, Object>();
						saveRowMap = dataMap.get(key);
						if(StringUtils.isNotEmpty(datakey) && saveRowMap != null){
							saveRowMap.put(Constants.COL_DBNAME_DATAKEY, datakey);
							dataMapUpdate.put(datakey, saveRowMap);
						}
						dataMap.remove(key);//放入dataMapUpdate之后从dataMap移除
					}else{
						exportInfo += "第" + checkRepeatMap.get(key) + "行逻辑主键的值在数据库中存在多条，逻辑主键："+ selectElement + "，值：" + key + Constants.EXCEL_LINE;
					}
				}
				//把剩余的dataMap放入insertList
				if(dataMap.values() != null && dataMap.values().size() > 0 && StringUtils.isEmpty(exportInfo)){
					insertList.addAll(dataMap.values());
				}
				if(dataMapUpdate.size() > 0 && StringUtils.isEmpty(exportInfo)){
					
					String select1 = "";
					String select2 = "";
					String insql = "";
					for(HashMap<String, Object> map: dataMapUpdate.values()){
						
						select1 = " select "+ Constants.COL_DBNAME_DATAKEY ;
						
						String datakey = "";
						if(map.containsKey(Constants.COL_DBNAME_DATAKEY)){
							datakey = (String) map.get(Constants.COL_DBNAME_DATAKEY);
						}
						if(StringUtils.isEmpty(datakey)){
							continue;
						}
						select2 += " select '"+datakey+"'";
						insql += " select '"+datakey+"' from dual union ";
						for(Column column : displayLeafColumns){
							String columnDbName = column.getColumnDBName();
							if(Constants.COL_DBNAME_DATAKEY.equals(columnDbName) || column.isVirtual() || column.isBindcol() || Constants.COL_DBNAME_DBVERSION.equals(columnDbName)){
			                	continue;
			                }
							//人员表特殊处理，不比较人员状态CONFIRMFLAG
							if(Constants.TABLE_DEALTYPE_A0_01.equals(dictTModel.getDealtype()) && Constants.COL_DBNAME_CONFIRMFLAG.equals(columnDbName)){
								continue;
							}
							
							String formatValue = "";
							//拼结果集2
			                String dataType = column.getDataType();
							if(map.containsKey(columnDbName)){
								String value = (String) map.get(columnDbName);
				                if("N".equals(dataType)){
				                	formatValue = value;
				                }else if("D".equals(dataType)) {
				        			String colformat = column.getMask();//列格式
				        			if(StringUtils.isEmpty(colformat)){
				        				colformat = "yyyy-MM-dd";
				        			}
				        			formatValue = " to_char('" + value + "','" + colformat + "' ";
				        		}else{
				        			formatValue = " '" + value + "' ";
				                }
							}
							if(StringUtils.isEmpty(formatValue)){
								if("N".equals(dataType)){
									formatValue = "0";
				                }else{
				                	formatValue = "''";
				                }
							}
							select1 += "," + columnDbName;
							select2 += "," + formatValue;
						}
						
						select2 += " from dual union";
					}
					
					//比较sql，如果存在差别，则放入updateList
					insql = insql.substring(0, insql.length() - 6);
					select2 = select2.substring(0, select2.length() - 6);
					String minusSql = select1 + " from " + dbTableName +" where datakey in (" + insql + ") minus (" + select2 +")";
					tmpList = this.excelMapper.selectExcelInfo(minusSql);
					for(HashMap<String, Object> map : tmpList){
						String datakey = "";
						if(map.containsKey(Constants.COL_DBNAME_DATAKEY)){
							datakey = (String) map.get(Constants.COL_DBNAME_DATAKEY);
						}
						
						if(StringUtils.isNotEmpty(datakey) && !dataMapUpdate.containsKey(datakey)){
							dataMapUpdate.remove(datakey);
						}
					}
					//人员表特殊处理，如果显示列不含有CONFIRMFLAG，查出CONFIRMFLAG来判断导入的CONFIRMFLAG的值，如果修改的数据集中的CONFIRMFLAG为新增，则还是新增，其他改为修改待确认。
					if(Constants.TABLE_DEALTYPE_A0_01.equals(dictTModel.getDealtype()) && dataMapUpdate.size() > 0){
						String personSql = "select "+Constants.COL_DBNAME_DATAKEY+","+Constants.COL_DBNAME_CONFIRMFLAG+" from " + dbTableName +" where datakey in (" + insql + ")";
						tmpList = this.excelMapper.selectExcelInfo(personSql);
						for(HashMap<String, Object> map : tmpList){
							String datakey = "";
							if(map.containsKey(Constants.COL_DBNAME_DATAKEY)){
								datakey = (String) map.get(Constants.COL_DBNAME_DATAKEY);
							}
							if(StringUtils.isNotEmpty(datakey)){
								if(dataMapUpdate.containsKey(datakey)){
									HashMap<String, Object> data = dataMapUpdate.get(datakey);
									
									//人员表特殊处理，如果显示列含有CONFIRMFLAG，查出CONFIRMFLAG来判断导入的CONFIRMFLAG的值，如果修改的数据集中的CONFIRMFLAG为新增，则还是新增，其他改为修改待确认。
									String confirmflag = "";
									if(map.containsKey(Constants.COL_DBNAME_CONFIRMFLAG)){
										confirmflag = (String) map.get(Constants.COL_DBNAME_CONFIRMFLAG);
									}
									if("0".equals(confirmflag)){
										data.put(Constants.COL_DBNAME_CONFIRMFLAG, "0");
									}else{
										data.put(Constants.COL_DBNAME_CONFIRMFLAG, "2");
									}
								}
							}
						}
					}
					
					updateList.addAll(dataMapUpdate.values());
				}
			}
			
			//有提示信息则返回信息并导出txt
			if(StringUtils.isNotEmpty(exportInfo)){
				resultMap.put("exportInfo", exportInfo);
            	return resultMap;
			}else{
				//logger.error("add:" + insertList.size()+ "|update:"+updateList.size());
				List<String> agencyList = this.saveData(dictTModel, agencyID, request, insertList, updateList);
				
				logList.addAll(updateList);
				logList.addAll(insertList);
				//释放内存
				insertList = null;
				//释放内存
				updateList = null;
				//释放内存
				dataMap = null;
				//释放内存
				dataMapUpdate = null;
				resultMap.put("agencyList", agencyList);
            	return resultMap;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "导入失败:"+e.getMessage(), false);
		}
	}
	
	/**
	 * //检查并处理数据，放入saveRowMap
	 * @return
	 * @throws ServiceException
	 */
	public String[] checkValue(String v, Column column, String cellAddr, HashMap<String, LinkedHashMap<String, String>> codeNameMap) throws ServiceException {
		//返回值，放入数组返回
		String[] result = new String[2];
		//提示信息
		String msg = "";
		try{
			//列类型
			String dataType = column.getDataType();
			//数字列
			if ("N".equals(dataType)) {
				try{
					int scale = column.getScale();
					if(scale > 0){
						BigDecimal bd = new BigDecimal(v); 
						String scaleStr = fillString("0", scale);
						DecimalFormat df = new DecimalFormat("0."+scaleStr);
						v = df.format(bd.doubleValue());
					}
				}catch (Exception e){
					e.printStackTrace();
					msg += cellAddr + ":"+column.getColumnName() + " 应填数字" + Constants.EXCEL_LINE;
				}
				if(StringUtils.isEmpty(msg) && !isRealNumber(v)){
					msg += cellAddr + ":"+column.getColumnName() + " 应填数字" + Constants.EXCEL_LINE;
				}
			} else if("D".equals(dataType)) {
				try{
					String colformat = column.getMask();//列格式
					if(StringUtils.isEmpty(colformat)){
						colformat = "yyyy-MM-dd";
					}
			        SimpleDateFormat dff = new SimpleDateFormat(colformat); 
					v = dff.format(v);
				}catch (Exception e){
					e.printStackTrace();
					msg += cellAddr + ":"+column.getColumnName() + " 日期格式错误" + Constants.EXCEL_LINE;
				}
			} else {
				//refTableID判断是否引用列
				String refTableID = column.getRefTableID();
				//引用列
				if(StringUtils.isNotEmpty(refTableID)){
					if(StringUtils.isNotEmpty(v)){
						v = v.trim();
						LinkedHashMap<String, String> eleMap = codeNameMap.get(refTableID);
						if(eleMap != null && eleMap.containsKey(v)){
							v = (String) eleMap.get(v);
						}else{
							v = "";
							msg += cellAddr + ":"+column.getColumnName();
							//扩展列取填入的值
							if (column.getSysExtPro() != null && column.getSysExtPro().length() > 1 && column.getSysExtPro().charAt(1) == '1') {
								msg += " 扩展列值无效" + Constants.EXCEL_LINE;
							}else{//非扩展列取GUID
								msg += " 引用值无效" + Constants.EXCEL_LINE;
							}
						}
					}
					
				}
			}
			//去除两边空格
			if(StringUtils.isNotEmpty(v)){
				v = v.trim();
			}
			//必填
			if(column.isNullable() == false && StringUtils.isEmpty(v)){
				msg += cellAddr + ":"+column.getColumnName()+" 必填" + Constants.EXCEL_LINE;
			}
			
		}catch (Exception e){
			e.printStackTrace();
			msg += cellAddr + ":单元格出错" + Constants.EXCEL_LINE;
		}
		result[0] = v;
		result[1] = msg;
		return result;
	}
	/**
	 * 检查逻辑主键是否重复，逻辑主键在数据库存在的数据update，不存在的insert
	 */
	public String[] checkRow(DictTModelPO dictTModel, String agencyID, HashMap<String, Object> checkRepeatMap, String nowRowNum, String getDatakeySql, List<Column> hiddenLogicColumns) throws ServiceException{
		//提示信息
		String msg = "";
		//检查逻辑主键重复的数据
		if(StringUtils.isNotEmpty(getDatakeySql)){
			if(checkRepeatMap.containsKey(getDatakeySql)){
				msg += "第" + checkRepeatMap.get(getDatakeySql) + "行与第" + nowRowNum + "行数据逻辑主键重复，请修改";
			}else{
				checkRepeatMap.put(getDatakeySql, nowRowNum);
			}
		}
		
		//根据逻辑主键获取datakey
		String datekey = "";
		if(StringUtils.isNotEmpty(getDatakeySql)){
			//getDatakeySql = "select datakey from " + dictTModel.getDbtablename() + " where agencyid = '" + agencyID + "' "  + getDatakeySql;
			List<HashMap<String, Object>> tmpList = this.excelMapper.selectExcelInfo(getDatakeySql);
			if(tmpList.size() > 1){
				msg += "第" + nowRowNum + "行数据的逻辑主键的值在数据库存在多数据，请修改";
			}
			if(tmpList.size() == 1){
				HashMap<String, Object> tmpMap = tmpList.get(0);
				datekey = (String) tmpMap.get(Constants.COL_DBNAME_DATAKEY);
			}
		}
		//返回值，放入数组返回
		String[] result = new String[2];
		result[0] = datekey;
		result[1] = msg;
		return result;
	}
	
	/**
	 * 导入，保存数据
	 * @param dictTModel
	 * @param agencyID
	 * @param insertList
	 * @param updateList
	 */
	public List<String> saveData(DictTModelPO dictTModel, String agencyID, HttpServletRequest request, ArrayList<Map<String, Object>> insertList, ArrayList<Map<String, Object>> updateList) throws ServiceException{
		
		String tableID = dictTModel.getTableid();
		/*
		 * 保存数据
		 */
		CommonGrid commonGrid = new CommonGrid();
		commonGrid.setTableID(tableID);
		commonGrid.setInsertValues(insertList);
		commonGrid.setUpdateValues(updateList);
		commonGrid.setTableDBName(dictTModel.getDbtablename());
		commonGrid.setTableName(dictTModel.getName());
		commonGridService.saveData(commonGrid);
		
		return null;
	}
	
	/**
	 * 获取代码表信息，只获取表下显示列，非扩展列的代码表信息。
	 * @param tableID
	 * @param agencyID
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, LinkedHashMap<String, String>> getCodeNameMap(String tableID, String agencyID, String agencyIsLeaf) throws Exception {
		HashMap<String, LinkedHashMap<String, String>> codeMap = new HashMap<String, LinkedHashMap<String, String>>();
		String str = "";
		if("1".equals(agencyIsLeaf)){
			str = " and u.isvisible = '1' ";
		}else{
			str = " and (u.isvisible = '1' or u.dbcolumnname = 'AGENCYID') ";
		}
		String sql = "select distinct u.csid, t.dbtablename, t.dynamicwhere, substr(u.extprop,2,1) as extprop from dict_t_modelcode t,dict_t_factor u where t.tableid = u.csid and u.tableid = '" + tableID + "' " + str; //and substr(u.extprop,2,1) <> '1' 
		List<HashMap<String, Object>> modelCodeList = this.excelMapper.selectExcelInfo(sql);
		
		for (HashMap<String, Object> map1 : modelCodeList) {
			String csid = map1.get("CSID") == null ? "" : (String) map1.get("CSID");// 代码表where条件
			String codeTableName = map1.get("DBTABLENAME") == null ? "" : (String) map1.get("DBTABLENAME");//代码表 表名
			String dynamicwhere = map1.get("DYNAMICWHERE") == null ? "" : (String) map1.get("DYNAMICWHERE");// 代码表where条件
			String extprop = map1.get("EXTPROP") == null ? "" : (String) map1.get("EXTPROP");// 扩展列
			
			dynamicwhere = StringUtils.isNotEmpty(dynamicwhere) ? " where " + dynamicwhere : "";
			dynamicwhere = dynamicwhere.replaceAll(Matcher.quoteReplacement("$AGENCYID$"), agencyID);
			//如果是扩展列，导入时，EXCEL中填入的是GUID，校验GUID即可。
			String codenameStr = getCodeNameStr();
			if("1".equals(extprop)){
				codenameStr = " guid ";
			}
			sql = "select " + codenameStr + " as codename, guid from " + codeTableName + " " + Constants.TABLE_NAME_ALIAS + " " + dynamicwhere + "";
			List<HashMap<String, Object>> codeNameList = this.excelMapper.selectExcelInfo(sql);
			/*if(codeNameList.size() == 0){
				throw new Exception("条件为{"+dynamicwhere+"}的引用代码表" + codeTableName + "数据为空，不能导入导出，请重新导出模板或检查代码表数据。");
			}*/
			/*HashMap check1Map = new HashMap();
			for (int i = 0; i < codeNameList.size(); i++) {
				HashMap rowMap = (HashMap) codeNameList.get(i);
				String codeName = (String) rowMap.get("CODENAME");
				if (!check1Map.containsKey(codeName)) {
					check1Map.put(codeName, codeName);
				} else {
					throw new Exception("引用代码表" + codeTableName + "的CODE-NAME出现重复,在excel离线编辑时通过CODE-NAME：" + codeName + "反查GUID时无法确定唯一值，不能导入导出，请重新导出模板或检查代码表数据。");
				}
			}*/
			LinkedHashMap<String, String> itemidMap = new LinkedHashMap<String, String>();
			for (HashMap<String, Object> rowMap : codeNameList) {
				String itemid = (String) rowMap.get("GUID");
				String codeName = (String) rowMap.get("CODENAME");
				itemidMap.put(codeName, itemid);
			}
			codeMap.put(csid, itemidMap);
		}
		return codeMap;
		
	}
	
	/**
	 * 获取开始读取数据的行数，一般录入表
	 * @return
	 */
	public int getStartRowNum(String tableID) throws ServiceException{
		//合计行数
		int sumHeadCount = getSumFieldCount(tableID);
		//表头所占行数
		int headrownum = getHeadRowNum(tableID);
		//数据开始行
		int startRowNum = headrownum + 1;
		//若有合计行则跳过合计行读数
		if(sumHeadCount > 0){
			startRowNum += 1;
		}
		return startRowNum;
	}
	
	/**
	 * 刷新列间公式
	 * @param tableID
	 * @param agencyID
	 * @param appID
	 * @throws ServiceException
	 */
	
	public void refreshFormula_0(String tableID, String agencyID, String appID) throws ServiceException {
		try {
			String where = "";
			if(StringUtils.isNotEmpty(agencyID)){
				String agencyTableName = SystemConfUtils.getAppCode(appID, "AgencyID");
				where += " agencyid in (select t.guid from "+agencyTableName+" t start with guid = '"+agencyID+"' connect by prior guid = superguid)";
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tableID", tableID);
			params.put("where", where);
			this.excelMapper.callProcedure_p_calculateformula_0(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "列间公式计算错误", false);
		}
	}
	/**
     * 调用存储过程
     */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
    public void callProcedure(Map<String,Object> map, String mainTaskID) throws ServiceException {
		try{
			excelMapper.callProcedure(map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "调用存储过程错误", false);
		}
		
    }
	
	
}
