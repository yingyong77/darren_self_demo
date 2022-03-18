package com.mybatis.spring;

import com.darren.demo.spring.mybatis.PureStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : darren
 * @date : 2022/3/18
 */
@Component
public class UserService {

    @Autowired
    private PureStudentMapper pureStudentMapper;

    public void test() {
        System.out.println(pureStudentMapper.findAll());
    }

}
