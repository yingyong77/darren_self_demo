package com.jvm.method;

import aj.org.objectweb.asm.ClassWriter;
import aj.org.objectweb.asm.Opcodes;

/**
 * 演示元空间内存溢出
 * 系统内存 不会使用上限
 *
 * @author : darren
 * @date : 2022/2/10
 */
public class Demo1 extends ClassLoader { //可以用来加载类的二进制字节码

    public static void main(String[] args) {
        int j = 0;
        try {
            Demo1 demo1 = new Demo1();
            //加载一万个新的类
            for (int i = 0; i < 2000000; i++, j++) {
                //用代码的方式生成二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                //版本号  方位修饰符  类名  包名 父类  接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                //生成类 并返回类的二进制字节码数组
                byte[] code = classWriter.toByteArray();
                //执行了类的加载
                demo1.defineClass("Class" + i, code, 0, code.length);
            }
        } finally {
            System.out.println(j);
        }

    }
}
