package com.temp.code.spring.aspectj;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAnnotationTest {  
    
    @Test  
    public void testHelloworld() {  
        ApplicationContext ctx =  new ClassPathXmlApplicationContext("/com/temp/code/spring/aspectj/application.xml");  
        IHelloWorld2Service helloworldService =ctx.getBean("helloWorld2Service", IHelloWorld2Service.class);  
        String param = "12";  
        System.out.println(helloworldService.sayHello2(param));  
        System.out.println(helloworldService.otherMethod(param));  
    }  
  
}  