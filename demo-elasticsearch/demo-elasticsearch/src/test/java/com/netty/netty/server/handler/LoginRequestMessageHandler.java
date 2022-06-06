package com.netty.netty.server.handler;

import com.netty.netty.message.LoginRequestMessage;
import com.netty.netty.message.LoginResponseMessage;
import com.netty.netty.server.service.UserServiceFactory;
import com.netty.netty.server.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 登录
 *
 * @author : darren
 * @date : 2022/6/3
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage msg) throws Exception {
        String username = msg.getUsername();
        String password = msg.getPassword();
        boolean login = UserServiceFactory.getUserService().login(username, password);
        LoginResponseMessage responseMessage;
        if (login) {
            SessionFactory.getSession().bind(ctx.channel(), username);
            responseMessage = new LoginResponseMessage(true, "登录成功");
        } else {
            responseMessage = new LoginResponseMessage(false, "用户名或密码不正确");
        }
        ctx.writeAndFlush(responseMessage);
    }
}
