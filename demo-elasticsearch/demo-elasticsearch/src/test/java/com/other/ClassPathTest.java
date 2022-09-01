package com.other;

import com.darren.demo.SelfDemoApplication;
import com.darren.demo.boot.prop.ConfigurationPropertiesInject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : darren
 * @date : 2022/8/25
 */
@SpringBootTest(classes = SelfDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClassPathTest {


    @Autowired
    private ConfigurationPropertiesInject propertiesInject;

    @Test
    public void test1() {
        System.out.println(propertiesInject.getAa());
    }

    public void test2() {
        new Object() {
            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        };
    }


}
