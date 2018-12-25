package com.lovetogether.java8.myinterface;

public class TestDefaultInterface {
    public static void main(String[] args) {
        SubClass sc = new SubClass();
        String name = sc.getName();
        System.out.println(name);

        MyInterface.show();
    }
}
