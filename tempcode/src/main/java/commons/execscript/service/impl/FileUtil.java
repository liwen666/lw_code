package commons.execscript.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.tjhq.commons.execscript.common.Constants;

public class FileUtil {
	public static void main(String[] args) throws Exception {
		buildFilesMain();//编号并生成脚本记录文件
		//renameCNFiles();//重命名旧编号规则生成的脚本文件，将中文名称去掉，而作为注释写到脚本内容结尾。
	}
	/*
	 * 重命名旧编号规则生成的脚本文件，将中文名称去掉，而作为注释写到脚本内容结尾。
	 */
	private static void renameCNFiles() throws Exception {
		String scriptFilePath = Constants.USERDIR + Constants.SCRIPT_SRCPATH;
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		renameAllBuildedFiles("",scriptFilePath,Constants.SCRIPT_DIVNAME,fileList);
	}
	/*
	 * 获取所有已编号脚本文件
	 * @param dirkey 路径唯一标识
	 * @param filePath 全路径
	 * @param divName 文件夹名
	 * @param fileList 所有文件list
	 */
	private static void renameAllBuildedFiles(String dirkey , String filePath, String divName, List<Map<String, String>> fileList) throws Exception {
		File[] files = new File(filePath).listFiles();
		for (File file : files) {
			if(".svn".equals(file.getName())){
				continue;
			}
			if (file.isDirectory()) {
				String subKey = "";
				if("".equals(dirkey)){//遍历的是sql文件夹
					subKey = file.getName();
				}else{//通用或业务系统或旧脚本文件夹
					subKey = dirkey+"_"+file.getName();
				}
				renameAllBuildedFiles(subKey, file.getAbsolutePath(), file.getName(), fileList);
			} else {
				if(Constants.SCRIPTRECORD_FILENAME.equals(file.getName())){//脚本记录文件不编号
					continue;
				}
				String fileName = file.getName();
				String tempFileName = file.getName();
				Map<String, String> fileMap = new HashMap<String, String>();
				if (fileName.startsWith(divName + "_") || Constants.SCRIPT_OLDPATH.equals(divName)) {//只取已编号的和旧脚本
					String[] strArray = fileName.split("_");
					//common_18_jdbc_更新执行脚本存储过程.sql（4个）
					//common_22_冉蒙增加用户对单位菜单.sql（3个）
					if(strArray.length==3){
						continue;
					}
					if(strArray.length==4 && Constants.SCRIPT_EXECTYPE.equals(strArray[3])){
						continue;
					}
					fileMap.put("divName", divName);
					fileMap.put("dirkey", dirkey);
					fileMap.put("fileName", fileName);
					
					String nameHead = divName;
					if(Constants.SCRIPT_OLDPATH.equals(divName)){
						nameHead = Constants.SCRIPT_OLDHEAD;
					}
					int beginIdx = fileName.indexOf("_", (nameHead+"_").length())+1;
					if(Constants.SCRIPT_EXECTYPE.equals(fileName.split("_")[2])){
						beginIdx = fileName.indexOf("_", (nameHead+"_"+fileName.split("_")[1]+"_"+fileName.split("_")[2]+"_").length())+1;
					}
					int endIdx = fileName.lastIndexOf(".");
					String scriptName = fileName.substring(beginIdx, endIdx);
					
					fileMap.put("scriptName", scriptName);
					fileList.add(fileMap);//这个List目前没什么卵用
					
					/*
					 * 1.重命名，只保留编号
					 * 2.将文件名称作为注释写到脚本内容结尾。
					 */
					String scriptCode = fileName.split("_")[1];
					String fileEnd = fileName.substring(endIdx);
					if(Constants.SCRIPT_EXECTYPE.equals(fileName.split("_")[2])){
						fileEnd = Constants.SCRIPT_EXECTYPE+"_"+fileEnd;
					}
					fileName = nameHead + "_" + scriptCode + "_" + fileEnd;
					if(fileName.equals(tempFileName)){//已经编辑过的不再编辑
						continue;
					}
					System.out.println(filePath + "\\" + fileName);
					File newFile = new File(filePath + "\\" + fileName);
					FileUtils.copyFile(file, newFile);
					file.deleteOnExit();//先复制再删除

					String scriptEnd = "--"+scriptName;
					writeFile(newFile, scriptEnd, true);
				}
			}
		}
	}
	/*
	 * 编号并生成脚本记录文件
	 */
	public static void buildFilesMain() throws Exception {
		String scriptFilePath = Constants.USERDIR + Constants.SCRIPT_SRCPATH;
		// 获取已有的最大编号
		Map<String, Integer> codeMap = getCodeMap("", scriptFilePath, Constants.SCRIPT_DIVNAME, new HashMap<String, Integer>());
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
		System.out.println("最大编号："+codeMap);
		// 编号
		buildFiles("",scriptFilePath, Constants.SCRIPT_DIVNAME, codeMap, fileList);
		System.out.println("-------------------脚本编号完成------------------------");
		
		//编辑脚本目录文件
		orderByName(fileList);
		writeScriptRecordFile(fileList);
	}
	//编辑脚本目录文件 : scriptRecord.txt
	private static void writeScriptRecordFile(List<Map<String, String>> fileList) throws IOException {
		String scriptFilePath = Constants.USERDIR + Constants.SCRIPT_SRCPATH;
		BufferedWriter writer = null;
		File file = null;
		String currentDiv = "";
		for (Map<String, String> fileMap : fileList) {
			String dirkey = fileMap.get("dirkey");
			String divName = "";
			String fileName = fileMap.get("fileName");
			String scriptName = fileMap.get("scriptName");
			if(!currentDiv.equals(dirkey)){//新增文件
				if(writer!=null){
					writer.flush();
					writer.close();
				}
				currentDiv = dirkey;
				for (String dir : dirkey.split("_")) {
					divName += dir + "/";
				}
				file = new File(scriptFilePath+divName+Constants.SCRIPTRECORD_FILENAME);
				file.delete();
				file.createNewFile();
				System.out.println("脚本目录文件："+divName+file.getName());
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true), "gbk"));
			}
			//写
			writer.write((fileName+Constants.SCRIPTSPLITFLAG +scriptName).trim());
			writer.newLine();
		}
		if(writer!=null){
			writer.flush();
			writer.close();
		}
		System.out.println("-------------------脚本目录文件编辑完成------------------------");
	}
	// 获取最大编号
	private static Map<String, Integer> getCodeMap(String key, String scriptFilePath, String divName, Map<String, Integer> codeMap) {
		final File f = new File(scriptFilePath);
		File[] files = null;
		if (Constants.SCRIPT_DIVNAME.equals(divName) 
				|| Constants.SCRIPT_BGTPATH.equals(divName) 
				|| Constants.SCRIPT_SPFPATH.equals(divName)
				|| Constants.SCRIPT_BASPATH.equals(divName) 
                || Constants.SCRIPT_KPIPATH.equals(divName)
                || Constants.SCRIPT_BILPATH.equals(divName)
                || Constants.SCRIPT_INDIPATH.equals(divName)
				//|| Constants.SCRIPT_OLDPATH.equals(divName)
		) {
			files = f.listFiles();
		} else {
			files = f.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.startsWith(dir.getName() + "_");
				}
			});
		}
	
		for (File file : files) {
			if (file.isDirectory()) {
				if(Constants.SCRIPT_OLDDIVNAME.equals(file.getName())){//old文件夹不编号
					continue;
				}
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
	/*
	 * 编号
	 */
	private static void buildFiles(String key , String filePath, String divName, Map<String, Integer> codeMap, List<Map<String, String>> fileList)
			throws Exception {
//		File[] files = orderByDate(filePath);
	    File[] files = orderByNameBeforBuild(filePath);
		for (File file : files) {
			if(".svn".equals(file.getName())){
				continue;
			}
			if (file.isDirectory()) {
				/*if(Constants.SCRIPT_OLDDIVNAME.equals(file.getName())){//old文件夹不编号
					continue;
				}*/
				String subKey = "";
				if("".equals(key)){//遍历的是sql文件夹
					subKey = file.getName();
				}else{//通用或业务系统文件夹
					subKey = key+"_"+file.getName();
				}
				buildFiles(subKey, file.getAbsolutePath(), file.getName(), codeMap, fileList);
			} else {
				if(Constants.SCRIPTRECORD_FILENAME.equals(file.getName())){//脚本记录文件不编号
					continue;
				}
				String fileName = file.getName();
				String scriptName = fileName;
				Map<String, String> fileMap = new HashMap<String, String>();
				if (!fileName.startsWith(divName + "_") && !Constants.SCRIPT_OLDDIVNAME.equals(divName)) { // 未编号的（并且不是old脚本）
					int maxCode = codeMap.get(key) == null ? 1 : codeMap.get(key) + 1;
					codeMap.put(key, maxCode);
					/*// 不再在文件末尾追加脚本记录表删除新增语句，改为在程序中另外执行 2015.9.21
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
					String typename = sdf.format(new Date());

					String dbFileName = fileName.substring(0, fileName.lastIndexOf('.'));
					String scriptEnd = getScriptEnd(divName, Integer.toString(maxCode), typename, dbFileName);
					readFile(file);
					writeFile(file, scriptEnd, true);*/
					
					scriptName = fileName.split("\\.")[0];
					//在脚本文件末尾追加注释：文件中文名
					String scriptEnd = "--"+scriptName;
					//readFile(file);
					writeFile(file, scriptEnd, true);
					
					// 编号并重命名
					//boolean renameRes = file.renameTo(new File(filePath + "\\" + divName + "_" + maxCode + "_" + fileName+"bizaiyi"));
					String fileEnd = fileName.split("\\.")[fileName.split("\\.").length-1];
					if(fileName.startsWith(Constants.SCRIPT_EXECTYPE)){
						fileName = Constants.SCRIPT_EXECTYPE+"_."+fileEnd;
					}else if(fileName.startsWith(Constants.SCRIPT_DFBZX)){
						fileName = Constants.SCRIPT_DFBZX+"_."+fileEnd;
					}else{
						fileName = "."+fileEnd;
					}
					fileName = divName + "_" + maxCode + "_" + fileName;
					FileUtils.copyFile(file, new File(filePath + "\\" + fileName));
					file.deleteOnExit();//先复制再删除
					System.out.println("编号成功："+key+" : "+scriptEnd);
				}else{//已编号
					String scriptContent = readFile(file);
					String[] strArr = scriptContent.split("--");
					scriptName = strArr[strArr.length-1];
				}
				fileMap.put("divName", divName);
				fileMap.put("dirkey", key);
				fileMap.put("fileName", fileName);
				fileMap.put("scriptName", scriptName);
				fileList.add(fileMap);
			}
		}
	}
	private static File[] orderByNameBeforBuild(String filePath) {
	    File file = new File(filePath);
        File[] fs = file.listFiles();
        if (fs == null) {
            return new File[0];
        }
        Arrays.sort(fs, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                if(f1.isDirectory() || f2.isDirectory()){
                    return 0;                
                }
                String[] names1 = f1.getName().substring(0, f1.getName().indexOf("."))
                                  .split("_");
                String[] names2 = f2.getName().substring(0, f2.getName().indexOf("."))
                                  .split("_");
                int diff = 0;
                for (int i = 0; i < Math.min(names1.length, names2.length); i++) {
                    String name1 = names1[i];
                    String name2 = names2[i];
                    if (StringUtils.isNumeric(name1) && StringUtils.isNumeric(name2)) {
                        diff = Integer.parseInt(name1)- Integer.parseInt(name2);
                    } else {
                        diff = name1.compareTo(name2) ;
                    }
                    if (diff != 0) {
                        return diff;
                    }
                }
                if (names1.length - names1.length > 0) {
                    return 12;
                }
                return 0;
            }
            
            public boolean equals(Object obj) {
                return true;
            }
        });
        return fs;
    }
    // 按名称排序，从小到大。
	private static void orderByName(List<Map<String, String>> strList) {
		if (strList == null) {
			return;
		}
		Collections.sort(strList, new Comparator<Map<String, String>>() {
			@Override
			public int compare(Map<String, String> o1, Map<String, String> o2) {
				String keyType = "S";//比较对象为String
				//先比较是否为同文件夹
				String name1 = o1.get("dirkey");
				String name2 = o2.get("dirkey");
				
				//文件夹相同时，比较文件名中的编号。（数字）
				if(name1.equals(name2)){
					name1 = o1.get("fileName").split("_")[1];
					name2 = o2.get("fileName").split("_")[1];
					keyType = "I";//比较对象为int
				}
				int diff = 0;
				if(keyType.equals("S")){//比较对象为String
					diff = name1.compareTo(name2) ;
				}else if(keyType.equals("I")){//比较对象为int
					diff = Integer.parseInt(name1)- Integer.parseInt(name2);
				}else{
					throw new RuntimeException("文件名不规范:"+name1+" || "+name2);
				}
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}
		});
	}
	// 按最后编辑日期排序，从小到大。
	private static File[] orderByDate(String filePath) {
		File file = new File(filePath);
		File[] fs = file.listFiles();
		if (fs == null) {
			return new File[0];
		}
		Arrays.sort(fs, new Comparator<File>() {
			public int compare(File f1, File f2) {
				long diff = f1.lastModified() - f2.lastModified();
				if (diff > 0)
					return 1;
				else if (diff == 0)
					return 0;
				else
					return -1;
			}

			public boolean equals(Object obj) {
				return true;
			}
		});
		return fs;
	}
	/*
	//获取脚本追加的编辑内容
	private static String getScriptEnd(String typeid, String keyid, String typename, String keyname){
		StringBuffer scEnd = new StringBuffer();
		scEnd.append("\n");
		scEnd.append("delete from "+Constants.SCRIPT_TABLENAME+" where typeid = '"+typeid+"' and keyid = '"+keyid+"';\n");
		//scEnd.append("commit;\n");
		scEnd.append("insert into "+Constants.SCRIPT_TABLENAME+" (typeid, keyid, typename, keyname)\n");
		scEnd.append("values ('"+typeid+"','"+keyid+"','"+typename+"','"+keyname+"');\n");
		scEnd.append("\n");
		return scEnd.toString(); 
	}*/
	//读脚本文件
	private static String readFile(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"gbk");
		BufferedReader in = new BufferedReader(isr);
		
		//BufferedReader in = new BufferedReader(new FileReader(file));
		StringBuffer  scriptBf = new StringBuffer();
		String line = null;
		while ((line = in.readLine()) != null) {
			scriptBf.append(line).append("\n");
		}
		if(fis !=null)fis.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		return scriptBf.toString();
	}
	/**
	 * 写文件
	 * @param file
	 * @param script 写入的脚本
	 * @param isAppend 追加还是覆盖重写
	 * @throws Exception
	 */
	private static void writeFile(File file , String script , boolean isAppend) throws Exception{
		//BufferedWriter out = new BufferedWriter(new FileWriter(file, isAppend));
		BufferedWriter writer = new BufferedWriter(
				                     new OutputStreamWriter(
				                              new FileOutputStream(file,isAppend), "gbk"));
		writer.write(script);
		writer.newLine();
		writer.flush();
		writer.close();
	}
}