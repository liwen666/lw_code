package commons.excel.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTFactorPO;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.inputcomponent.grid.propertygrid.po.PropertyGrid;
import com.tjhq.commons.inputcomponent.po.PageInfo;
@Service
public class BaseNumberPoiService extends BasePoiService {
	public BaseNumberPoiService() {
		super();
		importExcelSaveDBMethod="3";
	}
	public HashMap getdataPageinfoFormJson(HashMap codeMap, DictTModelPO dictTModel, String grid, String tableID, HashMap preMap,HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException, Exception, SQLException {
		byte[] filexlsx1=null;
		PageInfo dataPageInfo1 = null;
	    HashMap retMap=new HashMap();
		PropertyGrid localPropertyGrid = (PropertyGrid) new ObjectMapper().readValue(grid, PropertyGrid.class);
		this.formulaUtilService.synchRefreshFormula(localPropertyGrid.getTableID(),(String) localPropertyGrid.getExtProperties().get("agencyID"));
		dataPageInfo1 = (PageInfo) this.propertyGridService.getData(localPropertyGrid);
		HashMap resultMap= generateXlsxTemplate(codeMap, dictTModel, tableID, request);
		retMap.put("errorInfo", resultMap.get("errorInfo"));
		retMap.put("successInfo", resultMap.get("successInfo"));
		retMap.put("success", resultMap.get("success"));
		if (retMap.get("success") == null|| !(Boolean) retMap.get("success")) {
			return retMap;
		}

		filexlsx1=(byte[]) resultMap.get("excelDataTemplate");
	      
		preMap.put("filexlsx", filexlsx1);
		preMap.put("dataPageInfo", dataPageInfo1);
		return retMap;
	}
	/*public HashMap getDataPageInfoAndXlsxFromDB(HashMap rowMap, String agencyID,HttpServletRequest request) throws Exception {
		HashMap retMap = new HashMap();
		PageInfo dataPageInfo = null;

		String tablename = (String) rowMap.get("NAME");
		String tabid1 = (String) rowMap.get("TABLEID");
		String suitName = (String) rowMap.get("SUITNAME");
		String appid1 = (String) rowMap.get("APPID");
		String userID = SecureUtil.getCurrentUser().getGuid();

		HashMap dbMap = (HashMap) this.xlsx.SelectModelTableSelectModelTableCache(tabid1);
		String tableName = (String) dbMap.get("NAME");

		
		HashMap resultMap= generateXlsxTemplate(tabid1,request);
		retMap.put("errorInfo", resultMap.get("errorInfo"));
		retMap.put("successInfo", resultMap.get("successInfo"));
		retMap.put("success", resultMap.get("success"));
		if (retMap.get("success") == null|| !(Boolean) retMap.get("success")) {
			return retMap;
		}
		HashMap dXlsxMap = getXlsxDatapart(tabid1);
		String dataJson = (String) dXlsxMap.get("filelob");
		HashMap dXlsxNull = this.getXlsxNullTpl(tabid1);
		byte[] filexlsx=(byte[]) resultMap.get("excelDataTemplate");
		if(filexlsx==null)
		    filexlsx = (byte[]) dXlsxNull.get("filelob");
		HashMap agencyMap = (HashMap) this.excelFileMapper.SelectDivName(agencyID);
		//文件名
		String outFilename = super.getFileName(agencyMap, tableName);

		XSSFtjhqWorkbook wb;
		wb = new XSSFtjhqWorkbook(true);
		XSSFWorkbook wbdb = new XSSFWorkbook(new ByteArrayInputStream(filexlsx));
		for (XSSFSheet sht1 : wbdb) {
			XSSFSheet tmpSheet = wb.copySheetFromOtherXLSX(sht1, wbdb);
		}
		HashMap dataTplMap = (HashMap) new ObjectMapper().readValue(dataJson,
				HashMap.class);
		Object sht = dataTplMap.get(tableName);
		if (sht == null) {
			retMap.put("success", false);
			retMap.put("errorInfo", "系统中的的Excel模板有问题,中没有找到sheet：" + tableName	+ "的工作表！\r\n数据表导出失败！");
			return retMap;
		}

		String UserGuid = SecureUtil.getCurrentUser().getGuid();
		Condition cnd = new Condition();
		cnd.setColumnDbName("AGENCYID");
		cnd.setOperator("=");
		cnd.setQueryValue(agencyID);
		ArrayList conditonList = new ArrayList();
		conditonList.add(cnd);
		LinkedHashMap exTmap = new LinkedHashMap();
		exTmap.put("tableID", tabid1);
		exTmap.put("agencyID", agencyID);
		HashMap paraMap=new HashMap();
		String sql="select  f.DBCOLUMNNAME,t.COLUMNID   from dict_t_setbasenumtab t, dict_t_factor f where t.TABLEID =  '"+tabid1+"'   and t.COLUMNID = f.COLUMNID";
		 
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap=(HashMap) tmpList.get(0);
		String extFieldname =(String) tmpMap.get("DBCOLUMNNAME");// "FINYEAR";
		String extFieldid=(String) tmpMap.get("COLUMNID");
		Condition cnd2 = new Condition();
		cnd2.setColumnDbName(extFieldname);
		cnd2.setOperator("in");
		sql=" select   wmsys.wm_concat(t.COLVALUE) years from dict_t_setbasenumsub t  where t.COLUMNID ='"+extFieldid+"'";
		 
		  tmpList = this.excelFileMapper.selectExcelInfo(sql);
		  tmpMap=(HashMap) tmpList.get(0);
		java.sql.Clob clob = (java.sql.Clob) tmpMap.get("YEARS"); //"FINYEAR";
		String YEARS = (clob == null || clob.length() == 0) ? "": clob.getSubString(1, (int) clob.length());

		cnd2.setQueryValue(YEARS"2015,2016");
		conditonList.add(cnd2);

		PropertyGrid localPropertyGrid = new PropertyGrid();
		localPropertyGrid.setTableID(tabid1);
		this.propertyGridService.initStructure(localPropertyGrid, userID);
		localPropertyGrid.setColumnList(null);
		localPropertyGrid.setExtProperties(exTmap);
		localPropertyGrid.setQueryPanelParamList(conditonList);
		this.formulaUtilService.synchRefreshFormula(localPropertyGrid.getTableID(),	(String) localPropertyGrid.getExtProperties().get("agencyID"));
		dataPageInfo = (PageInfo) this.propertyGridService.getData(localPropertyGrid);
		HashMap selMap = selectFixedGridData(dataPageInfo, tabid1,  agencyID, 0);
		HashMap dataMap = (HashMap) selMap.get("data");
		boolean bColRowConvert = true;
		filldataGrid(retMap, dataMap, dataPageInfo, dataTplMap,wb,request);

		WorkbookAllCellsCalc(wb);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] databytes = baos.toByteArray();
		retMap.put("filelob", databytes);
		retMap.put("filename", outFilename);
		return retMap;
	}*/
	public HashMap selectFixedGridData(HashMap codeMap, DictTModelPO dictTModel, PageInfo dataPageInfo, String tableID, String DefaultAgencyID, int SumHeadCount, HashMap fieldTypeMap) { // River修改20151230
		HashMap retMap = new HashMap();
		List dataL = null;
		List columnList = null;

//		String sql = " select max(levelno) headrownum from DICT_T_FACTOR t where t.tableid ='" + tableID + "' and t.ISVISIBLE = '1'";
//		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
//		HashMap tmpMap = (HashMap) tmpList.get(0);
//		BigDecimal tmpBig = (BigDecimal) tmpMap.get("HEADROWNUM");
//		int headrownum = tmpBig.intValue();
		startfillrownum = 2;
		dataL = dataPageInfo.getDataList();
		columnList = dataPageInfo.getColumns();
		HashMap colmap = GetXlsxDataFormDBII(tableID);
		HashMap data = new HashMap();
		HashMap cacheMap = new HashMap();
		HashMap tableLevelInfoMap = GetTableLevelInfo(tableID);
		LinkedHashMap composeKeyMap = (LinkedHashMap) fieldTypeMap.get("composeKey-Key");
		String composeKeys = "";
		//boolean isDatablank = false;
		data.put("thisTableXlsxTotalRownum", dataL.size());
		String phytableName = dictTModel.getDbtablename();
		HashMap bntMap = getBaseNumberTableinfo(codeMap, tableID, phytableName);
		String headField = (String) bntMap.get("headField");
		String headFieldvals = (String) bntMap.get("headFieldvals");
		String[] headFieldvalsArray = headFieldvals.split(",");
		int extColsCC = headFieldvalsArray.length;
		HashMap headFieldvalsMap = new HashMap();
		for(int ie=0;ie<extColsCC;ie++) {
			String headFieldval=headFieldvalsArray[ie].replaceAll("'","");
			headFieldvalsMap.put(headFieldval, ie);
		}
		for (int ir = 0; ir < dataL.size(); ir++) {
			List rowData = (List) dataL.get(ir);
			HashMap rowMap = new HashMap();
			LinkedHashMap curComposeKeyMap = (LinkedHashMap) composeKeyMap.clone();
			for (int ic = 0; ic < columnList.size(); ic++) {
				String fieldName = (String) columnList.get(ic);
				rowMap.put(fieldName, rowData.get(ic));
			}
			String curExtcolv=(String) rowMap.get(headField);
			Integer iext=(Integer) headFieldvalsMap.get(curExtcolv);
			if (!rowMap.containsKey("AGENCYID") && ir == 0)
			{
				columnList.add("AGENCYID");
			}
			for (int ic = 0; ic < columnList.size(); ic++) {
				String fieldName = (String) columnList.get(ic);
				HashMap fieldCodeM = (HashMap) cacheMap.get(tableID + "-" + fieldName);
				if (fieldCodeM == null) {
					fieldCodeM = getElementCodeName(codeMap, tableID, fieldName, true);
					cacheMap.put(tableID + "-" + fieldName, fieldCodeM);
				}
				if (curComposeKeyMap.containsKey(fieldName)) {
					curComposeKeyMap.put(fieldName, (String) rowData.get(ic));
				}
				String xlsxcol = (String) colmap.get("dbcolname_" + fieldName);
				if (xlsxcol == null || xlsxcol.equalsIgnoreCase("")) {
					if(iext != null)
						data.put("Hide_" + fieldName + "." + (iext.intValue() + 1 + startfillrownum), rowData.get(ic));
					else
					    data.put("Hide_" + fieldName + "." + (ir + 1 + startfillrownum), rowData.get(ic));
				} else {
					Object cv = null;
					if (fieldTypeMap.containsKey(fieldName)) {
						HashMap colinfoMap = (HashMap) fieldTypeMap.get(fieldName);
						double type = (Double) colinfoMap.get("DATATYPE");
						cv = rowData.get(ic);
					} else
						cv = rowData.get(ic);
					if ("agencyid".equalsIgnoreCase(fieldName) && ir == 0 && SumHeadCount > 0 && cv == null)
					{
						cv = DefaultAgencyID;
					}
					if (fieldCodeM.size() > 0) {
						cv = fieldCodeM.get(cv);
					}
					String levelField = (String) tableLevelInfoMap
							.get(fieldName);
					if (levelField != null) {
						Object o = rowMap.get(levelField);
						int levelcc = 0;
						if (o == null) {
							levelcc = 0;
						} else if (o.getClass().getName()
								.endsWith("BigDecimal")) {
							BigDecimal lv = (BigDecimal) o;
							levelcc = (lv == null ? 0 : lv.intValue());
						} else if (o.getClass().getName()
								.endsWith("String")) {
							levelcc = Integer.valueOf((String) o);
						}
						String preSpace = generateSpace((levelcc - 1) * 3);
						if (preSpace.length() > 0)
							cv = preSpace + cv;
					}

					if(iext != null)
						data.put(xlsxcol + (iext.intValue() + 1 + startfillrownum), cv);
					else
					    data.put(xlsxcol + (ir + 1 + startfillrownum), cv);
				}

			}
			boolean isAddKey = true;
			String keyValues = "";
			if (!"".equalsIgnoreCase(composeKeys))
				isAddKey = false;
			for (Iterator it = curComposeKeyMap.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				if (isAddKey) {
					composeKeys = composeKeys + key + splitstr;
				}
				keyValues = keyValues + curComposeKeyMap.get(key) + splitstr;
			}
			data.put("composeKey." + (ir + startfillrownum), keyValues); // River修改20151230
		}
		// 空的一般录入表加入空行
		if (dataL.size() == 0) {
			for (Iterator itc = colmap.keySet().iterator(); itc.hasNext();) {
				String key = (String) itc.next();
				if (!key.contains("dbcolname_"))
					continue;
				String fieldName = key.split("_")[1];
				HashMap fieldCodeM = (HashMap) cacheMap.get(tableID + "-" + fieldName);
				if (fieldCodeM == null) {
					fieldCodeM = getElementCodeName(codeMap, tableID, fieldName, true);
					cacheMap.put(tableID + "-" + fieldName, fieldCodeM);
				}
				for (int ir = 0; ir < extColsCC; ir++)
				{
					LinkedHashMap curComposeKeyMap = (LinkedHashMap) composeKeyMap.clone();
					String xlsxcol = (String) colmap.get(key);
					if (xlsxcol == null || xlsxcol.equalsIgnoreCase("")) {

					} else {
						Object cv = null;
						if (fieldTypeMap.containsKey(fieldName)) {
						}
						if ("agencyid".equalsIgnoreCase(fieldName))
						{
							cv = DefaultAgencyID;
						}
						if (fieldCodeM.size() > 0) {
							cv = fieldCodeM.get(cv);
						}
						data.put(xlsxcol + (ir +1+ startfillrownum), cv);
					}
					boolean isAddKey = true;
					String keyValues = "";
					if (!"".equalsIgnoreCase(composeKeys))
						isAddKey = false;
					for (Iterator it = curComposeKeyMap.keySet().iterator(); it.hasNext();) {
						String key1 = (String) it.next();
						if (isAddKey) {
							composeKeys = composeKeys + key1 + splitstr;
						}
						keyValues = keyValues + "" + splitstr;
					}
					data.put("composeKey." + (ir + startfillrownum), keyValues); // River修改20151230
				}
			}
		}
		data.put("thisTableXlsxTotalRownum", extColsCC);
		data.put("composeKey-keys", composeKeys);
		retMap.put("data", data);
		return retMap;
	}

	public void filldataGrid(HashMap codeMap, DictTModelPO dictTModel, HashMap retMap, HashMap dataMap,PageInfo dataPageInfo, HashMap dataTplMap, XSSFWorkbook wb, HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException {
 		Iterator shtIt = dataTplMap.keySet().iterator();
		int totalRows = (Integer) dataMap.get("thisTableXlsxTotalRownum");
		while (shtIt.hasNext()) {

			String shtName = (String) shtIt.next();
			if (shtName.endsWith("$"))
				continue;
			XSSFSheet sheet = wb.getSheet(shtName);
            int lastCC=sheet.getLastRowNum();
			Row lastRow=sheet.getRow(lastCC);
			Cell hideCell=lastRow.getCell(1);
			String keyFiledL = (String) dataMap.get("composeKey-keys");
			hideCell.setCellValue(keyFiledL);
			//2开始填充行和列
			boolean bColRowConvert=true;

			for(int ic=2;ic<2+totalRows;ic++)
			{
				for(int ir=2;ir<lastCC;ir++)
				{
				    Row row = sheet.getRow(ir);
				    XSSFCell cell=(XSSFCell) row.getCell(ic);
				    String celladdr=cell.getReference();
					String CellName2 = celladdr;
					if (bColRowConvert)
						CellName2 = sheetColRowConvert(2, 2, celladdr);

					String CellValue = (String) dataMap.get(CellName2);
					if (CellValue != null || "".equalsIgnoreCase(CellValue)) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC: {
							cell.setCellValue(Double.valueOf(CellValue));
							break;
						}
						case Cell.CELL_TYPE_STRING: {
							cell.setCellValue(CellValue);
							break;
						}

						default: {
							String regex2 = "^-?\\d+\\.?\\d*$";
							Pattern ptn2 = Pattern.compile(regex2);
							Matcher m2 = ptn2.matcher(CellValue);
							if (m2.find() && CellValue.length() <= 15) {
								cell.setCellValue(Double.valueOf(CellValue));
							} else {
								cell.setCellValue(CellValue);
							}
						}
						}
					} else {
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
								cell.setCellType(3);
							}
						}
					}

				}
				  hideCell=lastRow.getCell(ic);
				if (hideCell == null) {
					hideCell = (XSSFCell) lastRow.createCell(ic);
				}
				String curKey = (String) dataMap.get("composeKey." + (ic ));
				if (curKey != null)
					hideCell.setCellValue(curKey);
			}
		}

	}
	
	// 判断导入excel操作前后的逻辑主键是否重复。
	public HashMap checkKeysCount(HashMap codeMap, DictTModelPO dictTModel, List dataL,String tableid,String checkinfoHead,HashMap agencyLstMap) throws Exception {
		
		HashMap retMap = new HashMap();
		retMap.put("checkInfo", "");
		String dbTableName = dictTModel.getDbtablename();
		HashMap paraMap = new HashMap();
		HashMap elementCacheMap = new HashMap();
		String sql;
		List tmpList;
		int lastPos;
		StringBuffer sqlBUF=new StringBuffer();
		sqlBUF.append("select ");

			HashMap rowKeyMap=(HashMap)dataL.get(0);
			if (rowKeyMap.size()==0) return retMap;
			for(Iterator itk=rowKeyMap.keySet().iterator();itk.hasNext();)
			{
				String k=(String)itk.next();
				sqlBUF.append(k).append(",");
			}
			sqlBUF.append("count(*) cc from ").append(dbTableName).append(" t where exists (select 1 from (" );
			for(Iterator ita=agencyLstMap.keySet().iterator();ita.hasNext();)
			{
				String k=(String)ita.next();
				sqlBUF.append("select '").append(k).append("' agencyid from dual union all ");
			}
			lastPos=sqlBUF.lastIndexOf("union all");
			sqlBUF.replace(lastPos, lastPos + 9, " ");
			sqlBUF.append(") t2 where t2.agencyid = t.agencyid) group by ");
			for(Iterator itk=rowKeyMap.keySet().iterator();itk.hasNext();)
			{
				String k=(String)itk.next();
				sqlBUF.append(k).append(",");
			}
			lastPos=sqlBUF.lastIndexOf(",");
			sqlBUF.replace(lastPos, lastPos + 1, " ");
			sqlBUF.append(" having count(*)>1 ");
			sql=sqlBUF.toString();
			
			tmpList = this.excelFileMapper.selectExcelInfo(sql);
			StringBuffer retStrBuf=new StringBuffer();
			for(int ir=0;ir<tmpList.size();ir++) {
				HashMap map=(HashMap) tmpList.get(ir); 
				if(ir==0)
				     retStrBuf.append(checkinfoHead+", 发现"+tmpList.size()+"行: \r\n");
				for(Iterator itr=map.keySet().iterator();itr.hasNext();) {
					String k=(String)itr.next();
					if(!"CC".equalsIgnoreCase(k)) {
					   String v=(String)map.get(k);
					   
					   LinkedHashMap elevalues=(LinkedHashMap) elementCacheMap.get(tableid+"-"+k);
					   if(elevalues==null) {
						   elevalues = getElementCodeName(codeMap, tableid, k, true, "itemid");
						   elementCacheMap.put(tableid+"-"+k,elevalues);
					   }
					   if(elevalues !=null && elevalues.size() >0) {
						   v=(String) elevalues.get(v);
					   }
					   retStrBuf.append(k).append(":").append(v).append(",");
					}
				}
				retStrBuf.append("  出现次数:").append(((BigDecimal)map.get("CC")).toString()).append("次;\r\n ");
			}
 			retMap.put("checkInfo", retStrBuf.toString());
		return retMap;
	}
	@Override
	public HashMap handleAndSaveExcelData(HashMap codeMap, DictTModelPO dictTModel, HashMap configMap,HashMap excelElevalueguidMap,XSSFWorkbook wb,String curTableid, HashMap paramMap,HttpServletRequest request) throws Exception {
		HashMap retMap = new HashMap();
		String agencyID = (String) paramMap.get("agencyID");

		HashMap fieldsInfoMap = GetXlsxFieldTypeFormDB(curTableid);
		HashMap curtabdefMap = (HashMap) configMap.get(curTableid);
		String curShtname = (String) curtabdefMap.get("sheetName");
		String curShtsumFieldCC = (String) curtabdefMap.get("curShtsumFieldCC");
		String curTableType = (String) curtabdefMap.get("curTableType");
		LinkedHashMap tabdefFieldsMap = (LinkedHashMap) curtabdefMap.get("tabdefFieldsMap");
		HashMap elementsMap = new HashMap();
		for (Iterator it2 = tabdefFieldsMap.keySet().iterator(); it2.hasNext();) {
			int fkey = (Integer) it2.next();
			String fieldinfo = ((String) tabdefFieldsMap.get(fkey)).split(splitstr)[0];
			LinkedHashMap eleMap = getElementCodeName(codeMap, curTableid, fieldinfo, false, "itemid");
			if (eleMap != null && eleMap.size() > 0) {
				elementsMap.put(fieldinfo, eleMap);
			}
		}
		Sheet sheet = wb.getSheet(curShtname);
		if (sheet == null)
			return returnErrorInfo("Excel文件中未找到sheetname为【" + curShtname + "】工作表!");
		if (!sheet.getSheetName().equalsIgnoreCase(firstSheetName) && !sheet.getSheetName().equalsIgnoreCase(hideConfigSheetName)) {
			int snICC = 0;
			int xlsxRowStart = 2;
			int colnum = xlsxRowStart;
			int fieldcol = 1;
			for (int ic = colnum; ic < sheet.getLastRowNum() - 1; ic++) {
				Row xssfrow = sheet.getRow(ic);
				Cell cell = xssfrow.getCell(fieldcol);
				String fieldname = cell.getStringCellValue();
				fieldname = fieldname.trim();
				String[] dbfieldinfo = ((String) tabdefFieldsMap.get(ic - colnum + 1)).split(splitstr); // 0
				if (!fieldname.equalsIgnoreCase(dbfieldinfo[1].trim()) && !fieldname.equalsIgnoreCase(dbfieldinfo[1].trim()+fieldIsNullMark)) {   //River修改20151230
					return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的表头字段名称:｛" + fieldname + "｝未找到对应的数据库信息!");
				}
			}
			Row lastRow = sheet.getRow(sheet.getLastRowNum());
			String hideColfieldsinfoStr = lastRow.getCell(fieldcol).getStringCellValue();
			String[] hideColfieldsinfo = hideColfieldsinfoStr.split(splitstr);
			int datakeyPos = -1;
			for (int ik = 0; ik < hideColfieldsinfo.length; ik++) {
				if ("DATAKEY".equalsIgnoreCase(hideColfieldsinfo[ik])) {
					datakeyPos = ik;
					break;
				}
			}
			HashMap KeyFieldMap = (HashMap) fieldsInfoMap.get("composeKey-Key");
			HashMap nonNullMap = (HashMap) fieldsInfoMap.get("nonNullMap-Key");
			ArrayList AllsavedataList = new ArrayList();
			ArrayList InsertsavedataList = new ArrayList();
			ArrayList DelsavedataList = new ArrayList();
			ArrayList UpdatesavedataList = new ArrayList();
			
			HashMap keysMap = new HashMap();
			ArrayList keysList = new ArrayList();
			HashMap agencyLstMap = new HashMap();
			agencyLstMap.put(agencyID, "1");
			HashMap virtrulFieldsMap = new HashMap();
			HashMap curTableDataKeyMap=getExistsDataKeyByTableidAgencyid(curTableid, agencyID, dictTModel);
			int lastc = 0;
			Row titleRow = sheet.getRow(1);
			String phytableName = dictTModel.getDbtablename();
			HashMap bntMap = getBaseNumberTableinfo(codeMap, curTableid, phytableName);
			String headField = (String) bntMap.get("headField");
			tabdefFieldsMap.put(tabdefFieldsMap.size()+10, headField+splitstr+"基本数字表横向扩展");
			String headFieldvals = (String) bntMap.get("headFieldvals");
			String[] headFieldvalsArray = headFieldvals.split(",");
			int extColsCC = headFieldvalsArray.length;
			for (int ic = colnum; ic < titleRow.getLastCellNum(); ic++) {

				HashMap saveRowMap = new HashMap();
				lastc = sheet.getLastRowNum() - 1;
				String curExtproperty=titleRow.getCell(ic).getStringCellValue();
				if (!headFieldvalsArray[ic-colnum].replaceAll("'", "").equalsIgnoreCase(curExtproperty)) {   //River修改20151230
					return returnErrorInfo("Excel文件中sheetname为【"
							+ curShtname + "】基本数字表的表头扩展字段名称:｛" + curExtproperty
							+ "｝未找到对应的数据库信息!");
				}
				saveRowMap.put(headField, curExtproperty.trim());
				for (int ir = xlsxRowStart; ir <= lastc; ir++) {
					//River修改20151230 end
					Row row = sheet.getRow(ir);
					Cell cell = row.getCell(ic);
					String[] dbfieldinfo = ((String) tabdefFieldsMap.get(ir - xlsxRowStart + 1)).split(splitstr); // 0
					LinkedHashMap eleMap = (LinkedHashMap) elementsMap.get(dbfieldinfo[0]);
					String elementTablename = (String) excelElevalueguidMap.get(curTableid + splitstr2 + dbfieldinfo[0]);
					HashMap ExceleleMap = (HashMap) excelElevalueguidMap.get("elementTablename" + splitstr2 + elementTablename);
					if (dbfieldinfo[0] == null || dbfieldinfo[0].equalsIgnoreCase("null"))
					{
						if (!virtrulFieldsMap.containsKey(ir - xlsxRowStart + 1))
							virtrulFieldsMap.put(ir - xlsxRowStart + 1, dbfieldinfo[0]);
						continue;
					}
					HashMap colinfoMap = (HashMap) fieldsInfoMap.get(dbfieldinfo[0]);
					double fieldtype = (Double) colinfoMap.get("DATATYPE");
					boolean isblank = false;
					String isVirtual = (String) colinfoMap.get("ISVIRTUAL"); // 虚列跳过存储
					String ISBANDCOL = (String) colinfoMap.get("ISBANDCOL");
					if ("1".equalsIgnoreCase(isVirtual) || "1".equalsIgnoreCase(ISBANDCOL))
					{
						if (!virtrulFieldsMap.containsKey(ir - xlsxRowStart + 1))
							virtrulFieldsMap.put(ir - xlsxRowStart + 1, dbfieldinfo[0]);
						continue;
					}
					if (fieldtype == 1 || fieldtype == 2) {
						if (cell.getCellType() == Cell.CELL_TYPE_STRING
								&& "".equalsIgnoreCase(cell.getStringCellValue())
								|| cell.getCellType() == Cell.CELL_TYPE_BLANK) {
							
							saveRowMap.put(dbfieldinfo[0], new Double(0));
							isblank = true;
						} else
							saveRowMap.put(dbfieldinfo[0],
									cell.getNumericCellValue());
					} else {
						String v;
						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
						{
							// 判断单元格是否属于日期格式  
							if(DateUtil.isCellDateFormatted(cell)){
								Date date2 = cell.getDateCellValue();
								DictTFactorPO a = dictTFactorService.getDictTFactorByDBColumnName(dbfieldinfo[0], curTableid);
								String colformat = a.getColformat();
								if(StringUtils.isEmpty(colformat)){
									colformat = "yyyy-MM-dd";
								}
						        SimpleDateFormat dff = new SimpleDateFormat(colformat); 
						        v=dff.format(date2);
							}else{
								java.text.DecimalFormat df = new java.text.DecimalFormat("#");  
								Double dv=cell.getNumericCellValue();
								v=df.format(dv);
							}
						}
						else
						{
							v = cell.getStringCellValue();
						}
						if (eleMap != null)
						{
							v=v.trim();
							String excelEleGuid = (String) ExceleleMap.get(v);
							if (eleMap.containsKey(excelEleGuid))
								v = excelEleGuid;
							else
							{
								v = null;
								isblank = true;
							}
						}
						if ("".equalsIgnoreCase(v) || v == null)
							isblank = true;
						saveRowMap.put(dbfieldinfo[0], v);
					}
					if (nonNullMap.containsKey(dbfieldinfo[0])
							&& isblank) {
						return returnErrorInfo("Excel文件中sheetname为【"
								+ curShtname + "】工作表的第" + (ir + 1)
								+ "行字段名称（" + dbfieldinfo[0] + "）不能为空!");
					}
				}
				// 关键字处理
				Row row = sheet.getRow(sheet.getLastRowNum());
				Cell lastcell = row.getCell(ic);
				String hideColinfo = "";
				if (lastcell.getCellType() == Cell.CELL_TYPE_STRING)
					hideColinfo = lastcell.getStringCellValue();
				StringBuffer userFillkeys = new StringBuffer();
				HashMap curKeysMap = new HashMap();
				String curRowDataKey = "";
				for (Iterator itk = KeyFieldMap.keySet().iterator(); itk.hasNext();)
				{
					String dbkey = (String) itk.next();
					String key1 = "";
					if ("AGENCYID".equalsIgnoreCase(dbkey))
						key1 = agencyID;
					else if ("DATAKEY".equalsIgnoreCase(dbkey))
					{
						// 判断hideColinfo的值
						String[] keys = hideColinfo.split(splitstr);
						if (datakeyPos < keys.length && datakeyPos >= 0)
						{
							key1 = keys[datakeyPos];
							if (key1 != null && !"".equalsIgnoreCase(key1))
								saveRowMap.put("DATAKEY", key1);
						}
						continue;
					}
					else
					{
						key1 = (String) saveRowMap.get(dbkey);
					}
					if ("".equalsIgnoreCase(key1))
						return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的第" + (ic + 1) + "行关键字段名称（" + dbkey + "）不能为空!");
					userFillkeys.append(key1).append(splitstr);
					curKeysMap.put(dbkey, key1);
				}
				// 不能为空处理
				if (!saveRowMap.containsKey("DATAKEY") || "".equalsIgnoreCase((String) saveRowMap.get("DATAKEY")))
				{
					String v = getGUID();
					saveRowMap.put("DATAKEY", v);

				}
				if (!saveRowMap.containsKey("STATUS")) {
					String v = "1";
					saveRowMap.put("STATUS", v);

				}
					saveRowMap.put("AGENCYID", agencyID);
				String composeKey = userFillkeys.toString();
				if (!"".equalsIgnoreCase(composeKey) && keysMap.containsKey(composeKey)) {
					return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的第" + (ic + 1) + "列关键字段\r\n｛" + hideColfieldsinfoStr + "｝\r\n的组合关键字段内容\r\n｛" + composeKey + "｝不能为重复!");
				} else {
					keysMap.put(composeKey, "1");
				}
				String curRowDatakey = (String) saveRowMap.get("DATAKEY");
				if (curTableDataKeyMap.containsKey(curRowDatakey)) {
					UpdatesavedataList.add(saveRowMap);
				} else {
					String gid = getGUID();
					saveRowMap.put("DATAKEY", gid);
					InsertsavedataList.add(saveRowMap);
					if(extColsCC<(curTableDataKeyMap.size()+InsertsavedataList.size()))
					{
						String agencyCodeName=(String) request.getAttribute("CuragencyCodeName");
						return returnErrorInfo("导入数据的行标识DATAKEY值缺失或行标识DATAKEY在库中_" +agencyCodeName + "_下不存在，数据导入方式为insert，Excel的第" + (ic + 1) + "列插入后库中【" + curShtname + "】基本数字表此单位的数据行数（"+(curTableDataKeyMap.size()+InsertsavedataList.size())+"行）将超过本表横向扩展属性（"+headFieldvals+"）的个数,插入失败!");
					}
				}

				AllsavedataList.add(saveRowMap);
				keysList.add(curKeysMap);
			}
			if (agencyLstMap.size() > 1)
			{
				return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的存在着" + agencyLstMap.size() + "个单位的数据 或 要导入单位编码与excel文件中的单位编码不相符!");
			}
			for (Iterator itv = virtrulFieldsMap.keySet().iterator(); itv.hasNext();)
			{
				Object key = itv.next();
				tabdefFieldsMap.remove(key);
			}
			HashMap resultMap;
			if ("1".equalsIgnoreCase(importExcelSaveDBMethod))
				resultMap = saveData1(tabdefFieldsMap, AllsavedataList, curTableid, paramMap);
			else if("2".equalsIgnoreCase(importExcelSaveDBMethod))
			{
				List onkeyList = new ArrayList();
				onkeyList.add("DATAKEY");
				onkeyList.add("AGENCYID");
				HashMap retM = checkKeysCount(codeMap, dictTModel, keysList, curTableid, "导入前主键重复", agencyLstMap);
				String checkInfo = (String) retM.get("checkInfo");
				if (checkInfo.length() > 0)
					return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表\r\n 导入前出现主键重复情况，不能导入！\r\n\r\n" + checkInfo);
				resultMap = saveData(tabdefFieldsMap, AllsavedataList, curTableid, paramMap, onkeyList, keysList, curShtname, wb);

				retM = checkKeysCount(codeMap, dictTModel, keysList, curTableid, "导入后主键重复", agencyLstMap);
				checkInfo = (String) retM.get("checkInfo");
				if (checkInfo.length() > 0)
					throw new RuntimeException("Excel文件中sheetname为【" + curShtname + "】工作表\r\n 导入后出现主键重复，导入失败!\r\n\r\n" + checkInfo);
			}
			else
			{
				List onkeyList = new ArrayList();
				onkeyList.add("DATAKEY");
				onkeyList.add("AGENCYID");
				if(UpdatesavedataList.size()>0)
				{
					resultMap = updateSaveData2DB(tabdefFieldsMap, UpdatesavedataList, curTableid, paramMap,onkeyList);
				}
			 
				if(InsertsavedataList.size()>0)
				{
					resultMap = insertSaveData2DB(tabdefFieldsMap, InsertsavedataList, curTableid, paramMap);
				}
				 
			 
			}
		}

		retMap.put("success", true);
		retMap.put("successInfo", "上传Excel数据文件成功!");
		return retMap;
	}
	public void dealTypeTableSql(String phytableName,  HashMap paramMap1) {
		String sql1="";
		String isExistsLevelCols1="0";
		paramMap1.put("sql", sql1);
		paramMap1.put("isExistsLevelCols", isExistsLevelCols1);
		
	}
	@Override
	public HashMap generateXlsxTemplate(HashMap codeMap, DictTModelPO dictTModel, String tableid, HttpServletRequest request) throws Exception {
		HashMap retMap = new HashMap();

		String phytableName = dictTModel.getDbtablename();
		String sheetName = dictTModel.getName();
		HashMap paraMap = new HashMap();
		String sql = "SELECT * FROM dict_t_factor u WHERE TABLEID = '"
				+ tableid
				+ "'  START WITH (SUPERID = '0' OR SUPERID IS NULL OR SUPERID = '') "
				+ " CONNECT BY PRIOR COLUMNID = SUPERID  "
				+ " ORDER SIBLINGS BY ORDERID, COLUMNID";
		
		List headsList = this.excelFileMapper.selectExcelInfo(sql);
		ArrayList DisplayColsList = new ArrayList();
		ArrayList HideColsList = new ArrayList();
		ArrayList ExcelColsList = new ArrayList();

		HashMap xlsxRowsMap = new HashMap();
		HashMap xlsxColsMap = new HashMap();
		int Maxhidecolnum = 0;
		String firstHideFieldname = "TEMPLATEID";
		 HashMap bntMap= getBaseNumberTableinfo(codeMap, tableid, phytableName);
		 String headField=(String)bntMap.get("headField");
		 String headFieldvals=(String)bntMap.get("headFieldvals");
	 
		XSSFWorkbook wb = new XSSFWorkbook();
		HashMap cacheMap = new HashMap();

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
		font2.setFontHeightInPoints((short) 16);
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setFont(font2);
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

		xssfrow = sheet.createRow(1);
		cell = xssfrow.createCell((short) 0);
		cell.setCellValue("序号");
		cell.setCellStyle(headStyle);
		cell = xssfrow.createCell((short) 1);
		cell.setCellValue("属性");
		cell.setCellStyle(headStyle);
		StringBuffer hideColsStrBuf = new StringBuffer();
		StringBuffer ExcelHideColsStrBuf = new StringBuffer();
		hideColsStrBuf.append(firstHideFieldname).append(splitstr);
		int xlsxRowStart = 2;
		int colnum = xlsxRowStart;
		StringBuffer batsqlBUF=new StringBuffer();
		for (int iL = 0; iL < headsList.size(); iL++) {
			HashMap HeadMap = (HashMap) headsList.get(iL);
			String colname = (String) HeadMap.get("DBCOLUMNNAME");
			BigDecimal level = (BigDecimal) HeadMap.get("LEVELNO");
			String cellValue = (String) HeadMap.get("NAME");
			String ALIAS = (String) HeadMap.get("ALIAS");
			if (ALIAS != null && !"".equalsIgnoreCase(ALIAS))
				cellValue = ALIAS;
			cellValue = generateSpace((level.intValue() - 1) * 3) + cellValue;
			//River修改20151230 start
			HeadMap.put("COLUMNINDEX", iL);   
			String NULLABLE=(String) HeadMap.get("NULLABLE");
			//River修改20151230 end
			if (firstHideFieldname.equalsIgnoreCase(colname)) {
				ExcelHideColsStrBuf.append(cellValue + splitstr2 + colname).append(splitstr);
				continue;
			}
			String isvisible = (String) HeadMap.get("ISVISIBLE");
			String columnid = (String) HeadMap.get("COLUMNID");			
			if ("1".equalsIgnoreCase(isvisible) || "AGENCYID".equalsIgnoreCase(colname))
			{
				xssfrow = sheet.createRow(colnum);
				cell = xssfrow.createCell(0);
				cell.setCellValue("" + (colnum - xlsxRowStart + 1));
				cell.setCellStyle(bodyStyle);
				cell = xssfrow.createCell(1);
				cell.setCellValue(cellValue);
				//River修改20151230 start
				if("0".equalsIgnoreCase(NULLABLE))
				{
					cell.setCellValue(cellValue+fieldIsNullMark);  
				}
				//River修改20151230 end
				cell.setCellStyle(bodyStyle);
				String coluri = CellReference.convertNumToColString(colnum);

				getFieldsinfoToExcel(ExcelColsList, HeadMap, colname, cellValue);
				xlsxColsMap.put(colname, coluri);
				if (colname != null && !colname.equalsIgnoreCase("")) {
					batsqlBUF.append("select '"+columnid+"' COLUMNID,'"+tableid+"' TABLEID,'"+coluri.toUpperCase()+"' xlsxcolumnid from dual ");
					batsqlBUF.append("union all ");
				}
				colnum++;
				DisplayColsList.add(HeadMap);
			} else {
				if (colname != null && !colname.equalsIgnoreCase("")) {
					batsqlBUF.append("select '"+columnid+"' COLUMNID,'"+tableid+"' TABLEID,null xlsxcolumnid from dual ");
					batsqlBUF.append("union all ");
				}
				hideColsStrBuf.append(colname).append(splitstr);
				ExcelHideColsStrBuf.append(cellValue + splitstr2 + colname).append(splitstr);
				HideColsList.add(HeadMap);
			}
		}
		int lastPos=batsqlBUF.lastIndexOf("union all");
		batsqlBUF.replace(lastPos, lastPos + 9, "");
		sql="MERGE INTO dict_t_factorxlsx a " +
				"USING ("+batsqlBUF.toString()+") b " +
				"ON (a.tableid = b.tableid and a.columnid = b.columnid) " +
				"WHEN MATCHED THEN " +
				"  update set xlsxcolumnid = b.xlsxcolumnid  where tableid = b.tableid    and columnid = b.columnid " +
				"WHEN NOT MATCHED THEN " +
				"  INSERT (COLUMNID, TABLEID, xlsxcolumnid)  values  ( b.columnid, b.tableid,b.xlsxcolumnid) ";
		
		this.excelFileMapper.selectExcelInfo(sql);	
		
		xssfrow = sheet.createRow(colnum);
		cell = xssfrow.createCell(1);
		cell.setCellValue(hideColsStrBuf.toString());
		ExcelColsList.add(ExcelHideColsStrBuf.toString());

		sql = "select * from " + phytableName
				+ " T  where  AGENCYID = (select AGENCYID from "
				+ phytableName + " where rownum<=1) and "+headField+" in ("+headFieldvals+") order by " + headField;

		
		List dataList = this.excelFileMapper.selectExcelInfo(sql);
		int iR = 0;
		//River修改20151230 start
		HashMap cellStyleCacheMap=new HashMap();   
		String[] headFieldvalsArr = headFieldvals.split(",");
		for (iR = 0; iR < headFieldvalsArr.length/*dataList.size()*/; iR++) {

			colnum = xlsxRowStart;
			HashMap rowMap = new HashMap();
			if(dataList.size()>0)
				rowMap=(HashMap) dataList.get(iR);
			xssfrow = sheet.getRow(colnum - 1);
			cell = xssfrow.createCell(iR + 2);
			String hfield = headFieldvalsArr[iR].replaceAll("'", "");//(String) rowMap.get(headField);
			cell.setCellValue(hfield);
			cell.setCellStyle(headStyle);
			for (int ic = 0; ic < DisplayColsList.size(); ic++) {
				xssfrow = sheet.getRow(colnum);
				cell = xssfrow.createCell(iR + 2);
				HashMap headm = (HashMap) DisplayColsList.get(ic);
				String fieldn = (String) headm.get("DBCOLUMNNAME");
				String fieldcnName = (String) headm.get("NAME");
				rowMap.put("DBFIELD_DATATYPE", headm.get("DATATYPE"));
				
				colnum++;
				String isupdate = (String) headm.get("ISUPDATE");
				String ISVIRTUAL = (String) headm.get("ISVIRTUAL");
				String ISBANDCOL = (String) headm.get("ISBANDCOL");
				String ISLEAF = (String) headm.get("ISLEAF");
				if ("1".equalsIgnoreCase(ISVIRTUAL) || "1".equalsIgnoreCase(ISBANDCOL)|| "0".equalsIgnoreCase(isupdate)||"0".equalsIgnoreCase(ISLEAF))
					cell.setCellStyle(bodyStyle);
				else
					cell.setCellStyle(bodyUnLockedStyle);
				setxCellValue1(codeMap, tableid, cacheMap, cell, rowMap, fieldn,	fieldcnName, "",cellStyleCacheMap); 
				
			}
			//River修改20151230 end
			xssfrow = sheet.getRow(colnum);
			cell = xssfrow.createCell(iR + 2);
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
		xssfrow.setZeroHeight(true);
		int dataMaxColnum = iR + 1;
		CellRangeAddress region1 = new CellRangeAddress(0, 0, 0, dataMaxColnum);
		prepareCellmergeRegion(sheet, region1);
		sheet.addMergedRegion(region1);
		for (int ic = 1; ic <= iR + 1; ic++) {
			sheet.setColumnWidth(ic, 30 * 256);
		}
		sheet.protectSheet(worksheetProtectPassword);
		HashMap xlsxRowColMap = new HashMap();
		xlsxRowColMap.put("rows", xlsxRowsMap);
		xlsxRowColMap.put("cols", xlsxColsMap);
		codeMap.put("ext_FieldValues",headFieldvals);
		HashMap resultMap = generateHideConfigSheet(codeMap, dictTModel, wb, ExcelColsList, xlsxRowColMap, cacheMap, 1, request);
		retMap.put("errorInfo", resultMap.get("errorInfo"));
		retMap.put("successInfo", resultMap.get("successInfo"));
		retMap.put("success", resultMap.get("success"));
		if (retMap.get("success") == null || !(Boolean) retMap.get("success")) {
			return retMap;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] databytes = baos.toByteArray();
		HashMap ret2 = handleAndSaveExcel2(databytes, sheetName, tableid, request);
		retMap.put("excelDataTemplate", ret2.get("excelDataTemplate"));

		retMap.put("success", true);
		return retMap;

	}
	@Override
	public boolean getIsRowColConvert() {
		return true;
	}
	public HashMap GetTableLevelInfo(String tableid) {
		String sql;
		HashMap tabFieldlevelInfo = new HashMap();
		return tabFieldlevelInfo;
	}
    public String getCurDealType() {
    	return "62";
    }
}