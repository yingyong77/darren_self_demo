package com.darren.demo.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * AQS
 *
 * @author : darren
 * @date : 2020/6/8
 * @see AbstractQueuedSynchronizer
 */
public class AbstractQueuedSynchronizerTest {

    //独占模式下
//    acquire 获取独占资源时 先调用tryAcquire 尝试获取锁,设置state变量 成功则返回，失败则封装成node对象Node.Exclusive 插入到AQS阻塞队列队尾并调用lockSupport.park(this)
//    release

    //共享方式下
}
