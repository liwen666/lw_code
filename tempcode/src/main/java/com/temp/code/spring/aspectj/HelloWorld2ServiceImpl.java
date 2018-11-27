package com.temp.code.spring.aspectj;

public class HelloWorld2ServiceImpl implements IHelloWorld2Service {

	@Override
	public String sayHello2(String param) {
		System.out.println("目标方法执行！");
		System.out.println(param);
		System.out.println(getValue());
		return"方法执行完成";
         
	}
	public String getValue(){
		System.out.println("返回值方法");
		return "kkkkk";
	}
	@Override
	public String otherMethod(String param) {
		System.out.println("other");
		return "kkkkk";
	}
}
