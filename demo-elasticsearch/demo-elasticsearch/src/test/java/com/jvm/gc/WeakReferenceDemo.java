package com.jvm.gc;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用 示例演示
 *
 * @author : darren
 * @date : 2022/2/14
 */
public class WeakReferenceDemo {

    private static final int _4MB = 4 * 1024 * 1024;

    public static void main(String[] args) {

        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WeakReference<byte[]> weakReference = new WeakReference<>(new byte[_4MB]);
            list.add(weakReference);
            for (WeakReference<byte[]> w : list) {
                System.out.print(w.get() + "");
            }
            System.out.println();
        }
        System.out.println("循环结束：" + list.size());
    }
}
