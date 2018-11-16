package api.myjuc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：
 *      闭锁：在完成某些运算时，只有其他所有线程的运算全部完成，当前运算才能继续执行
 * @author xy
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(5);
        LatchDemo ld = new LatchDemo(latch);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(ld).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间为： " + (end - start));
    }

}


class LatchDemo implements Runnable{
    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        int time = 5000;

        synchronized (this){
            try{
                for (int i = 0; i < time; i++) {
                    if (i >> 1 == 0) {
                        System.out.println(i);
                    }
                }
            }finally {
                latch.countDown();
            }
        }
    }
}
