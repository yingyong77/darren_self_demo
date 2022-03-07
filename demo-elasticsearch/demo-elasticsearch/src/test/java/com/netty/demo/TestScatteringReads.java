package com.netty.demo;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * 分段读取
 *
 * @author : darren
 * @date : 2022/3/6
 */
public class TestScatteringReads {
    public static void main(String[] args) {
//        RandomAccessFile randomAccessFile = new RandomAccessFile("");
//        FileChannel fileChannel = randomAccessFile.getChannel();

        try {
            File file = ResourceUtils.getFile("classpath:Netty/word.txt");
            FileChannel channel = new FileInputStream(file).getChannel();
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{b1, b2, b3});
            b1.flip();
            b2.flip();
            b3.flip();

            System.out.println(StandardCharsets.UTF_8.decode(b1));
            System.out.println(StandardCharsets.UTF_8.decode(b2));
            System.out.println(StandardCharsets.UTF_8.decode(b3));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
