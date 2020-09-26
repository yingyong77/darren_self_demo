package com.sky.demo.elasticsearch.threadconcurrency;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 获取Unsafe
 * 需要通过反反射去拿
 * 不是引导类加载器bootstarp 会报错
 *
 * @author : darren
 * @date : 2020/5/27
 */
public class UnsafeInstance {

    /**
     * get Unsafe
     *
     * @return {@link Unsafe}
     */
    public static Unsafe reflectUnsafe(){
        try {
            Field field= Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(Boolean.TRUE);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
