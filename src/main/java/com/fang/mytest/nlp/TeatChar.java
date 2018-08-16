package com.fang.mytest;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Locale;

public class TeatChar {

    @Test
    public void test1(){
        String term = "您好，我是XXX";
        String query = "您好";
        int dis = StringUtils.getFuzzyDistance(term, query, Locale.CHINA);
        System.out.println(dis);
    }

}
