package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 15:53
 * 验证二叉搜索树
 */

public class E04Leetcode98_03 {
    /*
        能否值判断父亲比左孩子大，比右孩子小？
     */
    public boolean isValidBST(TreeNode root) {
        return doValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean doValid(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return doValid(node.left, min, node.val) && doValid(node.right, node.val, max);
    }

}
