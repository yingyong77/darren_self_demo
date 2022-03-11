package com.darren.demo.utils.enumutil;

import javax.validation.constraints.NotNull;

public class EnumUtils {

    public interface Compare<T> {
        /**
         * 枚举类比较接口
         *
         * @param t: 输入参数
         * @return 返回是否相等
         * @author Xiaguangmin
         * @date 2021/5/10 11:05
         */
        int compare(@NotNull T t);
    }

    public static <T extends Compare<V>, V> T getEnum(Class<T> clazz, @NotNull V value) {
        T[] enums = clazz.getEnumConstants();
        for (T t : enums) {
            if (t.compare(value) == 0) {
                return t;
            }
        }
        return null;
    }
}