package com.selenium.jrpc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射工具类
 */
public class ReflectionUtils {

    //根据类clazz创建对象
    public static <T> T newInstance(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    //获得该类自身声明的所有public方法
    public static Method[] getPublicMethods(Class clazz) {
        List<Method> pMethods = new ArrayList<>();
        //返回该类声明的所有方法
        Method[] methods = clazz.getDeclaredMethods();
        //过滤到public方法
        pMethods = Arrays
                .stream(methods)
                .filter(method ->
                        Modifier.isPublic(method.getModifiers())
                ).collect(Collectors.toList());
        return pMethods.toArray(new Method[0]);
    }

    //调用指定对象的指定方法
    public static Object invoke(Object obj, Method method, Object... args) {
        Object object = null;
        try {
            object = method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
