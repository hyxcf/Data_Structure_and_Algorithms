package com.hyx.leetcode.treenode.ercha;

import com.hyx.treenode.ercha_treenode.TreeNode;

import java.util.Arrays;

/**
 * @Author：hyx
 * @Date：2024/9/24 11:46
 * 从前序与中序遍历序列构造二叉树
 */
public class E06Leetcode105 {

    /*
        preorder = [1,2,4,3,6,7]
        inorder = [4,2,1,6,3,7]
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        // 先从前序遍历中找到根节点
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        // 区分左右子树
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                // 0 ~ i-1 左子树
                // i+1 ~ arr.length - 1 右子树
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i); // 0 ~ i-1 左闭右开区间 [4,2]
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length); // [6,3,7]

                int[] preLeft = Arrays.copyOfRange(preorder, 1, i + 1); // [2,4]
                int[] preRight = Arrays.copyOfRange(preorder, i + 1, preorder.length);// [3,6,7]

                root.left = buildTree(preLeft, inLeft);
                root.right = buildTree(preRight, inRight);
                break;
            }
        }
        return root;
    }
}
