package com.leetcode;

import java.util.Stack;

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

}
