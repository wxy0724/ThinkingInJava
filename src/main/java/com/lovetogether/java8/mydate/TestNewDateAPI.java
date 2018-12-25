package com.lovetogether.java8.mydate;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * TemporalAdjust 时间校正器，例如：将日期调整到下个周日等操作
 * TemporalAdjusters 该类通过静态方法提供了大量的常用TemporalAdjuster的实现
 * @author
 */
public class TestNewDateAPI {

    @Test
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Europe/Vatican"));
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now();
        ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Europe/Vatican"));
        System.out.println(zdt);
    }

    /**
     * 带时区的时间日期API
     * ZonedDate、ZonedTime、ZonedDateTim
     */
    @Test
    public void test3(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    /**
     * DateTimeForMatter：格式化时间/日期
     */
    @Test
    public void test2(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();

        String format = ldt.format(dtf);
        System.out.println(format);

        System.out.println("-------------------------");
        //自定义
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String strDate2 = dtf2.format(ldt);
        System.out.println(strDate2);

        //解析回时间日期
        LocalDateTime parse = ldt.parse(strDate2, dtf2);
        System.out.println(parse);
    }


    /**
     * TemporalAdjust 时间校正器，例如：将日期调整到下个周日等操作
     * TemporalAdjusters 该类通过静态方法提供了大量的常用TemporalAdjuster的实现
     */
    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //指定需要日期
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);


        //自定义: 下一个工作日日期
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime)l;

            DayOfWeek dow = ldt4.getDayOfWeek();
            if (dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

}
