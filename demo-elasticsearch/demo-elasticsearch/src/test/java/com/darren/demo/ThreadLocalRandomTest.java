package com.darren.demo;

import com.darren.demo.utils.stream.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : darren
 * @date : 2020/9/28
 */
@Slf4j
public class ThreadLocalRandomTest {

    /**
     * old random test
     */
    @Test
    public void testRandom() {

        StreamUtils.forEach(0, Arrays.asList(new String[5]), (index, item) -> System.out.print(new Random().nextInt(5)));
    }

    /**
     * new random test
     */
    @Test
    public void testThreadLocalRandom() {

        StreamUtils.forEach(0, Arrays.asList(new String[5]), (index, item) -> System.out.print(ThreadLocalRandom.current().nextInt(5)));
    }

}
