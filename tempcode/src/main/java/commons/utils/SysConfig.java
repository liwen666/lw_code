package commons.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 用于读全局常数变量属性的方法
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author 
 * @version 1.0
 */

public class SysConfig {

	private String FILE_NAME ="configinfo.properties";
	// 属性文件
	private static Properties pro = null;
	private static SysConfig sys = new SysConfig();
	private static String filepath = "";

	private SysConfig() {
		if (sys == null) {
			init();
		}
	}

	public void init() {
		try {
			URL url = (SysConfig.class).getClassLoader().getResource(FILE_NAME);
			
			if (url == null)
				url = ClassLoader.getSystemResource(FILE_NAME);
			InputStream is=url.openStream();
			pro = new Properties();
			pro.load(is);			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 自我实例化的方法
	 * 
	 * @return SysConfig
	 */
	public static SysConfig getInstance() {
		return sys;
	}

	/**
	 * 获取系统属性的方法
	 * 
	 * @param Key
	 *            String
	 * @return String
	 * @throws FileNotFoundException
	 */
	public String getSysPara(String Key) {
		// onLoadFile();
		return pro.getProperty(Key);
	}

	/**
	 * 对属性文件进行修改
	 * @param Key
	 * @param value
	 */
	public void setSysPara(String Key, String value) {
		// onLoadFile();
		FileOutputStream fout = null;
		String path = "";
		try {
			path = getClass().getResource(filepath).toString();
			path = path.substring(5, path.length());
			fout = new FileOutputStream(path);
			pro.put(Key, value);
			pro.store(fout, "System Parameter List");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * 将中文常数变量进行编码转化
	 * @param strvalue
	 * @return
	 */
	public static String toChinese(String strvalue) {
		if (strvalue == null) {
			return null;
		}
		try {
			strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
		} catch (Exception e) {
		}
		return strvalue;
	}

	public static void main(String arg[])  {
		System.out.println(getInstance().getSysPara("lutaserver.host"));
	}
}
