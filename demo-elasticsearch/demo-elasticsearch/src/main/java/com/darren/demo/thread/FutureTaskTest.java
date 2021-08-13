package com.darren.demo.thread;

import java.util.concurrent.*;

/**
 * @author : darren
 * @date : 2020/6/12
 */
public class FutureTaskTest {

    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        //-----------------使用Callable-------------------
        //通过labdan表达式去实现
        Callable<String> callable = () -> "ss";
        ExecutorService fixedThreadPool = ThreadPoolUtil.getFixedThreadPool(5);
        //Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
        Future<String> future = fixedThreadPool.submit(callable);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //-----------------使用FutureTask-------------------
        /*
            FutureTask实现了RunnableFuture接口
            可以看出RunnableFuture继承了Runnable接口和Future接口，而FutureTask实现了RunnableFuture接口。
            所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。
        */
        //1:使用Callable + Future获取结果  Executors 是一个工具类 获取ExecutorService
        FutureTask<String> futureTaskOfCallable = new FutureTask<>(callable);
        FutureTask<String> futureTaskOfRunnable = new FutureTask<>(() -> {
        }, "1");

        //注意:此时会遇到一个问题 并没有返回值 因为直接新建了一个新的FutureTask  value1为空
        fixedThreadPool.submit(futureTaskOfCallable);
        try {
            String value1 = futureTaskOfCallable.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        String value = "";
        Future<String> future1 = fixedThreadPool.submit(() -> {
        }, "ttttt");
        try {
            value = future1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("futureTaskOfCallable--->" + value);
        //当作一个
        fixedThreadPool.submit(futureTaskOfCallable, 1);

        //2:可以直接使用因为实现了Runnable 接口
        futureTaskOfCallable.run();

        try {
            String v = futureTaskOfCallable.get();
            System.out.println("futureTaskOfCallable:run-->" + v);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //3:直接配合Thread使用  因为FutureTask 本身也是个runnable
        Thread thread = new Thread(futureTaskOfCallable);
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


    /**
     * 当一个线程需要等待另一个线程把某个任务执行完后它才能继续执行，此时可以使用FutureTask
     * 假设有多个线程执行若干任务，每个任务最多只能被执行一次。当多个线程试图同时执行同一个任务时，只允许一个线程执行任务，其他线程需要等待这个任务执行完后才能继续执行
     *
     * @param taskName
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @date 2021-06-03
     */
    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = () -> taskName;
                FutureTask<String> futureTask = new FutureTask<>(task);
                //原子性的操作 线程安全
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }
}
