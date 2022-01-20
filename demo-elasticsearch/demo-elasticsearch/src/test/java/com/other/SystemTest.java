package com.other;

import io.netty.util.internal.SystemPropertyUtil;

/**
 * 查看jvm信息
 *
 * @author : darren
 * @date : 2021/11/23
 */
public class SystemTest {

    public static void main(String[] args) {
        String key = "java.vm.name";
        
        System.out.println(SystemPropertyUtil.get("java.vm.name") + "---" + System.getProperty(key));
    }
}
