package commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ApplicationContextUtil.context = context;
	}
	
	public static ApplicationContext getContext() {
		return ApplicationContextUtil.context;
	}
	
	public static Object getBean(String beanID) {
	    return getContext().getBean(beanID);
	}

}
