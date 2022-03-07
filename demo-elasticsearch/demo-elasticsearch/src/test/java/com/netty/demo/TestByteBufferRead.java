package com.netty.demo;

import java.nio.ByteBuffer;

/**
 * position（指针） limit  capacity
 *
 * @author : darren
 * @date : 2022/3/6
 */
public class TestByteBufferRead {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd' });
        buffer.flip();
        //从头开始读
//        buffer.get(new byte[4]);
//        //将position设置为0
//        buffer.rewind();
//        buffer.get();

        //mark & reset
        //mark做一个标记，记录position的位置，reset是将position重置到mark的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.mark(); //加标记索引2的位置c的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.reset();  //将position重置到索引2
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());

        System.out.println((char) buffer.get());
    }
}
