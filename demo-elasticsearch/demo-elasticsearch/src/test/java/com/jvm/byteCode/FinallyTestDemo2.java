package com.jvm.byteCode;

/**
 * finally 对返回值的影响
 * 在finally之前将return暂存已经固定了
 * finally中没有return 将会有一个athrows  异常会抛出去不会造成异常被吞掉
 *
 * @author : darren
 * @date : 2022/8/17
 */
public class FinallyTestDemo2 {

    public static void main(String[] args) {
        int result = test();
        System.out.println(result);
    }

    public static int test() {
        int i = 10;
        try {
            return i;
        } finally {
            i = 20;
        }
    }
}
