package commons.excel.web;

import gov.mof.fasp2.ca.user.dto.UserDTO;
import gov.mof.fasp2.sec.syncuserinfo.SecureUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tjhq.commons.dict.external.po.DictTModelPO;
import com.tjhq.commons.dict.external.service.IDictTModelService;
import com.tjhq.commons.excel.dao.ExcelFileMapper;
import com.tjhq.commons.excel.service.ExcelFileServerService;
import com.tjhq.commons.excel.util.ExcelProgressBarUtil;

@Controller
//@Scope("prototype")
@RequestMapping( { "/commons/excelfileserver" })
public class ExcelFileServerController {

	public Logger logger = Logger.getLogger(ExcelFileServerController.class);
	
	@Resource
	public ExcelFileServerService excelFileServerService;
	
	@Resource
	private IDictTModelService dictTModelService;
	
	@Resource
	public ExcelFileMapper excelFileMapper;

	// 导出
	@RequestMapping( { "ExportExcelFile" })
	@ResponseBody
	public void ExportExcelFile(HttpServletRequest request, HttpServletResponse response, String data, String grid) {
		response.reset();
		ServletOutputStream localServletOutputStream = null;
		try {
			localServletOutputStream = response.getOutputStream();
			
			UserDTO user = SecureUtil.getCurrentUser();
			if (user == null) {
				throw new Exception("用戶超時，请重新登陆");
			}
			
			request.setAttribute("curent_progressText", "准备导出...");
			request.setAttribute("total_progressValue", 100);
			request.setAttribute("curent_progressValue", 0);
			ExcelProgressBarUtil.setProgressBar(request);
			
			HashMap<String, Object> map = (HashMap) new ObjectMapper().readValue(data, HashMap.class);
			
			String agencyID = (String) map.get("agencyID");
			//导出通用模板，因为页面未选择单位，所以使用当前用户单位id，单纯防止excel宏代码出错，没有其他影响
			String isCommon = (String) map.get("isCommon");
			
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date); 
			
			if(StringUtils.isNotEmpty(isCommon) && "1".equals(isCommon) && StringUtils.isEmpty(agencyID) ){
				logger.debug("▇▇▇▇开始导出：通用模板"+time+data);
				agencyID = user.getAgency();
				map.put("agencyID", agencyID);
			}else{
				String noData = request.getParameter("noData");
				String noHiddenSheet = request.getParameter("noHiddenSheet");
				if(StringUtils.isNotEmpty(noData) && "1".equals(noData)){
					logger.debug("▇▇▇▇开始导出：空模板"+time+data);
				}else if(StringUtils.isNotEmpty(noHiddenSheet) && "1".equals(noHiddenSheet)){
					logger.debug("▇▇▇▇开始导出：导出数据（不可导入）"+time+data);
				}else{
					logger.debug("▇▇▇▇开始导出：导出模板数据"+time+data);
				}
			}
			HashMap<String, Object> retMap = null;
			String isSuit = (String) map.get("isSuit");
			if ("suit".equalsIgnoreCase(isSuit)) {
				//retMap = this.excelFileServerService.ExportSuitDataToExcel(map, request);
			} else {
				String tableID = (String) map.get("tableID");
				excelFileServerService.saveDictTFactorxlsx(tableID);//处理Dict_T_Factorxlsx表的功能提取到service外与getData的事务分开，在查询数据慢时不锁表Dict_T_Factorxlsx
				
				retMap = this.excelFileServerService.ExportDataToExcel(map, grid, request);
			}
			logger.debug("▇▇导出结束"+time+data+user);
			String fn = (String) retMap.get("filename");
			byte[] ognxlsxBin = (byte[]) retMap.get("filelob");
			if (retMap.get("success") == null || !(Boolean) retMap.get("success")) {
				ognxlsxBin = ((String) retMap.get("errorInfo")).getBytes();
				fn = "提示信息.txt";
			}
			if (ognxlsxBin == null || ognxlsxBin.length == 0)
				throw new Exception("不能生成 " + fn + " 的excel导出文件！");

			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, "UTF-8"));
			response.addHeader("Content-Length", ognxlsxBin.length + "");
			InputStream localInputStream = new ByteArrayInputStream(ognxlsxBin);
			byte[] arrayOfByte = new byte[1024];
			int i = -1;
			while ((i = localInputStream.read(arrayOfByte)) != -1)
				localServletOutputStream.write(arrayOfByte, 0, i);
			localInputStream.close();

			request.setAttribute("curent_progressText", "导出完毕！");
			request.setAttribute("total_progressValue", 100);
			request.setAttribute("curent_progressValue", 100);
			ExcelProgressBarUtil.setProgressBarEND(request);
			Thread.sleep(1000);
		} catch (Exception localException) {
			localException.printStackTrace();

			request.setAttribute("curent_progressText", "导出错误！");
			request.setAttribute("curent_progressValue", 100);
			request.setAttribute("total_progressValue", 100);
			ExcelProgressBarUtil.setProgressBarEND(request);

			byte[] errstrBin = localException.getMessage().getBytes();
			String fn = "提示信息.txt";
			response.setContentType("application/msexcel;charset=UTF-8");
			try {
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, "UTF-8"));
				response.addHeader("Content-Length", errstrBin.length + "");
				InputStream localInputStream = new ByteArrayInputStream(errstrBin);
				byte[] arrayOfByte = new byte[1024];
				int i = -1;
				while ((i = localInputStream.read(arrayOfByte)) != -1){
					localServletOutputStream.write(arrayOfByte, 0, i);
				}
				localInputStream.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				localServletOutputStream.flush();
				localServletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 导入
	@RequestMapping( { "ImportExcelFile" })
	public void ImportExcelFile(HttpServletRequest request, HttpServletResponse response, String storeFilePath, String limitSize) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		JSONObject localJSONObject = new JSONObject();
		
		try {
			localPrintWriter = response.getWriter();
			
			UserDTO user = SecureUtil.getCurrentUser();
			if (user == null) {
				localJSONObject.put("success", false);
				localJSONObject.put("errorInfo", "用戶超時，请重新登陆");
				localPrintWriter.print(localJSONObject.toString());
				localPrintWriter.close();
				return;
			}
			
			String tableID = request.getParameter("tableID");
			String taskID = request.getParameter("taskID");
			String appID = request.getParameter("appID");
			String agencyID = request.getParameter("agencyID");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskID", taskID);
			paramMap.put("appID", appID);
			paramMap.put("agencyID", agencyID);
			if (!ServletFileUpload.isMultipartContent(request)) {
				localJSONObject.put("success", false);
				localPrintWriter.print(localJSONObject.toString());
				localPrintWriter.close();
				return;
			}
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileList = multipartRequest.getFiles("files[]");
			for (MultipartFile fileItem : fileList) {
				if (fileItem.isEmpty()) {
					continue;
				}
				// 如果有大小限制,单位M
				if (limitSize != null && !limitSize.trim().equals("")) {
					if (Integer.parseInt(limitSize) * 1024 * 1024 < Integer
							.valueOf(String.valueOf(fileItem.getSize()))) {
						throw new Exception("上传的单个文件大小不能超过" + limitSize + "M。");
					}
				}
				String fileName = fileItem.getOriginalFilename();
				if (fileName.indexOf("\\") != -1) {
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}

				// 加入文件名单引号限制，防止引起内外网同步出错
				if (fileName.indexOf("'") != -1) {
					throw new Exception("上传的文件名称中不能包含单引号。");
				}
				
				byte[] xlsxFile = fileItem.getBytes();
				logger.debug("★★★★开始导入-文件名："+fileName);
				
				DictTModelPO dictTModel = dictTModelService.getDictTModelByID(tableID, false);
				
				HashMap resultMap = this.excelFileServerService.handleAndSaveExcelData(xlsxFile, fileName, tableID, paramMap, request, dictTModel); // River修改20151230
				
				String dbTableName = dictTModel.getDbtablename();
				if(StringUtils.isNotEmpty(dbTableName)){
					this.excelFileServerService.callProcedure_p_clear_deleted_data(dbTableName, agencyID);
				}
				// 刷新列间公式
				if(StringUtils.isNotEmpty(tableID)){
					this.excelFileServerService.refreshFormula_0(tableID, agencyID, appID);
				}
				logger.debug("★★★★结束导入-文件名："+fileName);
				
				localJSONObject.put("errorInfo", resultMap.get("errorInfo"));
				localJSONObject.put("successInfo", resultMap.get("successInfo"));
				localJSONObject.put("success", resultMap.get("success"));
				// 只处理了的单个文件，注意多个文件处理仅返回了最后一个文件的处理结果

			}
			localPrintWriter.print(localJSONObject.toString());
			
		} catch (FileUploadBase.SizeLimitExceededException localSizeLimitExceededException) {
			localJSONObject.put("success", false);
			localJSONObject.put("errorInfo", "文件大小限制");
			localPrintWriter.print(localJSONObject.toString());
		} catch (Exception localException) {
			localException.printStackTrace();
			localJSONObject.put("success", false);
			localJSONObject.put("errorInfo", localException.getMessage());
			localPrintWriter.print(localJSONObject.toString());
		} finally {
			localPrintWriter.flush();
			localPrintWriter.close();
		}
	}
	
	/*@RequestMapping({"uploadFile" })
	public void uploadFile(HttpServletRequest request,
			HttpServletResponse response, String storeFilePath, String limitSize)
			throws JSONException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		JSONObject localJSONObject = new JSONObject();
		try {
			localPrintWriter = response.getWriter();

			String tableid = request.getParameter("tableid");
			String isVbaMacro = request.getParameter("isVbaMacro");
			if (!ServletFileUpload.isMultipartContent(request)) {
				localJSONObject.put("success", false);
				localPrintWriter.print(localJSONObject.toString());
				localPrintWriter.close();
				return;
			}

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> fileList = multipartRequest.getFiles("files[]");
			for (MultipartFile fileItem : fileList) {
				if (fileItem.isEmpty()) {
					continue;
				}
				// 如果有大小限制,单位M
				if (limitSize != null && !limitSize.trim().equals("")) {
					if (Integer.parseInt(limitSize) * 1024 * 1024 < Integer
							.valueOf(String.valueOf(fileItem.getSize()))) {
						throw new Exception("上传的单个文件大小不能超过" + limitSize + "M。");
					}
				}
				String fileName = fileItem.getOriginalFilename();
				if (fileName.indexOf("\\") != -1) {
					fileName = fileName
							.substring(fileName.lastIndexOf("\\") + 1);
				}
				// 加入文件名单引号限制，防止引起内外网同步出错
				if (fileName.indexOf("'") != -1) {
					throw new Exception("上传的文件名称中不能包含单引号。");
				}
				Map resultMap = null;
				byte[] xlsxFile = fileItem.getBytes();
				if ("1".equalsIgnoreCase(isVbaMacro))
					resultMap = this.excelFileServerService.saveXlsmTpl(xlsxFile, fileName,
							tableid);
				else
					resultMap = this.excelFileServerService.handleAndSaveExcelTpl(xlsxFile,
							fileName, tableid);

				localJSONObject.put("success", resultMap.get("success"));
				localJSONObject.put("errorInfo", resultMap.get("errorInfo"));
			}
			localPrintWriter.print(localJSONObject.toString());
		} catch (FileUploadBase.SizeLimitExceededException localSizeLimitExceededException) {
			localJSONObject.put("success", false);
			localJSONObject.put("errorInfo", "文件大小限制");
			localPrintWriter.print(localJSONObject.toString());
		} catch (Exception localException) {
			localException.printStackTrace();
			localJSONObject.put("success", false);
			localJSONObject.put("errorInfo", localException.getMessage());
			localPrintWriter.print(localJSONObject.toString());
		} finally {
			localPrintWriter.flush();
			localPrintWriter.close();
		}
	}*/

	/*@RequestMapping( { "generateXlsxFromDB" })
	public void generateXlsxFromDB(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter localPrintWriter = response.getWriter();
			String tableid = request.getParameter("tableid");
			String xlsxType = request.getParameter("xlsxType");
			String isSuit = request.getParameter("isSuit");

			Map ret;
			if ("1".equalsIgnoreCase(isSuit))
				ret = this.excelFileServerService.generateXlsxSuitFromDB(tableid, request);
			else
				ret = this.excelFileServerService.generateXlsxFromDB(tableid, request);

			JSONObject localJSONObject = new JSONObject();
			BigDecimal bsize = new BigDecimal(0);

			localJSONObject.put("success", ret.get("success"));
			localPrintWriter.print(localJSONObject.toString());
			localPrintWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/

	/*@RequestMapping( { "checkXlsxExists" })
	public void checkXlsxExists(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		try {
			localPrintWriter = response.getWriter();

			String tableid = request.getParameter("tableid");
			String xlsxType = request.getParameter("xlsxType");
			JSONObject localJSONObject = new JSONObject();
			BigDecimal bsize = new BigDecimal(0);
			Map ret;
			if (xlsxType.equalsIgnoreCase("downVBAxlsm")) {
				ret = this.excelFileServerService.getVBAXlsmSize(tableid);
				bsize = (BigDecimal) ret.get("VBAXLSMBLOBsize");
			} else {
				// 暂不用检测，用户导出时生成模版、填充数据、导出
				ret = this.excelFileServerService.getXlsxSize(tableid);

				if (ret != null) {
					if (xlsxType.equalsIgnoreCase("originalXlsx"))
						bsize = (BigDecimal) ret.get("ORIGINALXLSXTPLSIZE");
					else if (xlsxType.equalsIgnoreCase("nullXlsxTpl"))
						bsize = (BigDecimal) ret.get("NULLXLSXTPLSIZE");
					else if (xlsxType.equalsIgnoreCase("datapartXlsx"))
						bsize = (BigDecimal) ret.get("DATAPARTXLSXSIZE");
					else if (xlsxType.equalsIgnoreCase("stylepartXlsx"))
						bsize = (BigDecimal) ret.get("STYLEPARTXLSXSIZE");
					else if (xlsxType.equalsIgnoreCase("generateXlsx"))
						bsize = (BigDecimal) ret.get("ORIGINALXLSXTPLSIZE");
					else if (xlsxType.equalsIgnoreCase("uploadExcel"))
						bsize = (BigDecimal) ret.get("ORIGINALXLSXTPLSIZE");
				}

			}

			long size = bsize.longValue();
			if (size > 0)
				localJSONObject.put("success", true);
			else
				localJSONObject.put("success", false);
			localPrintWriter.print(localJSONObject.toString());
			localPrintWriter.close();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}*/

	/*@RequestMapping( { "downloadFile" })
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response) {
		String tableid = request.getParameter("tableid");
		String xlsxType = request.getParameter("xlsxType");
		response.reset();
		ServletOutputStream localServletOutputStream = null;
		try {
			localServletOutputStream = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (xlsxType.equalsIgnoreCase("originalXlsx")) {
				Map ognxlsx = this.excelFileServerService.getXlsxOriginalFile(tableid);
				String fn = (String) ognxlsx.get("filename");
				byte[] ognxlsxBin = (byte[]) ognxlsx.get("filelob");
				if (ognxlsxBin == null || ognxlsxBin.length == 0)
					throw new Exception("不能找到 " + fn + " 的excel模板文件！");
				fn = fn + ".xlsx";
				response.setContentType("application/msexcel;charset=UTF-8");
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fn, "UTF-8"));
				response.addHeader("Content-Length", ognxlsxBin.length + "");
				InputStream localInputStream = new ByteArrayInputStream(
						ognxlsxBin);
				byte[] arrayOfByte = new byte[1024];
				int i = -1;
				while ((i = localInputStream.read(arrayOfByte)) != -1)
					localServletOutputStream.write(arrayOfByte, 0, i);
				localInputStream.close();
			} else if (xlsxType.equalsIgnoreCase("nullXlsxTpl")) {
				Map ognxlsx = this.excelFileServerService.getXlsxNullTpl(tableid);
				String fn = (String) ognxlsx.get("filename");
				byte[] ognxlsxBin = (byte[]) ognxlsx.get("filelob");
				if (ognxlsxBin == null || ognxlsxBin.length == 0)
					throw new Exception("不能找到 " + fn + " 的excel 空模板文件！");
				fn = fn + "null.xlsx";
				response.setContentType("application/msexcel;charset=UTF-8");
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fn, "UTF-8"));
				response.addHeader("Content-Length", ognxlsxBin.length + "");
				InputStream localInputStream = new ByteArrayInputStream(
						ognxlsxBin);
				byte[] arrayOfByte = new byte[1024];
				int i = -1;
				while ((i = localInputStream.read(arrayOfByte)) != -1)
					localServletOutputStream.write(arrayOfByte, 0, i);
				localInputStream.close();
			} else if (xlsxType.equalsIgnoreCase("datapartXlsx")) {
				Map ognxlsx = this.excelFileServerService.getXlsxDatapart(tableid);
				String fn = (String) ognxlsx.get("filename");
				String datapart = (String) ognxlsx.get("filelob");
				if (datapart == null || datapart.length() == 0)
					throw new Exception("不能找到 " + fn + " 的excel 数据文件！");
				fn = fn + "data.txt";
				response.setContentType("application/xml;charset=UTF-8");
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fn, "UTF-8"));
				response.addHeader("Content-Length", datapart.length() + "");
				BufferedOutputStream Buff = new BufferedOutputStream(
						localServletOutputStream);
				Buff.write(datapart.getBytes("UTF-8"));
				Buff.flush();
				Buff.close();
			} else if (xlsxType.equalsIgnoreCase("stylepartXlsx")) {
				Map ognxlsx = this.excelFileServerService.getXlsxStylepart(tableid);
				String fn = (String) ognxlsx.get("filename");
				String datapart = (String) ognxlsx.get("filelob");
				if (datapart == null || datapart.length() == 0)
					throw new Exception("不能找到 " + fn + " 的excel 格式文件！");
				fn = fn + "data.xml";
				response.setContentType("application/xml;charset=UTF-8");
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fn, "UTF-8"));
				response.addHeader("Content-Length", datapart.length() + "");
				BufferedOutputStream Buff = new BufferedOutputStream(
						localServletOutputStream);
				Buff.write(datapart.getBytes());
				Buff.flush();
				Buff.close();
			} else if (xlsxType.equalsIgnoreCase("downVBAxlsm")) {

				Map vbaXlsm = this.excelFileServerService.getVBAXlsmFile(tableid);
				String fn = (String) vbaXlsm.get("filename");
				byte[] vbafilepart = (byte[]) vbaXlsm.get("filelob");
				if (vbafilepart == null || vbafilepart.length == 0)
					throw new Exception("不能找到 " + fn + " 的excel xlsm格式文件！");
				// fn=fn+"xlsm";
				response.setContentType("application/xml;charset=UTF-8");
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fn, "UTF-8"));
				response.addHeader("Content-Length", vbafilepart.length + "");
				InputStream localInputStream = new ByteArrayInputStream(
						vbafilepart);
				byte[] arrayOfByte = new byte[1024];
				int i = -1;
				while ((i = localInputStream.read(arrayOfByte)) != -1)
					localServletOutputStream.write(arrayOfByte, 0, i);
				localInputStream.close();
			}

		} catch (Exception localException) {
			localException.printStackTrace();
		} finally {

			try {
				localServletOutputStream.flush();
				localServletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

	@RequestMapping({"checkProgressBar" })
	@ResponseBody
	public void checkProgressBar(HttpServletRequest request,
			HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		try {
			localPrintWriter = response.getWriter();
			HashMap progressMap = ExcelProgressBarUtil.getProgressBar(request);
			if (progressMap != null) {
				JSONObject localJSONObject = new JSONObject();
				int curProgressValue = progressMap.get("curent_progressValue") == null ? 0
						: ((Integer) progressMap.get("curent_progressValue"))
								.intValue();
				String progressText = (String) progressMap
						.get("curent_progressText")
						+ ".";
				localJSONObject.put("progressValue", curProgressValue);
				localJSONObject.put("progressText", progressText);
				localJSONObject.put("success", true);
				String ss = localJSONObject.toString();
				localPrintWriter.print(localJSONObject.toString());
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
	}

	
}