package com.mybatis.spring;

import com.darren.demo.spring.mybatis.PureStudentMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : darren
 * @date : 2022/3/18
 */
public class CustomMapperFactorybean implements FactoryBean {

    private SqlSession sqlsessin;

    @Getter
    @Setter
    private Class mapperClass;

    public CustomMapperFactorybean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    //spring会自动调用这个set方法  用户自定义
    public void setSqlsessin(SqlSessionFactory factory) {
        sqlsessin.getConfiguration().addMapper(mapperClass);
        this.sqlsessin = factory.openSession();
    }

    @Override
    public Object getObject() throws Exception {

        //  mybatis的代理对象
        sqlsessin.getMapper(PureStudentMapper.class);

        //生成一个代理对象
        return Proxy.newProxyInstance(CustomMapperFactorybean.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                return null;
            }
        });

    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }

}
