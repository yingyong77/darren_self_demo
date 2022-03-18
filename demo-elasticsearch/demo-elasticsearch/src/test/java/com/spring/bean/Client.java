package com.spring.bean;

import com.darren.demo.spring.mybatis.StudentDao;
import com.mybatis.spring.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * spring容器启动方式之配置
 * <p>
 * 1:用于研究spring的启动流程
 *
 * </p>
 *
 * @author : darren
 * @date : 2021/12/13
 */
public class Client {

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        return StudentDao.getSqlSessionFactory();
    }

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(Appconfig.class);

//        context.register(Appconfig.class);
//        context.getEnvironment().setRequiredProperties("bmlxx");
//        context.refresh();

        //factoryBean
//        Object messageService = context.getBean("&bmlFactoryBean");
//        Object messageService1 = context.getBean("bmlFactoryBean");
//        System.out.println(messageService + "--" + messageService1);

//        SpringAopBean springAopBean = context.getBean(SpringAopBean.class);
//        System.out.println(springAopBean);
//        springAopBean.test();

        //context.registerShutdownHook();
        //声明式的
//        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//        beanDefinition.setBeanClass(CustomMapperFactorybean.class);
//        beanDefinition.getConstructorArgumentValues().addArgumentValues();
//        context.registerBeanDefinition("user", beanDefinition);

        context.refresh();
        //Bean 管理创建

        UserService userService = context.getBean("userService", UserService.class);
        userService.test();
    }
}
