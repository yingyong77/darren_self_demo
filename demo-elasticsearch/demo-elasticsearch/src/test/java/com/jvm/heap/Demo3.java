package com.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示查看对象个数
 * 堆转储 dump
 *
 * @author : darren
 * @date : 2022/2/10
 */
public class Demo3 {

    public static void main(String[] args) throws InterruptedException {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            studentList.add(new Student());
        }
        Thread.sleep(10000000000000L);
    }
}


class Student {
    private byte[] big = new byte[1024 * 1024];
}
