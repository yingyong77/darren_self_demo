package com.darren.demo.netty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author : darren
 * @date : 2021/11/15
 */
public class DemoSocketClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
        ctx.channel().writeAndFlush("from client: " + System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(5000);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
        ctx.channel().writeAndFlush("from client：begin talking");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
