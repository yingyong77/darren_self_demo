package com.mybatis.spring;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 接口扫描器
 *
 * @author : darren
 * @date : 2022/3/18
 */
public class CustomScan extends ClassPathBeanDefinitionScanner {


    public CustomScan(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 走spring的扫描逻辑
     *
     * @param basePackages
     * @return
     */
    @Override
    public int scan(String... basePackages) {
        int scan = super.scan(basePackages); //spring的扫描
        System.out.println("scan.." + scan);
        return scan;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> set = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : set) {
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();

            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClassName(CustomMapperFactorybean.class.getName());
        }
        System.out.println(set);
        return set;
    }

    /**
     * 定义什么样才是候选者
     *
     * @param beanDefinition
     * @return
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
