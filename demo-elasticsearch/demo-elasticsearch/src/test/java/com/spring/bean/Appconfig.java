package com.spring.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

/**
 * @author : darren
 * @date : 2021/12/13
 */
@Configuration
@ComponentScan(basePackageClasses = {AnnotationConfigScanBean.class})
//会注册一个AnnotationAwareAspectJAutoProxyCreator
@EnableAspectJAutoProxy
public class Appconfig {


    /**
     * 是直接可以使用@Autowired进行注入的 注入的applicationContext是从缓存resolvableDependencies拿的
     */
    @Autowired
    private ApplicationContext applicationContext;


    //environment.getProperty("bmlxx")
    @Autowired
    private Environment environment;

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
