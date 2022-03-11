package com.darren.demo.spring.mybatis.handler;

/**
 * enum
 *
 * @author Sky.
 */
public interface BaseEnum<E extends Enum<E>, T> {

    /**
     * 获取key
     *
     * @return key
     */
    T getKey();

    /**
     * 获取 value
     *
     * @return value
     */
    String getValue();
}
