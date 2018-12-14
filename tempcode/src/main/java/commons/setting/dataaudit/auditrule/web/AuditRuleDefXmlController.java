package commons.setting.dataaudit.auditrule.web;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tjhq.commons.exception.ServiceException;
import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleDefService;
import com.tjhq.commons.setting.dataaudit.auditrule.po.AuditRuleColPO;
import com.tjhq.commons.setting.dataaudit.auditrule.service.IAuditRuleDefXmlService;
import com.tjhq.commons.utils.UserUtil;

/**
 * 
* @ClassName: AuditRuleXmlControllerNew
* @Description: 审核规则xml导出和导入
* @author xiehongtao
* @date 2017-6-9 下午5:43:32
*
*	@RequestMapping(value = "/commons/setting/auditrule/xml") //原来的请求路径
*
 */
@Controller
@RequestMapping(value = "/commons/setting/dataaudit/auditrule/xml")
public class AuditRuleDefXmlController {
	
	/**
	 * 审核规则定义服务
	 */
	@Resource
	private IAuditRuleDefService auditRuleDefService;
	
	/**
	 * 审核规则 xml 导入导出服务
	 */
	@Resource
	IAuditRuleDefXmlService auditRuleDefXmlService;
	
	/**
	* @Title: expData
	* @Description: 导出数据方法
	* @param @param req
	* @param @param response
	* @param @throws Exception
	* @return void
	* @throws
	 */
	@RequestMapping(value="expData")
	@ResponseBody
	public void expData(HttpServletRequest req,
			HttpServletResponse response) throws Exception{
		    String appId=req.getParameter("appId");

		   String checkIds=req.getParameter("checkIds");//选择下载ids

			//创建写出对象   
			Map<String,Object> paraMap =new HashMap<String,Object>();
 
				try{
					if(null!=checkIds || "".equals(checkIds)){
						checkIds="'"+checkIds.replaceAll(",", "','")+"'";
					}
					List<AuditRuleColPO> colList=auditRuleDefXmlService.findAuditColList();
					List<AuditRuleColPO> colList_fl=auditRuleDefXmlService.findAuditSortColList();
					paraMap.put("appID", appId);
					paraMap.put("checkIDs", checkIds);
					//审核
					List<Map<String,String>> auditList=auditRuleDefXmlService.findAuditData(paraMap);
					//审核分类
					List<Map<String,String>> auditSortList=auditRuleDefXmlService.findAuditSortDataByExp(paraMap);
					//视图对应物理表
					List<Map<String,String>> viewToTableList=auditRuleDefXmlService.findViewToTable(paraMap);
					//录入对象对应审核对象
					List<Map<String,String>> inputObjToAuditObjList=auditRuleDefXmlService.findInputObjToAuditObj(paraMap);
				    Document doc = DocumentHelper.createDocument(); 
					Element rootEle =doc.addElement("AuditRules");
					
					rootEle.addComment("审核规则数据  导出时间:"+auditRuleDefXmlService.getSysDate());
					Element sysEle=rootEle.addElement("SYSID");
					sysEle.setText(appId);
					
					Element AuditSortsEle=rootEle.addElement("AuditSorts");
					
					for(Map<String,String> ele:auditSortList){
						appendXmpNode("AuditSort",AuditSortsEle,ele,colList_fl);
					}
					//加入视图对应物理表
					Element viewTOTablesEle=rootEle.addElement("ViewToTables");
					List<AuditRuleColPO> viewToTableColList=auditRuleDefXmlService.findViewToTableColList();
					if(null!=viewToTableList&&viewToTableList.size()>0){
						for(Map<String,String> ele:viewToTableList){
						appendXmpNode("ViewToTable",viewTOTablesEle,ele,viewToTableColList);
					}
					}
	
					//加入录入对象对应审核对象
					Element inputObjTOAuditObjEle=rootEle.addElement("InputObjToAuditObjs");
					List<AuditRuleColPO> inputObjToAuditObjColList=auditRuleDefXmlService.findInputObjToAuditObjColList();
					if(null!=inputObjToAuditObjList&&inputObjToAuditObjList.size()>0){
						for(Map<String,String> ele:inputObjToAuditObjList){
						appendXmpNode("InputObjToAuditObj",inputObjTOAuditObjEle,ele,inputObjToAuditObjColList);
					}
					}
					
					for(Map<String,String> ele:auditList){
						appendXmpNode("AuditRule",rootEle,ele,colList);
						
					}
					//auditRuleService
					String strFileName = "审核规则数据集合.xml";
					strFileName = java.net.URLEncoder.encode(strFileName, "UTF-8");//处理中文文件名的问题
					strFileName = new String(strFileName.getBytes("UTF-8"),"GBK");
					response.reset();
					//response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition","attachment; filename=" + strFileName);
					OutputStream os = response.getOutputStream(); 	
					OutputFormat format = OutputFormat.createPrettyPrint();   
					format.setEncoding("UTF-8");  
					XMLWriter writer = new XMLWriter(os,format); 
					writer.write(doc);
					writer.flush();
					writer.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
	}
	
	/**
	* @Title: appendXmpNode
	* @Description:增加数据节点
	* @param @param elementName
	* @param @param rootEle
	* @param @param ele
	* @param @param colList
	* @return void
	* @throws
	 */
	private void appendXmpNode(String elementName,Element rootEle, Map<String, String> ele,List<AuditRuleColPO> colList) {
		Element auditRuleEle=rootEle.addElement(elementName);
		for(AuditRuleColPO col: colList){
			//if(col.isVirtual())continue;
			Element childNode=auditRuleEle.addElement(col.getColDbName());
			String v=ele.get(col.getColDbName());
			if(null!=v && !"".equals(v)){
				if(col.isNeedCDATA()){
					childNode.addCDATA(v);
				}else{
					childNode.setText(v);
				}
			}else{
				childNode.setText("");
			}
		}
	}
	

	/**
	* @Title: impXmlFile
	* @Description: 导入审核规则数据
	* @param @param request
	* @param @param response
	* @param @param files
	* @return void
	* @throws
	 */
	@RequestMapping("/impXmlFile") 
   public void impXmlFile(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "files[]", required = false) MultipartFile[] files){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter localPrintWriter = null;
		JSONObject localJSONObject = new JSONObject();
		try {
			localPrintWriter = response.getWriter();
			String appID = request.getParameter("appID");
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("appID", appID);
			paramMap.put("USERDISTRICTID",UserUtil.getUserInfo().getAdmdiv());
			
			//判断是普通表单，还是带文件上传的表单。文件上传的表单值不能按普通表单接收值那样直接获取。
			if (!ServletFileUpload.isMultipartContent(request)) {
				localJSONObject.put("success", false);
				localPrintWriter.print(localJSONObject.toString());
				localPrintWriter.close();
				return;
			}
			              
				if (null==files || files.length==0) {
					localJSONObject.put("success", false);
					localJSONObject.put("errorInfo", "上传文件集合为空");
					localPrintWriter.print(localJSONObject.toString());
					localPrintWriter.close();
					return;
				}
				SAXReader reader = new SAXReader();    
				MultipartFile fileItem =files[0];
				String fileName = fileItem.getOriginalFilename();
				if (fileName.indexOf("\\") != -1) {
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
				}

				// 加入文件名单引号限制，防止引起内外网同步出错
				if (fileName.indexOf("'") != -1) {
					throw new Exception("上传的文件名称中不能包含单引号。");
				}
				
				Document   document = reader.read(fileItem.getInputStream());
				Element root = document.getRootElement(); 
				String sysID=root.element("SYSID").getTextTrim();
				if(!sysID.equals(appID)){//检查是不是同一个业务系统
					throw new Exception("系统ID不一致 sysID="+sysID+"  appID="+appID);
				}
				
				paramMap.put("userBgtLevel", auditRuleDefService.findBudgetLevel(UserUtil.getUserInfo().getUpAgencyID()));
				
				auditRuleDefXmlService.impXmlData(paramMap,root);

				localJSONObject.put("success", true);
				localPrintWriter.print(localJSONObject.toString());
			
		} catch (FileUploadBase.SizeLimitExceededException localSizeLimitExceededException) {
			localJSONObject.put("success", false);
			localJSONObject.put("errorInfo", "文件大小限制");
			localPrintWriter.print(localJSONObject.toString());
		}catch (ServiceException localException) {
			localException.printStackTrace();
			localJSONObject.put("success", false);
			localJSONObject.put("errorInfo", localException.getErrorMessage());
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
}

