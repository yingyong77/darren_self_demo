package com.darren.demo.operation;

import com.darren.demo.threadconcurrency.UnsafeInstance;
import lombok.Getter;
import sun.misc.Unsafe;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AQS同步器 test 自定义实现同步器
 *
 * @author : darren
 * @date : 2020/5/27
 * @see sun.misc.Unsafe
 * @see AbstractQueuedSynchronizer abstarct AQS
 * <p>CAS三板斧 核心骨架 1: CAS保证状态的修改 原子性 中间不中断 原子指令 单元电路 <>一个状态变量 2：自旋 一直 控制阻塞 不让其跳出逻辑之外 但不能一直空跑
 * 不然会严重的浪费CPU的资源 3：lockSupport park unpark
 */
public class TradeService {

    /**
     * 状态 维持共享变量可见性
     */
    @Getter
    private volatile int state = 0;

    private static final Unsafe unsafe = UnsafeInstance.reflectUnsafe();

    private static final long stateOffset;

    /**
     * 线程安全链表队列
     */
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue();

    /**
     * 重入锁
     */
    private ReentrantLock lock = new ReentrantLock(Boolean.TRUE);

    /**
     * 当前线程是谁
     */
    private Thread lockHodler;

    // 类加载时
    static {
        try {
            stateOffset = unsafe.objectFieldOffset(TradeService.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    /**
     * CAS 算法 原子操作
     *
     * @param expect
     * @param update
     * @return
     */
    public Boolean campareAndSwap(int expect, int update) {
        // 当前对象,内存位置偏移量,
        return Objects.requireNonNull(unsafe).compareAndSwapInt(this, stateOffset, expect, update);
    }

    /**
     * 减库存
     *
     * @return
     */
    public void desStockNoLock() {

        // lock.lock();

        Thread current = Thread.currentThread();

        // 此处加锁 进入不到下面的同步块
        for (; ; ) { // T1,T2，T3开始竞争 T1成功 其余失败 成功的可以进来
            // 自旋操作 先自旋进来 开始抢state这个资源

            int state = getState();

            if (state == 0) {
                // 原子操作保证只会有一个线程可以进入 互斥
                if (campareAndSwap(0, 1)) {
                    // 如果说线程1 加锁即成功
                    lockHodler = current;
                    // 此时加锁成功  具体逻辑
                    break;
                }
            }

            queue.add(current);
            // 被阻塞的线程必然会执行 在这个地方将线程阻塞住 此时全部阻塞住 节省CPU开销
            LockSupport.park();
            // T2,T3必然会阻塞
            // 需要一个数据结构,用来保存所有被阻塞的线程,在持有线程锁的线程中去唤醒,,
            // 很显然需要这样的一个队列,,先进先出FIFO  得到先唤醒锁的权利
            // 必然像synchronized一样唤醒所有的管程对象 所有阻塞停留在管程上的线程

        }

        // 业务逻辑。。。
        //        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from mccAccount
        // where deleted = 0");

        //        state不为0说明 一家有一个线程持有锁
        for (; ; ) {
            int state = getState();
            if (state != 0 && lockHodler == current) {
                Boolean canUnlock = campareAndSwap(state, 0);
                // 此时进行唤醒操作 进行unpark操作 此时需要一个数据结构保存阻塞的线程 此时我需要知道该唤醒哪个线程

                if (canUnlock) {
                    if (queue.size() > 0) {
                        Thread thread = queue.poll();
                        // 唤醒
                        LockSupport.unpark(thread);
                    }
                }
            }

            //            lock.unlock();

        }
    }
}
