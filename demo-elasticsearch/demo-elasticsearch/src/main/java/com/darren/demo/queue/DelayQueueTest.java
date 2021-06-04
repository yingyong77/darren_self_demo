package com.darren.demo.queue;

import lombok.Data;

import java.util.PriorityQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 无阻塞延迟队列
 * 只有过期元素才会出队列
 * 对头是快要过期的额元素
 *
 * @author :darren
 * @date : 2020/9/28
 * @see PriorityQueue
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        DelayQueue delayQueue = new DelayQueue();
    }

    @Data
    static class DelayFile implements Delayed {

        //延迟时间
        private final long delayTime;

        //任务时间
        private final long expire;

        //任务名称
        private final long taskName;

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }
    }
}
