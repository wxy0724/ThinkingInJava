package com.fang.java8.myinterface;

public interface MyInterface {

    default String getName(){
        return "hehehe";
    }

    public static void show(){
        System.out.println("接口中的静态方法");
    }
}
