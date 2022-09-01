package com.darren.demo.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : darren
 * @date : 2022/8/31
 */
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class Student implements Serializable {

    /**
     * 必须指定id列
     */
    @Id
    private String studentId;

    private String studentName;

    private Integer studentAge;
    
    private Double studentScore;

    private Date studentBirthday;
}
