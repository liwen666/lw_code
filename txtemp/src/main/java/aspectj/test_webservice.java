package aspectj;  
  
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.DataConversion;

import com.client.IBpmnCommonService;
import com.client.Model;
import com.h2.BpmnTemplateDefDaoH2;
import com.h2.H2DataInit;
import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;

  
public class test_webservice {  
    public static void main(String[] args) throws UnsupportedEncodingException, SQLException, IllegalArgumentException, IllegalAccessException {  
        @SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("aspectj/springContextTest.xml");  
        IBpmnCommonService client = (IBpmnCommonService) ctx.getBean("client");  
                     String userId = client.userId("88888888");
                     System.out.println(userId);
                     Model m = new Model();
                     m.setAge(10);
                     Model initBpmnData = client.initBpmnData(m);
                     System.out.println(initBpmnData.getAge()+initBpmnData.getName());
                     List<com.client.BpmnTemplateDef> initBpmnTemplateDef = client.initBpmnTemplateDef();
                     BpmnTemplateDefDaoH2  dao = (BpmnTemplateDefDaoH2) ctx.getBean("bpmnTemplateDefDaoH2");
                     DataSource h2 = (DataSource) ctx.getBean("h2dataSource");
                     H2DataInit.createTable(h2);
                     System.out.println("添加远程数据");
                     for(com.client.BpmnTemplateDef b:initBpmnTemplateDef){
                         int addBpmnDefTem = dao.addBpmnDefTemp1((BpmnTemplateDef) DataConversion.XmlObjectToObject(b, new BpmnTemplateDef()));
                     }
                     System.out.println("查询H2内存数据");
                     List<BpmnTemplateDef> selectTemplateDef = dao.selectTemplateDef();
                     for(BpmnTemplateDef def: selectTemplateDef){
                   	  System.out.println(def.getCreateTime()+"==id======"+def.getId());
                   	  System.out.println(new String(def.getContentBytes(),"gbk"));
                     }
    }  
}  