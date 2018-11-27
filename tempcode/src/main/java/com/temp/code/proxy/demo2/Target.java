package com.temp.code.proxy.demo2;

public class Target implements Interface1 {

	@Override
	public void say() {
		System.out.println("say()");
	}

	@Override
	public String get() {
		System.out.println("get()");
		return "get()";
	}

	@Override
	public String getBy(String key) {
		System.out.println("getBy");
		return key+"getBy";
	}
	

}
