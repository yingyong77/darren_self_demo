package com.netty.socket;

import com.netty.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : darren
 * @date : 2022/3/7
 */
@Slf4j
public class Server2 {
    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //2调用channel的方法 建立与selector的联系
        //selectionKey 事件发生后 得到事件是什么事件  还可以知道是哪个channel发生的事件
        //select管理员
        SelectionKey sscKey = serverSocketChannel.register(selector, 0, null);

        serverSocketChannel.bind(new InetSocketAddress(8145));

        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            }
            for (SocketChannel channel : channels) {
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    ByteBufferUtil.debugRead(buffer);
                    buffer.clear();
                }
            }
        }
    }

}
