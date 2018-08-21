package com.fang.textsimilarity.nlp;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Locale;

public class TeatChar {

    @Test
    public void test1(){
        String term = "你好";
        String query = "你";
        int dis = StringUtils.getFuzzyDistance(term, query, Locale.CHINA);
        System.out.println(dis);
    }

    /**
     * Jaro相似度：
     */
    @Test
    public void test2(){
        String query = "梦幻西游";
        String term = "梦幻诛仙";
        double dis = StringUtils.getJaroWinklerDistance(term.toLowerCase(), query.toLowerCase());
        System.out.println(dis);
    }

}
