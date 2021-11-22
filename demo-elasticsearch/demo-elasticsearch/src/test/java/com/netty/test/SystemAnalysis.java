package com.netty.test;

import com.darren.demo.DemoElasticsearchApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

/**
 * 查看系统属性
 *
 * @author : darren
 * @date : 2021/11/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoElasticsearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemAnalysis {

    @Test
    public void test() {
        String cn = System.getProperty("java.nio.channels.spi.SelectorProvider");
        System.out.println(cn);
        //获取所有的属性
        Properties properties = System.getProperties();
        //遍历所有的属性
        for (String key : properties.stringPropertyNames()) {
            //输出对应的键和值
            System.out.println(key + "=" + properties.getProperty(key));
        }

    }
}
