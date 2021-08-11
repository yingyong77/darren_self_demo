package com.darren.demo.hashmap;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author : darren
 * @date : 2020/6/15
 */
public class ConcurrentHashMapTest {

    static ConcurrentMap<Integer, BusLine> lineMap = new ConcurrentHashMap<Integer, BusLine>();

    public static void main(String[] args) {

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(32);

        concurrentHashMap.put("", "");

        //笨重的锁
        Hashtable d = new Hashtable();

    }

    /**
     * 对比一下@method {putIfAbsent} 与方法 {put}
     * <p>
     * onlyIfAbsent == true 是用来判断如果存在相应的值 是更新还是 直接返回 为ture时是直接返回
     */
    private void testMethodOfPutOrPutIfAbsent() {
        BusLine busLineOne = new BusLine().setLinedId(666).setLineName("one");
        lineMap.putIfAbsent(busLineOne.getLinedId(), busLineOne);
        System.out.println(lineMap);

        //putIfAbsent 如果已经存在相应的值 就会返回对应的值 否则就会存入相应的值 并且返回null

        BusLine busLineTwo = new BusLine().setLinedId(666).setLineName("two");
        BusLine busLine = lineMap.putIfAbsent(busLineTwo.getLinedId(), busLineTwo);
        System.out.println(busLine);

        //回判断hash值是否一样 如果值一样则不会更新  直接返回原值

        BusLine busLineThree = new BusLine().setLinedId(666).setLineName("three");
        BusLine busLineFour = new BusLine().setLinedId(666).setLineName("four");

    }

    @Data
    @Accessors(chain = true)
    static class BusLine {

        private Integer linedId;

        private String lineName;
    }

}
