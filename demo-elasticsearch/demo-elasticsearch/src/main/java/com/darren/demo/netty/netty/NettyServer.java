package com.darren.demo.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LoggingHandler;

/**
 * netty-server
 *
 * @author : darren
 * @date : 2021/11/15
 */
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();

        try {
            //创建服务端启动引导/辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //给引导类配置两大线程组,确定线程模型
            serverBootstrap.group(parentGroup, childGroup)
                    //打印日志(非必备)
                    .handler(new LoggingHandler())
                    //指定IO模型
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline channelPipeline = ch.pipeline();

                            channelPipeline.addLast(new StringDecoder());
                            channelPipeline.addLast(new StringDecoder());
                            //自定义客户端消息的业务处理逻辑
                            channelPipeline.addLast(new DemoSocketServerHandler());

                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            System.out.println("服务器已启动。。。");

            channelFuture.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }


    }

}
