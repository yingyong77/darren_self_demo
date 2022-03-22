package com.netty.netty;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author : darren
 * @date : 2022/3/22
 */
@Slf4j
public class TestJDKFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(1000);
                return 50;
            }
        });


        log.debug("等待结果");
        //主线程拿到结果
        System.out.println("结果是" + future.get());
    }
}
