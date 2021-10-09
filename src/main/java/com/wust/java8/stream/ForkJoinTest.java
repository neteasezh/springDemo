package com.wust.java8.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.LongBinaryOperator;
import java.util.stream.LongStream;

/**
 * RecursiveTask有返回值
 * RecursiveAction无返回值
 */
public class ForkJoinTest extends RecursiveTask<Long> {
    private long start;
    private long end;
    public static final long THRESHOLD = 20000L;

    @Override
    protected Long compute() {
        if(end - start <= THRESHOLD){
            long sum = 0;
            for(long i = start;i <= end; i++){
                sum += i;
            }
            return sum;
        }else{
            long mid = (start + end) / 2;
            ForkJoinTest left = new ForkJoinTest(start, mid);
            //将子任务压入队列
            left.fork();
            ForkJoinTest right = new ForkJoinTest(mid + 1, end);
            right.fork();
            return left.join() + right.join();
        }
    }

    public ForkJoinTest(long start , long end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        ForkJoinTest task = new ForkJoinTest(0, 100000);
        ForkJoinPool pool = new ForkJoinPool();
        Instant start = Instant.now();
        Long res = pool.invoke(task);
        Instant end = Instant.now();
        System.out.println("the time spent is " + Duration.between(start,end).toMillis());
        System.out.println(res);

        //Java8并行流
        long r = LongStream.rangeClosed(0, 100000)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println(r);
    }
}
