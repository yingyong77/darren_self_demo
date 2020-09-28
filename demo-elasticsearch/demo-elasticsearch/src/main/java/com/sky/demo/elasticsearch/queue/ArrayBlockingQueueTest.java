package com.sky.demo.elasticsearch.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 * 阻塞的意思是
 * 1:当队列是空的时从队列获取元素的操作会被阻塞
 * 2:当队列是满时,往队列中添加元素的操作会被阻塞
 * 面对生产者消费者模型时。。如果不适用阻塞队列那么就必须额外使用同步队列和线程唤醒策略。。
 *
 * @author : darren
 * @date : 2020/6/9
 * @see ArrayBlockingQueue
 */
public class ArrayBlockingQueueTest {

    //单向链表
    BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(20);


}
