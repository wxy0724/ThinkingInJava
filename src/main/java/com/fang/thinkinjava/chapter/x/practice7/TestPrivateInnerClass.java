package com.fang.thinkinjava.chapter.x.practice7;

public class TestPrivateInnerClass {
    private int i = 0;
    private String sayHi(){
        return "Hello World!";
    }
    public Inner getInner(){
        return new Inner();
    }
    class Inner{
        private void changeI(int a){
            i = a;
            System.out.println(sayHi());
        }
    }

    public static void main(String[] args) {
        TestPrivateInnerClass t = new TestPrivateInnerClass();
        System.out.println("Before： " + t.i);
        Inner i = t.getInner();
        i.changeI(2);
        System.out.println("After： " + t.i);
    }

}
