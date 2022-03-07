package com.netty.demo;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 网络传输 粘包半包现象
 * Hello,world\nI'm zhangsan\nHo  粘包
 * w are you?\n   半包
 * tcp每次发送的数据远小于缓冲区的大小 所以一般会出现粘包现象
 * 半包现象:缓冲区大小不能一次性读取这么多数据  分多次读取
 *
 * @author : darren
 * @date : 2022/3/7
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                //把这条完整消息存入新的byteBuffer 每次读取的消息长度length-起始读取数据的位置
                ByteBuffer target = ByteBuffer.allocate(length);
                //从source读 向target写
                for (int j = 0; j < length; j++) {
                    byte b = source.get();
                    target.put(b);
                }
                target.flip();
                System.out.println(StandardCharsets.UTF_8.decode(target));
            }
        }
        source.compact();
    }
}
