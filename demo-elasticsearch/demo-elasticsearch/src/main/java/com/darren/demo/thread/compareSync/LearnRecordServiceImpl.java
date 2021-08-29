package com.darren.demo.thread.compareSync;

/**
 * @author : darren
 * @date : 2021/8/28
 */
public class LearnRecordServiceImpl implements RemoteLoader {

    @Override
    public String load() {
        this.delay();
        return "客户的学习信息";
    }
}
