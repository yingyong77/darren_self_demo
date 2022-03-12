package com.netty.socket;

import com.netty.utils.ByteBufferUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : darren
 * @date : 2022/3/12
 */
@Slf4j
public class MultiThreadServer {

    /**
     * 只负责建立连接
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Thread.currentThread().setName("boss");
        Selector boss = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        SelectionKey sscKey = serverSocketChannel.register(boss, 0, null);
        serverSocketChannel.bind(new InetSocketAddress(8181));
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        //创建固定数量的worker
        int coreSize = Runtime.getRuntime().availableProcessors();  //在docker容器下是有问题的 jdk10修复 拿到的是物理机的核数不是实际分给docker的核数
        log.info("当前cpu核数为，{}", coreSize);
        Worker[] workers = new Worker[coreSize]; //建议最好是cpu的核心数
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker("worker-" + i);
        }
        AtomicInteger index = new AtomicInteger();
        while (true) {
            boss.select();
            Iterator<SelectionKey> iterator = boss.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    log.debug("connected...{}", sc.getRemoteAddress());
                    log.debug("before register...{}", sc.getRemoteAddress());
                    //2.管理selector     //register必须在前
                    //round robin 轮询的效果
                    workers[index.getAndIncrement() % workers.length].register(sc);

                    log.debug("after register...{}", sc.getRemoteAddress());
                }

            }
        }
    }

    /**
     * CPU调度执行
     */
    static class Worker implements Runnable {
        private Thread thread;
        private Selector selector;
        private String name;
        private volatile boolean start = false;   //还未初始化
        private ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue(); //在两个线程之间通信用的

        public Worker(String name) {
            this.name = name;
        }

        /**
         * netty的做法
         */
        @SneakyThrows({IOException.class})
        public void register(SocketChannel sc) {
            if (!start) {
                this.thread = new Thread(this, name);
                thread.start();
                selector = Selector.open();
                start = true;
            }
            //向队列添加任务
            queue.add(() -> {
                try {
                    sc.register(selector, SelectionKey.OP_READ, null); //执行了sc的注册读事件
                } catch (ClosedChannelException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup(); //唤醒阻塞
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                selector.select();
                Runnable task = queue.poll();
                if (task != null) {
                    task.run();
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(16);
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        log.debug("before register...{}", socketChannel.getRemoteAddress());
                        socketChannel.read(buffer);
                        buffer.flip();
                        ByteBufferUtil.debugAll(buffer);
                    }
                }
            }
        }

    }
}
