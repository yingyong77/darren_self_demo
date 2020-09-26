package com.sky.demo.elasticsearch.thread;

import java.util.concurrent.*;

/**
 * @author : darren
 * @date : 2020/6/12
 */
public class FutureTaskTest {

    public static void main(String[] args) {

//        通过labdan表达式去实现
        Callable<String> callable = () -> "ss";

        ExecutorService fixedThreadPool = ThreadPoolUtil.getFixedThreadPool(5);

//        Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
        Future<String> future = fixedThreadPool.submit(callable);

/*
    FutureTask实现了RunnableFuture接口
    可以看出RunnableFuture继承了Runnable接口和Future接口，而FutureTask实现了RunnableFuture接口。
    所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。
 */


//        1:使用Callable + Future获取结果  Executors 是一个工具类
        ExecutorService fixedThreadPool1 = Executors.newCachedThreadPool();
        Future<String> future1 = fixedThreadPool1.submit(callable);
        try {
            future1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        2:Callable + FutureTask获取执行结果
        FutureTask<String> futureTask = new FutureTask<>(callable);
        fixedThreadPool.submit(futureTask, 1);

        Future f = fixedThreadPool.submit(() -> {

            return 1;
        });

        futureTask.run();

//        3:用Thread
        Thread thread = new Thread(futureTask);
        thread.start();

    }


    class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }

    }
}
