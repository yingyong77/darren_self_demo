package com.netty.netty.server.handler;

import com.netty.netty.message.RpcRequestMessage;
import com.netty.netty.message.RpcResponseMessage;
import com.netty.netty.server.service.HelloService;
import com.netty.netty.server.service.ServicesFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author : darren
 * @date : 2022/6/5
 */
@ChannelHandler.Sharable
@Slf4j
public class RpcRequestMessageHandler extends SimpleChannelInboundHandler<RpcRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequestMessage requestMessage) throws Exception {
        RpcResponseMessage rpcResponseMessage = new RpcResponseMessage();
        rpcResponseMessage.setSequenceId(requestMessage.getSequenceId());
        try {
            HelloService service = (HelloService) ServicesFactory.getService(Class.forName(requestMessage.getInterfaceName()));
            Method declaredMethod = service.getClass().getDeclaredMethod(requestMessage.getMethodName(), requestMessage.getParameterTypes());
            Object result = declaredMethod.invoke(service, requestMessage.getParameterValue());
            rpcResponseMessage.setReturnValue(result);
            ctx.writeAndFlush(rpcResponseMessage);
        } catch (Exception e) {
            rpcResponseMessage.setExceptionValue(e);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RpcRequestMessage requestMessage = new RpcRequestMessage(1, "com.netty.netty.server.service.HelloService",
                "sayHello",
                String.class, new Class[]{String.class}, new Object[]{"Darren"});

        HelloService service = (HelloService) ServicesFactory.getService(Class.forName(requestMessage.getInterfaceName()));
        Method declaredMethod = service.getClass().getDeclaredMethod(requestMessage.getMethodName(), requestMessage.getParameterTypes());
        Object invoke = declaredMethod.invoke(service, requestMessage.getParameterValue());
        System.out.println(invoke);
    }
}
