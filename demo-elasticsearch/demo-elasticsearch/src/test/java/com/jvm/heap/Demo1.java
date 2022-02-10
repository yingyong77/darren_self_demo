package com.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * java.lang.OutOfMemoryError: Java heap space
 *
 * @author : darren
 * @date : 2022/2/9
 */
public class Demo1 {

    /***
     * 程序没达到catch之前 list肯定不会被垃圾回收掉
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "hello";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println(i + "次");
        }

    }

}
