package com.sky.demo.elasticsearch.thread;

/**
 * 测试 thread interrupted
 * <p>
 * interrupt() 给线程设置中断标记
 * interrupted() 测试当前线程是否被中断 如果中断则清楚其中断状态
 * isInterrupted() 查看线程的中断状态
 *
 * @author : darren
 * @d 2020/5/29
 */
public class ThreadInInterrupted {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {

            int i = 0;

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("i--->" + i++);
            }

        });

        thread.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //设置中断标志
        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
