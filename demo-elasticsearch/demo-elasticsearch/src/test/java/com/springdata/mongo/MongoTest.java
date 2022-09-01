package com.springdata.mongo;

import com.darren.demo.SelfDemoApplication;
import com.darren.demo.mongo.dao.StudentDaoTypeTwo;
import com.darren.demo.mongo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author : darren
 * @date : 2022/8/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SelfDemoApplication.class)
public class MongoTest {

    @Autowired
    private StudentDaoTypeTwo studentDaoTypeTwo;

    @Test
    public void andOneTest() {
        for (int i = 0; i < 10; i++) {
            Student student = new Student()
                    .setStudentId("student_" + i)
                    .setStudentName("Darren_" + i)
                    .setStudentAge(i)
                    .setStudentScore(100.0 - i)
                    .setStudentBirthday(new Date());
            studentDaoTypeTwo.addOneStudent(student);
        }
    }

    @Test
    public void deleteOneStudentByStudentId() {
//        删除id为study_0的学生
        studentDaoTypeTwo.deleteOneStudentByStudentId("student_1");
    }

    @Test
    public void updateOneStudent() {
//        修改id为study_1的Student年龄为21
        Student student = studentDaoTypeTwo.getOneStudentByStudentId("student_2");
        student.setStudentAge(21);
        studentDaoTypeTwo.updateOneStudent(student);
    }

    @Test
    public void getOneStudentByStudentId() {
        System.out.println(studentDaoTypeTwo.getOneStudentByStudentId("student_2"));
    }

    @Test
    public void getAllStudent() {
        List<Student> studentList = studentDaoTypeTwo.getAllStudent();
        studentList.forEach(System.out::println);
    }
}
