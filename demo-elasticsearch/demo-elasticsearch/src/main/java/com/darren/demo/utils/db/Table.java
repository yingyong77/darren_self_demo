package com.darren.demo.utils.db;

import java.lang.annotation.*;

/**
 * 自定义table 注解
 * 用户标识在需要映射的类上
 *
 * @author : darren
 * @date : 2021/6/3
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {

    String value() default "";
}
