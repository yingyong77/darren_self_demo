package com.spring.BeanNameGenerator;

import com.darren.demo.SelfDemoApplication;
import com.darren.demo.spring.BeanNameGenerator.BeanNameGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * word convert PDF
 *
 * @author : darren
 * @date : 2021/11/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SelfDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WordConvertPDF {

    @Autowired
    @Qualifier("beanNameGeneratorWay1")
    private BeanNameGenerator beanNameGenerator;

    @Test
    public void testAutowired() {

        String ss = "aaa";
    }

}
