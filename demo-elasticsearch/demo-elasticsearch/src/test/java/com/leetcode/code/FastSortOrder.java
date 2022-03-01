package com.leetcode.code;

/**
 * 快排
 * 总是以最后一个数 作为区分大的放前面 小的放后面
 * 递归两个区间
 */
public class FastSortOrder {

    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8, 3, 6, 9, 5};
        doSomething(a, 0, a.length - 1);
        for (int i = 0; i <= a.length - 1; i++)
            System.out.print(a[i] + " ");
    }

    private static void doSomething(int[] a, int start, int end) {
        if (start < end) {
            int p = core(a, start, end);
            doSomething(a, start, p - 1);
            doSomething(a, p + 1, end);
        }
    }

    private static int core(int[] a, int start, int end) {
        int x = a[end];
        int i = start;
        //遍历的目的是把参与排序的这轮数中比最后一个数大的数都放到最后一个数前面
        for (int j = start; j <= end - 1; j++) {
            if (a[j] >= x) {
                swap(a, i, j);
                //每遇到一个比最后一个数大的数,最后一个数应该放的位置就+1
                //确定最后一个值应该要放的位置
                i++;
            }
        }
        //这里一交换后就把最后一个数放在了正确的位置,这样左边的数都比最后一个数大,右边的数都比最后一个数小
        swap(a, i, end);
        return i;
    }

    /**
     * 交换i和j的值
     *
     * @param a
     * @param i
     * @param j
     */
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}