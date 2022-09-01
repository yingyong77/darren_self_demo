package com.jvm.byteCode;

/**
 * 调用方法指令
 * invokespecial与invokestatic
 *
 * @author : darren
 * @date : 2022/8/17
 */
public class Demo3_9 {

    public Demo3_9() {
    }

    private void test1() {
    }

    private final void test2() {
    }

    public void test3() {
    }

    public static void test4() {
    }

    public static void main(String[] args) {
        Demo3_9 d = new Demo3_9();   //new:堆空间分配了内存，对象的引用放入了操作数栈   dup:复制了一份（配合构造方法的调用） invokespecial 调用了构造方法 存储到了局部变量表中
        d.test1();     //invokespecial
        d.test2();       //invokespecial
        d.test3();        //invokevirtual    编译期间并不能知道是哪个方法 动态绑定  运行的时候确定
        d.test4();       //invokestatic       利用对象调用虚拟机方法时会产生两天不必要的虚拟机指令
        Demo3_9.test4();    //invokestatic
    }
}
