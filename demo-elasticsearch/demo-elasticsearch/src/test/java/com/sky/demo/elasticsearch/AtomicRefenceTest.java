package com.sky.demo.elasticsearch;

import com.sky.demo.elasticsearch.common.dto.ComputeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author : darren
 * @date : 2020/9/28
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AtomicRefenceTest {


    /**
     * 为什么这里把堆的位置放在外面会提示
     */
    @Test
    public void test() {

        List<ComputeDTO> computeDTOS = new ArrayList<>();
        List<Map<String, Object>> list = new ArrayList<>();

        AtomicReference<Map<String, Object>> mapAtomicReference = new AtomicReference<>();

        computeDTOS.forEach(i -> {

            mapAtomicReference.set(new HashMap<>());

            mapAtomicReference.get().put("date", i.getDate());

            list.add(mapAtomicReference.get());
        });


    }


}
