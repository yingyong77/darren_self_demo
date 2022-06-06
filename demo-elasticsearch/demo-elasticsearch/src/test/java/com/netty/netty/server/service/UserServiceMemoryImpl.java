package com.netty.netty.server.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : darren
 * @date : 2022/6/2
 */
public class UserServiceMemoryImpl implements UserService {

    private Map<String, String> allUserMap = new ConcurrentHashMap<>();

    {
        allUserMap.put("zhangsan", "123");
        allUserMap.put("lisi", "123");
        allUserMap.put("wangwu", "123");
        allUserMap.put("zhaoliu", "123");
        allUserMap.put("qianqi", "123");
    }

    @Override
    public boolean login(String userName, String password) {
        String pass = allUserMap.get(userName);
        if (pass == null) {
            return false;
        }

        return pass.equals(password);
    }
}
