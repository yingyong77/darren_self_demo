package com.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 求解两数之和
 *
 * @author : darren
 * @date : 2022/3/2
 */
public class TwoSum {


    /**
     * 暴力枚举
     * 时间复杂度 最坏情况下o(N2次方)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] + nums[i] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 哈希表
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> hashTable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashTable.containsKey(target - nums[i])) {
                return new int[]{hashTable.get(target - nums[i]), i};
            }
            hashTable.put(nums[i], i);
        }
        return new int[0];
    }


    public static void main(String[] args) {
        twoSum1(new int[]{2, 6, 4, 5}, 9);
    }

}



