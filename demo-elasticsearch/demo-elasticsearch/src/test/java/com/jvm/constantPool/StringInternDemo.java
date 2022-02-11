package com.jvm.constantPool;

/**
 * intern方法说明
 * 可以主动将串池中还没有字符串放入常量池
 *
 * @author : darren
 * @date : 2022/2/11
 * @since 1.8
 */
public class StringInternDemo {

    //StringTable["a","b","ab"]
    public static void main(String[] args) {

        //1:创建自负床常量ldc时
        String s = new String("a") + new String("b");

        String s2 = s.intern(); //将这个堆中对象尝试方式串池，
        // 有则不会放入 s还是指向堆的对象
        // 没有则放入串池，会把串池对象返回  s指向串池中的地址
        // 其实就是把s对象放入了串池中   返回的是串池中的和ab值相同的地址引用
        //s只要执行了 那其指向的地址就变为了指向串池的地址

        System.out.println(s2 == "ab");   //true
        System.out.println(s == "ab");   //true

    }
}
