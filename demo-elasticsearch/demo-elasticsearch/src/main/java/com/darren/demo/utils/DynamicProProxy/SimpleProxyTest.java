package com.darren.demo.utils.DynamicProProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 动态代理类
 * 预处理消息;过滤消息;消息传给委托类;事后处理消息
 *
 * @author : darren
 * @date : 2021/7/14
 */
public class SimpleProxyTest {


    /**
     * @param args
     */
    public static void main(String[] args) {

        WorkInstance workInstance = new WorkInstance();

        WorkInvocationHandler<WorkInstance> workInvocationHandler = new WorkInvocationHandler<>(workInstance);

        //Proxy类就是用来创建一个代理对象的类，它提供了很多方法，但是我们最常用的是newProxyInstance方法
        CommonFunction commonFunction = (CommonFunction) Proxy.newProxyInstance(CommonFunction.class.getClassLoader(), new Class<?>[]{CommonFunction.class}, workInvocationHandler);

        commonFunction.doWork();
    }


    /**
     * 内置对象
     * 可将这一部 做进去
     * WorkInstance workInstance = new WorkInstance();
     */
    class WorkInvocationHandlerInnerBuilt implements InvocationHandler {

        private CommonFunction commonFunction;

        private WorkInstance workInstance = new WorkInstance();

        private CommonFunction getInstance() {

            if (!Objects.isNull(workInstance)) {
                commonFunction = (CommonFunction) Proxy.newProxyInstance(CommonFunction.class.getClassLoader(), new Class<?>[]{CommonFunction.class}, this);
            }
            return commonFunction;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }

}
