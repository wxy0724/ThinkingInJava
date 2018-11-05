package com.fang.mytest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.StringTokenizer;

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

    @Test
    public void test3() {
        String str = "ABCD.DCBA.EFG";
        System.out.println(str.indexOf("."));
        System.out.println(str.lastIndexOf("."));
        Random r = new Random(47);
        int i = r.nextInt(33);
        System.out.println(i);
    }

    @Test
    public void test4() {
        System.out.println(StringUtils.isBlank(" "));
        System.out.println(StringUtils.isBlank(null));
    }

}

class C {
    private int i = 5;
    private void printI() {
        System.out.println(this.i);
    }
}

