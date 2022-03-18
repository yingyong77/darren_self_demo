package com.mybatis.spring;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author : darren
 * @date : 2022/3/18
 */
public class CustomImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {

    //扫描  spring的扫描
    private final String path = "com.mybatis.spring.mapper";

    /**
     * @param importingClassMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {


        CustomScan customScan = new CustomScan(registry);
        customScan.addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
        customScan.scan(path);
    }
}
