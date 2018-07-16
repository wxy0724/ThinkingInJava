package com.fang.jvm.learn;

public class MyLearnJVM {
    public static void main(String[] args) {
//        Parent p = new Parent();
        Child child = new Child();
        try {
            child.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

class Parent {
    {
        System.out.println("Parent的普通代码块");
    }

    static {
        System.out.println("Parent的静态代码块");
    }

    public Parent() {
        System.out.println("Parent的构造代码块");
    }

    public static void staticMethod1() {
        System.out.println("Parent的静态代方法");
    }

    public static void staticMethod2() {
        System.out.println("Parent的静态代方法2");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("销毁Parent类");
    }
}

class Child extends Parent {
    {
        System.out.println("Child的普通代码块");
    }

    static {
        System.out.println("Child的静态代码块");
    }

    public Child() {
        System.out.println("Child的构造代码块");
    }

    public static void staticMethod1() {
        System.out.println("Child的静态代方法");
    }

    public static void staticMethod2() {
        System.out.println("Child的静态代方法2");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("销毁Child类");
    }
}
