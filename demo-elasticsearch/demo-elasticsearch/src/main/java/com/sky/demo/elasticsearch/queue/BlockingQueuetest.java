package com.sky.demo.elasticsearch.queue;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 一个限流的例子
 *
 * @author : darren
 * @date : 2020/6/9
 * @see ArrayBlockingQueue, LinkedBlockingQueue
 */
@Component
@Log
public class BlockingQueuetest<T> {

//    ArrayBlockingQueue
//    LinkedBlockingQueue
//    PriorityBlockingQueue
//    DelayQueue
//    SynchronousQueue

    protected BlockingQueue<T> blockingQueue = new LinkedBlockingQueue<>();

    protected int batchCount = 500;

    /**
     * 增加消息到 内存缓冲区
     *
     * @param model message model
     */
    protected void addBlockingQueue(T model) {

        blockingQueue.add(model);

        if (blockingQueue.size() >= batchCount) {
            //
            insertAdsReportData();
        }

    }

    /**
     * 新增数据
     */
    private synchronized void insertAdsReportData() {

        HashSet<T> dataHashSet = new HashSet<>(blockingQueue.size() * 2);

        blockingQueue.drainTo(dataHashSet, batchCount);

        if (dataHashSet.size() <= 0) {
            return;
        }

        log.info("InsertDataQueueReceiver.insertData dataList.size:" + dataHashSet.size());

        //do some jdbc

    }


}
