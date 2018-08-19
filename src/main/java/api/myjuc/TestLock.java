package api.myjuc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  解决多线程安全：
 *  1、同步代码块：synchronized
 *  2、同步方法：synchronized
 *  jdk1.5以后
 *  3、同步锁Lock：显式锁，需要通过lock()方法进行上锁，通过unlock()方法释放锁
 *  存在风险，必须保证unlock()方法正常执行，所以通常将unlock()放入finally中
 */
public class TestLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "1号窗口").start();
        new Thread(ticket, "2号窗口").start();
        new Thread(ticket, "3号窗口").start();
    }

}

class Ticket implements Runnable{

    private int ticket = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true){
            //上锁
            lock.lock();

            try{
                if (ticket > 0){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticket);
                }
            }finally {
                //释放锁
                lock.unlock();
            }
        }

    }



    /*  线程不安全示例
    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticket);
            }
        }
    }*/
}