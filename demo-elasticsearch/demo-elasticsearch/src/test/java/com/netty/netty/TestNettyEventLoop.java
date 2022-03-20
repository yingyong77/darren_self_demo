package com.netty.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author : darren
 * @date : 2022/3/20
 */
@Slf4j
public class TestNettyEventLoop {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(); //io事件 普通任务 定时任务
        //  EventLoopGroup eventLoopGroup = new DefaultEventLoopGroup(); //普通任务 定时任务
        //System.out.println(NettyRuntime.availableProcessors());
        //定时任务
        group.next().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.debug("Hello darren");
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

}
