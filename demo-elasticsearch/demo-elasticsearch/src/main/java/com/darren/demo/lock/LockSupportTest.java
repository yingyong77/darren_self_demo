package com.darren.demo.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : darren
 * @date : 2020/6/8
 */
public class LockSupportTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("child thread begin park!");

            while (!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
            }
            System.out.println("child thread unpark!");

        });

        thread.start();

        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        thread.interrupt();
    }


    private static void test1() throws InterruptedException {

        Thread thread = new Thread(() -> {

            System.out.println("child thread begin park!");
            //调用park方法,挂起自己
            LockSupport.park();
            System.out.println("child thread unpark!");

        });

        thread.start();
        Thread.sleep(1000);

        System.out.println("main thread begin unpark!");

        //让子线程持有许可证。
        LockSupport.unpark(thread);

    }
}
