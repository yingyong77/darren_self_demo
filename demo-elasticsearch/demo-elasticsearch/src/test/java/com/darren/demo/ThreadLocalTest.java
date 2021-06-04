package com.darren.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : darren
 * @date : 2020/9/28
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ThreadLocalTest {

    private static final ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    private static final ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    /**
     * 打印
     *
     * @param string
     */
    static void print(String string) {

        System.out.print(string + ":" + stringThreadLocal.get());

        stringThreadLocal.remove();
    }


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {

            //设置线程one中本地变量stringThreadLocal的值
            stringThreadLocal.set("thread one local");

            print("threadOne");

            System.out.print("thread remove after" + ":" + stringThreadLocal.get());

        });

        Thread thread2 = new Thread(() -> {

            stringThreadLocal.set("thread two local");

            print("threadTwo");

            System.out.print("threadTwo remove after" + ":" + stringThreadLocal.get());

        });

        thread.start();
        thread2.start();
    }

    /**
     * InheritableThreadLocal 作用
     * scene:子线程需使用父线程中的的用户信息等
     * 或者父线程传子线程一些数据之类
     */
    @Test
    public void testInheritableThreadLocal() {

        inheritableThreadLocal.set("hello world");

        Thread subThread = new Thread(() -> {

            //设置线程one中本地变量stringThreadLocal的值
            stringThreadLocal.set("thread one local");

            print("threadOne");

        });

        subThread.start();

        System.out.print("main thread" + ":" + stringThreadLocal.get());
    }
}
