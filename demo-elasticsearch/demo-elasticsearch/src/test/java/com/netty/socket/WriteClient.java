package com.netty.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : darren
 * @date : 2022/3/9
 */
public class WriteClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));

        //接收数据
        int count = 0; //实际读到的字节数
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            count += socketChannel.read(buffer);
            System.out.println(count);
            buffer.clear();
        }
    }
}
