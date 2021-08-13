package com.darren.demo.code.tomcat.nioEndPoint;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * <p>
 * BlockingQueue方法以四种形式出现,对于不能立即满足但可能在将来某一时刻可以满足的操作,
 * 这四种形式的处理方式不同:第一种是抛出一个异常,
 * 第二种是返回一个特殊值(零或假,具体取决于操作),
 * 第三种是在操作可以成功前,无限期地阻塞当前线程,
 * 第四种是在放弃前只在给定的最大时间限制内阻塞下表中总结了这些方法
 * <p/>
 * <p>
 * ArrayBlockingQueue是一个阻塞式的队列，继承自{@link java.util.AbstractQueue},
 * 间接的实现了Queue接口和Collection接口。
 * 底层以数组的形式保存数据(实际上可看作一个循环数组)。常用的操作包括 add，offer，put，remove，poll，take，peek。
 * <p>
 * 阻塞队列
 * 阻塞的意思是
 * 1:当队列是空的时从队列获取元素的操作会被阻塞
 * 2:当队列是满时,往队列中添加元素的操作会被阻塞
 * 面对生产者消费者模型时。。如果不适用阻塞队列那么就必须额外使用同步队列和线程唤醒策略。。
 *
 * @author : darren
 * @date : 2021/8/1
 */
public class ArrayBlockingQueueTest {

    private static ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(500);

    public static void main(String[] args) {
        /**
         * 把 e 加到 BlockingQueue 里，即如果 BlockingQueue 可以容纳，则返回 true，否则报异常
         */
        arrayBlockingQueue.add("aa");

        /**
         * 表示如果可能的话，将 e 加到 BlockingQueue 里，即如果 BlockingQueue 可以容纳，则返回 true，否则返回 false
         */
        arrayBlockingQueue.offer("bb");

        /**
         *把 e 加到 BlockingQueue 里，如果 BlockQueue 没有空间，则调用此方法的线程被阻断直到 BlockingQueue 里面有空间再继续
         */
        try {
            arrayBlockingQueue.put("cc");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         *取走 BlockingQueue 里排在首位的对象，若不能立即取出，则可以等 time 参数规定的时间,取不到时返回 null
         */
        arrayBlockingQueue.poll();

        /**
         *取走 BlockingQueue 里排在首位的对象，若 BlockingQueue 为空，阻断进入等待状态直到 Blocking 有新的对象被加入为止
         */
        try {
            arrayBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
