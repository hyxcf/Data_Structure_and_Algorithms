package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 12:57
 * 二叉搜索树的搜索（递归版）
 */
public class E03Leetcode700_01 {
    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null) {
            return null;
        }

        if (val < root.val) {
            return searchBST(root.left, val);
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return root;
        }
    }
}
