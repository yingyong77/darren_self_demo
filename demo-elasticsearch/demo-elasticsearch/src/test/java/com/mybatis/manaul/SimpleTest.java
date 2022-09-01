package com.mybatis.manaul;

import com.darren.demo.spring.mybatis.Student;
import com.darren.demo.spring.mybatis.StudentDao;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SimpleTest {

    private StudentDao studentDao;
    private int i;

    @Before
    public void init() throws IOException {
        studentDao = new StudentDao("mybatis-config.xml");
    }

    @Test
    public void insertTest() {
        Student student = new Student();
        student.setId(1);
        student.setName("darren");
        student.setAge(24);
        student.setGender(1);
        student.setScore(100);
        studentDao.addStudent(student);
    }

    @Test
    public void findAllTest() {
        List<Student> all = studentDao.findAll();
        all.forEach(System.out::println);
    }


}
