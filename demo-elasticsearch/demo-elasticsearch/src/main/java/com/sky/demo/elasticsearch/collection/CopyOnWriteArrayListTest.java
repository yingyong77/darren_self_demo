package com.sky.demo.elasticsearch.collection;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1:线程安全的 写时复制的策略来保证list的一致性，增删改的时候用到了独占锁，
 * 2:提供了弱一致性迭代器
 *
 * @author : darren
 * @date : 2020/6/8
 */
public class CopyOnWriteArrayListTest {

    private static volatile CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
//        //线程安全List
//        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
//
//        copyOnWriteArrayList.add("hello");
//        copyOnWriteArrayList.add("darren");
//
//        Iterator<String> itr = copyOnWriteArrayList.iterator();
//
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//

        list.add("hello");
        list.add("alibaba");
        list.add("welcome");
        list.add("to");
        list.add("hangzhou");

        Thread t = new Thread(() -> {

            list.set(1, "baba");
            list.remove(2);
            list.remove(3);

        });
        //子线程执行之前现货的迭代器
        Iterator<String> iterator = list.iterator();

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }


    }
}
