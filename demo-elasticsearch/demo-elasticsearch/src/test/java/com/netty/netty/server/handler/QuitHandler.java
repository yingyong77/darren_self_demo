package com.netty.netty.server.handler;

import com.netty.netty.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理退出
 *
 * @author : darren
 * @date : 2022/6/3
 */
@Slf4j
@ChannelHandler.Sharable
public class QuitHandler extends ChannelInboundHandlerAdapter {

    //连接断开时-触发
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //移除
        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("{}已经断开", ctx.channel());
    }

    //异常捕捉
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SessionFactory.getSession().unbind(ctx.channel());
        log.debug("{} 已经异常断开 异常是{}", ctx.channel(), cause.getMessage());

    }
}
