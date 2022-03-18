package com.spring.bean;

import com.spring.aop.SpringAopBean;
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

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);

//        context.register(Appconfig.class);
//        context.getEnvironment().setRequiredProperties("bmlxx");
//        context.refresh();

        //context.registerShutdownHook();
        //声明式的
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        beanDefinition.setBeanClass(MyBean.class);

        //factoryBean
//        Object messageService = context.getBean("&bmlFactoryBean");
//        Object messageService1 = context.getBean("bmlFactoryBean");
//        System.out.println(messageService + "--" + messageService1);

        SpringAopBean springAopBean = context.getBean(SpringAopBean.class);
        System.out.println(springAopBean);
//        springAopBean.test();

        System.out.println(context.getBean(AnnotationConfigScanBean.class));
    }
}
