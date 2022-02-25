package com.leetcode.treeDepth;

/**
 * @author : darren
 * @date : 2022/2/25
 */
public class SolutionOfRecursion {


    /**
     * 递归回朔算法
     * 时间复杂度O(n)
     * 空间复杂度O(height)=二叉树的高度 递归函数需要栈空间 栈空间取决于递归的深度
     *
     * @return
     */
    int maxDepth(TreeNode treeNode) {

        if (treeNode == null) {
            return 0;
        }
        int leftDepth = maxDepth(treeNode.left);
        int rightDepth = maxDepth(treeNode.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
