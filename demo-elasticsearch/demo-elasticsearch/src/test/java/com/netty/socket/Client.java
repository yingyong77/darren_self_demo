package com.netty.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author : darren
 * @date : 2022/3/7
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8181));
        //测试粘包问题
        //socketChannel.write(Charset.defaultCharset().encode("hello\nworld\n"));
        //测试半包问题 服务器一次性读不了这么多数据 所以服务器每次扩容
        socketChannel.write(Charset.defaultCharset().encode("0123456789abcdef3333\n"));
        System.in.read();
    }
}
