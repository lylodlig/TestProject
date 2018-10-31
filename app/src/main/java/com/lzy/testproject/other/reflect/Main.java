package com.lzy.testproject.other.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by LiZhiyu on 2018/10/31.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.lzy.testproject.other.reflect.Person");
            Object instance = aClass.getDeclaredConstructor(String.class).newInstance("哈哈");
            //获取私有属性
            Field name = aClass.getDeclaredField("name");
            name.setAccessible(true);
            String n = (String) name.get(instance);
            //调用私有方法
            Method method = aClass.getDeclaredMethod("study", String.class);
            method.setAccessible(true);
            method.invoke(instance, n);

            Method method1 = aClass.getDeclaredMethod("method");
            method1.invoke(instance);

            System.out.println("打印私有参数：" + n);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
