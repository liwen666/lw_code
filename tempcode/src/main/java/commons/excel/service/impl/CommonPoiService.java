package commons.excel.service.impl;

import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

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
import java.util.Map;

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
import com.tjhq.commons.inputcomponent.grid.commonGrid.po.CommonGrid;
import com.tjhq.commons.inputcomponent.po.Condition;
import com.tjhq.commons.inputcomponent.po.PageInfo;
@Service 
public class CommonPoiService extends BasePoiService {
	
	public CommonPoiService() {
		super();
	}

	public HashMap getdataPageinfoFormJson(HashMap codeMap, DictTModelPO dictTModel, String grid, String tableID, HashMap<String, Object> preMap, HttpServletRequest request) throws IOException, JsonParseException, JsonMappingException, Exception, SQLException {
		byte[] filexlsx1 = null;
		PageInfo dataPageInfo1 = null;
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		//是否导出空模板，空模板noData="1"
		String noData = request.getParameter("noData");
		if("1".equals(noData)){
			dataPageInfo1 = new PageInfo();
		}else{
			CommonGrid localCommonGrid = (CommonGrid) new ObjectMapper().readValue(grid, CommonGrid.class);
			//同步刷新当前表所有公式
			Map localMap = localCommonGrid.getExtProperties();
			String str1 = (String) localMap.get("taskID");
			String str2 = (String) localMap.get("agencyID");
			String str3 = SecureUtil.getCurrentUser().getGuid();
			String str4 = (String) localMap.get("appID");
			String str5 = SecureUtil.getCurrentUser().getUpagencyid();
			if("1".equals(SecureUtil.getCurrentUser().getUsertype())){
				str5 = "";
			}
			this.formulaUtilService.synchRefreshFormula(localCommonGrid.getTableID(), str2, str3, str1, str4, str5);
			// 加入条件
			Condition localCondition = new Condition();
			localCondition.setExpression(" AGENCYID = '" + str2 + "'");
			Object localObject = localCommonGrid.getQueryPanelParamList();
			if (localObject == null){
				localObject = new ArrayList();
			}
			((List) localObject).add(localCondition);
			localCommonGrid.setQueryPanelParamList((List) localObject);
			//去掉分组
			localCommonGrid.setGroupColumnsList(null);
			dataPageInfo1 = (PageInfo) this.commonGridService.getData(localCommonGrid);
		}
		
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
		int SumHeadCount = getCommonGridSumFieldcount(tableID);
		preMap.put("SumHeadCount", SumHeadCount);
        return retMap;
	}
	/*public HashMap getDataPageInfoAndXlsxFromDB(HashMap rowMap, String agencyID, HttpServletRequest request)
			throws Exception {
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
		if(filexlsx==null)
             filexlsx = (byte[]) dXlsxNull.get("filelob");
		HashMap agencyMap = (HashMap) this.excelFileMapper.SelectDivName(agencyID);
		
		String outFilename = super.getFileName(agencyMap, tableName);//文件名 wuxiaopeng

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
			retMap.put("errorInfo", "系统中的的Excel模板有问题,中没有找到sheet：" + tableName
					+ "的工作表！\r\n数据表导出失败！");
			return retMap;
		}

		String UserGuid = SecureUtil.getCurrentUser().getGuid();
		Condition cnd = new Condition();
		cnd.setColumnDbName("AGENCYID");
		cnd.setOperator("=");
		cnd.setQueryValue(agencyID);
		ArrayList conditonList = new ArrayList();
		conditonList.add(cnd);
 
		CommonGrid localCommonGrid1 = new CommonGrid();
		localCommonGrid1.setTableID(tabid1);
		CommonGrid localCommonGrid2 = (CommonGrid) this.commonGridService.initStructure(localCommonGrid1, UserGuid);
		localCommonGrid2.setGroupColumnsList(null);
		localCommonGrid2.setColumnList(null);
		localCommonGrid2.setQueryPanelParamList(new ArrayList());
		HashMap extp = (HashMap) localCommonGrid2.getExtProperties();
		if (extp == null)
			extp = new HashMap();
		extp.put("tableID", tabid1);
		extp.put("agencyID", agencyID);
		extp.put("appID", appid1);
		extp.put("pagination", false);
		String taskID1 = (String) extp.get("taskID");
		localCommonGrid2.setExtProperties(extp);

		Condition localCondition = new Condition();
		localCondition.setExpression(" AGENCYID = '" + agencyID + "'");//wuxiaopeng
		Object localObject = localCommonGrid2.getQueryPanelParamList();
		if (localObject == null)
			localObject = new ArrayList();
		((List) localObject).add(localCondition);
		localCommonGrid2.setQueryPanelParamList((List) localObject);
		localCommonGrid2.setGroupColumnsList(null);//wuxiaopeng修改，强制设置分组为空。
		dataPageInfo = (PageInfo) this.commonGridService.getData(localCommonGrid2);
		int SumHeadCount = getCommonGridSumFieldcount(tabid1);
		retMap.put("SumHeadCount", SumHeadCount);
		HashMap selMap = selectFixedGridData(dataPageInfo, tabid1,  agencyID, 0SumHeadCount);
		HashMap dataMap = (HashMap) selMap.get("data");
		filldataGrid(retMap, dataMap, dataPageInfo, dataTplMap, wb,request);

		WorkbookAllCellsCalc(wb);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		wb.write(baos);
		byte[] databytes = baos.toByteArray();
		retMap.put("filelob", databytes);
		retMap.put("filename", outFilename);
		return retMap;
	}*/
	public HashMap selectFixedGridData(HashMap codeMap, DictTModelPO dictTModel, PageInfo dataPageInfo, String tableID, String defaultAgencyID, int sumHeadCount, HashMap fieldTypeMap) {
		HashMap retMap = new HashMap();
		String sql = " select max(levelno) headrownum from dict_t_factor t where t.tableid ='" + tableID + "' and t.ISVISIBLE = '1'";
		List tmpList = this.excelFileMapper.selectExcelInfo(sql);
		HashMap tmpMap = (HashMap) tmpList.get(0);
		BigDecimal tmpBig = (BigDecimal) tmpMap.get("HEADROWNUM");
		int headrownum = tmpBig.intValue();
		startfillrownum = headrownum + 1;
		List dataL = dataPageInfo.getDataList();
		List columnList = dataPageInfo.getColumns();
		if(columnList.size() == 0){
			sql = "SELECT * FROM dict_t_factor u WHERE TABLEID = '" + tableID
				+ "' START WITH (SUPERID = '0' OR SUPERID IS NULL OR SUPERID = '') "
				+ "CONNECT BY PRIOR COLUMNID = SUPERID  "
				+ " ORDER SIBLINGS BY ORDERID";
			
			List headsList = this.excelFileMapper.selectExcelInfo(sql);
			for (Object object : headsList) {
				HashMap HeadMap = (HashMap) object;
				String dbcolumnname = HeadMap.get("DBCOLUMNNAME")==null ? "" : (String) HeadMap.get("DBCOLUMNNAME");
				columnList.add(dbcolumnname);
			}
		}
		
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
					data.put("Hide_" + fieldName + "."+ (ir + 1 + startfillrownum), rowData.get(ic));
				} else {
					Object cv = rowData.get(ic);
					if (fieldTypeMap.containsKey(fieldName)) {
						HashMap colinfoMap = (HashMap) fieldTypeMap.get(fieldName);
						double type = (Double) colinfoMap.get("DATATYPE");
						if (type == 1 || type == 2) {
							String valueVV = (String) cv;
							if (valueVV != null && super.isRealNumber(valueVV)){
								cv = BigDecimal.valueOf(Double.valueOf(valueVV));
							}
						} else if(type == 6){
							cv = fieldCodeM.get(cv);
						}
					}
					
					if ("agencyid".equalsIgnoreCase(fieldName) && ir == 0 && sumHeadCount > 0 && cv == null) {
						cv = defaultAgencyID;
					}
					
					String levelField = (String) tableLevelInfoMap.get(fieldName);
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
				keyValues = keyValues + curComposeKeyMap.get(key)	+ splitstr;
			}
			data.put("composeKey." + (ir + startfillrownum), keyValues);
		}
		
		//2016-08-19，当没有数据的时候，若有合计行，加入一行合计行和一行空行；若无合计行，只加入一行空行。
		//判断依据是，表有合计列时，queryData返回的数据中必定有一条合计行，数量为1。
		if ((dataL.size() == 1 && sumHeadCount > 0) || dataL.size() == 0) {
			int startNum = 1;
			if(sumHeadCount > 0){
				for (int ic = 0; ic < columnList.size(); ic++) {
					String fieldName = (String) columnList.get(ic);

					String xlsxcol = (String) colmap.get("dbcolname_"+ fieldName);
					if (xlsxcol == null || xlsxcol.equalsIgnoreCase("")) {
						
					} else {
						Object cv = null;
						if (fieldTypeMap.containsKey(fieldName)) {
							HashMap colinfoMap = (HashMap) fieldTypeMap.get(fieldName);
							double type = (Double) colinfoMap.get("DATATYPE");
							//dateType 1整形、 2小数、3字符、4标题、6引用、7大文本、8日期
							if (type == 1 || type == 2) {
								BigDecimal dv = new BigDecimal(0);
								cv = dv;
							}else if(type == 3){
								cv = " ";//2016-08-19字符类型的数据，加入空格，在filldataGrid的setxCellValue方法中通过数据类型给单元格添加文本格式，然后去掉空格
							}else{
								cv = "";
							}
						} else {
							cv = "";
						}
						data.put(xlsxcol + (startNum + startfillrownum), cv);
					}
				}
				startNum += 1;
			}
			for (int ic = 0; ic < columnList.size(); ic++) {
				String fieldName = (String) columnList.get(ic);

				String xlsxcol = (String) colmap.get("dbcolname_"+ fieldName);
				if (xlsxcol == null || xlsxcol.equalsIgnoreCase("")) {
					
				} else {
					Object cv = null;
					if (fieldTypeMap.containsKey(fieldName)) {
						HashMap colinfoMap = (HashMap) fieldTypeMap.get(fieldName);
						double type = (Double) colinfoMap.get("DATATYPE");
						//dateType 1整形、 2小数、3字符、4标题、6引用、7大文本、8日期
						if (type == 1 || type == 2) {
							BigDecimal dv = new BigDecimal(0);
							cv = dv;
						}else if(type == 3){
							cv = " ";//2016-08-19字符类型的数据，加入空格，在filldataGrid的setxCellValue方法中通过数据类型给单元格添加文本格式，然后去掉空格
						}else{
							cv = "";
						}
					} else {
						cv = "";
					}
					data.put(xlsxcol + (startNum + startfillrownum), cv);
				}

			}
			data.put("thisTableXlsxTotalRownum", startNum);
		}
		/*if ((dataL.size() == 1 && sumHeadCount > 0) || (dataL.size() == 0 && sumHeadCount == 0 )) {
			int startBlankRow=(sumHeadCount > 0 ? 2 : 1);
			for (int ic = 0; ic < columnList.size(); ic++) {
				String fieldName = (String) columnList.get(ic);

				String xlsxcol = (String) colmap.get("dbcolname_"+ fieldName);
				if (xlsxcol == null || xlsxcol.equalsIgnoreCase("")) {
					
				} else {
					Object cv = null;
					if (fieldTypeMap.containsKey(fieldName)) {
						HashMap colinfoMap = (HashMap) fieldTypeMap.get(fieldName);
						double type = (Double) colinfoMap.get("DATATYPE");
						if ((type == 1 || type == 2)) {
							BigDecimal dv = new BigDecimal(0);
							cv = dv;
						} else {
							cv = "";
						}
					} else {
						cv = "";
					}
					data.put(xlsxcol + (startBlankRow + startfillrownum), cv);
				}

			}
			data.put("thisTableXlsxTotalRownum", startBlankRow);
		}*/
		data.put("composeKey-keys", composeKeys);

		retMap.put("data", data);
		return retMap;
	}

	public void filldataGrid(HashMap codeMap, DictTModelPO dictTModel, HashMap retMap, HashMap dataMap, PageInfo dataPageInfo, HashMap dataTplMap, XSSFWorkbook wb, HttpServletRequest request) throws IOException, JsonParseException,
			JsonMappingException {
		Iterator shtIt = dataTplMap.keySet().iterator();
		int totalRows = (Integer) dataMap.get("thisTableXlsxTotalRownum");
		int SumHeadCount = 0;
 		SumHeadCount = (Integer) retMap.get("SumHeadCount");
 
		HashMap cellStyleCacheMap=new HashMap();
		while (shtIt.hasNext()) {

			String shtName = (String) shtIt.next();
			if (shtName.endsWith("$")){
				continue;
			}
			XSSFSheet sheet = wb.getSheet(shtName);
			int xRow;
			int noFilldataColnum = 1; // 序号列
			Row startRow = sheet.getRow(startfillrownum);
			int StartRowColscc=startRow.getLastCellNum();
			ArrayList colStyleList = new ArrayList();
			for(int icol=0;icol<StartRowColscc;icol++) {
				colStyleList.add(startRow.getCell(icol).getCellStyle().getIndex());
			}
			Row titleRow = sheet.getRow(startfillrownum - 1); // 填充组合关键字字段列表
			int endCellpos = titleRow.getLastCellNum() - noFilldataColnum;
			XSSFCell cellTitlekey = (XSSFCell) titleRow.getCell(endCellpos);
			if (cellTitlekey == null) {
				cellTitlekey = (XSSFCell) titleRow.createCell(endCellpos);
				cellTitlekey.setCellStyle(wb.getCellStyleAt((Short)colStyleList.get(endCellpos)));
			}
			String titlekeys = (String) dataMap.get("composeKey-keys");
			setxCellValue(cellTitlekey, titlekeys,cellStyleCacheMap);

			for (xRow = startfillrownum; xRow < totalRows + startfillrownum; xRow++) {
				Row row = sheet.getRow(xRow);
				if (row == null)
					row = sheet.createRow(xRow);
				int xColscc = StartRowColscc;
				for (int xCol = 0; xCol < xColscc - noFilldataColnum; xCol++) {
					XSSFCell cell = (XSSFCell) row.getCell(xCol);
					if (cell == null) {
						cell = (XSSFCell) row.createCell(xCol);
						cell.setCellStyle(wb.getCellStyleAt((Short)colStyleList.get(xCol)));
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
						setxCellValue(cell, new BigDecimal(sn),cellStyleCacheMap);
					} else {
						String cellR = cell.getReference();
						Object cv = dataMap.get(cellR);
						setxCellValue(cell, cv,cellStyleCacheMap);
					}
				}
				XSSFCell cellkey = (XSSFCell) row.getCell(xColscc - noFilldataColnum);
				if (cellkey == null) {
					cellkey = (XSSFCell) row.createCell(xColscc	- noFilldataColnum);
					cellkey.setCellStyle(wb.getCellStyleAt((Short)colStyleList.get(xColscc - noFilldataColnum)));
				}
				String curKey = (String) dataMap.get("composeKey." + (xRow ));
				if(StringUtils.isEmpty(curKey)){
					curKey = getGUID() + splitstr;
				}
				setxCellValue(cellkey, curKey, cellStyleCacheMap);
			}
			//0 行数据处理
			/*if(totalRows - (SumHeadCount > 0 ? 1 : 0) == 0) {
				Row row = sheet.getRow(xRow);
				
				if (row == null)
					row = sheet.createRow(xRow);
				int xColscc = StartRowColscc;
				for (int xCol = 0; xCol < xColscc - noFilldataColnum; xCol++) {
					XSSFCell cell = (XSSFCell) row.getCell(xCol);
					if (cell == null) {
						cell = (XSSFCell) row.createCell(xCol);
						cell.setCellStyle(wb.getCellStyleAt((Short)colStyleList.get(xCol)));
					}

			    	if (xCol == 0) {
						int sn = 1;
						setxCellValue(cell, new BigDecimal(sn),cellStyleCacheMap);
					} else if(xCol == xColscc - noFilldataColnum){
						
					} else {
						Object cv = "";
						if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
							cv = new BigDecimal(0);
						}
						setxCellValue(cell, cv, cellStyleCacheMap);
					}
				}
				XSSFCell cellkey = (XSSFCell) row.getCell(xColscc - noFilldataColnum);
				if (cellkey == null) {
					cellkey = (XSSFCell) row.createCell(xColscc	- noFilldataColnum);
					cellkey.setCellStyle(wb.getCellStyleAt((Short)colStyleList.get(xColscc - noFilldataColnum)));
				}
				String curKey = getGUID() + splitstr;
				setxCellValue(cellkey, curKey, cellStyleCacheMap);
				xRow++;
			}*/
			
			while (xRow <= sheet.getLastRowNum()) {
				Row r = sheet.getRow(xRow);
				sheet.removeRow(r);
				xRow++;
			}
		}

	}


	// 判断导入excel操作前后的逻辑主键是否重复。
	public HashMap checkKeysCount(HashMap codeMap, DictTModelPO dictTModel, List dataL, String tableid, String checkinfoHead, HashMap agencyLstMap) throws Exception {
		HashMap retMap = new HashMap();
		retMap.put("checkInfo", "");
		
		String tableName = dictTModel.getName();
		String dbTableName = dictTModel.getDbtablename();
		HashMap elementCacheMap=new HashMap();
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
						   elevalues= getElementCodeName(codeMap, tableid, k, true, "itemid");
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
	public HashMap handleAndSaveExcelData(HashMap codeMap, DictTModelPO dictTModel, HashMap configMap, HashMap excelElevalueguidMap, XSSFWorkbook wb, String curTableid, HashMap paramMap, HttpServletRequest request) throws Exception{
		HashMap retMap = new HashMap();
		String agencyID = (String) paramMap.get("agencyID");

		HashMap fieldsInfoMap = GetXlsxFieldTypeFormDB(curTableid);
		HashMap curtabdefMap = (HashMap) configMap.get(curTableid);
		String curShtname = (String) curtabdefMap.get("sheetName");
		int curSheetstartRow = Integer.valueOf((String) curtabdefMap.get("curSheetstartRow"));
		String curShtsumFieldCC = (String) curtabdefMap.get("curShtsumFieldCC");
		String curTableType = (String) curtabdefMap.get("curTableType");
		LinkedHashMap tabdefFieldsMap = (LinkedHashMap) curtabdefMap.get("tabdefFieldsMap");
		HashMap elementsMap = new HashMap();
		for (Iterator it2 = tabdefFieldsMap.keySet().iterator(); it2.hasNext();) {
			int fkey = (Integer) it2.next();
			String fieldinfo = ((String) tabdefFieldsMap.get(fkey)).split(splitstr)[0];
			LinkedHashMap eleMap = getElementCodeName(codeMap, curTableid, fieldinfo, false,"code-name");
			if (eleMap != null && eleMap.size() > 0) {
				elementsMap.put(fieldinfo, eleMap);
			}
		}
		Sheet sheet = wb.getSheet(curShtname);
		if (sheet==null) return returnErrorInfo("Excel文件中未找到sheetname为【" + curShtname + "】工作表!");
		if (!sheet.getSheetName().equalsIgnoreCase(firstSheetName)
				&& !sheet.getSheetName().equalsIgnoreCase(hideConfigSheetName)) {
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
					return returnErrorInfo("Excel文件中sheetname为【"
							+ curShtname + "】工作表的表头字段名称:｛" + fieldname
							+ "｝未找到对应的数据库信息!");
				}
			}
			String hideColfieldsinfoStr = titleRow.getCell(titleRow.getLastCellNum() - 1).getStringCellValue();
			String[] hideColfieldsinfo = hideColfieldsinfoStr.split(splitstr);
			int datakeyPos = 0;//20160316修改
			for (int ik = 0; ik < hideColfieldsinfo.length; ik++) {
				if("DATAKEY".equalsIgnoreCase(hideColfieldsinfo[ik])) {
					datakeyPos=ik;
					break;
				}
			}
			HashMap KeyFieldMap = (HashMap) fieldsInfoMap.get("composeKey-Key");
			HashMap nonNullMap = (HashMap) fieldsInfoMap.get("nonNullMap-Key");
			ArrayList AllsavedataList = new ArrayList();
			ArrayList InsertsavedataList = new ArrayList();
			ArrayList DelsavedataList = new ArrayList();
			ArrayList UpdatesavedataList = new ArrayList();
			// 一般录入表 有合计行的跳过合计行读数
			int skipRow = (Integer.valueOf((String) curtabdefMap.get("curShtsumFieldCC")) > 0 ) ? 2: 1;
			HashMap keysMap = new HashMap();
			ArrayList keysList=new ArrayList();
			HashMap agencyLstMap = new HashMap();
			agencyLstMap.put(agencyID, "1");
			HashMap virtrulFieldsMap=new HashMap();
			int lastc=0;
			for (int ir = curSheetstartRow + skipRow; ir <= sheet.getLastRowNum(); ir++) {
				Row row = sheet.getRow(ir);
				HashMap saveRowMap = new HashMap();
				lastc=(lastc>(row.getLastCellNum()-1)?lastc:(row.getLastCellNum()-1));
				for (int ic = 0; ic <lastc ; ic++) {
					if (ic == snICC){
						continue;
					}
					Cell cell = row.getCell(ic);
					String[] dbfieldinfo = ((String) tabdefFieldsMap.get(ic)).split(splitstr); // 0
					LinkedHashMap eleMap = (LinkedHashMap) elementsMap.get(dbfieldinfo[0]);
					String elementTablename=(String) excelElevalueguidMap.get(curTableid+splitstr2+dbfieldinfo[0]);
					HashMap excelEleMap=(HashMap)excelElevalueguidMap.get("elementTablename"+splitstr2+elementTablename);
					HashMap colinfoMap=(HashMap) fieldsInfoMap.get(dbfieldinfo[0]);
					double fieldtype = (Double) colinfoMap.get("DATATYPE");
					boolean isblank = false;
                    String isVirtual=(String) colinfoMap.get("ISVIRTUAL");  //虚列跳过存储
					String ISBANDCOL = (String) colinfoMap.get("ISBANDCOL");
                    if("1".equalsIgnoreCase(isVirtual)||"1".equalsIgnoreCase(ISBANDCOL)) {
                    	if(!virtrulFieldsMap.containsKey(ic)){
                    	      virtrulFieldsMap.put(ic, dbfieldinfo[0]);
                    	}
                    	continue;
                    }
                    String v = "";
                    //数字列
					if (fieldtype == 1 || fieldtype == 2) {
						if (cell.getCellType() == Cell.CELL_TYPE_STRING && "".equalsIgnoreCase(cell.getStringCellValue()) || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
							saveRowMap.put(dbfieldinfo[0],	new Double(0));
							isblank = true;
						} else {
							saveRowMap.put(dbfieldinfo[0], cell.getNumericCellValue());
						}
					//引用列
					} else if(fieldtype == 6) {
						v = cell.getStringCellValue();
						//如果数据库中代码表中对应的数据含有code-name则导入数据库中的guid，若数据库没有对应的code-name，则使用excel中的guid
						v = v.trim();
						String guid = "";
						if(excelEleMap != null && excelEleMap.containsKey(v)){
							guid = (String) excelEleMap.get(v);
						}
						if(eleMap != null && eleMap.containsKey(v)){
							guid = (String) eleMap.get(v);
						}
						saveRowMap.put(dbfieldinfo[0], guid);
						
						if (nonNullMap.containsKey(dbfieldinfo[0]) && StringUtils.isEmpty(guid)) {
						 	return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的第" + (ir + 1) + "行的列（" + dbfieldinfo[0] + "）不能为空，此列是引用列请确定数据库或EXCEL中是否含有对应的GUID，code-name：" + v); 
						}
					//字符和其他列
					} else {
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
								v = dff.format(date2);
							}else{
								java.text.DecimalFormat df = new java.text.DecimalFormat("#");  
								Double dv=cell.getNumericCellValue();
								v = df.format(dv);
							}
						} else {
							v = cell.getStringCellValue();
						}
						
						if ("".equalsIgnoreCase(v) || v == null){
							isblank = true;
						}
						saveRowMap.put(dbfieldinfo[0], v);
					}
					if (nonNullMap.containsKey(dbfieldinfo[0]) && isblank) {
					 	return returnErrorInfo("Excel文件中sheetname为【" + curShtname + "】工作表的第" + (ir + 1) + "行的列（" + dbfieldinfo[0] + "）不能为空"); 
					}
				}
				// 关键字处理
				Cell lastcell = row.getCell(row.getLastCellNum() - 1);
				String hideColinfo = "";
				if (lastcell.getCellType() == Cell.CELL_TYPE_STRING){
					hideColinfo = row.getCell(row.getLastCellNum() - 1).getStringCellValue();
				}
				StringBuffer userFillkeys = new StringBuffer();
				HashMap curKeysMap=new HashMap();
				String curRowDataKey="";
				for(Iterator itk=KeyFieldMap.keySet().iterator();itk.hasNext();) {
				String dbkey=(String) itk.next();
					String key1 = "";
					if ("AGENCYID".equalsIgnoreCase(dbkey)){
						key1 = agencyID;
					} else if ("DATAKEY".equalsIgnoreCase(dbkey)) {
						//判断hideColinfo的值
						String[] keys = hideColinfo.split(splitstr);
						if (datakeyPos < keys.length && datakeyPos>=0) {
							key1 = keys[datakeyPos];
							if (key1 != null && !"".equalsIgnoreCase(key1))
								saveRowMap.put("DATAKEY", key1);
						}
						continue;
					} else {
						key1 = (String) saveRowMap.get(dbkey);
					}
					if ("".equalsIgnoreCase(key1))
						return returnErrorInfo("Excel文件中sheetname为【"
								+ curShtname + "】工作表的第" + (ir + 1)
								+ "行关键字段名称（" + dbkey
								+ "）不能为空!");
					userFillkeys.append(key1).append(splitstr);
					curKeysMap.put(dbkey, key1);
			  	}
				// 不能为空处理
				if (!saveRowMap.containsKey("DATAKEY")||"".equalsIgnoreCase((String) saveRowMap.get("DATAKEY"))) {

				}
				if (!saveRowMap.containsKey("STATUS")) {
					String v = "1";
					saveRowMap.put("STATUS", v);

				}
				if (!saveRowMap.containsKey("AGENCYID")) {
					String v = agencyID;
					saveRowMap.put("AGENCYID", v);

				} else {
					agencyLstMap.put((String) saveRowMap.get("AGENCYID"), "1");
				}
				String composeKey = userFillkeys.toString();
				if (!"".equalsIgnoreCase(composeKey) && keysMap.containsKey(composeKey)) {
	 				return returnErrorInfo("Excel文件中sheetname为【"
							+ curShtname + "】工作表的第" + (ir + 1)
							+ "行关键字段\r\n｛" + hideColfieldsinfoStr
							+ "｝\r\n的组合关键字段内容\r\n｛" + composeKey
							+ "｝不能为重复!"); 
				} else {
					keysMap.put(composeKey, "1");
				}
				//比较中含有DATAKEY,逻辑主键和DATAKEY都能确定唯一行，所以此处更新有存在 插入了 逻辑主键相同而DATAKEY不同的行。
				if (hideColinfo.equalsIgnoreCase(composeKey)) {
					UpdatesavedataList.add(saveRowMap);
				} else  {
					InsertsavedataList.add(saveRowMap);
				}
				AllsavedataList.add(saveRowMap);
				keysList.add(curKeysMap);
			}
			if (agencyLstMap.size() > 1) {
				return returnErrorInfo("Excel文件中sheetname为【"
						+ curShtname + "】工作表的存在着" + agencyLstMap.size() + "个单位的数据 或 要导入单位编码与excel文件中的单位编码不相符!");
			}
			for(Iterator itv=virtrulFieldsMap.keySet().iterator();itv.hasNext();) {
				Object key = itv.next();
				tabdefFieldsMap.remove(key);
			}
			HashMap resultMap;
			if("1".equalsIgnoreCase(importExcelSaveDBMethod)){
				resultMap= saveData1(tabdefFieldsMap, AllsavedataList, curTableid, paramMap);
			} else {
				List onkeyList=new ArrayList();
				onkeyList.add("DATAKEY");
				onkeyList.add("AGENCYID");
				HashMap retM= checkKeysCount(codeMap, dictTModel, keysList,curTableid,"导入前主键重复",agencyLstMap);
				String checkInfo=(String) retM.get("checkInfo");
				if(checkInfo.length()>0){
					return returnErrorInfo("Excel文件中sheetname为【"	+ curShtname + "】工作表\r\n 导入前出现主键重复情况，不能导入！\r\n\r\n"+checkInfo);
				}
				resultMap = saveData(tabdefFieldsMap, AllsavedataList, curTableid, paramMap, onkeyList, keysList, curShtname, wb);
				 
				retM= checkKeysCount(codeMap, dictTModel, keysList,curTableid,"导入后主键重复",agencyLstMap);
				checkInfo=(String) retM.get("checkInfo");
				if(checkInfo.length()>0){
					throw new RuntimeException("Excel文件中sheetname为【"	+ curShtname + "】工作表\r\n 导入后出现主键重复，导入失败!\r\n\r\n"+checkInfo);
				}
			}
		}
		retMap.put("success", true);
		retMap.put("successInfo", "上传Excel数据文件成功!");
		return retMap;
	}
	
	public void dealTypeTableSql(String phytableName, HashMap paramMap1) {
		String sql1="";
		String isExistsLevelCols1="0";
		sql1 = "select * from " + phytableName + " where agencyid = '**' ";
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
		return tabFieldlevelInfo;
	}
	
    public String getCurDealType() {
    	return "A0";
    }

}