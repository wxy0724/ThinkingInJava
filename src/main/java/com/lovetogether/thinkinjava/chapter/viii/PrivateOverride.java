package com.lovetogether.thinkinjava.chapter.viii;

public class PrivateOverride {
    private void f(){
        System.out.println("print (private f())");
    }
    public static void main(String[] args){
        PrivateOverride derive = new Derived();
        derive.f();
    }
}

class Derived extends PrivateOverride{
    public void f(){
        System.out.println("print (public f())");
    }
}
