package com.spring.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * {@link org.springframework.beans.factory.FactoryBean}
 * <p>
 * 1: FactoryBean创建的对象都是单例Bean
 * 2: FactoryBean默认是懒加载 getBean的时候才会放入到单例池 singletonObjects
 * 3： beanName前面加了&就表示获取本身的FactoryBean; 而不加&则表示获取FactoryBean中的getObject返回的对象
 * <p>
 * 4: factoryBean默认是懒加载的  在真正调用的时候也就是getObject 才会返回具体的对象
 *
 * </p>
 *
 * @author : darren
 * @date : 2021/12/10
 */
@Component
public class BmlFactoryBean<T> implements FactoryBean<T> {

    /**
     * @return
     * @throws Exception
     */
    @SuppressWarnings(value = "unchecked")
    @Override
    public T getObject() throws Exception {
        return (T) new User();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    class User {

    }

}
