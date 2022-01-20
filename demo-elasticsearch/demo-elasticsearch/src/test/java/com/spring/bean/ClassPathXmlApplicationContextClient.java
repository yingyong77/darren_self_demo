package com.spring.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author : darren
 * @date : 2022/1/20
 */
public class ClassPathXmlApplicationContextClient {


    // 用我们的配置文件来启动一个 ApplicationContext
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");

    //System.out.pr("context 启动成功");

    // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
    //   MessageService messageService = context.getBean(MessageService.class);
    // 这句将输出: hello world
    //     System.out.println(messageService.getMessage());
}
