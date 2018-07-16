package com.fang.chapterviii;

public class Circle extends Shape{
    @Override
    public void draw(){
        System.out.println("Circle.draw()");
    }
    @Override
    public void erase(){
        System.out.println("Circle.erase");
    }
    @Override
    public void end(){
        System.out.println("Circle already end!");
    }

}
