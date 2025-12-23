package com.leetcode;


/*
    124. 二叉树中的最大路径和
        二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
        路径和 是路径中各节点值的总和。
        给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */

public class LeetCode_124_二叉树的最大路径和_12_23 {

    private int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int lVal = dfs(node.left);
        int rVal = dfs(node.right);
        res = Math.max(res, lVal + rVal + node.val);
        return Math.max(Math.max(lVal, rVal) + node.val, 0);

    }

    public static class Solution2 {
        public int maxPathSum(TreeNode root) {
            int[] maxSum = {Integer.MIN_VALUE};
            dfs(root, maxSum);
            return maxSum[0];
        }

        private int dfs(TreeNode node, int[] maxSum) {
            if (node == null) {
                return 0;
            }
            int leftSum = Math.max(0, dfs(node.left, maxSum)); // Prune left branch if its path sum is negative
            int rightSum = Math.max(0, dfs(node.right, maxSum)); // Prune right branch if its path sum is negative
            maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + node.val);
            return Math.max(leftSum + node.val, rightSum + node.val);
        }
    }
}
