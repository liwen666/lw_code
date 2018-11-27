package com.temp.code.reflect.field.f2;

import java.util.*;
import java.lang.reflect.*;
/**
*通过反射为字段赋值用的不是set方法。使用的对象是内存中的对象克隆出来的
*对此对象的操作都是通过反射，和原来对象无关
*  
*  getType就是获取对象的类型  判断类型为类型赋值
*/
public class Sample11{
	public static void main(String[] args) throws Exception {
		
        Class cl = Class.forName("com.temp.code.reflect.field.f2.ReflectTest");
        Object rt = cl.newInstance();//创建一个对象
        Field[] field = cl.getDeclaredFields();
        for(Field item : field)
        {
            item.setAccessible(true);//值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。 
            if(item.getType() == String.class)
            {
                item.set(rt,"张三");
            }
            if(item.getType() == int.class)
            {
                item.set(rt,10);
            }
            System.out.println(item.get(rt));//输出当前字段在对象中的值是多少
        }

    }
}
