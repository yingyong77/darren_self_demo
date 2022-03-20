package com.darren.demo.spring.mybatis;

import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class StudentDao {

    @Getter
    private final SqlSessionFactory sqlSessionFactory;

    private final static String resourcepath = "mybatis-config.xml";

    @SneakyThrows
    public StudentDao(String configPath) {
        InputStream inputStream = Resources.getResourceAsStream(configPath);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public List<Student> findAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Student> studentList = sqlSession.selectList("findAll");
        sqlSession.close();
        return studentList;
    }

    public int addStudent(Student student) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rowsAffected = sqlSession.insert("insert", student);
        sqlSession.commit();  //提交
        sqlSession.close();   //关闭连接
        return rowsAffected;
    }

    public int deleteStudent(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int rowsAffected = sqlSession.delete("delete", id);
        sqlSession.commit();
        sqlSession.close();
        return rowsAffected;
    }

}

