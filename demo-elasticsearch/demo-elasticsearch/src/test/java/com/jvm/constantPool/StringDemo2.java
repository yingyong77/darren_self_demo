package com.jvm.constantPool;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示StringTable位置
 * 在jdk1.8下设置 -Xmx10m -XX:-UseGCOverheadLimit  堆
 * 在jdk1.6下设置 -XX:MaxPermSize=10m    永久代
 *
 * @author : darren
 * @date : 2022/2/11
 */
public class StringDemo2 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        int i = 0;
        try {
            for (int j = 0; j < 260000; j++) {
                list.add(String.valueOf(j).intern());
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }

}
