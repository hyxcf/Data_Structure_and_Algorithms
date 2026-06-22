package com.leetcode;

public class LeetCode_108_将有序数组转化为二叉搜索树_12_16 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int begin, int last) {
        if (begin > last) {
            return null;
        }
        int mid = begin + (last - begin) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, begin, mid - 1);
        root.right = dfs(nums, mid + 1, last);
        return root;
    }

    /*
     平衡二叉搜索树的核心特点：
        1、平衡：左右子树的高度差不超过1
        2、二叉搜索树：
            BST 的性质（对任意节点 node）：
            左子树中所有节点的值 < node.val
            右子树中所有节点的值 > node.val
            左右子树也都是 BST
     */
    static class Preview_2_4 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return dfs2(nums, 0, nums.length - 1);
        }

        private TreeNode dfs2(int[] nums, int begin, int last) {
            if (begin > last) {
                return null;
            }
            int mid = begin + (last - begin) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = dfs2(nums, begin, mid - 1);
            root.right = dfs2(nums, mid + 1, last);
            return root;
        }

    }

    private static class Preview_2_24 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return dfs(nums, 0, nums.length - 1);
        }

        private TreeNode dfs(int[] nums, int begin, int last) {
            if (begin > last) {
                return null;
            }
            int mid = begin + (last - begin) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = dfs(nums, begin, mid - 1);
            root.right = dfs(nums, mid + 1, last);
            return root;
        }
    }

    private static class Preview_3_19 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return dfs(nums, 0, nums.length - 1);
        }

        private TreeNode dfs(int[] nums, int begin, int last) {
            if (begin > last) {
                return null;
            }
            int mid = (last + begin) >>> 1;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = dfs(nums, begin, mid - 1);
            node.right = dfs(nums, mid + 1, last);
            return node;
        }
    }

    private static class Preview_4_11 {
        public TreeNode sortedArrayToBST(int[] nums) {
            return dfs2(nums, 0, nums.length - 1);
        }

        private TreeNode dfs2(int[] nums, int begin, int end) {
            if (begin > end) {
                return null;
            }
            int mid = (begin + end) >>> 1;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = dfs2(nums, begin, mid - 1);
            node.right = dfs2(nums, mid + 1, end);
            return node;
        }
    }
}
