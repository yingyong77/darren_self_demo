package com.darren.demo.mongo.dao;

import com.darren.demo.mongo.entity.Student;

import java.util.List;

/**
 * @author : darren
 * @date : 2022/8/31
 */
public interface StudentDaoTypeTwo {

    //    增加一位学生
    void addOneStudent(Student student);

    //    根据id删除一位学生
    void deleteOneStudentByStudentId(String studentId);

    //    修改一位学生的信息
    void updateOneStudent(Student student);

    //    根据主键id获取一名学生
    Student getOneStudentByStudentId(String studentId);

    //    获取全部学生
    List<Student> getAllStudent();
}
