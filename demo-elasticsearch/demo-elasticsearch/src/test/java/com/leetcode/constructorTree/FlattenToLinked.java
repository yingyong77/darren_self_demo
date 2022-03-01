package com.leetcode.constructorTree;

import com.leetcode.treeDepth.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树展开为链表
 *
 * @author : darren
 * @date : 2022/2/28
 */
public class FlattenToLinked {

    /**
     * 递归
     *
     * @param root
     */
    public void flatten(TreeNode root) {

        List<TreeNode> treeNodes = new ArrayList<>();
        preOrderTraversal(root, treeNodes);
        int size = treeNodes.size();
        for (int i = 1; i < size; i++) {
            TreeNode pre = treeNodes.get(i - 1), curr = treeNodes.get(i);
            pre.left = null;
            pre.right = curr;
        }

    }

    /**
     * 先序遍历
     */
    public void preOrderTraversal(TreeNode treeNode, List<TreeNode> treeNodes) {
        if (treeNode != null) {
            treeNodes.add(treeNode);
        }
        preOrderTraversal(treeNode.left, treeNodes);
        preOrderTraversal(treeNode.right, treeNodes);
    }

    /**
     * 迭代算法
     */
    public static void iteration(TreeNode root) {
        List<TreeNode> treeNodes = new ArrayList<>();
        //双向链表
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                treeNodes.add(node);
                stack.push(node);
                node = node.left;
            }
            //移除队首元素  stack[9,3]
            node = stack.pop();
            node = node.right;
        }
    }

    /**
     * @param root
     */
    public static void iteration2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.left = null;
                prev.right = curr;
            }
            TreeNode left = curr.left, right = curr.right;
            if (right != null) {
                stack.push(right);
            }
            if (left != null) {
                stack.push(left);
            }
            prev = curr;
        }

    }

    public static void main(String[] args) {

        TreeNode treeNode = BuildTreeOfFrontMid.instanceTreeNode();
        iteration2(treeNode);
    }
}
