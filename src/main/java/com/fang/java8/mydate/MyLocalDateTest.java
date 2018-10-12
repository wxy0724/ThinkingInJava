package com.fang.java8.mydate;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Random;

public class MyLocalDateTest {

    /**
     *  LocalDate的常规操作
     */
    @Test
    public void test() {
        // 当前年月日
        LocalDate now = LocalDate.now();
        System.out.println(now);
        // 今天是周几
        int dayOfWeek = now.getDayOfWeek().getValue();
        System.out.println(dayOfWeek);
        // 今天是一年中的第几天
        int dayOfYear = now.getDayOfYear();
        System.out.println(dayOfYear);
        // 根据字符串转换成LocalDate
        LocalDate strToLocalDate = LocalDate.parse("2018-09-12");
        System.out.println(strToLocalDate.getYear() + "-" + strToLocalDate.getMonth().getValue() + "-" + strToLocalDate.getDayOfMonth());
        // 取本月的第一天
        LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth.getYear() + "-" + firstDayOfMonth.getMonth().getValue() + "-" + firstDayOfMonth.getDayOfMonth());
        // 取本月第二天
        LocalDate secondDayOfMonth = now.withDayOfMonth(2);
        System.out.println(secondDayOfMonth.getYear() + "-" + secondDayOfMonth.getMonth().getValue() + "-" + secondDayOfMonth.getDayOfMonth());
        // 取本月的最后一天
        LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDayOfMonth.getYear() + "-" + lastDayOfMonth.getMonth().getValue() + "-" + lastDayOfMonth.getDayOfMonth());
        // 取下一天
        LocalDate nextDay = lastDayOfMonth.plusDays(1);
        System.out.println(nextDay.getYear() + "-" + nextDay.getMonth().getValue() + "-" + nextDay.getDayOfMonth());
        // 取2017年1月第一个周一
        LocalDate firstMondayOf2017 = LocalDate.parse("2017-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstMondayOf2017.getYear() + "-" + firstMondayOf2017.getMonth().getValue() + "-" + firstMondayOf2017.getDayOfMonth());
    }

    /**
     *  LocalDate的格式化操作
     */
    @Test
    public void test2() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String strDate = dtf.format(now);
        System.out.println(strDate);
    }

    @Test
    public void test3() {
        System.out.println(LocalDate.now().toString());
        Date now = new Date();
        System.out.println(now.toString());
    }
}
