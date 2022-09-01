package com.darren.demo.designmodel.Singleton;

/**
 * @author : darren
 * @date : 2020/6/12
 */
public class Singleton3 {

    /*可以禁止指令重排序这个很重要*/
    private static volatile Singleton3 instance = null;

    private Singleton3() {
    }

    private static synchronized void syncInit() {
        if (instance == null) {
            instance = new Singleton3();
        }
    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            syncInit();
        }
        return instance;
    }
}
