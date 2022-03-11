package com.jvm.stack;

/**
 * 死锁
 *
 * @author : darren
 * @date : 2022/2/9
 */
class A {
}

class B {
}

public class Demo4 {

    static A a = new A();
    static B b = new B();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (a) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("我获得了a和b");
                }
            }
        });

        new Thread(() -> {
            synchronized (b) {
                synchronized (a) {
                    System.out.println("我获得了a和b");
                }
            }
        });
    }
}
