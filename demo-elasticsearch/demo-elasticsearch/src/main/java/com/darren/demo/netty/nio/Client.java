package com.darren.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : darren
 * @date : 2021/11/8
 */
public class Client {

    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8379);
        SocketChannel socketChannel = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //打开通道
            socketChannel = SocketChannel.open();
            //建立连接
            socketChannel.connect(inetSocketAddress);
            while (true) {
                byte[] bytes = new byte[1024];
                System.in.read(bytes);
                //把输入的数据放入buffer缓冲区中
                buffer.put(bytes);
                //复位操作。
                buffer.flip();
                //将buffer的数据写入通道中
                socketChannel.write(buffer);
                //清空缓冲区的数据
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socketChannel != null) {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
