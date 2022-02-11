package com.jvm.constantPool;

/**
 * @author : darren
 * @date : 2022/2/11
 */
public class StringTest1 {

    //StringTable [  ] 类似于hash表  长度固定  不能扩容
    //常量池中的a b ab只是常量池中的符号 还没有变成java中的字符串对象
    //ldc 加载一个参数
    //ldc #2 会把符号变为 "a" 字符串对象 把"a"作为key  如果没有则放入字符串串池中key就是"a"
    //ldc #3 会把符号变为 "b" 字符串对象"b" 去串种找一下看有没有 没有则放进去  只会存在一份
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        //s1和s2都是变量  
        //先创建一个stringBuilder对象 然后调用stringBuiler的init方法 其实就是构造方法在·
        //执行append方法  最后执行tostring方法  创建了一个新的值为ab的对象（new 出来的在堆中）
        //存入s4的局部变量中
        String s4 = s1 + s2;
        //System.out.println(s3 == s4);  //false 一个在堆中  一个在字符串常量池中

        String s5 = "a" + "b";   //javac在编译期的优化  他认为在编译时a和b都是常量不可能在变了  在串池中已经有了  不会创建新的字符串对象了
        //System.out.println(s5 == s3);

    }
}
