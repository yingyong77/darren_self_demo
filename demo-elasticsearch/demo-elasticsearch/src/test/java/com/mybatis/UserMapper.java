package com.mybatis;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : darren
 * @date : 2022/2/28
 */
@Mapper
public interface UserMapper {


    List<String> listUserByUserName();
}
