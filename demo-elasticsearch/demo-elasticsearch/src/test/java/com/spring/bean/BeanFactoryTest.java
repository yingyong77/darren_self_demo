package com.spring.bean;

import com.darren.demo.DemoElasticsearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : darren
 * @date : 2021/12/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeanFactoryTest {

    @Test
    public void doGetBeanFromBeanFactory() {
        AnnotationConfigApplicationContext c = new AnnotationConfigApplicationContext();
        //c.register(AppConfig.class);
        c.refresh();

    }

}
