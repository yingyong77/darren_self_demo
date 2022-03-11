package com.darren.demo.synchronizer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 回环屏障案例类
 * 让一组线程
 * 全部达到一个状态之后 再全部同时执行
 *
 * @author : darren
 * @date : 2021/6/16
 */
public class CyclicBarrierTest {

    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + " task1 merge result");
        }
    });

    public static void main(String[] args) {

        //创建一个线程个数固定为2的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        //讲线程A添加到线程池
//        executorService.submit(() -> {
//            try {
//                System.out.println(Thread.currentThread() + " task1-1");
//
//                System.out.println(Thread.currentThread() + "enter in barrier");
//
//                cyclicBarrier.await();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread() + "enter out barrier");
//
//        });
//
//
//        //讲线程B添加到线程池
//        executorService.submit(() -> {
//            try {
//                System.out.println(Thread.currentThread() + " task1-2");
//
//                System.out.println(Thread.currentThread() + "enter in barrier");
//
//                cyclicBarrier.await();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread() + "enter out barrier");
//
//        });
//
//        //关闭线程池
//        executorService.shutdown();

        //eg:假设一个任务由阶段1,阶段2和阶段3组成,每个线程要串行的执行阶段1，阶段2，阶段3，当多个线程执行该任务时，必须要保证所有线程的阶段1全部完成之后才会进入阶段2执行，
        // 当所有线程的阶段2完成之后才会进入阶段3执行。


        //将线程A添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " step1-A");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " step2-A");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " step3-A");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //将线程B添加到线程池
        executorService.submit(() -> {
            try {
                System.out.println(Thread.currentThread() + " step1-B");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " step2-B");
                cyclicBarrier.await();

                System.out.println(Thread.currentThread() + " step3-B");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();

    }


}
