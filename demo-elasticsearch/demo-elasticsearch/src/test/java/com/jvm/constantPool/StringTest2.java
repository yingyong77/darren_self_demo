package com.jvm.constantPool;

/**
 * 字符串延迟加载
 * 执行一行代码  遇到一个没见过的串值 才会加载到字符串串池中
 *
 * @author : darren
 * @date : 2022/2/11
 */
public class StringTest2 {

    //idea console工具观察
    public static void main(String[] args) {
        int x = args.length;
        System.out.println(); //字符串个数 3946
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        System.out.println("6");
        System.out.println("7");
        System.out.println("8");
        System.out.println("9");
        System.out.println("0");
        System.out.println("1"); //字符串个数 3956
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
    }
}
