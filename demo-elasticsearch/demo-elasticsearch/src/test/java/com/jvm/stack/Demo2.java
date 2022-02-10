package com.jvm.stack;

/**
 * 栈内存溢出：栈帧过多
 * 递归调用 没有合理的结束递归调用
 *
 * @author : darren
 * @date : 2022/2/9
 */
public class Demo2 {

    public static int count;

    public static void main(String[] args) {

        try {
            method1();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    public static void method1() {
        count++;
        method1();
    }

}
