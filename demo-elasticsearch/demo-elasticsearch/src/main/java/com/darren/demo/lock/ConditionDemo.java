package com.darren.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间的通信
 *
 * @author : darren
 * @date : 2021/8/26
 */
public class ConditionDemo {

    private static int shareDate = 0;
    private static boolean dataUsed = false;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private static void write() {
        lock.lock();
        try {
            while (!dataUsed) {
                condition.await();
            }
            //如果数据被使用了，修改数据，修改完之后标识位未使用
            TimeUnit.SECONDS.sleep(1);
            shareDate++;
            dataUsed = false;
            System.out.println("共享数据值被修改了。。" + shareDate);
            condition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    private static void read() {
        lock.lock();
        try {
            //如果当前值已经被使用
            while (dataUsed) {
                condition.await();
            }
            //如果数据被使用了，修改数据，修改完之后标识位未使用
            TimeUnit.SECONDS.sleep(1);
            System.out.println("线程读取到了共享数据:shareDate" + shareDate);
            dataUsed = true;
            condition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        System.out.println("condition---");
        new Thread(() -> {
            while (true) {
                write();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                read();
            }
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
