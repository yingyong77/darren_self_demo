package com.spring.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : darren
 * @date : 2021/12/13
 */
@Configuration
@ComponentScan(basePackageClasses = {AnnotationConfigScanBean.class})
public class Appconfig {

    /**
     * 使用@Bean 注解表明myBean需要交给spring进行管理
     * 未指定bean 的名称，默认采用的是 “方法名”+“首字母小写”的配置方式
     *
     * @return
     */
    @Bean
    //@Lazy
    //@Scope("prototype")
    public MyBean myBean() {
        return new MyBean();
    }

}
