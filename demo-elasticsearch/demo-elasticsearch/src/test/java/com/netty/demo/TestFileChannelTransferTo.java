package com.netty.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 传输大文件的数据
 *
 * @author : darren
 * @date : 2022/3/7
 */
public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("data.txt").getChannel();
             FileChannel to = new FileOutputStream("to.txt").getChannel()
        ) {
            //效率高，底层会调用操作系统的零拷贝进行优化 2g 数据
            long size = from.size();
            //left变量表示还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position" + (size - left) + "left" + left);
                left -= from.transferTo(size - left, left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
