package com.mybatis.spring;

import com.darren.demo.spring.mybatis.PureStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

/**
 * @author : darren
 * @date : 2022/3/18
 */
//@Component
@DependsOn("pureStudentMapper")
public class UserService {

    @Autowired
    private PureStudentMapper pureStudentMapper;

    public void test() {
        System.out.println(pureStudentMapper.findAll());
    }

}
