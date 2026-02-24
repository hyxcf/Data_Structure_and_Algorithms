package com.leetcode;

public class LeetCode_226_翻转二叉树_12_11 {

    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private void fn(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        fn(root.left);
        fn(root.right);
    }

    static class Preview_2_3 {
        // 翻转二叉树
        public TreeNode invertTree(TreeNode root) {
            reverse(root);
            return root;
        }

        private void reverse(TreeNode root) {
            if (root == null) {
                return;
            }
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            reverse(root.left);
            reverse(root.right);
        }
    }

    private static class Preview_2_24 {
        // 翻转二叉树
        public TreeNode invertTree(TreeNode root) {
            reverse(root);
            return root;
        }

        private void reverse(TreeNode node) {
            if (node == null) {
                return;
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            reverse(node.left);
            reverse(node.right);
        }

    }

}
