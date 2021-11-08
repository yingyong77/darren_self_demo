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
//        System.out.println(intBuffer + "---" + intBuffer.get());
//        System.out.println(intBuffer + "---" + intBuffer.get());

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
        //warp方法的使用
//        int[] arr = new int[]{1, 2, 3};
//        IntBuffer intBuffer = IntBuffer.wrap(arr);
//        System.out.println(intBuffer + "---" + intBuffer.get());
//        intBuffer = IntBuffer.wrap(arr, 1, 1);
//        System.out.println(intBuffer);

        IntBuffer intBuffer = IntBuffer.allocate(10);
        int[] arr = new int[]{1, 2, 3};
        intBuffer.put(arr);
        System.out.println("调用put（arr）方法后的buffer" + intBuffer);
        IntBuffer intBuffer1 = intBuffer.duplicate();
        System.out.println("buffer1" + intBuffer1);

        intBuffer.position(1);
        System.out.println("调用position方法后的buffer" + intBuffer);
        System.out.println("buffer的可读数据量：" + intBuffer.remaining()); //计算出从pos到lim的长度
        int[] arr1 = new int[intBuffer.remaining()];
        //将缓冲区的数据放入arr中
        intBuffer.get(arr1);
        for (Integer i : arr1) {
            System.out.println(Integer.toString(i) + ",");
        }

        System.out.println("buufer1的可读数量：" + intBuffer1.remaining() + "dddd" + intBuffer1);
//比较flip()方法和position(index)方法的区别
        intBuffer1.flip();
        System.out.println("buffer1的可读数量：" + intBuffer1.remaining() + "ddd" + intBuffer1);
        arr1 = new int[intBuffer1.remaining()];
        intBuffer1.get(arr1);
        for (Integer i : arr1) {
            System.out.print(Integer.toString(i) + ",");
        }
    }
}
