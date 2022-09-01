package com.mybatis.spring;

import com.darren.demo.SelfDemoApplication;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : darren
 * @date : 2022/2/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SelfDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class demo {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     *
     */
    public void getMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.listUserByUserName();
    }
}
