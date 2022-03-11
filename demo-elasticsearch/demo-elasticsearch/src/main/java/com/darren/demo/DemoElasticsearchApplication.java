package com.darren.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
@MapperScan(basePackages = "com.darren.demo.spring.mybatis")
public class DemoElasticsearchApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        //org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
        applicationContext = SpringApplication.run(DemoElasticsearchApplication.class, args);

    }

}

