package com.darren.demo.utils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : darren
 * @date : 2021/8/4
 */
public class TimerTest {

    /**
     * 创建定时器对象
     */
    static Timer timer = new Timer();

    public static void main(String[] args) {
//        添加任务1,延迟500s执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("----task-one---");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("故意设计抛出的error");
            }
        }, 500);


        //        添加任务2,延迟1000s执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("----task-two---");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

    /**
     * better than timer
     */
    private void testScheduledPoolExecutor() {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        executor.schedule((Runnable) () -> {
            System.out.println("---one Task---");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            throw new RuntimeException("error ");
        }, 50, TimeUnit.MILLISECONDS);

        executor.shutdown();

    }
}
