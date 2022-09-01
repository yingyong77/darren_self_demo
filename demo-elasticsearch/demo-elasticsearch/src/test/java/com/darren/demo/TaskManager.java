package com.darren.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManager {

    private static final Logger logger = LoggerFactory.getLogger(TaskManager.class);

    private TaskManager() {
    }

    private static int POOLSIZE = 10;

    static {
        try {
            File file = ResourceUtils.getFile("classpath:prop_demo/appConfig.properties");
            InputStream inputStream = new FileInputStream(file);
            Properties props = new Properties();
            props.load(inputStream);
            POOLSIZE = (int) props.get("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建一个可重用固定线程数的线程池
     */
    private static ExecutorService executorService = new ThreadPoolExecutor(POOLSIZE, POOLSIZE, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new DefaultThreadFactory());


    static class DefaultThreadFactory implements ThreadFactory {
        static final AtomicInteger POOLNUMBER = new AtomicInteger(1);
        final ThreadGroup group;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = "gbl-pool-" + POOLNUMBER.getAndIncrement() + "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(this.group, r, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }


    public static void main(String[] args) {
        executorService.isShutdown();
    }

}
