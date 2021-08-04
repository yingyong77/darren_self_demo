package com.darren.demo.utils.db;

import java.lang.annotation.*;

/**
 * 自定义Column注解
 * 用户标识在需要映射的字段上
 *
 * @author : darren
 * @date : 2021/6/3
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    String value() default "";
}
