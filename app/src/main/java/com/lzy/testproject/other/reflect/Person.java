package com.lzy.testproject.other.reflect;

/**
 * Created by LiZhiyu on 2018/10/31.
 */
public class Person {
    public Person() {
        System.out.println("无参构造函数");
    }

    public Person(String name) {
        this.name = name;
        System.out.println("有参数构造函数" + name);
    }

    private String name;

    private void study(String name) {
        System.out.println("调用有参数方法" + name);
    }

    public static void method(){
        System.out.println("调用静态方法");
    }
}
