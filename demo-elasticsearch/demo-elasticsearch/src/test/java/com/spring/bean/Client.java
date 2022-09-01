package com.spring.bean;

import com.mybatis.spring.CustomMapperFactorybean;
import com.mybatis.spring.mapper.PureStudentMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(CustomMapperFactorybean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(PureStudentMapper.class);
        context.registerBeanDefinition("user", beanDefinition);

        context.refresh();
        //Bean 管理创建
        SqlSessionFactory sqlSessionFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);

        final Object user = context.getBean("user");
        // UserService userService = context.getBean("userService", UserService.class);
        //userService.test();
    }
}
