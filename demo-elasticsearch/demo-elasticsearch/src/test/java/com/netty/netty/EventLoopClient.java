package com.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author : darren
 * @date : 2022/3/19
 */
@Slf4j
public class EventLoopClient {

    @SneakyThrows
    public static void main(String[] args) {
        //启动类
        ChannelFuture channelFuture = new Bootstrap()
                //添加eventLoop
                .group(new NioEventLoopGroup())
                //选择客户端的实现
                .channel(NioSocketChannel.class)
                //添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    //12 连接之后会被调用
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //15把hello 转为byteBuf
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                //11连接到服务器 异步非阻塞，main发起了调用，真正执行connect的是nio线程  得到执行结果的是主线程main
                .connect(new InetSocketAddress("localhost", 8080));   //1s后

//        channelFuture.sync(); //阻塞方法直到连接建立
//        Channel channel = channelFuture.channel();//代表连接对象
//        //向服务器发送数据
//        channel.writeAndFlush("hello darren");
        //使用addListener方法（回掉对象）,方法异步处理结果
        //在nio线程中执行完成之后 会调用
        channelFuture.addListener((ChannelFutureListener) future -> {
            Channel channel = future.channel();
            log.debug("{}", channel);
            channel.writeAndFlush("hello world");
        });

        System.out.println("");  //这里断点模式应该用thread
    }
}
