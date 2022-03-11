package com.jvm.gc;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示软引用
 * <p>
 * 软引用 配合引用队列
 *
 * @author : darren
 * @date : 2022/2/13
 */
class SoftReferenceDemo {

    private static final int _4MB = 4 * 1024 * 1024;

    /**
     * 强引用
     * -Xmx20m -XX:+PrintGCDetails  -verbose:gc
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
//        List<byte[]> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(new byte[_4MB]);
//        }
//        System.in.read();

        soft();
    }

    /**
     * 引发了full gc 全被清除了
     */
    public static void soft() {
        //list跟softReference直接是强引用
        List<SoftReference<byte[]>> list = new ArrayList<>();

        //软引用--引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
        for (int i = 0; i < 5; i++) {
            //关联了软引用对象  软引用管理的byte数组对象被回收时 软引用自身就会被加入队列queue中去
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[_4MB], queue);
            System.out.println(softReference.get());
            list.add(softReference);
            System.out.println(list.size());
        }
        //移除软引用对象
        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null) {
            list.remove(poll);
            poll = queue.poll();
        }

        System.out.println("循环结束：" + list.size());
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
    }
}
