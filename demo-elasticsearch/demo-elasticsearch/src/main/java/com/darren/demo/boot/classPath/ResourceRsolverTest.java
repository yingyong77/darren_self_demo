package com.darren.demo.boot.classPath;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : darren
 * @date : 2022/1/4
 */
public class ResourceRsolverTest {

    /**
     * 观察spring源码 应该是有这种写法的
     *
     * @throws IOException
     */
    public void getResource() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("");
        Resource resource = resources[0];
        try (InputStream inputStream = resource.getInputStream()) {

        }

    }


}
