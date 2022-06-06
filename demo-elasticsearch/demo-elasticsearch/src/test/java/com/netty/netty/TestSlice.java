package com.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.netty.netty.utils.ByteBufUtil.log;

/**
 * 读写指针是独立的
 * slice
 * duplicate
 * 数据复制
 * copy
 *
 * @author : darren
 * @date : 2022/4/30
 */
public class TestSlice {

    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buf.getClass());
        buf.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'h'});
        log(buf);
        //在切片过程中,没有发生数据复制
        //不允许再往切片后的ByteBuf写入更多的内容 最大容量做了限制
        //与原始公共同一块内存
        ByteBuf f1 = buf.slice(0, 5);
        f1.retain();
        ByteBuf f2 = buf.slice(5, 5);
        log(f1);
        log(f2);

        buf.release(); //引用计数-1
        //f1.setByte(0, 'b');
        log(f1);
        log(buf);

    }
}
