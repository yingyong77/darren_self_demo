package com.darren.demo.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

/**
 * Map 家族
 * HashMap
 * TreeMap
 * HashTable
 * properties
 * enumMap
 * ConcurrentHashMap
 *
 * @author : darren
 * @date : 2020/6/8
 */
public class MapTest {


    private void HashMapTest() {

        HashMap<String, Object> hashMap = new HashMap<>();

        Collections.synchronizedMap(hashMap);
    }

    private void HashTableTest() {

        Hashtable hashtable = new Hashtable();
    }

    private void LinkedHashTableTest() {

        LinkedHashMap hashtable = new LinkedHashMap();
    }


}
