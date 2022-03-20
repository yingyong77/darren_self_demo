package com.netty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * netty 服务端
 *
 * @author : darren
 * @date : 2022/3/19
 */
public class HelloServer {

    public static void main(String[] args) {
        //1:启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                //bossEventLoop，WorkerEventLoop（selector，thread）,group组
                .group(new NioEventLoopGroup()) //16由某个eventLoop处理read事件，接收到ByteBuf
                //选择服务器的serverSocketChannel实现
                .channel(NioServerSocketChannel.class)
                //boss负责处理连接worker（child）负责处理读写，决定了worker（child）能执行哪些操作
                .childHandler(
                        //channel 代表和客户端进行数据读写的通道，initializer初始化，负责添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            //12 连接建立之后 初始化方法
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                //17 将ByteBuf还原为字符串
                                ch.pipeline().addLast(new StringDecoder()); //将byteBuf 转化为字符串
                                //18 接受read方法 ，打印hello
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { //自定义的handler
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        //打印上一步转换好的字符串
                                        System.out.println(msg);
                                    }
                                });
                            }
                        }).bind(8080);
    }
}
