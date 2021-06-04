package com.darren.demo.thread;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * Description:符合阿里巴巴规范的线程池
 *
 * @author : Darren
 * @date : 2019-09-17
 */
public class ThreadPoolUtil {


    /**
     * newFixedThreadPool
     * 定长线程池：可控制线程最大并发数，超出的线程会在队列中等待 全核心线程
     * corePoolSize：核心线程池
     * maximumPoolSize：最大线程池大小
     * keepAliveTime 这个参数对核心线程池无效，而定长线程池全为核心线程
     * workQueue:无界阻塞队列  如果持续任务提交速度大于任务处理速度 会造成大量阻塞，因为队列很大，有可能再拒绝策略之前，内存溢出了。。
     * 无序执行
     *
     * <p>
     * 定长线程池的大小最好根据系统资源进行设置
     * Creates a thread pool that reuses a fixed number of threads
     * operating off a shared unbounded queue.  At any point, at most
     * {@code nThreads} threads will be active processing tasks.
     * If additional tasks are submitted when all threads are active,
     * they will wait in the queue until a thread is available.
     * If any thread terminates due to a failure during execution
     * prior to shutdown, a new one will take its place if needed to
     * execute subsequent tasks.  The threads in the pool will exist
     * until it is explicitly {@link ExecutorService#shutdown shutdown}.
     *
     * @param nThreads the number of threads in the pool
     * @return the newly created thread pool
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    static ThreadPoolExecutor getFixedThreadPool(int nThreads) {

        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(500), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 可缓存线程池
     * 无界线程池，自动线程回收
     * <p>
     * 适用场景：快速处理大量耗时较短的任务
     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
     * 耗时较短 线程可复用
     * <p>
     * newCachedThreadPool
     * <p>
     * corePoolSize = 0
     * maximumPoolSize = Integer.MAX_VALUE
     * keepAliveTime = 60
     * SynchronousQueue 同时只能存在一个线程,入队出对同时传递
     * 如果有则一直会重复使用 否则就直接创建。。
     *
     * @return
     */
    static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 延迟/周期执行线程池
     *
     * @param corePoolSize
     * @return
     */
    static ScheduledExecutorService newScheduledThreadPool(int corePoolSize) {
        return new ScheduledThreadPoolExecutor(corePoolSize);
    }


//    /**
//     * @return
//     */
//    public static ExecutorService newSingleThreadExecutor() {
//        return new FinalizableDelegatedExecutorService
//                (new ThreadPoolExecutor(1, 1,
//                        0L, TimeUnit.MILLISECONDS,
//                        new LinkedBlockingQueue<Runnable>()));
//    }


    @SneakyThrows
    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newFixedThreadPool(50);
        Executors.newScheduledThreadPool(100);

        cachedThreadPool.execute(() -> {
            System.out.print("...");
        });

        Future<String> future = cachedThreadPool.submit(() -> {
            System.out.print("...");
            return "dddd";
        });

        if (future.isCancelled()) {
            future.get();
        }
    }
}
