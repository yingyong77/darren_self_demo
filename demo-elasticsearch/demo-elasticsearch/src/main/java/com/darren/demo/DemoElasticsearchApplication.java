package com.darren.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class DemoElasticsearchApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        //org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
        applicationContext = SpringApplication.run(DemoElasticsearchApplication.class, args);

    }

}

