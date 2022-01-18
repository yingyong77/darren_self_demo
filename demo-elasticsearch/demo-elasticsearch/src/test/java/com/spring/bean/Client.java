package com.spring.bean;

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


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);

//        context.register(Appconfig.class);
//        context.getEnvironment().setRequiredProperties("bmlxx");
//        context.refresh();

        //context.registerShutdownHook();

        System.out.println(context.getBean(AnnotationConfigScanBean.class));
    }
}
