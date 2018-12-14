package commons.excel2.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjhq.commons.Constants;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.excel2.service.IExcelBaseNumService;
import com.tjhq.commons.excel2.service.IExcelCodeService;
import com.tjhq.commons.excel2.service.IExcelCommonService;
import com.tjhq.commons.excel2.service.IExcelFixService;
import com.tjhq.commons.excel2.service.IExcelFloatService;
import com.tjhq.commons.excel2.service.IExcelPersonService;
import com.tjhq.commons.excel2.service.IExcelService;
import com.tjhq.commons.excel2.service.IExcelTransferPayService;

@Controller
@RequestMapping( { "/commons/excel" })
public class ExcelController {

	public Logger logger = Logger.getLogger(ExcelController.class);
	@Resource
	public IDictTModelService dictTModelService;
	@Resource
	public IExcelService excelService;
	@Resource
	public IExcelCommonService excelCommonService;
	@Resource
	public IExcelFixService excelFixService;
	@Resource
	public IExcelFloatService excelFloatService;
	@Resource
	public IExcelBaseNumService excelBaseNumService;
	@Resource
	public IExcelTransferPayService excelTransferPayService;
	@Resource
	public IExcelPersonService excelPersonService;
	@Resource
	public IExcelCodeService excelCodeService;
	
	/**
	 * 导出数据xlsx
	 * @param request
	 * @param response
	 * @param grid
	 * @param appID
	 * @param tableID
	 * @param agencyID
	 * @param taskID
	 * @param noData
	 */
	@RequestMapping({"export"})
	@ResponseBody
	public void exportData(HttpServletRequest request, HttpServletResponse response, String grid, String appID, String tableID, String agencyID, String taskID, String noData, String agencyIsLeaf) {
		response.reset();
		ServletOutputStream servletOutputStream = null; 
		response.setContentType("application/msexcel;charset=UTF-8");	
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		try {
			servletOutputStream = response.getOutputStream();
			if (StringUtils.isEmpty(tableID)){
				throw new Exception("导出数据的输入参数不够，缺少tableID!");
			}
			//表信息
			DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
			if (StringUtils.isEmpty(tableID)){
				throw new Exception("根据tableID:"+tableID+"未查询到表");
			}
			
			UserDTO user = SecureUtil.getCurrentUser();
			if (user == null) {
				throw new Exception("用戶超時，请重新登陆");
			}
			/*progressRequest(request, "准备导出...", 0);
			ExcelUtil.setProgressBar(request);*/
			
			if(StringUtils.isNotEmpty(noData) && "1".equals(noData)){
				logger.debug("▇开始导出xlsx：不带数据导出"+time);
			}else{
				logger.debug("▇开始导出xlsx：带数据导出"+time);
			}
			//新建Workbook对象
			SXSSFWorkbook sxssfWorkbook = null;
			String dealType = dictTModel.getDealtype();
			if (Constants.TABLE_DEALTYPE_A0.equals(dealType)) {//一般录入表
				excelCommonService.saveDictTFactorxlsx(tableID, grid, agencyIsLeaf);
				sxssfWorkbook = this.excelCommonService.exportData(grid, appID, dictTModel, agencyID, taskID, noData, agencyIsLeaf);
				
			}else if(Constants.TABLE_DEALTYPE_A0_01.equals(dealType)){//人员表
				excelPersonService.saveDictTFactorxlsx(tableID, grid, agencyIsLeaf);
				sxssfWorkbook = this.excelPersonService.exportData(grid, appID, dictTModel, agencyID, taskID, noData, agencyIsLeaf);
			
			}else if(Constants.TABLE_DEALTYPE_A0_05.equals(dealType)){//转移支付表
				excelTransferPayService.saveDictTFactorxlsx(tableID, grid, agencyIsLeaf);
				sxssfWorkbook = this.excelTransferPayService.exportData(grid, appID, dictTModel, agencyID, taskID, noData, agencyIsLeaf);
				
			}else if(Constants.TABLE_DEALTYPE_A1.equals(dealType)){//固定行列表
				excelFixService.saveDictTFactorxlsx(tableID, grid, agencyIsLeaf);
				sxssfWorkbook = this.excelFixService.exportData(grid, appID, dictTModel, agencyID, taskID, noData, agencyIsLeaf);
				
			}else if(Constants.TABLE_DEALTYPE_62.equals(dealType)){//基本数字表
				excelBaseNumService.saveDictTFactorxlsx(tableID, grid, agencyIsLeaf);
				sxssfWorkbook = this.excelBaseNumService.exportData(grid, appID, dictTModel, agencyID, taskID, noData, agencyIsLeaf);
			
			}else{//其他表
				excelCommonService.saveDictTFactorxlsx(tableID, grid, agencyIsLeaf);
				sxssfWorkbook = this.excelCommonService.exportData(grid, appID, dictTModel, agencyID, taskID, noData, agencyIsLeaf);
				//throw new Exception("此种类型的表不支持导出dealType："+dealType);
			}
			
			String fileName = dictTModel.getName() + "_" + time + ".xlsx";
			
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			//response.addHeader("Content-Length", bytes.length + "");
			sxssfWorkbook.write(servletOutputStream);
			
			logger.debug("▇导出结束xlsx:"+time);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String fileName = "导出xlsx提示"+time+".txt";
				byte[] bytes = (fileName+":"+e.getMessage()).getBytes();
				createFile(response, fileName, bytes);
			} catch (Exception ee) {
				ee.printStackTrace();
			} 
		} finally {
			try {
				/*
				progressRequest(request, "导出完毕", 100);
				ExcelUtil.setProgressBarEND(request);
				
				if (servletOutputStream != null) {
					//servletOutputStream.flush();
					//servletOutputStream.close();
				}
				*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createFile(HttpServletResponse response, String fileName, byte[] bytes) throws Exception {
		response.reset();
		ServletOutputStream servletOutputStream = response.getOutputStream();
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.addHeader("Content-Length", bytes.length + "");
		InputStream inputStream = new ByteArrayInputStream(bytes);
		byte[] arrayOfByte = new byte[1024];
		int i = -1;
		while ((i = inputStream.read(arrayOfByte)) != -1){
			servletOutputStream.write(arrayOfByte, 0, i);
		}
		inputStream.close();
	}
	
	/*public void progressRequest(HttpServletRequest request, String text, int currentValue) throws Exception {
		request.setAttribute("curent_progressText", text);
		request.setAttribute("total_progressValue", 100);
		request.setAttribute("curent_progressValue", currentValue);
	}*/

	/**
	 * 导入xlsx
	 * @param request
	 * @param response
	 * @param limitSize
	 * @throws Exception
	 */
	@RequestMapping({"import"})
	public void importData(HttpServletRequest request, HttpServletResponse response, String limitSize) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		JSONObject json = new JSONObject();
		long startTime=System.currentTimeMillis();
		try {
			localPrintWriter = response.getWriter();
			
			UserDTO user = SecureUtil.getCurrentUser();
			if (user == null) {
				json.put("success", false);
				json.put("errorInfo", "用戶超時，请重新登陆");
				localPrintWriter.print(json.toString());
				localPrintWriter.close();
				return;
			}
			String appID = request.getParameter("appID");
			String tableID = request.getParameter("tableID");
			String agencyID = request.getParameter("agencyID");
			//String docID = request.getParameter("docID");
			String taskID = request.getParameter("taskID");
			String agencyIsLeaf = request.getParameter("agencyIsLeaf");
			//String flowType = request.getParameter("flowType");
			
			if (!ServletFileUpload.isMultipartContent(request)) {
				throw new Exception("不支持多文件导入！");
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileList = multipartRequest.getFiles("files[]");
			for (MultipartFile fileItem : fileList) {
				if (fileItem.isEmpty()) {
					continue;
				}
				// 如果有大小限制,单位M
				if (limitSize != null && !limitSize.trim().equals("")) {
					if (Integer.parseInt(limitSize) * 1024 * 1024 < Integer.valueOf(String.valueOf(fileItem.getSize()))) {
						throw new Exception("上传的单个文件大小不能超过" + limitSize + "M");
					}
				}
				String fileName = fileItem.getOriginalFilename();
				//截取目录
				if (fileName.indexOf("\\") != -1) {
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}
				//校验后缀
				if(fileName.contains(".")){
					String postfix = fileName.substring(fileName.lastIndexOf(".") + 1);
					if(StringUtils.isNotEmpty(postfix)){
						postfix = postfix.toLowerCase();
					}
					if(!"xlsx".equals(postfix)){
						throw new Exception("请导入xlsx文件");
					}
				}
				
				// 加入文件名单引号限制，防止引起内外网同步出错
				if (fileName.indexOf("'") != -1) {
					throw new Exception("上传的文件名称中不能包含单引号。");
				}
				
				//byte[] xlsxFile = fileItem.getBytes();
				InputStream inputStream = fileItem.getInputStream();
				logger.debug("★★★★开始导入-文件名："+fileName);
				
				//表信息
				DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
				String dealType = dictTModel.getDealtype();
				HashMap<String, Object> resultMap = null;
				//一般录入表和人员表
				if (Constants.TABLE_DEALTYPE_A0.equals(dealType)) {
					excelCommonService.saveDictTFactorxlsx(tableID, null, agencyIsLeaf);
					resultMap = this.excelCommonService.importData(request, inputStream);
				//固定行列表
				//}else if(Constants.TABLE_DEALTYPE_A1.equals(dealType)){
				//	resultMap = excelFixService.importData(request, inputStream);
				//基本数字表
				//}else if(Constants.TABLE_DEALTYPE_62.equals(dealType)){
				//	resultMap = excelBaseNumService.importData(request, inputStream);
				//人员表
				}else if(Constants.TABLE_DEALTYPE_A0_01.equals(dealType)){
					excelPersonService.saveDictTFactorxlsx(tableID, null, agencyIsLeaf);
					resultMap = excelPersonService.importData(request, inputStream);
				//转移支付表
				}else if(Constants.TABLE_DEALTYPE_A0_05.equals(dealType)){
					excelTransferPayService.saveDictTFactorxlsx(tableID, null, agencyIsLeaf);
					resultMap = excelTransferPayService.importData(request, inputStream);
				//其他表
				}else{
					//throw new Exception("此种类型的表不支持导入dealType："+dealType);
					excelService.saveDictTFactorxlsx(tableID, null, agencyIsLeaf);
					resultMap = this.excelService.importData(request, inputStream);
				}
				
				json.put("success", resultMap.get("success"));
				json.put("errorInfo", resultMap.get("errorInfo"));
				json.put("successInfo", resultMap.get("successInfo"));
				json.put("exportInfo", resultMap.get("exportInfo"));
				json.put("fileName", fileName);
				
				String exportInfo = "";
				if(resultMap.containsKey("exportInfo")){
					exportInfo = (String)resultMap.get("exportInfo");
				}
				Set<String> agencyList = null;
				if(resultMap.containsKey("agencyList")){
					agencyList = (Set<String>)resultMap.get("agencyList");
				}
				
				if(StringUtils.isNotEmpty(tableID) && StringUtils.isEmpty(exportInfo) && agencyList != null && agencyList.size() > 0){
					for(String agency : agencyList){
						//刷新列间公式
						excelService.refreshFormula_0(tableID, agency, appID);
						
						//标记
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("sql", "call pkg_formula.p_markneedupdate('"+tableID+"','"+agency+"')");
						excelService.callProcedure(map2, taskID);
					}
				}
				logger.debug("★★★★结束导入-文件名："+fileName);
				// 执行方法
				long endTime = System.currentTimeMillis();
				float excTime = (float) (endTime - startTime) / 1000;
				logger.error(fileName+"导入执行时间："+excTime+"s");

			}
			localPrintWriter.print(json.toString());
			
		} catch (FileUploadBase.SizeLimitExceededException e) {
			json.put("success", false);
			json.put("errorInfo", "文件大小限制");
			localPrintWriter.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("errorInfo", e.getMessage());
			localPrintWriter.print(json.toString());
		} finally {
			localPrintWriter.flush();
			localPrintWriter.close();
		}
	}
	
	/**
	 * 进度条
	 * @param request
	 * @param response
	 *//*
	@RequestMapping({"progress" })
	@ResponseBody
	public void progress(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		try {
			localPrintWriter = response.getWriter();
			HashMap progressMap = ExcelUtil.getProgressBar(request);
			if (progressMap != null) {
				JSONObject localJSONObject = new JSONObject();
				int curProgressValue = progressMap.get("curent_progressValue") == null ? 0 : ((Integer) progressMap.get("curent_progressValue")).intValue();
				if(curProgressValue < 99){
					progressMap.put("curent_progressValue", curProgressValue + 1);
				}
				
				String progressText = (String) progressMap.get("curent_progressText") + ".";
				localJSONObject.put("progressValue", curProgressValue);
				logger.debug(curProgressValue);
				localJSONObject.put("progressText", progressText);
				localJSONObject.put("success", true);
				String ss = localJSONObject.toString();
				localPrintWriter.print(ss);
				localPrintWriter.close();
			} else {
				JSONObject localJSONObject = new JSONObject();
				localJSONObject.put("success", false);
				localJSONObject.put("errorInfo", "正在执行导出进度");
				localPrintWriter.print(localJSONObject.toString());
				localPrintWriter.close();

			}
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}*/
	
	/**
	 * 导出代码表
	 * @param request
	 * @param response
	 */
	@RequestMapping({"exportCode"})
	@ResponseBody
	public void exportCode(HttpServletRequest request, HttpServletResponse response) {
		response.reset();
		ServletOutputStream servletOutputStream = null; 
		response.setContentType("application/msexcel;charset=UTF-8");	
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		try {
			servletOutputStream = response.getOutputStream();
			UserDTO user = SecureUtil.getCurrentUser();
			if (user == null) {
				throw new Exception("用戶超時，请重新登陆");
			}
			//progressRequest(request, "准备导出...", 0);
			//ExcelUtil.setProgressBar(request);
			logger.debug("▇开始导出代码表xlsx：任务类型相关代码表" + time);
			
			SXSSFWorkbook workbook = this.excelCodeService.exportData(request);
			//所选业务类型名称
			String taskTypeName = request.getParameter("taskTypeName");
			//文件名
			String fileName = taskTypeName + "_数据字典_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
			
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			//response.addHeader("Content-Length", bytes.length + "");
			workbook.write(servletOutputStream);
			
			logger.debug("▇结束导出代码表xlsx:任务类型相关代码表"+time);
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String fileName = "导出代码表提示信息"+time+".txt";
				byte[] bytes = (fileName+":"+e.getMessage()).getBytes();
				createFile(response, fileName, bytes);
			} catch (Exception ee) {
				ee.printStackTrace();
			} 
		} finally {
			try {
				//progressRequest(request, "导出完毕", 100);
				//ExcelUtil.setProgressBarEND(request);
				
				//servletOutputStream.flush();
				//servletOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 导出TXT
	 */
	@RequestMapping({"exportTxt"})
	@ResponseBody
	public void exportTxt(HttpServletRequest request, HttpServletResponse response) {
		response.reset();
		ServletOutputStream servletOutputStream = null;
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
		try {
			servletOutputStream = response.getOutputStream();
			/*progressRequest(request, "准备导出...", 0);
			ExcelUtil.setProgressBar(request);*/
			
			String data = request.getParameter("data");
			String fileName = request.getParameter("fileName");
			//去后缀
			if(fileName.contains(".")){
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
			}
			
			fileName = fileName + "_提示" + time +".txt";
			byte[] bytes = data.getBytes();
			if (bytes == null || bytes.length == 0){
				throw new Exception("TXT不能生成空文件！");
			}
			createFile(response, fileName, bytes);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				String fileName = "导出TXT报错"+time+".txt";
				byte[] bytes = (fileName+":"+e.getMessage()).getBytes();
				createFile(response, fileName, bytes);
			} catch (Exception ee) {
				ee.printStackTrace();
			} 
		} finally {
			try {
				/*progressRequest(request, "导出完毕", 100);
				ExcelUtil.setProgressBarEND(request);*/
				
				servletOutputStream.flush();
				servletOutputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}