package com.netty.netty.advance;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 定长解码器解决粘包半包问题
 *
 * @author : darren
 * @date : 2022/5/10
 */
public class FixedLengthFrameDecoderClient {

    static final Logger log = LoggerFactory.getLogger(FixedLengthFrameDecoderClient.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            send();
        }
        System.out.println("finish");
    }

    private static void send() {
        try {
            NioEventLoopGroup worker = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        //会在channel连接之后 触发active事件
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
