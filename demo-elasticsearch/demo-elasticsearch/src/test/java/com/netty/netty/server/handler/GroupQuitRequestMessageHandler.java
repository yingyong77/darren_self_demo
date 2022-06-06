package com.netty.netty.server.handler;

import com.netty.netty.message.GroupQuitRequestMessage;
import com.netty.netty.message.GroupQuitResponseMessage;
import com.netty.netty.server.session.GroupSessionFactory;
import com.netty.netty.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 退出群组
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class GroupQuitRequestMessageHandler extends SimpleChannelInboundHandler<GroupQuitRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupQuitRequestMessage msg) throws Exception {
        GroupSessionFactory.getGroupSession().removeMember(msg.getGroupName(), msg.getUsername());
        Channel channel = SessionFactory.getSession().getChannel(msg.getUsername());
        channel.writeAndFlush(new GroupQuitResponseMessage(true, "成功退出群组" + msg.getGroupName()));
    }
}
