package com.netty.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * 写服务端
 *
 * @author : darren
 * @date : 2022/3/9
 */
public class WriteServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(new InetSocketAddress(8080));
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    //这里可以确定这个对象就是当前key中的channel 这两个是同一个
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    //向客户端发送大量数据
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < 3000000; i++) {
                        stringBuilder.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(stringBuilder.toString());
                    //返回值代表实际写入的字节数
                    while (buffer.hasRemaining()) {
                        int write = socketChannel.write(buffer);
                        System.out.println("实际写入的字节数:" + write);
                    }
                }
            }
        }

    }
}
