package com.darren.demo.boot.classPath;

/**
 * @author : darren
 * @date : 2022/1/5
 */
public class ClassPathUtils {

    public static void main(String[] args) {
        //System.out.println(System.getProperty("java.class.path"));//系统的classpaht路径

        String path2 = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        System.out.println("path2 = " + path2);
    }
}
