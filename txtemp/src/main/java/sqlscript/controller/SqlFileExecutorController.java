package sqlscript.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.hq.bpmn.common.bean.ProcessResult;
//import com.hq.bpmn.common.util.JsonUtil;
//import com.hq.bpmn.common.util.WfBeanFactory;
//import com.hq.bpmn.exception.BpmnException;
//import com.hq.bpmn.h2.service.impl.BpmnTempLateDefH2Service;

 
/**
 * 读取 SQL 脚本并执行
 * @author Unmi
 */
@Controller
@RequestMapping("/**/initSqlScript")
public class SqlFileExecutorController {
	private static final Logger logger = LoggerFactory.getLogger(SqlFileExecutorController.class);
    
	/**
	 * 初始化脚本
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/initSqlScriptSource")     
	public void initSqlScriptSource(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		URL resource = this.getClass().getResource("");
		String dataPath = resource.getPath().substring(0, resource.getPath().lastIndexOf("/",resource.getPath().length()-2))+"/";
		List<String> sqlFilePath  = new ArrayList<String>();
		try{
			getFiles(dataPath,sqlFilePath);
			sortFiles(sqlFilePath);
			for(String s:sqlFilePath){
				if(logger.isInfoEnabled()){
					logger.info("执行脚本文件：{} =========== ",s);
				}
				String sqlSource = null;
				try {
						sqlSource = loadSqlSource(s);
						executeSource(sqlSource);
					} catch (Exception e) {
						logger.error("执行脚本文件出错：{} =========== ",s);
						e.printStackTrace();
					}
			}
			
		}catch(Exception e){
			System.out.println("解析脚本文件路径出错");
			e.printStackTrace();
		}
		
		
//		ProcessResult<String> pr = new ProcessResult<String>();
//		pr.setSuccess(true);
//		pr.setMessage("升级成功！");
//		JsonUtil.outPutPRToJSON(pr, response);
	}
	public void sortFiles(List<String> sqlFilePath) throws Exception{
		
		List<String> files = new ArrayList<String>();
		int setValueCount = 0; 
		// 从数组第二个元素开始排序，因为第一个元素本身肯定是已经排好序的 
		  for (int j = 1; j < sqlFilePath.size(); j++) {// 复杂度 n 
		   // 保存当前值 
			  String key = sqlFilePath.get(j);
//			  int key = Integer.parseInt(sqlFilePath.get(j).split("_")[sqlFilePath.get(j).split("_").length-1]);
		   // ∆ 利用二分查找定位插入位置 
		//   int index = binarySearchAsc(ary, ary[j], 0, j - 1);// 复杂度：O(logn) 
		   int index = binarySearchAsc(sqlFilePath, sqlFilePath.get(j), 0, j - 1);// 复杂度：O(logn) 
		   System.out.println("第" + j +"个索引上的元素"+key+"要插入的位置是：" + index); 
		   // 将目标插入位置，同时右移目标位置右边的元素 
		   for (int i = j; i > index; i--) { 
		    sqlFilePath.set(i, sqlFilePath.get(i-1)) ; 
		    setValueCount++; 
		   } 
		   sqlFilePath.set(index, key) ; 
		   setValueCount++; 
		  } 
		  System.out.println("\n 设值次数(setValueCount)=====> " + setValueCount); 
		// TODO Auto-generated method stub
	}
	/** 
	  * 二分查找 升序 递归 
	  * 
	  * @param sqlFilePath 
	  *   给定已排序的待查数组 
	  *
	  *   查找目标 
	  * @param from 
	  *   当前查找的范围起点 
	  * @param to 
	  *   当前查找的返回终点 
	  * @return 返回目标在数组中，按顺序应在的位置 
	  */ 
	 private static int binarySearchAsc(List<String> sqlFilePath, String target, int from, int to) { 
	  int range = to - from; 
	  // 如果范围大于0，即存在两个以上的元素，则继续拆分 
	  if (range > 0) { 
	   // 选定中间位 
	   int mid = (to + from) / 2; 
		   if (Integer.parseInt(sqlFilePath.get(mid).split("_")[sqlFilePath.get(mid).split("_").length-1].replace(".sql", ""))> 
		   Integer.parseInt(target.split("_")[target.split("_").length-1].replace(".sql", ""))) { 
	    return binarySearchAsc(sqlFilePath, target, from, mid - 1); 
	   } else { 
	    return binarySearchAsc(sqlFilePath, target, mid + 1, to); 
	   } 
	  } else { 
		  if (Integer.parseInt(sqlFilePath.get(from).split("_")[sqlFilePath.get(from).split("_").length-1].replace(".sql", ""))> 
		   Integer.parseInt(target.split("_")[target.split("_").length-1].replace(".sql", ""))) { 
	    return from; 
	   } else { 
	    return from + 1; 
	   } 
	  } 
	 } 
	private void executeSource(String sqlSource) throws Exception {
//		 DataSource  source = (DataSource) WfBeanFactory.getBean("busiDataSource");
//		 Connection conn = null;
//		 Statement stmt = null;
//		 try {
//			 conn=source.getConnection();
//				conn.setAutoCommit(false);
//				stmt = conn.createStatement();
//					stmt.execute(sqlSource);
//				conn.commit();
//			} catch (Exception ex) {
//				conn.rollback();
//				throw ex;
//			} finally {
//				conn.close();
//				stmt.close();
//			}
	}
	private String loadSqlSource(String sqlFile) throws Exception {
		InputStream sqlFileIn = null;
		 
		try {
			sqlFileIn=new FileInputStream(sqlFile);
 
			StringBuffer sqlSb = new StringBuffer();
			byte[] buff = new byte[1024];
			int byteRead = 0;
			while ((byteRead = sqlFileIn.read(buff)) != -1) {
				sqlSb.append(new String(buff, 0, byteRead));
			}
			String sqlArr = sqlSb.toString();
				String source = sqlArr.replaceAll("--.*", "").trim();
			return source;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}finally{
			sqlFileIn.close();
		}
	}
	private  void getFiles(String path ,List <String> sqlFilePath) {
	        File file = new File(path);
	        // 如果这个路径是文件夹
	        if (file.isDirectory()) {
	            // 获取路径下的所有文件
	            File[] files = file.listFiles();
	            for (int i = 0; i < files.length; i++) {
	                // 如果还是文件夹 递归获取里面的文件 文件夹
	                if (files[i].isDirectory()) {
	                    System.out.println("目录：" + files[i].getPath());
	                    getFiles(files[i].getPath(), sqlFilePath);
	                } else {
	                    System.out.println("文件：" + files[i].getPath());
	                    if(files[i].getPath().endsWith(".sql")){
	                    	sqlFilePath.add(files[i].getPath());
	                    }
	                }
	 
	            }
	        } else {
	            System.out.println("文件：" + file.getPath());
	            if(file.getPath().endsWith(".sql")){
                	sqlFilePath.add(file.getPath());
                }
	        }
	    }
}