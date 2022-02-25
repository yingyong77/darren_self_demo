package com.leetcode.treeDepth;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先算法
 *
 * @author : darren
 * @date : 2022/2/25
 */
public class BFSSolution {

    /**
     * 时间复杂度 O(n)
     * 空间复杂度取决于队列中存储的元素数量 最坏的情况是O(n)   height=2
     *
     * @param treeNode
     * @return
     */
    int maxDepth(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(treeNode.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans++;
        }
        return ans;
    }
}
