package com.darren.demo.synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量计数器
 * a kind of Java同步器
 * 计数器内部是递增的
 * 一开始可以指定一个初始值  但不需要知道同步的线程个数，
 * 而是在需要同步的地方调用acquire方式时指定需要同步的线程个数
 *
 * @author : darren
 * @date : 2021/7/1
 * @see Semaphore
 */
public class SemaphoreTest {

    //    //创建一个semaphore实例
    private static Semaphore semaphore = new Semaphore(0);
//
//    public static void main(String[] args) {
//
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        try {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread() + "over");
//                    semaphore.release();
//                }
//            });
//
//            //
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread() + "over");
//                    semaphore.release();
//                }
//            });
//
//            //等待子线程执行完毕,返回
//            semaphore.acquire(2);
//            System.out.println("all child thread over!");
//
//            executorService.shutdown();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }


    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //将线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "A task over");
                semaphore.release();
            }
        });

        //将线程B添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "A task over");
                semaphore.release();
            }
        });
        //等待子线程执行任务A完毕,返回  返回后信号量的值变为0
        semaphore.acquire(2);

        //将线程C添加到线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + "B task over");
            semaphore.release();
        });
        //将线程d添加到线程池
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + "B task over");
            semaphore.release();
        });

        //等待子线程执行任务A完毕,返回
        semaphore.acquire(2);

        System.out.println("Task is over");

        executorService.shutdown();

    }

}
