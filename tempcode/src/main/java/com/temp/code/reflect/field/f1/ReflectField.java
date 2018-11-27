package com.temp.code.reflect.field.f1;

import java.lang.reflect.Field;

public class ReflectField {
    
    /**
     * 获取字段方法和前面获取构造器和方法大同小异。getFileds():获取所有Public修饰的字段以及继承自父类的字段   规则与获取构造和方法大同小异
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Class claz = Field.class;
        Class claz1=Class.forName("com.temp.code.reflect.field.f1.Target");
        
    Field[]  fields=    claz.getFields();
    for(Field field:fields){
        System.out.println("获取所有的public及继承的字段"+field);
    }
    
    Field[]  fieldsDecrs=claz.getDeclaredFields();
    for(Field fieldDecr:fieldsDecrs){
        System.out.println("获取所有的字段包括private/public，但不包括继承的字段"+fieldDecr);        
    }
    
    /*获取指定的字段：参数传字段名称*/
    Field field1=claz1.getField("name");
    System.out.println("获取指定的public字段"+field1);
    
    /*获取指定的私有字段*/
    Field fieldP = claz1.getDeclaredField("number");
    System.out.println("获取指定的私有字段:"+fieldP);

   

       
     /**
      *为字段设值：setxxx(obj,基本类型数据)基本类型字段设值；  set(obj,引用类型数据)  引用类型设值 obj为字段所在底层对象 如果是静态字段则obj可以为NULL
      */
       /*给字段设置值*/  /**
        * 此处的newInstance只是一个复制的对象，其值来自于内存中的对象的属性和值
        * 可以通过反射为字段赋值
        */
//    	field1.setAccessible(true);
    	Target tr=(Target) claz1.newInstance();
        field1.set(tr, "张飞");
        System.out.println(field1.isAccessible());
        System.out.println(field1.getName());
        System.out.println(field1.get(tr));
        
        /*给私有字段设置值*/
        System.out.println(fieldP.isAccessible());
        fieldP.setAccessible(true);
        fieldP.setInt(tr, 12);
        System.out.println(fieldP.getInt(tr));
        System.out.println(claz1.newInstance()+tr.toString());
        
        
        
        /**
         *   获取字段值 ：getxxx(obj) get(obj) 如果字段为static修饰则obj可以为null
         */
        /*获取私有静态字段的值*/
        Field fieldd = claz1.getDeclaredField("name");
        fieldd.setAccessible(true);
            System.out.println(fieldd.get(claz1.newInstance())+"******私有静态引用类型");
            
            /*获取私有的字段的值*/
            Field fieldnum=claz1.getDeclaredField("number");
            fieldnum.setAccessible(true);
            /**
             * 这里通过字段  来拿实例的值
             * 这里的字段应该是   虚拟机里面的一个总的字段区域
             * field.get(实例对象)
             */
            System.out.println(fieldnum.getInt(claz1.newInstance())+"******私有基本类型");



        
    }
}
