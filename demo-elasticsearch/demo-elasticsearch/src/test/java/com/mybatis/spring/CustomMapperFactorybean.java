package com.mybatis.spring;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : darren
 * @date : 2022/3/18
 */
public class CustomMapperFactorybean implements FactoryBean {

    private SqlSessionTemplate sqlSessionTemplate;

    public CustomMapperFactorybean() {

    }

    @Getter
    @Setter
    private Class mapperClass;

    public CustomMapperFactorybean(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    /**
     * Set MyBatis SqlSessionFactory to be used by this DAO. Will automatically create SqlSessionTemplate for the given
     * SqlSessionFactory.
     *
     * @param sqlSessionFactory a factory of SqlSession
     */
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (this.sqlSessionTemplate == null || sqlSessionFactory != this.sqlSessionTemplate.getSqlSessionFactory()) {
            this.sqlSessionTemplate = createSqlSessionTemplate(sqlSessionFactory);
        }
    }

    /**
     * Create a SqlSessionTemplate for the given SqlSessionFactory. Only invoked if populating the DAO with a
     * SqlSessionFactory reference!
     * <p>
     * Can be overridden in subclasses to provide a SqlSessionTemplate instance with different configuration, or a custom
     * SqlSessionTemplate subclass.
     *
     * @param sqlSessionFactory the MyBatis SqlSessionFactory to create a SqlSessionTemplate for
     * @return the new SqlSessionTemplate instance
     * @see #setSqlSessionFactory
     */
    @SuppressWarnings("WeakerAccess")
    protected SqlSessionTemplate createSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * Return the MyBatis SqlSessionFactory used by this DAO.
     *
     * @return a factory of SqlSession
     */
    public final SqlSessionFactory getSqlSessionFactory() {
        return (this.sqlSessionTemplate != null ? this.sqlSessionTemplate.getSqlSessionFactory() : null);
    }

    /**
     * Set the SqlSessionTemplate for this DAO explicitly, as an alternative to specifying a SqlSessionFactory.
     *
     * @param sqlSessionTemplate a template of SqlSession
     * @see #setSqlSessionFactory
     */
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * Users should use this method to get a SqlSession to call its statement methods This is SqlSession is managed by
     * spring. Users should not commit/rollback/close it because it will be automatically done.
     *
     * @return Spring managed thread safe SqlSession
     */
    public SqlSession getSqlSession() {
        return this.sqlSessionTemplate;
    }

    /**
     * Return the SqlSessionTemplate for this DAO, pre-initialized with the SessionFactory or set explicitly.
     * <p>
     * <b>Note: The returned SqlSessionTemplate is a shared instance.</b> You may introspect its configuration, but not
     * modify the configuration (other than from within an {@link #initDao} implementation). Consider creating a custom
     * SqlSessionTemplate instance via {@code new SqlSessionTemplate(getSqlSessionFactory())}, in which case you're
     * allowed to customize the settings on the resulting instance.
     *
     * @return a template of SqlSession
     */
    public SqlSessionTemplate getSqlSessionTemplate() {
        return this.sqlSessionTemplate;
    }


    @Override
    public Object getObject() throws Exception {

        //  mybatis的代理对象ß
        //sqlsessin.getMapper(PureStudentMapper.class);

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
