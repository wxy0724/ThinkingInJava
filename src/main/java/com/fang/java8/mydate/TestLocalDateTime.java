package com.fang.java8.mydate;

import org.junit.Test;

import java.time.*;

/**
 * JAVA8 时间
 * @author
 */
public class TestLocalDateTime {

    /**
     * 1、LocalDate   LocalTime   LocalDateTime
     */
    @Test
    public void test1(){
        // 2018-09-11
        LocalDate ld = LocalDate.now();
        // 16:07:31.061
        LocalTime lt = LocalTime.now();
        // 2018-09-11T16:07:31.061
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ld);
        System.out.println(lt);
        System.out.println(ldt);

        //指定时间
        LocalDateTime ldt2 = LocalDateTime.of(2015, 10, 19, 13, 22, 33);
        System.out.println(ldt2);

        //日期运算
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);
        System.out.println(ldt.minusMonths(2));
        int monthValue = ldt.getMonth().getValue();
        int month = ldt.getMonthValue();
        System.out.println("LocalDateTime.getMonth().getValue(): " + monthValue);
        System.out.println("LocalDateTime.getMonthValue(): " + month);

    }

    /**
     * 2、Instant:时间戳（以Unix元年：1970年1月1日 0时0分0秒到限制之间的毫秒值）
     */
    @Test
    public void test2(){
        /*默认获取的是以UTC时区，世界协调时间
        2018-08-12T09:59:32.386Z*/
        Instant instant1 = Instant.now();
        System.out.println(instant1);

        //2018-08-12T17:59:32.386+08:00
        OffsetDateTime offsetDateTime = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //1534067972386
        System.out.println(instant1.toEpochMilli());

        //1970-01-01T00:00:01Z
        //Instant的构造器，获得一个Instant实例
        Instant ins2 = Instant.ofEpochSecond(1);
        System.out.println(ins2);

        Instant ins3 = Instant.ofEpochMilli(instant1.toEpochMilli());
        System.out.println(ins3.toEpochMilli());
    }

    /**
     * 3、
     * Duration：计算两个“时间”之间的间隔
     * Period：计算两个“日期”之间的问题
     */
    @Test
    public void test3(){
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.getSeconds());
        System.out.println(duration.toMillis());


        LocalTime lt1 = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime lt2 = LocalTime.now();

        System.out.println(Duration.between(lt1, lt2).toMillis());
    }

    /**
     * Period：计算两个“日期”之间的问题
     */
    @Test
    public void test4(){
        LocalDate ld1 = LocalDate.of(2015, 1, 1);
        LocalDate ld2 = LocalDate.now();

        Period period = Period.between(ld1, ld2);
        //P3Y7M11D 3年7个月11天
        System.out.println(period);

        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
}
