package com.fang.mytest;

public class MyStringSplite {

    public static void main(String[] args) {
        String str = "哈达";
        String[] split = str.split(",");
        for (String s : split) {
            System.out.println(s);
        }
        String str2 = "哈达|AAA|CCC";
        String str3 = str2.substring(str2.indexOf("|") + 1);
        System.out.println(str3.substring(0, str3.indexOf("|")));
        System.out.println(".$|()[{^?*+\\".indexOf("|") == -1);
    }

}
