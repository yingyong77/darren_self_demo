package com.mybatis.manaul;

import com.darren.demo.spring.mybatis.PureStudentMapper;
import com.darren.demo.spring.mybatis.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PureMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
        mapper.insert(new Student(10, "Tomcat", 120, 60, 0));
        sqlSession.commit();
        List<Student> studentList = mapper.findAll();
        studentList.forEach(System.out::println);
    }

    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
        List<Student> studentList = mapper.findAllFromXml();
        studentList.forEach(System.out::println);
    }
}
