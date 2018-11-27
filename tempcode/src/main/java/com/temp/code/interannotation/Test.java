package com.temp.code.interannotation;
/**
* <p>describe</p> 
* <p>title Test.java</p>
* <p></p>
* @author lw
* @date 2016年12月10日下午3:35:02
* @version 1.0
* @link
*/
public class Test {
		public static void main(String[] args) {
			Class claz = MapperScan.class;
			System.out.println(claz.getName());
			System.out.println(claz.getSimpleName());
			Class[] declaredClasses = claz.getDeclaredClasses();
		}
}
