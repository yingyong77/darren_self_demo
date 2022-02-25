package com.leetcode.treeDepth;

/**
 * DFS算法
 *
 * @author : darren
 * @date : 2022/2/25
 */
public class DFSSolution {


    int maxLevel = 0;

    /**
     * 标志位 level
     *
     * @param treeNode
     * @return
     */
    int maxDepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        dfs(treeNode, 1);

        return maxLevel;
    }

    void dfs(TreeNode treeNode, int level) {

        if (level > maxLevel) {
            maxLevel = level;
        }
        dfs(treeNode.left, level + 1);
        dfs(treeNode.right, level + 1);
    }

}
