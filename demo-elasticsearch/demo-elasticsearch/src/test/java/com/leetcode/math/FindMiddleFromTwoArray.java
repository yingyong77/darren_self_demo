package com.leetcode.math;

/**
 * 求两个正序数组的中位数
 *
 * @author : darren
 * @date : 2022/3/7
 */
public class FindMiddleFromTwoArray {

    public static void main(String[] args) {


    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1[nums1.length] < nums2[0]) {
            System.arraycopy(nums1, 0, nums2, 0, nums1.length);
        }
        

        return 0;
    }
}
