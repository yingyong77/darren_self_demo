package com.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : darren
 * @date : 2022/1/17
 */
@Component
public class A {

    @Autowired
    private List<B> b;

}
