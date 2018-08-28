package api.myjuc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestProductorAndConsumerForLock {

    public static void main(String[] args) {
        ClerkForLock clerk = new ClerkForLock();

        ProductorForLock productor = new ProductorForLock(clerk);

        ConsumerForLock cus = new ConsumerForLock(clerk);

        new Thread(productor, "生产者A").start();
        new Thread(cus, "消费者B").start();
        new Thread(productor, "生产者C").start();
        new Thread(cus, "消费者D").start();
    }

}


//店员
class ClerkForLock {
    private int product = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //进货
    public void get() {//循环次数：2
        lock.lock();

        try{
            while (product >= 1) {
                System.out.println("产品已满！");

                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " : " + ++product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    //卖货
    public void sale() {//循环次数：1
        lock.lock();

        try {
            while (product <= 0) {//为了避免虚假唤醒问题，应该总是使用在循环中
                System.out.println("缺货");

                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " : " + --product);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

//生产者
class ProductorForLock implements Runnable {
    private ClerkForLock clerk;

    public ProductorForLock(ClerkForLock clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}

//消费者
class ConsumerForLock implements Runnable {
    private ClerkForLock clerk;

    public ConsumerForLock(ClerkForLock clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}