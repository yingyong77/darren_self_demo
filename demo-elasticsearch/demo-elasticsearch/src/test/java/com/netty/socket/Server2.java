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
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : darren
 * @date : 2022/3/7
 */
@Slf4j
public class Server2 {
    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //2调用channel的方法 建立与selector的联系
        //selectionKey 事件发生后 得到事件是什么事件  还可以知道是哪个channel发生的事件
        //select管理员
        SelectionKey sscKey = serverSocketChannel.register(selector, 0, null);

        serverSocketChannel.bind(new InetSocketAddress(8145));

        //key只关注连接事件  专门关注accept事件的
        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        log.debug("register key{}:", sscKey);

        while (true) {
            //select方法 没有事件发生 线程阻塞，有事件 线程才会恢复运行
            //select 在事件未处理时，他不会阻塞，事件发生后要么处理，要么取消 不能置之不理
            selector.select();
            //处理事件，selectKeys包含了所有发生的事件
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove(); //处理key的时候 要从selectedKeys中删除
                log.debug("register key{}:", sscKey);
                //区分事件类型
                //可连接事件
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    //accept在非阻塞模式下返回的是null
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    //将byteBuffer管理到selectionKet上 生命周期跟selectionKey保持一致
                    ByteBuffer buffer = ByteBuffer.allocate(16); //附件attachment
                    SelectionKey socketSelectionKey = socketChannel.register(selector, 0, buffer);
                    socketSelectionKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", socketChannel);
                } else if (selectionKey.isReadable()) { //如果是read
                    try {
                        SocketChannel channel = (SocketChannel) selectionKey.channel(); //拿到可读事件的channel
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        int read = channel.read(buffer);
                        if (read == -1) {
                            selectionKey.cancel();
                            log.debug("客户端正常断开连接..");
                        } else {
//                            buffer.flip();
//                            ByteBufferUtil.debugRead(buffer1);
                            split(buffer);
                            if (buffer.position() == buffer.limit()) {
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                //数据拷贝
                                newBuffer.put(buffer.get());
                                buffer.flip();
                                selectionKey.attach(newBuffer);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        //因为客户端断开了，因此需要将key取消 从select selectorKeys集合中删除掉
                        selectionKey.cancel();
                    }
                }
            }
        }
    }

    /**
     * 解决粘包问题
     *
     * @param source
     */
    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                //把这条完整消息存入新的byteBuffer 每次读取的消息长度length-起始读取数据的位置
                ByteBuffer target = ByteBuffer.allocate(length);
                //从source读 向target写
                for (int j = 0; j < length; j++) {
                    byte b = source.get();
                    target.put(b);
                }
                ByteBufferUtil.debugRead(target);
                target.flip();
                System.out.println(StandardCharsets.UTF_8.decode(target));
            }
        }
        source.compact();
    }

}
