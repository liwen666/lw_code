package com.temp.code.proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author  作者 :lw E-mail:
 * @date 创建时间：2016年12月1日 下午5:28:59
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class PersonHandler implements InvocationHandler {  
    
    private Object obj;  
      
    public PersonHandler(Object obj){  
        this.obj=obj;  
    }  
      
      
    @Override  
    public Object invoke(Object proxy, Method method, Object[] args)  
            throws Throwable {  
          
        System.out.println("before");  
        Object result = method.invoke(obj, args);  
        System.out.println("after");  
        return result;  
    }  
  
}  