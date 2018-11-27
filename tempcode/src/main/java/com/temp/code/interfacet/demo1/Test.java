package com.temp.code.interfacet.demo1;
/**
* <p>describe</p> 
* <p>title Test.java</p>
* <p></p>
* @author lw
* @date 2016年12月10日下午5:22:26
* @version 1.0
* @link
*/
public class Test {
		public static void main(String[] args) throws InstantiationException, IllegalAccessException {
			say(Demo1.class.newInstance());
			say(Demo2.class.newInstance());
			say(Demo3.class.newInstance());
			
		}
		public static void say(Iinterface is){
			is.excute();
		}
}
