package com.springdata.redis;

import com.darren.demo.SelfDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis 命令之 key
 *
 * @author : darren
 * @date : 2022/2/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SelfDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeyDemo {

    /**
     * 连接池自动管理，提供了一个高度封装的redisTemplate类
     * redisTemplate.opsForList();//操作list
     * redisTemplate.opsForValue();//操作字符串
     * redisTemplate.opsForCluster();//集群时使用
     * redisTemplate.opsForGeo();//地理位置时使用
     * redisTemplate.opsForHash();//操作hash
     * redisTemplate.opsForSet();//操作set
     * redisTemplate.opsForZSet();//操作有序set
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void KeyOperate() {
        //删除一个key
        redisTemplate.delete("99988hhhh");
        String[] strings = new String[]{"a", "b"};
        //删除多个key
        redisTemplate.delete(Arrays.asList(strings));
        //指定key的失效时间
        redisTemplate.expire("name1", 10, TimeUnit.SECONDS);
        //获取key的过期时间 key不存在为-2
        Long expire = redisTemplate.getExpire("name1");
        System.out.println("redis key name1 过期时间为:" + expire);

    }

    /**
     * 设置值以及设置过期时间
     */
    @Test
    public void stringOperate() {
        //通过redisTemplate设置值
        redisTemplate.boundValueOps("darren").set("darren1111");

        redisTemplate.opsForValue().set("aa", "ddddd");

        //2、通过BoundValueOperations设置值
        BoundValueOperations stringKey = redisTemplate.boundValueOps("StringKey");
        stringKey.set("StringVaule");
        stringKey.set("StringValue", 1, TimeUnit.MINUTES);

        //3、通过ValueOperations设置值
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set("StringKey", "StringVaule");
        ops.set("StringValue", "StringVaule", 1, TimeUnit.MINUTES);

        //设置过期时间 单独设置
        redisTemplate.boundValueOps("darren").expire(1, TimeUnit.MINUTES);
        redisTemplate.expire("darren", 1, TimeUnit.MINUTES);

    }

    /**
     * 获取值
     */
    @Test
    public void getkey() {

        //获取缓存值
        //1、通过redisTemplate获取值
        String str1 = (String) redisTemplate.boundValueOps("StringKey").get();

        //2、通过BoundValueOperations获取值
        BoundValueOperations darren = redisTemplate.boundValueOps("StringKey");
        String str2 = (String) darren.get();

        //3、通过ValueOperations获取值
        ValueOperations ops1 = redisTemplate.opsForValue();
        String str3 = (String) ops1.get("StringKey");

        //删除key
        Boolean result = redisTemplate.delete("StringKey");


    }

    /**
     * 顺序递增/递减
     */
    @Test
    public void incrementByOrder() {

        //每次增加3
        redisTemplate.boundValueOps("darren:orderCount").increment(3L);

        redisTemplate.boundValueOps("darren:orderCount").increment(-3L);
    }

    @Test
    public void hashOperate() {

        //1、通过redisTemplate设置值
        redisTemplate.boundHashOps("cart:1001").put("10088", "1");

        //2、通过BoundValueOperations设置值
        BoundHashOperations hashKey = redisTemplate.boundHashOps("HashKey");
        hashKey.put("SmallKey", "HashVaue");

        //3、通过ValueOperations设置值
        HashOperations hashOps = redisTemplate.opsForHash();
        hashOps.put("HashKey", "SmallKey", "HashVaue");
    }

    /**
     * 设置过期时间
     */
    @Test
    public void setHashExpireTime() {
        //两种都可以
        redisTemplate.boundValueOps("cart:1001").expire(10, TimeUnit.MINUTES);
        // redisTemplate.boundHashOps("cart:1001").expire(10, TimeUnit.MINUTES);
        //redisTemplate.expire("cart:1001", 5, TimeUnit.MINUTES);
        Long expire = redisTemplate.getExpire("cart:1001");
        System.out.println(expire);

        //1、通过redisTemplate设置值
        // redisTemplate.boundHashOps("cart:1001").put(10090, 1);
    }

    /**
     * 添加一个hashMap集合
     */
    @Test
    public void setHashOfHashMapvalue() {

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(10088, 1);
        hashMap.put(10089, 2);

        redisTemplate.boundHashOps("cart:1002").putAll(hashMap);

        //得到所有的filed
        Set keys1 = redisTemplate.boundHashOps("cart:1002").keys();
        List values1 = redisTemplate.boundHashOps("cart:1002").values();

        //2、通过BoundValueOperations获取值
        BoundHashOperations hashKey = redisTemplate.boundHashOps("cart:1002");
        Set keys2 = hashKey.keys();
        List values2 = hashKey.values();

        //3、通过ValueOperations获取值
        HashOperations hashOps = redisTemplate.opsForHash();
        Set keys3 = hashOps.keys("cart:1002");
        List values3 = hashOps.values("cart:1002");
        System.out.println(keys1 + "value" + values1 + ";" + keys2 + "value" + values2 + ";" + keys3 + "value" + values3);


        //1、通过redisTemplate获取
        String value1 = (String) redisTemplate.boundHashOps("cart:1002").get("10088");
        //2、通过BoundValueOperations获取值
        BoundHashOperations cart1 = redisTemplate.boundHashOps("cart:1002");
        String value2 = (String) hashKey.get("10088");
        //3、通过ValueOperations获取值
        HashOperations cart2 = redisTemplate.opsForHash();
        String value3 = (String) hashOps.get("cart:1002", "10088");

        //获取所有的键值对集合
        //1、通过redisTemplate获取
        Map entries = redisTemplate.boundHashOps("HashKey").entries();

        //2、通过BoundValueOperations获取值
        BoundHashOperations hashKey1 = redisTemplate.boundHashOps("HashKey");
        Map entries1 = hashKey.entries();

        //3、通过ValueOperations获取值
        HashOperations hashOps2 = redisTemplate.opsForHash();
        Map entries2 = hashOps.entries("HashKey");
    }

    /**
     * 删除key
     */
    @Test
    public void deleteHashkey() {
        //删除小key
        redisTemplate.boundHashOps("cart:1002").delete("10088");
        //删除大key
        redisTemplate.delete("cart:1002");
    }

    /**
     * 判断hash中是否有该值
     */
    @Test
    public void ifhasKeyOfhash() {
        //
        Boolean isEmpty = redisTemplate.boundHashOps("cart:1002").hasKey("10088");
    }

    /**
     * redis操作之 set
     */
    @Test
    public void redisOperationOfSet() {
        //1、通过redisTemplate设置值
        redisTemplate.boundSetOps("setKey").add("setValue1", "setValue2", "setValue3");

        //2、通过BoundValueOperations设置值
        BoundSetOperations setKey = redisTemplate.boundSetOps("setKey");
        setKey.add("setValue1", "setValue2", "setValue3");

        //3、通过ValueOperations设置值
        SetOperations setOps = redisTemplate.opsForSet();
        setOps.add("setKey", "SetValue1", "setValue2", "setValue3");

        //设置过期时间
        redisTemplate.boundHashOps("setkeys").expire(1, TimeUnit.SECONDS);
        redisTemplate.expire("setKey", 1, TimeUnit.MINUTES);
    }

    /**
     * redis操作set 根据key获取set的所有值
     */
    @Test
    public void redisOperationOfGetValueFromSet() {
        //1、通过redisTemplate获取值
        Set set1 = redisTemplate.boundSetOps("setKey").members();

        //2、通过BoundValueOperations获取值
        BoundSetOperations setKey = redisTemplate.boundSetOps("setKey");
        Set set2 = setKey.members();

        //3、通过ValueOperations获取值
        SetOperations setOps = redisTemplate.opsForSet();
        Set set3 = setOps.members("setKey");

        //查询某个值是否存在于这个set中
        Boolean isEmpty = redisTemplate.boundSetOps("setKey").isMember("setValue2");

        //获取set集合的容量
        Long size = redisTemplate.boundSetOps("setKey").size();

        //移除某个元素
        Long result1 = redisTemplate.boundSetOps("setKey").remove("setValue1");

        //移除指定的key
        Boolean result2 = redisTemplate.delete("setKey");
    }


    @Test
    public void redisOperationOfList() {
        //1、通过redisTemplate设置值
        redisTemplate.boundListOps("listKey").leftPush("listLeftValue1");
        redisTemplate.boundListOps("listKey").rightPush("listRightValue2");

        //2、通过BoundValueOperations设置值
        BoundListOperations listKey = redisTemplate.boundListOps("listKey");
        listKey.leftPush("listLeftValue3");
        listKey.rightPush("listRightValue4");

        //3、通过ValueOperations设置值
        ListOperations opsList = redisTemplate.opsForList();
        opsList.leftPush("listKey", "listLeftValue5");
        opsList.rightPush("listKey", "listRightValue6");

        ArrayList<String> list = new ArrayList<>();
        redisTemplate.boundListOps("listKey").rightPushAll(list);
        redisTemplate.boundListOps("listKey").leftPushAll(list);

        //设置过期时间
        redisTemplate.boundValueOps("listKey").expire(1, TimeUnit.MINUTES);
        redisTemplate.expire("listKey", 1, TimeUnit.MINUTES);

        //获取List缓存全部内容（起始索引，结束索引）
        List listKey1 = redisTemplate.boundListOps("listKey").range(0, 10);

        String listKey2 = (String) redisTemplate.boundListOps("listKey").leftPop();  //从左侧弹出一个元素
        String listKey3 = (String) redisTemplate.boundListOps("listKey").rightPop(); //从右侧弹出一个元素

        //根据索引查询元素
        String listKey4 = (String) redisTemplate.boundListOps("listKey").index(1);

        //获取List缓存的长度
        Long size = redisTemplate.boundListOps("listKey").size();

        //根据索引修改List中的某条数据(key，索引，值)
        redisTemplate.boundListOps("listKey").set(3L, "listLeftValue3");

        //移除N个值为value(key,移除个数，值)
        redisTemplate.boundListOps("listKey").remove(3L, "value");

    }


    @Test
    public void redisOperationOfZset() {
        //1、通过redisTemplate设置值
        redisTemplate.boundZSetOps("zSetKey").add("zSetVaule", 100D);

        //2、通过BoundValueOperations设置值
        BoundZSetOperations zSetKey = redisTemplate.boundZSetOps("zSetKey");
        zSetKey.add("zSetVaule", 100D);

        //3、通过ValueOperations设置值
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add("zSetKey", "zSetVaule", 100D);

        //向集合中插入多个元素,并设置分数
        DefaultTypedTuple<String> p1 = new DefaultTypedTuple<>("zSetVaule1", 2.1D);
        DefaultTypedTuple<String> p2 = new DefaultTypedTuple<>("zSetVaule2", 3.3D);
        redisTemplate.boundZSetOps("zSetKey").add(new HashSet<>(Arrays.asList(p1, p2)));

        //按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
        Set<String> range = redisTemplate.boundZSetOps("zSetKey").range(0, -1);
        //获得指定元素的分数
        Double score = redisTemplate.boundZSetOps("zSetKey").score("zSetVaule");
        //返回集合内的成员个数
        Long size = redisTemplate.boundZSetOps("zSetKey").size();
        //返回集合内指定分数范围的成员个数（Double类型）
        Long COUNT = redisTemplate.boundZSetOps("zSetKey").count(0D, 2.2D);
        //返回集合内元素在指定分数范围内的排名（从小到大）
        Set byScore = redisTemplate.boundZSetOps("zSetKey").rangeByScore(0D, 2.2D);
        //带偏移量和个数，(key，起始分数，最大分数，偏移量，个数)
        Set<String> ranking2 = redisTemplate.opsForZSet().rangeByScore("zSetKey", 0D, 2.2D, 1, 3);
        //返回集合内元素的排名，以及分数（从小到大）
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.boundZSetOps("zSetKey").rangeWithScores(0L, 3L);
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            System.out.println(tuple.getValue() + " : " + tuple.getScore());
        }
        //返回指定成员的排名
        //从小到大
        Long startRank = redisTemplate.boundZSetOps("zSetKey").rank("zSetVaule");
        //从大到小
        Long endRank = redisTemplate.boundZSetOps("zSetKey").reverseRank("zSetVaule");

        //从集合中删除指定元素
        redisTemplate.boundZSetOps("zSetKey").remove("zSetVaule");
        ///删除指定索引范围的元素（Long类型）
        redisTemplate.boundZSetOps("zSetKey").removeRange(0L, 3L);
        //删除指定分数范围内的元素（Double类型）
        redisTemplate.boundZSetOps("zSetKey").removeRangeByScore(0D, 2.2D);
        //为指定元素加分（Double类型）
        Double score1 = redisTemplate.boundZSetOps("zSetKey").incrementScore("zSetVaule", 1.1D);
    }

}

