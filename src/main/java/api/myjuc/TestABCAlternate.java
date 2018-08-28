package api.myjuc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 编写一个程序，开启3个线程，这3个现成的ID分别为A, B, C，
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出的记过必须按顺序显式。
 * 如：ABCABCABC…… 依次递归
 */
public class TestABCAlternate {

    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();

        new Thread(() -> {

            for (int i = 0; i <= 20; i++) {
                ad.loopA(i);
            }

        }, "A").start();

        new Thread(() -> {

            for (int i = 0; i <= 20; i++) {
                ad.loopB(i);
            }

        }, "B").start();

        new Thread(() -> {

            for (int i = 0; i <= 20; i++) {
                ad.loopC(i);

                System.out.println("----------------------");
            }

        }, "C").start();
    }

}

class AlternateDemo {

    //表示当前正在执行线程的标记
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /**
     *
     * @param totalLoop 循环几轮
     */
    public void loopA(int totalLoop) {
        lock.lock();

        try {
            //1、判断
            if (number != 1) {
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2、打印
            for (int i = 0; i <= 5; i++) {
                 System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
             }

            //3、唤醒
            number = 2;
            condition2.signal();

        }finally {
            lock.unlock();
        }
    }


    public void loopB(int totalLoop) {
        lock.lock();

        try {
            //1、判断
            if (number != 2) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2、打印
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3、唤醒
            number = 3;
            condition3.signal();

        }finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLoop) {
        lock.lock();

        try {
            //1、判断
            if (number != 3) {
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //2、打印
            for (int i = 0; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3、唤醒
            number = 1;
            condition1.signal();

        }finally {
            lock.unlock();
        }
    }


}
