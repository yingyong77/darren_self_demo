package com.sky.demo.elasticsearch.designmodel.Singleton;

/**
 * @author : darren
 * @date : 2020/6/12
 */
public class Singleton3 {

    private static Singleton3 instance = null;

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
