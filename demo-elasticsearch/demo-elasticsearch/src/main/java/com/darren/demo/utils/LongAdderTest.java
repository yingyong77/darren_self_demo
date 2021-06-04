package com.darren.demo.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * atomicLong通过CAS 提供了非阻塞的原子操作,相比阻塞算法同步器性能已经好很多，
 * 高并发环境下 大量线程会去竞争一个原子变量，由于只有一个线程的AQS操作会成功，
 * 这就造成了大量线程竞争失败候会进入无限循环不断尝试自旋操作CAS操作，这会白白浪费CPU资源
 * <p>
 * 惰性加载。。
 * 用来解决 缓存的争用  也就是伪共享问题cell元素是2N次方
 * 用数组的目的在于原子性数组的内存地址是连续的
 * cell @sun.misc.Contended用这个注解对cell类进行填充
 * 一个缓存行64字节6*8=48对象头8字节（32位）12（64位）
 * <p>
 * use LongAdder reason  高并发下多线程对一个变量CAS争夺失败后进行自旋而造成的降低并发性能问题，
 * 内部维护了多个cell元素，（一个动态cell数组）
 *
 * @author : darren
 * @date : 2020/6/3
 */
public class LongAdderTest {

    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(DemoApplication.class, args);

        long l1 = System.currentTimeMillis();
        AtomicLong atomicLong = new AtomicLong(1);

        LongAdder l = new LongAdder();
        for (int i = 0; i < 100000; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                l.add(1);
//                l.getAndIncrement();
            });
            t.start();
            t.join();
        }
        System.out.println(l.sum() + "==" + (System.currentTimeMillis() - l1));
//        System.out.println(l.sum()+"=="+(System.currentTimeMillis()-l1));

    }
}
