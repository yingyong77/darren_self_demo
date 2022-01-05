package com.netty.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * twr  try-with-resource
 *
 * @author : darren
 * @date : 2022/1/4
 */
@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {
        //FileChannel
        //1：输入输出流   2:RandomAccessFile
        try {
            File file = ResourceUtils.getFile("classpath:Netty/data.txt");
            FileChannel fileChannel = new FileInputStream(file).getChannel();
            //划分一块内存作为缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                //从channel读取 向buffer写入
                int len = fileChannel.read(buffer);
                log.debug("读取到的字节数 {}", len);
                if (len == -1) {
                    break;
                }
                //打印buffer的内容
                buffer.flip(); //切换至读模式
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.debug("读取到的字节 {}", (char) b);
                }
                //切换为写模式
                buffer.clear();
            }


        } catch (IOException e) {
        }
    }

}
