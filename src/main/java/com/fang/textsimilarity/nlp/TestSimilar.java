package com.fang.textsimilarity.nlp;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.spell.JaroWinklerDistance;
import org.junit.Test;

import java.util.Locale;

public class TestSimilar {

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

    /**
     * Lucene提供的JaroWinklerDistance
     */
    @Test
    public void testJaroWinklerDistanceFromLucene(){
        JaroWinklerDistance jaro = new JaroWinklerDistance();
        String query = "北京市吉林街庄维花园7号楼";
        String term  = "吉林市北京街庄维花园7号楼";
        float f = jaro.getDistance(query, term);
        System.out.println(f);
    }

    /**
     * 修改t测试JaroWinklerDistance
     */
    @Test
    public void testMyJaroWinklerDistanceFromLucene(){
        MyExplainJaroWinklerDistance jaro = new MyExplainJaroWinklerDistance();
        String query = "北京市吉林街庄维花园7号楼";
        String term  = "吉林市北京街庄维花园7号楼";
        float f = jaro.getDistance(query, term);
        System.out.println(f);
    }

}
