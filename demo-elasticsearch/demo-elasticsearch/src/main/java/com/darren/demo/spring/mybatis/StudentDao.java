package com.darren.demo.spring.mybatis;

import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StudentDao {

    @Getter
    private static final SqlSessionFactory sqlSessionFactory;

    private final static String resourcepath = "mybatis-config.xml";

    static {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("resourcepath");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public StudentDao() {

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

