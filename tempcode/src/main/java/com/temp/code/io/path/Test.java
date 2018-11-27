package com.temp.code.io.path;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Test {
	public static void main(String[] args) throws IOException {
		// 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
		File f = new File(Test.class.getResource("/").getPath());
		System.out.println(f+"----1111");
//		System.out.println(f.getPath()+"---getPath--");
//		System.out.println(f.getAbsolutePath()+"--getAbsolutePath()---");
//		System.out.println(f.getCanonicalPath()+"--getCanonicalPath()---");
		// 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
		File f2 = new File(Test.class.getResource("").getPath());
		System.out.println(f2+"----2222");
		// 第二种：获取项目路径    D:\git\daotie\daotie
		File directory = new File("");// 参数为空
		String courseFile = null;
		try {
			courseFile = directory.getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(courseFile+"---3333");
		// 第三种：  file:/D:/git/daotie/daotie/target/classes/
		URL xmlpath = Test.class.getClassLoader().getResource("");
		System.out.println(xmlpath+"---4444");
		// 第四种： D:\git\daotie\daotie
		System.out.println(System.getProperty("user.dir")+"---5555");/** 结果： C:\Documents and Settings\Administrator\workspace\projectName
		* 获取当前工程路径*/// 第五种：  获取所有的类路径 包括jar包的路径
	}

}
