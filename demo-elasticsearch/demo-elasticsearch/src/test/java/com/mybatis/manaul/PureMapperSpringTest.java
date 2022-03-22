package com.mybatis.manaul;

import com.darren.demo.DemoElasticsearchApplication;
import com.darren.demo.spring.mybatis.PureStudentMapper;
import com.darren.demo.spring.mybatis.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PureMapperSpringTest {

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    @Autowired
    private PureStudentMapper pureStudentMapper;

    @Test
    public void test() {
        List<Student> studentList = pureStudentMapper.findAllFromXml();
        studentList.forEach(System.out::println);
    }

    @Test
    public void test1() {
        List<Object> studentList = sessionTemplate.selectList("findAllFromXml");
        studentList.forEach(System.out::println);
    }

}
