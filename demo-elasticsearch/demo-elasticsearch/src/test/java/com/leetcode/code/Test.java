package com.leetcode.code;

/**
 * 值传递与引用传递
 */
class Value {
    public int i = 15;
}

public class Test {
    public static void main(String[] argv) {
        Test t = new Test();
        t.first();
    }

    public void first() {
        int i = 5;
        //v只是一个引用
        Value v = new Value();
        v.i = 25;
        second(v, i);
        System.out.println(v.i);
    }

    public void second(Value v, int i) {
        i = 0;
        v.i = 20;
        Value val = new Value();
        v = val;
        System.out.println(v.i + " " + i);
    }


    public void second1(Value tmp, int i) {
        i = 0;
        tmp.i = 20;
        Value val = new Value();
        tmp = val;
        System.out.println(tmp.i + " " + i);
    }
}
