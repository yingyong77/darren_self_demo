package com.leetcode.constructorTree;

import com.leetcode.treeDepth.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 已知前序中序遍历树
 * 时间复杂度O(n)n是树的节点个数
 * 空间复杂度 treeNode需要用到O(n)
 *
 * @author : darren
 * @date : 2022/2/25
 */
public class BuildTreeOfFrontMid {

    /**
     * 用这种方式去做遍历 效率高
     */
    private Map<Integer, Integer> indexMap;


    /**
     * @param preorder 前序遍历 中左右
     * @param inorder  中序遍历  左右中  用来计算左子树有多少个用处
     * @return
     */
    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {

        if (preorder_left > preorder_right) {
            return null;
        }
        //前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        //在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        //根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        //左子树节点数目
        int size_left_subTree = inorder_root - inorder_left;
        //递归的构造左子树,并连接到根节点
        //先序遍历中[从左边界+1开始的size_left_subTree]个元素对应了中序遍历中[从左边界开始到根节点为-1的元素]
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subTree, inorder_left, inorder_root - 1);
        //递归的构造右子树，并连接到根节点
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subTree + 1, preorder_right, inorder_root + 1, inorder_right);

        return root;
    }

    /**
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

}
