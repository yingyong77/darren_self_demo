package com.netty.netty.server.handler;

import com.netty.netty.message.GroupCreateRequestMessage;
import com.netty.netty.message.GroupCreateResponseMessage;
import com.netty.netty.server.session.Group;
import com.netty.netty.server.session.GroupSession;
import com.netty.netty.server.session.GroupSessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;
import java.util.Set;

/**
 * 创建群组
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class GroupCreateRequestMessageHandler extends SimpleChannelInboundHandler<GroupCreateRequestMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupCreateRequestMessage msg) throws Exception {

        String groupName = msg.getGroupName();
        Set<String> members = msg.getMembers();
        GroupSession groupSession = GroupSessionFactory.getGroupSession();
        Group group = groupSession.createGroup(groupName, members);
        if (group == null) {
            //向群的创建者通知创建成功
            ctx.writeAndFlush(new GroupCreateResponseMessage(true, groupName + "创建成功"));
            //发送拉群消息
            List<Channel> membersChannel = groupSession.getMembersChannel(groupName);
            for (Channel channel : membersChannel) {
                channel.writeAndFlush(new GroupCreateResponseMessage(true, "您已被拉入" + groupName));
            }
        } else {
            ctx.writeAndFlush(new GroupCreateResponseMessage(false, groupName + "已经存在"));
        }

    }
}
