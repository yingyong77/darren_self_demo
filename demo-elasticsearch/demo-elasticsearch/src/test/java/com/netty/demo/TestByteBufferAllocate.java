package com.netty.demo;

import java.nio.ByteBuffer;

/**
 * @author : darren
 * @date : 2022/1/5
 */
public class TestByteBufferAllocate {

    public static void main(String[] args) {
        //堆内存  java堆内存 读写效率低 收到GC的影响（标记清理 标记复制 标记整理）
        System.out.println(ByteBuffer.allocate(10).getClass());
        //直接内存 读写效率高（少一次拷贝 ）  不会收到gc的影响  分配效率低
        System.out.println(ByteBuffer.allocateDirect(10).getClass());
    }
}
