package com.netty.netty.client;

import com.netty.netty.client.handler.RpcResponseMessageHandler;
import com.netty.netty.message.RpcRequestMessage;
import com.netty.netty.protocol.MessageCodecSharable;
import com.netty.netty.protocol.ProtocolFrameDecoder;
import com.netty.netty.protocol.SequenceIdGenerator;
import com.netty.netty.server.service.HelloService;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

@Slf4j
public class RpcClientManager {


    public static void main(String[] args) {
        HelloService helloService = getProxyService(HelloService.class);
        System.out.println(helloService.sayHello("Hi Darren!"));
        System.out.println(helloService.sayHello("Hi Darren!"));
    }

    private static Channel channel = null;

    private static final Object Lock = new Object();

    //创建代理类
    public static <T> T getProxyService(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[]{serviceClass}, (proxy, method, args) -> {
            //1.将方法的调用转换为RPCRequestMessage 消息对象
            //2.将消息对象发送出去
            int sequenceId = SequenceIdGenerator.nexId();
            RpcRequestMessage message = new RpcRequestMessage(
                    sequenceId,
                    serviceClass.getName(),
                    method.getName(),
                    method.getReturnType(),
                    method.getParameterTypes(),
                    args
            );
            getChannel().writeAndFlush(message);

            //准备一个空的 promise对象  指定promise对象异步接收结果的线程
            DefaultPromise<Object> promise = new DefaultPromise<>(getChannel().eventLoop());
            RpcResponseMessageHandler.PROMISES.put(sequenceId, promise);

            //等待promise的结果
            promise.await(); //结果成功或失败 不会抛异常
            if (promise.isSuccess()) {
                return promise.getNow();
            } else {
                throw new RuntimeException(promise.cause());
            }
        });
    }

    /**
     * 单例模式
     * 双重检查锁
     *
     * @return
     */
    private static Channel getChannel() {
        if (channel != null) {
            return channel;
        }
        synchronized (Lock) {
            if (channel == null) {
                initChannel();
            }
        }
        return channel;
    }

    //初始化channel
    private static void initChannel() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_CODEC = new MessageCodecSharable();
        RpcResponseMessageHandler RPC_HANDLER = new RpcResponseMessageHandler();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(group);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ProtocolFrameDecoder());
                    ch.pipeline().addLast(LOGGING_HANDLER);
                    ch.pipeline().addLast(MESSAGE_CODEC);
                    ch.pipeline().addLast(RPC_HANDLER);
                }
            });
            channel = bootstrap.connect("localhost", 8081).sync().channel();

            channel.closeFuture().addListener(future -> {
                group.shutdownGracefully();
            });
        } catch (Exception e) {
            log.error("client error", e);
        }
    }
}
