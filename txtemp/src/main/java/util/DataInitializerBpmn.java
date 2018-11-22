package util;

import org.springframework.context.ApplicationContext;


public class DataInitializerBpmn  {

	public void initMethod() throws Exception {
		System.out.println("initMethod 被执行");
		
		ApplicationContext context = ApplicationContextUtil.getContext();
		System.out.println(context);
		}
		public void destroyMethod() throws Exception {
		System.out.println("destroyMethod 被执行");
		}

}
