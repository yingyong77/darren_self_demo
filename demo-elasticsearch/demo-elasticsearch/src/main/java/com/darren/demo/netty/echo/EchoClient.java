package com.darren.demo.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author : darren
 * @date : 2021/9/9
 */
public class EchoClient {

    private final String host;

    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.out.println("Usage: " + EchoServer.class.getSimpleName() + "<host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[0]);
        new EchoClient(host, port).start();
    }

    /**
     *
     */
    public void start() throws InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        EchoClientHandler clientHandler = new EchoClientHandler();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(clientHandler);
                }
            });

            //连接到远程节点,阻塞等待直到连接完成
            ChannelFuture f = bootstrap.connect().sync();
            //阻塞，直到channel关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
