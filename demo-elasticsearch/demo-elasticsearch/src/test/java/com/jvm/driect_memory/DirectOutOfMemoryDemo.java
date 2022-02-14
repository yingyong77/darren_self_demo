package com.jvm.driect_memory;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存溢出
 * <p>
 * java.lang.OutOfMemoryError: Direct buffer memory
 *
 * @author : darren
 * @date : 2022/2/13
 */
public class DirectOutOfMemoryDemo {

    static int _100Mb = 1024 * 1024 * 100;

    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(_100Mb);
                list.add(buffer);
                i++;
            }
        } finally {
            System.out.println(i);
        }
        //方法区是jvm规范，jdk6中对方法区的实现是永久代
        //jdk8 对方法区的实现是元空间
    }
}
