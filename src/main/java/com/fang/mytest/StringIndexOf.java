package com.fang.mytest;

public class StringIndexOf {
    public static void main(String[] args) {
        String city = "北京市";
        String district = "丰台区";
        String temp = "北京市丰台区北京方向XXX小区";

        System.out.println("test:" + city.substring(0, city.indexOf('北')));
        if(temp.indexOf(city) == 0){
            temp = temp.substring(city.length());
        }
        System.out.println(temp);
        if(temp.indexOf("区北京") == 0){
            temp = temp.substring(city.length());
        }
        System.out.println(temp);
        if(temp.indexOf(district) == 0){
            temp = temp.substring(city.length());
        }
        System.out.println(temp);

        System.out.println("别墅".indexOf("别墅住宅楼小区"));

    }
}
