package com.darren.demo.designmodel.Singleton;

/**
 * @author : darren
 * @date : 2020/6/12
 */
public class Singleton {

    /**
     * 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
     */
    private static Singleton instance = null;

    /**
     * 私有构造方法，防止被实例化
     */
    private Singleton() {
    }

//    /**
//     * 多线程环境并不适用
//     * 静态工程方法，创建实例
//     */
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

    /**
     * 1：直接加在方法上:在性能上会有所下降
     * 2：调用的时候不需要加锁。
     * <p>
     * 由于JVM内部的优化机制，JVM先画出了一些分配给Singleton实例的空白内存，并赋值给instance成员  但是并没有实例化，此时如果有线程b 去拿<(￣ ﹌ ￣) @值就会拿不到。。
     *
     * @return
     */
    public static Singleton getInstance() {
        synchronized (instance) {
            if (instance == null) {
                instance = new Singleton();
            }
        }

        return instance;
    }

    /**
     * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
     */
    public Object readResolve() {
        return instance;
    }

}
