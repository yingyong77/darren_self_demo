package com.spring.aop.proxyFactoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : darren
 * @date : 2022/1/12
 */
@Component
public class MyService implements proxyInterface {

    @Autowired

    @Override
    public void say() {
        System.out.println("dddddd");
    }
}
