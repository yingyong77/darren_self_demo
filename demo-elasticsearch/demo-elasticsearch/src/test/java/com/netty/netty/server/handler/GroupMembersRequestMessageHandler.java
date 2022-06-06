package com.netty.netty.server.handler;

import com.netty.netty.message.GroupMembersRequestMessage;
import com.netty.netty.message.GroupMembersResponseMessage;
import com.netty.netty.server.session.GroupSessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Set;

/**
 * 获取群组成员
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class GroupMembersRequestMessageHandler extends SimpleChannelInboundHandler<GroupMembersRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMembersRequestMessage msg) throws Exception {
        Set<String> members = GroupSessionFactory.getGroupSession().getMembers(msg.getGroupName());
        ctx.channel().writeAndFlush(new GroupMembersResponseMessage(members));
    }
}
