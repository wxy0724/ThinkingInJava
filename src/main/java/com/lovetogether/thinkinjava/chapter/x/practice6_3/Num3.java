package com.lovetogether.thinkinjava.chapter.x.practice6_3;

import com.lovetogether.thinkinjava.chapter.x.practice6.Num1;
import com.lovetogether.thinkinjava.chapter.x.practice6_2.Num2;

public class Num3 extends Num2 {
    public Num1 getPractice1(){
        return new Num2().getPractice();
    }
}
