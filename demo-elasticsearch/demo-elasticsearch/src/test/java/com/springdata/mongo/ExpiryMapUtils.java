package com.springdata.mongo;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : darren
 * @date : 2022/8/31
 */
public class ExpiryMapUtils<K, V> implements Map<K, V> {

    private ConcurrentHashMap workMap;
    private ConcurrentHashMap expiryMap;

    /**
     * 默认保存时间5分钟
     */
    private long EXPIRYTIME = 1000 * 60 * 1;

    public ExpiryMapUtils() {
        ExpiryMapUtils(16, EXPIRYTIME);
    }

    /**
     * 单位秒
     *
     * @param expiryTime
     * @return
     */
    public void ExpiryMapUtils(long expiryTime) {
        ExpiryMapUtils(16, expiryTime);
    }

    /**
     * 单位秒
     *
     * @param initialCapacity
     * @param expiryTime
     * @return
     */
    public void ExpiryMapUtils(int initialCapacity, long expiryTime) {
        if (expiryTime > 0)
            EXPIRYTIME = expiryTime * 1000;
        workMap = new ConcurrentHashMap(initialCapacity);
        expiryMap = new ConcurrentHashMap(initialCapacity);
    }

    /**
     * 使用 全局过期时间
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        expiryMap.put(key, System.currentTimeMillis() + EXPIRYTIME);
        return (V) workMap.put(key, value);
    }

    /**
     * 使用 自定义过期时间 单位秒
     *
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value, long exrity) {
        expiryMap.put(key, System.currentTimeMillis() + exrity * 1000);
        return (V) workMap.put(key, value);
    }

    public void removeExpiryKeys() {
        Set set = expiryMap.keySet();
        for (Object o : set) {
            checkExpiry((K) o, true);
        }
    }

    /**
     * 是否过期判断
     *
     * @param key
     * @param isDelete
     * @return 过期true 不过期false
     */
    public boolean checkExpiry(K key, boolean isDelete) {
        Object timeObject = expiryMap.get(key);
        if (timeObject == null) {
            return true;
        }
        long setTime = Long.parseLong(timeObject.toString());
        boolean isExpiry = System.currentTimeMillis() - setTime >= 0;
        if (isExpiry) {
            if (isDelete) {
                expiryMap.remove(key);
                workMap.remove(key);
            }
            return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        boolean isExpiry = checkExpiry((K) key, true);
        if (isExpiry) {
            return null;
        }
        return (V) workMap.get(key);
    }

    @Override
    public int size() {
        removeExpiryKeys();
        return workMap.size();
    }

    @Override
    public boolean isEmpty() {
        removeExpiryKeys();
        return workMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        removeExpiryKeys();
        return workMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        removeExpiryKeys();
        return workMap.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        expiryMap.remove(key);
        return (V) workMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
//        m.entrySet().forEach(en->{
//            expiryMap.put(en.getKey(), System.currentTimeMillis()+EXPIRYTIME);
//            workMap.put(en.getKey(),en.getValue());
//        });

        Set<? extends Map.Entry<? extends K, ? extends V>> entries = m.entrySet();
        for (Map.Entry<? extends K, ? extends V> en : entries) {
            expiryMap.put(en.getKey(), System.currentTimeMillis() + EXPIRYTIME);
            workMap.put(en.getKey(), en.getValue());
        }
    }

    @Override
    public void clear() {
        expiryMap.clear();
        workMap.clear();
    }

    @Override
    public Set<K> keySet() {
        removeExpiryKeys();
        return workMap.keySet();
    }

    @Override
    public Collection<V> values() {
        removeExpiryKeys();
        return workMap.values();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        removeExpiryKeys();
        return workMap.entrySet();
    }

}
