package com.darren.demo.utils.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     */
    public static Object getBean(String beanId) throws BeansException {

        Map<String, MyBean> beansOfType = applicationContext
                .getBeansOfType(MyBean.class);

        return applicationContext.getBean(beanId);
    }

    /**
     * 获取指定类型的Bean 实例集合
     *
     * @param beanType Bean 类型
     * @return 指定类型的Bean 实例集合
     */
    public static <T> List<T> getBeansOfType(Class<T> beanType) {
        Map<String, T> beansOfType = applicationContext
                .getBeansOfType(beanType);

        Set<Entry<String, T>> entries = beansOfType.entrySet();

        Set<T> beanSet = new HashSet<>();
        Iterator<Entry<String, T>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Entry<String, T> next = iterator.next();
            beanSet.add(next.getValue());
        }

        List<T> beanList = new ArrayList<>(beanSet);
        return beanList;
    }

}