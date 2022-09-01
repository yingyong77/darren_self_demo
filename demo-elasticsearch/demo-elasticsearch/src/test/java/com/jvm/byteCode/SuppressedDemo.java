package com.jvm.byteCode;

/**
 * @author : darren
 * @date : 2022/8/18
 */
public class SuppressedDemo {

    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new Exception("close 异常");
    }
}
