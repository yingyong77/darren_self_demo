package com.jvm.byteCode;

/**
 * @author : darren
 * @date : 2022/8/17
 */
public class Demo3 {

    public static void main(String[] args) {
        int i = 0;
        int x = 0;
        while (i < 10) {
            x = x++;
            i++;
        }
        System.out.println(x); // 结果是 0 }
    }

}
