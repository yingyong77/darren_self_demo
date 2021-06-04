package com.darren.demo.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数类Random
 * 并发环境下  线程安全的随机数生成器
 * <p>
 * 种子seed是原子变量需要用到当前种子去计算新的种子，原子变量是CAS操作，，只会一个线程执行成功，但是大量的线程会进行自旋操作，这会降低并发性能
 * <p>
 * 可以通过ThreadLocalRandom 解决这个问题
 *
 * @author : darren
 * @date : 2020/6/2
 */
public class ThreadLocalRandomTest {

    public static void main(String[] args) {
        //创建一个有默认种子的随机生成器
        Random random = new Random();

        for (int i = 1; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

        for (int i = 1; i < 10; i++) {
            System.out.println(threadLocalRandom.nextInt(5));
        }


    }
}
