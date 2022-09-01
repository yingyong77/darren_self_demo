package com.jvm.byteCode;

/**
 * @author : darren
 * @date : 2022/8/16
 */
public class Demo1 {
    public static void main(String[] args) {
        int a = 10;  //10在128 -127之间所有是byte bipush10 一个字节
        int b = Short.MAX_VALUE + 1;  //编译期间就已经算好了 常量折叠的优化
        int c = a + b;
        System.out.println(c);    //System.out对象存在于堆内存中
    }
}
