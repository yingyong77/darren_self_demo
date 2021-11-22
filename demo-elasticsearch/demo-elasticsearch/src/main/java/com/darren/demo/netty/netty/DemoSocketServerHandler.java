package com.darren.demo.netty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * Server 端 Handler
 *
 * @author : darren
 * @date : 2021/11/15
 */
public class DemoSocketServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client Address ==========" + ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush("from server:" + UUID.randomUUID());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
