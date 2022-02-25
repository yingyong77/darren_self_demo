package com.leetcode.treeDepth;

import lombok.Data;

/**
 * 树结构
 *
 * @author : darren
 * @date : 2022/2/25
 */
@Data
public class TreeNode {

    int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
