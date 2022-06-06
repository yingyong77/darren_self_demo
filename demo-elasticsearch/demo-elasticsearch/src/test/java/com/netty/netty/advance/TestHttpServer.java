package com.netty.netty.advance;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;

/**
 * @author : darren
 * @date : 2022/6/1
 */
@Slf4j
public class TestHttpServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                    ch.pipeline().addLast(new HttpServerCodec());
                   /* ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            log.error("{}", msg.getClass());
                            if (msg instanceof HttpRequest) {
                            } else if (msg instanceof HttpContent) {
                            }
                        }
                    });*/
                    ch.pipeline().addLast(new SimpleChannelInboundHandler<HttpRequest>() {

                        @Override
                        protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
                            //获取请求
                            log.debug(msg.uri());
                            //返回响应
                            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK);
                            byte[] bytes = "<h1>Hello,Darren!</h1>".getBytes();
                            //
                            defaultFullHttpResponse.headers().setInt(CONTENT_LENGTH, bytes.length);

                            defaultFullHttpResponse.content().writeBytes(bytes);
                            //写回响应
                            ctx.writeAndFlush(defaultFullHttpResponse);
                        }
                    });
                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("Server Error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
