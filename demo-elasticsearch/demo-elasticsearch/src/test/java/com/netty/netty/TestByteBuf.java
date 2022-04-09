package com.netty.netty;

import com.netty.netty.utils.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * 可以自动扩容
 *
 * @author : darren
 * @date : 2022/3/24
 */
public class TestByteBuf {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer();
        ByteBufUtil.log(buf);
        System.out.println(buf.getClass());
        StringBuilder stringBuilder = new StringBuilder();
        //占用300个字节
        for (int i = 0; i < 32; i++) {
            stringBuilder.append("a");
        }
        buf.writeBytes(stringBuilder.toString().getBytes());
        //byteBuf的容量发生了变化
        ByteBufUtil.log(buf);
    }
}
