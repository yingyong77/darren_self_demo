package com.jvm.heap;

/**
 * 演示堆内存
 *
 * @author : darren
 * @date : 2022/2/9
 */
public class Demo2 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000);
        byte[] bytes = new byte[1024 * 1024 * 10]; //10m内存
        System.out.println("2...");
        Thread.sleep(30000);
        bytes = null;   //表示不会再被引用了  可以被回收了
        System.gc();
        System.out.println("3...");
        Thread.sleep(1000000L);
    }

}
