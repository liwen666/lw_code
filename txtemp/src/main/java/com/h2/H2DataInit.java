package com.h2;  
  
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;
import java.sql.SQLException;  
import java.sql.Statement;  
  







import javax.sql.DataSource;

import org.h2.tools.Server;  
  
public class H2DataInit {  
	private  final static String ACT_GE_BYTEARRAY_SQL= "insert into act_ge_bytearray (ID_, REV_, NAME_, DEPLOYMENT_ID_, "
						+ "BYTES_, GENERATED_)values (?, ?, ?, ?, ?, ?)";
	private  final static String ACT_HQ_TEM_DEF_SQL= "insert into ACT_HQ_TEM_DEF (ID, NAME, CATEGORY, VERSION, CREATE_BY, MODIFY_BY, CREATE_TIME, MODIFY_TIME, DEPLOY_STATE, VERSION_STATE, DEPLOYMENT_ID, CONTENT_BYTES,"
    	         		+ " INIT_NUM, CANVASWIDTH, CANVASHEIGHT, TABLE_IDS, TABLE_NAMES) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?)";
	private  final static String ACT_HQ_TASK_EXTEND_SQL= "insert into ACT_HQ_TASK_EXTEND (ID, PROCESS_DEFINITION_ID, TASK_KEY, "
						+ "IS_CREATE_SUB_TASK, CUSTOM_BUTTON, IS_TO_BE_RECEIVED, IS_TO_BE_REVOKED, IS_TO_BE_RETURNED, TASK_TYPE, PROCESSOR, PROCESSOR_EXPRESSION, SHOW_TYPE, IS_TO_ALL_USER, IS_TO_BE_FREEDOM_NODE, IS_ENABLED_ALL_USER, CANDIDATE_GROUP_, IS_DEFAULT_USER) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  
  /**
   * 初始化数据库表
   * @param h2dataSource
   * @throws SQLException
   */
    public static void createTable(DataSource h2dataSource) throws SQLException{
    	// create table  
    	Connection conn = h2dataSource.getConnection();
    	Statement createStatement = conn.createStatement();
        
    	createStatement.execute("create table ACT_GE_BYTEARRAY ("
								  +"  ID_ varchar(64),           "
								  +"  REV_ integer,              "
								  +"  NAME_ varchar(255),        "
								  +"  DEPLOYMENT_ID_ varchar(64),"
								  +"  BYTES_ longvarbinary,      "
								  +"  GENERATED_ bit,            "
								  +"  primary key (ID_)          "
								  + ")");                             
        
    	createStatement.execute("create table ACT_HQ_TEM_DEF"
								+"	(                                      "
								+"	  id            VARCHAR2(64) not null, "
								+"	  name          VARCHAR2(64),          "
								+"	  category      VARCHAR2(255),         "
								+"	  version       INTEGER,               "
								+"	  create_by     VARCHAR2(64),          "
								+"	  modify_by     VARCHAR2(64),          "
								+"	  create_time   timestamp,          "
								+"	  modify_time   timestamp,          "
								+"	  deploy_state  INTEGER,               "
								+"	  version_state INTEGER,               "
								+"	  deployment_id VARCHAR2(255),         "
								+"	  content_bytes longvarbinary,                  "
								+"	  init_num      INTEGER,               "
								+"	  canvaswidth   VARCHAR2(32),          "
								+"	  canvasheight  VARCHAR2(32),          "
								+"	  table_ids     VARCHAR2(255),         "
								+"	  table_names   VARCHAR2(255)          "
								+"	)"); 
    	
    	createStatement.execute("create table ACT_HQ_TASK_EXTEND("
								+"  id                    VARCHAR2(32) not null,   "
								+"  process_definition_id VARCHAR2(255),           "
								+"  task_key              VARCHAR2(255),           "
								+"  is_create_sub_task    VARCHAR2(64),            "
								+"  custom_button         VARCHAR2(1000),          "
								+"  is_to_be_received     NUMBER(10),              "
								+"  is_to_be_revoked      NUMBER(10),              "
								+"  is_to_be_returned     NUMBER(10),              "
								+"  task_type             VARCHAR2(32),            "
								+"  processor             CLOB,                    "
								+"  processor_expression  VARCHAR2(500),           "
								+"  show_type             VARCHAR2(32),            "
								+"  is_to_all_user        NUMBER(10),              "
								+"  is_to_be_freedom_node NUMBER(10),              "
								+"  is_enabled_all_user   NUMBER(10),              "
								+"  candidate_group_      NVARCHAR2(2000),         "
								+"  is_default_user       NUMBER(10)               "
								+")");   
    	createStatement.close();  
          conn.close();  
    	
    }
    public static void copyDataFromOrcl4H2(DataSource orclDatasource, DataSource h2DataSource) throws SQLException, UnsupportedEncodingException{
    	Connection connOrcl = orclDatasource.getConnection();
    	Connection connH2 = h2DataSource.getConnection();
    	PreparedStatement orclps;
    	PreparedStatement h2ps;
    	String[] tableName = new String[]{"ACT_GE_BYTEARRAY","ACT_HQ_TEM_DEF","ACT_HQ_TASK_EXTEND"};
    	for(String str:tableName){//TODO
    		String sql = "SELECT * FROM "+str;
    		String h2Sql ="";
    		if("ACT_GE_BYTEARRAY".equals(str)){
    			h2Sql= ACT_GE_BYTEARRAY_SQL;
    		}
    		if("ACT_HQ_TEM_DEF".equals(str)){
    			h2Sql= ACT_HQ_TEM_DEF_SQL;
    		}
    		if("ACT_HQ_TASK_EXTEND".equals(str)){
    			h2Sql= ACT_HQ_TASK_EXTEND_SQL;
    		}
        	orclps = connOrcl.prepareStatement(sql);
        	 h2ps = connH2.prepareStatement(h2Sql);  
        	ResultSet orclRs = orclps.executeQuery();
            ResultSetMetaData rsmd = orclRs.getMetaData();   
            int columnCount = rsmd.getColumnCount(); 
            while (orclRs.next()){ 
            	System.out.println(orclRs.getString(1));
                for (int i=1; i<=columnCount; i++){
               	 if(!"BLOB".equals(rsmd.getColumnTypeName(i))){
               		 h2ps.setString(i, orclRs.getString(i)); 
               	 }
                    //设置Blob  
               	 if("BLOB".equals(rsmd.getColumnTypeName(i))){
//               		 System.out.println("进入blob");
               		 Blob blob = orclRs.getBlob(i);
               		 h2ps.setBlob(i, blob);
               	      String content = new String(blob.getBytes((long)1, (int)blob.length()),"utf8");            
               	      System.out.println("orcle---"+tableName+"---------"+content);
               		 continue;
               	 }
                
                }   
                h2ps.executeUpdate(); 
                
                int i = 1;  
                Statement createStatement = connH2.createStatement();
                ResultSet result = createStatement.executeQuery("select content_bytes from ACT_HQ_TEM_DEF where id='3EBA01302BC441FC86B60FA473E192DF' ");  
                while (result.next()) {  
               	 Blob blob = result.getBlob("content_bytes");
                    String content = new String(blob.getBytes((long)1, (int)blob.length()),"utf8");            
          	      System.out.println("ACT_HQ_TEM_DEF==========================="+content);
                }  
                Statement createStatement2 = connH2.createStatement();
                ResultSet result2 = createStatement2.executeQuery("select BYTES_ from ACT_GE_BYTEARRAY where ID_='4002' ");  
                while (result2.next()) {  
                	Blob blob = result2.getBlob("BYTES_");
                	String content = new String(blob.getBytes((long)1, (int)blob.length()),"utf8");            
                	System.out.println("ACT_GE_BYTEARRAY============HHHH2322==============="+content);
                }  
                result.close();
            }
            orclRs.close();
            orclps.close();
            h2ps.close();   
    	}//TODO
    	connOrcl.close();
    	connH2.close();
         
    }
    public void dropTable(Statement stat) throws SQLException{
    	 stat.execute("DROP TABLE TEST");  
//    	 stat.execute("drop table ACT_HQ_TEM_DEF");  
    	
    	
    }
}  