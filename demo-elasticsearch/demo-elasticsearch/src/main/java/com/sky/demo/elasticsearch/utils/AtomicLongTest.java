package com.sky.demo.elasticsearch.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子性操作类 atomicLong
 * AtomicLong中得unsafe类可以通过unsafe的静态方法直接获取，是因为atomicLong类也是在rt.jar包下面的
 * 因此atomicLong就是通过Bootstarp类加载的
 * <p>
 * <p>
 * 都是CAS非阻塞算法
 * 对性能损耗较少
 *
 * @author : darren
 * @date : 2020/6/2
 */
public class AtomicLongTest {

    public static void main(String[] args) {

        AtomicLong atomicLong = new AtomicLong(1);

        atomicLong.incrementAndGet();

        atomicLong.getAndIncrement();

        atomicLong.decrementAndGet();

        atomicLong.getAndDecrement();

        atomicLong.compareAndSet(1, 2);

    }


}
