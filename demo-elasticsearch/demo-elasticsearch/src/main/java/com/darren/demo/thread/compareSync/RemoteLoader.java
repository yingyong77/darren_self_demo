package com.darren.demo.thread.compareSync;

/**
 * @author : darren
 * @date : 2021/8/28
 */
public interface RemoteLoader {

    String load();

    default void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
