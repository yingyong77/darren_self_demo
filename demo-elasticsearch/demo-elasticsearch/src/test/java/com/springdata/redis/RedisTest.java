package com.springdata.redis;

import com.darren.demo.SelfDemoApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Map;

/**
 * @author : darren
 * @date : 2022/9/1
 */
@SpringBootTest(classes = SelfDemoApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTemplate() {
        // 写入一条String数据
//        redisTemplate.opsForValue().set("name", "darren");
        redisTemplate.opsForValue().set("name1", "哈哈");

        // 获取string数据
        Object name = redisTemplate.opsForValue().get("name1");
        System.out.println("name = " + name);
    }

    @Test
    public void testSaveUser() {
        //序列化存储了类信息
        redisTemplate.opsForValue().set("name1", new User("darren", 28));
        User user = (User) redisTemplate.opsForValue().get("name1");
        System.out.println("user:" + user);
    }

    /**
     *
     */
    @Test
    public void testSaveUser1() throws IOException {
        User user = new User("Hsoliel", 26);
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:200", userJson);

        // 获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        // 手动反序列化
        User user1 = objectMapper.readValue(jsonUser, User.class);
        System.out.println("user1 = " + user1);
    }


    @Test
    public void testHash() throws IOException {
        //操作习惯更接近于java
        stringRedisTemplate.opsForHash().put("user:400", "name", "darren");
        stringRedisTemplate.opsForHash().put("user:400", "job", "javaer");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:400");
        System.out.println("entries:" + entries);
    }

}
