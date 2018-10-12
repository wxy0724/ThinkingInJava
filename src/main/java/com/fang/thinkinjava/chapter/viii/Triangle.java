package com.fang.thinkinjava.chapter.viii;

public class Triangle extends Shape {
    @Override
    public void draw(){
        System.out.println("Triangle.draw()");
    }
    @Override
    public void erase(){
        System.out.println("Triangle.erase()");
    }
    @Override
    public void end(){
        System.out.println("Triangle already end!");
    }
}
