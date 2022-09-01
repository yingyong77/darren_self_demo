package com.jvm.byteCode;

/**
 * @author : darren
 * @date : 2022/8/17
 */
public class Demo3_8_1 {

    static {
        i = 30;
    }

    static int i = 10;

    static {
        i = 20;
    }

    public static void main(String[] args) {
        System.out.println(i);
    }
}
