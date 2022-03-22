package com.netty.netty;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author : darren
 * @date : 2022/3/22
 */
@Slf4j
public class TestNettyPromise {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop eventLoop = new NioEventLoopGroup().next();

        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(() -> {
            //任意一个线程执行完毕，向promise填充结果
            log.debug("开始计算...");
            try {
                Thread.sleep(1000);
                int i = 1 / 0;
            } catch (InterruptedException e) {
                promise.setFailure(e);
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();

        //接收结果的线程
        //等待结果。。。
        log.debug("等待结果");
        //主线程拿到结果
        System.out.println("结果是" + promise.get());

    }
}
