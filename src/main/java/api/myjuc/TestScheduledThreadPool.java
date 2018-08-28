package api.myjuc;


import java.util.Random;
import java.util.concurrent.*;

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
public class TestScheduledThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Future<Integer> result = pool.schedule(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);

            System.out.println(result.get());

        }

        pool.shutdown();

    }

}
