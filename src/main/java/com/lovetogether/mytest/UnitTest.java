package com.lovetogether.mytest;


import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnitTest {

    @Test
    public void testSubList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            list.add(i + "");
        }
        System.out.println(list.subList(0, 9));
        System.out.println(list.subList(9, 18));
    }

    @Test
    public void testIsNum() {
        System.out.println(NumberUtils.isNumber("a"));
    }

    @Test
    public void testDate() {
        // 201901151000
        String date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyyMMddHHmm").parse("201901141618");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("20190115101100".compareTo("201901151000"));
        System.out.println(date);
    }

}
