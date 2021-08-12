package com.darren.demo.utils;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Stream 处理工具类
 *
 * @author : darren
 * @date : 2020/9/17
 */
public class StreamUtils {


    /**
     * stream forEach 带下标
     * 可获取遍历的下标
     *
     * @param <T>        泛型 <类型>
     * @param startIndex 开始遍历的索引
     * @param elements   集合
     * @param action
     */
    public static <T> void forEach(int startIndex, Iterable<? extends T> elements, BiConsumer<Integer, ? super T> action) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(action);
        if (startIndex < 0) {
            startIndex = 0;
        }
        int index = 0;
        for (T element : elements) {
            index++;
            if (index <= startIndex) {
                continue;
            }

            action.accept(index - 1, element);
        }
    }
}
