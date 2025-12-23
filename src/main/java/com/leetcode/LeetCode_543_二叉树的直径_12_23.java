package com.leetcode;

public class LeetCode_543_二叉树的直径_12_23 {

    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = {Integer.MIN_VALUE};
        dfs(root, max);
        return max[0];
    }

    private int dfs(TreeNode node, int[] max) {
        if (node == null) {
            return -1;
        }
        int lLen = dfs(node.left, max) + 1;
        int rLen = dfs(node.right, max) + 1;
        max[0] = Math.max(max[0], lLen + rLen);
        return Math.max(lLen, rLen);
    }

    class Solution {
        private int ans;

        public int diameterOfBinaryTree(TreeNode root) {
            dfs(root);
            return ans;
        }

        private int dfs(TreeNode node) {
            if (node == null) {
                return -1;
            }
            int lLen = dfs(node.left) + 1;
            int rLen = dfs(node.right) + 1;
            ans = Math.max(ans, lLen + rLen);
            return Math.max(lLen, rLen);
        }
    }
}
