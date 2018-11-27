package com.temp.code.proxy.demo3;

public class Targe implements ItargeInterface {

	@Override
	public Class[] getTak(String arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void say() {
		System.out.println("targe111111");
	}

	@Override
	public void invok(Integer index) {
		System.out.println("targe111111");
		// TODO Auto-generated method stub
		
	}
//	public void unInterfaceMethod(){
//		System.out.println("-------unInterfaceMethod----------");
//	}
	public void unInterfaceMethod(int m){
		System.out.println("-------unInterface----------");
	}
}
