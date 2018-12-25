package com.lovetogether.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 想使用forkjoin必须继承RecursiveAction（无返回值）RecursiveAction（有返回值）
    @author wangxiyao
 */
public class ForkJoinCalculate extends RecursiveTask<Long>{

    private long start;
    private long end;

    public ForkJoinCalculate(long start, long end){
        this.start = start;
        this.end = end;
    }

    /**
     * 停止拆分的临界值
     */
    private static final long THRESHOLD = 10000;

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD){
            long sum = 0L;
            for (long i = start; i <= end ; i++) {
                sum += i;
            }
            return sum;
        }else{
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            //拆分子任务，同时压入线程队列
            left.fork();

            ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
