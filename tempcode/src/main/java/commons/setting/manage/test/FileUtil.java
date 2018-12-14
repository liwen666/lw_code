package commons.setting.manage.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static void main(String[] args) throws Exception {
		String basePath = "I:\\西安项目20140804之前\\bzy项目\\西安\\新框架\\pub2.0治国交接\\all-js\\";
		String rmFilePath = basePath+"ranmeng.txt";
		String bzyFilePath = basePath+"bizaiyi.txt";
		
		File rmfile = new File(rmFilePath);
		File bzyfile = new File(bzyFilePath);

		List<String> rmJsNameList = readFile(rmfile);
		List<String> bzyJsNameList = readFile(bzyfile);
	/*	rmJsNameList.removeAll(bzyJsNameList);
		System.out.println("rm-bzy:");
		for (String jsName : rmJsNameList) {
			System.out.println(jsName);
		}
		System.out.println(rmJsNameList.size());*/
		bzyJsNameList.removeAll(rmJsNameList);
		System.out.println("bzy-rm:");
		for (String jsName : bzyJsNameList) {
			System.out.println(jsName);
		}
		System.out.println(bzyJsNameList.size());
	}

	
	//读脚本文件
	private static List<String> readFile(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr=new InputStreamReader(fis,"gbk");
		BufferedReader in = new BufferedReader(isr);
		List<String> jsNameList = new ArrayList<String>();

		String line = null;
		while ((line = in.readLine()) != null) {
			jsNameList.add(line);
		}
		if(fis !=null)fis.close();
		if(isr !=null)isr.close();
		if(in!=null)in.close();
		return jsNameList;
	}
	/*/**
	 * 写文件
	 * @param file
	 * @param script 写入的脚本
	 * @param isAppend 追加还是覆盖重写
	 * @throws Exception
	 *//*
	private static void writeFile(File file , String script , boolean isAppend) throws Exception{
		//BufferedWriter out = new BufferedWriter(new FileWriter(file, isAppend));
		BufferedWriter writer = new BufferedWriter(
				                     new OutputStreamWriter(
				                              new FileOutputStream(file,isAppend), "gbk"));
		writer.write(script);
		writer.newLine();
		writer.flush();
		writer.close();
	}*/
}