package com.darren.demo.esIndex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
@ComponentScan(basePackages = {"com.skytech.edata"})
public class DemoElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElasticsearchApplication.class, args);
    }

}

