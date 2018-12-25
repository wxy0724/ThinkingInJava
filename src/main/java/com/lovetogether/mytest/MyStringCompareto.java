package com.lovetogether.mytest;

public class MyStringCompareto {

    public static void main(String[] args) {
        String s = "123ABC";
        String a = "123abcd";
        String c = "你好吗？";
        String b = "4567890";
        System.out.println(s.compareTo(a));
        System.out.println(s.compareTo(c));
        System.out.println(s.compareTo(b));
    }

}
