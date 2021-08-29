package com.darren.demo.thread.compareSync;

/**
 * @author : darren
 * @date : 2021/8/28
 */
public class WatchRecordServiceImpl implements RemoteLoader {
    @Override
    public String load() {
        this.delay();
        return "观看记录";
    }
}
