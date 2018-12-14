package commons.excel2.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjhq.commons.excel2.dao.ExcelMapper;
import com.tjhq.commons.excel2.service.IExcelCodeService;
import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.exception.core.code.ExceptionCode;
@Service
@Transactional
public class ExcelCodeService implements IExcelCodeService {
	
	public Logger logger = Logger.getLogger(ExcelCodeService.class);
	
	@Resource
	public ExcelMapper excelMapper;
	
	public SXSSFWorkbook exportData(HttpServletRequest request) throws ServiceException {
		try {
			//新建XSSFWorkbook对象
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			//标题的格式
			CellStyle headStyle = this.getStyle(workbook, "宋体", Font.BOLDWEIGHT_BOLD, (short) 12, XSSFCellStyle.ALIGN_CENTER, true);
			//数据的格式
			//CellStyle bodyStyle = this.getStyle(xssfWorkbook, "微软雅黑", (short) 0, (short) 10, XSSFCellStyle.ALIGN_LEFT, false);
			//链接样式
			CellStyle linkStyle = workbook.createCellStyle();  
	        Font font= workbook.createFont();  
	        linkStyle.setFont(font);
	        font.setUnderline((byte) 1);  
	        font.setColor(HSSFColor.BLUE.index);
			
			//所选代码表ID
			String jsonStr = request.getParameter("jsonStr");
			JSONArray jsonArray = JSONArray.fromObject(jsonStr);
			String codeTableIDs = "";
			
			HashMap<String, String> tableIDNameMap1 = new HashMap<String, String>();
			if(jsonArray.size() > 0){
				for(int i = 0; i < jsonArray.size(); i++){
					JSONObject jObject = JSONObject.fromObject(jsonArray.get(i));
					String tableID = jObject.get("TABLEID").toString();
					String name = jObject.get("NAME").toString();
					if(StringUtils.isNotEmpty(tableID)){
						if(!tableIDNameMap1.containsKey(tableID)){
							tableIDNameMap1.put(tableID, name);
							if(i == 0){
								codeTableIDs += tableID;
							}else{
								codeTableIDs += "','" + tableID;
							}
						}
					}
				}
			}else{
				throw new ServiceException(ExceptionCode.ERR_00000, "导出报错：代码表ID参数为空", false);
			}
			//处理中文名称相同的数据，以防止生成sheet名称时报错
			int a = 0;
			HashMap<String, String> tableIDNameMap2 = new HashMap<String, String>();
			for(String key : tableIDNameMap1.keySet()){
				
				String value = tableIDNameMap1.get(key);
				value = StringFilterName(value);
				if(tableIDNameMap2.containsValue(value)){
					a++;
					value += a;
				}
				tableIDNameMap2.put(key, value);
			}
			//所选业务表ID
			String jsonStr2 = request.getParameter("jsonStr2");
			JSONArray jsonArray2 = JSONArray.fromObject(jsonStr2);
			String tableIDs = StringUtils.join(jsonArray2, "','");
			
			
			//查询代码表的业务表，列名，代码表名，过滤条件：1、所选业务表，2、所选代码表，3、dict_t_factor.EXTPROP第二位不为1
			String sql = "select c.name TABLE_NAME, a.name COLUMN_NAME, b.name MODEL_CODE_NAME, b.DBTABLENAME, b.TABLEID, b.ISFASP from dict_t_factor a join dict_t_modelcode b on a.csid = b.tableid left join dict_t_model c on a.tableid = c.tableid where a.tableid in ('"+tableIDs+"') and b.tableid in ('"+codeTableIDs+"') and substr(a.EXTPROP,2,1) <> '1' and a.ISVISIBLE = '1' order by c.name, a.name, b.name";
			List<HashMap<String, Object>> catalogList = this.excelMapper.selectExcelInfo(sql);
			if(catalogList.size() == 0){
				throw new ServiceException(ExceptionCode.ERR_00000, "查询数据为空，不能导出，sql为："+sql, false);
			}
			Sheet sheet = workbook.createSheet("业务表列名与代码表对应关系");
			//数据
			CreationHelper createHelper = workbook.getCreationHelper();  
			
			int startRow = 0;
			//第1行:标题
			Row row0 = sheet.createRow(startRow);
			Cell cell = row0.createCell(0);
			cell = row0.createCell(0);
			cell.setCellValue("业务表名");
			cell.setCellStyle(headStyle);
			cell = row0.createCell(1);
			cell.setCellValue("列名");
			cell.setCellStyle(headStyle);
			cell = row0.createCell(2);
			cell.setCellValue("代码表名");
			cell.setCellStyle(headStyle);
			startRow++;
			//关系页
			String tempName = "";
			for (int xRow = startRow; xRow < catalogList.size() + startRow; xRow++) {
				Row row = sheet.getRow(xRow);
				if (row == null){
					row = sheet.createRow(xRow);
				}
				HashMap<String, Object> map = catalogList.get(xRow - startRow);
				for (int xCol = 0; xCol <= 2; xCol++) {
					Cell xCell = row.getCell(xCol);
					if (xCell == null) {
						xCell = row.createCell(xCol);
					}
					if(xCol == 0){
						String tableName = (String) map.get("TABLE_NAME");
						if(!tableName.equals(tempName)){
							tempName = tableName;
							setxCellValue(xCell, tableName);
						}
						
					}else if(xCol == 1){
						setxCellValue(xCell, (String) map.get("COLUMN_NAME"));
					}else if(xCol == 2){
						String tableID = (String) map.get("TABLEID");
						String name = tableIDNameMap2.get(tableID);
				        
				        setxCellValue(xCell, name);
						XSSFHyperlink hyperlink = (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
						hyperlink.setAddress("#'"+name+"'!A1"); 
						xCell.setHyperlink(hyperlink);
						xCell.setCellStyle(linkStyle);
					}
				}
			}
			sheet.autoSizeColumn((short)0);//调整第一列宽度
			sheet.autoSizeColumn((short)1);
			sheet.autoSizeColumn((short)2);
			
			List<String> dbtablenameList = new ArrayList<String>();
			for(int i = 0; i < catalogList.size(); i++){
				HashMap<String, Object> map1 = catalogList.get(i);
				String dbtablename = (String) map1.get("DBTABLENAME");
				if(dbtablenameList.contains(dbtablename)){
					continue;
				}else{
					dbtablenameList.add(dbtablename);
				}
				
				sql = "select trim(code)||'-'||trim(name) codename, guid, trim(code) code, trim(name) name, isleaf from " + dbtablename ;
				//如果是平台代码表，则加上status = '1'
				if(dbtablename.startsWith("FASP_V")){
					sql += " where status = '1' ";
				}
				//根据code排序
				sql += " order by code ";
				List<HashMap<String, Object>> list = this.excelMapper.selectExcelInfo(sql);
				String tableID = (String) map1.get("TABLEID");
				String name = tableIDNameMap2.get(tableID);
				//创建SHEET
				sheet = workbook.createSheet(name);
				
				startRow = 0;
				//第1行:标题
				row0 = sheet.createRow(startRow);
				cell = row0.createCell(0);
				cell = row0.createCell(0);
				cell.setCellValue("编码");
				cell.setCellStyle(headStyle);
				cell = row0.createCell(1);
				cell.setCellValue("名称");
				cell.setCellStyle(headStyle);
				cell = row0.createCell(2);
				cell.setCellValue("需填内容");
				cell.setCellStyle(headStyle);
				startRow++;
				//数据
				for (int xRow = startRow; xRow < list.size() + startRow; xRow++) {
					Row row = sheet.getRow(xRow);
					if (row == null){
						row = sheet.createRow(xRow);
					}
					HashMap<String, Object> map = list.get(xRow - startRow);
					for (int xCol = 0; xCol <= 2; xCol++) {
						Cell xCell = row.getCell(xCol);
						if (xCell == null) {
							xCell = row.createCell(xCol);
							//xCell.setCellStyle(bodyStyle);
						}
						if(xCol == 0){
							setxCellValue(xCell, (String) map.get("CODE"));
						}else if(xCol == 1){
							setxCellValue(xCell, (String) map.get("NAME"));
						}else if(xCol == 2){
							setxCellValue(xCell, (String) map.get("CODENAME"));
						}
					}
				}
				
				sheet.autoSizeColumn((short)0);//调整第一列宽度
				sheet.autoSizeColumn((short)1);
				sheet.autoSizeColumn((short)2);
				
			}
			
			return workbook;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionCode.ERR_00000, "导出报错："+e.getMessage(), false);
		}
	}

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
	
	public void setxCellValue(Cell cell, Object cellvalue) {
		if (cellvalue == null){
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			return;
		}
		String fieldtype = cellvalue.getClass().getName();
		if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")) {
			BigDecimal cv = new BigDecimal(0);
			if (fieldtype.equalsIgnoreCase("java.math.BigDecimal")){
				cv = (BigDecimal) cellvalue;
			}
			cell.setCellValue(cv.doubleValue());
		} else {
			String cv = (String) cellvalue;
			//2016-08-19字符类型的数据，加入空格，在filldataGrid的setxCellValue方法中通过数据类型给单元格添加文本格式，然后去掉空格
			if(" ".equals(cv)){
				cv = "";
			}
			cell.setCellValue(cv);
		}
	}
	//处理名称
	public static String StringFilterName(String name) {
		//处理名称，去掉特殊字符，sheet的名称不能含有特殊字符
		if(name.contains("]")){
			name = name.substring(name.indexOf("]") + 1);
		}
		//去除特殊字符
		name = StringFilter(name, "");
		//sheet名不能超过31位
		if(name.length()>25){
			name.substring(0, 25);
		}
		return name;
	}
	//去除特殊字符，把特殊字符变为str1
	public static String StringFilter(String str, String str1) {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String result = m.replaceAll(str1).trim();
		p = Pattern.compile("\\s*|\t|\r|\n");
        m = p.matcher(result);
        result = m.replaceAll("");
		return result;
	}
}
