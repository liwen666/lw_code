package com.temp.code.reflect.field.f2;

public class ReflectTest
{
    private String name;
    private int age;
    public void setName(String aName)
    {
    	System.out.println("调用setName");
        this.name = aName;
    }
    public String getName()
    {
        return this.name;
    }
    public void setAge(int aAge)
    {
    	System.out.println("调用setAge");
        this.age = aAge;
    }
    public int getAge()
    {
        return this.age;
    }
    public ReflectTest()
    {
        System.out.println("调用无参数构造函数!");
    }
}