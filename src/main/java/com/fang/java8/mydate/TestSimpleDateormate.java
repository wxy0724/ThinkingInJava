package com.fang.java8.mydate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 错误实例，java.util.concurrent.ExecutionException: java.lang.NumberFormatException: For input string: ""
 * 传统的日期不支持多线程安全
 */
public class TestSimpleDateormate {

    /**
     * 1.8
     * @param args
     */
    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20161218", dtf);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future: results) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        pool.shutdown();
    }

    /**
     * 1.7
     */
//    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        Callable<Date> task = new Callable<Date>() {
//            @Override
//            public Date call() throws Exception {
////                return sdf.parse("20161218");
//                return DateFormatThreadLocal.convert("20161218");
//            }
//        };
//
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//        List<Future<Date>> results = new ArrayList<>(10);
//
//        for (int i = 0; i < 10; i++) {
//            results.add(pool.submit(task));
//        }
//
//        for (Future<Date> future: results) {
//            try {
//                System.out.println(future.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        pool.shutdown();
//    }

}
