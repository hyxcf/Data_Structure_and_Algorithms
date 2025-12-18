package com.leetcode;

public class LeetCode_114_二叉树展开为链表_12_18 {

    /**
     * 核心思想就是，将当前节点的左子树挪到当前节点的右子树上，然后找到右子树的下一个节点，继续如此操作
     * 就是将右边最小的放到左边最大的下面，然后变成右子树，循环操作
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

}
