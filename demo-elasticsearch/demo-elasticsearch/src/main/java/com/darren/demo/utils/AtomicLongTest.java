package com.darren.demo.utils;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子性操作类 atomicLong
 * AtomicLong中得unsafe类可以通过unsafe的静态方法直接获取，是因为atomicLong类也是在rt.jar包下面的
 * 因此atomicLong就是通过Bootstarp类加载的
 * <p>
 * <p>
 * 都是CAS非阻塞算法
 * 对性能损耗较少
 *
 * @author : darren
 * @date : 2020/6/2
 */
public class AtomicLongTest {

    public static void main(String[] args) {

        AtomicLong atomicLong = new AtomicLong(1);

        atomicLong.incrementAndGet();

        atomicLong.getAndIncrement();

        atomicLong.decrementAndGet();

        atomicLong.getAndDecrement();

        atomicLong.compareAndSet(1, 2);


        String jsonOfBackUpStrategy = "{\n" +
                "    \"backupStrategyName\": \"备份策略-高\",\n" +
                "    \"syncEnable\": 1,\n" +
                "    \"syncIntervalType\": 3,\n" +
                "    \"syncIntervalValue\": 2,\n" +
                "    \"syncEnableLimitedTime\": 1,\n" +
                "    \"syncLimitedBackupTime\": null,\n" +
                "    \"snapIntervalType\": 3,\n" +
                "    \"snapIntervalValue\": 2,\n" +
                "    \"snapEnableTimeLimited\": 1,\n" +
                "    \"snapLimitedBackupTime\": null,\n" +
                "    \"backupSnapFullKeepNum\": 2,\n" +
                "    \"snapKeepFullType\": 1,\n" +
                "    \"eachSnapDataGroupMaxNum\": 64,\n" +
                "    \"snapKeepDayCount\": null,\n" +
                "    \"keepSnapEnable\": null,\n" +
                "    \"keepFullBackupEnable\": null,\n" +
                "    \"backupEnableSpeedLimited\": 1,\n" +
                "    \"backupSpeedLimited\": 1024,\n" +
                "    \"backupModelType\": 1,\n" +
                "    \"backupCheckModel\": 2,\n" +
                "    \"lanFreeChannelType\": null,\n" +
                "    \"enableParticles\": 2,\n" +
                "    \"createTime\": null,\n" +
                "    \"updateTime\": null,\n" +
                "    \"syncLimitedBackupStartTime\": \"00:00\",\n" +
                "    \"syncLimitedBackupEndTime\": \"00:10\",\n" +
                "    \"snapLimitedBackupStartTime\": \"09:00\",\n" +
                "    \"snapLimitedBackupEndTime\": \"10:20\"\n" +
                "}";

    }


}
