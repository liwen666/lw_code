package commons.execscript.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.tjhq.commons.execscript.common.Constants;

public class FileXmlUtil {
    final static String basePath=Constants.USERDIR + Constants.SCRIPT_SRCPATH;
	
	public static void main(String[] args) throws Exception {		
		//获得系统版本号
		String[] versions=readPropertiesInfo();
		createXmlInfo(versions[0],versions[1]);
	}
	
	
	
	
	/** 读取xml文件
     * @param xmlFilePath xml文件路径 
     * @return Document对象 
	 * @throws IOException 
     */  
    public static Document parse2Document(String xmlFilePath) throws IOException{  
        SAXReader reader = new SAXReader();  
        Document document = null;  
        File f = null;  
        InputStream in=null;
        try {  
            f = new File(xmlFilePath);  
            in = new FileInputStream(f);  
            document = reader.read(in);  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
            System.out.println("读取文件发生异常，请检查path和文件名是否存在！");  
            e.printStackTrace();  
        }  finally{
        	if(in!=null){in.close();}
        }
        return document;  
    }  
    /***
	 * 获得资源文件中的版本号
	 * @return
	 * @throws IOException 
	 */
	private static String[] readPropertiesInfo() throws IOException{
		 Properties prop =  new  Properties();  
		 String[] reversion=new String[3];
		 InputStream in =null;
		 try {
		    in = new BufferedInputStream (new FileInputStream(Constants.USERDIR+"/src/application.properties"));  		
			prop.load(in);
			 String sysVersion = prop.getProperty( "system.version" ).trim();   
			 String comVersion = prop.getProperty( "common.version" ).trim();
			 String appid = prop.getProperty( "system.appid" ).trim();
			 reversion[0]=sysVersion;
			 reversion[1]=comVersion;
			 reversion[2]=appid;
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in!=null){in.close();}
		}
		 return reversion;
	}
	
	/**
	 * 比较版本号
	 * @param currVersion 当前版本号
	 * @param sysVersion  系统版本号
	 * @return greater:大于 equal:等于 less:小于
	 */
	private static String compileVersion(String currVersion,String sysVersion){
		String rs="";
		if(currVersion!=null&&!"".equals(currVersion)&&sysVersion!=null&&!"".equals(sysVersion)){
			String currstr=currVersion.replace("V", "");
			String sysstr=sysVersion.replace("V", "");
			String[] currs=currstr.split("\\.");
			String[] syss=sysstr.split("\\.");
			if(currs.length==syss.length){
				for(int i=0;i<currs.length;i++){
					if(Integer.parseInt(currs[i])>Integer.parseInt(syss[i])){
						rs="greater";
						break;
					}else if(Integer.parseInt(currs[i])==Integer.parseInt(syss[i])){
						rs="equal"; 
					}else{
						rs="less";
						break;
					}
				}				
			}else{
				rs="版本号格式不一致";
			}			
		}else{
			rs="版本号不能为空";
		}
		return rs;
	}
	 /***
	   * 获取最大编号
	   * @param key
	   * @param scriptFilePath
	   * @param divName
	   * @param codeMap
	   * @return
	   */
		private static Map<String, Integer> getCodeMap(String key, String scriptFilePath, String divName, Map<String, Integer> codeMap) {
			final File f = new File(scriptFilePath);
			
			File[] files = null;
			if (Constants.SCRIPT_DIVNAME.equals(divName) 
					|| Constants.SCRIPT_BGTPATH.equals(divName) 
					|| Constants.SCRIPT_SPFPATH.equals(divName)
					|| Constants.SCRIPT_BASPATH.equals(divName) 
					|| Constants.SCRIPT_KPIPATH.equals(divName)
					
			) {
				
				files = f.listFiles();
			} 
			else {
				files = f.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File dir, String name) {
						String fileName=dir.getName();
						if(fileName.equals("old")){
							fileName="busi";
						}
						return name.startsWith(fileName + "_");
					}
				});
			
			}
		
			for (File file : files) {
				if (file.isDirectory()) {
					String subKey = "";
					if("".equals(key)){//遍历的是sql文件夹
						subKey = file.getName();
					}else{//通用或业务系统文件夹
						subKey = key+"_"+file.getName();
					}
					
					getCodeMap(subKey, file.getAbsolutePath(), file.getName(), codeMap);
				} else {
		 			if(file.getName().equals(Constants.SCRIPTVARSIONDATA_FILENAME)){
		 				continue;
		 			}				
					String scriptCode = file.getName().split("_")[1];
					int maxCode = codeMap.get(key) == null ? 0 : codeMap.get(key);
					if (maxCode == 0 || maxCode < Integer.parseInt(scriptCode)) {
						codeMap.put(key, Integer.parseInt(scriptCode));
					}
				}
			}
			return codeMap;
		}
		/***
		 * 写数据到xml文件中
		 * @param path文件路径
		 * @param document文档内容
		 * @throws IOException
		 */
		private static void writeXml(String path, Document document) throws IOException{		
	        XMLWriter output = null;
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        output = new XMLWriter(new FileWriter(new File(path)),format);
	        output.write(document);
	        output.close();     
		}
		/***
		 * 取所有的版本号
		 * @param document
		 * @return  内容格式为"common_V2.0.0.1" "app_V2.0.0.1"
		 */
		@SuppressWarnings("rawtypes")
		private static List<String> getAllVersion(Document document){
			Element root = document.getRootElement();  
			List<String> list=new ArrayList<String>();
	        for(Iterator i_first=root.elementIterator();i_first.hasNext();){
	        	 Element e_first=(Element)i_first.next();
	        	//取版本号属性
	        	 String sysversion=e_first.attributeValue("sysversion").trim();
	        	 String apptype=e_first.attributeValue("apptype").trim();
	        	 list.add(apptype+"_"+sysversion); 
	        }
	        return list;
		}
		/***
		 * 取第几个版本号   applist.size必须大于1
		 * @param arr 版本号的数组
		 * @param xh  序号(倒数)
		 * @param appId
		 */
		private static String getVersionByOper(List<String> list,int xh,String appId){	
			List<String> appList=new ArrayList<String>();
					
			if(list!=null&&list.size()>1){
				for(int k=0;k<list.size();k++){
					String va=list.get(k);
					String[] vas=va.split("_");
					if(vas[0].equals(appId)){
					  appList.add(va);
					}
				}
				if(appList!=null&&appList.size()>1){
					String[] arr=new String[appList.size()];
					for(int k=0;k<appList.size();k++){
						String va=appList.get(k);
						String[] vas=va.split("_");
						if(vas[0].equals(appId)){
						  arr[k]=vas[1];
						}
					}
					 for(int i = 0 ; i < arr.length-1 ; i++){
						   for(int j = i+1 ; j < arr.length ; j++){
						    String temp ;
						    if(compileVersion(arr[i],arr[j]).equals("greater")){//大于
							     temp = arr[j];
							     arr[j] = arr[i];
							     arr[i] = temp;
						    }
						   }
						 }
					 System.out.println(arr.length);
					 if(arr.length>=xh){
					     return arr[arr.length-xh];
					  }else{
						  return "序号过大,请重新输入";
					  }
				}else{
					  return "applist的长度最少为2";
				  }				 
		  }else{
			  return "list的长度最少为2";
		  }
		}
		
		/***
		 * 获得最大版本信息
		 * @param list  内容格式为"common_V2.0.0.1"
		 * @param appId
		 * @return
		 */
		public static String getMaxVersion(List<String> list,String appId){
			String maxversion="V2.0.0.0";
			for(String rs:list){
				String[] vas=rs.split("_");
				if(vas[0].equals(appId)){
					String flag=compileVersion(vas[1], maxversion);
					if(flag.equals("greater")){
						maxversion=vas[1];
					}
				}				
			}
			return maxversion;
		}
		/***
		 * 获得指定版本recordinterval的信息
		 * @param version  指定版本
		 * @param packname 包名称  格式bgt.common
		 * @param document xml的document信息
		 * @param appId 
		 */
		@SuppressWarnings("rawtypes")
		private static String getLastRecordinterval(String version,String packname,Document document,String appId){
			Element root = document.getRootElement();  
			String rs="0_0";
	        for(Iterator i_first=root.elementIterator();i_first.hasNext();){
	        	 Element e_first=(Element)i_first.next();
	        	//取版本号属性
	        	 String sysversion=e_first.attributeValue("sysversion").trim();
	        	 String appType=e_first.attributeValue("apptype").trim();
	        	 if(sysversion.equals(version)&&appType.equals(appId)){
	        		 for (Iterator i_second = e_first.elementIterator(); i_second.hasNext();){
	              		  Element e_second=(Element)i_second.next(); 
	              		  String str_packagename=e_second.element("packagename").getText().trim();         		     
	          		      if(str_packagename.equals(packname)){
	          		    	 rs=e_second.element("recordinterval").getText().trim();
	          		    	break;  
	          		      }      		      
	               	 } 
	        		 break;
	        	 }        	 
	        }
	        return rs;
		}
		/***
		 * 是否加入
		 * @param pakName
		 * @param type
		 * @return
		 */
		private static boolean isAddToDocument(String pakName,String type){
			boolean flag=false;
			   if(type.equals("app")){
				if(pakName.indexOf(Constants.SCRIPT_BGTPATH)>-1||pakName.indexOf(Constants.SCRIPT_SPFPATH)>-1||pakName.indexOf(Constants.SCRIPT_BASPATH)>-1||pakName.indexOf(Constants.SCRIPT_KPIPATH)>-1){
					flag=true;
				}else{
					flag=false;
				}
			}else{
				if(pakName.indexOf(Constants.SCRIPT_BGTPATH)>-1||pakName.indexOf(Constants.SCRIPT_SPFPATH)>-1||pakName.indexOf(Constants.SCRIPT_BASPATH)>-1||pakName.indexOf(Constants.SCRIPT_KPIPATH)>-1){
					flag=false;
				}else{
					flag=true;
				}
			}
			
			return flag;
		}
	/***
	 *  创建xml文档
	 * @param sysversion系统版本号
	 * @param comversion通用版本号
	 * @param appid 业务id
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes"})
	private static void createXmlInfo(String sysversion,String comversion) throws IOException{
		 String path=basePath+"ScriptVersionData.xml";		
		 Document document = parse2Document(path);//取到xml文档内容; 
		 Document documentSearch=document;
		 String compileSysversion="";//用于比较的系统版本号
		 
		 List<String> listallversion= getAllVersion(documentSearch);//所有的系统版本号
		 
		  Map<String,String> editMap=new HashMap<String,String>();
		 //获得最大编号
		 Map<String, Integer> codeMap = getCodeMap("", basePath, Constants.SCRIPT_DIVNAME, new HashMap<String, Integer>());   
		 Iterator iter = codeMap.entrySet().iterator();
		 while (iter.hasNext()) {
	        	Entry entry = (Entry) iter.next();
	        	String key = ((String)entry.getKey()).replace("_", ".");//pakName
	        	Integer val = (Integer)entry.getValue();
	        	editMap.put(key, val.toString());  
     	 }
		 //获取文档的根元素  
         Element root = document.getRootElement();  
       //遍历当前元素（根元素）的元素(scriptdata)
         for(Iterator i_first=root.elementIterator();i_first.hasNext();){//for循环主要做:1.删除xml中比系统版本高的数据;2.修改当前系统版本的数据
        	 Element e_first=(Element)i_first.next();
        	 //获取xml中版本号属性
        	 String currversion=e_first.attributeValue("sysversion").trim();//当前版本号
        	 String apptype=e_first.attributeValue("apptype").trim();//当前业务类型
        	 
        	 if(apptype.equals("common")){//通用版本,设置用于比较版本号为通用版本号
        		 compileSysversion= comversion; 		 
        	 }else if(apptype.equals("app")){//业务系统,置用于比较版本号为业务版本号
        		 compileSysversion=sysversion;
        	 }
        	//删除xml中版本号大的记录
        	  if(compileVersion(currversion, compileSysversion).equals("greater")){
             	 root.remove(e_first);
              }else if(compileVersion(currversion, compileSysversion).equals("equal")){//等于当前版本的时候，修改信息
            	  String secondVersion="";
          		  if(listallversion.size()>1){         			
          			secondVersion=getVersionByOper(listallversion,2,apptype).trim();
          		  }
          		//获得当前版本下的所有节点
         		 for (Iterator i_second = e_first.elementIterator(); i_second.hasNext();){
            		  Element e_second=(Element)i_second.next();                 		 
            		  String str_packagename=e_second.element("packagename").getText().trim();
            		  String rsv=getLastRecordinterval(secondVersion,str_packagename,documentSearch,apptype);
            		  if(editMap.get(str_packagename)!=null){//存在的情况下进行修改
                			 if(Integer.parseInt(editMap.get(str_packagename))>=Integer.parseInt(rsv.split("_")[1])+1){
                				 e_second.element("recordinterval").setText((Integer.parseInt(rsv.split("_")[1])+1)+"_"+editMap.get(str_packagename));
                			 }else{
                				 e_first.remove(e_second) ; 
                			 }    			
                			 editMap.remove(str_packagename);//editMap中保存没有进行操作过的数据
                		  }else{//不存在的情况下进行删除
                			 e_first.remove(e_second) ; 
                		  }               		     	
            		  
         		 }
         		Iterator iteredit = editMap.entrySet().iterator();
         		while (iteredit.hasNext()) {                 		 
     	        	Entry entry = (Entry) iteredit.next();
     	        	String key = ((String)entry.getKey()); 
     	        	String val = (String)entry.getValue(); 
     	        	if(isAddToDocument(key,apptype)){
	     	        	String rsv=getLastRecordinterval(secondVersion,key,documentSearch,apptype);
	     	        	System.out.println(rsv+"add");
	     	            int rsvs=(Integer.parseInt(rsv.split("_")[1])+1);
	     	            if(Integer.parseInt(val)>=rsvs){
	     	        	  Element eidtPackageElement=e_first.addElement("package");
	     	        	  eidtPackageElement.addElement("packagename").addText(key);  
	     	        	  eidtPackageElement.addElement("recordinterval").addText(rsvs+"_"+val);
	     	            }
     	        	}
         		}
            	//相等修改结束，当前版本比系统版本小的时候，不进行操作
              }       	 
        	//遍历当前元素结束 
         }
		 
         String maxAppVersion=getMaxVersion(listallversion,"app");//最大版本号  根据业务 获得最大版本号
         String maxComVersion=getMaxVersion(listallversion,"common");//最大版本号  根据业务 获得最大版本号
         //如果当前最大版本小于系统版本,新增信息(由于历史记录都小于当前版本，故要取到最大版本号，所以新增的要放在for循环外)
         if(compileVersion(maxComVersion,comversion).equals("less")){//处理common
        	root= addNewElement(root, comversion, "common", codeMap, documentSearch, maxComVersion);
         }
         if(compileVersion(maxAppVersion,sysversion).equals("less")){//处理app
        	 root=  addNewElement(root, sysversion, "app", codeMap, documentSearch, maxAppVersion);
         }
		 
       //写入到xml文件
         writeXml(path,document);
	}
	/***
	 * 在根节点增加信息
	 * @param root
	 * @param sysversion
	 * @param type
	 * @param codeMap
	 * @param document
	 * @param maxComVersion
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	private static Element addNewElement(Element root,String sysversion,String type, Map<String, Integer> codeMap, Document document,String maxComVersion){
		 Element newElement = root.addElement("scriptrecord");  
		 newElement.addAttribute("sysversion", sysversion);
		 newElement.addAttribute("apptype", type); 
		 Iterator itercom = codeMap.entrySet().iterator();
		 while (itercom.hasNext()) {
			 Entry entry = (Entry) itercom.next();
	         String pakName = ((String)entry.getKey()).replace("_", ".");
	         Integer val = (Integer)entry.getValue();
	     	if(isAddToDocument(pakName,type)){
	     		 String rsv=getLastRecordinterval(maxComVersion,pakName,document,type);
	     		int rsvs=(Integer.parseInt(rsv.split("_")[1])+1);
	     		if(val>=rsvs){
		            Element newPackageElement=newElement.addElement("package");
		            newPackageElement.addElement("packagename").addText(pakName);		           
		            newPackageElement.addElement("recordinterval").addText(rsvs+"_"+val);  
	        	}
	     	}
		 }
		 return root;
	}
}
