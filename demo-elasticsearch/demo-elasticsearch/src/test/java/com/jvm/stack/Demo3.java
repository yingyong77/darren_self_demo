package com.jvm.stack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * json 数据转换
 * 循环引用 导致栈溢出
 *
 * @author : darren
 * @date : 2022/2/9
 */
public class Demo3 {

    public static void main(String[] args) throws JsonProcessingException {
        Dept dept = new Dept();
        dept.setName("Market");

        Emp emp1 = new Emp();
        emp1.setName("Darren");
        emp1.setDept(dept);

        Emp emp2 = new Emp();
        emp2.setName("Hsoilel");
        emp2.setDept(dept);

        dept.setEmps(Arrays.asList(emp1, emp2));
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(dept));
    }

}

@Data
class Emp {
    private String name;
    @JsonIgnore
    private Dept dept;
}

@Data
class Dept {
    private String name;
    private List<Emp> emps;
}
