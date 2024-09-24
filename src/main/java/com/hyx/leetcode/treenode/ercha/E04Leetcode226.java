package com.hyx.leetcode.treenode.ercha;

import com.hyx.ercha_treenode.TreeNode;

/**
 * @Author：hyx
 * @Date：2024/9/24 10:03
 * 翻转二叉树
 */
public class E04Leetcode226 {

    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private void fn(TreeNode node) {
        if (node == null){
            return;
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
        fn(node.left);
        fn(node.right);
    }





}
