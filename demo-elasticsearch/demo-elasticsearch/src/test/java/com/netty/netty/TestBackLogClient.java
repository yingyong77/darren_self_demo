package com.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * @author : darren
 * @date : 2022/3/19
 */
public class TestBackLogClient {

    @SneakyThrows
    public static void main(String[] args) {
        //启动类
        new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                                  @Override
                                                  public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                      ctx.writeAndFlush(ctx.alloc().buffer().writeBytes("heelo".getBytes()));
                                                  }
                                              }
                        );
                    }
                })
                .connect(new InetSocketAddress("localhost", 8080))
                .sync()
                .channel();
    }
}
