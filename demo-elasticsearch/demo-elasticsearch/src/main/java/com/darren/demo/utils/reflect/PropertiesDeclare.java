package com.darren.demo.utils.reflect;

import java.lang.annotation.*;

/**
 * @author : darren
 * @date : 2020/8/14
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PropertiesDeclare {

    /**
     * 字段的名称
     */
    String name() default "";

    /**
     * 支持哪些类
     */
    Class[] isChoose() default {};

}
