package com.spring.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * spring整合tomcat分析
 *
 * @author : darren
 * @date : 2022/8/29
 */
@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class SpringbootStarter {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootStarter.class);
    }
}
