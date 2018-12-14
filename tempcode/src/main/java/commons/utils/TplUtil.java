package commons.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;

import org.json.JSONObject;

public class TplUtil {
	private static final String USERDIR = System.getProperty("user.dir"); 
	private static final String BASEPATH = USERDIR + "/efmisweb";
	private static final String FilePath = "/efmisweb/static/pub2.0/js/conf/Hq.Modules.js"; 
	private static final String ThemesPath = "/efmisweb/static/pub2.0/themes/";
	private static final String HqPluginPath = "/efmisweb/static/pub2.0/js/plugin/";
	private static final String HqUxGridPath = "/efmisweb/static/pub2.0/js/ux/grid/";
	private static final String WEBROOT= "/efmisweb/";
	private static final String StyleFlag = "<%=style%>";
	private static final String MyPluginFlag = "static/pub2.0/js/plugin";
	private static final String HqUxGridFlag = "static/pub2.0/js/ux/grid";
	
	private static final String TplAllFileName = "hq_all.tpl";
	private static final String HqPluginAll_FileName = "Hq.plugin.all.js";
	private static final String HqUxGridAll_FileName = "Hq.ux.grid.all.js";

	private static final String ModulesJsonHead = "Hq.modules ="; 
	private static final String ModulesJsonEd = ";"; 
	private static final String PathMapKey = "path";
	private static final String JsMapKey = "js";
	private static final String JsEnd = ".js";
	private static final String GzFlag = ".gz";
	private static final String GzJsEnd = ".gzjs";
	private static final String TplMapKey = "js";
	private static final String TplEnd = ".tpl";
	private static final String TplHead = "static/pub2.0/themes";
	
	private static List<String> styleList = new ArrayList<String>();
	private static List<String> jsEnds = new ArrayList<String>();
	
	static{
		styleList.add("deepblue");
		styleList.add("default");
		styleList.add("flatblue");
		styleList.add("normal");
		
		jsEnds.add(JsEnd);
		jsEnds.add(GzJsEnd);
	}
	public static void main(String[] args) throws Exception {
	    execTPLUtil();
	}
    public static void execTPLUtil()  throws Exception {
        File file = new File(USERDIR + FilePath);//Hq.Modules.js
        writeAllJsFile();//hq_all.js
        //write_artTmp_lext_coreJs();//artTmp_ltext_core_all.js
        //write_artTmp_lext_coreCss();//artTmp_ltext_core_all.css
        writeAllTplFile(file,TplMapKey, TplEnd);//打包所有tpl文件
        writeAllCustomJsFile(file,JsMapKey, jsEnds ,MyPluginFlag ,HqPluginPath, HqPluginAll_FileName);//打包所有自定义插件:static/pub2.0/js/plugin
        writeAllCustomJsFile(file,JsMapKey, jsEnds ,HqUxGridFlag ,HqUxGridPath, HqUxGridAll_FileName);//打包所有表格公示插件:efmisweb/static/pub2.0/js/ux/grid
        System.out.println("----------TPLUtil执行完成！-----------------------------------------------------------------");
    }
    private static void write_artTmp_lext_coreCss() {
        String cssFileName = "/static/pub2.0/js/core/artTmp_ltext_core_all.css";
        Object[] fileArry = {
                "static/jquery-ui/themes/jquery-ui.min.css",
                "static/ext.lt/calendar.css",
                "static/pub2.0/themes/normal/platform/default.css",
                "static/pub2.0/themes/normal/platform/css/master.css",
                "static/jquery-plugin/ztree/css/zTreeStyle/normal/zTreeStyle.css",
                "static/jquery-plugin/ztree/showloading/showLoading.css"};
        TplUtil.writeJsArrayFiles(fileArry,cssFileName);
    }
    private static void write_artTmp_lext_coreJs() {
        String jsFileName = "/static/pub2.0/js/core/artTmp_ltext_core_all.js";
        Object[] fileArry = {
                "static/artTemplate/template.js",
                "static/jquery-plugin/My97DatePicker/WdatePicker.js",
                "static/ext.lt/js/editpanel.js",
                "static/ext.lt/js/formulas.js",
                "static/ext.lt/js/calendar.js",
                "static/jquery-plugin/ztree/js/jquery.ztree.core-3.5.js",
                "static/jquery-plugin/ztree/js/jquery.ztree.excheck-3.5.js",
                "static/jquery-plugin/ztree/js/jquery.ztree.exedit-3.5.js",
                "static/jquery-plugin/ztree/showloading/jquery.showLoading.js",
                "static/json/json2.js",
                "static/pub2.0/js/util/Hq.Logger.js",
                "static/pub2.0/js/Hq.ComponentManager.js",
                "static/pub2.0/js/Hq.Component.js",
                "static/pub2.0/js/panel/Hq.Panel.js",
                "static/pub2.0/js/panel/Hq.Panel.PanelEvent.js",
                "static/pub2.0/js/util/Hq.Tools.js",
                "static/pub2.0/js/plugin/jquery.mousewheel.js",
                "static/jquery-plugin/blockUI/jquery.blockUI.js",
                "static/pub2.0/js/MessageBox/Hq.MessageBox.js",
                "static/pub2.0/js/plugin/jquery.Drag.js",
                "static/pub2.0/js/event/Hq.BaseEvent.js",
                "static/pub2.0/js/event/Hq.EventManager.js",
                "static/jquery-ui/jquery-ui.min.js",
                "static/jquery-plugin/fileupload/js/jquery.fileupload.js",
                "static/jquery-plugin/fileupload/js/jquery.iframe-transport.js",
                "static/pub2.0/js/applyScope/Hq.spf.ApplyScope.js",
                 "static/pub2.0/js/applyScope/Hq.detailPage.js"};
        TplUtil.writeJsArrayFiles(fileArry,jsFileName);
    }
    /**
	 * 打包pluginPath下所有符合mypluginflag的文件
	 * @param file 登记所有js的Hq.Modules.js文件
	 * @param jsmapkey Hq.Modules.js中Hq.modules Object 中的 所有key为"js"的
	 * @param ends	jsmapkey对应的值中，以ends结尾的文件
	 * @param mypluginflag	文件路径名包含的特殊标志
	 * @param pluginPath 文件路径
	 * @param allFileName 生成的all.js文件的名字
	 * @throws Exception
	 */
	private static void writeAllCustomJsFile(File file, String jsmapkey, List<String> ends, String mypluginflag , String pluginPath, String allFileName) throws Exception {
		List<String> pluginJsFilePathList = getMyPluginJsFile(file, jsmapkey, ends, mypluginflag);

		//新建all文件
		File allFile = new File(USERDIR+pluginPath+allFileName);
		allFile.delete();
		allFile.createNewFile();
		
		for (String filePath : pluginJsFilePathList) {
			//读每个tpl文件内容
			String fileContent = "";
			try {
				fileContent = readFile(new File(USERDIR+WEBROOT+filePath));
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			//写入all文件
			writeFile(allFile, fileContent, true);
		}
		System.out.println("文件生成完成："+pluginPath+allFileName);
	}
	//获取modulesJs文件中所有自定义插件路径
	private static List<String> getMyPluginJsFile(File file, String jsmapkey, List<String> ends, String mypluginflag) throws Exception {
		List<String> jsFilePathList = new ArrayList<String>();
		List<String> pluginJsFilePathList = new ArrayList<String>();
		for (String filenameExtension : ends) {
			jsFilePathList.addAll(formatModulesJsFile(file,jsmapkey, filenameExtension));//读取js数组中的js文件路径
		}
		for (String jsPath : jsFilePathList) {
			if(jsPath.contains(mypluginflag)){
				pluginJsFilePathList.add(jsPath);
				System.out.println(jsPath);
			}
		}
		System.out.println("共"+pluginJsFilePathList.size()+"个自定义插件"+ends+"文件！");
		return pluginJsFilePathList;
	}
	//生成all.tpl文件
	private static void writeAllTplFile(File file, String tplmapkey, String tplend) throws Exception {
		List<String> tplFilePathList = formatModulesJsFile(file,tplmapkey, tplend);//读取js数组中的tpl文件路径
		//List<String> tplFilePathList2 = readModulesJsFile(file);

		if(tplFilePathList!=null && tplFilePathList.size()>0){
			for (String styleName : styleList) {//循环各种style
				//新建all文件
				File allTplFile = new File(USERDIR+ThemesPath+styleName+"/"+TplAllFileName);
				allTplFile.delete();
				allTplFile.createNewFile();
				
				for (String tplFilePath : tplFilePathList) {
					//替换style路径
					if(tplFilePath.indexOf(StyleFlag)>0){
						tplFilePath = tplFilePath.replaceAll(StyleFlag, styleName);
					}
					//读每个tpl文件内容
					String tplFileContent = "";
					try {
						tplFileContent = readFile(new File(USERDIR+WEBROOT+tplFilePath));
					} catch (Exception e) {
						System.err.println(styleName+"："+e.getMessage());
					}
					//写入all文件
					writeFile(allTplFile, tplFileContent, true);
				}
				System.out.println("all.tpl文件生成完成："+USERDIR+ThemesPath+styleName+"/"+TplAllFileName);
			}
		}
	}
	//按行读modules.js文件，未处理注释
	@SuppressWarnings("unused")
    @Deprecated
	private static List<String> readModulesJsFile(File file) throws Exception {
		List<String> tplFilePathList = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"utf-8");
		BufferedReader in = new BufferedReader(isr);
		String line = null;
		while ((line = in.readLine()) != null) {
			if(line.indexOf(TplEnd)>0 && line.indexOf(TplHead)>0){
				int headIdx = line.indexOf(TplHead);
				int endIdx = line.indexOf(TplEnd);
				tplFilePathList.add(line.substring(headIdx, endIdx+TplEnd.length()));
			}
		}
		if(fis !=null)fis.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		return tplFilePathList;
	}
	//按json对象方式解析modules.js文件
	private static List<String> formatModulesJsFile(File file, String typeKey, String filenameExtension) throws Exception{
		List<String> filePathList = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"utf-8");
		BufferedReader in = new BufferedReader(isr);
		StringBuffer  modulesJsBf = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			line = line.trim();
			if(line.contains("//")){
				line = line.substring(0,line.indexOf("//")).trim();//去掉行注释
			}
			if(!line.isEmpty()){
				modulesJsBf.append(line);
			}
		}
		String modulesJs = modulesJsBf.toString();
		while (modulesJs.indexOf("/*") > 0) {//去掉块注释
			String commend = modulesJs.substring(modulesJs.indexOf("/*"), modulesJs.indexOf("*/", modulesJs.indexOf("/*")) + 2);
			modulesJs = modulesJs.replace(commend, "");
		}
		
		modulesJs = modulesJsBf.substring(modulesJsBf.indexOf(ModulesJsonHead),modulesJsBf.indexOf(ModulesJsonEd, modulesJsBf.indexOf(ModulesJsonHead)));
		modulesJs = modulesJs.substring(modulesJs.indexOf("=")+1).trim();
		
		JSONObject modulesJsonObj = new JSONObject(modulesJs);
        @SuppressWarnings("rawtypes")
		Iterator it = modulesJsonObj.keys();  
        while (it.hasNext()) {  
            String key = (String) it.next();  
            String value = modulesJsonObj.getString(key);  
    		JSONObject modulesObj = new JSONObject(value);
    		if(modulesObj.has(typeKey)){
    			String pathHead = modulesObj.optString(PathMapKey)==null?"":modulesObj.optString(PathMapKey);
    			JSONArray pathArray = modulesObj.optJSONArray(typeKey);
    			if(pathArray==null){
    				String pathStr = modulesObj.optString(typeKey);
    				if(pathStr!=null && !pathStr.isEmpty()){
    					pathArray = new JSONArray();
    					pathArray.put(pathStr);
    				}
    			}
    			for (int i = 0; i < pathArray.length(); i++) {
    				String pathStr = pathHead+pathArray.getString(i);
            		if(pathStr.endsWith(filenameExtension) && !filePathList.contains(pathStr)){
            			System.out.println(pathStr);
            			if(filenameExtension.contains(GzFlag)){
            				pathStr = pathStr.replace(GzFlag, ".");
            			}
            			filePathList.add(pathStr);
            		}
				}
    		}
        }  

		if (fis != null)fis.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		System.out.println("共"+filePathList.size()+"个*"+filenameExtension+"文件！\n");
		return filePathList;
	}
	//读文件内容
	private static String readFile(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"utf-8");
		BufferedReader in = new BufferedReader(isr);
		
		StringBuffer  bf = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			bf.append(line).append("\n");
		}
		if(fis !=null)fis.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		return bf.toString();
	}
	// 写文件
	private static void writeFile(File file , String content , boolean isAppend) throws Exception{
		BufferedWriter writer = new BufferedWriter(
				                     new OutputStreamWriter(
				                              new FileOutputStream(file,isAppend), "utf-8"));
		writer.write(content);
		writer.newLine();
		writer.flush();
		writer.close();
	}
	//冉蒙：生成hq_all.js文件
	private static void writeAllJsFile() throws Exception {
		String hqAllFileName = "/static/pub2.0/js/hq_all.js";
		String modules = BASEPATH + "/static/pub2.0/js/conf/modules.json";
		File modlefile = new File(modules);

		FileInputStream modulefis = new FileInputStream(modlefile);
		String line; // 用来保存每行读取的内容
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(modulefis));
		line = reader.readLine(); // 读取第一行
		while (line != null) { // 如果 line 为空说明读完了
			buffer.append(line); // 将读到的内容添加到 buffer 中
			buffer.append("\n"); // 添加换行符
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		modulefis.close();
		net.sf.json.JSONObject config = net.sf.json.JSONObject.fromObject(buffer.toString());
		@SuppressWarnings("unchecked")
        Iterator<String> it = config.keys();
		while (it.hasNext()) {
			net.sf.json.JSONObject moduleJson = (net.sf.json.JSONObject) config.get(it.next().toString());
			net.sf.json.JSONArray jsfileArray = null;
			if (moduleJson != null) {
				if (moduleJson.get("js") instanceof net.sf.json.JSONArray) {
					jsfileArray = (net.sf.json.JSONArray) moduleJson.get("js");
				} else if (moduleJson.get("js") instanceof String) {
					List<String> jsfile = new ArrayList<String>();
					jsfile.add(moduleJson.get("js").toString());
					jsfileArray = net.sf.json.JSONArray.fromObject(jsfile);
				}

			}
            Object[] fileArray =  jsfileArray.toArray();
            System.out.println("共"+fileArray.length+"个！");
            System.out.println(jsfileArray);
            if (fileArray != null) {
                //writeJsArrayFiles(fileArry,hqAllFileName);
                String fileName = hqAllFileName;
                
                fileName = BASEPATH + fileName;
                List<String> fileadded = new ArrayList<String>();
                for (int t = 0; t < fileArray.length; t++) {
                    String jsfileName = BASEPATH + "/" + fileArray[t].toString();
                    String prefix = jsfileName.substring(jsfileName.lastIndexOf(".") + 1);
                    if (prefix.equals("js")) {// 将js写入all.js文件
                        if (!fileadded.contains(jsfileName) && jsfileName.indexOf("jquery") == -1) {
                            readAndWriteFile(jsfileName, fileName);
                            fileadded.add(jsfileName);
                            System.out.println(t + "--" + jsfileName);
                        }
                    }
                }
                System.out.println(fileName+"生成完成，打包js个数 : " + fileadded.size());
            }
		}
	}
	/**
	 * 通用打包js方法
	 * @param fileArray js文件路径数组，元素为js文件路径，格式：static/artTemplate/template.js
	 * @param fileName 最后生成的文件名，格式：/static/pub2.0/js/hq_all.js
	 */
	public static void writeJsArrayFiles(Object[] fileArray, String fileName) {
	    fileName = BASEPATH + fileName;
        List<String> fileadded = new ArrayList<String>();
        for (int t = 0; t < fileArray.length; t++) {
            if (fileArray[t] == null) {
                continue;
            }
            String jsfileName = BASEPATH + "/" + fileArray[t].toString();
                if (!fileadded.contains(jsfileName) ) {
                    readAndWriteFile(jsfileName, fileName);
                    fileadded.add(jsfileName);
                    System.out.println(t + "--" + jsfileName);
                }
        }
        System.out.println(fileName+"生成完成，打包文件个数 : " + fileadded.size());
    }
    public static void readAndWriteFile(String readfile, String writefile) {
		try {
	        File hqAllFile = new File(writefile);
	        if (hqAllFile.exists()) {
	            hqAllFile.delete();
	        }
	        hqAllFile.createNewFile();
	        
			FileReader fr = new FileReader(readfile);
			FileWriter fw = new FileWriter(writefile, true);
			BufferedReader br = new BufferedReader(fr);
			BufferedWriter bw = new BufferedWriter(fw);
			int i = 0;// 记录行数的标识。
			StringBuffer sb = new StringBuffer();
			String temp = br.readLine();
			// 当读取六行，则通过bw写到写文件中。
			while (temp != null) {
				i += 1;
				sb.append(temp);
				if (i == 1) {// 如果到一行，则把拼接的一行写到文件中。
					bw.write(new String(sb));
					bw.newLine();// 换行符
					bw.flush();// 需要急时涮新流的缓冲区，不然写文件时，当缓冲区满就再也写不进去了
					i = 0;
					sb = new StringBuffer();
				}
				temp = br.readLine();
			}
			if (sb.length() > 0) {
				bw.write(new String(sb));
				bw.newLine();
				bw.flush();
				bw.close();// 关闭方法在涮新以后,
			}
			br.close();
			// System.out.println("转写文件完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
