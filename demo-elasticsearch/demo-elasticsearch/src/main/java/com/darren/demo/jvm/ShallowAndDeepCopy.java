package com.darren.demo.jvm;

import org.springframework.beans.BeanUtils;

import java.io.*;

/**
 * 复制对象or复制引用
 * 浅拷贝or深拷贝
 *
 * @author : darren
 * @date : 2021/8/12
 */
public class ShallowAndDeepCopy {

    /**
     *
     */
    private void beanUtilsCopy() {

        //浅拷贝就是object下的clone()方法
        Object source = new Object();
        Object target = new Object();
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 深拷贝实现方式 1：重写 clone 方法来实现深拷贝
     * bad aspect:
     * 虽然层次调用clone方法可以实现深拷贝，但是显然代码量实在太大。
     * 特别对于属性数量比较多、层次比较深的类而言，每个类都要重写clone方法太过繁琐。
     */
    private void deepCloneMethod1() {

        //实现cloneable方法 一层一层实现
    }

    /**
     * 深拷贝实现方式 2：通过对象序列化实现深拷贝(推荐)
     * 将对象 序列化 为字节序列后，默认会将该对象的整个对象图进行序列化，再通过 反序列 即可完美地实现深拷贝。
     */
    private void deepCloneMethod2() {

        ObjectOutputStream oos;
        ByteArrayOutputStream bos;
        ObjectInputStream ois;
        ByteArrayInputStream bis;

        //序列化 创建一个字节数组输入流 创建一个对象输出流,并且将之前的字节数组输出流包装起来 将this写入oos中的bos --->立即刷写
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 深度拷贝
     * 上面方法的规范化
     *
     * @param source 待拷贝的对象
     * @return [T] 拷贝的副本对象
     */
    public static <T> T deepCopy(T source) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(source);
        oos.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        T desc = (T) ois.readObject();
        ois.close();

        return desc;
    }
}
