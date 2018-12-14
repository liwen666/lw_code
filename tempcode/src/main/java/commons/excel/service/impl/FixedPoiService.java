package commons.excel.service.impl;

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
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import com.tjhq.commons.inputcomponent.grid.fixgrid.po.FixGrid;
import com.tjhq.commons.inputcomponent.po.PageInfo;


@Service
public class FixedPoiService extends BasePoiService
{
	public FixedPoiService() {
		super();
	}
	public HashMap getdataPageinfoFormJson(HashMap codeMap, DictTModelPO dictTModel, String grid, String tableID, HashMap preMap, HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException, Exception, SQLException {
		byte[] filexlsx1=null;
		PageInfo dataPageInfo1 = null;
		HashMap retMap = new HashMap();
		
		FixGrid localFixGrid = (FixGrid) new ObjectMapper().readValue(grid, FixGrid.class);
		dataPageInfo1 = (PageInfo) this.fixGridInputService.getData(localFixGrid);
		//开始：处理数据，如果agencyid是*，则把agencyid设为所选的agencyid，datakey设为新的datakey
		String agencyID = (String) localFixGrid.getExtProperties().get("agencyID");
		int dataKeyIndex = 0;
		int agencyIdIndex = 0;
        if (dataPageInfo1.getColumns().size() > 0) {
        	dataKeyIndex = dataPageInfo1.getColumns().indexOf("DATAKEY");
        	agencyIdIndex = dataPageInfo1.getColumns().indexOf("AGENCYID");
        } 
        List<Object> tempDataList = null;
        String agencyIdtemp = null;
        for (Object data : dataPageInfo1.getDataList()) {
        	tempDataList = (List<Object>) data;
        	agencyIdtemp = (String) tempDataList.get(agencyIdIndex);
        	if("*".equals(agencyIdtemp)){
        		tempDataList.remove(dataKeyIndex);
                tempDataList.add(dataKeyIndex, getGUID());
                tempDataList.remove(agencyIdIndex);
                tempDataList.add(agencyIdIndex, agencyID);
        	}
        }
        //结束：处理数据，如果agencyid是*，则把agencyid设为所选的agencyid，datakey设为新的datakey
        
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
	/*public HashMap getDataPageInfoAndXlsxFromDB(HashMap rowMap, String agencyID,HttpServletRequest request)throws Exception {
		HashMap retMap = new HashMap();
		PageInfo dataPageInfo = null;

		String tablename = (String) rowMap.get("NAME");
		String tabid1 = (String) rowMap.get("TABLEID");
		String suitName = (String) rowMap.get("SUITNAME");
		String appid1 = (String) rowMap.get("APPID");
		String userID = SecureUtil.getCurrentUser().getGuid();

		HashMap dbMap = (HashMap) SelectModelTableCache(tabid1);
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
		if(filexlsx==null){
			filexlsx = (byte[]) dXlsxNull.get("filelob");
		}
		HashMap agencyMap = (HashMap) this.excelFileMapper.SelectDivName(agencyID);
		
		//文件名
		String outFilename = super.getFileName(agencyMap, tableName);

		XSSFtjhqWorkbook wb;
		wb = new XSSFtjhqWorkbook(true);
		XSSFWorkbook wbdb = new XSSFWorkbook(new ByteArrayInputStream(filexlsx));
		for (XSSFSheet sht1 : wbdb) {
			XSSFSheet tmpSheet = wb.copySheetFromOtherXLSX(sht1, wbdb);
		}
		HashMap dataTplMap = (HashMap) new ObjectMapper().readValue(dataJson, HashMap.class);
		Object sht = dataTplMap.get(tableName);
		if (sht == null) {
			retMap.put("success", false);
			retMap.put("errorInfo", "系统中的的Excel模板有问题,中没有找到sheet：" + tableName + "的工作表！\r\n数据表导出失败！");
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
		exTmap.put("pagination", false);
		FixGrid localFixGrid = new FixGrid();
		localFixGrid.setTableID(tabid1);
		Grid localGrid = (Grid) this.fixGridInputService.initStructure(localFixGrid,UserGuid);
		localGrid.setColumnList(null);
		localGrid.setQueryPanelParamList(conditonList);
		localGrid.setExtProperties(exTmap);
		dataPageInfo = (PageInfo) this.fixGridInputService.getData(localGrid);
		HashMap selMap = selectFixedGridData(dataPageInfo, tabid1,  agencyID, 0);
		HashMap dataMap = (HashMap) selMap.get("data");
		boolean bColRowConvert = false;
		filldataGrid(retMap, dataMap, dataPageInfo, dataTplMap,	wb,request);
		
		WorkbookAllCellsCalc(wb);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] databytes = baos.toByteArray();
		retMap.put("filelob", databytes);
		retMap.put("filename", outFilename);
		return retMap;
	}*/
	public HashMap selectFixedGridData(HashMap codeMap, DictTModelPO dictTModel, PageInfo dataPageInfo, String tableID,  String DefaultAgencyID, int SumHeadCount, HashMap fieldTypeMap) {
		HashMap retMap = new HashMap();
		List dataL = null;
		List columnList = null;

		String sql = " select max(levelno) headrownum from dict_t_factor t where t.tableid ='" + tableID + "' and t.isvisible = '1'";
		
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		BigDecimal tmpBig = (BigDecimal) tmpMap.get("HEADROWNUM");
		int headrownum = tmpBig.intValue();
		startfillrownum = headrownum + 1;
		dataL = dataPageInfo.getDataList();
		columnList = dataPageInfo.getColumns();
		HashMap	colmap = GetXlsxDataFormDBII(tableID);
		HashMap data = new HashMap();
		HashMap cacheMap = new HashMap();
		HashMap tableLevelInfoMap = GetTableLevelInfo(tableID);
		LinkedHashMap composeKeyMap = (LinkedHashMap) fieldTypeMap.get("composeKey-Key");
		String composeKeys = "";
		data.put("thisTableXlsxTotalRownum", dataL.size());
		for (int ir = 0; ir < dataL.size(); ir++) {
			List rowData = (List) dataL.get(ir);
			HashMap rowMap = new HashMap();
			LinkedHashMap curComposeKeyMap = (LinkedHashMap) composeKeyMap.clone();
			LinkedHashMap hidecolumnMap=new LinkedHashMap();
			for (int ic = 0; ic < columnList.size(); ic++) {
				String fieldName = (String) columnList.get(ic);
				rowMap.put(fieldName, rowData.get(ic));
			}
			for (int ic = 0; ic < columnList.size(); ic++) {
				String fieldName = (String) columnList.get(ic);
				HashMap fieldCodeM = (HashMap) cacheMap.get(tableID + "-"+ fieldName);
				if (fieldCodeM == null) {
					fieldCodeM = getElementCodeName(codeMap, tableID, fieldName, true);
					cacheMap.put(tableID + "-" + fieldName, fieldCodeM);
				}
				if (curComposeKeyMap.containsKey(fieldName)) {
					curComposeKeyMap.put(fieldName,	(String) rowData.get(ic));
				}
				String xlsxcol = (String) colmap.get("dbcolname_"+ fieldName);
				if (xlsxcol == null || xlsxcol.equalsIgnoreCase("")) {
					data.put("Hide_" + fieldName + "."+ (ir + startfillrownum), rowData.get(ic));
						hidecolumnMap.put(fieldName, rowData.get(ic));
				}
				else {
					Object cv = null;
					if (fieldTypeMap.containsKey(fieldName)) {
						HashMap colinfoMap=(HashMap) fieldTypeMap.get(fieldName);
						double type = (Double) colinfoMap.get("DATATYPE");

							cv = rowData.get(ic);
					} else
						cv = rowData.get(ic);
					if ("agencyid".equalsIgnoreCase(fieldName) && ir == 0 && SumHeadCount > 0 && cv == null) {
						cv = DefaultAgencyID;
					}
					if (fieldCodeM.size() > 0) {
						cv = fieldCodeM.get(cv);
					}
					String levelField = (String) tableLevelInfoMap.get(fieldName);
					if (levelField != null) {
						Object o = rowMap.get(levelField);
						int levelcc = 0;
						if (o == null) {
							levelcc = 0;
						} else if (o.getClass().getName().endsWith("BigDecimal")) {
							BigDecimal lv = (BigDecimal) o;
							levelcc = (lv == null ? 0 : lv.intValue());
						} else if (o.getClass().getName().endsWith("String")) {
							levelcc = Integer.valueOf((String) o);
						}
						String preSpace = generateSpace((levelcc - 1) * 3);
						if (preSpace.length() > 0)
							cv = preSpace + cv;
					}
					data.put(xlsxcol + (ir + 1 + startfillrownum), cv);
				}
			}
			boolean isAddKey = true;
			String keyValues = "";
			if (!"".equalsIgnoreCase(composeKeys))
				isAddKey = false;
			for (Iterator it = hidecolumnMap.keySet().iterator(); it	.hasNext();) {
				String key = (String) it.next();
				if (isAddKey) {
					composeKeys = composeKeys + key + splitstr;
				}
				keyValues = keyValues + hidecolumnMap.get(key)	+ splitstr;
			}
			data.put("composeKey." + (ir + startfillrownum), keyValues);
		}

		data.put("composeKey-keys", composeKeys);

		retMap.put("data", data);
		return retMap;
	}
	/*public void filldataGrid_old(HashMap retMap, HashMap dataMap, PageInfo dataPageInfo, HashMap dataTplMap, XSSFWorkbook wb,HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException  {
    
		boolean bColRowConvert = false;
		Iterator shtIt = dataTplMap.keySet().iterator();
		while (shtIt.hasNext()) {
			String shtName = (String) shtIt.next();
			List cellList = (List) dataTplMap.get(shtName);
			if(cellList.size()==0)continue;
			Sheet sheet = wb.getSheet(shtName);
			for (int i = 0; i < cellList.size(); i++) {
				
				String cellstr = (String) cellList.get(i);
				String regex = "^([A-Z]+\\d+)/(\\d+)_(\\d+)" + splitstr + "(.*)$";
				Pattern ptn = Pattern.compile(regex);
				Matcher m = ptn.matcher(cellstr);
				if (m.find()) {
					String CellName = m.group(1);
					CellReference cellReference = new CellReference(CellName);
					Row row = sheet.getRow(cellReference.getRow());
					Cell cell = row.getCell(cellReference.getCol());
					String CellName2 = CellName;
					if (bColRowConvert)
						CellName2 = sheetColRowConvert(2, 2, CellName);

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
				} else {
					System.out.println("读取单元格位置出错！");
				}
			}
			int totalRows = (Integer) dataMap.get("thisTableXlsxTotalRownum");
			Row fieldRow=sheet.getRow(startfillrownum-1);
			Cell hideCell=fieldRow.getCell(fieldRow.getLastCellNum()-1);
			String keyFiledL = (String) dataMap.get("composeKey-keys");
			hideCell.setCellValue(keyFiledL);
			for(int ir=startfillrownum;ir<startfillrownum+totalRows;ir++) {
				Row row=sheet.getRow(ir);
				hideCell=row.getCell(row.getLastCellNum()-1);
				String curKey = (String) dataMap.get("composeKey." + (ir));
				if (curKey != null){
					hideCell.setCellValue(curKey);
				}
			}
		}
	}*/
	public void filldataGrid(HashMap codeMap, DictTModelPO dictTModel, HashMap retMap, HashMap dataMap,PageInfo dataPageInfo, HashMap dataTplMap, XSSFWorkbook wb,HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException {
		Iterator shtIt = dataTplMap.keySet().iterator();
		int totalRows = (Integer) dataMap.get("thisTableXlsxTotalRownum");
		int SumHeadCount = 0;

		HashMap cellStyleCacheMap = new HashMap();
		while (shtIt.hasNext()) {

			String shtName = (String) shtIt.next();
			if (shtName.endsWith("$"))
				continue;
			XSSFSheet sheet = wb.getSheet(shtName);
			int xRow;
			int noFilldataColnum = 1; // 序号列

			Row startRow = sheet.getRow(startfillrownum);
			int StartRowColscc = startRow.getLastCellNum();
			ArrayList colStyleList = new ArrayList();
			for (int icol = 0; icol < StartRowColscc; icol++) {
				colStyleList.add(startRow.getCell(icol).getCellStyle().getIndex());
			}
			Row titleRow = sheet.getRow(startfillrownum - 1); // 填充组合关键字字段列表
			int endCellpos = titleRow.getLastCellNum() - noFilldataColnum;
			XSSFCell cellTitlekey = (XSSFCell) titleRow.getCell(endCellpos);
			if (cellTitlekey == null) {
				cellTitlekey = (XSSFCell) titleRow.createCell(endCellpos);
				cellTitlekey.setCellStyle(wb.getCellStyleAt((Short) colStyleList.get(endCellpos)));
			}
			String titlekeys = (String) dataMap.get("composeKey-keys");
			setxCellValue(cellTitlekey, titlekeys, cellStyleCacheMap);

			for (xRow = startfillrownum; xRow < totalRows + startfillrownum; xRow++) {

				Row row = sheet.getRow(xRow);
				if (row == null)
					row = sheet.createRow(xRow);
				int xColscc = StartRowColscc;
				for (int xCol = 0; xCol < xColscc - noFilldataColnum; xCol++) {
					XSSFCell cell = (XSSFCell) row.getCell(xCol);
					if (cell == null) {
						cell = (XSSFCell) row.createCell(xCol);
						cell.setCellStyle(wb.getCellStyleAt((Short) colStyleList.get(xCol)));
					}

					if (SumHeadCount > 0 && xRow == startfillrownum) {
						XSSFCellStyle cellstyle = cell.getCellStyle();
						XSSFCellStyle newStyle = (XSSFCellStyle) cellstyle.clone();
						newStyle.setLocked(true);
						byte[] rgb = new byte[3];
						rgb[0] = (byte) 255; // 红 red
						rgb[1] = (byte) 255; // 绿 green
						rgb[2] = (byte) 255; // 蓝 blue
						XSSFColor color1 = new XSSFColor(rgb);
						newStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
						newStyle.setFillForegroundColor(color1);
						cell.setCellStyle(newStyle);
					}
					if (xCol == 0) {
						int sn = ((SumHeadCount > 0) ? (xRow - startfillrownum) : (xRow - startfillrownum + 1));
						setxCellValue(cell, new BigDecimal(sn), cellStyleCacheMap);
					} else {
						String cellR = cell.getReference();
						String CellValue = (String) dataMap.get(cellR);
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
				}
				XSSFCell cellkey = (XSSFCell) row.getCell(xColscc - noFilldataColnum);
				if (cellkey == null) {
					cellkey = (XSSFCell) row.createCell(xColscc - noFilldataColnum);
					cellkey.setCellStyle(wb.getCellStyleAt((Short) colStyleList.get(xColscc - noFilldataColnum)));
				}
				String curKey = (String) dataMap.get("composeKey." + (xRow));
				setxCellValue(cellkey, curKey, cellStyleCacheMap);
			}
			while (xRow <= sheet.getLastRowNum()) {
				Row r = sheet.getRow(xRow);
				sheet.removeRow(r);
				xRow++;
			}
		}

	}
	
	public HashMap checkKeysCount(HashMap codeMap, DictTModelPO dictTModel, List dataL,String tableid,String checkinfoHead,HashMap agencyLstMap) throws Exception {
		// 判断导入excel操作前后的逻辑主键是否重复。
		HashMap retMap = new HashMap();
		retMap.put("checkInfo", "");
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		HashMap elementCacheMap = new HashMap();
		String sql;
		List tmpList;
		int lastPos;
		StringBuffer sqlBUF=new StringBuffer();
		sqlBUF.append("select ");

		HashMap rowKeyMap=(HashMap)dataL.get(0);
		if (rowKeyMap.size()==0) return retMap;
		for(Iterator itk=rowKeyMap.keySet().iterator();itk.hasNext();){
			String k=(String)itk.next();
			sqlBUF.append(k).append(",");
		}
		sqlBUF.append("count(*) cc from ").append(dbTableName).append(" t where exists (select 1 from (" );
		for(Iterator ita=agencyLstMap.keySet().iterator();ita.hasNext();){
			String k=(String)ita.next();
			sqlBUF.append("select '").append(k).append("' agencyid from dual union all ");
		}
		lastPos=sqlBUF.lastIndexOf("union all");
		sqlBUF.replace(lastPos, lastPos + 9, " ");
		sqlBUF.append(") t2 where t2.agencyid = t.agencyid) group by ");
		for(Iterator itk=rowKeyMap.keySet().iterator();itk.hasNext();){
			String k=(String)itk.next();
			sqlBUF.append(k).append(",");
		}
		lastPos=sqlBUF.lastIndexOf(",");
		sqlBUF.replace(lastPos, lastPos + 1, " ");
		sqlBUF.append(" having count(*)>1 ");
		sql=sqlBUF.toString();
		
		tmpList = this.excelFileMapper.selectExcelInfo(sql);
		StringBuffer retStrBuf=new StringBuffer();
		for(int ir=0;ir<tmpList.size();ir++){
			HashMap map=(HashMap) tmpList.get(ir); 
			if(ir==0)
			     retStrBuf.append(checkinfoHead+", 发现"+tmpList.size()+"行: \r\n");
			for(Iterator itr=map.keySet().iterator();itr.hasNext();){
				String k=(String)itr.next();
				if(!"CC".equalsIgnoreCase(k)){
				   String v=(String)map.get(k);
				   
				   LinkedHashMap elevalues=(LinkedHashMap) elementCacheMap.get(tableid+"-"+k);
				   if(elevalues==null){
					   elevalues=getElementCodeName(codeMap, tableid, k, true, "itemid");
					   elementCacheMap.put(tableid+"-"+k,elevalues);
				   }
				   if(elevalues !=null && elevalues.size() >0){
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
	public HashMap handleAndSaveExcelData(HashMap codeMap, DictTModelPO dictTModel, HashMap configMap, HashMap excelElevalueguidMap, XSSFWorkbook wb, String curTableid, HashMap paramMap,HttpServletRequest request) throws Exception{
		HashMap retMap = new HashMap();
		String agencyID = (String) paramMap.get("agencyID");

		HashMap fieldsInfoMap = GetXlsxFieldTypeFormDB(curTableid);
		HashMap curtabdefMap = (HashMap) configMap.get(curTableid);
		String curShtname = (String) curtabdefMap.get("sheetName");
		int curSheetstartRow = Integer.valueOf((String) curtabdefMap.get("curSheetstartRow"));
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
		if (sheet == null){
			return returnErrorInfo("Excel文件中未找到sheetname为【" + curShtname + "】工作表!");
		}
		if (!sheet.getSheetName().equalsIgnoreCase(firstSheetName) && !sheet.getSheetName().equalsIgnoreCase(hideConfigSheetName)) {
			Row titleRow = sheet.getRow(curSheetstartRow);
			int snICC = 0;
			for (int ic = 0; ic < titleRow.getLastCellNum() - 1; ic++) {
				Cell cell = titleRow.getCell(ic);
				String fieldname = cell.getStringCellValue();
				if ("".equalsIgnoreCase(fieldname)) {
					fieldname = getMergedRegionValue(sheet, curSheetstartRow, ic);
					if (fieldname == null) {
						return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的表头字段名称读取错误!");
					}
				}
				if ("序号".equalsIgnoreCase(fieldname)) {
					snICC = ic;
					continue;
				}
				String[] dbfieldinfo = ((String) tabdefFieldsMap.get(ic)).split(splitstr); 
				fieldname = fieldname.trim();
				if (!fieldname.equalsIgnoreCase(dbfieldinfo[1].trim()) && !fieldname.equalsIgnoreCase(dbfieldinfo[1].trim()+fieldIsNullMark)) {   //River修改20151230
					return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的表头字段名称:｛" + fieldname + "｝未找到对应的数据库信息!");
				}
			}
			String hideColfieldsinfoStr = titleRow.getCell(titleRow.getLastCellNum() - 1).getStringCellValue();
			String[] hideColfieldsinfo = hideColfieldsinfoStr.split(splitstr);
			HashMap tabdefFieldsMap2 = new HashMap();
			for (Iterator itd = tabdefFieldsMap.keySet().iterator(); itd.hasNext();) {
				int isn = (Integer) itd.next();
				String fieldn = (String) tabdefFieldsMap.get(isn);
				tabdefFieldsMap2.put(fieldn.split(splitstr)[0], isn);

			}
			HashMap KeyFieldMap = (HashMap) fieldsInfoMap.get("composeKey-Key");
			HashMap nonNullMap = (HashMap) fieldsInfoMap.get("nonNullMap-Key");
			ArrayList AllsavedataList = new ArrayList();
			ArrayList InsertsavedataList = new ArrayList();
			ArrayList DelsavedataList = new ArrayList();
			ArrayList UpdatesavedataList = new ArrayList();
			// 一般录入表 有合计行的跳过合计行读数
			int skipRow = 1;
			HashMap keysMap = new HashMap();
			ArrayList keysList = new ArrayList();
			HashMap agencyLstMap = new HashMap();
			agencyLstMap.put(agencyID, "1");
			HashMap virtrulFieldsMap = new HashMap();
			HashMap curTableDataKeyMap = getExistsDataKeyByTableidAgencyid(curTableid, agencyID, dictTModel);
			int lastc = 0;
			for (int ir = curSheetstartRow + skipRow; ir <= sheet.getLastRowNum(); ir++) {
				Row row = sheet.getRow(ir);
				HashMap saveRowMap = new HashMap();
				lastc = (lastc > (row.getLastCellNum() - 1) ? lastc : (row.getLastCellNum() - 1));
				for (int ic = 0; ic < lastc; ic++) {
					if (ic == snICC)
						continue;
					Cell cell = row.getCell(ic);
					String[] dbfieldinfo = ((String) tabdefFieldsMap.get(ic)).split(splitstr); // 0
					LinkedHashMap eleMap = (LinkedHashMap) elementsMap.get(dbfieldinfo[0]);
					String elementTablename = (String) excelElevalueguidMap.get(curTableid + splitstr2 + dbfieldinfo[0]);
					HashMap ExceleleMap = (HashMap) excelElevalueguidMap.get("elementTablename" + splitstr2 + elementTablename);
					HashMap colinfoMap = (HashMap) fieldsInfoMap.get(dbfieldinfo[0]);
					double fieldtype = (Double) colinfoMap.get("DATATYPE");
					boolean isblank = false;
					String isVirtual = (String) colinfoMap.get("ISVIRTUAL"); // 虚列跳过存储
					String ISBANDCOL = (String) colinfoMap.get("ISBANDCOL");
					if ("1".equalsIgnoreCase(isVirtual) || "1".equalsIgnoreCase(ISBANDCOL)) {
						if (!virtrulFieldsMap.containsKey(ic))
							virtrulFieldsMap.put(ic, dbfieldinfo[0]);
						continue;
					}
					if (fieldtype == 1 || fieldtype == 2) {
						if (cell.getCellType() == Cell.CELL_TYPE_STRING && "".equalsIgnoreCase(cell.getStringCellValue()) || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
							saveRowMap.put(dbfieldinfo[0], new Double(0));
							isblank = true;
						} else
							saveRowMap.put(dbfieldinfo[0], cell.getNumericCellValue());
					} else {
						String v;
						if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
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
						} else {
							v = cell.getStringCellValue();
						}
						if (eleMap != null) {
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
					if (nonNullMap.containsKey(dbfieldinfo[0]) && isblank) {
						return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的第" + (ir + 1) + "行字段名称（" + dbfieldinfo[0] + "）不能为空!");
					}
				}
				// 关键字处理
				Cell lastcell = row.getCell(row.getLastCellNum() - 1);
				String hideColinfo = "";
				if (lastcell.getCellType() == Cell.CELL_TYPE_STRING)
					hideColinfo = row.getCell(row.getLastCellNum() - 1).getStringCellValue();
				StringBuffer userFillkeys = new StringBuffer();
				HashMap curKeysMap = new HashMap();
				String curRowDataKey = "";
				String[] hidekeys = hideColinfo.split(splitstr);
				boolean bExtistsSuperid = false;
				for (int ik = 0; ik < hideColfieldsinfo.length; ik++) {
					String keyvalue = hidekeys[ik];
					if ("ISTEMPLATE".equalsIgnoreCase(hideColfieldsinfo[ik]))
						keyvalue = "0";
					if (hideColfieldsinfo[ik].toUpperCase().startsWith("SUPER"))
						bExtistsSuperid = true;
					if("null".equals(keyvalue))
						keyvalue=null;
					saveRowMap.put(hideColfieldsinfo[ik], keyvalue);
					if (!tabdefFieldsMap2.containsKey(hideColfieldsinfo[ik])) {
						HashMap colinfoMap = (HashMap) fieldsInfoMap.get(hideColfieldsinfo[ik]);
						if(colinfoMap !=null) {
							String isVirtual = (String) colinfoMap.get("ISVIRTUAL"); // 虚列跳过存储
							String ISBANDCOL = (String) colinfoMap.get("ISBANDCOL");
							if (!"1".equalsIgnoreCase(isVirtual) && !"1".equalsIgnoreCase(ISBANDCOL)) {
								tabdefFieldsMap.put(tabdefFieldsMap.size() + 1, hideColfieldsinfo[ik].toUpperCase() + splitstr + "隐藏列");
								tabdefFieldsMap2.put(hideColfieldsinfo[ik], tabdefFieldsMap.size());
							}
						} else {
							String  aa="bb";
						}
					}
				}
				if (!saveRowMap.containsKey("TEMPLATEID")){
					return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的第" + (ir + 1) + "行关键字段名称（TEMPLATEID）不能为空!");
				}
				// 不能为空处理
				if (!saveRowMap.containsKey("DATAKEY") || "".equalsIgnoreCase((String) saveRowMap.get("DATAKEY"))) {
					String v = getGUID();
					saveRowMap.put("DATAKEY", v);
				}
				if (!saveRowMap.containsKey("STATUS")) {
					String v = "1";
					saveRowMap.put("STATUS", v);

				}
				saveRowMap.put("ISTEMPLATE", "0");//wuxiaopeng 特殊处理 导入的ISTEMPLATE=0
				if (!saveRowMap.containsKey("ISTEMPLATE")) {
					tabdefFieldsMap.put(tabdefFieldsMap.size() + 1, "ISTEMPLATE" + splitstr + "是否模版行");
				}
				saveRowMap.put("AGENCYID", agencyID);

				/*String curRowDatakey = (String) saveRowMap.get("DATAKEY");
				if (curTableDataKeyMap.containsKey(curRowDatakey)) {
					UpdatesavedataList.add(saveRowMap);
				} else {
					InsertsavedataList.add(saveRowMap); 
					//River修改20121230 start
					String curRowTemplateid=(String) saveRowMap.get("TEMPLATEID");
					HashMap curTableAgencyIDMap =getExistsTemplateidByTableidAgencyid(curTableid,agencyID);
					if(curTableAgencyIDMap.containsKey(curRowTemplateid))
					{
						String agencyCodeName=(String) request.getAttribute("CuragencyCodeName");
						return returnErrorInfo("导入数据的行标识DATAKEY值缺失或行标识DATAKEY在库中_" +agencyCodeName + "_下不存在，数据导入方式为insert，但Excel的第" + (ir + 1) + "行TemplateID："+curRowTemplateid+"已在库中【" + curShtname + "】固定行列表此单位的数据行中，插入失败!");
					}
					//River修改20121230 end
				}*/
				// DelsavedataList
				AllsavedataList.add(saveRowMap);
				keysList.add(curKeysMap);
			}
			if (agencyLstMap.size() > 1) {
				return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的存在着" + agencyLstMap.size() + "个单位的数据 或 要导入单位编码与excel文件中的单位编码不相符!");
			}
			for (Iterator itv = virtrulFieldsMap.keySet().iterator(); itv.hasNext();) {
				Object key = itv.next();
				tabdefFieldsMap.remove(key);
			}
			HashMap resultMap;
			if ("1".equalsIgnoreCase(importExcelSaveDBMethod)){
				resultMap = saveData1(tabdefFieldsMap, AllsavedataList, curTableid, paramMap);
			} else {
				List onkeyList = new ArrayList();
				onkeyList.add("DATAKEY");
				onkeyList.add("AGENCYID");
				HashMap retM = checkKeysCount(codeMap, dictTModel, keysList, curTableid, "导入前主键重复", agencyLstMap);
				String checkInfo = (String) retM.get("checkInfo");
				if (checkInfo.length() > 0){
					return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表\r\n 导入前出现主键重复情况，不能导入！\r\n\r\n" + checkInfo);
				}
				resultMap = saveData(tabdefFieldsMap, AllsavedataList, curTableid, paramMap, onkeyList, keysList, curShtname, wb);

				retM = checkKeysCount(codeMap, dictTModel, keysList, curTableid, "导入后主键重复", agencyLstMap);
				checkInfo = (String) retM.get("checkInfo");
				if (checkInfo.length() > 0){
					throw new RuntimeException("Excel文件中sheetname为【" + curShtname + "】工作表\r\n 导入后出现主键重复，导入失败!\r\n\r\n" + checkInfo);
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
		sql1 = "select * from " + phytableName + "_cfg where istemplate = '1' and agencyid = '*' order by orderid";
		isExistsLevelCols1 = "1";
		paramMap1.put("sql", sql1);
		paramMap1.put("isExistsLevelCols", isExistsLevelCols1);
		
	}
	@Override
	public boolean getIsRowColConvert() {
		return false;
	}
	public HashMap GetTableLevelInfo(String tableid) {
		String sql;
		HashMap tabFieldlevelInfo = new HashMap();

		HashMap paraMap = new HashMap();
		sql = "select t.dbcolumnname, t2.typeid typeid  from dict_t_factor t,dict_t_setfix t2 where t.tableid='"
				+ tableid
				+ "' and t2.tableid=t.tableid and t2.lvlnanmecol=t.columnid";
		
		List levelList = this.excelFileMapper.selectExcelInfo(sql);
		for (int i = 0; i < levelList.size(); i++) {
			HashMap levTMap = (HashMap) levelList.get(i);
			String levnum = (String) levTMap.get("TYPEID");
			String fieldName = (String) levTMap.get("DBCOLUMNNAME");
			tabFieldlevelInfo.put(fieldName, "LEVELNO_" + levnum);

		}

		return tabFieldlevelInfo;
	}
    public String getCurDealType() {
    	return "A1";
    }
}