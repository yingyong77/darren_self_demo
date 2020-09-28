package com.sky.demo.elasticsearch;

import com.sky.demo.elasticsearch.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : darren
 * @date : 2020/9/28
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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
