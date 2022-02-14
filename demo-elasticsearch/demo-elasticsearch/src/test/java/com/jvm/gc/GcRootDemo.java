package com.jvm.gc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 演示Gc Roots
 *
 * @author : darren
 * @date : 2022/2/13
 */
public class GcRootDemo {

    public static void main(String[] args) throws IOException {
        //list1 引用的根对象是根对象
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("a");
        System.out.println(1);
        System.in.read();
        //从根对象列表中移除掉
        list1 = null;

        System.out.println(2);
        System.in.read();
        System.out.println("end...");

    }
}
