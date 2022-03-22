package com.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @author : darren
 * @date : 2022/3/19
 */
@Slf4j
public class CloseFutureClient {

    @SneakyThrows
    public static void main(String[] args) {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        //启动类
        ChannelFuture channelFuture = new Bootstrap()
                //添加eventLoop
                .group(eventLoopGroup)
                //选择客户端的实现
                .channel(NioSocketChannel.class)
                //添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    //12 连接之后会被调用
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                //11连接到服务器 异步非阻塞，main发起了调用，真正执行connect的是nio线程  得到执行结果的是主线程main
                .connect(new InetSocketAddress("localhost", 8080));   //1s后
        Channel channel = channelFuture.sync().channel();
        log.debug("{}", channel);
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if ("q".equals(line)) {
                    channel.close(); //close异步操作1s之后
                    log.debug("处理关闭之后的操作"); //不能在这里善后
                    break;
                }
                channel.writeAndFlush(line);
            }
        }, "input").start();

        //获取closeFuture对象， 1同步关闭处理，2异步处理关闭
        ChannelFuture closeFuture = channel.closeFuture();
        // log.debug("waiting close....");
//        closeFuture.sync();
        //log.debug("关闭后的处理");
        closeFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                log.debug("关闭之后的操作。。");
                eventLoopGroup.shutdownGracefully();  //将eventLoopGroup组的
            }
        });
    }
}
