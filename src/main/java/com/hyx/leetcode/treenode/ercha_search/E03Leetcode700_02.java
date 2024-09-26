package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 12:57
 * 二叉搜索树的搜索（迭代版）
 */
public class E03Leetcode700_02 {
    // 方法1:迭代
    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode p = root;
        while (p != null && p.val != val) {
            if (val < p.val) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return p;
    }
}
