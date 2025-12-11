package com.leetcode;

public class LeetCode_226_翻转二叉树_12_11 {
    
    public TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }

    private void fn(TreeNode root) {
        if (root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        fn(root.left);
        fn(root.right);
    }

}
