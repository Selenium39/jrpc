package com.selenium.jrpc.utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

//创建测试类快捷键Ctrl+Shift+T
public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestClass t = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1, methods.length);

        String mName = methods[0].getName();
        assertEquals("b", mName);


    }

    @Test
    public void invoke() {
        Class clazz = TestClass.class;
        Object obj = ReflectionUtils.newInstance(clazz);
        Method bMethod = ReflectionUtils.getPublicMethods(clazz)[0];
        String result = (String) ReflectionUtils.invoke(obj, bMethod);
        assertEquals("b", result);
    }
}