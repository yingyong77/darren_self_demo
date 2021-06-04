package com.darren.demo.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入的独占锁。。
 * 分为公平锁与非公平锁
 *
 * @author : darren
 * @date : 2020/6/9
 */
public class ReenntrantLockTest {

    /**
     * 线程不安全的arrayList
     */
    private ArrayList<String> array = new ArrayList<>();

    /**
     * 独占锁
     */
    private volatile ReentrantLock lock = new ReentrantLock(Boolean.TRUE);

    public void add(String e) {
        lock.lock();
        try {
            array.add(e);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String e) {
        lock.lock();
        try {
            array.remove(e);
        } finally {
            lock.unlock();
        }
    }

    public void get(int index) {
        lock.lock();
        try {
            array.get(index);
        } finally {
            lock.unlock();
        }
    }
}
