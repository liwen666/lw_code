package com.mybatis.multiple;

import org.aspectj.lang.ProceedingJoinPoint;  
import org.aspectj.lang.annotation.Around;  
import org.aspectj.lang.annotation.Aspect;  
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;  
  
@Component  
@Aspect  
public class MultipleDataSourceAspectAdvice {  
  
    @Around("execution(* com.service..DatainitServiceH2.*H2(..))")  
    public Object doAround1(ProceedingJoinPoint jp) throws Throwable {  
        MultipleDataSource.setThreadLocalDatasource("h2");  
        return jp.proceed();  
    }  
    
  
    @Around("execution(* com.service..DatainitServiceH2.*Bendi(..))")  
    public Object doAround2(ProceedingJoinPoint jp) throws Throwable {  
        MultipleDataSource.setThreadLocalDatasource("bendi");  
    	System.out.println("执行切面");
        return jp.proceed();  
    }  
    
    
    @Pointcut("execution( * com..AdminController.*(..))")
    public void myMethod(){};
    
    /*@Before("execution(public void com.oumyye.dao.impl.UserDAOImpl.save(com.oumyye.model.User))")*/
    @Before("myMethod()")
    public void before() {
        System.out.println("执行切面myMethod");
    } 
  
}  