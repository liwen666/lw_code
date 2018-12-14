package commons.utils;

/**
 * Title:   序列发生器
 * Description:  生成数据库记录唯一的标识
 * Copyright:    Copyright (c) 2012
 * Company:      太极华青
 * @author
 * @version 1.0
 */
import java.util.Random;
import java.util.UUID;

public abstract class IdGenerator {
	private static long uniqueID = System.currentTimeMillis();
	
	public static synchronized long getUniqueID() {
		uniqueID++;
		return (uniqueID * 1000 + (new Random()).nextInt(9999999));
	}

	/**
	 * 获得一个UUID，UUID是由一个十六位的数字组成,表现出来的形式如：550E8400-E29B-11D4-A716-446655440000
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String strRet_ = UUID.randomUUID().toString();
		return strRet_;
	}
	/**
	 * 获得32位码的UUID
	 * @return 
	 * String
	 * @author  bizy
	 * Apr 25, 2014 10:56:59 AM
	 */
	public static String get32UUID(){
		return getUUID().replaceAll("-", "");
	}
	
	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number     int 需要获得的UUID数量
	 *            
	 * @return String[]   UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

}
