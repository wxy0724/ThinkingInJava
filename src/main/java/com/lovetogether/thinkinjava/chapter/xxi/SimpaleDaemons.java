package com.lovetogether.thinkinjava.chapter.xxi;

import java.util.concurrent.TimeUnit;

public class SimpaleDaemons implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + ": " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpaleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
//        try {
//            TimeUnit.MILLISECONDS.sleep(175);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
