package com.jvm.classLoader;

import com.darren.demo.SelfDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author : darren
 * @date : 2022/8/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SelfDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassLoaderDemo1 {

    @Test
    public void getPath() {
        String path2 = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        System.out.println("path2 = " + path2);
    }
}
