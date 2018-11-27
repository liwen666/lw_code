package com.temp.code.proxy.demo2;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
		Interface1 f = new Target();
		Interface1 pro = (Interface1) Proxy.newProxyInstance(f.getClass().getClassLoader(), f.getClass().getInterfaces(), new ProxyDom(f));
		String string = pro.get();
		String by = pro.getBy("name");
		pro.say();
				System.out.println(string);
				System.out.println(by);
	}
	
}
