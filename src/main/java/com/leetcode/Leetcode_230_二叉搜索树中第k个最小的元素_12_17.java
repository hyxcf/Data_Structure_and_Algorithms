package com.leetcode;

import java.util.Stack;

public class Leetcode_230_二叉搜索树中第k个最小的元素_12_17 {

    /*
    在二叉搜索树中，任意子节点都满足“左子节点 < 根节点 < 右子节点”的规则。因此二叉搜索树具有一个重要性质：二叉搜索树的中序遍历为递增序列。
    也就是说，本题可被转化为求中序遍历的第 k 个节点。
     */
    int k, res;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (k == 0) {
            return;
        }
        if (--k == 0) {
            res = root.val;
        }
        dfs(root.right);
    }

    static class Preview_2_4 {
        // fixme:求二叉搜索树中第K个最小的元素，本质上就是考理不理解中序遍历 左<中<右 的特性
        public int kthSmallest(TreeNode root, int k) {
            TreeNode p = root;
            Stack<TreeNode> stack = new Stack<>();
            int count = 0; // 用来记录中序遍历到第几个节点

            while (p != null || !stack.isEmpty()) {
                if (p != null) {
                    stack.push(p);
                    p = p.left;
                } else {
                    TreeNode pop = stack.pop();
                    // 👇 这里是中序访问点 —— 和你原来处理 prev 的位置完全一致
                    count++;
                    if (count == k) {
                        return pop.val; // 找到第 k 小，直接返回
                    }
                    p = pop.right;
                }
            }

            // 题目保证 1 <= k <= 节点总数，所以理论上不会执行到这里
            return -1;
        }

    }

    private static class Preview_2_24 {
        // fixme:求二叉搜索树中第k个最小的元素，实际上就是考察 二叉搜索树中 中序遍历 左<中<右 的特性
        public int kthSmallest(TreeNode root, int k) {
            TreeNode p = root;
            Stack<TreeNode> stack = new Stack<>();
            int count = 0; // 用来记录中序遍历到第几个节点
            while (!stack.isEmpty() || p != null) {
                if (p != null) {
                    stack.push(p);
                    p = p.left;
                } else {
                    TreeNode pop = stack.pop();
                    count++;
                    if (count == k) {
                        return pop.val;
                    }
                    p = pop.right;
                }
            }
            return -1;
        }
    }

}
