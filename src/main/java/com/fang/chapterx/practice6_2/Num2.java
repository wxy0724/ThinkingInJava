package com.fang.chapterx.practice6_2;

import com.fang.chapterx.practice6.Num1;

public class Num2 {
    protected class Practice implements Num1 {
        @Override
        public void sayHi() {
            System.out.println("Hello World!");
        }
    }
    public Practice getPractice(){
        return new Practice();
    }
}
