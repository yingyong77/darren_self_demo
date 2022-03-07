package com.netty.demo;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author : darren
 * @date : 2022/3/6
 */
public class TestByteBufferString {

    public static void main(String[] args) {
        //1：字符串转成byteBuffer
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.put("darren".getBytes());

        //自动切换到读模式此时position为5 limit16 实际上是charBuffer转byteBuffer
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("darren");

        //wrap方法
        ByteBuffer buffer3 = ByteBuffer.wrap("darren".getBytes());

        //-----------byteBuffer转字符串-----------
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();

    }
}
