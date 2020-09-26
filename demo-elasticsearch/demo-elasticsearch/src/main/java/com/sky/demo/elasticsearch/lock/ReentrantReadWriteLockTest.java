package com.sky.demo.elasticsearch.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 采用读写分离的操作，多个线程可以同时获取读锁
 *
 * @author : darren
 * @date : 2020/6/9
 */
public class ReentrantReadWriteLockTest {

    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

}
