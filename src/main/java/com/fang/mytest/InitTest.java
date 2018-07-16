package com.fang.mytest;

public class InitTest {
    public static void main(String[] args) {
        System.out.println(A.value);
    }
}
class B{
    static int value = 100;
    static{
        System.out.println("Class B is initialized.");
    }
}

class A extends B {
    static{
        System.out.println("Class A is initialized.");
    }
}


