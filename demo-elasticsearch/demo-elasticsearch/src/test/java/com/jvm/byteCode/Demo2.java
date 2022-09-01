package com.jvm.byteCode;

/**
 * @author : darren
 * @date : 2022/8/17
 */
public class Demo2 {

    public static void main(String[] args) {
        int a = 10;
        int b = a++ + ++a + a--;
        System.out.println(a);
        System.out.println(b);
    }
}
