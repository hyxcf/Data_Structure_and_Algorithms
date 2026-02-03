package com.leetcode;

public class LeetCode_101_对称二叉树_12_11 {

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return check(left.left, right.right) && check(left.right, right.left);
    }

    static class Preview_2_3 {
        // 检查是否是对称二叉树
        public boolean isSymmetric(TreeNode root) {
            return checkSymmetric(root.left, root.right);
        }

        private boolean checkSymmetric(TreeNode left, TreeNode right) {
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            if (left.val != right.val) {
                return false;
            }
            return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
        }

    }

}
