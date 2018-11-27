package com.temp.code.proxy.demo1;

import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * @author  作者 :lw E-mail:
 * @date 创建时间：2016年12月1日 下午5:30:46
 * @version 1.0 * @parameter 
 * @since 
 * @return 
 */
public class PersonTest {  
    
    @Test  
    public void test(){  
        PersonDao pDao = new PersonDaoImpl();  
        PersonHandler handler = new PersonHandler(pDao);  
          
        PersonDao proxy = (PersonDao)Proxy.newProxyInstance(pDao.getClass().getClassLoader(), pDao.getClass().getInterfaces(), handler);  
        proxy.say();  
    }  
}  