package com.jvm.driect_memory;

import com.darren.demo.threadconcurrency.UnsafeInstance;
import sun.misc.Unsafe;

import java.io.IOException;

/**
 * 直接内存的分配和释放 是通过unsafe类来管理的
 *
 * @author : darren
 * @date : 2022/2/13
 */
public class UnsafeAllocateMemory {

    static int _1Gb = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        Unsafe unsafe = UnsafeInstance.reflectUnsafe();
        //分配内存 返回内存地址
        long base = unsafe.allocateMemory(_1Gb);
        unsafe.setMemory(base, _1Gb, (byte) 0);
        System.in.read();

        //释放内存  主动调用
        unsafe.freeMemory(base);
        System.in.read();
    }
}
