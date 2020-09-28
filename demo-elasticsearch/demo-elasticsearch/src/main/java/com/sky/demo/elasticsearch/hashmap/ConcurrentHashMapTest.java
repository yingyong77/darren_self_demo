package com.sky.demo.elasticsearch.hashmap;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : darren
 * @date : 2020/6/15
 */
public class ConcurrentHashMapTest {


    public static void main(String[] args) {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(32);

        concurrentHashMap.put("", "");

        //笨重的锁
        Hashtable d = new Hashtable();

    }


}
