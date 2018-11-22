package util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;


public class DataInitializerBpmn2 implements InitializingBean,DisposableBean {

		@Override
		public void destroy() throws Exception {
			System.out.println("destroyMethod 被执行");
			
			
		}
		@Override
		public void afterPropertiesSet() throws Exception {

			System.out.println("initMethod 被执行");
			
			ApplicationContext context = ApplicationContextUtil.getContext();
			System.out.println(context);
			
		}

}
