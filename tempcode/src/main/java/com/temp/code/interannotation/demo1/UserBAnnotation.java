package com.temp.code.interannotation.demo1;

import com.temp.code.interannotation.MapperScan;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类专门用来测试注解使用
 */
@MapperScan
@TestB(name = "type",id = 10,gid = Thread.class)
// 使用了类注解
public class UserBAnnotation {




    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println();
        Class<?> clazz = String.class;
        System.out.println(clazz);
        Class<UserBAnnotation> userBAnnotationClass = UserBAnnotation.class;
        Annotation[] annotations = userBAnnotationClass.getAnnotations();

        for (Annotation a : annotations) {
            if (a instanceof TestB) {
                TestB b = (TestB) a;
                Class<?> gid = b.gid();
                System.out.println(b.name()+b.id()+ gid);
                Object o = gid.newInstance();
                Thread eee = (Thread) o;
                System.out.println(eee.getName());

            }

        }
    }

}