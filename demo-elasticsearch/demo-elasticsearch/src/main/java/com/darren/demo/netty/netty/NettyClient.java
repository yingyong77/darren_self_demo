package com.darren.demo.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author : darren
 * @date : 2021/11/15
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        try {

            bootstrap.group(eventLoopGroup).channel(NioServerSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {

                    ChannelPipeline channelPipeline = ch.pipeline();
                    channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    channelPipeline.addLast(new DemoSocketClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            if (eventLoopGroup != null) {
                eventLoopGroup.shutdownGracefully();
            }
        }

    }
}
