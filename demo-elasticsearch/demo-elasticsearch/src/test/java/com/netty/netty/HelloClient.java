package com.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * @author : darren
 * @date : 2022/3/19
 */
public class HelloClient {

    @SneakyThrows
    public static void main(String[] args) {
        //启动类
        new Bootstrap()
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
                //11连接到服务器
                .connect(new InetSocketAddress("localhost", 8080))
                .sync() //阻塞方法直到连接建立
                .channel() //代表连接对象
                //向服务器发送数据
                .writeAndFlush("hello darren");
    }
}
