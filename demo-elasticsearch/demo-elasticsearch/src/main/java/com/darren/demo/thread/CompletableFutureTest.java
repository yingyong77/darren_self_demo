package com.darren.demo.thread;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

/**
 * @author : darren
 * @date : 2021/8/14
 */
public class CompletableFutureTest {

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    @Test
    public void test1() {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "start,Time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("test---");
            } else {
                System.out.println(Thread.currentThread() + "exit,Time->" + System.currentTimeMillis());
                return new Random().nextDouble();
            }
        });
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @SneakyThrows
    @Test
    public void test2() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + "start,Time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("test---");
            } else {
                System.out.println(Thread.currentThread() + "exit,Time->" + System.currentTimeMillis());
            }
        });
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @SneakyThrows
    @Test
    public void test3() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "start,Time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("test---");
            } else {
                System.out.println(Thread.currentThread() + "exit,Time->" + System.currentTimeMillis());
            }
            return new Random().nextInt();
        }, forkJoinPool);
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * thenApply / thenApplyAsync
     * 区别在于前者使用的是同一个线程  后者是两个线程
     * 某个任务执行完成后的执行动作，即回调方法，会将该任务的执行结果作为参数加入到回调方法的参数中
     */
    @SneakyThrows
    public void test4() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "start,Time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("test---");
            } else {
                System.out.println(Thread.currentThread() + "exit,Time->" + System.currentTimeMillis());
            }
            return new Random().nextInt();
        }, forkJoinPool);

        CompletableFuture<String> completableFuture = cf.thenApply((result) -> {
            System.out.println(Thread.currentThread() + "start,job1,Time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "start,job2,Time->" + System.currentTimeMillis());
            return "test:" + result;
        });
    }

    /**
     * thenAccept
     * thenRun
     */
    public void test5() {
    }

    /**
     * exceptionally
     */
    public void test6() {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "start,Time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("test---");
            } else {
                System.out.println(Thread.currentThread() + "exit,Time->" + System.currentTimeMillis());
            }
            return new Random().nextInt();
        }, forkJoinPool);

        CompletableFuture<Integer> cf2 = cf.exceptionally((param) -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("error stack trace->");
            param.printStackTrace();
            System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
            return new Random().nextInt();
        });

        CompletableFuture<Void> cf3 = cf2.thenAccept((param) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("param->" + param);
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
        });

        // cf将执行完成后的结果和执行过程中抛出的异常传入回调方法中，如果是正常执行的则传入的异常为null
        CompletableFuture<Integer> cf4 = cf.whenComplete((result, exception) -> {

            //如果是正常执行的返回result 如果是异常执行的则抛出异常
        });

    }
}
