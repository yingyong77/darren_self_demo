package com.netty.netty.protocol;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : darren
 * @date : 2022/6/5
 */
public abstract class SequenceIdGenerator {
    private static final AtomicInteger id = new AtomicInteger();

    public static int nexId() {
        return id.incrementAndGet();
    }
}
