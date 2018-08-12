package com.fang.java8.myinterface;

public class SubClass /*extends MyFun*/ implements MyInterface, MyFun{

    @Override
    public String getName() {
        return MyFun.super.getName();
    }
}
