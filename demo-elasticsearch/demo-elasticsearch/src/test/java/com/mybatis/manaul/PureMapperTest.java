package com.mybatis.manaul;

import com.darren.demo.spring.mybatis.PureStudentMapper;
import com.darren.demo.spring.mybatis.Student;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class PureMapperTest {
    
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //bean.setMapperLocations();
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

    /**
     * mapperProxy
     * 其mapper文件与dao必须在同一目录下
     */
    @Test
    public void test1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
        List<Student> studentList = mapper.findAllFromXml();
        studentList.forEach(System.out::println);
    }

    /**
     * 批量查询
     */
    @Test
    public void testBatchQuery() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
        List<Student> students = mapper.batchFind(Arrays.asList(1, 2, 3, 7, 9));
        students.forEach(System.out::println);
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFindById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Student u1 = sqlSession.selectOne("findStuById", 6);
        System.out.println(u1);
        Student u2 = sqlSession.selectOne("findStuById", 6);
        System.out.println(u2);
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void testLevel2Cache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
        Student student1 = mapper.findStuById(6);
        System.out.println(student1);
        //注意需要提交后才会将结果保存到二级缓存
        //若不提交，则还是会查询2次数据库
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        PureStudentMapper mapper2 = sqlSession2.getMapper(PureStudentMapper.class);
        Student user2 = mapper2.findStuById(7);
        System.out.println(user2);
    }

    /**
     * pageHelper分页插件
     */
    @Test
    public void testPageHelper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PureStudentMapper mapper = sqlSession.getMapper(PureStudentMapper.class);
        PageHelper.startPage(1, 3);
        List<Student> students = mapper.findAll();
        students.forEach(System.out::println);
    }
}
