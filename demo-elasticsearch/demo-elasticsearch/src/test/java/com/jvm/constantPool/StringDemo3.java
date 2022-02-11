package com.jvm.constantPool;

/**
 * StringTable的垃圾回收演示
 * <p>
 * -Xmx10m -XX:+PrintStringTableStatistics(查看字符串常量池中字符串的个数及大小信息) -XX:+PrintGCDetails -verbose:gc （打印垃圾回收的详细信息）
 * <p>
 * <p>
 * SymbolTable statistics  符号表（类的字节码里  类名 方法名 也需要被读入内存）
 * StringTable statistics  StringTable统计信息 Number of entries（键值对的个数） Number of literals（字符串常量个数） Number of buckets（桶的个数）
 *
 * @author : darren
 * @date : 2022/2/11
 */
public class StringDemo3 {

    public static void main(String[] args) {

        int i = 0;
        try {
            for (int j = 0; j < 20000; j++) {
                String.valueOf(j).intern();
                i++;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(i);
        }
    }
}
