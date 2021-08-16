package com.darren.demo.utils.stream;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * {@link java.util.stream.IntStream}
 *
 * @author : darren
 * @date : 2021/8/14
 */
public class IntStreamDemo {

    @Test
    private void test1ofIntStream() {

        IntStream intStream = IntStream.of(1, 2, 3);
        IntStream intStream1 = IntStream.builder().add(1).add(2).build();

        IntStream intStreamRange = IntStream.range(11, 16);
        IntStream intStreamRangeClosed = IntStream.rangeClosed(11, 16);

        //指定一个int生成函数，返回int流中的元素不排序
        intStream = IntStream.generate(() -> new Random().nextInt()).limit(6);
        //指定一个int生成函数，前一次的执行结果会作为下一次调用函数的入参
        // 第一个参数seed就是第一次调用函数生成的参数
        //返回int流中的元素不排序
        intStream = IntStream.iterate(1, x -> {
            int a = 2 * x;
            if (a > 16) {
                return a - 20;
            } else {
                return a;
            }
        }).limit(6);

        //将两个IntStream合并起来
        //返回的int流的元素排序与添加的流的元素排序不一致, 不排序。
        IntStream intStreamConcat = IntStream.concat(intStreamRange, intStreamRangeClosed);
    }

    /**
     * filter/map/flatMap/peek
     */
    @Test
    private void test2ofIntStream() {
        IntStream intStream = IntStream.rangeClosed(1, 10);

//        filter
        intStream.filter(x -> x % 2 == 0).peek(x -> System.out.println("filter-->" + x));
//        map
        intStream.map(x -> x + 1).peek(x -> System.out.println("map->" + x));
//        flatMap
        intStream.flatMap(x -> IntStream.of(x + 3, x + 2, x + 1)).peek(x -> System.out.println("flatMap->" + x));

    }

    /**
     *
     */
    private void test3ofIntStream() {
    }

    /**
     * forEach / forEachOrdered
     */
    private void test4ofIntStream() {

        IntStream intStream = IntStream.of(6, 1, 3, 2, 5, 4).parallel();
        intStream.forEach(x -> System.out.println("forEach->" + x));
        //并行流下会保证实际的处理顺序与流中的处理顺序一致
        intStream.forEachOrdered(x -> System.out.println("forEach->" + x));
    }

    /**
     * reduce / collect
     */
    @Test
    private void test5ofIntStream() {
        IntStream intStream = IntStream.of(6, 1, 3, 2, 5, 4);
        OptionalInt optional = intStream.reduce((x, y) -> {
            System.out.println("x->" + x + ", y->" + y);
            return x + y;
        });
        System.out.println("result->" + optional.orElse(0));

        intStream.collect(() -> {
            Random random = new Random();
            return random.nextInt();
        }, (x, y) -> {
            System.out.println("ObjIntConsumer x->" + x + ",y->" + y);
        }, null);

    }

}
