package com.mybatis.spring.mapper;

import com.darren.demo.spring.mybatis.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : darren
 * @date : 2022/3/11
 */
public interface PureStudentMapper {

    //往里面注册一个MappedStatement
    @Select("SELECT * FROM student")
    List<Student> findAll();

    @Insert("INSERT INTO student (name,age,score,gender) VALUES (#{name},#{age},#{score},#{gender})")
    int insert(Student student);

    @Select("SELECT * FROM student WHERE name like '%${name}%' AND major like '%${major}%'")
    List<Student> find(@Param("name") String name, @Param("major") String major);

    List<Student> findAllFromXml();

    List<Student> batchFind(List<Integer> list);

    Student findStuById(int studId);
}
