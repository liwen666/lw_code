package commons.setting.dataaudit.auditrule.web;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleDefService;
import com.tjhq.commons.setting.formula.service.IFormulaService;
import com.tjhq.commons.setting.input.web.ConverTables;

/**
* @ClassName: AuditRuleExcelControllerNew
* @Description: 审核规则检查的导出
* @author xiehongtao
* @date 2017-6-9 下午5:43:00
* 
*    @RequestMapping(value = "/commons/setting/auditrule/excel") //原来的请求路径
*
 */
@Controller
@RequestMapping(value = "/commons/setting/dataaudit/auditrule/excel")
public class AuditRuleDefExcelController {
	
	@Resource
	private IFormulaService formulaService;
	
	@Resource
	private IAuditRuleDefService auditRuleDefService;
	/**
	 * 数据审核定义检查 导出 功能
	 * @param appId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="getCheckDataExcelExp")
	@ResponseBody
	public void getCheckDataExcelExp(HttpServletRequest req,
			HttpServletResponse response) throws Exception{
		    String appId=req.getParameter("appId");
		   String checkSortId=req.getParameter("checkSortId");
		   String checkIds=req.getParameter("checkIds");//选择下载ids
			Map<String, String> map = new HashMap<String, String>();
			map.put("appId", appId);
			if(null!=checkSortId && !"".equals(checkSortId)){
				map.put("checkSortId", checkSortId);	
			}
			if(null!=checkIds && !"".equals(checkIds)){//选择下载
				map.put("checkIds", "'"+checkIds.replaceAll(",","','")+"'");	
			}
			
			List<Map<String,Object>> records = auditRuleDefService.getAuditRuleCheckList(map);

			LinkedHashMap<String, Object[]> dataMap = new LinkedHashMap<String, Object[]>();

			if(ConverTables.isNotNullList(records)){
				for(int i=0;i<records.size();i++){
					Map<String, Object> m = records.get(i);
					Object orderID = m.get("SERID");
					Object checkName = m.get("DEFNAME");
					Object checkType = m.get("CHECKTYPE");
					try {
						// 验证CHECKSQL 是否错误
						String checkSql=(String)m.get("CHECKSQL");
						if(null!=checkSql && checkSql.indexOf("@WHERE@")>0){
							checkSql=checkSql.replaceAll("@WHERE@", "");
						}
						formulaService.callErrorMessage(checkSql); 
					} catch (Exception e) {
						String errorMsg = e.getMessage().replace("\"", "'").replace("\n", "");
						dataMap.put(String.valueOf(i), new Object[]{orderID,checkName,checkType,errorMsg});			
					}	
				}
				
				String headers[] = {"序号","审核名称","审核类型","错误信息"};

				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet sheet = workBook.createSheet("审核定义检查");	
				inputDataInExcel(sheet,headers,dataMap,createStyles(workBook));

				try{
					String strFileName = "审核定义检查.xls";
					strFileName = java.net.URLEncoder.encode(strFileName, "UTF-8");//处理中文文件名的问题
					strFileName = new String(strFileName.getBytes("UTF-8"),"GBK");
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment; filename=" + strFileName);
					OutputStream os = response.getOutputStream(); 		
					workBook.write(os);
					os.flush();
					os.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
			}
	}
	
	public static void inputDataInExcel(HSSFSheet sheet, String[] headers, Map<String, Object[]> data,Map<String,Object> style) {
		 // 产生表格标题行
		Row headRow = sheet.createRow(0);
		headRow.setHeightInPoints(25);//设置行高
        for (int i = 0; i < headers.length; i++) {
        	Cell cell = headRow.createCell(i);
            cell.setCellValue(headers[i]);
			HSSFCellStyle cellStyle = (HSSFCellStyle) style.get("header");
            cell.setCellStyle(cellStyle);
        }
        sheet.setColumnWidth(0, 3500);
        sheet.setColumnWidth(1, 18000);
        sheet.setColumnWidth(2, 7000);
        sheet.setColumnWidth(3, 50000);
		Set<String> keyset = data.keySet();
		int rownum = 1; //预留标题列位置
		
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			row.setHeightInPoints(20);
			Object[] objArray = data.get(key);
			int cellnum = 0;
			for (Object obj : objArray) {
				Cell cell = row.createCell(cellnum++);
				HSSFCellStyle cellStyle = (HSSFCellStyle) style.get("normal");
				cell.setCellStyle(cellStyle); //设置样式
				
				if (obj instanceof Date) {
					cell.setCellValue((Date) obj);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				} else if (obj instanceof Boolean) {
					cell.setCellValue((Boolean) obj);
					cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
				} else if (obj instanceof String) {
					cell.setCellValue((String) obj);
					cell.setCellType(Cell.CELL_TYPE_STRING);
				} else if (obj instanceof Double) {
					cell.setCellValue((Double) obj);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				} else if (obj instanceof Float) {
					cell.setCellValue((Float) obj);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				} else if (obj instanceof Integer) {
					cell.setCellValue((Integer) obj);
					cell.setCellType(Cell.CELL_TYPE_NUMERIC);
				} else {
					cell.setCellValue(obj == null ? StringUtils.EMPTY : obj.toString());
					cell.setCellType(Cell.CELL_TYPE_STRING);
				}
			}
		}

	}
	
	public static Map<String,Object> createStyles(HSSFWorkbook workBook){  
	    Map<String,Object> styles = new HashMap<String,Object>();
	    
	    HSSFCellStyle border = workBook.createCellStyle();// 创建样式对象 
	    border.setBorderLeft(CellStyle.BORDER_THIN);
	    border.setBorderRight(CellStyle.BORDER_THIN);
	    border.setBorderTop(CellStyle.BORDER_THIN);
	    border.setBorderBottom(CellStyle.BORDER_THIN);
	    
	    HSSFDataFormat fmt = workBook.createDataFormat();
	    
	    HSSFCellStyle style1 = workBook.createCellStyle();// 创建样式对象 
	    style1.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);// 水平居中  
	    style1.setDataFormat(fmt.getFormat("0.0%"));  
	    styles.put("percent", style1); 
	  
	    HSSFCellStyle style2 = workBook.createCellStyle();  
	    style1.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);// 水平居中  
        style2.setDataFormat(fmt.getFormat("0.0X"));  
	    styles.put("coeff", style2);  
	  
	    HSSFCellStyle style3 = workBook.createCellStyle();  
	    style1.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);// 水平居中  
	    style3.setDataFormat(fmt.getFormat("$#,##0.00"));  
	    styles.put("currency", style3);  
	  
	    HSSFCellStyle style4 = workBook.createCellStyle();  
	    style4.setAlignment(CellStyle.ALIGN_RIGHT);  
	    style4.setDataFormat(fmt.getFormat("m/d/yy h:mm"));  
	    styles.put("date", style4);  
	  
	    HSSFCellStyle style5 = workBook.createCellStyle(); 	    
	    HSSFFont headerFont = workBook.createFont();  
	    headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    headerFont.setFontHeightInPoints((short)12);
	    style5.setFont(headerFont);
	    
	    style5.setFillPattern(CellStyle.SOLID_FOREGROUND); //填充方式，前色填充
	    style5.setFillForegroundColor(HSSFColor.SKY_BLUE.index); //前景颜色
	    style5.setWrapText(true); //自动换行

	    styles.put("header", style5);
	    
	    HSSFCellStyle style6 = workBook.createCellStyle();  
	    HSSFFont normalFont = workBook.createFont();
	    normalFont.setFontHeightInPoints((short) 12);// 设置字体大小  
	    style6.setFont(normalFont);
	    
	    style6.setWrapText(true); //自动换行
	    
	    styles.put("normal", style6);
	    
	    return styles;  
	}  

}

