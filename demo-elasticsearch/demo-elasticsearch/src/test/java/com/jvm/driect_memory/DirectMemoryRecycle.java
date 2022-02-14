package com.jvm.driect_memory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 直接内存垃圾回收
 * <p>
 * 禁用显式的垃圾回收
 * -XX:+DisableExplicitGC
 *
 * @author : darren
 * @date : 2022/2/13
 */
public class DirectMemoryRecycle {

    static int _1GB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        //在DirectByteBuffer构造方法中调用 unsafe的allocateMemory和setMemory两个方法
        ByteBuffer buffer = ByteBuffer.allocateDirect(_1GB);
        System.out.println("分配完毕");
        System.in.read();
        System.out.println("开始释放...");
        buffer = null;
        //最终调用的还是unsafe.freeMemory
        //显式的通过代码的方式 进行垃圾回收 Full Gc 比较影响性能的gc  回收新生代也回收老年代 暂停时间比较多
        System.gc();
    }
}
