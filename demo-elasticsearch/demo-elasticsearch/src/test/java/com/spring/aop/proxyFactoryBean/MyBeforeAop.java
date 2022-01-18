package com.spring.aop.proxyFactoryBean;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义增强类
 *
 * @author : darren
 * @date : 2022/1/12
 */
@Component
public class MyBeforeAop implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }
}
