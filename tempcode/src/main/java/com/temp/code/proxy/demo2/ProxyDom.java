package com.temp.code.proxy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyDom  implements InvocationHandler{
	private Object obj;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method[] methods = obj.getClass().getMethods();
		for(Method m:methods){
			System.out.println(m.getName());
		}
		System.out.println("----------------------------");
		Object invoke = method.invoke(obj, args);
		System.out.println("----------------------------");
		System.out.println(invoke);
		System.out.println("----------------------------");
		return invoke;
	}
	public ProxyDom(Object obj) {
		super();
		this.obj = obj;
	}
	public ProxyDom() {
		super();
	}
	

}
