package com.netty.netty.server.handler;

import com.netty.netty.message.GroupChatRequestMessage;
import com.netty.netty.message.GroupChatResponseMessage;
import com.netty.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * 群组聊天
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class GroupChatRequestMessageHandler extends SimpleChannelInboundHandler<GroupChatRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupChatRequestMessage msg) throws Exception {

        List<Channel> channels = GroupSessionFactory.getGroupSession().getMembersChannel(msg.getGroupName());
        for (Channel channel : channels) {
            channel.writeAndFlush(new GroupChatResponseMessage(msg.getFrom(), msg.getContent()));
        }

    }
}
