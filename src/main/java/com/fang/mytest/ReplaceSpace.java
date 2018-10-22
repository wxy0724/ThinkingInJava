package com.fang.mytest;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReplaceSpace {

    @Test
    public void test1() {
        String str = " 1 2 3 ";
        str = str.trim();
        System.out.println(str);
    }

    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        String name = "com.fang.mytest.C";
        Class c = Class.forName(name);
        Object obj = c.newInstance();
        Method method = c.getDeclaredMethod("printI");
        // 使用private方法可以被调用
        method.setAccessible(true);
        method.invoke(obj);
        Field field = c.getDeclaredField("i");
        // 使private成员可以被访问、修改
        field.setAccessible(true);
        field.set(obj, 10);
        method.invoke(obj);
    }

}

class C {
    private int i = 5;

    private void printI() {
        System.out.println(this.i);
    }
}
