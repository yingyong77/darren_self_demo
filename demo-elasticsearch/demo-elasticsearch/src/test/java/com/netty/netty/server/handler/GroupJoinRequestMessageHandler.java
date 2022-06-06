package com.netty.netty.server.handler;

import com.netty.netty.message.GroupJoinRequestMessage;
import com.netty.netty.message.GroupJoinResponseMessage;
import com.netty.netty.server.session.GroupSessionFactory;
import com.netty.netty.server.session.SessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 加入群组
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class GroupJoinRequestMessageHandler extends SimpleChannelInboundHandler<GroupJoinRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupJoinRequestMessage msg) throws Exception {
        GroupSessionFactory.getGroupSession().joinMember(msg.getGroupName(), msg.getUsername());
        Channel channel = SessionFactory.getSession().getChannel(msg.getUsername());
        channel.writeAndFlush(new GroupJoinResponseMessage(true, "成功加入群组" + msg.getGroupName()));
    }
}
