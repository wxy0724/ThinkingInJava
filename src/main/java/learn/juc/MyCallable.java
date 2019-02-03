package learn.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("-----come in callable!!!");
        Thread.sleep(4000);
        return 200;
    }
}

public class MyCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask, "A").start();
        //Integer resultCall = futureTask.get();//如果计算尚未完成，则阻塞 get 方法


        System.out.println(Thread.currentThread().getName() + "*******main****");

        Integer resultCall = futureTask.get();
        System.out.println("*********result: " + resultCall);
    }

}
