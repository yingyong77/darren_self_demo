package com.darren.demo.utils.reflect;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通过实体类中的注解选用某些特定的字段组成json
 *
 * @author : darren
 * @date : 2020/8/14
 */
@Component
public class PropertiesDeclareUtil {

    /**
     * 通过注解返回属性列表
     *
     * @param object
     * @return
     */
    @SneakyThrows(Exception.class)
    public Map<String, Object> getPropertiesMap(Object object) {

        Map<String, Object> map = new LinkedHashMap<>();
        //获取属性
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            //是否被特定注解修饰
            boolean fieldHasPropertiesDeclare = field.isAnnotationPresent(PropertiesDeclare.class);

            if (fieldHasPropertiesDeclare) {
                //putFieldValue(field, object, map);
            }
        }

        return map;

    }


    /**
     * 获取被注解修饰的属性组装返回map
     * ps: dto自身属性直接放入
     *
     * @param object
     * @return
     */
    @SneakyThrows(Exception.class)
    public Map<String, Object> getPropertiesContainsWithDTO(Object object) {

        Map<String, Object> map = new LinkedHashMap<>();


        Field[] subFields = object.getClass().getDeclaredFields();

        for (Field field : subFields) {
            field.setAccessible(Boolean.TRUE);
            String fieldName = field.getName();
            map.put(fieldName, field.get(object));
        }

        // 获得父类的字节码对象
        Class supperObject = object.getClass().getSuperclass();

        //获取属性   子类及所有父类
        while (supperObject != null) {
            // 获取字节码对象的属性对象数组
            Field[] declaredFields = supperObject.getDeclaredFields();

            Arrays.stream(declaredFields).forEach(field -> {
                //设置可见性
                field.setAccessible(true);
                //是否被特定注解修饰
                boolean fieldHasPropertiesDeclare = field.isAnnotationPresent(PropertiesDeclare.class);
                if (fieldHasPropertiesDeclare) {
                    putFieldValue(field, object, map);
                }
            });

            supperObject = supperObject.getSuperclass();
        }

        return map;
    }


    /**
     * 将属性放入map中
     *
     * @param field     属性对象
     * @param objectSub 操作object
     * @param map       返回map
     */
    @SneakyThrows(Exception.class)
    private void putFieldValue(Field field, Object objectSub, Map<String, Object> map) {

        PropertiesDeclare propertiesDeclare = field.getAnnotation(PropertiesDeclare.class);

        val cl = Arrays.asList(propertiesDeclare.isChoose());

        if (cl.contains(objectSub.getClass())) {

            //注入注解属性
            String fieldName = propertiesDeclare.name();

            if (StringUtils.isBlank(fieldName)) {
                fieldName = field.getName();
            }

            //放入值
            map.put(fieldName, field.get(objectSub));
        }

    }


}

