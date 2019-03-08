package com.lovetogether.java8.mydate;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
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
//        System.out.println(LocalDate.now().toString());
//        Date now = new Date();
//        System.out.println(now.toString());

        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(bf.format(date));

    }

    @Test
    public void test4() {
        if (getInteger() == null || getInteger() < 0) {
            System.out.println("错误");
        } else {
            System.out.println("success");
        }

    }

    @Test
    public void test5() {
        LocalDate localDate = LocalDate.parse("2019-03-01");
        String format = DateTimeFormatter.ofPattern("yyyy-MM").format(localDate);
        System.out.println(format);
        System.out.println(localDate.toString());
        System.out.println(localDate.getYear() + "-" + localDate.getMonthValue() + "-" + localDate.getDayOfMonth());
    }

    /**
     * LocalDate间隔
     */
    @Test
    public void test6() {
        LocalDate localDate1 = LocalDate.parse("2019-03-01");
        LocalDate localDate2 = LocalDate.parse("2018-03-01");
        // 不好用，只能用来计算年月日，localDate2 - localDate1，结果不好
//        Period between = Period.between(localDate1, localDate2);
        System.out.println(localDate2.toEpochDay() - localDate1.toEpochDay());
        System.out.println(localDate2.until(localDate1).getMonths());
        LocalDate localDate = localDate2.minusMonths(5);
        System.out.println(localDate.getYear() + "-" +localDate.getMonthValue() + "-" + localDate.getDayOfMonth());

    }

    public Integer getInteger() {
        return 5;
    }


}
