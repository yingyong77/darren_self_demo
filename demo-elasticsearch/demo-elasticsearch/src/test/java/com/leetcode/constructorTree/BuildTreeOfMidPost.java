package com.leetcode.constructorTree;

import com.leetcode.treeDepth.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 从中序和后序遍历构造二叉树
 * 中序:左中右
 * 后续:左右中
 *
 * @author : darren
 * @date : 2022/2/25
 */
public class BuildTreeOfMidPost {

    /***
     *后续遍历根的位置
     */
    int post_idx;

    int[] postorder;
    int[] inorder;

    /**
     *
     */
    Map<Integer, Integer> idx_map = new HashMap<>();

    /**
     * @return
     */
    public TreeNode helper(int in_left, int in_right) {

        if (in_left > in_right) {
            return null;
        }
        //选择post_idx的位置作为当前子树根节点
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        //根据root所在位置分为左右两颗子树
        int index = idx_map.get(root_val);
        //下标减1
        post_idx--;
        //构造右子树
        root.right = helper(index + 1, in_right);
        //构造左子树
        root.left = helper(in_left, index - 1);

        return root;
    }

    /**
     * @param inOrder
     * @param postorder
     * @return
     */
    TreeNode buildTree(int[] inOrder, int[] postorder) {

        this.postorder = postorder;
        this.inorder = inOrder;

        post_idx = postorder.length - 1;
        //建立元素 下标对应的hash表
        int idx = 0;
        for (Integer val : inOrder) {
            idx_map.put(val, idx++);
        }

        return helper(0, inOrder.length - 1);
    }
}
