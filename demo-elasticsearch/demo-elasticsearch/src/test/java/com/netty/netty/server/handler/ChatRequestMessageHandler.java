package com.netty.netty.server.handler;

import com.netty.netty.message.ChatRequestMessage;
import com.netty.netty.message.ChatResponseMessage;
import com.netty.netty.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 聊天消息处理
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class ChatRequestMessageHandler extends SimpleChannelInboundHandler<ChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ChatRequestMessage msg) throws Exception {
        //消息的目的地
        String to = msg.getTo();
        //根据接收者的用户名找到其对应的channel
        Channel channel = SessionFactory.getSession().getChannel(to);
        if (channel != null) {
            channel.writeAndFlush(new ChatResponseMessage(msg.getFrom(), msg.getContent()));
        } else {
            ctx.writeAndFlush(new ChatResponseMessage(false, "对方不在线"));
        }
    }
}
