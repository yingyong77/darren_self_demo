package com.darren.demo.designmodel.Singleton;

/**
 * 实际情况是，单例模式使用内部类来维护单例的实现，JVM内部的机制能够保证当一个类被加载的时候，
 * 这个类的加载过程是线程互斥的
 * <p>
 * 能保证懒加载的原因是:
 * 访问外部类的方法时
 * 不会访问静态资源时，这个类是不会被初始化的
 * 只有调用时，才会加载-连接-初始化
 *
 * @author : darren
 * @date : 2020/6/12
 */
public class Singleton2 {

    /* 私有构造方法，防止被实例化 */
    private Singleton2() {
    }

    /* 此处使用一个内部类来维护单例 */
    private static class SingletonFactory {
        //静态内部类可以访问外部类的私有方法
        private static final Singleton2 instance = new Singleton2();
    }

    /* 获取实例 */
    public static Singleton2 getInstance() {
        return SingletonFactory.instance;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getInstance();
    }
}
