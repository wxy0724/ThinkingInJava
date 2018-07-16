package com.fang.mytest;

import java.util.Arrays;
import java.util.List;

public class ArrayAsListNoUpdate {
    public static void main(String[] args) {
        String[] strs = new String[]{"AA", "BB", "CC"};
        List<String> list = Arrays.asList(strs);
        System.out.println(list.get(0));
        strs[0] = "aa";
        String s = list.get(0);
        System.out.println(s);
//        Exception in thread "main" java.lang.UnsupportedOperationException
//        list.add("DD");
//        System.out.println(list.size());
    }
}
