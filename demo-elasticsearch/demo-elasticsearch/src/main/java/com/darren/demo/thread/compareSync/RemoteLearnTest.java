package com.darren.demo.thread.compareSync;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author : darren
 * @date : 2021/8/28
 */
public class RemoteLearnTest {

    /**
     * 同步执行的效果
     * 因为两个接口都是延迟1s,结果大于2s。
     */
    @Test
    public void testSync() {
        long start = System.currentTimeMillis();
        Stream<RemoteLoader> remoteLoaders = Stream.<RemoteLoader>builder().add(new CustomerInfoServiceImpl())
                .add(new LearnRecordServiceImpl()).build();
        List<String> customerDetails = remoteLoaders.map(RemoteLoader::load).collect(toList());
        System.out.println(customerDetails);
        long end = System.currentTimeMillis();
        System.out.println("执行总共花费的时间" + (end - start));
    }


    /**
     * 虽然future实现了效果，但是将两个异步的结果进行合并处理就稍显麻烦。
     */
    @SneakyThrows
    @Test
    public void testWithFuture() {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        Stream<RemoteLoader> loaderStreams = Stream.of(new CustomerInfoServiceImpl(), new LearnRecordServiceImpl());
        List<Future<String>> futures = loaderStreams.map(x -> {
            return executorService.submit(() -> {
                String result = x.load();
                //System.out.println("已经执行完了---" + result);
                int tt = 10 / 0;
                return result;
            });
        }).collect(toList());

        //下面这么做的方法其实是对主线程的一种限定，，为了统计执行消耗时间的。。。
        List<String> customerDetails = futures.stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull).collect(toList());

        System.out.println(customerDetails);
        long end = System.currentTimeMillis();
        System.out.println("future执行总共花费的时间" + (end - start));

        Thread.sleep(2000);
    }

    /**
     * 并行流。。
     */
    @Test
    public void testParallelStream() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> loaderList = Arrays.asList(new CustomerInfoServiceImpl(), new LearnRecordServiceImpl());
        List<String> customerDetails = loaderList.parallelStream().map(RemoteLoader::load).collect(toList());
        System.out.println(customerDetails);
        long end = System.currentTimeMillis();
        System.out.println("并行流parallel方式执行总共花费的时间" + (end - start));
    }

    /**
     * 并行流测试2。。
     */
    @Test
    public void testParallelStream2() {
        long start = System.currentTimeMillis();
        List<RemoteLoader> loaderList = Arrays.asList(new CustomerInfoServiceImpl(), new LearnRecordServiceImpl(), new WatchRecordServiceImpl()
                , new OrderRecordServiceImpl(), new LabelServiceImpl());
        List<String> customerDetails = loaderList.parallelStream().map(RemoteLoader::load).collect(toList());
        System.out.println(customerDetails);
        long end = System.currentTimeMillis();
        System.out.println("并行流parallel2方式执行总共花费的时间" + (end - start));
    }

    /**
     * 衍生的基本用法。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                future.complete("Finish");
                System.out.println("doSomething...");
                throw new RuntimeException("Test Exception");
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();
        System.out.println(future.get());
    }


    @Test
    public void testCompletableFuture4() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<RemoteLoader> remoteLoaders = Arrays.asList(
                new CustomerInfoServiceImpl(), new LearnRecordServiceImpl(), new WatchRecordServiceImpl()
                , new OrderRecordServiceImpl(), new LabelServiceImpl());

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(remoteLoaders.size(), 50));

        List<CompletableFuture<String>> completableFutures = remoteLoaders
                .stream()
                .map(loader -> CompletableFuture.supplyAsync(loader::load, executorService))
                .collect(toList());

        List<String> customerDetail = completableFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(toList());

        System.out.println(customerDetail);
        long end = System.currentTimeMillis();
        System.out.println("总共花费时间:" + (end - start));
    }

    /**
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testCompletableFuture2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> {
                    //----
                    int aa = 10 / 0;
                    return "Finish";
                })
                .exceptionally(throwable -> "Throwable exception message:" + throwable.getMessage());
        System.out.println(future.get());
    }


}
