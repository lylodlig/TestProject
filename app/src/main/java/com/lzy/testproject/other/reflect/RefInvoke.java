package com.lzy.testproject.other.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 用于反射的工具类
 */
public class RefInvoke {


    public static Object createObject(String className) {
        try {
            Class<?> r = Class.forName(className);
            return createObject(r);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createObject(String className, Class parseType, Class parseValue) {
        try {
            Class<?> r = Class.forName(className);
            return createObject(r, parseType, parseValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createObject(String className, Class[] parseTypes, Object[] parseValues) {
        try {
            Class<?> r = Class.forName(className);
            return createObject(r, parseTypes, parseValues);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object createObject(Class clazz, Class parseType, Object parseValue) {
        Class[] parseTypes = new Class[]{parseType};
        Object[] parseValues = new Object[]{parseValue};
        return createObject(clazz, parseTypes, parseValues);
    }

    public static Object createObject(Class clazz) {
        Class[] parseTypes = new Class[]{};
        Class[] parseValues = new Class[]{};
        return createObject(clazz, parseTypes, parseValues);
    }

    public static Object createObject(Class clazz, Class[] parseTypes, Object[] parseValues) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor(parseTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(parseValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Object getFiledObject(Object object, String filedName) {
        return getFiledObject(object.getClass(), filedName);
    }

    public static Object getFiledObject(String className, Object object, String filedName) {
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFiledObject(Class clazz, Object object, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFiledObject(String className, Object object, String filedName, Object filedValue) {
        try {
            Class<?> aClass = Class.forName(className);
            Field field = aClass.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(object, filedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFiledObject(Class clazz, Object object, String filedName, Object filedValue) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(object, filedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setFiledObject(Object object, String filedName, Object filedValue) {
        setFiledObject(object.getClass(), filedName, filedValue);
    }

    public static Object getStaticFieldObject(String className, String filedName) {
        return getFiledObject(className, null, filedName);
    }

    public static void setStaticFiledObject(String className, String filedName, Object filedValue) {
        setFiledObject(className, filedName, filedValue);
    }

    public static Object invokeInstanceMethod(Object object, String methodName, Class[] parseTypes, Object[] parseValues) {
        if (object == null) return null;
        try {
            Method method = object.getClass().getDeclaredMethod(methodName, parseTypes);
            method.setAccessible(true);
            return method.invoke(object, parseValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
