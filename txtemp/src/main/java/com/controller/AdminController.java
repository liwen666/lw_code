package com.controller;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;





import com.bendi.BpmnTemplateDefDao;
import com.client.ActBusiLogDomain;
import com.client.IBpmnCommonService;
import com.h2.BpmnTemplateDefDaoH2;
import com.h2.H2DataInit;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
import com.schedule.ActBusiLogDao;
import com.service.DatainitServiceH2;

import util.ApplicationContextUtil;
import util.DataConversion;

@Controller
@RequestMapping(value = "/client/page")
public class AdminController {
	private static int m =0;
	public static void main(String[] args) throws Exception {
		inputOfficCheckDoc(null, null);
	}
	
	@RequestMapping(value = "/index")
	public static String inputOfficCheckDoc(HttpServletRequest request, Model model) throws Exception {
		System.out.println("_________________hello_________________");
		
//		ApplicationContext context = new ClassPathXmlApplicationContext("com/springContextTest.xml");  
		ApplicationContext context = ApplicationContextUtil.getContext();
		System.out.println(context);
		
		
		 IBpmnCommonService client = (IBpmnCommonService) context.getBean("client");  
		
         String userId = client.userId("88888888");
         System.out.println(userId);
         List<com.client.BpmnTemplateDef> initBpmnTemplateDef = client.initBpmnTemplateDef();
         BpmnTemplateDefDaoH2  dao = (BpmnTemplateDefDaoH2) context.getBean("bpmnTemplateDefDaoH2");
         DatainitServiceH2  h2Service = (DatainitServiceH2) context.getBean("datainitServiceH2");
         DataSource h2 = (DataSource) context.getBean("h2dataSource");
         
         if(m==0){
        	 H2DataInit.createTable(h2);
        	 m++;
         }
         
         System.out.println("添加远程数据");
         for(com.client.BpmnTemplateDef b :initBpmnTemplateDef){
//             int addBpmnDefTem = h2Service.addBpmnDefTempH2((BpmnTemplateDef) DataConversion.XmlObjectToObject(b, new BpmnTemplateDef()));
         }
         System.out.println("查询H2内存数据");
//         List<BpmnTemplateDef> selectTemplateDef = h2Service.selectTemplateDefH2();
//        	 System.out.println(selectTemplateDef.size());
//       	  System.out.println(new String(selectTemplateDef.get(0).getContentBytes(),"gbk"));
          
//         将数据导入到本地数据库
//       	h2Service.addBpmnDefTempBendi(selectTemplateDef.get(0));
       	System.out.println(h2Service.selectTemplateDefBendi().size());
       	 ActBusiLogDao daolog = (ActBusiLogDao) context.getBean("actBusiLogDao");
       	 ActBusiLogDomain dom = new ActBusiLogDomain();
	       dom.setAppId("bpmn");
	       String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	       dom.setBusiLogId(uuid);
	       daolog.addLogId(dom);
       	  
       	  
  		return "/client/index";
	}  
	@RequestMapping(value = "/home")
	public static String homePage(HttpServletRequest request, Model model) throws Exception {
		System.out.println("_________________hello_________________");
		throw new Exception("错误页面");
		
//		return "/client/home";
	}  
}
