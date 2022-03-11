package com.darren.demo.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Nio 使用
 *
 * @author : darren
 * @date : 2021/11/7
 */
public class Server implements Runnable {

    private Selector selector;

    private final ByteBuffer buffer = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            //打开多路复用器
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置服务器通道为非阻塞方式
            serverSocketChannel.configureBlocking(false);
            //绑定地址
            serverSocketChannel.bind(new InetSocketAddress(port));
            //把服务器通道注册到多路复用选择器上，并监听阻塞状态
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start,port:" + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                //必须让多路复用器开始监听
                selector.select();
                //返回所有已经注册到多路复用选择器上的通道的SelectionKey
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                //遍历key
                while (selectionKeyIterator.hasNext()) {
                    SelectionKey selectionKey = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    //如果key的状态是有效的
                    if (selectionKey.isValid()) {
                        //如果key的状态是阻塞状态，则调用accept()方法
                        if (selectionKey.isAcceptable()) {
                            accept(selectionKey);
                        }
                        //如果key的状态是可读状态，则调用read方法
                        if (selectionKey.isReadable()) {
                            read(selectionKey);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void accept(SelectionKey key) {
        try {
            //获取服务器通道
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            //执行阻塞方法
            SocketChannel socketChannel = serverSocketChannel.accept();
            //设置阻塞模式为非阻塞
            socketChannel.configureBlocking(false);
            //注册到多路复用器上,并设置读取标识
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("注册到了复用器上了");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void read(SelectionKey key) {
        try {
            buffer.clear();
            //获取之前注册的SocketChannel
            SocketChannel socketChannel = (SocketChannel) key.channel();
            //将socketChannel的数据放入buffer中
            int count = socketChannel.read(buffer);
            //== -1 表示通道中没有数据
            if (count == -1) {
                key.channel().close();
                key.cancel();
                return;
            }
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            //将buffer的数据写入到byte[]中
            buffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("Server:" + body);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Thread(new Server(8379)).start();
    }
}
