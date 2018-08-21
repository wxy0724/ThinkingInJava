package api.myjuc;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {

//    public static void main(String[] args) {
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(
//                0, Integer.MAX_VALUE,
//                60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());
//
//
//
//        long start = System.currentTimeMillis();
//        for (int i = 0; i <= 5; i++) {
//            pool.execute(new Job());
//        }
//
//        pool.shutdown();
//
//        // pool.awaitTermination(1, TimeUnit.SECONDS) 会每隔一秒钟检查一次是否执行完毕（状态为 TERMINATED）
//        // 当从 while 循环退出时就表明线程池已经完全终止了。
//        try {
//            while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
//                LOGGER.info("线程还在执行。。。");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long end = System.currentTimeMillis();
//        LOGGER.info("一共处理了【{}】", (end - start));
//    }

}
