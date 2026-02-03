package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;
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

    // 今天小保底除了白马仙人，然后30抽提前金又出了白马仙人，额，其实我是不想要一命了。我只是想垫垫卡池，结果他就出了。菲林斯随缘吧，主要是爱可菲。慢慢存吧
    static class Preview_2_3 {

        // 递归 dfs
        public int maxDepth1(TreeNode root) {
            if (root == null) return 0;
            int left = maxDepth1(root.left);
            int right = maxDepth1(root.right);
            return Math.max(left, right) + 1;
        }

        //  BFS 层序遍历
        public int maxDepth2(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 0;
            while (!queue.isEmpty()) {
                int size = queue.size(); // ⭐ 当前层有多少个节点
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.right != null) queue.offer(node.right);
                    if (node.left != null) queue.offer(node.left);
                }
                depth++; // ⭐ 处理完一层才 +1
            }
            return depth;
        }

        // 使用非递归后序遍历，栈的最大高度即为最大深度
        public int maxDepth3(TreeNode root) {
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

}
