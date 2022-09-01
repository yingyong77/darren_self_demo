package com.jvm.byteCode;

/**
 * monitorenter (lock引用)
 * monitorenter
 * <p>
 * Synchronized 加载方法上不会有这个体现
 *
 * @author : darren
 * @date : 2022/8/17
 */
public class SynchronizedDemo1 {

    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock) {
            System.out.println("ok");
        }
    }
}
