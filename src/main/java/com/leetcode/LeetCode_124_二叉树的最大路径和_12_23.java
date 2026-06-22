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

    /*
        二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中至多出现一次 。该路径至少包含一个节点，且不一定经过根节点。
        路径和 是路径中各节点值的总和。
        给你一个二叉树的根节点 root ，返回其最大路径和 。
        示例 1：
            输入：root = [1,2,3]
            输出：6
            解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
        示例 2：
            输入：root = [-10,9,20,null,null,15,7]
            输出：42
            解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
     */
    static class Preview_2_5 {
        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] maxSum = new int[1];
            dfs(root, maxSum);
            return maxSum[0];
        }

        /*
        fixme:
            ✅ dfs(node, maxSum) 的返回值是：
            “从当前 node 向下延伸（只能选左或右）所能得到的最大单边路径和”
            （注意：必须包含 node 本身）
            但这 不是 最终答案！最终答案是通过 maxSum[0] 记录的 可能包含左右子树的完整路径。
         */
        private int dfs(TreeNode node, int[] maxSum) {
            if (node == null) {
                return 0;
            }
            int leftSum = Math.max(0, dfs(node.left, maxSum));
            int rightSum = Math.max(0, dfs(node.right, maxSum));
            maxSum[0] = Math.max(maxSum[0], leftSum + rightSum + node.val);
            return Math.max(leftSum + node.val, rightSum + node.val);
        }
    }

    // 二叉树的最大路径和
    private static class Preview_2_25 {
        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] res = new int[]{Integer.MIN_VALUE}; // todo: 关键修复！这里如果只有一个节点且为负数，如果不设置为最小值，则得到的结果是0是错误的
            dfs(root, res);
            return res[0];
        }

        private int dfs(TreeNode root, int[] res) {
            if (root == null) {
                return 0;
            }
            int leftSum = Math.max(dfs(root.left, res), 0);
            int rightSum = Math.max(dfs(root.right, res), 0);
            res[0] = Math.max(res[0], leftSum + rightSum + root.val);
            return Math.max(leftSum + root.val, rightSum + root.val);
        }
    }

    /*
        二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中至多出现一次 。该路径至少包含一个节点，且不一定经过根节点。
        路径和 是路径中各节点值的总和。
        给你一个二叉树的根节点 root ，返回其最大路径和 。
        示例 1：
            输入：root = [1,2,3]
            输出：6
            解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
        示例 2：
            输入：root = [-10,9,20,null,null,15,7]
            输出：42
            解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
     */
    static class Preview_3_19 {
        public int maxPathSum(TreeNode root) {
            if (root == null) return 0;
            int[] res = new int[]{Integer.MIN_VALUE};
            dfs(root, res);
            return res[0];
        }

        private int dfs(TreeNode root, int[] res) {
            if (root == null) return 0;
            int leftSum = Math.max(dfs(root.left, res), 0);
            int rightSum = Math.max(dfs(root.right, res), 0);
            res[0] = Math.max(res[0], leftSum + rightSum + root.val);
            return Math.max(leftSum + root.val, rightSum + root.val);
        }
    }


    /*
        124. 二叉树中的最大路径和
            二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
            路径和 是路径中各节点值的总和。
            给你一个二叉树的根节点 root ，返回其 最大路径和 。
     */
    static class Preview_4_11 {
        public int maxPathSum(TreeNode root) {
            if (root == null) return 0;
            int[] res = new int[]{Integer.MIN_VALUE};
            dfs(root, res);
            return res[0];
        }

        private int dfs(TreeNode root, int[] res) {
            if (root == null) return 0;
            int leftSum = Math.max(dfs(root.left, res), 0);
            int rightSum = Math.max(dfs(root.right, res), 0);
            res[0] = Math.max(res[0], leftSum + rightSum + root.val);
            return Math.max(leftSum, rightSum) + root.val;
        }
    }
}
