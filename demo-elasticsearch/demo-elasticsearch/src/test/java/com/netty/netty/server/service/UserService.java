package com.netty.netty.server.service;

/**
 * 用户管理接口
 *
 * @author : darren
 * @date : 2022/6/2
 */
public interface UserService {

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 登录成功返回true, 否则返回false
     */
    boolean login(String userName, String password);
}
