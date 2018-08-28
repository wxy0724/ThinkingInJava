package com.fang.chapter.viiii.practicetwo.practicethree;

public class Printer {
    public static void main(String[] args) {
        Print s = new SubPrint();
        s.print();
    }
}

abstract class Print{
     public abstract void print();
}
class SubPrint extends Print{
    private int i = 3;
    public void print() {
        System.out.println("Version is " + i);
    }
}
