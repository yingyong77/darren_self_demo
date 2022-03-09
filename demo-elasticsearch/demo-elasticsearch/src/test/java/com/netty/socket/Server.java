package com.netty.socket;

import com.netty.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 非阻塞模式下
 *
 * @author : darren
 * @date : 2022/3/7
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(16);
        //创建服务器
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8145));

        List<SocketChannel> channels = new ArrayList<>();
        //accept
        while (true) {
            //accept建立与客户端直接的连接 socketChannel与客户端之间进行通信
            SocketChannel socketChannel = serverSocketChannel.accept();//阻塞方法，线程停止运行
            if (socketChannel != null) {
                log.debug("connected....");
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
            }
            for (SocketChannel channel : channels) {
//                log.debug("before read....{}", channel);
                //接收客户端发送的数据
                int read = channel.read(buffer); //阻塞方法 线程停止运行
                if (read > 0) {
                    buffer.flip();
                    ByteBufferUtil.debugRead(buffer);
                    buffer.clear();
                    log.debug("after read....{}", channel);
                }
            }
        }
    }

}
