package com.fang.chapter.x.practice1;

public class Outer {
    class Inner{
        public void say(){
            System.out.println("I am OK!");
        }
    }
    public Inner getInner(){
        return new Inner();
    }

    public static void main(String[] args) {
        Outer o = new Outer();
        Inner i = o.getInner();
        i.say();
    }
}
