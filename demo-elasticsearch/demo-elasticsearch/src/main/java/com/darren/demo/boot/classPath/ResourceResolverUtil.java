package com.darren.demo.boot.classPath;

import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author : darren
 * @date : 2022/1/4
 */
public class ResourceResolverUtil {

    public static void main(String[] args) {
        //this.getClass().getClassLoader();
        getResourceByClassPathResource();
    }

    static final String PROP_FILEPATH = "prop_demo/appConfig.properties";

    /**
     * prop文件加载方式
     * classLoader getResource 方式
     */
    @SneakyThrows
    public static void getResourceAsStreamByClassLoader() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_FILEPATH);

        Properties props = new Properties();
        props.load(inputStream);
    }

    /**
     * 进行封装过的 Resources 工具类
     * 底层还是classLoader  getResourceAsStream
     */
    @SneakyThrows
    public static void getResourceAsStreamByResource() {
        InputStream inputStream = Resources.getResourceAsStream(PROP_FILEPATH);

        Properties props = new Properties();
        props.load(inputStream);
    }

    /**
     * ClassPathResource 底层还是要去 classLoader
     */
    @SneakyThrows
    public static void getResourceByClassPathResource() {
        ClassPathResource classPathResource = new ClassPathResource(PROP_FILEPATH);
        Properties props = new Properties();
        props.load(classPathResource.getInputStream());
    }

    //可以通过@PropertySource(“classpath:xxxx”) 注释方法
    //@Value("${xxx}") 注释属性的方法

    //@PropertySource 和 @ConfigurationProperties 组合使用，可以将属性文件与一个Java类  // 主机名
    //    @Value("${host:localhost}")
    //    public String host;  @ConfigurationProperties(SPRING_DS_PROPERTY)

    /**
     * classs getResource 方式 底层还是要去 classLoader
     */
    @SneakyThrows
    public void getResourceByClass() {
        InputStream inputStream = this.getClass().getResourceAsStream("/prop_demo/appConfig.properties");
        Properties props = new Properties();
        props.load(inputStream);
    }

    /**
     * classs getResource 方式 底层还是要去 classLoader
     */
    @SneakyThrows
    public void getResourceByResourceUtils() {
        File file = ResourceUtils.getFile("classpath:prop_demo/appConfig.properties");
        InputStream inputStream = new FileInputStream(file);
        Properties props = new Properties();
        props.load(inputStream);
    }

    /**
     * 观察spring源码 应该是有这种写法的
     *
     * @throws IOException
     */
    public void getResource() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("");
        Resource resource = resources[0];
        try (InputStream inputStream = resource.getInputStream()) {
        }
    }


}
