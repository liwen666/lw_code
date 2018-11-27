package com.temp.code.proxy.demo3;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Test {
		public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
			Map<String, String> setting= new HashMap<>(2);
			setting.put("tag1", "com.temp.code.proxy.demo3.Targe");
			setting.put("tag2", "com.temp.code.proxy.demo3.Targe2");
			
			ItargeInterface tar = (ItargeInterface) Class.forName(setting.get("tag1")).newInstance();
			ItargeInterface tar2 = (ItargeInterface) Class.forName(setting.get("tag2")).newInstance();
			Object[] obj={tar,tar2};
			Object[] obj2={new ItargeInterface(){

				@Override
				public Class[] getTak(String arg) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void say() {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void invok(Integer index) {
					System.out.println("匿名参数类");
					
				}}};
			ItargeInterface proxy= (ItargeInterface) Proxy.newProxyInstance(tar.getClass().getClassLoader(), tar.getClass().getInterfaces(), new HandleInvoke(obj2));
			ItargeInterface proxy2= (ItargeInterface) Proxy.newProxyInstance(tar.getClass().getClassLoader(), tar.getClass().getInterfaces(), new HandleInvoke(obj));
//			proxy.say();
//			proxy2.say();
//			proxy2.invok(0);
			proxy2.invok(1);
//			proxy.invok(0);
			
		}
}
