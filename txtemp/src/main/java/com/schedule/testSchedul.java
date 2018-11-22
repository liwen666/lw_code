package com.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.client.ActBusiLogDomain;
import com.client.IBpmnCommonService;

import util.ApplicationContextUtil;

public class testSchedul {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/schedule/TimeConfig.xml");
	}
	  public static void execute() throws IOException
	    {
		  
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        System.out.println("do my job"+dateFormat.format(new Date()));
	        
	        ApplicationContext context = ApplicationContextUtil.getContext();
	       ActBusiLogDao dao = (ActBusiLogDao) context.getBean("actBusiLogDao");
	       ActBusiLogDomain dom = new ActBusiLogDomain();
	       dom.setAppId("bpmn");
	       String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	       dom.setBusiLogId(uuid);
	       dao.addLogId(dom);
	       
	       List<ActBusiLogDomain> logList = new ArrayList<ActBusiLogDomain>();
	       logList.add(dom);
	       logList.add(dom);
	       int batchAddLogId = dao.batchAddLogId(logList);
	       System.out.println(batchAddLogId);
	        
	        
	    }
//	  定时数据传输
	  public void addBusiLogId(){
		  ApplicationContext context = ApplicationContextUtil.getContext();
		  IBpmnCommonService client = (IBpmnCommonService) context.getBean("client"); 
		  ActBusiLogDomain dom = new ActBusiLogDomain();
 	       dom.setAppId("bpmn");
 	       String uuid = UUID.randomUUID().toString().replaceAll("-", "");
 	       dom.setBusiLogId(uuid);
            List<ActBusiLogDomain> logList = new ArrayList<ActBusiLogDomain>();
 	       logList.add(dom);
 	       logList.add(dom);
            boolean receiveBusiLog = client.receiveBusiLog(logList);
            System.out.println(receiveBusiLog);
		  
		  
		  
		  
	  }
	  
}
