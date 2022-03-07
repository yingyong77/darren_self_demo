package com.leetcode.math;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，求不含有重复字符的最长子串的长度
 *
 * @author : darren
 * @date : 2022/3/7
 */
public class CalMaxSubStr {

    public static void main(String[] args) {

        String s = "abcabcbb";

        System.out.println(lengthOfLongestSubstring2(s));
    }


    public static int lengthOfLongestSubstring(String s) {

        Set<String> set = new HashSet();

        int maxSubLength = 0, currentLength;
        for (int i = 0; i < s.length(); i++) {

            set.add(String.valueOf(s.charAt(i)));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(String.valueOf(s.charAt(j)))) {
                    break;
                } else {
                    set.add(String.valueOf(s.charAt(j)));
                }
            }
            currentLength = set.size();
            maxSubLength = Math.max(maxSubLength, currentLength);
            set.clear();
        }
        return maxSubLength;
    }


    /**
     * start表示字符串是否重复出现过
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int[] last = new int[128];
        int maxSubLength = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char index = s.charAt(i);
            start = Math.max(start, last[index]);
            maxSubLength = Math.max(maxSubLength, i - start + 1);
            last[index] = i + 1;
        }
        return maxSubLength;
    }
}
