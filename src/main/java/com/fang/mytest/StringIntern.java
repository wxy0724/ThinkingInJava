package com.fang.mytest;

import java.util.HashMap;

public class StringIntern {

    public static void main(String[] args) {
        String s3 = "abc".intern();
        String s1 = "abc";
        String s2 = new String("abc");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);

        HashMap<String, Integer> map = new HashMap();
        map.put("A", 1);
        map.put("B", 2);

        map.values();

    }

}
