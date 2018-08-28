package api.myjuc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 1、ReadWriteLock
 *
 *  写写/读写 需要“互斥”
 *
 */

public class TestReadWriteLock {

    public static void main(String[] args) {

        ReadWriteLockDemp rwd = new ReadWriteLockDemp();

        new Thread(() -> rwd.set((int)(Math.random() * 101)), "Write").start();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> rwd.get()).start();
        }
    }

}

class ReadWriteLockDemp {

    private int number = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //读
    public void get() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        }finally {
            //释放锁
            lock.readLock().unlock();
        }
    }

    //写
    public void set(int number) {
        lock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        }finally {
            lock.writeLock().unlock();
        }
    }
}
