package commons.excel.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFtjhqWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.excel.dao.ExcelFileMapper;
import com.tjhq.commons.excel.po.ExcelFilePO;
import com.tjhq.commons.excel.util.ExcelProgressBarUtil;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.commonGrid.service.ICommonGridService;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixColumn;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.po.FloatGrid;
import com.tjhq.commons.inputcomponent.grid.floatgrid.service.IFloatGridService;
import com.tjhq.commons.inputcomponent.grid.propertygrid.service.impl.PropertyGridService;
import com.tjhq.commons.inputcomponent.po.Column;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.PageInfo;
import com.tjhq.commons.inputcomponent.utils.TableUtil;
import com.tjhq.commons.secu.external.IDataAuthService;
import com.tjhq.commons.setting.external.dao.EntryOuterMapper;
import com.tjhq.commons.setting.external.po.DictTSetFddefPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaDataPO;
import com.tjhq.commons.setting.external.po.DictTSetRefrelaPO;
import com.tjhq.commons.setting.external.service.IEntryOuterService;
import com.tjhq.exp.datainput.formula.service.IFormulaUtilService;
import com.tjhq.exp.datainput.table.service.IFixGridInputService;
import com.tjhq.exp.datainput.table.service.IFloatGridInputService;

@Transactional
public abstract class BasePoiService {
	
	@Resource(name = "floatGridService")
	protected IFloatGridService floatGridService;
	
	@Resource(name = "fixGridInputService")
	protected IFixGridInputService fixGridInputService;

	@Resource
	private IEntryOuterService entryOuterService;
	
	@Resource
	protected IFloatGridInputService floatGridInputService;

	@Resource(name = "commonGridService")
	protected ICommonGridService commonGridService;

	@Resource
	protected IDataAuthService dataAuthService;

	@Resource
	protected IFormulaUtilService formulaUtilService;

	@Resource(name = "propertyGridService")
	protected PropertyGridService propertyGridService;
	
	@Resource
	public IDictTFactorService dictTFactorService;
	
	@Resource
	private IDictTModelService dictTModelService;
	
	@Resource
	public ExcelFileMapper excelFileMapper;
	
	@Resource
	public EntryOuterMapper entryOuterMapper;
	
	public Logger logger = Logger.getLogger(BasePoiService.class);
	
//	public HashMap BasePoiCacheMap;
//
//	public HashMap getBasePoiCacheMap() {
//		return BasePoiCacheMap;
//	}
//
//	public void setBasePoiCacheMap(HashMap basePoiCacheMap) {
//		BasePoiCacheMap = basePoiCacheMap;
//	}

	//Excel中参数分隔符1
	public String splitstr = ":::";
	
	//Excel中参数分隔符2
	public String splitstr2 = "^";
	
	//导出excel中隐藏工作表名称
	public String hideConfigSheetName = "tjhqExcelConfig$";
	
	//导出excel中未启用宏时看到的第一张表名称
	public String firstSheetName = "首页说明";
	
	//导出的 工作簿保护密码
	public String worksheetProtectPassword = "Tjhq201508";
	
	//是否导出表间公式，本参数优先于isExportrelationTable参数，1 导出 ；0 不导出
	public String isExportTablebtnformula = "0";
	
	//自动判断是否产生excel模版后再填充数据，1 自动判断；0 强制先生成模版再填充数据
	//public String generateXlsxTemplateFromCacheAuto = "0";
	
	// 默认的开始填充行
	int startfillrownum = 2; 
	
	//字段不能为空在字段名称右边的标识，默认为*
	public String fieldIsNullMark = "*"; 
	
	// 数据导入写入数据的方式；1=按AgencyID全删除，再全Insert；2=Merge into按AgencyID,逻辑主键存在就更新，否则插入；3=分批次按AgencyID,逻辑主键存在就update，否则insert；
	public String importExcelSaveDBMethod = "2"; 
	

	public abstract HashMap getdataPageinfoFormJson(HashMap codeMap, DictTModelPO dictTModel, String grid, String tableID, HashMap<String, Object> preMap, HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException, Exception, SQLException;

	//public abstract HashMap getDataPageInfoAndXlsxFromDB(HashMap rowMap, String agencyID, HttpServletRequest request) throws Exception;

	public abstract HashMap selectFixedGridData(HashMap codeMap, DictTModelPO dictTModel, PageInfo dataPageInfo, String tableID, String DefaultAgencyID, int SumHeadCount, HashMap fieldTypeMap);

	public abstract void dealTypeTableSql(String phytableName, HashMap paramMap1);

	public abstract HashMap checkKeysCount(HashMap codeMap, DictTModelPO dictTModel, List dataL, String tableID, String checkinfoHead, HashMap agencyLstMap) throws Exception;

	public abstract HashMap handleAndSaveExcelData(HashMap codeMap, DictTModelPO dictTModel, HashMap configMap, HashMap excelElevalueguidMap, XSSFWorkbook wb, String curTableid, HashMap paramMap, HttpServletRequest request) throws Exception;

	public abstract void filldataGrid(HashMap codeMap, DictTModelPO dictTModel, HashMap retMap, HashMap dataMap, PageInfo dataPageInfo, HashMap dataTplMap, XSSFWorkbook wb, HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException;

	public abstract boolean getIsRowColConvert();

	public abstract HashMap GetTableLevelInfo(String tableID);

	public abstract String getCurDealType();

	
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
    
    
	//导出 文件名
	public String getFileName(HashMap agencyMap, String tableName){
		String agencyName = agencyMap.get("NAME")== null ? "" : (String) agencyMap.get("NAME");
		if(StringUtils.isNotEmpty(agencyName) && agencyName.contains("[") && agencyName.contains("]")){
			agencyName = agencyName.replace("[", "").replace("]", "");
		}
		String districtCode = agencyMap.get("DISTRICTCODE")== null ? "" : (String) agencyMap.get("DISTRICTCODE");
		String agencyCode = agencyMap.get("CODE")== null ? "" : (String) agencyMap.get("CODE");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String nowtime = df.format(now);
		String fileName = "";
		//isCommon==1通用模板
		String isCommon = agencyMap.get("isCommon")== null ? "" : (String) agencyMap.get("isCommon");
		if("1".equals(isCommon)){
			fileName = districtCode+"_"+agencyCode+"_"+tableName +"_" + agencyName + "_" + nowtime;
		}else{
			fileName = tableName +"_" + agencyName + "_" + nowtime;
		}
		return fileName;
	}
	
	

	// 从数据库中取得宏模板
	/*public HashMap getVBAXlsmFile(String tableID) {
		HashMap retMap = new HashMap();

		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();
		String suitid = dictTModel.getSuitid();

		HashMap resultMap = this.excelFileMapper.SelectXlsmVBATpl(suitid);
		byte[] vbaFile = (byte[]) resultMap.get("vbaxlsmBlob");
		String fn = (String) resultMap.get("name");
		retMap.put("filelob", vbaFile);
		retMap.put("filename", fn);
		return retMap;
	}*/
	
	/*public HashMap SelectModelTableCache(HashMap<String, String> BasePoiCacheMap, String tableID) {
		HashMap modelTableMap = null;
		if (BasePoiCacheMap != null) {
			modelTableMap = (HashMap) BasePoiCacheMap.get("modelTableMap");
		} else {
			BasePoiCacheMap = new HashMap();
		}
		if (modelTableMap == null) {
			modelTableMap = new HashMap();
			BasePoiCacheMap.put("modelTableMap", modelTableMap);
		}
		HashMap tabidMap = (HashMap) modelTableMap.get("tabidMap");
		if (tabidMap == null) {
			tabidMap = new HashMap();
			modelTableMap.put("tabidMap", tabidMap);
		}
		HashMap rettabMap = (HashMap) tabidMap.get(tableID);
		if (rettabMap == null) {
			HashMap appidMap = (HashMap) modelTableMap.get("appidMap");
			if (appidMap == null) {
				appidMap = new HashMap();
				modelTableMap.put("appidMap", appidMap);
			}
			HashMap suitidMap = (HashMap) modelTableMap.get("suitidMap");
			if (suitidMap == null) {
				suitidMap = new HashMap();
				modelTableMap.put("suitidMap", suitidMap);
			}
			HashMap paraMap = new HashMap();
			String sql = " select * from DICT_T_MODEL t where t.APPID =(select appid from dict_t_model where tableID='" + tableID + "')";
			
			List tmpList = this.excelFileMapper.selectExcelInfo(sql);
			for (int i = 0; i < tmpList.size(); i++) {
				HashMap tmpMap = (HashMap) tmpList.get(i);
				String tableid1 = (String) tmpMap.get("tableID");
				String appid1 = (String) tmpMap.get("APPID");
				String suitid1 = (String) tmpMap.get("SUIDID");
				String tableName = (String) tmpMap.get("NAME");
				if(StringUtils.isNotEmpty(tableName)){
					if(tableName.length() > 30){
						tableName = tableName.substring(0, 27) + "...";
					}
				}else{
					tableName = tableid1;
				}
				tmpMap.put("NAME", tableName);
				tabidMap.put(tableid1, tmpMap);
				if (tableid1.equals(tableID))
					rettabMap = tmpMap;
				ArrayList appidList = (ArrayList) appidMap.get(appid1);
				if (appidList == null) {
					appidList = new ArrayList();
					appidMap.put(appid1, appidList);
				}
				appidList.add(tableid1);
				ArrayList suitidList = (ArrayList) suitidMap.get(suitid1);
				if (suitidList == null) {
					suitidList = new ArrayList();
					suitidMap.put(suitid1, suitidList);
				}
				suitidList.add(tableid1);
			}

		}
		return rettabMap;
	}*/
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap ExportDataToExcel(HashMap paraMap, String grid, HttpServletRequest request, DictTModelPO dictTModel, HashMap codeMap) throws Exception {
		ExcelProgressBarUtil.progressAdd(request, 2);
		HashMap retMap = new HashMap();
		//表中文名
		String tableName = dictTModel.getName();
		//表ID
		String tableID = (String) paraMap.get("tableID");
		if (StringUtils.isEmpty(tableID)){
			throw new Exception("导出数据的输入参数不够，缺少tableID!");
		}
		//单位ID
		String agencyID = (String) paraMap.get("agencyID");
		if (StringUtils.isEmpty(agencyID)){
			throw new Exception("导出数据的输入参数不够，缺少agencyID!");
		}
		//查单位信息
		HashMap agencyMap = (HashMap) this.excelFileMapper.SelectDivName(agencyID);
		if(agencyMap == null){
			retMap.put("success", false);
			retMap.put("errorInfo", "根据agencyID="+agencyID+"在表CODE_T_AGENCY_SPF获取单位0个");
			return retMap;
		}
		//是否通用模板
		String isCommon = (String) paraMap.get("isCommon");
		if(StringUtils.isNotEmpty(isCommon) && "1".equals(isCommon)){
			agencyMap.put("NAME", "通用模板");
			agencyMap.put("isCommon", "1");
		}
		// 文件名
		String outFilename = this.getFileName(agencyMap, tableName);
		
		byte[] filexlsx = null;
		PageInfo dataPageInfo = null;
		logger.debug("----------------------excel开始查询数据------------------------");
		retMap.put("SumHeadCount", 0);
		HashMap resultMap = getdataPageinfoFormJson(codeMap, dictTModel, grid, dictTModel.getTableid(), retMap, request);
		retMap.put("errorInfo", resultMap.get("errorInfo"));
		retMap.put("successInfo", resultMap.get("successInfo"));
		retMap.put("success", resultMap.get("success"));
		if (retMap.get("success") == null || !(Boolean) retMap.get("success")) {
			return retMap;
		}
		filexlsx = (byte[]) retMap.get("filexlsx");
		dataPageInfo = (PageInfo) retMap.get("dataPageInfo");
		
		//处理文件列-------------------------开始
		//列信息
		List<DictTFactorPO> dictTFactors = dictTFactorService.getDictTFactorsByTableId(dictTModel.getTableid());
		//文件列位置
		List<String> fileDbColumnNames = new ArrayList<String>();
		for (Iterator iterator = dictTFactors.iterator(); iterator.hasNext();) {
			DictTFactorPO dictTFactorPO = (DictTFactorPO) iterator.next();
			if("C".equals(dictTFactorPO.getShowformat())){
				fileDbColumnNames.add(dictTFactorPO.getDbcolumnname());
			}
		}
		List<String> columns = dataPageInfo.getColumns();
		List<Object> tempDataList = null;
		//处理
		for (Object data : dataPageInfo.getDataList()) {
        	tempDataList = (List<Object>) data;
        	
        	for (String dbColumnName : fileDbColumnNames) {
				int index1 = columns.indexOf(dbColumnName);
				int index2 = columns.indexOf("SNAME_" + dbColumnName);
				if(index2 != -1){
					String fileName = (String) tempDataList.get(index2);
					tempDataList.remove(index1);
					tempDataList.add(index1, fileName);
				}
				
			}
        }
		//处理文件列-------------------------结束
		
		ExcelProgressBarUtil.progressAdd(request, 10);


		//HashMap colmap = GetXlsxDataFormDBII(tableID);
		HashMap dXlsxMap = getXlsxDatapart(tableID);
		String dataJson = (String) dXlsxMap.get("filelob");
		HashMap dXlsxNull = this.getXlsxNullTpl(tableID);
		if (filexlsx == null) {
			filexlsx = (byte[]) dXlsxNull.get("filelob");
		}
		XSSFtjhqWorkbook wb;
		wb = new XSSFtjhqWorkbook(true);
		XSSFWorkbook wbdb = new XSSFWorkbook(new ByteArrayInputStream(filexlsx));
		for (XSSFSheet sht1 : wbdb) {
			XSSFSheet tmpSheet = wb.copySheetFromOtherXLSX(sht1, wbdb);
			if (tmpSheet.getSheetName().equalsIgnoreCase(hideConfigSheetName)) {
				Row firstConfRow = tmpSheet.getRow(0);
				Cell agencyCell = firstConfRow.getCell(4); // 固定
				String codename = (String) agencyMap.get("CODE") + "-" + (String) agencyMap.get("NAME");
				agencyCell.setCellValue(codename);
			}
		}
		HashMap dataTplMap = (HashMap) new ObjectMapper().readValue(dataJson, HashMap.class);
		Object sht = dataTplMap.get(tableName);
		if (sht == null) {
			retMap.put("success", false);
			retMap.put("errorInfo", "系统中的的Excel模板有问题,中没有找到sheet：" + tableName + "的工作表！\r\n数据表导出失败！");
			return retMap;
		}
		
		HashMap fieldTypeMap = GetXlsxFieldTypeFormDB(tableID);
		
		int sumHeadCount = (Integer) retMap.get("SumHeadCount");
		HashMap selMap = selectFixedGridData(codeMap, dictTModel, dataPageInfo, tableID, agencyID, sumHeadCount, fieldTypeMap);
		
		HashMap dataMap = (HashMap) selMap.get("data");
		if (dataMap == null) {
			retMap.put("error", "未检索到数据,请确认选中的了单位！");
			return retMap;
		}

		ExcelProgressBarUtil.progressAdd(request, 5);
		request.setAttribute("filldata_cost", 10);
		//excel填充数据。
		filldataGrid(codeMap, dictTModel, retMap, dataMap, dataPageInfo, dataTplMap, wb, request);
		//处理公式
		WorkbookAllCellsCalc(wb);

		// 从数据库中取得宏模板
		/*
		 * HashMap vbaMap = getVBAXlsmFile(tableID); byte[] vbaBin = (byte[])
		 * vbaMap.get("filelob"); XSSFWorkbook vbAwb = new XSSFWorkbook(new
		 * ByteArrayInputStream(vbaBin));
		 */
		
		// 从代码目录中取得宏模板
		String separatorChar = "/";
		String filePath =  "com" + separatorChar + "tjhq" + separatorChar + "commons" + separatorChar + "excel" + separatorChar + "template" + separatorChar + "tjhqpoiTemplate.xlsm";
		InputStream fis = this.getClass().getClassLoader().getResourceAsStream(filePath);
		XSSFWorkbook vbAwb = new XSSFWorkbook(fis);

		XSSFSheet srcfirstsht = vbAwb.getSheet(firstSheetName);
		wb.copySheetFromOtherXLSX(srcfirstsht, vbAwb);
		wb.setSheetOrder(firstSheetName, 0);
		wb.copycustomUIfromOtherXLSM(vbAwb);
		wb.copyVBAfromOtherXLSM(vbAwb);
		CTBookViews bv = wb.getCTWorkbook().getBookViews();
		bv.getWorkbookViewArray(0).setActiveTab(0L);
		
		//若 正常导出，则显示首页，隐藏其他页面。
		//若 不导出隐藏sheet，只有数据页，则直接显示数据页面。
		String noHiddenSheet = request.getParameter("noHiddenSheet");
		if(StringUtils.isNotEmpty(noHiddenSheet) && "1".equals(noHiddenSheet)){
			wb.setSheetHidden(0, true);
			for (int isheetnum = 1; isheetnum < wb.getNumberOfSheets(); isheetnum++) {
				wb.setSheetHidden(isheetnum, false);
			}
			wb.setActiveSheet(1);
		}else{
			for (int isheetnum = 1; isheetnum < wb.getNumberOfSheets(); isheetnum++) {
				wb.setSheetHidden(isheetnum, true);
			}
		}
		
		String Extxlsm = ".xlsx";
		if (wb.isMacroEnabled())
			Extxlsm = ".xlsm";

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] databytes = baos.toByteArray();
		retMap.put("filelob", databytes);
		retMap.put("filename", outFilename + Extxlsm);

		ExcelProgressBarUtil.progressAdd(request, 3);

		retMap.put("success", true);
		return retMap;
	}

	public void calcFilldataProgress(int icur, int itotal,
			HttpServletRequest request) {
		Integer iFcost = (Integer) request.getAttribute("filldata_cost");
		if (iFcost == null)
			return;
		int icurCost = iFcost.intValue();
		if (icurCost == 0)
			return;
		if (itotal > 0) {
			int iadd = 0;
			for (int ib = 1; ib <= icurCost; ib++) {
				if (icur == itotal / icurCost * ib) {
					iadd = 1;
					ExcelProgressBarUtil.progressAdd(request, iadd);
					break;
				}
			}

		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashMap getXlsxDatapart(String tableID) {
		HashMap retMap = new HashMap();
		ExcelFilePO retPo = this.excelFileMapper.SelectXlsxDatapart(tableID);
		String dataFile = (String) retPo.getDatapartxlsx();
		String fn = retPo.getName();
		retMap.put("filelob", dataFile);
		retMap.put("filename", fn);
		return retMap;
	}

	public HashMap getXlsxNullTpl(String tableID) {
		HashMap retMap = new HashMap();
		ExcelFilePO retPo = this.excelFileMapper.SelectXlsxNullTpl(tableID);
		byte[] nullFile = (byte[]) retPo.getNullxlsxtpl();
		String fn = retPo.getName();
		retMap.put("filelob", nullFile);
		retMap.put("filename", fn);
		return retMap;

	}

	public String sheetColRowConvert(int topnum, int leftnum, String cellname)
			throws IOException, JsonParseException, JsonMappingException {
		String regex = "^([a-zA-Z]+)(\\d+)$";
		String retCellName = "";
		topnum = topnum + 1; // 行号从1开始级数;
		Pattern ptn = Pattern.compile(regex);
		Matcher m = ptn.matcher(cellname);
		if (m.find()) {
			String ColName = m.group(1);
			String rowNum = m.group(2);
			int col1 = CellReference.convertColStringToIndex(ColName);
			int row1 = Integer.valueOf(rowNum);
			int iTmp = col1 - leftnum + topnum;
			col1 = row1 - topnum + leftnum;
			row1 = iTmp;
			ColName = CellReference.convertNumToColString(col1);
			retCellName = ColName + row1;
		}
		return retCellName;
	}

	/*public HashMap handleAndSaveExcelTpl(byte[] bais, String orgFilename,
			String tableID) throws JsonGenerationException,
			JsonMappingException, IOException {
		HashMap retMap = new HashMap();
		HashMap dbMap = (HashMap) SelectModelTableCache(tableID);
		String tableName = (String) dbMap.get("NAME");
		String guid = getGUID();

		ObjectMapper objectMapper = new ObjectMapper();
		HashMap notLockWbCellMap = new HashMap();
		XSSFWorkbook wb;
		wb = new XSSFWorkbook(new ByteArrayInputStream(bais));
		StylesTable styles = wb.getStylesSource();
		CTStylesheet sty1 = styles.getCTStylesheet();
		CTCellXfs xfs = (CTCellXfsImpl) sty1.getCellXfs();
		CTXf[] xfsarray = xfs.getXfArray();
		int pos = 0;
		HashMap pxfMap = new HashMap();
		for (CTXf xf1 : xfsarray) {
			CTCellProtection p1 = xf1.getProtection();
			if (p1 != null) {
				boolean lc1 = p1.getLocked();
				if (!lc1) {
					pxfMap.put(pos, "Locked_false");
				}
			}
			pos++;
		}
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			List notLockSheetCellList = new ArrayList();
			Sheet sheet = wb.getSheetAt(i);
			for (Row row : sheet) {
				for (Cell cell : row) {
					CellStyle cellstyle = cell.getCellStyle();
					short styleIdx = cellstyle.getIndex();
					String isProtect = (String) pxfMap.get(Integer
							.valueOf(styleIdx));
					if (isProtect != null) {
						XSSFCell xssfcell = (XSSFCell) cell;
						String data = xssfcell.getReference() + "/"
								+ cell.getRowIndex() + "_"
								+ cell.getColumnIndex();
						data = data + splitstr + cell.toString();
						notLockSheetCellList.add(data);
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC: {
								cell.setCellValue(0);
								break;
							}
							case Cell.CELL_TYPE_STRING: {
								cell.setCellValue("");
								break;
							}
							default: {
								cell.setCellValue("");
								cell.setCellType(3);
							}
						}
					}
				}
			}
			notLockWbCellMap.put(wb.getSheetName(i), notLockSheetCellList);
		}
		WorkbookAllCellsCalc(wb);
		Object sht = notLockWbCellMap.get(tableName);
		if (sht == null) {
			retMap.put("success", false);
			retMap.put("errorInfo", "传入的Excel中没有找到sheet：\"" + tableName + "\" 的工作表！\r\n模板表导入失败！");
			return retMap;
		}
		String datajson = objectMapper.writeValueAsString(notLockWbCellMap);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] filenull = baos.toByteArray();
		System.out.println("excel模板文件大小:" + filenull.length);
		ExcelFilePO epo = new ExcelFilePO();
		epo.setTableid(tableID);
		epo.setOriginalxlsxname(orgFilename);
		epo.setName(tableName);
		epo.setOriginalxlsxtpl(bais);
		epo.setNullxlsxtpl(filenull);
		epo.setDatapartxlsx(datajson);
		epo.setStylepartxlsx("");
		this.excelFileMapper.updateXlsx(epo);

		retMap.put("success", true);
		retMap.put("errorInfo", "上传Excel模板文件成功!");

		return retMap;
	}*/

	public void WorkbookAllCellsCalc(XSSFWorkbook wb) {
		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
		for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
			Sheet sheet = wb.getSheetAt(sheetNum);
			for (Row r : sheet) {
				for (Cell c : r) {
					if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
						logger.debug("公式单元格(" + ((XSSFCell) c).getReference() + "):" + c.getCellFormula());
						evaluator.evaluateFormulaCell(c);
					}
				}
			}
		}
		XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
	}

	/*public HashMap GetXlsxDataFormDBold(String tableID) {

		List retListMap = this.excelFileMapper.SelectXlsxColMap(tableID);
		Iterator it1 = retListMap.iterator();
		HashMap<String, String> resultMap = new HashMap<String, String>();
		while (it1.hasNext()) {
			HashMap map = (HashMap) it1.next();
			String DBCOLUMNNAME = (String) map.get("DBCOLUMNNAME");
			if (DBCOLUMNNAME == null){
				continue;
			}
			String XLSXCOLUMNID = (String) map.get("XLSXCOLUMNID");
			resultMap.put(XLSXCOLUMNID.toUpperCase(), DBCOLUMNNAME);
			resultMap.put("dbcolname_" + DBCOLUMNNAME, XLSXCOLUMNID.toUpperCase());
		}
		HashMap<String, Map> XlsxcolumnidMap = (HashMap<String, Map>) BasePoiCacheMap.get("XlsxcolumnidMap");
		if (XlsxcolumnidMap == null) {
			XlsxcolumnidMap = new HashMap<String, Map>();
			BasePoiCacheMap.put("XlsxcolumnidMap", XlsxcolumnidMap);
		}
		XlsxcolumnidMap.put(tableID, resultMap);
		return resultMap;
	}*/

	public HashMap GetXlsxDataFormDBII(String tableID) {
		HashMap<String, Map> XlsxcolumnidMap = new HashMap<String, Map>();
		HashMap<String, String> colInfoMap = (HashMap<String, String>) XlsxcolumnidMap.get(tableID);
		colInfoMap = new HashMap();
		HashMap paraMap = new HashMap();
		String sql = " select t.dbcolumnname,t2.xlsxcolumnid,t.name,t.columnid " +
				"from dict_t_factor t,dict_t_factorxlsx t2 " +
				"where t.tableID=t2.tableID and t.columnid=t2.columnid and t.tableID ='" + tableID
				+ "' and (t.isvisible=1 or t.dbcolumnname='agencyid' ) order by length(t2.xlsxcolumnid),t2.xlsxcolumnid";

		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		if (tmpList.size() == 0)
			return null;
		Iterator it1 = tmpList.iterator();
		while (it1.hasNext()) {
			HashMap map = (HashMap) it1.next();
			String DBCOLUMNNAME = (String) map.get("DBCOLUMNNAME");
			String COLUMNID = (String) map.get("COLUMNID");
			String XLSXCOLUMNID = (String) map.get("XLSXCOLUMNID");
			if (DBCOLUMNNAME == null || XLSXCOLUMNID == null){
				continue;
			}
			String fieldCNname = (String) map.get("NAME");
			colInfoMap.put(XLSXCOLUMNID.toUpperCase(), DBCOLUMNNAME);
			colInfoMap.put("dbcolname_" + DBCOLUMNNAME, XLSXCOLUMNID.toUpperCase());
			colInfoMap.put("dbFieldCNname_" + DBCOLUMNNAME, fieldCNname);
			colInfoMap.put("columnid_" + COLUMNID, DBCOLUMNNAME);
		}

		XlsxcolumnidMap.put(tableID, colInfoMap);
		return colInfoMap;
	}

	public HashMap GetXlsxFieldTypeFormDB(String tableID) {
		HashMap resultMap = new HashMap();
		String sql = "select t.dbcolumnname,t.datatype,t.iskey,t.nullable,t.isvirtual,t.isbandcol from dict_t_factor t where t.tableID='" + tableID + "' order by t.orderid";
		
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		if (tmpList.size() == 0){
			return null;
		}
		Iterator it1 = tmpList.iterator();
		LinkedHashMap composeKeyMap = new LinkedHashMap();
		LinkedHashMap tmpMap = new LinkedHashMap();
		LinkedHashMap nonNullMap = new LinkedHashMap();
		while (it1.hasNext()) {
			HashMap map = (HashMap) it1.next();

			String DBCOLUMNNAME = (String) map.get("DBCOLUMNNAME");
			if (DBCOLUMNNAME == null)
				continue;
			String ISKEY = (String) map.get("ISKEY");
			if ("1".equalsIgnoreCase(ISKEY)) {
				composeKeyMap.put(DBCOLUMNNAME, "key");
			}
			if ("DATAKEY".equalsIgnoreCase(DBCOLUMNNAME)) {
				composeKeyMap.put(DBCOLUMNNAME, "key");
			}
			if ("TEMPLATEID".equalsIgnoreCase(DBCOLUMNNAME)) {
				tmpMap.put(DBCOLUMNNAME, "key");
			}
			String NULLABLE = (String) map.get("NULLABLE");
			if ("0".equalsIgnoreCase(NULLABLE)) {
				nonNullMap.put(DBCOLUMNNAME, "1");
			}
			HashMap colinfoMap = new HashMap();
			double datatype = ((BigDecimal) map.get("DATATYPE")).doubleValue();
			String isVirtual = (String) map.get("ISVIRTUAL");
			String ISBANDCOL = (String) map.get("ISBANDCOL");
			colinfoMap.put("DATATYPE", datatype);
			colinfoMap.put("ISVIRTUAL", isVirtual);
			colinfoMap.put("ISBANDCOL", ISBANDCOL);
			resultMap.put(DBCOLUMNNAME, colinfoMap);
		}
		if (composeKeyMap.size() == 0) {
			composeKeyMap = (LinkedHashMap) tmpMap.clone();
		}
		resultMap.put("composeKey-Key", composeKeyMap);
		resultMap.put("nonNullMap-Key", nonNullMap);
		return resultMap;
	}

	public String getGUID() {
		UUID uuid = UUID.randomUUID();
		String guid = uuid.toString();
		guid = guid.replaceAll("-", "").toUpperCase();
		return guid;
	}

	public String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return fCell.getStringCellValue();
				}
			}
		}
		return null;
	}

	public HashMap returnErrorInfo(String errInfo) {
		HashMap retMap = new HashMap();
		retMap.put("success", false);
		retMap.put("errorInfo", errInfo);
		logger.debug("【Excel相关错误】:" + errInfo);
		return retMap;
	}

	//得到某表某单位所有的datakey
	public HashMap getExistsDataKeyByTableidAgencyid(String tableID, String agencyID, DictTModelPO dictTModel) throws Exception {

		HashMap retMap = new HashMap();
		String dbTableName = dictTModel.getDbtablename();
		HashMap paraMap = new HashMap();
		String sql = "select datakey from " + dbTableName + " t where t.agencyid='" + agencyID + "'";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		for (int i = 0; i < tmpList.size(); i++) {
			HashMap tmpMap = (HashMap) tmpList.get(i);
			String dk = (String) tmpMap.get("DATAKEY");
			retMap.put(dk, dk);
		}
		return retMap;
	}

	/*public HashMap getExistsTemplateidByTableidAgencyid(String tableID, String AgencyID) throws Exception {

		HashMap retMap = new HashMap();
		HashMap dbMap = (HashMap) SelectModelTableCache(tableID);
		String tableName = (String) dbMap.get("NAME");
		String DBtableName = (String) dbMap.get("DBTABLENAME");
		HashMap paraMap = new HashMap();
		String sql = "select distinct templateid templateid from " + DBtableName + " t where t.agencyid='" + AgencyID + "'";
		
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		for (int i = 0; i < tmpList.size(); i++) {
			HashMap tmpMap = (HashMap) tmpList.get(i);
			String dk = (String) tmpMap.get("TEMPLATEID");
			retMap.put(dk, dk);
		}
		return retMap;
	}*/

	public HashMap updateSaveData2DB(LinkedHashMap tabdefFieldsMap, List dataL, String tableID, HashMap paramMap, List keyFieldsList) throws Exception {
		// 先按单位全删除，再Insert
		HashMap retMap = new HashMap();
		// 数据权限验证
		String AgencyID = (String) paramMap.get("agencyID");
		String userID = SecureUtil.getCurrentUser().getGuid();
		String taskID = (String) paramMap.get("taskID");
		String appID = (String) paramMap.get("appID");
		Condition localCondition = new Condition();
		localCondition.setExpression(" AGENCYID = '" + AgencyID + "'");
		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		HashMap paraMap = new HashMap();
		
		int pageRowCC = 1; // 分页insert；
		for (int iPage = 0; iPage < dataL.size(); iPage += pageRowCC) {
			StringBuffer sqlBUF = new StringBuffer();
			sqlBUF.append("update " + dbTableName + "\r\n set ");
			HashMap rowMap = (HashMap) dataL.get(iPage);
			List<String> aStringList = new ArrayList<String>();
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
				if(StringUtils.isNotEmpty(fieldinfo) && aStringList.indexOf(fieldinfo) == -1){
					aStringList.add(fieldinfo);
					Object vv = rowMap.get(fieldinfo);
					if (vv == null) {
						
					} else if (vv.getClass().getName().endsWith("String")) {
						sqlBUF.append(fieldinfo).append("=");
						sqlBUF.append("'").append((String) vv).append("' ").append(
								",");
					} else {
						sqlBUF.append(fieldinfo).append("=");
						sqlBUF.append((Double) vv).append("  ").append(",");
					}
				}
				
			}
			int lastPos = sqlBUF.lastIndexOf(",");
			sqlBUF.replace(lastPos, lastPos + 1, "");
			String datakey = (String) rowMap.get("DATAKEY");
			sqlBUF.append("\r\nwhere 1=1 ");
			for (int ik = 0; ik < keyFieldsList.size(); ik++) {
				String keyfield = (String) keyFieldsList.get(ik);
				String val = (String) rowMap.get(keyfield);
				if ("AGENCYID".equalsIgnoreCase(keyfield)) {
					val = AgencyID;
				}
				sqlBUF.append(" and ").append(keyfield).append(" = '").append(val).append("'");
			}
			String sql = sqlBUF.toString();
			
			this.excelFileMapper.updateSql(sql);
		}
		return retMap;
	}

	public HashMap insertSaveData2DB(LinkedHashMap tabdefFieldsMap, List dataL, String tableID, HashMap paramMap) throws Exception {
		// 先按单位全删除，再Insert
		HashMap retMap = new HashMap();
		// 数据权限验证
		String AgencyID = (String) paramMap.get("agencyID");
		String userID = SecureUtil.getCurrentUser().getGuid();
		String taskID = (String) paramMap.get("taskID");
		String appID = (String) paramMap.get("appID");
		Condition localCondition = new Condition();
		localCondition.setExpression(" AGENCYID = '" + AgencyID + "'");
		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		HashMap paraMap = new HashMap();
		
		int pageRowCC = 100; // 分页insert；
		for (int iPage = 0; iPage < dataL.size(); iPage += pageRowCC) {
			StringBuffer sqlBUF = new StringBuffer();
			sqlBUF.append("INSERT INTO " + dbTableName + " (");
			List<String> aStringList = new ArrayList<String>();
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it
					.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key))
						.split(splitstr)[0];
				if(StringUtils.isNotEmpty(fieldinfo) && aStringList.indexOf(fieldinfo) == -1){
					sqlBUF.append(fieldinfo).append(",");
				}
			}
			sqlBUF.replace(sqlBUF.lastIndexOf(","), sqlBUF.lastIndexOf(",") + 1, "");
			sqlBUF.append(") \r\n (");
			
			aStringList = new ArrayList<String>();
			int curEndRowCC = (iPage + pageRowCC) < dataL.size() ? (iPage + pageRowCC) : dataL.size();
			for (int iL = iPage; iL < curEndRowCC; iL++) {
				HashMap rowMap = (HashMap) dataL.get(iL);
				sqlBUF.append("SELECT ");
				for (Iterator it = tabdefFieldsMap.keySet().iterator(); it
						.hasNext();) {
					int key = (Integer) it.next();
					String fieldinfo = ((String) tabdefFieldsMap.get(key))
							.split(splitstr)[0];
					// River修改20151230 start
					if(StringUtils.isNotEmpty(fieldinfo) && aStringList.indexOf(fieldinfo) == -1){
						if ("DATAKEY".equalsIgnoreCase(fieldinfo))
							sqlBUF.append("sys_guid() , ");
						else {
							Object vv = rowMap.get(fieldinfo);
							if (vv == null) {
								sqlBUF.append("null").append(",");
							} else if (vv.getClass().getName().endsWith("String")) {
								sqlBUF.append("'").append((String) vv).append("',");
							} else {
								sqlBUF.append((Double) vv).append(",");
							}
						}
					}
					// River修改20151230 end
				}
				sqlBUF.replace(sqlBUF.lastIndexOf(","),
						sqlBUF.lastIndexOf(",") + 1, "");
				sqlBUF.append(" FROM DUAL \r\n union all \r\n");
			}
			sqlBUF.replace(sqlBUF.lastIndexOf("union all"), sqlBUF
					.lastIndexOf("union all") + 9, "");
			sqlBUF.append(")");
			String sql = sqlBUF.toString();
			
			this.excelFileMapper.updateSql(sql);
		}
		return retMap;
	}
	
	/**
	 * 导入
	 * @param tabdefFieldsMap
	 * @param dataL
	 * @param tableID
	 * @param paramMap
	 * @param keyFieldsList
	 * @param keysList
	 * @param curShtname
	 * @param wb
	 * @return
	 * @throws Exception
	 */
	public HashMap saveData(LinkedHashMap tabdefFieldsMap, List dataL, String tableID, HashMap paramMap, List keyFieldsList, List keysList, String curShtname, XSSFWorkbook wb) throws Exception {
		HashMap retMap = new HashMap();
		// 数据权限验证
		String agencyID = (String) paramMap.get("agencyID");
		String userID = SecureUtil.getCurrentUser().getGuid();
		String taskID = (String) paramMap.get("taskID");
		String appID = (String) paramMap.get("appID");
		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		
		String sql = "";
		
		//按照datakey删除excel中删除的数据
		Sheet sheetHideconfig = wb.getSheet(hideConfigSheetName);
		int numStart = 29;
		Cell deleteCell = sheetHideconfig.getRow(0).getCell(numStart);
		if(deleteCell != null){
			int deleteCellCount = Integer.parseInt(deleteCell.getStringCellValue());
			String deleteDatakey = "";
			for(int i = 1; i <= deleteCellCount; i++){
				Cell cell = sheetHideconfig.getRow(0).getCell( numStart + i );
				if(cell != null){
					deleteDatakey += cell.getStringCellValue();
				}
			}
			if(StringUtils.isNotEmpty(deleteDatakey)){
				deleteDatakey = deleteDatakey.substring(0, deleteDatakey.length() - 1);
				deleteDatakey = deleteDatakey.replaceAll(Matcher.quoteReplacement(","), "','");
				sql = "delete from " + dbTableName + " where datakey in ('" +deleteDatakey+"') and agencyid = '" + agencyID + "'";
				
				this.excelFileMapper.updateSql(sql);
			}
		}
		//处理文件列-------------------------开始
		//列信息
		List<DictTFactorPO> dictTFactors = dictTFactorService.getDictTFactorsByTableId(tableID);
		//文件列位置
		List<String> fileDbColumnNames = new ArrayList<String>();
		for (Iterator iterator = dictTFactors.iterator(); iterator.hasNext();) {
			DictTFactorPO dictTFactorPO = (DictTFactorPO) iterator.next();
			if("C".equals(dictTFactorPO.getShowformat())){
				fileDbColumnNames.add(dictTFactorPO.getDbcolumnname());
			}
		}
		//处理文件列-------------------------结束
		
		// 逻辑键存在则update，否则Insert
		List tmpList;
		int pageRowCC = 200; // 分页insert；
		int lastPos;
		
		//将所有字段名用逗号分隔
		StringBuffer sql1 = new StringBuffer();
		for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
			int key = (Integer) it.next();
			String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
			//不处理文件列
			if(fileDbColumnNames.indexOf(fieldinfo) == -1){
				sql1.append(fieldinfo).append(",");
			}
		}
		lastPos = sql1.lastIndexOf(",");
		sql1.replace(lastPos, lastPos + 1, "");
		
		for (int iPage = 0; iPage < dataL.size(); iPage += pageRowCC) {
			StringBuffer sql2 = new StringBuffer();
			StringBuffer sql3 = new StringBuffer();
			int curEndRowCC = (iPage + pageRowCC) < dataL.size() ? (iPage + pageRowCC) : dataL.size();
			for (int iL = iPage; iL < curEndRowCC; iL++) {
				HashMap rowMap = (HashMap) dataL.get(iL);
				sql2.append("select ");
				for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
					int key = (Integer) it.next();
					String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
					//不处理文件列
					if(fileDbColumnNames.indexOf(fieldinfo) == -1){
						Object vv = rowMap.get(fieldinfo);
						if (vv == null) {
							sql2.append("null ").append(fieldinfo).append(",");
						} else if (vv.getClass().getName().endsWith("String")) {
							sql2.append("'").append((String) vv).append("' ").append(fieldinfo).append(",");
						} else {
							sql2.append((Double) vv).append(" ").append(fieldinfo).append(",");
						}
						if("DATAKEY".equals(fieldinfo)){
							sql3.append("'").append((String) vv).append("'").append(",");
						}
					}
				}
				lastPos = sql2.lastIndexOf(",");
				sql2.replace(lastPos, lastPos + 1, "");
				sql2.append(" from dual union all ");
			}
			lastPos = sql2.lastIndexOf("union all");
			sql2.replace(lastPos, lastPos + 9, "");
			
			sql3.replace(sql3.lastIndexOf(","), sql3.lastIndexOf(",") + 1, "");
			
			sql = "update "+dbTableName+" a set ("+sql1+") = (select "+sql1+" from (" + sql2 + ") b where a.datakey = b.datakey and a.agencyid = b.agencyid) where a.datakey in ("+sql3+") and agencyid = '" + agencyID + "'";
			this.excelFileMapper.updateSql(sql);
			sql = "insert into "+dbTableName+" a ("+sql1+") (select "+sql1+" from (" + sql2 + ") b where not exists (select 1 from "+dbTableName+" t where t.datakey = b.datakey and t.agencyid = b.agencyid))";
			this.excelFileMapper.updateSql(sql);
		}
		return retMap;
	}
	/*public HashMap saveData(LinkedHashMap tabdefFieldsMap, List dataL, String tableID, HashMap paramMap, List keyFieldsList, List keysList, String curShtname, XSSFWorkbook wb) throws Exception {
		HashMap retMap = new HashMap();
		// 数据权限验证
		String AgencyID = (String) paramMap.get("agencyID");
		String userID = SecureUtil.getCurrentUser().getGuid();
		String taskID = (String) paramMap.get("taskID");
		String appID = (String) paramMap.get("appID");
		Condition localCondition = new Condition();
		localCondition.setExpression(" agencyid = '" + AgencyID + "'");
		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		
		String sql = "";
		
		//按照datakey删除excel中删除的数据
		Sheet sheetHideconfig = wb.getSheet(hideConfigSheetName);
		int numStart = 29;
		Cell deleteCell = sheetHideconfig.getRow(0).getCell(numStart);
		if(deleteCell != null){
			int deleteCellCount = Integer.parseInt(deleteCell.getStringCellValue());
			String deleteDatakey = "";
			for(int i = 1; i <= deleteCellCount; i++){
				Cell cell = sheetHideconfig.getRow(0).getCell( numStart + i );
				if(cell != null){
					deleteDatakey += cell.getStringCellValue();
				}
			}
			if(StringUtils.isNotEmpty(deleteDatakey)){
				deleteDatakey = deleteDatakey.substring(0, deleteDatakey.length() - 1);
				deleteDatakey = deleteDatakey.replaceAll(Matcher.quoteReplacement(","), "','");
				sql = "delete from " + dbTableName + " where datakey in ('" +deleteDatakey+"') and agencyid = '" + AgencyID + "'";
				
				this.excelFileMapper.updateSql(sql);
			}
		}
		//处理文件列-------------------------开始
		//列信息
		List<DictTFactorPO> dictTFactors = dictTFactorService.getDictTFactorsByTableId(tableID);
		//文件列位置
		List<String> fileDbColumnNames = new ArrayList<String>();
		for (Iterator iterator = dictTFactors.iterator(); iterator.hasNext();) {
			DictTFactorPO dictTFactorPO = (DictTFactorPO) iterator.next();
			if("C".equals(dictTFactorPO.getShowformat())){
				fileDbColumnNames.add(dictTFactorPO.getDbcolumnname());
			}
		}
		//处理文件列-------------------------结束
		
		// 逻辑键存在则update，否则Insert
		List tmpList;
		int pageRowCC = 200; // 分页insert；
		int lastPos;
		
		for (int iPage = 0; iPage < dataL.size(); iPage += pageRowCC) {
			StringBuffer sqlBUF = new StringBuffer();
			sqlBUF.append("MERGE INTO (select ") ;
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
				//不处理文件列
				if(fileDbColumnNames.indexOf(fieldinfo) == -1){
					sqlBUF.append(fieldinfo).append(",");
				}
			}
			lastPos = sqlBUF.lastIndexOf(",");
			sqlBUF.replace(lastPos, lastPos + 1, "");
			
			sqlBUF.append(" from " + dbTableName + ") t USING (\r\n");
			int curEndRowCC = (iPage + pageRowCC) < dataL.size() ? (iPage + pageRowCC) : dataL.size();
			for (int iL = iPage; iL < curEndRowCC; iL++) {
				HashMap rowMap = (HashMap) dataL.get(iL);
				sqlBUF.append("SELECT ");
				for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
					int key = (Integer) it.next();
					String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
					//不处理文件列
					if(fileDbColumnNames.indexOf(fieldinfo) == -1){
						Object vv = rowMap.get(fieldinfo);
						if (vv == null) {
							sqlBUF.append("null ").append(fieldinfo).append(",");
						} else if (vv.getClass().getName().endsWith("String")) {
							sqlBUF.append("'").append((String) vv).append("' ").append(fieldinfo).append(",");
						} else {
							sqlBUF.append((Double) vv).append(" ").append(fieldinfo).append(",");
						}
					}
				}
				lastPos = sqlBUF.lastIndexOf(",");
				sqlBUF.replace(lastPos, lastPos + 1, "");
				sqlBUF.append(" FROM DUAL \r\n union all \r\n");
			}
			lastPos = sqlBUF.lastIndexOf("union all");
			sqlBUF.replace(lastPos, lastPos + 9, "");
			sqlBUF.append(") t2\r\n");
			sqlBUF.append("ON ( ");
			HashMap keyFieldsMap = new HashMap();
			for (int ik = 0; ik < keyFieldsList.size(); ik++) {
				String keyfield = (String) keyFieldsList.get(ik);
				keyFieldsMap.put(keyfield, keyfield);
				sqlBUF.append("t.").append(keyfield).append(" = ") .append("t2.").append(keyfield).append(" and ");
			}
			lastPos = sqlBUF.lastIndexOf("and ");
			sqlBUF.replace(lastPos, lastPos + 4, "");
			sqlBUF.append(" )\r\n");
			sqlBUF.append("WHEN MATCHED THEN \r\n  UPDATE SET ");
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
				//不处理文件列
				if(fileDbColumnNames.indexOf(fieldinfo) == -1){
					if (keyFieldsMap.containsKey(fieldinfo)){
						continue;
					}
					sqlBUF.append("t.").append(fieldinfo).append(" = ").append("t2.").append(fieldinfo).append(" , ");
				}
			}
			lastPos = sqlBUF.lastIndexOf(", ");
			sqlBUF.replace(lastPos, lastPos + 2, "");
			sqlBUF.append(" \r\n");
			sqlBUF.append("WHEN NOT MATCHED THEN \r\n  INSERT (");
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
				//不处理文件列
				if(fileDbColumnNames.indexOf(fieldinfo) == -1){
					sqlBUF.append("t.").append(fieldinfo).append(" , ");
				}
			}
			lastPos = sqlBUF.lastIndexOf(", ");
			sqlBUF.replace(lastPos, lastPos + 2, "");
			sqlBUF.append(") \r\n  values( ");
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
				//不处理文件列
				if(fileDbColumnNames.indexOf(fieldinfo) == -1){
					sqlBUF.append("t2.").append(fieldinfo).append(" , ");
				}
			}
			lastPos = sqlBUF.lastIndexOf(", ");
			sqlBUF.replace(lastPos, lastPos + 2, "");
			sqlBUF.append(") ");
			sql = sqlBUF.toString();
			
			this.excelFileMapper.updateSql(sql);
		}
		return retMap;
	}*/
	
	public HashMap saveData1(LinkedHashMap tabdefFieldsMap, List dataL, String tableID, HashMap paramMap) throws Exception {
		// 先按单位全删除，再Insert
		HashMap retMap = new HashMap();
		// 数据权限验证
		String AgencyID = (String) paramMap.get("agencyID");
		String userID = SecureUtil.getCurrentUser().getGuid();
		String taskID = (String) paramMap.get("taskID");
		String appID = (String) paramMap.get("appID");
		// 待修改
		Condition localCondition = new Condition();
		localCondition.setExpression(" AGENCYID = '" + AgencyID + "'");
		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		HashMap paraMap = new HashMap();
		String sql = "select count(*) CC from " + dbTableName + " t where t.AgencyID='" + AgencyID + "'";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		int curCC = ((BigDecimal) ((HashMap) tmpList.get(0)).get("CC")).intValue();
		if (curCC > 0) {
			sql = "delete from " + dbTableName + " t where t.AgencyID='" + AgencyID + "'";
			tmpList = this.excelFileMapper.selectExcelInfo(sql);
		}
		int pageRowCC = 100; // 分页insert；
		for (int iPage = 0; iPage < dataL.size(); iPage += pageRowCC) {
			StringBuffer sqlBUF = new StringBuffer();
			sqlBUF.append("INSERT INTO " + dbTableName + " (");
			for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
				int key = (Integer) it.next();
				String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
				sqlBUF.append(fieldinfo).append(",");
			}
			sqlBUF.replace(sqlBUF.lastIndexOf(","), sqlBUF.lastIndexOf(",") + 1, "");
			sqlBUF.append(") \r\n (");
			int curEndRowCC = (iPage + pageRowCC) < dataL.size() ? (iPage + pageRowCC) : dataL.size();
			for (int iL = iPage; iL < curEndRowCC; iL++) {
				HashMap rowMap = (HashMap) dataL.get(iL);
				sqlBUF.append("SELECT ");
				for (Iterator it = tabdefFieldsMap.keySet().iterator(); it.hasNext();) {
					int key = (Integer) it.next();
					String fieldinfo = ((String) tabdefFieldsMap.get(key)).split(splitstr)[0];
					if ("DATAKEY".equalsIgnoreCase(fieldinfo))
						sqlBUF.append("sys_guid() , ");
					else {
						Object vv = rowMap.get(fieldinfo);
						if (vv == null) {
							sqlBUF.append("null").append(",");
						} else if (vv.getClass().getName().endsWith("String")) {
							sqlBUF.append("'").append((String) vv).append("',");
						} else {
							sqlBUF.append((Double) vv).append(",");
						}
					}
				}
				sqlBUF.replace(sqlBUF.lastIndexOf(","), sqlBUF.lastIndexOf(",") + 1, "");
				sqlBUF.append(" FROM DUAL \r\n union all \r\n");
			}
			sqlBUF.replace(sqlBUF.lastIndexOf("union all"), sqlBUF.lastIndexOf("union all") + 9, "");
			sqlBUF.append(")");
			sql = sqlBUF.toString();
			
			tmpList = this.excelFileMapper.selectExcelInfo(sql);
		}
		return retMap;
	}

	public HashMap handleAndSaveExcel2(byte[] bais, String orgFilename, String tableID, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {
		HashMap retMap = new HashMap();

		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
		String tableName = dictTModel.getName();

		ObjectMapper objectMapper = new ObjectMapper();
		HashMap notLockWbCellMap = new HashMap();
		XSSFWorkbook wb;
		wb = new XSSFWorkbook(new ByteArrayInputStream(bais));
		StylesTable styles = wb.getStylesSource();
		HashMap pxfMap = new HashMap();
		for (int i = 0; i < styles.getNumCellStyles(); i++) {
			XSSFCellStyle style1 = styles.getStyleAt(i);
			if (!style1.getLocked()) {
				pxfMap.put(i, "Locked_false");
			}
		}
		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			List notLockSheetCellList = new ArrayList();
			Sheet sheet = wb.getSheetAt(i);
			for (Row row : sheet) {
				for (Cell cell : row) {
					CellStyle cellstyle = cell.getCellStyle();
					short styleIdx = cellstyle.getIndex();
					String isProtect = (String) pxfMap.get(Integer.valueOf(styleIdx));
					if (isProtect != null) {
						XSSFCell xssfcell = (XSSFCell) cell;
						String data = xssfcell.getReference() + "/" + cell.getRowIndex() + "_" + cell.getColumnIndex();
						data = data + splitstr + cell.toString();
						notLockSheetCellList.add(data);
						switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC: {
								cell.setCellValue(0);
								break;
							}
							case Cell.CELL_TYPE_STRING: {
								cell.setCellValue("");
								break;
							}
							default: {
								cell.setCellValue("");
								cell.setCellType(3);
							}
						}
					}
				}
			}
			notLockWbCellMap.put(wb.getSheetName(i), notLockSheetCellList);
		}
		WorkbookAllCellsCalc(wb);
		Object sht = notLockWbCellMap.get(tableName);
		if (sht == null) {
			retMap.put("success", false);
			retMap.put("errorInfo", "Excel中没有找到sheet：\"" + tableName + "\" 的工作表！\r\n模板表！");
			return retMap;
		}
		String datajson = objectMapper.writeValueAsString(notLockWbCellMap);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] filenull = baos.toByteArray();
		logger.debug("excel模板文件大小:" + filenull.length);
		ExcelFilePO epo = new ExcelFilePO();
		epo.setTableid(tableID);
		epo.setName(tableName);
		epo.setOriginalxlsxname(orgFilename);
		epo.setOriginalxlsxtpl(bais);
		epo.setNullxlsxtpl(filenull);
		epo.setDatapartxlsx(datajson);
		epo.setStylepartxlsx("");
		HashMap relationTableidMap = (HashMap) request.getAttribute("relationTableidMap");
		if (relationTableidMap != null) {
			HashMap allTabsMap = (HashMap) relationTableidMap.get(tableID);
			if (allTabsMap != null && allTabsMap.size() > 1) {
				ObjectMapper objectMapper1 = new ObjectMapper();
				String json = objectMapper1.writeValueAsString(allTabsMap);
				epo.setRelationjsondata(json);
				//HashMap relationTableidMapCache = (HashMap) BasePoiCacheMap.get("relationTableidMap");
				//relationTableidMapCache.put(tableID, allTabsMap);
			}
		}

		HashMap paraMap = new HashMap();
		String sql = " select count(*) cc from DICT_T_MODELEXCEL t where t.tableID ='" + tableID + "'";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		BigDecimal tmpBig = (BigDecimal) tmpMap.get("CC");
		if (tmpBig.intValue() < 1){
			this.excelFileMapper.insertXlsx(epo);
		}else{
			this.excelFileMapper.updateXlsx(epo);
		}
		retMap.put("excelDataTemplate", filenull);
		retMap.put("success", true);
		retMap.put("errorInfo", "上传Excel模板文件成功!");

		return retMap;
	}

	public LinkedHashMap getElementCodeName(HashMap codeMap, String tableID, String dbColumnName, boolean id_codename) {
		LinkedHashMap itemidMap = new LinkedHashMap();
		List elementtableList = getElementTablesFromCache(codeMap, tableID, dbColumnName);
		if (elementtableList == null || elementtableList.size() == 0){
			return itemidMap;
		}
		for (int i = 0; i < elementtableList.size(); i++) {
			HashMap rowMap = (HashMap) elementtableList.get(i);
			String itemid = (String) rowMap.get("GUID");
			String codeName = (String) rowMap.get("CODENAME");
			if (id_codename)
				itemidMap.put(itemid, codeName);
			else
				itemidMap.put(codeName, itemid);
		}
		return itemidMap;

	}
	
	public List getElementTablesFromCache(HashMap codeMap, String tableID, String dbColumnName) {
		String sql = "";
		HashMap colname2eleTableNameMap = (HashMap) codeMap.get("colname2eleTableNameMap");
		if (colname2eleTableNameMap == null) {
			return null;
		}
		String elementTable = (String) colname2eleTableNameMap.get(tableID + "_" + dbColumnName);
		if (StringUtils.isEmpty(elementTable)){
			return null;
		}
		HashMap elementTablesMap = (HashMap) codeMap.get("elementTablesMap");
		if (elementTablesMap == null) {
			return null;
		}
		List elementtableList = (List) elementTablesMap.get(elementTable);
		if (elementtableList == null) {
			return null;
		}
		return elementtableList;
	}

	public LinkedHashMap getElementCodeName(HashMap codeMap, String tableID, String dbColumnName, boolean id_codename, String keyType) {
		LinkedHashMap itemidMap = new LinkedHashMap();
		List elementtableList = getElementTablesFromCache(codeMap, tableID, dbColumnName);
		if (elementtableList == null || elementtableList.size() == 0){
			return itemidMap;
		}
		for (int i = 0; i < elementtableList.size(); i++) {
			HashMap rowMap = (HashMap) elementtableList.get(i);
			String itemid = (String) rowMap.get("GUID");
			String codeName = (String) rowMap.get("CODENAME");
			String code = (String) rowMap.get("CODE");
			String Name = (String) rowMap.get("NAME");
			if ("itemid".equalsIgnoreCase(keyType)) {
				itemidMap.put(itemid, codeName);
			} else if ("code-name".equalsIgnoreCase(keyType)) {
				itemidMap.put(codeName, itemid);
			} else if ("code".equalsIgnoreCase(keyType)) {
				itemidMap.put(code, itemid);
			} else if ("name".equalsIgnoreCase(keyType)) {
				itemidMap.put(Name, itemid);
			}
		}
		return itemidMap;

	}

	/*public LinkedHashMap getElementInfo(String tableID, String dbColumnName) {
		LinkedHashMap itemidMap = new LinkedHashMap();
		List elementtableList = getElementTablesFromCache(tableID, dbColumnName);
		if (elementtableList.size() == 0)
			return itemidMap;
		for (int i = 0; i < elementtableList.size(); i++) {
			HashMap rowMap = (HashMap) elementtableList.get(i);
			String itemid = (String) rowMap.get("GUID");
			String codeName = (String) rowMap.get("CODENAME");
			int isLeaf = 1;
			BigDecimal leaf = (BigDecimal) rowMap.get("ISLEAF");
			if (leaf != null)
				isLeaf = leaf.intValue();
			itemidMap.put(itemid, codeName + splitstr2 + isLeaf);
		}
		return itemidMap;

	}*/

	/*public LinkedHashMap getTableColsMap(String tableID) {
		HashMap paraMap = new HashMap();
		String sql = "select t.* from dict_t_factor t where t.tableid='" + tableID + "' order by orderid";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		if (tmpList.size() == 0)
			return null;
		LinkedHashMap ColsMap = new LinkedHashMap();
		int colnum = 0;
		for (int i = 0; i < tmpList.size(); i++) {
			HashMap rowMap = (HashMap) tmpList.get(i);
			String colname = (String) rowMap.get("DBCOLUMNNAME");
			String cellValue = (String) rowMap.get("NAME");
			String isvisible = (String) rowMap.get("ISVISIBLE");
			if ("1".equalsIgnoreCase(isvisible)) {
				String coluri = (String) rowMap.get("XLSXCOLUMNID");
				if (coluri == null) {
					coluri = CellReference.convertNumToColString(colnum);
					String columnid = (String) rowMap.get("COLUMNID");
					if (colname != null && !colname.equalsIgnoreCase("")) {
						sql = "merge into dict_t_factorxlsx a "
							+ " using (select '"
							+ columnid
							+ "' COLUMNID,'"
							+ tableID
							+ "' tableID,'"
							+ coluri.toUpperCase()
							+ "' xlsxcolumnid from dual) b "
							+ " ON (a.tableID = b.tableID and a.columnid = b.columnid) "
							+ " WHEN MATCHED THEN "
							+ "  update set xlsxcolumnid = b.xlsxcolumnid  where tableID = b.tableID and columnid = b.columnid "
							+ " WHEN NOT MATCHED THEN "
							+ "  INSERT (COLUMNID, tableID, xlsxcolumnid)  values  (b.columnid, b.tableID,b.xlsxcolumnid ) ";
						
						this.excelFileMapper.selectExcelInfo(sql);
					} else {
						coluri = null;
					}
				}
				colnum++;
				ColsMap.put(colname, coluri);

			}

		}
		return ColsMap;

	}*/

	public String generateSpace(int cc) {
		String retStr = "";
		for (int i = 0; i < cc; i++)
			retStr = retStr + " ";
		return retStr;
	}

	public void getFieldsinfoToExcel(ArrayList excelColsList, HashMap HeadMap, String colname, String cellValue) {
		String ISKEY = HeadMap.get("ISKEY") == null ? "" : (String) HeadMap.get("ISKEY");
		String DATATYPE = HeadMap.get("DATATYPE") == null ? "" : ((BigDecimal) HeadMap.get("DATATYPE")).toString();
		String ISSUM = HeadMap.get("ISSUM") == null ? "" : (String) HeadMap.get("ISSUM");
		String NULLABLE = HeadMap.get("NULLABLE") == null ? "" : (String) HeadMap.get("NULLABLE");
		String ISUPDATE = HeadMap.get("ISUPDATE") == null ? "" : (String) HeadMap.get("ISUPDATE");
		String ISVIRTUAL = HeadMap.get("ISVIRTUAL") == null ? "" : (String) HeadMap.get("ISVIRTUAL");
		String ISREGEX = HeadMap.get("ISREGEX") == null ? "" : (String) HeadMap.get("ISREGEX");
		String REGEXPR = HeadMap.get("REGEXPR") == null ? "" : (String) HeadMap.get("REGEXPR");
		String REGEXPRINFO = HeadMap.get("REGEXPRINFO") == null ? "" : (String) HeadMap.get("REGEXPRINFO");
		Integer COLUMNINDEX = HeadMap.get("COLUMNINDEX") == null ? 0 : (Integer) HeadMap.get("COLUMNINDEX");
		String ALIAS = HeadMap.get("ALIAS") == null ? "" : (String) HeadMap.get("ALIAS");
		String DATALENGTH = ((HeadMap.get("DATALENGTH") == null ? new BigDecimal(0) : (BigDecimal) HeadMap.get("DATALENGTH"))).toString();
		String PARENTNODECANCHECK = HeadMap.get("PARENTNODECANCHECK")== null ? "" : (String) HeadMap.get("PARENTNODECANCHECK");
		String OWNER = HeadMap.get("OWNER") == null ? "0" : (String) HeadMap.get("OWNER");
		String SCALE = ((HeadMap.get("SCALE") == null ? "0" : (BigDecimal) HeadMap.get("SCALE"))).toString();
		String SHOWFORMAT = HeadMap.get("SHOWFORMAT") == null ? "" : (String) HeadMap.get("SHOWFORMAT");
		String ORDERID = HeadMap.get("ORDERID") == null ? "" : ((BigDecimal) HeadMap.get("ORDERID")).toString();
		excelColsList.add(
				cellValue 
				+ splitstr + colname 
				+ splitstr + ISKEY
				+ splitstr + DATATYPE 
				+ splitstr + ISSUM 
				+ splitstr + NULLABLE
				+ splitstr + ISUPDATE 
				+ splitstr + ISVIRTUAL 
				+ splitstr + ISREGEX 
				+ splitstr + REGEXPR 
				+ splitstr + REGEXPRINFO
				+ splitstr + ALIAS 
				+ splitstr + DATALENGTH 
				+ splitstr + PARENTNODECANCHECK 
				+ splitstr + COLUMNINDEX
				+ splitstr + OWNER
				+ splitstr + SCALE
				+ splitstr + SHOWFORMAT
				+ splitstr + ORDERID
		);
	}

	public void setxCellValue(XSSFCell cell, Object cellvalue, HashMap cellStyleCacheMap) {
		if (cellvalue == null){
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			return;
		}

		String fieldtype = cellvalue.getClass().getName();
		if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")) {
			BigDecimal cv = new BigDecimal(0);
			if (fieldtype.equalsIgnoreCase("java.math.BigDecimal"))
				cv = (BigDecimal) cellvalue;
			cell.setCellValue(cv.doubleValue());
		} else {
			String cv = (String) cellvalue;
			XSSFCellStyle curcellStyle = cell.getCellStyle();
			if (curcellStyle.getLocked() == false) {
				long curXfID = cell.getCellStyle().getIndex();
				XSSFCellStyle textCellStyle = (XSSFCellStyle) cellStyleCacheMap.get(curXfID);
				if (textCellStyle == null) {
					textCellStyle = cell.getSheet().getWorkbook().createCellStyle();
					textCellStyle.cloneStyleFrom(cell.getCellStyle());
					textCellStyle.setDataFormat((short) BuiltinFormats.getBuiltinFormat("text")); // @
					cellStyleCacheMap.put(curXfID, textCellStyle);
				}
				cell.setCellStyle(textCellStyle);
			}
			//2016-08-19字符类型的数据，加入空格，在filldataGrid的setxCellValue方法中通过数据类型给单元格添加文本格式，然后去掉空格
			if(" ".equals(cv)){
				cv = "";
			}
			cell.setCellValue(cv);
		}
	}

	// 准备修改
	public void setxCellValue1(HashMap codeMap, String tableID, HashMap cacheMap, Cell cell,
			HashMap rowMap, String fieldn, String fieldcnName, String preSpace,
			HashMap cellStyleCacheMap) {
		HashMap fieldCodeM = null;
		Object cellO = null;
		if (fieldn != null) {
			cellO = rowMap.get(fieldn);
			fieldCodeM = (HashMap) cacheMap.get(tableID + "-" + fieldn + "-" + fieldcnName);
			if (fieldCodeM == null) {
				fieldCodeM = getElementCodeName(codeMap, tableID, fieldn, true);
				if (fieldCodeM != null){
					cacheMap.put(tableID + "-" + fieldn + "-" + fieldcnName, fieldCodeM);
				} else {
					fieldCodeM = new LinkedHashMap();
					cacheMap.put(tableID + "-" + fieldn + "-" + fieldcnName, fieldCodeM);
				}
			}
		}
		String cellType = ((BigDecimal) rowMap.get("DBFIELD_DATATYPE")).toString();
		if (cellO == null) {
			if (!"1".equalsIgnoreCase(cellType) && !"2".equalsIgnoreCase(cellType))
				setxCellValue((XSSFCell) cell, "", cellStyleCacheMap);
			else
				cell.setCellType(Cell.CELL_TYPE_BLANK);
			return;
		}
		String fieldtype = cellO.getClass().getName();
		if (fieldtype.equalsIgnoreCase("java.math.BigDecimal") || "1".equalsIgnoreCase(cellType) || "2".equalsIgnoreCase(cellType)) {
			BigDecimal cv = new BigDecimal(0);
			if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")){
				cv = (BigDecimal) cellO;
			}
			setxCellValue((XSSFCell) cell, cv, cellStyleCacheMap); 

		} else {
			String cv =  cellO.toString();
			if (fieldCodeM != null && fieldCodeM.size() > 0){
				cv = (String) fieldCodeM.get(cv);
			}
			if (preSpace.length() > 0){
				cv = preSpace + cv;
			}
			setxCellValue((XSSFCell) cell, cv, cellStyleCacheMap); 
		}
	}

	public HashMap getBaseNumberTableinfo(HashMap codeMap, String tableID, String phytableName) {
		HashMap<String, Map> baseNumberTableinfoMap = (HashMap<String, Map>) codeMap.get("baseNumberTableinfoMap");
		if (baseNumberTableinfoMap == null) {
			baseNumberTableinfoMap = new HashMap<String, Map>();
			codeMap.put("baseNumberTableinfoMap", baseNumberTableinfoMap);
		}
		HashMap<String, String> btnMap = (HashMap<String, String>) baseNumberTableinfoMap.get(tableID);
		if (btnMap == null) {
			btnMap = new HashMap();
			HashMap paraMap = new HashMap();
			String sql = "select  f.DBCOLUMNNAME,t.COLUMNID,f.datatype from dict_t_setbasenumtab t, dict_t_factor f where t.tableID =  '"
					+ tableID + "'   and t.COLUMNID = f.COLUMNID";
			
			List tmpList = this.excelFileMapper.selectExcelInfo(sql);
			if (tmpList.size() == 0)
				throw new RuntimeException(phytableName + ":基本数字表的配置有问题，dict_t_setbasenumtab，tableID= " + tableID);
			HashMap tmpMap = (HashMap) tmpList.get(0);
			String headField = (String) tmpMap.get("DBCOLUMNNAME");// "FINYEAR";
			String headFieldcolid = (String) tmpMap.get("COLUMNID");
			double headFieldDataType = ((BigDecimal) tmpMap.get("DATATYPE"))
					.doubleValue();
			sql = " select  t.COLVALUE years from dict_t_setbasenumsub t  where t.COLUMNID ='"
					+ headFieldcolid + "'";
			
			tmpList = this.excelFileMapper.selectExcelInfo(sql);
			String headFieldvals = "";
			for (int ie = 0; ie < tmpList.size(); ie++) {
				tmpMap = (HashMap) tmpList.get(ie);
				String tmpStr = (String) tmpMap.get("YEARS");
				if (headFieldDataType == 1 || headFieldDataType == 2)
					headFieldvals = headFieldvals + tmpStr + ",";
				else
					headFieldvals = headFieldvals + "'" + tmpStr + "',";
			}
			if ("".equals(headFieldvals)) {
				throw new RuntimeException(phytableName
						+ " 基本数字表的 横向扩展内容为空 dict_t_setbasenumsub！，tableID= "
						+ tableID);
			}
			headFieldvals = headFieldvals.substring(0,
					headFieldvals.length() - 1);

			btnMap.put("headField", headField);
			btnMap.put("headFieldvals", headFieldvals);
		}
		return btnMap;
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
	
	//生成模板
	public HashMap generateXlsxTemplate(HashMap codeMap, DictTModelPO dictTModel, String tableID, HttpServletRequest request) throws Exception {
		HashMap retMap = new HashMap();
		String dbTableName = dictTModel.getDbtablename();
		String sheetName = dictTModel.getName();
		HashMap paraMap = new HashMap();
		String sql = " select max(levelno) headrownum from DICT_T_FACTOR t where t.tableID ='" + tableID + "' and t.ISVISIBLE = '1'";
		
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		BigDecimal tmpBig = (BigDecimal) tmpMap.get("HEADROWNUM");
		int headrownum = tmpBig.intValue();
		
		ArrayList DisplayColsList = new ArrayList();
		ArrayList HideColsList = new ArrayList();
		ArrayList ExcelColsList = new ArrayList();
		HashMap xlsxRowsMap = new HashMap();
		HashMap xlsxColsMap = new HashMap();
		int Maxhidecolnum = 0;
		String firstHideFieldname = "TEMPLATEID";
		HashMap cacheMap = new HashMap();
		XSSFWorkbook wb = new XSSFWorkbook();
		HashMap cellStyleCacheMap = new HashMap(); // River修改20151230
		// 格式设置
		// title
		CellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 20);// 设置字体大小
		titleStyle.setFont(font);
		// head
		Font font2 = wb.createFont();
		font2.setFontName("宋体");
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);// 粗体显示
		font2.setFontHeightInPoints((short) 12);
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(font2);
		headStyle.setAlignment((short) (XSSFCellStyle.ALIGN_CENTER));
		headStyle.setVerticalAlignment((short) (XSSFCellStyle.VERTICAL_CENTER));
		headStyle.setWrapText(true);
		headStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		headStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		headStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		headStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
		// body
		Font font3 = wb.createFont();
		font3.setFontName("微软雅黑");
		font3.setFontHeightInPoints((short) 10);
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setFont(font3);
		bodyStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
		bodyStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
		bodyStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
		bodyStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框

		Font font4 = wb.createFont();
		font4.setFontName("宋体");
		font4.setFontHeightInPoints((short) 10);
		XSSFCellStyle bodyUnLockedStyle = wb.createCellStyle();
		bodyUnLockedStyle.cloneStyleFrom(bodyStyle);
		bodyUnLockedStyle.setFont(font4);
		bodyUnLockedStyle.setLocked(false);

		byte[] rgb = new byte[3];
		rgb[0] = (byte) 242; // 红 red
		rgb[1] = (byte) 242; // 绿 green
		rgb[2] = (byte) 242; // 蓝 blue
		XSSFColor color1 = new XSSFColor(rgb);
		XSSFColor color2 = new XSSFColor();
		color2.setTheme(9);
		color2.setTint(0.8);
		bodyUnLockedStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		bodyUnLockedStyle.setFillForegroundColor(color2);
		Sheet sheet = wb.createSheet(sheetName);
		Row xssfrow = sheet.createRow((short) 0);
		Cell cell = xssfrow.createCell((short) 0);
		cell.setCellValue(sheetName);
		cell.setCellStyle(titleStyle);

		xssfrow = sheet.createRow(headrownum);
		StringBuffer hideColsStrBuf = new StringBuffer();

		StringBuffer ExcelHideColsStrBuf = new StringBuffer();
		hideColsStrBuf.append(firstHideFieldname).append(splitstr);
		
		cell = xssfrow.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(headStyle);
		ArrayList titleHeadTotalList = new ArrayList();
		//StringBuffer batsqlBUF = new StringBuffer();
		List<DictTFactorPO> factorList = dictTFactorService.getDictTFactorsByTableIdForTree(tableID);
		List<DictTFactorPO> leafFactorList = new ArrayList<DictTFactorPO>();
		this.getLeafColumnList(leafFactorList, factorList);
		
		
		sql = "select * from dict_t_factor u where tableid = '"
			+ tableID
			+ "' start with (superid = '0' or superid is null or superid = '') "
			+ "connect by prior columnid = superid  "
			+ " order siblings by orderid";
		
		List headsList = this.excelFileMapper.selectExcelInfo(sql);
		
		//固定行列表处理
		String dealtype = getCurDealType();
		//List<DictTSetFixPO> fixFactorList = new ArrayList();
		List<Column> fixColumnList = new ArrayList();
		if("A1".equals(dealtype)){
			try {
	            //fixFactorList = entryOuterService.getDataFixList(tableID);
	            FixGrid fixGrid = new FixGrid();
	            fixGrid.setTableID(tableID);
	            String userID = SecureUtil.getCurrentUser().getGuid();
	            fixGridInputService.initStructure(fixGrid, userID);
	            fixColumnList = TableUtil.getLeafColumnList(fixGrid);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new ServiceException(ExceptionCode.ERR_00000, "加载固定行列表固定列设置出错!", false);
	        }
		}
		int colnum = 1;
		for (int i = 0; i < headsList.size(); i++) {
			HashMap HeadMap = (HashMap) headsList.get(i);
			String nullable = HeadMap.get("NULLABLE")==null ? "" :(String) HeadMap.get("NULLABLE");
			String isvisible = HeadMap.get("ISVISIBLE")==null ? "" :(String) HeadMap.get("ISVISIBLE");
			String columnid = HeadMap.get("COLUMNID")==null ? "" :(String) HeadMap.get("COLUMNID");
			String dbcolumnname = HeadMap.get("DBCOLUMNNAME")==null ? "" : (String) HeadMap.get("DBCOLUMNNAME");
			String name = HeadMap.get("NAME")==null ? "" :(String) HeadMap.get("NAME");
			String alias = HeadMap.get("ALIAS")==null ? "" :(String) HeadMap.get("ALIAS");
			String datatype = HeadMap.get("DATATYPE") == null ? "" : ((BigDecimal) HeadMap.get("DATATYPE")).toString();
        	String issum = HeadMap.get("ISSUM") == null ? "" : (String) HeadMap.get("ISSUM");
			
			//处理COLUMNINDEX
			for (DictTFactorPO dictTFactorPO : leafFactorList) {
				int columnindex = dictTFactorPO.getColumnindex();
				if(columnid.equals(dictTFactorPO.getColumnid())){
					HeadMap.put("COLUMNINDEX", columnindex);
				}
			}
			
			//处理列名
			if (StringUtils.isNotEmpty(alias)){
				name = alias;
			}
			HeadMap.put("NAME", name);
			
			
			
			//列显示方式为C的文件列，在EXCEL中不能修改
			String showformat = HeadMap.get("SHOWFORMAT")==null ? "" :(String) HeadMap.get("SHOWFORMAT");
			if("C".equals(showformat)){
				HeadMap.put("ISUPDATE", "0");
			}
			//绑定列和虚列设为隐藏
			String isbandcol = HeadMap.get("ISBANDCOL")==null ? "" :(String) HeadMap.get("ISBANDCOL");//绑定列
			String isvirtual = HeadMap.get("ISVIRTUAL")==null ? "" :(String) HeadMap.get("ISVIRTUAL");//虚列
			if ("1".equals(isbandcol) || "1".equals(isvirtual)) {
				isvisible = "0";
            }
			// 一般录入报表 人员表
			if ("A0".equalsIgnoreCase(dealtype)||"A0*01".equalsIgnoreCase(dealtype)) {
				if (ExcelProgressBarUtil.getControlFields_A0().contains(dbcolumnname)) {
					isvisible = "0";
	            }
			}
			//wuxiaopeng 20160301 固定行列表处理
			String owner = "0";
			if("A1".equals(dealtype)){
	        	//控制列强制设为只读
				for (Iterator iterator = fixColumnList.iterator(); iterator.hasNext();) {
	            	FixColumn fixColumn = (FixColumn) iterator.next();
	            	if(fixColumn != null){
	            		if(columnid.equals(fixColumn.getColumnID())){
		            		owner = fixColumn.getOwnerType();
		            		if(fixColumn.getIsCtrl() == 1){
								HeadMap.put("ISUPDATE", "0");
							}
		            	}
	            	}
				}
	        	//数字列且owner不为0
        		if(("1".equals(datatype) || "2".equals(datatype)) && !"0".equals(owner)){
            		issum = "1";
            	}else{
            		issum = "0";
            	}
        		//参数列隐藏
				if(StringUtils.isNotEmpty(dbcolumnname)){
					if (ExcelProgressBarUtil.getControlFields_A1().contains(dbcolumnname.replaceAll("[_1,_2,_3]", ""))) {
						isvisible = "0";
		            }
				}
				
			}
			HeadMap.put("OWNER", owner);
			HeadMap.put("ISSUM", issum);
			
			//wuxiaopeng 20160301 浮动表处理
			if("A2".equals(dealtype)){
				if (ExcelProgressBarUtil.getControlFields_A2().contains(dbcolumnname)) {
					isvisible = "0";
	            }
			}
			HeadMap.put("ISVISIBLE", isvisible);
			
			if ("1".equalsIgnoreCase(isvisible) ) {
				if (firstHideFieldname.equalsIgnoreCase(dbcolumnname)) {
					hideColsStrBuf = hideColsStrBuf.replace(0, firstHideFieldname.length() + splitstr.length(), "");
				}
				String isLeaf = HeadMap.get("ISLEAF")==null ? "" :(String) HeadMap.get("ISLEAF");
				if ("0".equalsIgnoreCase(isLeaf)) {
					titleHeadTotalList.add(HeadMap);
					continue;
				}
				cell = xssfrow.createCell(colnum);
				cell.setCellValue(name);
				if ("0".equalsIgnoreCase(nullable)) {
					cell.setCellValue(name + fieldIsNullMark);
				}
				cell.setCellStyle(headStyle);
				String coluri = CellReference.convertNumToColString(colnum);

				getFieldsinfoToExcel(ExcelColsList, HeadMap, dbcolumnname, name);
				xlsxColsMap.put(dbcolumnname, coluri);
//				if (dbcolumnname != null && !dbcolumnname.equalsIgnoreCase("")) {
//					batsqlBUF.append("select '" + columnid + "' columnid,'" + tableID + "' tableid, '" + coluri.toUpperCase() + "' xlsxcolumnid from dual ");
//					batsqlBUF.append("union all ");
//				}
				colnum++;
				DisplayColsList.add(HeadMap);
			} else {
//				if (dbcolumnname != null && !dbcolumnname.equalsIgnoreCase("")) {
//					batsqlBUF.append("select '" + columnid + "' columnid,'" + tableID + "' tableid, null xlsxcolumnid from dual ");
//					batsqlBUF.append("union all ");
//				}
				ExcelHideColsStrBuf.append(name + splitstr2 + dbcolumnname).append(splitstr);
				if (firstHideFieldname.equalsIgnoreCase(dbcolumnname)) {
					continue;
				}
				hideColsStrBuf.append(dbcolumnname).append(splitstr);
				HideColsList.add(HeadMap);
			}
		}
		//int lastPos = batsqlBUF.lastIndexOf("union all");
		//batsqlBUF.replace(lastPos, lastPos + 9, "");
		//String batsqlBufStr = batsqlBUF.toString();
		//先改后增
		//sql = "update dict_t_factorxlsx a set (columnid, tableid, xlsxcolumnid) = (select b.columnid, b.tableid, b.xlsxcolumnid from (" + batsqlBufStr + ") b where a.columnid = b.columnid and a.tableid = b.tableid) where exists (select 1 from ("+batsqlBufStr+") t where t.columnid = a.columnid and t.tableid = a.tableid)";
		//this.excelFileMapper.updateSql(sql);
		//sql = "insert into dict_t_factorxlsx a (columnid, tableid, xlsxcolumnid) (select b.columnid, b.tableid, b.xlsxcolumnid from (" + batsqlBufStr + ") b where not exists (select 1 from dict_t_factorxlsx t where t.columnid = b.columnid and t.tableid = b.tableid))";
		//this.excelFileMapper.updateSql(sql);
		//merge into
		/*sql = "merge into dict_t_factorxlsx a using (" + batsqlBufStr + ") b "
				+ " on (a.tableid = b.tableid and a.columnid = b.columnid) "
				+ " when matched then "
				+ " update set xlsxcolumnid = b.xlsxcolumnid where tableid = b.tableid and columnid = b.columnid "
				+ " when not matched then "
				+ " insert (columnid, tableid, xlsxcolumnid) values (b.columnid, b.tableid, b.xlsxcolumnid) ";
		this.excelFileMapper.updateSql(sql);*/
		
		for (int i = 0; i < titleHeadTotalList.size(); i++) {
			HashMap headMap = (HashMap) titleHeadTotalList.get(i);
			String columnid = (String) headMap.get("COLUMNID");
			String cellStr = (String) headMap.get("NAME");
			int levelHead = ((BigDecimal) headMap.get("LEVELNO")).intValue();

			sql = "select t2.XLSXCOLUMNID "
					+ "from DICT_T_FACTOR t, dict_t_factorxlsx t2 "
					+ "where t.tableID = t2.tableID(+) "
					+ " and t.columnid = t2.columnid(+) "
					+ " and t.tableID = '" + tableID + "' "
					+ " and t2.XLSXCOLUMNID is not null "
					+ " START WITH (t.SUPERID = '" + columnid + "') "
					+ " CONNECT BY PRIOR t.COLUMNID = t.SUPERID "
					+ " ORDER SIBLINGS BY t.ORDERID, t.COLUMNID ";
			
			tmpList = this.excelFileMapper.selectExcelInfo(sql);
			if (tmpList != null && tmpList.size() > 0) {
				HashMap tmpMap1 = (HashMap) tmpList.get(0);
				String startCell = (String) tmpMap1.get("XLSXCOLUMNID");
				int istartcol = CellReference.convertColStringToIndex(startCell);
				tmpMap1 = (HashMap) tmpList.get(tmpList.size() - 1);
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
				CellRangeAddress region1 = new CellRangeAddress(levelHead,
						levelHead, istartcol, endcol);
				prepareCellmergeRegion(sheet, region1);
				sheet.addMergedRegion(region1);
				RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1,
						sheet, wb);
				RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_THIN, region1,
						sheet, wb);
				RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1,
						sheet, wb);
				RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1,
						sheet, wb);
			}
		}
		if (titleHeadTotalList.size() > 0) {

			for (int i = 0; i < DisplayColsList.size() + 1; i++) {
				int headstartRow = 1;
				while (headstartRow < headrownum) {
					Row row = sheet.getRow(headstartRow);
					Cell cell2 = row.getCell(i);
					if (cell2 == null) {
						cell2 = row.createCell(i);
						Cell cell3 = sheet.getRow(headrownum).getCell(i);
						cell2.setCellValue(cell3.getStringCellValue());
						cell2.setCellStyle(headStyle);
						sheet.getRow(headrownum).removeCell(cell3);
						CellRangeAddress region1 = new CellRangeAddress(
								headstartRow, headrownum, i, i);
						prepareCellmergeRegion(sheet, region1);
						sheet.addMergedRegion(region1);
						RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN,
								region1, sheet, wb);
						RegionUtil.setBorderLeft(XSSFCellStyle.BORDER_THIN,
								region1, sheet, wb);
						RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN,
								region1, sheet, wb);
						RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN,
								region1, sheet, wb);

						headstartRow = headrownum;
					}
					headstartRow++;
				}
			}
		}
		if (Maxhidecolnum <= DisplayColsList.size() + 1){
			Maxhidecolnum = DisplayColsList.size() + 1;
		}
		cell = xssfrow.createCell(Maxhidecolnum);
		cell.setCellValue(hideColsStrBuf.toString());
		ExcelColsList.add(ExcelHideColsStrBuf.toString());

		// 表格分类
		String LevelTable = "";
		HashMap tableLevelInfoMap = GetTableLevelInfo(tableID);
		HashMap paramMap1 = new HashMap();
		dealTypeTableSql(dbTableName, paramMap1);

		sql = (String) paramMap1.get("sql");
		String isExistsLevelCols = ((String) paramMap1.get("isExistsLevelCols") == null ? "0" : (String) paramMap1.get("isExistsLevelCols"));
		
		List dataList = this.excelFileMapper.selectExcelInfo(sql);
		int xlsxRowStart = headrownum + 1;
		// 准备修改A0
		if (this.getClass().equals(CommonPoiService.class) && dataList.size() == 0) {// 一般录入表生成一行没有实例数据的空模版
			int iR = 0;
			xssfrow = sheet.createRow(iR + xlsxRowStart);
			colnum = 1;
			cell = xssfrow.createCell(0);
			cell.setCellValue("" + (iR + 1));
			cell.setCellStyle(bodyStyle);

			for (int ic = 0; ic < DisplayColsList.size(); ic++) {
				cell = xssfrow.createCell(colnum);
				HashMap headm = (HashMap) DisplayColsList.get(ic);
				String fieldn = (String) headm.get("DBCOLUMNNAME");
				String preSpace = "";

				String fieldcnName = (String) headm.get("NAME");

				HashMap fieldCodeM = null;
				Object cellO = null;
				if (fieldn == null)
					cellO = "";
				if (cellO == null)
					cellO = "";
				String fieldtype = cellO.getClass().getName();
				String cellType = ((BigDecimal) headm.get("DATATYPE"))
						.toString();
				if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")
						|| "1".equalsIgnoreCase(cellType)
						|| "2".equalsIgnoreCase(cellType)) {
					BigDecimal cv = new BigDecimal(0);
					if (fieldtype.equalsIgnoreCase("java.math.BigDecimal"))
						cv = (BigDecimal) cellO;
					setxCellValue((XSSFCell) cell, cv, cellStyleCacheMap); // River修改20151230

				} else {
					String cv = (String) cellO;
					if (fieldCodeM != null && fieldCodeM.size() > 0)
						cv = (String) fieldCodeM.get(cv);
					if (preSpace.length() > 0)
						cv = preSpace + cv;
					setxCellValue((XSSFCell) cell, cv, cellStyleCacheMap); // River修改20151230

				}

				colnum++;
				String isupdate = (String) headm.get("ISUPDATE");
				String ISVIRTUAL = (String) headm.get("ISVIRTUAL");
				if ("1".equalsIgnoreCase(ISVIRTUAL)
						|| "0".equalsIgnoreCase(isupdate))
					cell.setCellStyle(bodyStyle);
				else
					cell.setCellStyle(bodyUnLockedStyle);
			}
			cell = xssfrow.createCell(Maxhidecolnum);

			cell.setCellValue("");

		}
		if (this.getClass().equals(FloatPoiService.class) && dataList.size() == 0) {
			throw new RuntimeException(dbTableName + ":浮动表的 templateid为空或记录为空！");
		}
		for (int iR = 0; iR < dataList.size(); iR++) {
			xssfrow = sheet.createRow(iR + xlsxRowStart);
			HashMap rowMap = (HashMap) dataList.get(iR);
			colnum = 1;
			cell = xssfrow.createCell(0);
			cell.setCellValue("" + (iR + 1));
			cell.setCellStyle(bodyStyle);

			for (int ic = 0; ic < DisplayColsList.size(); ic++) {
				cell = xssfrow.createCell(colnum);
				HashMap headm = (HashMap) DisplayColsList.get(ic);
				String fieldn = (String) headm.get("DBCOLUMNNAME");
				String preSpace = "";
				if ("1".equalsIgnoreCase(isExistsLevelCols)) {
					String levelField = (String) tableLevelInfoMap.get(fieldn);
					if (levelField != null) {
						Object o = rowMap.get(levelField);
						int levelcc = 0;
						if (o == null) {
							levelcc = 0;
						} else if (o.getClass().getName()
								.endsWith("BigDecimal")) {
							BigDecimal lv = (BigDecimal) o;
							levelcc = (lv == null ? 0 : lv.intValue());
						} else if (o.getClass().getName().endsWith("String")) {
							levelcc = Integer.valueOf((String) o);
						}
						preSpace = generateSpace((levelcc - 1) * 4);
					}
				}

				String fieldcnName = (String) headm.get("NAME");
				rowMap.put("DBFIELD_DATATYPE", headm.get("DATATYPE"));

				colnum++;
				String isupdate = (String) headm.get("ISUPDATE");
				String ISVIRTUAL = (String) headm.get("ISVIRTUAL");
				if ("1".equalsIgnoreCase(ISVIRTUAL) || "0".equalsIgnoreCase(isupdate)){
					cell.setCellStyle(bodyStyle);
				}else{
					cell.setCellStyle(bodyUnLockedStyle);
				}
				setxCellValue1(codeMap, tableID, cacheMap, cell, rowMap, fieldn, fieldcnName, preSpace, cellStyleCacheMap);
			}
			cell = xssfrow.createCell(Maxhidecolnum);
			StringBuffer hidedataStrBuf = new StringBuffer();
			String datakey = (String) rowMap.get(firstHideFieldname);
			xlsxRowsMap.put(datakey, "" + (iR + xlsxRowStart + 1));
			hidedataStrBuf.append(datakey).append(splitstr);
			for (int ic2 = 0; ic2 < HideColsList.size(); ic2++) {
				HashMap headm = (HashMap) HideColsList.get(ic2);
				String fieldn = (String) headm.get("DBCOLUMNNAME");
				if (firstHideFieldname.equalsIgnoreCase(fieldn)) {
					continue;
				}
				Object cellO = rowMap.get(fieldn);
				if (cellO == null)
					cellO = "";
				hidedataStrBuf.append(cellO.toString()).append(splitstr);
			}
			cell.setCellValue(hidedataStrBuf.toString());

		}
		
		if("A2".equals(dealtype)){
			//logger.debug("");
			String grid = request.getParameter("grid");
			FloatGrid localFloatGrid = (FloatGrid) new com.tjhq.commons.jackson.ObjectMapper().readValue(grid, FloatGrid.class);
			PageInfo dataPageInfo1 = (PageInfo) this.floatGridInputService.getData(localFloatGrid);
			int templateidIndex = 0;
	        if (dataPageInfo1.getColumns().size() > 0) {
	        	templateidIndex = dataPageInfo1.getColumns().indexOf("TEMPLATEID");
	        } 
	        List<Object> tempDataList = null;
	        String templateid = null;
	        List<Object> dataList2 = dataPageInfo1.getDataList();
	        
	        for (int i = 0; i < dataList2.size(); i++) {
				Object data = dataList2.get(i);
	        	tempDataList = (List<Object>) data;
	        	templateid = (String) tempDataList.get(templateidIndex);
	        	xlsxRowsMap.put(templateid, "" + (i + xlsxRowStart + 1));
	        }
		}
		
		int dataMaxColnum = DisplayColsList.size();
		CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, dataMaxColnum); // 准备单元格合并
		prepareCellmergeRegion(sheet, region1);
		sheet.addMergedRegion(region1);
		for (int ic = 0; ic < DisplayColsList.size(); ic++) {
			HashMap headm = (HashMap) DisplayColsList.get(ic);
			BigDecimal width = (BigDecimal) headm.get("SHOWWIDTH");
			if (width != null) {
				int wth = width.divide(new BigDecimal(10)).intValue() * 256;
				sheet.setColumnWidth(ic + 1, wth);
			}
		}
		// Maxhidecolnum
		sheet.setColumnHidden(Maxhidecolnum, true);
		
		HashMap xlsxRowColMap = new HashMap();
		xlsxRowColMap.put("rows", xlsxRowsMap);
		xlsxRowColMap.put("cols", xlsxColsMap);
		
		//不导出隐藏sheet
		String noHiddenSheet = request.getParameter("noHiddenSheet");
		if(StringUtils.isEmpty(noHiddenSheet) || !"1".equals(noHiddenSheet)){
			sheet.protectSheet(worksheetProtectPassword);//保护工作表，导出只读数据时不保护工作表
			HashMap resultMap = generateHideConfigSheet(codeMap, dictTModel, wb, ExcelColsList, xlsxRowColMap, cacheMap, headrownum, request);
			retMap.put("errorInfo", resultMap.get("errorInfo"));
			retMap.put("successInfo", resultMap.get("successInfo"));
			retMap.put("success", resultMap.get("success"));
			if (retMap.get("success") == null || !(Boolean) retMap.get("success")) {
				return retMap;
			}
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] databytes = baos.toByteArray();
		HashMap ret2 = handleAndSaveExcel2(databytes, sheetName, tableID,
				request);
		retMap.put("excelDataTemplate", ret2.get("excelDataTemplate"));

		retMap.put("success", true);
		return retMap;

	}

	public void prepareCellmergeRegion(Sheet sheet, CellRangeAddress region1) {
		HashMap cellStyleCacheMap = new HashMap();
		for (int ir = region1.getFirstRow(); ir <= region1.getLastRow(); ir++) {
			Row rw = sheet.getRow(ir);
			if (rw == null) {
				throw new IllegalStateException(sheet.getSheetName()
						+ ":合并单元格的" + ir + "行为空！请进入菜单-录入表定义，检查列与上级标题列的可见选项，保持一致。");
			}
			for (int ic = region1.getFirstColumn(); ic <= region1
					.getLastColumn(); ic++) {
				Cell cell = rw.getCell(ic);
				if (cell == null) {
					cell = rw.createCell(ic);
					long curXfID = cell.getCellStyle().getIndex();
					XSSFCellStyle regionCellStyle = (XSSFCellStyle) cellStyleCacheMap
							.get(curXfID);
					if (regionCellStyle == null) {
						regionCellStyle = (XSSFCellStyle) cell.getSheet()
								.getWorkbook().createCellStyle();
						regionCellStyle.cloneStyleFrom(cell.getCellStyle());
						regionCellStyle
								.setBorderBottom(XSSFCellStyle.BORDER_THIN);
						regionCellStyle
								.setBorderLeft(XSSFCellStyle.BORDER_THIN);
						regionCellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
						regionCellStyle
								.setBorderRight(XSSFCellStyle.BORDER_THIN);
						cellStyleCacheMap.put(curXfID, regionCellStyle);
					}
					cell.setCellStyle(regionCellStyle);
				}

			}
		}
	}

	/**
	 * 隐藏sheet信息处理
	 */
	public HashMap generateHideConfigSheet(HashMap codeMap, DictTModelPO dictTModel, Workbook wb, List ExcelColsList, HashMap xlsxRowColMap, HashMap cacheMap, int headrownum, HttpServletRequest request) throws Exception {
		HashMap retMap = new HashMap();
		String tableID = dictTModel.getTableid();
		String NAME = dictTModel.getName();
		String sql = "select GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('DIVID') divid,GLOBAL_MULTYEAR_CZ.SECU_F_GLOBAL_PARM('YEAR') year,SYSDATE DBVERSION  from dual";
		HashMap paraMap = new HashMap();
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		String division = (String) tmpMap.get("DIVID");
		String year = (String) tmpMap.get("YEAR");

		int r = 0;
		
		Sheet sheetConfig = wb.createSheet(hideConfigSheetName);
		Row xssfrowconfig = sheetConfig.createRow((short) r);
		/**
		 * 隐藏sheet中的第一行的意义：
		 * A1:数据结束行,
		 * B1:数据开始行,
		 * C1:区划,
		 * D1:财年,
		 * E1:单位code-[code]name,
		 * F1:agencyID,
		 * G1:导出日期时间,
		 * H1:浮动表控制列dbcolumnname,
		 * I1:导出的是否为通用模板,
		 * AD1:删除数据所在单元格数，EXCEL单元格最大限制是32767，所以删除的datakey往后依次记录在AD1往右的单元格。
		 */
		Cell firstCell = xssfrowconfig.createCell((short) 0); // 数据结束行
		Cell secordCell = xssfrowconfig.createCell((short) 1); // 数据开始行
		secordCell.setCellValue(3);
		Cell thridCell = xssfrowconfig.createCell((short) 2); // 区划
		thridCell.setCellValue(division);
		thridCell = xssfrowconfig.createCell((short) 3); // 财年
		thridCell.setCellValue(year);
		Cell fourCell = xssfrowconfig.createCell((short) 4); // 单位code
		fourCell.setCellValue("单位编码");
		Cell fiveCell = xssfrowconfig.createCell((short) 5); // 单位ID
		String agencyID = request.getParameter("agencyID");
		fiveCell.setCellValue(agencyID);
		Cell sixCell = xssfrowconfig.createCell((short) 6); // 导出日期时间
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String dt = df.format(now);
		sixCell.setCellValue(dt);
		Cell cellconfig = xssfrowconfig.createCell((short) 15);
		cellconfig.setCellValue("");
		
		//20160303 wuxiaopeng 浮动表控制列
		DictTSetFddefPO dictTSetFddefPO = null;
		if("A2".equals(getCurDealType())){
			dictTSetFddefPO = entryOuterService.getDataFddefList(tableID);
			if (dictTSetFddefPO != null && dictTSetFddefPO.getIsFix() != null && dictTSetFddefPO.getLvlNanmeCol() != null) {
				DictTFactorPO levelNameColPO = dictTFactorService.getDictTFactorByColumnId(dictTSetFddefPO.getLvlNanmeCol());
				Cell sevenCell = xssfrowconfig.createCell((short) 7);
				sevenCell.setCellValue(levelNameColPO.getDbcolumnname());
			}
		}
		
		//是否导出通用录入模板  是：isCommon==1（代表点击业务表管理导出通用模板，可以通过文件名上的单位信息在数据填报页面导入） ，否： isCommon==其他（代表通过点击单位导出的模板，可以在通用录入页面导出导入）
		String isCommon = request.getParameter("isCommon");
		Cell cell_I1 = xssfrowconfig.createCell((short) 8);
		if(StringUtils.isNotEmpty(isCommon) && "1".equals(isCommon)){
			cell_I1.setCellValue("1");
		}else{
			cell_I1.setCellValue("0");
		}
		
		//删除数据记录单元格数默认值1
		xssfrowconfig.createCell((short) 29).setCellValue("1");
		
		r++;
		firstCell.setCellValue(r + 1);
		xssfrowconfig = sheetConfig.createRow(r);
		cellconfig = xssfrowconfig.createCell((short) 0);
		cellconfig.setCellValue("数据类型");
		cellconfig = xssfrowconfig.createCell((short) 1);
		cellconfig.setCellValue("数据地址集");
		cellconfig = xssfrowconfig.createCell((short) 2);
		cellconfig.setCellValue("sheetname");
		cellconfig = xssfrowconfig.createCell((short) 3);
		cellconfig.setCellValue("GUID");
		cellconfig = xssfrowconfig.createCell((short) 4);
		cellconfig.setCellValue("保留1");
		cellconfig = xssfrowconfig.createCell((short) 5);
		cellconfig.setCellValue("保留2");
		cellconfig = xssfrowconfig.createCell((short) 6);
		cellconfig.setCellValue("保留3");
		cellconfig = xssfrowconfig.createCell((short) 7);
		cellconfig.setCellValue("保留4");
		cellconfig = xssfrowconfig.createCell((short) 8);
		cellconfig.setCellValue("保留5");
		cellconfig = xssfrowconfig.createCell((short) 9);
		cellconfig.setCellValue("保留6");
		cellconfig = xssfrowconfig.createCell((short) 10);
		cellconfig.setCellValue("数据开始");
		r++;
		firstCell.setCellValue(r + 1);
		xssfrowconfig = sheetConfig.createRow(r);
		cellconfig = xssfrowconfig.createCell((short) 0);
		cellconfig.setCellValue("01-表定义"); // 数据类型
		cellconfig = xssfrowconfig.createCell((short) 2);
		cellconfig.setCellValue(NAME);
		cellconfig = xssfrowconfig.createCell((short) 3);
		cellconfig.setCellValue(tableID);
		cellconfig = xssfrowconfig.createCell((short) 4);
		if (this.getClass().equals(BaseNumberPoiService.class)) {
			String extFieldVals = (String) codeMap.get("ext_FieldValues");
			cellconfig.setCellValue(extFieldVals + splitstr + "数字表表头横向扩展的值集（行数）"); // 数据开始行数
		} else {
			cellconfig.setCellValue(headrownum + splitstr + "表头占的行数"); // 数据开始行数
		}
		//String tabdbVersion = getOracleTimestamp(tableMap.get("DBVERSION")).toString();
		//cellconfig = xssfrowconfig.createCell((short) 5);
		//cellconfig.setCellValue(tabdbVersion + splitstr + "表dbVersion"); // 数据开始行数

		int SumHeadCount = getCommonGridSumFieldcount(tableID);
		cellconfig = xssfrowconfig.createCell((short) 7);
		cellconfig.setCellValue(SumHeadCount + splitstr + "待汇总的字段数");

		cellconfig = xssfrowconfig.createCell((short) 8);
		cellconfig.setCellValue(getCurDealType() + splitstr + "表处理类型"); // 表类型

		String fieldProperty = "NAME" 
			+ splitstr + "DBCOLUMNNAME" 
			+ splitstr + "ISKEY" 
			+ splitstr + "DATATYPE" 
			+ splitstr + "ISSUM"
			+ splitstr + "NULLABLE" 
			+ splitstr + "ISUPDATE" 
			+ splitstr + "ISVIRTUAL" 
			+ splitstr + "ISREGEX" 
			+ splitstr + "REGEXPR"
			+ splitstr + "REGEXPRINFO" 
			+ splitstr + "ALIAS" 
			+ splitstr + "DATALENGTH" 
			+ splitstr + "PARENTNODECANCHECK" 
			+ splitstr + "COLUMNINDEX"
			+ splitstr + "OWNER"
			+ splitstr + "SCALE"
			+ splitstr + "SHOWFORMAT"
			+ splitstr + "ORDERID"
			;

		cellconfig = xssfrowconfig.createCell((short) 9);
		cellconfig.setCellValue(fieldProperty); // 表字段属性定义列表

		int colnum = 0;
		for (int iL = 0; iL < ExcelColsList.size(); iL++) {
			String cv = (String) ExcelColsList.get(iL);
			cellconfig = xssfrowconfig.createCell(iL + 10);
			cellconfig.setCellValue(cv);
		}
		cellconfig = xssfrowconfig.createCell((short) 1);
		cellconfig.setCellValue("10:" + (ExcelColsList.size() + 9));
		// 导出公式
		HashMap xlsxRowsMap = (HashMap) xlsxRowColMap.get("rows");
		HashMap xlsxColsMap = (HashMap) xlsxRowColMap.get("cols");
		HashMap elementColsCacheMap = new HashMap();
		elementColsCacheMap.put(tableID, xlsxColsMap);
		paraMap = new HashMap();
		sql = "select * from formula_t_formuladef t  where t.tableid ='" + tableID + "' order by orderid";
		
		List dataList = this.excelFileMapper.selectExcelInfo(sql);
		for (int i = 0; i < dataList.size(); i++) {
			HashMap rowMap = (HashMap) dataList.get(i);
			String formulaType = (String) rowMap.get("FORMULATYPE");
			r++;
			firstCell.setCellValue(r + 1);
			xssfrowconfig = sheetConfig.createRow(r);
			cellconfig = xssfrowconfig.createCell((short) 0);
			cellconfig.setCellValue("02-公式"); // 数据类型
			cellconfig = xssfrowconfig.createCell((short) 2);
			cellconfig.setCellValue(NAME);
			String FORMULAID = (String) rowMap.get("FORMULAID");
			cellconfig = xssfrowconfig.createCell((short) 3);
			cellconfig.setCellValue(FORMULAID);
			Iterator it1 = rowMap.keySet().iterator();
			colnum = 10;
			StringBuffer fieldsbf = new StringBuffer();
			String formulaDef = "";
			while (it1.hasNext()) {
				String key = (String) it1.next();
				fieldsbf.append(key).append(splitstr);
				Object obj = rowMap.get(key);
				String fieldv = "";
				if (obj != null) {
					String objtype = obj.getClass().getSimpleName();
					if (objtype.endsWith("String")) {
						fieldv = (String) obj;

					} else if (objtype.endsWith("Clob_oracle_sql_CLOB")) {
						java.sql.Clob clob = (java.sql.Clob) obj;
						fieldv = (clob == null || clob.length() == 0) ? ""
								: clob.getSubString(1, (int) clob.length());
					} else {
						fieldv = String.valueOf(obj);
					}
				}
				cellconfig = xssfrowconfig.createCell(colnum);
				cellconfig.setCellValue(fieldv);
				colnum++;
				if (key.equalsIgnoreCase("FORMULADEF")) {
					formulaDef = fieldv;
				}
			}
			colnum--;
			cellconfig = xssfrowconfig.createCell((short) 1);
			cellconfig.setCellValue("10:" + (colnum));
			cellconfig = xssfrowconfig.createCell((short) 9);
			cellconfig.setCellValue(fieldsbf.toString());
			cellconfig = xssfrowconfig.createCell((short) 8);
			if ("1".equalsIgnoreCase(formulaType)) // 行公式
			{
				cellconfig.setCellValue("1-行公式");
				String regex = "(〖TEMPLATEID\\=\\'(\\w+?)\\'?〗\\{(\\w+?)?\\})";
				Pattern ptn = Pattern.compile(regex);
				Matcher m = ptn.matcher(formulaDef);
				while (m.find()) {
					String dbcelladdr = m.group(1);
					String rowaddr = m.group(2);
					String coladdr = m.group(3);
					rowaddr = (String) xlsxRowsMap.get(rowaddr);
					coladdr = (String) xlsxColsMap.get(coladdr);
					formulaDef = formulaDef.replace(dbcelladdr, coladdr
							+ rowaddr);
				}
				cellconfig = xssfrowconfig.createCell((short) 4);
				cellconfig.setCellValue(formulaDef);
			} else if ("0".equalsIgnoreCase(formulaType)){
			
				cellconfig.setCellValue("0-列公式");
				String regex = "((TT\\((\\w+)\\)\\.〖(.*?)〗\\{)(.+?)\\})";
				HashMap allTabsMap = new HashMap();
				Pattern ptn = Pattern.compile(regex);
				Matcher m = ptn.matcher(formulaDef);
				while (m.find()) {
					String formula1 = m.group(5);
					String formula1tmp = formula1.toUpperCase();
					Pattern ppp = Pattern.compile("(?i)case\\s+when");
					Matcher mmm = ppp.matcher(formula1tmp);
					boolean bFind = mmm.find();
					if (bFind)
						formulaDef = formulaDeepProcess(codeMap, dictTModel, tableID, xlsxColsMap, formulaDef, allTabsMap, m, cacheMap);
					else
						formulaDef = formulaProcess(codeMap, dictTModel, tableID, xlsxColsMap, formulaDef, allTabsMap, m);
				}
				cellconfig = xssfrowconfig.createCell((short) 4);
				if (allTabsMap.size() == 1) {
					String k = (String) allTabsMap.keySet().iterator().next();
					String shtname1 = (String) allTabsMap.get(k);
					formulaDef = formulaDef.replace(shtname1 + "!", "");
				}
				cellconfig.setCellValue(formulaDef);

			} 
			/*else if ("1".equals(isExportTablebtnformula) && "8".equalsIgnoreCase(formulaType)) {
				cellconfig.setCellValue("8-表间公式");
				String regex = "((TT\\((\\w+)\\)\\.〖)(.*?)〗\\s*\\{(.+?)\\})";

				HashMap relationTableidMap = (HashMap) request
						.getAttribute("relationTableidMap");
				if (relationTableidMap == null) {
					relationTableidMap = new HashMap();
					request.setAttribute("relationTableidMap",
							relationTableidMap);
				}
				HashMap allTabsMap = (HashMap) relationTableidMap.get(tableID);
				if (allTabsMap == null) {
					allTabsMap = new HashMap();
					relationTableidMap.put(tableID, allTabsMap);
				}
				String tableName1 = dictTModel.getName();
				allTabsMap.put(tableID, tableName1);
				Pattern ptn = Pattern.compile(regex);
				Matcher m = ptn.matcher(formulaDef);
				int iaddLen = 0;
				while (m.find()) {
					int istart = 0;
					int ilen = 0;
					String target = "｛";
					String sourceStr1 = m.group(1);
					String strStart = m.group(2);
					String tabid1 = m.group(3);
					String condition1 = m.group(4);
					String formula1 = m.group(5);
					istart = m.start(4);
					ilen = m.group(4).length();
					dbMap1 = (HashMap) SelectModelTableCache(tabid1);
					tableName1 = (String) dbMap1.get("NAME");
					String dealType1 = (String) dbMap1.get("DEALTYPE");
					if (!allTabsMap.containsKey(tabid1)){
						allTabsMap.put(tabid1, tableName1);
					}
					HashMap curTableElementColsMap = (HashMap) elementColsCacheMap
							.get(tabid1);
					if (curTableElementColsMap == null) {
						curTableElementColsMap = getTableColsMap(tabid1);
						elementColsCacheMap.put(tabid1, curTableElementColsMap);
					}

					if (formula1.length() > 0) {
						String Regex_casewhen = "(?i)case\\s+when\\s+(.+)\\s+then\\s+(\\w+)\\s+else\\s+(\\w+)\\s+end(.*)";
						String Regex_Field = "([\\w|.]+)";
						String Regex_Count = "(?i)^COUNT\\((.+?)\\)$";
						String Regex_Sum = "(?i)^SUM\\((.+?)\\)$";
						int countR = 0;
						Pattern ptn1 = Pattern.compile(Regex_Count,
								Pattern.CASE_INSENSITIVE);
						Matcher m1 = ptn1.matcher(formula1);
						while (m1.find()) {
							istart = m1.start(0);
							ilen = m1.group(0).length();
							String count1 = m1.group(1);
							String target1 = tabBetweenFormulaProcess(tabid1,
									dealType1, curTableElementColsMap,
									allTabsMap, count1);
							target1 = "select count(" + target1 + ") from 【"
									+ tableName1 + "】";
							target = target + target1;
							countR++;
						}
						ptn1 = Pattern.compile(Regex_Sum,
								Pattern.CASE_INSENSITIVE);
						m1 = ptn1.matcher(formula1);
						while (m1.find()) {
							istart = m1.start(0);
							ilen = m1.group(0).length();
							String count1 = m1.group(1);
							String target1 = tabBetweenFormulaProcess(tabid1,
									dealType1, curTableElementColsMap,
									allTabsMap, count1);
							target1 = "select sum(" + target1 + ") from 【"
									+ tableName1 + "】";
							target = target + target1;
							countR++;
						}
						ptn1 = Pattern.compile(Regex_casewhen,
								Pattern.CASE_INSENSITIVE);
						m1 = ptn1.matcher(formula1);
						while (m1.find()) {
							istart = m1.start(0);
							ilen = m1.group(0).length();
							StringBuffer sbuf1 = new StringBuffer();
							String casewhen = m1.group(1);
							HashMap conditionMap = new HashMap();
							conditionMap.put("condition", condition1);
							String target1 = tabBetweenFormulaDeepProcess(
									tabid1, dealType1, curTableElementColsMap,
									allTabsMap, casewhen, conditionMap,
									cacheMap);
							sbuf1.append("select iif( ").append(target1)
									.append(" , ");
							String casethen = m1.group(2);
							String target2 = tabBetweenFormulaDeepProcess(
									tabid1, dealType1, curTableElementColsMap,
									allTabsMap, casethen, conditionMap,
									cacheMap);
							sbuf1.append(target2).append(" , ");
							String caseelse = m1.group(3);
							String target3 = tabBetweenFormulaDeepProcess(
									tabid1, dealType1, curTableElementColsMap,
									allTabsMap, caseelse, conditionMap,
									cacheMap);
							sbuf1.append(target3).append(" ) ").append(
									m1.group(4)).append(" from 【").append(
									tableName1).append("】 ");
							if ("62".equalsIgnoreCase(dealType1)) {
								String transposeCondition = (String) conditionMap
										.get("transposeCondition");
								sbuf1.append(transposeCondition);
							}
							target = target + sbuf1.toString();
							countR++;
						}
						if (countR == 0)
							target = tabBetweenFormulaProcess(tabid1,
									dealType1, curTableElementColsMap,
									allTabsMap, formula1);
					}
					if (condition1.length() > 0
							&& !"62".equalsIgnoreCase(dealType1)) {
						HashMap conditionMap = new HashMap();
						String target1 = tabBetweenFormulaDeepProcess(tabid1,
								dealType1, curTableElementColsMap, allTabsMap,
								condition1, conditionMap, cacheMap);
						target = target + " Where " + target1;

					}
					if (target.indexOf("｛") == 0)
						target = target + "｝";
					String regex11 = "^(.{" + (m.start(1) + iaddLen) + "})(.{"
							+ m.group(1).length() + "})";
					int oldLen = formulaDef.length();
					formulaDef = formulaDef.replaceAll(regex11, "$1" + target);
					iaddLen = iaddLen + formulaDef.length() - oldLen;

				}
				cellconfig = xssfrowconfig.createCell((short) 4);
				if (allTabsMap.size() == 1) {
					String k = (String) allTabsMap.keySet().iterator().next();
					String shtname1 = (String) allTabsMap.get(k);
					formulaDef = formulaDef.replace(shtname1 + "!", "");
				}
				cellconfig.setCellValue(formulaDef);
			}
			*/
		}
		
		HashMap colname2eleTableNameMap = (HashMap) codeMap.get("colname2eleTableNameMap");
		HashMap elementTablesMap = (HashMap) codeMap.get("elementTablesMap");
		List modelCodeList = (List) codeMap.get("modelCodeList");
		//如果导出表的代码表为空，则不导出代码表信息
		if (modelCodeList == null || modelCodeList.size() == 0) {
			retMap.put("success", true);
			return retMap;
		}
		// 06-引用列
		for (Iterator iterator = modelCodeList.iterator(); iterator.hasNext();) {
			HashMap map1 = (HashMap) iterator.next();
			
			String dbcolumnname = map1.get("DBCOLUMNNAME") == null ? "" : (String) map1.get("DBCOLUMNNAME");//代码表 列名
			String name = map1.get("NAME") == null ? "" : (String) map1.get("NAME");//代码表 列中文名
			String alias = map1.get("ALIAS") == null ? "" : (String) map1.get("ALIAS");
			String elementTable = map1.get("DBTABLENAME") == null ? "" : (String) map1.get("DBTABLENAME");//代码表 表名
			if (StringUtils.isNotEmpty(alias)){
				name = alias;
			}
			r++;
			firstCell.setCellValue(r + 1);
			xssfrowconfig = sheetConfig.createRow(r);
			cellconfig = xssfrowconfig.createCell((short) 0);
			cellconfig.setCellValue("06-引用列"); // 数据类型
			cellconfig = xssfrowconfig.createCell((short) 2);
			cellconfig.setCellValue(NAME);
			cellconfig = xssfrowconfig.createCell((short) 3);
			cellconfig.setCellValue(tableID);
			cellconfig = xssfrowconfig.createCell((short) 4);
			cellconfig.setCellValue(dbcolumnname);
			cellconfig = xssfrowconfig.createCell((short) 5);
			cellconfig.setCellValue(name);
			cellconfig = xssfrowconfig.createCell((short) 6);
			cellconfig.setCellValue(elementTable);
			int endCol = 10;
			cellconfig = xssfrowconfig.createCell((short) 1);
			cellconfig.setCellValue("10:" + endCol);
		}
		
		List<String> a = new ArrayList<String>();
		List elementtableList = new ArrayList();
		int codeColStartNum = 0;//20160719，代码表数据起始列
		int codeRowStartNum = 5000;//20160719，代码表数据起始行
		HashMap check1Map = new HashMap();
		
		// 04-数据元值集
		for (Iterator iterator = modelCodeList.iterator(); iterator.hasNext();) {
			HashMap map1 = (HashMap) iterator.next();
			String codeDbTableName = (String) map1.get("DBTABLENAME");//代码表 表名
			
			if (!check1Map.containsKey(codeDbTableName)) {
				check1Map.put(codeDbTableName, codeDbTableName);
			} else {
				continue;
			}
			
			elementtableList = (List) elementTablesMap.get(codeDbTableName);
			r++;
			firstCell.setCellValue(r + 1);
			xssfrowconfig = sheetConfig.createRow(r);
			cellconfig = xssfrowconfig.createCell((short) 0);
			cellconfig.setCellValue("04-数据元值集"); // 数据类型
			cellconfig = xssfrowconfig.createCell((short) 6);
			cellconfig.setCellValue(codeDbTableName);
			cellconfig = xssfrowconfig.createCell((short) 9);
			cellconfig.setCellValue("code-name");
			int endRow = codeRowStartNum;
			codeColStartNum += 1;
			for (int i = 0; i < elementtableList.size(); i++) {
				HashMap rowMap = (HashMap) elementtableList.get(i);
				String codeName = (String) rowMap.get("CODENAME");
				Row row = sheetConfig.getRow(endRow);
				if(row == null){
					row = sheetConfig.createRow(endRow);
				}
				
				cellconfig = row.createCell(codeColStartNum-1);
				cellconfig.setCellValue(codeName);
				endRow++;
			}
			cellconfig = xssfrowconfig.createCell((short) 1);
			cellconfig.setCellValue(codeColStartNum + ":" + endRow); // 数据范围
			
			// 导出值集guID（code-guid）
			r++;
			firstCell.setCellValue(r + 1);
			xssfrowconfig = sheetConfig.createRow(r);
			cellconfig = xssfrowconfig.createCell((short) 0);
			cellconfig.setCellValue("05-数据元值集GUID"); // 数据类型
			cellconfig = xssfrowconfig.createCell((short) 6);
			cellconfig.setCellValue(codeDbTableName);
			cellconfig = xssfrowconfig.createCell((short) 9);
			cellconfig.setCellValue("code-name^isleaf^guid");
			
			endRow = codeRowStartNum;
			codeColStartNum += 1;
			for (int i = 0; i < elementtableList.size(); i++) {
				HashMap rowMap = (HashMap) elementtableList.get(i);
				String itemid = (String) rowMap.get("GUID");
				String codeName = (String) rowMap.get("CODENAME");
				int isLeaf = 1;
				Object leaf = rowMap.get("ISLEAF");
				if (leaf != null && "0".equals(leaf.toString())) {
					isLeaf = Integer.parseInt(leaf.toString());
				}
				String codeNameStr = codeName + splitstr2 + isLeaf + splitstr2 + itemid;
				
				Row row = sheetConfig.getRow(endRow);
				if(row == null){
					row = sheetConfig.createRow(endRow);
				}
				cellconfig = row.createCell(codeColStartNum-1);
				cellconfig.setCellValue(codeNameStr);
				endRow++;
				
			}
			cellconfig = xssfrowconfig.createCell((short) 1);
			cellconfig.setCellValue(codeColStartNum + ":" + endRow); // 数据范围
		}
		//07引用列关系
		logger.debug("初始化关联引用列,表名："+NAME);
		tableID = dictTModel.getTableid();
		HashMap tabcolMap = GetXlsxDataFormDBII(tableID);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableID", tableID);
		List<DictTSetRefrelaPO> RelaColList = entryOuterMapper.getDataRefrelaList(map);
		Iterator localIterator = RelaColList.iterator();
		for (DictTSetRefrelaPO dictTSetRefrelaPO : RelaColList) {
			List<DictTSetRefrelaDataPO> reladata = dictTSetRefrelaPO.getRefrelaData();
			if(reladata == null || reladata.size() == 0){
				continue;
			}
			
			String colid = dictTSetRefrelaPO.getColumnID();
			String dbColumnName = (String) tabcolMap.get("columnid_" + colid);
			String fieldCnName = (String) tabcolMap.get("dbFieldCNname_"+ dbColumnName);
			String condColid = dictTSetRefrelaPO.getCondColumnID();
			String condDbColumnName = (String) tabcolMap.get("columnid_"+ condColid);
			String condfieldCnName = (String) tabcolMap.get("dbFieldCNname_"+ condDbColumnName);
			String elementTable = (String) colname2eleTableNameMap.get(tableID+ "_" + dbColumnName);
			if (elementTable == null) {
				continue;
			}
			String condelementTable = (String) colname2eleTableNameMap.get(tableID + "_" + condDbColumnName);
			if (condelementTable == null) {
				continue;
			}
			HashMap fieldCodeM = getElementCodeName(codeMap, tableID, dbColumnName, true);//代码表元素
			HashMap condfieldCodeM = getElementCodeName(codeMap, tableID,condDbColumnName, true);//代码表元素
			
			int numTemp = 0;
			for (DictTSetRefrelaDataPO dictTSetRefrelaDataPO : reladata) {
				String condCodeName = (String) condfieldCodeM.get(dictTSetRefrelaDataPO.getCondDataID());//代码表元素
				String codeName = (String) fieldCodeM.get(dictTSetRefrelaDataPO.getDataID());////代码表元素
				//若关联信息在所查的代码表中存在，则放入excel
				if(StringUtils.isNotEmpty(condCodeName) && StringUtils.isNotEmpty(codeName)){
					numTemp += 1;
				}
			}
			//放入excel的有关联元素，则放入这条关联引用的基本信息。
			if(numTemp > 0){
				r++;
				firstCell.setCellValue(r + 1);
				xssfrowconfig = sheetConfig.createRow(r);
				cellconfig = xssfrowconfig.createCell((short) 0);
				cellconfig.setCellValue("07-引用列关联"); // 数据类型
				
				cellconfig = xssfrowconfig.createCell((short) 2);
				cellconfig.setCellValue(NAME);
				cellconfig = xssfrowconfig.createCell((short) 3);
				cellconfig.setCellValue(tableID);
				cellconfig = xssfrowconfig.createCell((short) 4);
				cellconfig.setCellValue(condDbColumnName + splitstr + condfieldCnName + splitstr2 + "condData");
				cellconfig = xssfrowconfig.createCell((short) 5);
				cellconfig.setCellValue(dbColumnName + splitstr + fieldCnName + splitstr2 + "Data");
				cellconfig = xssfrowconfig.createCell((short) 6);
				cellconfig.setCellValue(condelementTable);
				cellconfig = xssfrowconfig.createCell((short) 7);
				cellconfig.setCellValue(elementTable);
				cellconfig = xssfrowconfig.createCell((short) 9);
				cellconfig.setCellValue("condData:::Data");
				
				int endRow = codeRowStartNum;
				codeColStartNum += 1;
				for (DictTSetRefrelaDataPO dictTSetRefrelaDataPO : reladata) {
					String condCodeName = (String) condfieldCodeM.get(dictTSetRefrelaDataPO.getCondDataID());//代码表元素
					String codeName = (String) fieldCodeM.get(dictTSetRefrelaDataPO.getDataID());////代码表元素
					//若关联信息在所查的代码表中存在，则放入excel
					if(StringUtils.isNotEmpty(condCodeName) && StringUtils.isNotEmpty(codeName)){
						Row row = sheetConfig.getRow(endRow);
						if(row == null){
							row = sheetConfig.createRow(endRow);
						}
						cellconfig = row.createCell(codeColStartNum-1);
						cellconfig.setCellValue(condCodeName + splitstr + codeName);
						endRow++;
					}
				}
				
				cellconfig = xssfrowconfig.createCell((short) 1);
				cellconfig.setCellValue(codeColStartNum + ":" + endRow);
				
			}
		}
		retMap.put("success", true);
		return retMap;
	}

	public String formulaDeepProcess(HashMap codeMap, DictTModelPO dictTModel, String tableID, HashMap xlsxColsMap, String formulaDef, HashMap allTabsMap, Matcher m, HashMap cacheMap) {
		String sourceStr = m.group(1);
		String strStart = m.group(2);
		String tabid1 = m.group(3);
		String formula1 = m.group(5);
		String tableName1 = (String) allTabsMap.get(tabid1);
		if (tableName1 == null) {
			tableName1 = dictTModel.getName();
			allTabsMap.put(tableID, tableName1);
		}
		String formula1tmp = formula1;// .toUpperCase();
		Pattern ppp = Pattern.compile("(?i)case\\s+when");
		Matcher mmm = ppp.matcher(formula1tmp);
		boolean bFind = mmm.find();
		if (bFind) {
			formula1tmp = formula1tmp.replaceAll("(?i)CASE\\s+WHEN", "if(");
			formula1tmp = formula1tmp.replaceAll("(?i)THEN", ",");
			formula1tmp = formula1tmp.replaceAll("(?i)ELSE", ",");
			formula1tmp = formula1tmp.replaceAll("(?i)END", ")");
			int oldLength = formula1tmp.length();
			formula1tmp = formula1tmp.replaceAll("(?i)WHEN ", ",if(");
			int newLength = formula1tmp.length();
			for (int iend = newLength; iend < oldLength; iend++) {
				formula1tmp = formula1tmp + ")";
			}
			formula1tmp = formula1tmp.replaceAll("\\s{1,}", " ");
			String regex1 = "([\\w|.]+)";
			Pattern ptn1 = Pattern.compile(regex1);
			Matcher m1 = ptn1.matcher(formula1tmp);

			String strout = formula1tmp;
			int istartFind = 0;
			int iaddLen = 0;
			while (m1.find(istartFind)) {

				String target = "";
				String field1 = m1.group(1);
				int istart = m1.start(1);
				istartFind = m1.end(1);
				if (!field1.replaceAll("\\d+(.\\d+)?", "").equals("")) {
					String coladdr = (String) xlsxColsMap.get(field1);
					if (coladdr == null) { // Round
						target = field1;
						continue;
					}
					if (this.getClass().equals(BaseNumberPoiService.class)) {
						int col1 = CellReference.convertColStringToIndex(coladdr) + 1;
						coladdr = "" + col1;
					}
					target = tableName1 + "![" + coladdr + "]";
					HashMap elementMap = (HashMap) cacheMap.get(tabid1 + "-" + field1 + "-" + tableName1);
					if (elementMap == null) {
						elementMap = getElementCodeName(codeMap, tabid1, field1, true);
						cacheMap.put(tabid1 + "-" + field1 + "-" + tableName1, elementMap);
					}
					if (elementMap.size() > 0) {
						boolean bMatch = false;
						if (!bMatch) {
							String regex11 = "^(.{" + (istart) + "})(" + field1
									+ ")\\s*(=|<>)\\s*'(.+?)'";
							Pattern ptn11 = Pattern.compile(regex11);
							Matcher m11 = ptn11.matcher(formula1tmp);
							if (m11.find()) {
								String eq = m11.group(3);
								String eleVid = m11.group(4);
								String eleVal = (String) elementMap.get(eleVid);
								target = target + eq + "\""
										+ eleVal + "\"";
								istartFind = m11.end(0);
								bMatch = true;
							}
							if (bMatch) {
								regex11 = "^(.{" + (istart + iaddLen) + "})("
										+ field1 + ")\\s*(?:=|<>)\\s*'(.+?)'";
								int oldLen = strout.length();
								strout = strout.replaceAll(regex11, "$1"
										+ target);
								iaddLen = iaddLen + strout.length() - oldLen;
							}
						}
						if (!bMatch) {
							String regex_in = "(?i)^(.{" + (istart) + "})("
									+ field1 + ")\\s*in\\s*\\((.+?)\\)";
							Pattern ptn_in = Pattern.compile(regex_in);
							Matcher m_in = ptn_in.matcher(formula1tmp);
							bMatch = false;
							if (m_in.find()) {
								String eleVidstr = m_in.group(3);
								String eleVids[] = eleVidstr.split(",");
								StringBuffer sbufin = new StringBuffer();
								for (int iel = 0; iel < eleVids.length; iel++) {
									String eleVid = eleVids[iel].replaceAll(
											"'", "");
									eleVid = eleVid.trim();
									String eleVal = (String) elementMap
											.get(eleVid);
									sbufin.append(target).append("=").append(
											"\"").append(eleVal).append("\"")
											.append(",");
								}
								int lastPos = sbufin.lastIndexOf(",");
								sbufin.replace(lastPos, lastPos + 1, "");
								target = "OR(" + sbufin.toString() + ")";
								istartFind = m_in.end(0);
								bMatch = true;
							}
							if (bMatch) {
								regex_in = "(?i)^(.{" + (istart + iaddLen)
										+ "})(" + field1
										+ ")\\s*in\\s*\\((.+?)\\)";
								int oldLen = strout.length();
								strout = strout.replaceAll(regex_in, "$1"
										+ target);
								iaddLen = iaddLen + strout.length() - oldLen;
							}
						}
					} else {
						String regex11 = "^(.{" + (istart + iaddLen) + "})("
								+ field1 + ")";
						int oldLen = strout.length();
						strout = strout.replaceAll(regex11, "$1" + target);
						iaddLen = iaddLen + strout.length() - oldLen;
					}

				}
			}

			formula1 = strout;
		}
		formula1 = formula1.replaceAll("\\s+", "");
		formula1 = formula1.replaceAll("'", "\"");
		formulaDef = formulaDef.replace(sourceStr, formula1);
		return formulaDef;
	}

	public String formulaProcess(HashMap codeMap, DictTModelPO dictTModel, String tableID, HashMap xlsxColsMap, String formulaDef, HashMap allTabsMap, Matcher m) {
		String sourceStr = m.group(1);
		String strStart = m.group(2);
		String tabid1 = m.group(3);
		String formula1 = m.group(5);
		String tableName1 = dictTModel.getName();
		allTabsMap.put(tableID, tableName1);
		String regex1 = "([\\w|.]+)";
		Pattern ptn1 = Pattern.compile(regex1);
		Matcher m1 = ptn1.matcher(formula1);
		String sourceStr1 = sourceStr;
		StringBuffer sbf = new StringBuffer();
		while (m1.find()) {
			String target = "";
			String field1 = m1.group(1);
			if (!field1.replaceAll("\\d+(.\\d+)?", "").equals("")) {
				String coladdr = (String) xlsxColsMap.get(field1);
				if (coladdr == null) { 
					target = field1;
					continue;
				}
				if (this.getClass().equals(BaseNumberPoiService.class)) {
					int col1 = CellReference.convertColStringToIndex(coladdr) + 1;
					coladdr = "" + col1;
				}
				target = tableName1 + "![" + coladdr + "]";
				m1.appendReplacement(sbf, target);
			}

		}
		m1.appendTail(sbf);
		sourceStr1 = sbf.toString();

		String regex_round0 = "(?i)^round\\((.+),\\d+(\\.\\d+)*\\)";
		Pattern ptn_round0 = Pattern.compile(regex_round0);
		Matcher m_round0 = ptn_round0.matcher(sourceStr1);
		String regex_round = "(?i)^round\\((.+)\\)";
		Pattern ptn_round = Pattern.compile(regex_round);
		Matcher m_round = ptn_round.matcher(sourceStr1);
		if (m_round.find() && !m_round0.find())
			sourceStr1 = sourceStr1.replaceAll("(?i)round\\((.+?)\\)",
					"round($1,0)");
		formulaDef = formulaDef.replace(sourceStr, sourceStr1);
		return formulaDef;
	}

	/*public String tabBetweenFormulaProcess(String tableID, String dealType, HashMap xlsxColsMap, HashMap allTabsMap, String formula1) {

		HashMap dbMap1 = (HashMap) SelectModelTableCache(tableID);
		String tableName1 = (String) dbMap1.get("NAME");
		allTabsMap.put(tableID, tableName1);
		String regex1 = "([\\w|.]+)";
		Pattern ptn1 = Pattern.compile(regex1);
		Matcher m1 = ptn1.matcher(formula1);
		StringBuffer sbf = new StringBuffer();
		while (m1.find()) {
			String target = "";
			String field1 = m1.group(1);
			if (!field1.replaceAll("\\d+(.\\d+)?", "").equals("")) {
				String coladdr = (String) xlsxColsMap.get(field1);
				if (coladdr == null) { 
					target = field1;
					continue;
				}
				int col1 = CellReference.convertColStringToIndex(coladdr);
				if ("62".equalsIgnoreCase(dealType)) {
					col1++;
				}
				coladdr = "" + col1;
				target = " F" + coladdr + " ";
				m1.appendReplacement(sbf, target);
			}

		}
		m1.appendTail(sbf);
		String sourceStr1 = sbf.toString();// sourceStr1.replace("}", "");
		return sourceStr1;
	}*/

	/*public String tabBetweenFormulaDeepProcess(String tabid1, String dealType, HashMap xlsxColsMap, HashMap allTabsMap, String formula1, HashMap conditionMap, HashMap cacheMap) {

		String tableName1 = (String) allTabsMap.get(tabid1);
		if (tableName1 == null) {
			HashMap dbMap1 = (HashMap) SelectModelTableCache(tabid1);
			tableName1 = (String) dbMap1.get("NAME");
			allTabsMap.put(tabid1, tableName1);
		}
		HashMap curColmap = GetXlsxDataFormDBII(tabid1);
		String headField = "";
		String headFieldvals = "";
		int baseNumFieldcondtionPos = 0;
		if ("62".equalsIgnoreCase(dealType)) {
			HashMap bntMap = getBaseNumberTableinfo(tabid1, tableName1);
			headField = (String) bntMap.get("headField");
			headFieldvals = (String) bntMap.get("headFieldvals");
			String condition1 = (String) conditionMap.get("condition");
			String conditionVals[] = condition1.split("=");
			if (headField.equalsIgnoreCase(conditionVals[0])) {
				String headFieldvalsArr[] = headFieldvals.split(",");
				for (int ia = 0; ia < headFieldvalsArr.length; ia++) {
					if (headFieldvalsArr[ia].trim().equalsIgnoreCase(
							conditionVals[1].trim())) {
						baseNumFieldcondtionPos = ia + 3;
						break;
					}
				}
			}
		}
		String regex1 = "([\\w|.]+)";
		Pattern ptn1 = Pattern.compile(regex1);
		Matcher m1 = ptn1.matcher(formula1);
		String strout = formula1;
		int istartFind = 0;
		int iaddLen = 0;
		while (m1.find(istartFind)) {

			String target = "";
			String field1 = m1.group(1);
			int istart = m1.start(1);
			istartFind = m1.end(1);
			if (!field1.replaceAll("\\d+(.\\d+)?", "").equals("")) {
				String coladdr = (String) xlsxColsMap.get(field1);
				if (coladdr == null) { // Round
					target = field1;
					continue;
				}
				int col1 = CellReference.convertColStringToIndex(coladdr);
				coladdr = "" + col1;
				if ("62".equalsIgnoreCase(dealType)) {
					coladdr = "" + baseNumFieldcondtionPos;
				}

				target = " F" + coladdr + " ";
				HashMap elementMap = (HashMap) cacheMap.get(tabid1 + "-"
						+ field1 + "-" + tableName1);
				if (elementMap == null) {
					elementMap = getElementCodeName(tabid1, field1, true);
					cacheMap.put(tabid1 + "-" + field1 + "-" + tableName1,
							elementMap);
				}
				if (elementMap.size() > 0) {
					boolean bMatch = false;
					if (!bMatch) {
						String regex11 = "^(.{" + (istart) + "})(" + field1
								+ ")\\s*(=|<>)\\s*'(.+?)'";
						Pattern ptn11 = Pattern.compile(regex11);
						Matcher m11 = ptn11.matcher(formula1);
						if (m11.find()) {
							String eq = m11.group(3);
							String eleVid = m11.group(4);
							String eleVal = (String) elementMap.get(eleVid);
							target =  m11.group(1)+ target + eq + " \""
									+ eleVal + "\"";
							istartFind = m11.end(0);
							bMatch = true;
						}
						if (bMatch) {
							regex11 = "^(.{" + (istart + iaddLen) + "})("
									+ field1 + ")\\s*(?:=|<>)\\s*'(.+?)'";
							int oldLen = strout.length();
							strout = strout.replaceAll(regex11, "$1" + target);
							iaddLen = iaddLen + strout.length() - oldLen;
							// continue;
						}
					}
					if (!bMatch) {
						String regex_in = "(?i)^(.{" + (istart) + "})("
								+ field1 + ")\\s*in\\s*\\((.+?)\\)";
						Pattern ptn_in = Pattern.compile(regex_in);
						Matcher m_in = ptn_in.matcher(formula1);
						bMatch = false;
						if (m_in.find()) {
							String eleVidstr = m_in.group(3);
							String eleVids[] = eleVidstr.split(",");
							StringBuffer sbufin = new StringBuffer();
							for (int iel = 0; iel < eleVids.length; iel++) {
								String eleVid = eleVids[iel]
										.replaceAll("'", "");
								eleVid = eleVid.trim();
								String eleVal = (String) elementMap.get(eleVid);
								sbufin.append("\"").append(eleVal).append("\"")
										.append(",");
							}
							int lastPos = sbufin.lastIndexOf(",");
							sbufin.replace(lastPos, lastPos + 1, "");
							target =  m11.group(1)+ target + " in ("
									+ sbufin.toString() + ")";
							istartFind = m_in.end(0);
							bMatch = true;
						}
						if (bMatch) {
							regex_in = "(?i)^(.{" + (istart + iaddLen) + "})("
									+ field1 + ")\\s*in\\s*\\((.+?)\\)";
							int oldLen = strout.length();
							strout = strout.replaceAll(regex_in, "$1" + target);
							iaddLen = iaddLen + strout.length() - oldLen;
						}
					}
				} else {
					String regex11 = "^(.{" + (istart + iaddLen) + "})("
							+ field1 + ")";
					int oldLen = strout.length();
					strout = strout.replaceAll(regex11, "$1" + target);
					iaddLen = iaddLen + strout.length() - oldLen;
				}
				if ("62".equalsIgnoreCase(dealType)) {
					String fieldCNname1 = (String) curColmap
							.get("dbFieldCNname_" + field1);
					String transposeCondition = " where trim(F2)=\""
							+ fieldCNname1 + "\"";
					conditionMap.put("transposeCondition", transposeCondition);
				}
			}
		}

		return strout;
	}*/

	protected int getCommonGridSumFieldcount(String tableID) {
		String sql = "select count(*) sumHeadCount from DICT_T_FACTOR t where issum=1 and tableID='" + tableID + "'";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		int SumHeadCount = ((BigDecimal) tmpMap.get("SUMHEADCOUNT")).intValue();
		return SumHeadCount;
	}

	protected int getViewTriggerCount(String tableID) {
		String sql = "select count(*) CC from user_triggers t where t.BASE_OBJECT_TYPE='VIEW' and t.table_name=(select DBTABLENAME from DICT_T_MODEL where tableID='" + tableID + "')";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		int CC = ((BigDecimal) tmpMap.get("CC")).intValue();
		return CC;
	}

	/*
	private Timestamp getOracleTimestamp(Object value) {
		try {
			Class clz = value.getClass();
			Method method = clz.getMethod("timestampValue", null);
			return (Timestamp) method.invoke(value, null);
		} catch (Exception e) {
			return null;
		}
	}

	public String getTableDealType(String tableID) {

		HashMap tableMap = (HashMap) SelectModelTableCache(tableID);
		String dealType = (String) tableMap.get("DEALTYPE");

		return dealType;
	}
	*/
}