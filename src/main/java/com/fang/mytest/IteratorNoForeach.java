package com.fang.mytest;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorNoForeach {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
//        while(iterator.hasNext()){
//            String item = iterator.next();
//            if ("2".equals(item)) {
//                iterator.remove();
//            }
//        }
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
