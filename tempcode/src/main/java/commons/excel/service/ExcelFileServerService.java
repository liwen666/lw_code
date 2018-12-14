package commons.excel.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTFactorService;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.excel.dao.ExcelFileMapper;
import com.tjhq.commons.excel.service.impl.BaseNumberPoiService;
import com.tjhq.commons.excel.service.impl.CommonPoiService;
import com.tjhq.commons.excel.service.impl.FixedPoiService;
import com.tjhq.commons.excel.service.impl.FloatPoiService;
import com.tjhq.commons.excel.util.ExcelProgressBarUtil;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixColumn;
import com.tjhq.exp.common.utils.SystemConfUtils;

@Service
@Transactional
public class ExcelFileServerService {
	@Resource
	public ExcelFileMapper excelFileMapper;
	@Resource
	public CommonPoiService commonPoiService;
	@Resource
	public BaseNumberPoiService baseNumberPoiService;	
	@Resource
	public FixedPoiService fixedPoiService;	
	@Resource
	public FloatPoiService floatPoiService;
	
	@Resource
	private IDictTModelService dictTModelService;
	
	@Resource
	public IDictTFactorService dictTFactorService;
	
	public Logger logger = Logger.getLogger(ExcelFileServerService.class);
	
	public String hideConfigSheetName = "tjhqExcelConfig$";
	
	public String firstSheetName = "首页说明";
	
	public String splitstr = ":::";
	
	public String splitstr2 = "^";
	
	//导出单表时是否导出表间公式相关联的其他表，1 同时导出；0 不导出
	public String isExportrelationTable = "0";
	
	//自动判断是否刷新引用值集缓存，1 自动判断；0 每个请求都刷新
	//public String isElementValuesCacheAuto = "0";
	
	public ExcelFileServerService(){
	}
   
	public HashMap returnErrorInfo(String errInfo) {
		HashMap retMap = new HashMap();
		retMap.put("success", false);
		retMap.put("errorInfo", errInfo);
		return retMap;
	}
	
	public HashMap ExportDataToExcel(HashMap<String, Object> paraMap, String grid, HttpServletRequest request) throws Exception {
		HashMap<String, Object> resultMap = null;
		
		String tableid = (String) paraMap.get("tableID");
		DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableid, false);
		String tableName = dictTModel.getName();
		String dealType = dictTModel.getDealtype();
		
		request.setAttribute("curent_progressText", "导出表【"+tableName+"】");
		request.setAttribute("total_progressValue", 100);
		ExcelProgressBarUtil.progressAdd(request,3);
		ExcelProgressBarUtil.setProgressBar(request);
		
		String tableID = (String) paraMap.get("tableID");
		if (StringUtils.isEmpty(tableID)){
			throw new Exception("导出数据的输入参数不够，缺少tableID!");
		}
		String agencyID = (String) paraMap.get("agencyID");
		if (StringUtils.isEmpty(agencyID)){
			throw new Exception("导出数据的输入参数不够，缺少agencyID!");
		}
		//初始化代码表
		HashMap codeMap = initBaseMap(tableID, agencyID, request);
		// 固定行列表
		if ("A1".equalsIgnoreCase(dealType)) {
			resultMap = fixedPoiService.ExportDataToExcel(paraMap, grid, request, dictTModel, codeMap);
		// 浮动表
		} else if ("A2".equalsIgnoreCase(dealType)){ 
			resultMap = floatPoiService.ExportDataToExcel(paraMap, grid, request, dictTModel, codeMap);
		// 一般录入报表
		} else if ("A0".equalsIgnoreCase(dealType) || "A0*01".equalsIgnoreCase(dealType)) {
			resultMap = commonPoiService.ExportDataToExcel(paraMap, grid, request, dictTModel, codeMap);
		// 基本数字表
		} else if ("62".equalsIgnoreCase(dealType)) {
			resultMap = baseNumberPoiService.ExportDataToExcel(paraMap, grid, request, dictTModel, codeMap);
		}
		//导出表间公式的相关表
		/*if("1".equals(isExportrelationTable)){
			HashMap relationTableidMap = (HashMap) request.getAttribute("relationTableidMap");
			if (relationTableidMap != null){
				HashMap allTabsMap = (HashMap) relationTableidMap.get(tableid);
				if (allTabsMap != null && allTabsMap.size() > 1){
					HashMap otherTabsMap = (HashMap) allTabsMap.clone();
					if (otherTabsMap.containsKey(tableid)){
						otherTabsMap.remove(tableid);
					}
					ExportOtherRelationExcel(paraMap, otherTabsMap, resultMap, tableName, request);
				}
			}
		}*/
		return resultMap;

	}
	
	public HashMap handleAndSaveExcelData(byte[] bais, String fileName, String tableid, HashMap paramMap,  HttpServletRequest request, DictTModelPO dictTModel) throws Exception {
		HashMap retMap = new HashMap();
		String tableName = dictTModel.getName();
		if (paramMap == null){
			return returnErrorInfo("获取参数信息失败!");
		}
		String agencyID = (String) paramMap.get("agencyID");
		if (agencyID == null){
			return returnErrorInfo("获取单位id信息失败!");
		}
		//初始化代码表
		HashMap codeMap = initBaseMap(dictTModel.getTableid(), agencyID, request);
		
		HashMap agencyMap = (HashMap) this.excelFileMapper.SelectDivName(agencyID);
		String agencyCodeName = (String) agencyMap.get("CODE") + "-"+ (String) agencyMap.get("NAME");
		request.setAttribute("CuragencyCodeName", agencyCodeName);
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap configMap = new HashMap();
		XSSFWorkbook wb;
		wb = new XSSFWorkbook(new ByteArrayInputStream(bais));
		Sheet sheetHideconfig = wb.getSheet(hideConfigSheetName);
		if (sheetHideconfig == null) {
			return returnErrorInfo("上传Excel数据文件缺少框架配置信息!");
		}
		
		//是否导出通用录入模板  是：isCommon==1（代表点击业务表管理导出通用模板，可以通过文件名上的单位信息在数据填报页面导入） ，否： isCommon==其他（代表通过点击单位导出的模板，可以在通用录入页面导出导入）
		Cell cell_I1 = sheetHideconfig.getRow(0).getCell(8);
		if(cell_I1 != null && "1".equals(cell_I1.getStringCellValue())){
			//通过文件名确定导入的单位id，格式为 地区code_单位code_表名…….xlsm
			if(StringUtils.isNotEmpty(fileName)){
				String[] arr = fileName.split("_");
				if(arr.length > 2){
					String districtCode = arr[0];
					String agencyCode = arr[1];
					String sql = "select a.guid agencyid, a.code agencycode, b.code districtcode from code_t_agency_spf a join code_t_district b on b.guid = a.districtid and b.code = '"+districtCode+"' where a.code = '"+agencyCode+"'";
					HashMap paraMap = new HashMap();
					List tmpList = this.excelFileMapper.selectExcelInfo(sql);
					if(tmpList.size() == 0){
						return returnErrorInfo("导入的文件是通用Excel模板，请检查文件名上的地区代码和单位代码，未查询到单位ID");
					}
					HashMap tmpMap = (HashMap) tmpList.get(0);
					if(! tmpMap.isEmpty()){
						String excelAgencyID = (String)tmpMap.get("AGENCYID");
						if(!agencyID.equals(excelAgencyID)){
							return returnErrorInfo("导入的文件是通用Excel模板，所选单位与Excel单位不一致，请检查文件名上的地区代码和单位代码");
						}
					}else{
						return returnErrorInfo("导入的文件是通用Excel模板，请检查文件名上的地区代码和单位代码，未查询到单位ID");
					}
				}else{
					return returnErrorInfo("导入的文件是通用Excel模板，文件名应为：地区代码_单位代码_其他内容.xlsm");
				}
			}else{
				return returnErrorInfo("导入的文件是通用Excel模板，文件名应为：地区代码_单位代码_其他内容.xlsm");
			}
		}else{
			//获取excel隐藏sheet导出时的agencyID，与页面上选择单位时的agencyID比较 
			String excelAgencyID = sheetHideconfig.getRow(0).getCell(5).getStringCellValue().trim();
			if(!agencyID.equals(excelAgencyID)){
				return returnErrorInfo("导入的文件是按单位导出的Excel模板，所选单位与Excel单位不一致，不能导入！");
			}
		}
		
		Row firstRow = sheetHideconfig.getRow(0);
		int startRowCC = (int) firstRow.getCell(1).getNumericCellValue() - 1;
		int endRowCC = (int) firstRow.getCell(0).getNumericCellValue();
		HashMap excelElevalueguidMap =new HashMap();
		for (int rowCC = startRowCC; rowCC < endRowCC; rowCC++) {
			Row row = sheetHideconfig.getRow(rowCC);
			Cell cell = row.getCell(0);
			String configDataType = cell.getStringCellValue();
			if ("01-表定义".equalsIgnoreCase(configDataType)) {
				HashMap tabdefMap = new HashMap();
				LinkedHashMap tabdefFieldsMap = new LinkedHashMap();
				cell = row.getCell(1);
				String[] startend = cell.getStringCellValue().split(":");
				cell = row.getCell(2);
				String sheetName = cell.getStringCellValue();
				tabdefMap.put("sheetName", sheetName);
				cell = row.getCell(3);
				String curtableid = cell.getStringCellValue();
				tabdefMap.put("tableid", curtableid);
				cell = row.getCell(4);
				if( cell != null ) {
					String curshStart = cell.getStringCellValue().split(splitstr)[0];
					tabdefMap.put("curSheetstartRow", curshStart);
				}
				cell = row.getCell(5);
				if( cell != null ) {
					String curDBtablever = cell.getStringCellValue().split(splitstr)[0];
					tabdefMap.put("curDBtablever", curDBtablever);
				}
				cell = row.getCell(6);
				String curAuditStatus="";
				if( cell !=null ) {
					curAuditStatus = cell.getStringCellValue().split(splitstr)[0];
				}
				if ( !curAuditStatus.equalsIgnoreCase("1")) {
					return returnErrorInfo("工作表【" + sheetName + "】未审核或审核未通过!");
				}
				
				cell = row.getCell(7);
				String curShtsumFieldCC = cell.getStringCellValue().split(splitstr)[0];
				tabdefMap.put("curShtsumFieldCC", curShtsumFieldCC);
				cell = row.getCell(8);
				String curTableType = cell.getStringCellValue().split(splitstr)[0];
				tabdefMap.put("curTableType", curTableType);
				cell = row.getCell(9);
				String[] curTablefieldsSn = cell.getStringCellValue().split(splitstr);
				// tabdefMap.put("curTableType", curTablefieldsSn);
				
				
				int dbColumnNameSN = 0;
				int fieldNameSN = 0;
				for (int i = 0; i < curTablefieldsSn.length; i++) {
					if ("DBCOLUMNNAME".equalsIgnoreCase(curTablefieldsSn[i])) {
						dbColumnNameSN = i;
					}
					if ("NAME".equalsIgnoreCase(curTablefieldsSn[i])) {
						fieldNameSN = i;
					}
				}
				int curStart = Integer.valueOf(startend[0]);
				int curEnd=Integer.valueOf(startend[1]);
				HashMap nonNULLfieldMap = new HashMap();
				nonNULLfieldMap.put("DATAKEY", "数据ID");
				nonNULLfieldMap.put("STATUS", "数据状态");
				nonNULLfieldMap.put("AGENCYID", "单位ID");
				int ic = 0;
				for (ic = curStart; ic < curEnd ; ic++) {
					String[] fieldinfo = row.getCell(ic).getStringCellValue().split(splitstr);
					String curDbColumnname = fieldinfo[dbColumnNameSN];
					String fieldname = fieldinfo[fieldNameSN];
					tabdefFieldsMap.put((ic - curStart + 1),
							curDbColumnname + splitstr + fieldname); // 0
																		// dbcolumnname,1
																		// cnName;
					if (nonNULLfieldMap.containsKey(curDbColumnname)) {
						nonNULLfieldMap.remove(curDbColumnname);
					}
				}
				for (Iterator itnon = nonNULLfieldMap.keySet().iterator(); itnon.hasNext();) {
					String dk = (String) itnon.next();
					String dkn = (String) nonNULLfieldMap.get(dk);
					tabdefFieldsMap.put((ic - curStart + 1), dk + splitstr + dkn);
					ic++;
				}
				tabdefMap.put("tabdefFieldsMap", tabdefFieldsMap);
				configMap.put(curtableid, tabdefMap);
			}
			if ("06-引用列".equalsIgnoreCase(configDataType)) {
				cell = row.getCell(1);
				String[] startend = cell.getStringCellValue().split(":");
				cell = row.getCell(2);
				String sheetName = cell.getStringCellValue();
				cell = row.getCell(3);
				String curtableid = cell.getStringCellValue();
				cell = row.getCell(4);
				String dbfieldENname = cell.getStringCellValue();
				cell = row.getCell(5);
				String fieldCNname = cell.getStringCellValue();
				cell = row.getCell(6);
				String elementTablename = cell.getStringCellValue();
				int curStart = Integer.valueOf(startend[0]);
				excelElevalueguidMap.put(curtableid+splitstr2+dbfieldENname, elementTablename);
			}
			if ("05-数据元值集GUID".equalsIgnoreCase(configDataType)) {
				HashMap eleMap = new HashMap();
				cell = row.getCell(1);
				String[] startend = cell.getStringCellValue().split(":");
				String fieldCNname = cell.getStringCellValue();
				cell = row.getCell(6);
				String elementTablename = cell.getStringCellValue();
				int curStart = 5000;
				for (int ic = curStart; ic < Integer.valueOf(startend[1]); ic++) {
					Row row1 = sheetHideconfig.getRow(ic);
					String cellV= row1.getCell(Integer.valueOf(startend[0])-1).getStringCellValue();
					if(StringUtils.isNotEmpty(cellV)){
						String[] elementInfo =cellV.split("\\"+splitstr2);
						String codeName = elementInfo[0];
						String itemid =  elementInfo[2];
						eleMap.put(codeName, itemid);
					}
				}
				excelElevalueguidMap.put("elementTablename"+splitstr2+elementTablename, eleMap);
			}
		}
		if(!configMap.containsKey(tableid))
		{
			return returnErrorInfo("导入excel文件（"+fileName+"）中不存在sheet名【" + tableName + "】;\r\n   导入报表未对应选择报表;\r\n请在左侧菜单中选择excel文件包含的报表再导入!");
		}
		StringBuffer errBuf=new StringBuffer();
		for (Iterator it = configMap.keySet().iterator(); it.hasNext();) {
			String curTableid = (String) it.next();
			HashMap curtabdefMap = (HashMap) configMap.get(curTableid);
			String curTableType = (String) curtabdefMap.get("curTableType");
			HashMap resultMap=null;
			// 固定行列表
			if ("A1".equalsIgnoreCase(curTableType)) {
				resultMap = fixedPoiService.handleAndSaveExcelData(codeMap, dictTModel, configMap, excelElevalueguidMap, wb, curTableid,  paramMap,request);
			// 浮动表
			} else if ("A2".equalsIgnoreCase(curTableType)) {
				resultMap = floatPoiService.handleAndSaveExcelData(codeMap, dictTModel, configMap, excelElevalueguidMap, wb, curTableid,  paramMap,request);
			// 一般录入报表
			} else if ("A0".equalsIgnoreCase(curTableType)) {
				resultMap = commonPoiService.handleAndSaveExcelData(codeMap, dictTModel, configMap, excelElevalueguidMap, wb, curTableid,  paramMap,request);
			// 基本数字表
			} else if ("62".equalsIgnoreCase(curTableType)) {
				resultMap = baseNumberPoiService.handleAndSaveExcelData(codeMap, dictTModel, configMap, excelElevalueguidMap, wb, curTableid,  paramMap,request);
			}
			if(resultMap.containsKey("errorInfo")){
				String curErrstr=(String) resultMap.get("errorInfo");
				errBuf.append(curErrstr).append("\r\n");
			}
			

		}
		if(errBuf.length()>0){
			return returnErrorInfo(errBuf.toString());
		}
		retMap.put("success", true);
		retMap.put("successInfo", "上传Excel数据文件成功!");
		return retMap;
	}
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
			this.excelFileMapper.callProcedure(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "列间公式计算错误", false);
		}
	}
	/**
	 * 调用存储过程，在导入完成之后调用，删除P#表中status=2且datakey=导入的datakey的数据--------解决页面上删除失败的问题
	 * @param tableName
	 * @param agencyID
	 * @throws ServiceException
	 */
	public void callProcedure_p_clear_deleted_data(String tableName, String agencyID) throws ServiceException {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("tableName", tableName);
			params.put("agencyID", agencyID);
			this.excelFileMapper.callProcedure_p_clear_deleted_data(params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "调用p_clear_deleted_data出错", false);
		}
	}
	//初始化代码表信息
	public HashMap initBaseMap(String tableID, String agencyID, HttpServletRequest request) throws Exception {
		String noHiddenSheet = request.getParameter("noHiddenSheet");
		HashMap colname2eleTableNameMap = new HashMap();
		HashMap elementTablesMap = new HashMap();
		HashMap codeMap = new HashMap();
		String sql = "select t.dbtablename, u.dbcolumnname, u.name, u.alias, t.dynamicwhere from dict_t_modelcode t,dict_t_factor u where t.tableID = u.csid and u.tableID = '" + tableID + "'"; 
		List modelCodeList = this.excelFileMapper.selectExcelInfo(sql);
		codeMap.put("modelCodeList", modelCodeList);
		
		ArrayList tempList = new ArrayList();//防止重复查询
		List codeNameList = new ArrayList();
		for (Iterator iterator = modelCodeList.iterator(); iterator.hasNext();) {
			HashMap map1 = (HashMap) iterator.next();
			String dbcolumnname = map1.get("DBCOLUMNNAME") == null ? "" : (String) map1.get("DBCOLUMNNAME");//代码表 列名
			String codeTableName = map1.get("DBTABLENAME") == null ? "" : (String) map1.get("DBTABLENAME");//代码表 表名
			colname2eleTableNameMap.put(tableID + "_" + dbcolumnname, codeTableName);
			
			if(tempList.indexOf(codeTableName) == -1){
				tempList.add(codeTableName);
				String dynamicwhere = map1.get("DYNAMICWHERE") == null ? "" : (String) map1.get("DYNAMICWHERE");// 代码表where条件
				dynamicwhere = StringUtils.isNotEmpty(dynamicwhere) ? " where " + dynamicwhere : "";
				dynamicwhere = dynamicwhere.replaceAll(Matcher.quoteReplacement("$AGENCYID$"), agencyID);
				//dynamicwhere = " where name = '111111111111' ";//测试空代码表
				sql = "select trim(code)||'-'||trim(name) codename, guid, trim(code) code, trim(name) name, isleaf from " + codeTableName + dynamicwhere + "";
				codeNameList = this.excelFileMapper.selectExcelInfo(sql);
				//20160818若只导出数据的，不判断代码表是否为空。
				if(StringUtils.isEmpty(noHiddenSheet) || "0".equals(noHiddenSheet)){
					if(codeNameList.size() == 0){
						throw new Exception("条件为{"+dynamicwhere+"}的引用代码表" + codeTableName + "数据为空，不能导入导出，请重新导出模板或检查代码表数据。");
					}
				}
				HashMap check1Map = new HashMap();
				for (int i = 0; i < codeNameList.size(); i++) {
					HashMap rowMap = (HashMap) codeNameList.get(i);
					String codeName = (String) rowMap.get("CODENAME");
					if (!check1Map.containsKey(codeName)) {
						check1Map.put(codeName, codeName);
					} else {
						throw new Exception("引用代码表" + codeTableName + "的CODE-NAME出现重复,在excel离线编辑时通过CODE-NAME：" + codeName + "反查GUID时无法确定唯一值，不能导入导出，请重新导出模板或检查代码表数据。");
					}
				}
				elementTablesMap.put(codeTableName, codeNameList);
			}else{
				continue;
			}
		}
		codeMap.put("elementTablesMap", elementTablesMap);
		codeMap.put("colname2eleTableNameMap", colname2eleTableNameMap);
		return codeMap;
		
	}
	
	/**
	 * 处理dict_t_factorxlsx表信息
	 */
	public void saveDictTFactorxlsx(String tableID) throws ServiceException {
		try {
			DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
			String tableName = dictTModel.getName();
			String dealtype = dictTModel.getDealtype();
			
			String sql = "select * from dict_t_factor u where tableid = '" + tableID
				+ "' start with (superid = '0' or superid is null or superid = '') "
				+ " connect by prior columnid = superid  "
				+ " order siblings by orderid";
			
			List headsList = this.excelFileMapper.selectExcelInfo(sql);
			StringBuffer batsqlBUF = new StringBuffer();
			int colnum = 1;
			for (int i = 0; i < headsList.size(); i++) {
				HashMap HeadMap = (HashMap) headsList.get(i);
				String isvisible = HeadMap.get("ISVISIBLE")==null ? "" :(String) HeadMap.get("ISVISIBLE");
				String columnid = HeadMap.get("COLUMNID")==null ? "" :(String) HeadMap.get("COLUMNID");
				String dbcolumnname = HeadMap.get("DBCOLUMNNAME")==null ? "" : (String) HeadMap.get("DBCOLUMNNAME");
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
				if("A1".equals(dealtype)){
	        		//参数列隐藏
					if(StringUtils.isNotEmpty(dbcolumnname)){
						if (ExcelProgressBarUtil.getControlFields_A1().contains(dbcolumnname.replaceAll("[_1,_2,_3]", ""))) {
							isvisible = "0";
			            }
					}
				}
				//浮动表处理
				if("A2".equals(dealtype)){
					if (ExcelProgressBarUtil.getControlFields_A2().contains(dbcolumnname)) {
						isvisible = "0";
		            }
				}
				if ("1".equalsIgnoreCase(isvisible) ) {
					String isLeaf = HeadMap.get("ISLEAF")==null ? "" :(String) HeadMap.get("ISLEAF");
					if ("0".equalsIgnoreCase(isLeaf)) {
						continue;
					}
					String coluri = CellReference.convertNumToColString(colnum);
					if (dbcolumnname != null && !dbcolumnname.equalsIgnoreCase("")) {
						batsqlBUF.append("select '" + columnid + "' columnid,'" + tableID + "' tableid, '" + coluri.toUpperCase() + "' xlsxcolumnid from dual ");
						batsqlBUF.append("union all ");
					}
					colnum++;
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
			sql = "update dict_t_factorxlsx a set (columnid, tableid, xlsxcolumnid) = (select b.columnid, b.tableid, b.xlsxcolumnid from (" + batsqlBufStr + ") b where a.columnid = b.columnid and a.tableid = b.tableid) where exists (select 1 from ("+batsqlBufStr+") t where t.columnid = a.columnid and t.tableid = a.tableid)";
			this.excelFileMapper.updateSql(sql);
			sql = "insert into dict_t_factorxlsx a (columnid, tableid, xlsxcolumnid) (select b.columnid, b.tableid, b.xlsxcolumnid from (" + batsqlBufStr + ") b where not exists (select 1 from dict_t_factorxlsx t where t.columnid = b.columnid and t.tableid = b.tableid))";
			this.excelFileMapper.updateSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "处理dict_t_factorxlsx表信息出错", false);
		}
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
	/*public HashMap ExportSuitDataToExcel(HashMap paraMap,HttpServletRequest request) throws Exception {
	String tableID = (String) paraMap.get("tableID");
	String taskID = (String) paraMap.get("taskID");
	String appID = (String) paraMap.get("appID");
	String agencyID = (String) paraMap.get("agencyID");
	HashMap retMap = new HashMap();
	HashMap agencyMap = (HashMap) this.excelFileMapper.SelectDivName(agencyID);
	String agencyCodeName = "_" + (String) agencyMap.get("CODE") + "-"+ (String) agencyMap.get("NAME") + "_";

	if (tableID == null || tableID.equalsIgnoreCase("")	|| agencyID == null || agencyID.equalsIgnoreCase("")){
		throw new Exception("导出数据的输入参数不够!");
	}
	paraMap = new HashMap();
	String sql = "select t.name,t.tableid,t.DEALTYPE,t.APPID,(SELECT  suitname FROM dict_t_suit where suitid=t.suitid) suitname from DICT_T_MODEL t where t.ISSHOW='1'  and t.suitid="
			+ "(select suitid from DICT_T_MODEL t2 where t2.TABLEID='"	+ tableID + "') order by orderid";
	paraMap.put("AllinOneSQL", sql);
	List tmpList = this.excelFileMapper.SelectModelFixed(paraMap);
	PageInfo dataPageInfo = null;
	XSSFtjhqWorkbook wbMain = new XSSFtjhqWorkbook(true);
	SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
	Date now = new Date();
	String nowtime = df.format(now);
	String suitName = "";
    HashMap curWbelementTabsMap=new HashMap();
    
    ExcelProgressBarUtil.progressAdd(request,3);
    int iCost=(100-3)/tmpList.size();
    request.setAttribute("filldata_cost", 0); 
    ExcelProgressBarUtil.setProgressBar(request);
    
	for (int i = 0; i < tmpList.size(); i++) {
		HashMap rowMap = (HashMap) tmpList.get(i);
		String tablename = (String) rowMap.get("NAME");
		String tabid1 = (String) rowMap.get("TABLEID");
		if ("".equalsIgnoreCase(suitName))
			suitName = (String) rowMap.get("SUITNAME");
		String dealType = (String) rowMap.get("DEALTYPE");
		String appid1 = (String) rowMap.get("APPID");
		System.out.println("正在导出数据表:" + (i + 1) + "【" + suitName + "】"+ tablename + ":" + dealType+",tableid="+tabid1);
		
	    	request.setAttribute("curent_progressText", "导出表【"+tablename+"】");
	    	
	        ExcelProgressBarUtil.progressAdd(request,iCost);
	        
		HashMap resultMap = null;
		// 固定行列表
		if ("A1".equalsIgnoreCase(dealType)) {
			resultMap = fixedPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
		// 浮动表	
		} else if ("A2".equalsIgnoreCase(dealType)) {
			resultMap = floatPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
		// 一般录入报表
		} else if ("A0".equalsIgnoreCase(dealType) || "A0*01".equalsIgnoreCase(dealType)){ 
			resultMap = commonPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
			// 基本数字表
		} else if ("62".equalsIgnoreCase(dealType)) {
			resultMap = baseNumberPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
		}
		byte[] filexlsx = (byte[]) resultMap.get("filelob");

		XSSFWorkbook wb1;
		wb1 = new XSSFWorkbook(new ByteArrayInputStream(filexlsx));
		XSSFSheet sht1 = wb1.getSheet(tablename);
		XSSFSheet sht2 = wb1.getSheet(hideConfigSheetName);
		XSSFSheet msheet1 = wbMain.copySheetFromOtherXLSX(sht1, wb1);
		XSSFSheet wbMainsht2 = wbMain.getSheet(hideConfigSheetName);
		if (wbMainsht2 == null) {
			wbMainsht2 = wbMain.copySheetFromOtherXLSX(sht2, wb1);
			XSSFRow MainfirstRow = wbMainsht2.getRow(0);
			XSSFCell Maincell_A1 = MainfirstRow.getCell(0);
			XSSFCell Maincell_B1 = MainfirstRow.getCell(1);
			int MainendRow = (int) Maincell_A1.getNumericCellValue();
			int MainStartRow = (int) Maincell_B1.getNumericCellValue();
			for(int imr=MainStartRow-1;imr<MainendRow;imr++){
			
				Row dataRow=wbMainsht2.getRow(imr);
				String dataRowType=dataRow.getCell(0).getStringCellValue();
				if("04-数据元值集".equalsIgnoreCase(dataRowType)){
					String elementTable=dataRow.getCell(6).getStringCellValue();
					curWbelementTabsMap.put(elementTable, imr);
				}
			}
			continue;
		}
		XSSFRow MainfirstRow = wbMainsht2.getRow(0);
		XSSFCell Maincell_A1 = MainfirstRow.getCell(0);
		int MainendRow = (int) Maincell_A1.getNumericCellValue();
		XSSFRow sht2firstRow = sht2.getRow(0);
		XSSFCell sht2cell_A1 = sht2firstRow.getCell(0);
		int sht2endRow = (int) sht2cell_A1.getNumericCellValue();
		XSSFCell sht2cell_A2 = sht2firstRow.getCell(1);
		int sht2startRow = (int) sht2cell_A2.getNumericCellValue();
		if (sht2endRow < sht2startRow || MainendRow == 0) {
			throw new Exception("tjhqExcelConfig$隐藏工作表数据错误！");
		}
		int ird = MainendRow;
		for (int ir = sht2startRow - 1; ir < sht2endRow; ir++) {
			XSSFRow srow = sht2.getRow(ir);
			String dataRowType=srow.getCell(0).getStringCellValue();
			if("04-数据元值集".equalsIgnoreCase(dataRowType))
			{
				String elementTable=srow.getCell(6).getStringCellValue();
				if(elementTable != null && curWbelementTabsMap.containsKey(elementTable))
				{
					ir++;      //跳过后面紧跟"05-数据元值集GUID"
					continue;
				}
				else
				{
				   curWbelementTabsMap.put(elementTable, ird);
				}
			}

			XSSFRow drow = wbMainsht2.createRow(ird);
			int cnum = 0;
			for (Cell cell : srow) {
				cnum++;

				int colNum = cell.getColumnIndex();
				Cell destCell = drow.getCell(colNum);
				if (destCell == null)
					destCell = drow.createCell(colNum);

				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					destCell.setCellValue(cell.getRichStringCellValue());
					break;
				// 这里判断是否是日期
				case XSSFCell.CELL_TYPE_NUMERIC:
					// 判断是否是日期格式
					// 测试发现如果这里不新建样式,日期显示的是数字
					if (DateUtil.isCellDateFormatted(cell)) {

						destCell.setCellValue(cell.getDateCellValue());
					} else {
						destCell.setCellValue(cell
								.getNumericCellValue());
					}
					break;
				case XSSFCell.CELL_TYPE_FORMULA:
					destCell.setCellFormula(cell.getCellFormula());
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					destCell.setCellValue(cell.getBooleanCellValue());
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					destCell.setCellType(XSSFCell.CELL_TYPE_BLANK);
					break;
				case XSSFCell.CELL_TYPE_ERROR:
					break;
				default:
					break;
				}
			}
			ird++;
			Maincell_A1.setCellValue(ird);
		}
	}
	wbMain.setSheetOrder(hideConfigSheetName,wbMain.getNumberOfSheets() - 1);
	WorkbookAllCellsCalc(wbMain);

	HashMap vbaMap = getVBAXlsmFile(tableID);
	byte[] vbaBin = (byte[]) vbaMap.get("filelob");
	XSSFWorkbook vbAwb = new XSSFWorkbook(new ByteArrayInputStream(
			vbaBin));
	XSSFSheet srcfirstsht = vbAwb.getSheet(firstSheetName);
	XSSFSheet firstSht = wbMain.copySheetFromOtherXLSX(srcfirstsht,
			vbAwb);
	wbMain.setSheetOrder(firstSheetName, 0);
	wbMain.copycustomUIfromOtherXLSM(vbAwb);
	wbMain.copyVBAfromOtherXLSM(vbAwb);
	CTBookViews bv = wbMain.getCTWorkbook().getBookViews();
	bv.getWorkbookViewArray(0).setActiveTab(0L);
	for (int isheetnum = 1; isheetnum < wbMain.getNumberOfSheets(); isheetnum++) {
		wbMain.setSheetHidden(isheetnum, true);
	}
	String Extxlsm = ".excelFileMapper";
	if (wbMain.isMacroEnabled())
		Extxlsm = ".xlsm";

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	wbMain.write(baos);
	byte[] databytes = baos.toByteArray();
	retMap.put("filelob", databytes);
	retMap.put("filename", agencyCodeName + "【" + suitName + "】套表"+ nowtime + Extxlsm);


	retMap.put("success", true);
	return retMap;
}*/
	/*public HashMap ExportOtherRelationExcel(HashMap paraMap,HashMap otherTabsMap,HashMap xlsmMap,String tablen,HttpServletRequest request) throws Exception {
		String tableID = (String) paraMap.get("tableID");
		String taskID = (String) paraMap.get("taskID");
		String appID = (String) paraMap.get("appID");
		String agencyID = (String) paraMap.get("agencyID");
		HashMap retMap = xlsmMap;//new HashMap();
		StringBuffer otherTabidsbuf=new StringBuffer();
		for(Iterator it1=otherTabsMap.keySet().iterator();it1.hasNext();)
		{   String str1=(String) it1.next();
			otherTabidsbuf.append("'").append(str1).append("'").append(",");
		}
		int lastPos=otherTabidsbuf.lastIndexOf(",");
		otherTabidsbuf.replace(lastPos,lastPos + 1, "");
			paraMap = new HashMap();
			String sql = "select t.name,t.tableid,t.DEALTYPE,t.APPID,(SELECT  suitname FROM dict_t_suit where suitid=t.suitid) suitname from DICT_T_MODEL t where t.ISSHOW='1'  and t.tableid in ("
					+ otherTabidsbuf.toString()+ ") order by orderid";
			paraMap.put("AllinOneSQL", sql);
			List tmpList = this.excelFileMapper.SelectModelFixed(paraMap);
			PageInfo dataPageInfo = null;
			XSSFtjhqWorkbook wbMain = new XSSFtjhqWorkbook(true);
            HashMap curWbelementTabsMap=new HashMap();
			byte[] filexlsx0 = (byte[]) xlsmMap.get("filelob");
           // System.arraycopy(src, srcPos, dest, destPos, length)
			XSSFWorkbook wb0;
			wb0 = new XSSFWorkbook(new ByteArrayInputStream(filexlsx0));
			XSSFSheet sht01 = wb0.getSheet(tablen);
			XSSFSheet sht02 = wb0.getSheet(hideConfigSheetName);
			XSSFSheet msheet01 = wbMain.copySheetFromOtherXLSX(sht01, wb0);
			XSSFSheet wbMainsht02 = wbMain.getSheet(hideConfigSheetName);
			if (wbMainsht02 == null) {
				wbMainsht02 = wbMain.copySheetFromOtherXLSX(sht02, wb0);
				XSSFRow MainfirstRow = wbMainsht02.getRow(0);
				XSSFCell Maincell_A1 = MainfirstRow.getCell(0);
				XSSFCell Maincell_B1 = MainfirstRow.getCell(1);
				int MainendRow = (int) Maincell_A1.getNumericCellValue();
				int MainStartRow = (int) Maincell_B1.getNumericCellValue();
				for(int imr=MainStartRow-1;imr<MainendRow;imr++)
				{
					Row dataRow=wbMainsht02.getRow(imr);
					String dataRowType=dataRow.getCell(0).getStringCellValue();
					if("04-数据元值集".equalsIgnoreCase(dataRowType))
					{
						String elementTable=dataRow.getCell(6).getStringCellValue();
						curWbelementTabsMap.put(elementTable, imr);
					}
				}
			}
            int iCost=(100-33)/tmpList.size();
            request.setAttribute("filldata_cost", 0); 

			for (int i = 0; i < tmpList.size(); i++) {
				HashMap rowMap = (HashMap) tmpList.get(i);
				String tablename = (String) rowMap.get("NAME");
				String tabid1 = (String) rowMap.get("TABLEID");
				String dealType = (String) rowMap.get("DEALTYPE");
				String appid1 = (String) rowMap.get("APPID");
				HashMap resultMap = null;
				
				request.setAttribute("curent_progressText", "导出相关表【"+tablename+"】");
	   	        ExcelProgressBarUtil.progressAdd(request,iCost);
				
				if ("A1".equalsIgnoreCase(dealType)) // 固定行列表
				{
					resultMap = fixedPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);

				} else if ("A2".equalsIgnoreCase(dealType)) // 浮动表
				{
					resultMap = floatPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
				} else if ("A0".equalsIgnoreCase(dealType)||"A0*01".equalsIgnoreCase(dealType)) // 一般录入报表
				{
					resultMap = commonPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
				} else if ("62".equalsIgnoreCase(dealType)) // 基本数字表
				{
					resultMap = baseNumberPoiService.getDataPageInfoAndXlsxFromDB(rowMap, agencyID,request);
				}
				byte[] filexlsx = (byte[]) resultMap.get("filelob");

				XSSFWorkbook wb1;
				wb1 = new XSSFWorkbook(new ByteArrayInputStream(filexlsx));
				XSSFSheet sht1 = wb1.getSheet(tablename);
				XSSFSheet sht2 = wb1.getSheet(hideConfigSheetName);
				XSSFSheet msheet1 = wbMain.copySheetFromOtherXLSX(sht1, wb1);
				XSSFSheet wbMainsht2 = wbMain.getSheet(hideConfigSheetName);
				if (wbMainsht2 == null) {
					wbMainsht2 = wbMain.copySheetFromOtherXLSX(sht2, wb1);
					continue;
				}
				XSSFRow MainfirstRow = wbMainsht2.getRow(0);
				XSSFCell Maincell_A1 = MainfirstRow.getCell(0);
				int MainendRow = (int) Maincell_A1.getNumericCellValue();
				XSSFRow sht2firstRow = sht2.getRow(0);
				XSSFCell sht2cell_A1 = sht2firstRow.getCell(0);
				int sht2endRow = (int) sht2cell_A1.getNumericCellValue();
				XSSFCell sht2cell_A2 = sht2firstRow.getCell(1);
				int sht2startRow = (int) sht2cell_A2.getNumericCellValue();
				if (sht2endRow < sht2startRow || MainendRow == 0) {
					throw new Exception("tjhqExcelConfig$隐藏工作表数据错误！");
				}
				int ird = MainendRow;
				for (int ir = sht2startRow - 1; ir < sht2endRow; ir++) {
					XSSFRow srow = sht2.getRow(ir);
					String dataRowType=srow.getCell(0).getStringCellValue();
					if("04-数据元值集".equalsIgnoreCase(dataRowType))
					{
						String elementTable=srow.getCell(6).getStringCellValue();
						if(elementTable != null && curWbelementTabsMap.containsKey(elementTable))
						{
							ir++;      //跳过后面紧跟"05-数据元值集GUID"
							continue;
						}
						else
						{
						   curWbelementTabsMap.put(elementTable, ird);
						}
					}
					XSSFRow drow = wbMainsht2.createRow(ird);
					int cnum = 0;
					for (Cell cell : srow) {
						cnum++;

						int colNum = cell.getColumnIndex();
						Cell destCell = drow.getCell(colNum);
						if (destCell == null)
							destCell = drow.createCell(colNum);

						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_STRING:
							destCell.setCellValue(cell.getRichStringCellValue());
							break;
						// 这里判断是否是日期
						case XSSFCell.CELL_TYPE_NUMERIC:
							// 判断是否是日期格式
							// 测试发现如果这里不新建样式,日期显示的是数字
							if (DateUtil.isCellDateFormatted(cell)) {

								destCell.setCellValue(cell.getDateCellValue());
							} else {
								destCell.setCellValue(cell.getNumericCellValue());
							}
							break;
						case XSSFCell.CELL_TYPE_FORMULA:
							destCell.setCellFormula(cell.getCellFormula());
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							destCell.setCellValue(cell.getBooleanCellValue());
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							destCell.setCellType(XSSFCell.CELL_TYPE_BLANK);
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							break;
						default:
							break;
						}
					}
					ird++;
					Maincell_A1.setCellValue(ird);
				}
			}
			wbMain.setSheetOrder(hideConfigSheetName,wbMain.getNumberOfSheets() - 1);
			WorkbookAllCellsCalc(wbMain);

			HashMap vbaMap = getVBAXlsmFile(tableID);
			byte[] vbaBin = (byte[]) vbaMap.get("filelob");
			XSSFWorkbook vbAwb = new XSSFWorkbook(new ByteArrayInputStream(	vbaBin));
			XSSFSheet srcfirstsht = vbAwb.getSheet(firstSheetName);
			XSSFSheet firstSht = wbMain.copySheetFromOtherXLSX(srcfirstsht,	vbAwb);
			wbMain.setSheetOrder(firstSheetName, 0);
			wbMain.copycustomUIfromOtherXLSM(vbAwb);
			wbMain.copyVBAfromOtherXLSM(vbAwb);
			CTBookViews bv = wbMain.getCTWorkbook().getBookViews();
			bv.getWorkbookViewArray(0).setActiveTab(0L);
			for (int isheetnum = 1; isheetnum < wbMain.getNumberOfSheets(); isheetnum++) {
				wbMain.setSheetHidden(isheetnum, true);
			}


			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wbMain.write(baos);
			byte[] databytes = baos.toByteArray();
			retMap.put("filelob", databytes);
			//retMap.put("filename", agencyCodeName + "【" + suitName + "】套表"+ nowtime + Extxlsm);
			String oldfName=(String) retMap.get("filename");
			SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");
			Date now = new Date();
			String nowtime = df.format(now);
			String fileName=oldfName.replaceAll("^(.+?)(\\d+?)\\.(.+)$", "$1_及其相关的"+otherTabsMap.size()+"张表"+nowtime+".$3");
			retMap.put("filename",fileName);

		retMap.put("success", true);
		return retMap;
	}*/
	/*public HashMap getVBAXlsmSize(String tableid) {
		HashMap retMap = new HashMap();

		HashMap dbMap = (HashMap) this.excelFileMapper.SelectModelTablecommonPoiService.SelectModelTableCache(tableid);
		String tableName = (String) dbMap.get("NAME");
		String suitid = (String) dbMap.get("SUITID");

		HashMap paraMap = new HashMap();
		String sql = " select  nvl(dbms_lob.getlength(VBAXLSMBLOB),0) VBAXLSMBLOB from dict_t_modelvba t where t.suitid ='" + suitid + "'";
		paraMap.put("AllinOneSQL", sql);
		List tmpList = this.excelFileMapper.SelectModelFixed(paraMap);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		BigDecimal tmpBig = (BigDecimal) tmpMap.get("VBAXLSMBLOB");
		retMap.put("VBAXLSMBLOBsize", tmpBig);

		return retMap;

	}*/
	/*public HashMap getXlsxSize(String tableid) {
		HashMap retMap = (HashMap) this.excelFileMapper.SelectXlsxSize(tableid);
		return retMap;

	}*/
	/*public HashMap getVBAXlsmFile(String tableid) {
		HashMap retMap = new HashMap();

		HashMap dbMap = (HashMap) this.excelFileMapper.SelectModelTablecommonPoiService.SelectModelTableCache(tableid);
		String tableName = (String) dbMap.get("NAME");
		String suitid = (String) dbMap.get("SUITID");

		HashMap resultMap = this.excelFileMapper.SelectXlsmVBATpl(suitid);
		byte[] vbaFile = (byte[]) resultMap.get("vbaxlsmBlob");
		String fn = (String) resultMap.get("name");
		retMap.put("filelob", vbaFile);
		retMap.put("filename", fn);
		return retMap;

	}

	public HashMap getXlsxOriginalFile(String tableid) {
		HashMap retMap = new HashMap();
		ExcelFilePO retPo = this.excelFileMapper.SelectXlsxOgnTpl(tableid);
		byte[] ognFile = (byte[]) retPo.getOriginalxlsxtpl();
		String fn = retPo.getName();
		retMap.put("filelob", ognFile);
		retMap.put("filename", fn);
		return retMap;

	}

	public HashMap getXlsxNullTpl(String tableid) {
		HashMap retMap = new HashMap();
		ExcelFilePO retPo = this.excelFileMapper.SelectXlsxNullTpl(tableid);
		byte[] nullFile = (byte[]) retPo.getNullxlsxtpl();
		String fn = retPo.getName();
		retMap.put("filelob", nullFile);
		retMap.put("filename", fn);
		return retMap;

	}

	public HashMap getXlsxDatapart(String tableid) {
		HashMap retMap = new HashMap();
		ExcelFilePO retPo = this.excelFileMapper.SelectXlsxDatapart(tableid);
		String dataFile = (String) retPo.getDatapartxlsx();
		String fn = retPo.getName();
		retMap.put("filelob", dataFile);
		retMap.put("filename", fn);
		return retMap;
	}

	public HashMap getXlsxStylepart(String tableid) {
		HashMap retMap = new HashMap();
		ExcelFilePO retPo = this.excelFileMapper.SelectXlsxStylepart(tableid);
		String styleFile = (String) retPo.getStylepartxlsx();
		String fn = retPo.getName();
		retMap.put("filelob", styleFile);
		retMap.put("filename", fn);
		return retMap;

	}*/

	/*public String WriteTmpXlsx(byte[] bais, String orgFilename, String tmpDir) {
		boolean bExcel2010 = true;
		if (!orgFilename.matches("(?i).*xls[a-z]$"))
			bExcel2010 = false;

		String fname = tmpDir;// System.getenv("temp");;
		try {

			fname += orgFilename;
			FileOutputStream fos = new FileOutputStream(fname);
			InputStream is = new ByteArrayInputStream(bais);
			int len = 0;
			int iascii = is.read();
			if (iascii != 80)
				bExcel2010 = false;
			fos.write(iascii);
			iascii = is.read();
			if (iascii != 75)
				bExcel2010 = false;
			fos.write(iascii);
			if (!bExcel2010) {
				fos.close();
				throw new IllegalStateException("传入的Excel模板文件:" + orgFilename
						+ " 不是Excel2010格式!");
			}

			while ((len = is.read()) != -1) {

				fos.write(len);
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fname;
	}*/


	/*public HashMap generateXlsxSuitFromDB(String tableid,HttpServletRequest request) throws Exception  {
		HashMap retMap = new HashMap();
		HashMap paraMap = new HashMap();
		String sql = "select t.name,t.tableid,(SELECT  suitname FROM dict_t_suit where suitid=t.suitid) suitname from DICT_T_MODEL t where t.suitid="
				+ "(select suitid from DICT_T_MODEL t2 where t2.TABLEID='"
				+ tableid + "')";
		paraMap.put("AllinOneSQL", sql);
		List tmpList = this.excelFileMapper.SelectModelFixed(paraMap);

		for (int i = 0; i < tmpList.size(); i++) {
			HashMap rowMap = (HashMap) tmpList.get(i);
			String tablename = (String) rowMap.get("NAME");
			String tabid1 = (String) rowMap.get("TABLEID");
			String suitName = (String) rowMap.get("SUITNAME");
			HashMap rM = generateXlsxFromDB(tabid1,request);
			retMap.put("success", rM.get("success"));
			// logger.info("正在生成模板表:【"+suitName+"】"+tablename);
			System.out.println("正在生成模板表:" + (i + 1) + "【" + suitName + "】"
					+ tablename);
		}

		return retMap;

	}*/

	/*public HashMap generateXlsxFromDB(String tableid,HttpServletRequest request) throws Exception  {
		HashMap retMap = new HashMap();
		HashMap dbMap = (HashMap) this.excelFileMapper.SelectModelTablecommonPoiService.SelectModelTableCache(tableid);
		String tableName = (String) dbMap.get("NAME");
		String dealType = (String) dbMap.get("DEALTYPE");
		HashMap resultMap = null;
		if ("A1".equalsIgnoreCase(dealType)) // 固定行列表
		{
			resultMap = fixedPoiService.generateXlsxTemplate(tableid,request);

		} else if ("A2".equalsIgnoreCase(dealType)) // 浮动表
		{
			resultMap = floatPoiService.generateXlsxTemplate(tableid,request);
		} else if ("A0".equalsIgnoreCase(dealType)||"A0*01".equalsIgnoreCase(dealType)) // 一般录入报表
		{
			resultMap = commonPoiService.generateXlsxTemplate(tableid,request);
		} else if ("62".equalsIgnoreCase(dealType)) // 基本数字表
		{
			resultMap = baseNumberPoiService.generateXlsxTemplate(tableid,request);
		}
		retMap.put("success", resultMap.get("success"));
		return retMap;

	}*/


	/*public HashMap saveXlsmTpl(byte[] bais, String orgFilename, String tableid) {
		HashMap retMap = new HashMap();
		HashMap dbMap = (HashMap) this.commonPoiService.SelectModelTableCache(tableid);
		String tableName = (String) dbMap.get("NAME");
		String suitid = (String) dbMap.get("SUITID");
		try {
			HashMap paraMap = new HashMap();
			String sql = " select count(*) suitidrownum from dict_t_modelvba t where t.suitid ='"
					+ suitid + "'";
			paraMap.put("AllinOneSQL", sql);
			List tmpList = this.excelFileMapper.SelectModelFixed(paraMap);
			HashMap tmpMap = (HashMap) tmpList.get(0);
			BigDecimal tmpBig = (BigDecimal) tmpMap.get("SUITIDROWNUM");

			HashMap map = new HashMap();
			map.put("tableid", tableid);
			map.put("suitid", suitid);
			map.put("name", orgFilename);
			map.put("vbaxlsmBlob", (Object) bais);
			map.put("vbaxlaBlob", null);
			if (tmpBig.intValue() < 1)
				this.excelFileMapper.insertXlsmTpl(map);
			else
				this.excelFileMapper.updateXlsmTpl(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		retMap.put("success", true);
		retMap.put("errorInfo", "上传Excel模板文件成功!");

		if (!this.logger.isDebugEnabled()) {
			// FileFolderUtil.DeleteFolder(tmppath);
		}
		return retMap;
	}*/

	/*//上传模板文件
	public HashMap handleAndSaveExcelTpl(byte[] bais, String orgFilename,String tableid) throws IOException {

		HashMap retMap = new HashMap();
		HashMap dbMap = (HashMap) this.commonPoiService.SelectModelTableCache(tableid);
		String tableName = (String) dbMap.get("NAME");

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
				retMap.put("errorInfo", "传入的Excel中没有找到sheet：\"" + tableName
						+ "\" 的工作表！\r\n模板表导入失败！");
				return retMap;
			}
			String datajson = objectMapper.writeValueAsString(notLockWbCellMap);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			byte[] filenull = baos.toByteArray();
			System.out.println("excel模板文件大小:" + filenull.length);
			ExcelFilePO epo = new ExcelFilePO();
			epo.setTableid(tableid);
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
	/*public void WorkbookAllCellsCalc(XSSFWorkbook wb) {
		FormulaEvaluator evaluator = wb.getCreationHelper()
				.createFormulaEvaluator();
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
	}*/
}