package com.leetcode;

/**
 * 求二叉树的高度
 * [3,9,20,null,null,15,7]
 *
 * @author : darren
 * @date : 2022/2/18
 */
public class TreeDepth {

    public static void main(String[] args) {

    }

    public int maxDepth(TreeNode root) {
        return 0;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

