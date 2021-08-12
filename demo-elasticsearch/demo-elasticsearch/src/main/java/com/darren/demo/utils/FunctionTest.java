package com.darren.demo.utils;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 大量的使用匿名内部类和方法引用
 *
 * @author : darren
 * @date : 2021/8/12
 */
public class FunctionTest {


    /**
     * 供给型
     * <p>
     * Supplier 接口是一个供给型的接口，其实，说白了就是一个容器，
     * 可以用来存储数据，然后可以供其他方法使用的这么一个接口
     */
    private void functionOfSupplierTest() {

        //lambda表达式作为 Supplier---lambda 表达式返回一个 Supplier类型的接口
        Supplier<Integer> supplier = () -> new Random().nextInt();
        supplier.get();

        //方法引用返回的也是一个::(方法引用标识)
        Supplier<Double> doubleSupplier = Math::random;

        Stream<Integer> stream = Stream.of(2, 4, 5);
        //返回一个optional对象
        Optional<Integer> first = stream.filter(x -> x > 4).findFirst();

        //orElse，如果first中存在数，就返回这个数，如果不存在，就放回传入的数
        int tempInt = first.orElse(new Random().nextInt());

        //orElseGet，如果first中存在数，就返回这个数，如果不存在，就返回supplier返回的值
        int tempInt2 = first.orElseGet(() -> new Random().nextInt());
        Supplier<Integer> supplierOfInt = () -> new Random().nextInt();
        int tempInt3 = first.orElseGet(supplierOfInt);
    }

    /**
     * 谓词测试，谓词其实就是一个判断的作用类似bool的作用
     * <p>
     * Predicate 是一个谓词型接口，其实只是起到一个判断作用
     * Predicate 通过实现一个 test 方法做判断
     */
    private void functionOfPredicateTest() {

        //判断大于5的值
        Predicate<Integer> predicate = integer -> integer > 5;
        boolean boolean1 = predicate.test(1);

        List<Integer> integerList = Stream.of(1, 2, 3).filter(predicate).collect(Collectors.toList());
    }

    /**
     * Function测试，function的作用是转换，将一个值转为另外一个值
     */
    private void functionOfFunctionTest() {
        //①使用map方法，泛型的第一个参数是转换前的类型，第二个是转化后的类型
        Function<String, Integer> function = String::length;
        Stream<String> stream = Stream.of("aa", "bb", "cc");
        Stream<Integer> streamOfMap = stream.map(function);

        //function 函数的运用
        Function<Integer, Integer> name = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        int value = name.andThen(square).apply(3);
        System.out.println("andThen value=" + value);
        int value2 = name.compose(square).apply(3);
        System.out.println("compose value2=" + value2);
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object identity = Function.identity().apply("huohuo");
        System.out.println(identity);

    }


}
