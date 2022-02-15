package com.darren.demo.utils.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * Reids 操作
 *
 * @author sky
 */
@Component
@Slf4j
@Scope(value = "singleton")
public class RedisUtils {

    public static final String LOCK_PREFIX = "redis_lock_";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 新增key
     *
     * @param key      key
     * @param value    value
     * @param timeOut  time out
     * @param timeUnit time unit
     */
    public void addKey(String key, String value, int timeOut, TimeUnit timeUnit) {

        if (redisTemplate.hasKey(key)) {
            return;
        }

        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    /**
     * delete
     *
     * @param key key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * redis lock
     *
     * @param key lock key
     * @return lock status
     */
    public boolean lock(String key) {
        return lock(key, 5, TimeUnit.MINUTES);
    }

    /**
     * redis lock
     *
     * @param key    lock key
     * @param timout time out value
     * @param unit   time unit
     * @return lock status
     */
    public synchronized boolean lock(String key, long timout, TimeUnit unit) {

        String lockKey = LOCK_PREFIX + key;

        try {

            long expireAt = System.currentTimeMillis();

            //类似于redis的setnx
            Boolean lockStatus = redisTemplate.opsForValue().setIfAbsent(lockKey, String.valueOf(expireAt));

            if (lockStatus) {
                redisTemplate.opsForValue().set(lockKey, String.valueOf(expireAt), timout, unit);
                return true;
            }

            return false;
        } catch (Exception e) {
            redisTemplate.delete(lockKey);
            return false;
        }
    }

    /**
     * redis 解锁
     *
     * @param key key
     */
    public void unLock(String key) {
        this.delete(LOCK_PREFIX + key);
    }

}
