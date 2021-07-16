package com.darren.demo.utils.DynamicProProxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理类
 * 预处理消息;过滤消息;消息传给委托类;事后处理消息
 *
 * @author : darren
 * @date : 2021/7/14
 */
public class SimpleProxyTest {

    public static void main(String[] args) {

        WorkInstance workInstance = new WorkInstance();

        WorkInvocationHandler workInvocationHandler = new WorkInvocationHandler(workInstance);

        CommonFunction commonFunction = (CommonFunction) Proxy.newProxyInstance(CommonFunction.class.getClassLoader(), new Class<?>[]{CommonFunction.class}, workInvocationHandler);

        commonFunction.doWork();
    }
}
