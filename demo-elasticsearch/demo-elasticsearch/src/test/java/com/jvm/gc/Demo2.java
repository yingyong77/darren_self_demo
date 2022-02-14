package com.jvm.gc;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示软引用
 *
 * @author : darren
 * @date : 2022/2/13
 */
public class Demo2 {

    private static int _4MB = 4 * 1024 * 1024;

    /**
     * 强引用
     * -Xmx20m -XX:+PrintGcDetails -verbose:gc
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new byte[_4MB]);
        }
        System.in.read();
    }

    /**
     *
     */
    public static void soft() {
        //list跟softReference直接是强引用
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> softReference = new SoftReference<>(new byte[_4MB]);
            System.out.println(softReference.get());
            list.add(softReference);
            System.out.println(list.size());
        }
        System.out.println("循环结束：" + list.size());
        for (SoftReference<byte[]> softReference : list) {
            System.out.println(softReference.get());
        }
    }
}
