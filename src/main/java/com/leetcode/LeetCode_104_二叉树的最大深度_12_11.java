package com.leetcode;

import java.util.Stack;

public class LeetCode_104_二叉树的最大深度_12_11 {

    // 使用非递归后序排序，栈的最大高度即为最大深度
    public int maxDepth(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        int max = 0;
        TreeNode visitedNode = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                max = Math.max(max, stack.size());
                root = root.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == visitedNode) {
                    visitedNode = stack.pop();
                } else {
                    root = peek.right;
                }
            }
        }
        return max;
    }

}
