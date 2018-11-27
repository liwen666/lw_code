package com.temp.code.proxy.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class HandleInvoke implements InvocationHandler {
	private Object[]objs;
	private Array [] array;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before");
//		Object result = method.invoke(objs[0], args);这个是目标的接口方法，可以自动执行
		Method[] methods = objs[0].getClass().getMethods();
		Object invoke = null;
		for(Method m:methods){
			if(m.getName().equals("unInterfaceMethod")){
				System.out.println("un------");
				 invoke = m.invoke(objs[0], args);   //这个是目标的自己方法，通过接口方法当入口，不执行几口方法，选择自己的方法执行
				System.out.println("method------");
			}
		}
		Object result2 = method.invoke(objs[1], args);
//		Object result=method.invoke(objs[(Integer)args[0]], args);
		System.out.println("after");
		return invoke;
	}
	public HandleInvoke(Object[] objs) {
		super();
		this.objs = objs;
	}
	public HandleInvoke() {
		super();
	}
	
}
