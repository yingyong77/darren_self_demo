package com.jvm.stack;

/**
 * 演示栈帧
 *
 * @author : darren
 * @date : 2022/2/9
 */
public class Demo1 {

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        method2(1, 2);
    }

    public static int method2(int a, int b) {
        int c = a + b;
        return c;
    }

}
