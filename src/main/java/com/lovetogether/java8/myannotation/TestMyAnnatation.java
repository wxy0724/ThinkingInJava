package com.lovetogether.java8.myannotation;

import org.junit.Test;

import java.lang.reflect.Method;

public class TestMyAnnatation {

    @Test
    public void test1(){
        Class<TestMyAnnatation> clazz = TestMyAnnatation.class;
        try {
            Method m1 = clazz.getMethod("show");

            MyAnnotation[] mas = m1.getAnnotationsByType(MyAnnotation.class);

            for (MyAnnotation myAnnotation : mas) {
                System.out.println(myAnnotation.value());
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation("ABC") String str){

    }

}
