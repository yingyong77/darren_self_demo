package com.spring.bean;

import com.darren.demo.spring.mybatis.StudentDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * @author : darren
 * @date : 2021/12/13
 */
@Configuration
//@ComponentScan(basePackageClasses = {AnnotationConfigScanBean.class})
//会注册一个AnnotationAwareAspectJAutoProxyCreator
@ComponentScans(value = {@ComponentScan("com.spring")})
//@Import(CustomImportBeanDefinitionRegister.class)
@MapperScan("com.mybatis.spring")
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

    //@Bean
    public SqlSessionFactory sqlSessionFactory() {
        return new StudentDao("mybatis-config.xml").getSqlSessionFactory();
    }

}
