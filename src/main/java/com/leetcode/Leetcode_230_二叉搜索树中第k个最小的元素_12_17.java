package com.leetcode;

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

}
