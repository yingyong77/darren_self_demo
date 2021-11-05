package com.darren.demo.netty.nio;

import java.nio.IntBuffer;

/**
 * @author : darren
 * @date : 2021/11/5
 */
public class IntBufferTest {

    public static void main(String[] args) {

//        //创建指定长度的缓冲区
//        IntBuffer intBuffer = IntBuffer.allocate(10);
//        intBuffer.put(11);
//        intBuffer.put(5);
//        intBuffer.put(32);
//        System.out.println(intBuffer);
//        //把位置复位为0，也就是position的位置由3->0
//        intBuffer.flip();
//        System.out.println(intBuffer + "----" + intBuffer.get(1));
//        intBuffer.put(1, 99);
//        System.out.println(intBuffer + "----" + intBuffer.get(1));
//
//        for (int i = 0; i < intBuffer.limit(); i++) {
//            System.out.println(intBuffer.get() + "\t");
//        }
//        System.out.println("\nbuffer对象遍历之后的值为:" + intBuffer);
//        warp方法的使用
        int[] arr = new int[]{1, 2, 3};
        IntBuffer intBuffer = IntBuffer.wrap(arr);
        System.out.println(intBuffer);
        intBuffer = IntBuffer.wrap(arr, 1, 1);
        System.out.println(intBuffer);

    }
}
