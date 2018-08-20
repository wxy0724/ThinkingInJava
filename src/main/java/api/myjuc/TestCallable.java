package api.myjuc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建执行线程的方式三：实现Callable接口。
 * 相较于实现Runnable的方式，方法可以有返回值，并且可以抛出异常
 */
public class TestCallable {

    public static void main(String[] args) {
        ThreadDemo1 td = new ThreadDemo1();

        //执行Callable 方式，需要FutureTask实现类的支持，用于接收运算结果
        //FutureTsak是Future的实现类
        FutureTask<Integer> result = new FutureTask<>(td);
        new Thread(result).start();

        System.out.println("------------------------------");

        //接收线程运算后的结果
        Integer sum = null;
        try {
            //Future也可用于闭锁的操作
            sum = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(sum);

    }

}

class ThreadDemo1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i < 1000001; i++) {
            sum += i;
        }

        return sum;
    }
}

//class ThreadDemo1 implements Runnable {
//
//    public void run() {
//
//    }
//}