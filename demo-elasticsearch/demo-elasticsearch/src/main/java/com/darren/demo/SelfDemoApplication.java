package com.darren.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@EnableElasticsearchRepositories
@SpringBootApplication
@MapperScan(basePackages = "com.darren.demo.spring.mybatis")
public class SelfDemoApplication {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        //org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
        applicationContext = SpringApplication.run(SelfDemoApplication.class, args);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}

