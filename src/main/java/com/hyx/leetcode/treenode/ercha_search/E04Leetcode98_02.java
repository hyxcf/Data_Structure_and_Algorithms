package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 14:25
 * 验证二叉搜索树（递归版）
 */
public class E04Leetcode98_02 {

    long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        // 解法2，中序遍历递归实现
        if (root == null) {
            return true;
        }
        boolean a = isValidBST(root.left);
        if (!a) {
            return false;
        }
        // 值
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;
        return isValidBST(root.right);
    }

}
