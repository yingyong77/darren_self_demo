package com.netty.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author : darren
 * @date : 2022/3/6
 */
public class TestGatheringWrite {

    public static void main(String[] args) {
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("aaa");
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("bbb");
        ByteBuffer buffer3 = StandardCharsets.UTF_8.encode("哈哈"); //六字节

        try (FileChannel channel = new RandomAccessFile("words.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[]{buffer1, buffer2, buffer3});
        } catch (IOException e) {
        }
    }
}
