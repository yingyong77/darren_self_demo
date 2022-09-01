package com.jvm.byteCode;

/**
 * @author : darren
 * @date : 2022/8/17
 */
public class FinallyTestDemo1 {

    public static void main(String[] args) {
        int result = test();
        System.out.println(result);
    }

    public static int test() {
        try {
            //int i = 1 / 0;
            return 10;
        } finally {
            return 20;
        }
    }
}
