package com.lovetogether.thinkinjava.chapter.xxi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPractice {
    private int num = 0;

    public void minus() {
        num--;
    }
    public void add() {
        num++;
    }


    public static void main(String[] args) {
        ThreadPractice practice = new ThreadPractice();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                practice.minus();
                System.out.println(Thread.currentThread().getName() + ": " + practice.num);
            });
        }
//        for (int i = 0; i < 100; i++) {
//            executorService.submit(() -> {
//                practice.add();
//                System.out.println(Thread.currentThread().getName() + ": " + practice.num);
//            });
//        }
    }
}
