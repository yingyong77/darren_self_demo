package com.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

/**
 * 组合byteBuf
 *
 * @author : darren
 * @date : 2022/4/30
 */
public class TestCompositeByteBuf {

    public static void main(String[] args) {

        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1, 2, 3, 4, 5});

        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1, 2, 3, 4, 5});

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        //会发生两次数据复制 性能有影响
        //buffer.writeBytes(buf1).writeBytes(buf2);
        CompositeByteBuf compositeByteBuf = ByteBufAllocator.DEFAULT.compositeBuffer();
        compositeByteBuf.addComponents(true, buf1, buf2);

    }
}
