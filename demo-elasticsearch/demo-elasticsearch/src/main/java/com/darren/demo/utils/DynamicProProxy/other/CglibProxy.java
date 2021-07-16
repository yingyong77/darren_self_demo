package com.darren.demo.utils.DynamicProProxy.other;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib动态代理：
 * Cglib动态代理，实现MethodInterceptor接口
 *
 * @author : darren
 * @date : 2021/7/16
 */
public class CglibProxy implements MethodInterceptor {

    /**
     * 需要代理的目标对象
     */
    private Object target;

    /**
     * 重写拦截方法
     *
     * @param o
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object result = method.invoke(target, args);//方法执行参数：target 目标对象 arr参数数组
        System.out.println("Cglib动态代理，监听结束！");
        return result;
    }

    /**
     * 定义获取代理对象的方法
     *
     * @return {@link UserManager}
     */
    public UserManager getCglibManager(Object targetObject) {
        this.target = targetObject;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为cglib是针对某一个特定的类生成一个子类,所以需要指定一个父类
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        Object result = enhancer.create();
        return (UserManager) result;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        UserManager proxy = cglibProxy.getCglibManager(new UserManagerImpl());
        proxy.delUser("");
    }
}
