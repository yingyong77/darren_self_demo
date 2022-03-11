package com.darren.demo.designmodel.DynamicProProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 聚合
 *
 * @author : darren
 * @date : 2021/7/14
 */
public class WorkInvocationHandler<T> implements InvocationHandler {

    private final T target;

    public WorkInvocationHandler(T target) {
        this.target = target;
    }

    /**
     * @param proxy  表示动态代理对象
     * @param method 表示正在执行的方法
     * @param args   表示调用目标方法时传入的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理执行" + method.getName() + "方法");
        //代理过程中插入监测方法,计算该方法耗时
        Object result = method.invoke(target, args);

        return result;
    }
}
