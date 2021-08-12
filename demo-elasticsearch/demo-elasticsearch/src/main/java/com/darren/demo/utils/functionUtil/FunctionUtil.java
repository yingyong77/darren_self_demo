package com.darren.demo.utils.functionUtil;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * {@link java.util.function}
 *
 * @author : darren
 * @date : 2021/8/12
 */
public class FunctionUtil {

    /**
     * 消费型
     * consumer就是一个消费型的接口，通过传入参数，然后输出值
     */
    @Test
    public void consumerDemoFollow() {
        Consumer<String> consumer = System.out::println;
        Stream<String> stream = Stream.of("aa", "bb", "cc");
        stream.forEach(consumer);
    }
}
