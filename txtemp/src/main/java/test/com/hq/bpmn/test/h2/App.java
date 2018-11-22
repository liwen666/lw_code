//package test.com.hq.bpmn.test.h2;  
//  
//import java.awt.image.BufferedImage;
//import java.io.BufferedInputStream;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.sql.Blob;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;  
//  
//
//
//
//
//import java.sql.Statement;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import org.h2.tools.Server;  
//import org.springframework.context.ApplicationContext;  
//import org.springframework.context.support.AbstractApplicationContext;  
//import org.springframework.context.support.ClassPathXmlApplicationContext;  
//
//import com.hq.bpmn.common.bean.ProcessResult;
//import com.hq.bpmn.templatedef.bean.BpmnTemplateDef;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//  
//public class App {  
//    public static void main(String[] args) throws SQLException,  
//            ClassNotFoundException, UnsupportedEncodingException {  
//        ApplicationContext context = new ClassPathXmlApplicationContext(  
//                "test\\com\\hq\\bpmn\\test\\h2\\springContextTest.xml");  
//      
//        /**
//         * 获取数据库连接
//         *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
//         */
//        DataSource datasourceMaster = (DataSource) context.getBean("dataSourceMaster");
//        DataSource h2dataSource = (DataSource) context.getBean("h2dataSource");
//        //初始化数据库
//        System.out.println("初始化数据库表。。。");
////        H2ServerTest.createTable(h2dataSource);
//        //导入数据
//        System.out.println("初始化数据。。。");
//        H2ServerTest.copyDataFromOrcl4H2(datasourceMaster, h2dataSource);
//        
//        System.out.println();
//        System.out.println("=============");
//        System.out.println();
//        
//        BpmnTemplateDefDao  dao = (BpmnTemplateDefDao) context.getBean("bpmnTemplateDefDao");
//      List<BpmnTemplateDef> selectTemplateDef = dao.selectTemplateDef();
//      for(BpmnTemplateDef def: selectTemplateDef){
//    	  System.out.println(def.toString());
//      }
//     
////      System.out.println();
////      System.out.println("=============");
////      System.out.println();
////      BpmnTemplateDefServiceImpl bpmnService = (BpmnTemplateDefServiceImpl) context.getBean("bpmnTemplateDefServiceImpl");
////      System.out.println(bpmnService.queryTemplateDef().getResult().get(0).getId());
////      System.out.println(selectTemplateDef.get(0).toString());
////      System.out.println();
////      System.out.println("=============");
////      System.out.println();
////      BpmnTemplateDefController bpmnController =(BpmnTemplateDefController) context.getBean("bpmnTemplateDefController");
////      System.out.println(bpmnController.queryTemplateDef().getResult().get(0).toString());
//        ((AbstractApplicationContext) context).close(); 
//    }  
//    
//    /**
//     * 查询Oracle数据库
//     * @param h2dataSource 
//     * @throws SQLException 
//     * @throws ClassNotFoundException 
//     * @throws UnsupportedEncodingException 
//     */
//    public static DataResult queryOracle(DataSource datasource, DataSource h2dataSource) throws SQLException, ClassNotFoundException, UnsupportedEncodingException{
//		return null;
//    	
//    	
//    	
//    }
//    
//    
//   
//  
//}  