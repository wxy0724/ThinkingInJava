package api.myjuc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  一、线程池
 *     提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁线程的一些额外开销，提高了响应速度
 *  二、线程池的体系结构
 *      java.util.concurrent.Executor:负责现成的使用与调度的根接口
 *          |--**ExecutorService  子接口：线程池的主要接口
 *              |--ThreadPoolExecutor  线程池的实现类
 *              |--SchedulaedExecutorService  子接口：负责线程的调度的子接口
 *                  |--ScheduledThreadPoolExcecutor  实现类：继承了ThreadPoolExecutor同时实现了SchedulaedExecutorService
 *  三、工具类：Executors
 *  ExecutorService newFixedThreadPool(): 创建固定大小的的线程池
 *  ExecutorService newCachedThreadPool(): 缓存线程池，线程吃的数量不固定，可以根据 需求 自动更新数量
 *  ExecutorService newSingleThreadExecutor： 创建单个线程池。线程池中只有一个线程
 *
 *  ScheduledExecutorService newScheduledThreadPook():  创建固定大小的线程，可以创建延迟或定时的执行任务
 *
 *
 */
public class TestThreadPool {


    /**
     * Callable
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<Integer>> list = new ArrayList<>(10);

        for (int j = 0; j < 10; j++) {
            Future<Integer> future = pool.submit(() -> {
               int sum = 0;

                for (int i = 0; i <= 100; i++) {
                    sum += i;
                }
                return sum;
            });
            list.add(future);
//            try {
//                Integer integer = future.get();
//                System.out.println(integer);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
        pool.shutdown();

        for (Future<Integer> future : list) {
            System.out.println(future.get());
        }
    }

    /**
     * Runnable
     * @param args
     */
   /* public static void main(String[] args) {

        //1、创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        ThreadPoolDemo tpd = new ThreadPoolDemo();

        //2、为线程池中的线程分配任务
        for (int i = 0; i < 10; i++) {
            pool.submit(tpd);
        }


        //3、关闭线程池
        pool.shutdown();//比较平和的关闭，等线程池中任务都执行完毕再关闭
//        pool.shutdownNow();//立即关闭

    }*/

    /*public static void main(String[] args) {
        ThreadPoolDemo tpd = new ThreadPoolDemo();

        //原始方式
        //new Thread(tpd).start();
        //new Thread(tpd).start();
    }*/

}


/**
 *  老办法
 */
class ThreadPoolDemo implements Runnable {
    private int i = 0;


    @Override
    public void run() {
        while (i <= 100) {
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}