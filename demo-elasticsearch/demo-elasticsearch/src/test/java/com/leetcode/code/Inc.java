package com.leetcode.code;

/**
 * @author : darren
 * @date : 2022/3/1
 */
public class Inc {

    public static void main(String[] args) {
        Inc inc = new Inc();
        int i = 0;
        inc.fermin(i);
        int a = i++;
        System.out.println(a);

    }

    void fermin(int i) {
        i++;
    }
}
