package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode_199_二叉树的右视图_12_17 {

    // 两种写法
    // 1、广度优先遍历 2、深度优先遍历


    /**
     * 广度优先遍历
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (i == size - 1) res.add(root.val);
                if (root.left != null) queue.offer(root.left);
                if (root.right != null) queue.offer(root.right);
            }
        }
        return res;
    }

    /**
     * 深度优先遍历
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 1, res);
        return res;
    }

    private void dfs(TreeNode node, int depth, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (res.size() < depth) {
            res.add(node.val);
        }
        dfs(node.right, depth + 1, res);
        dfs(node.left, depth + 1, res);
    }


    static class Preview_2_4 {
        // 两种方法：1、宽度优先遍历 2、深度优先遍历
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return null;
            }
            List<Integer> res = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode temp = queue.poll();
                    if (i == size - 1) res.add(root.val);
                    if (temp.left != null) {
                        queue.offer(temp.left);
                    }
                    if (temp.right != null) {
                        queue.offer(temp.right);
                    }
                }
            }
            return res;
        }

        // 深度优先遍历，直接拿每层的最右边 fixme:这个有点不理解
        public List<Integer> rightSideView2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(root, 1, res);
            return res;
        }

        private void dfs(TreeNode node, int depth, List<Integer> res) {
            if (node == null) {
                return;
            }
            if (res.size() < depth) {
                res.add(node.val);
            }
            dfs(node.right, depth + 1, res);
            dfs(node.left, depth + 1, res);
        }
    }

    private static class Preview_2_24 {
        // 二叉树的右视图 1、深度优先遍历 2、宽度优先遍历

        // 深度优先遍历
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(root, 1, res);
            return res;
        }

        private void dfs(TreeNode node, int depth, List<Integer> res) {
            if (node == null) {
                return;
            }
            if (res.size() < depth) {
                res.add(node.val);
            }
            dfs(node.right, depth + 1, res);
            dfs(node.left, depth + 1, res);
        }

        // 宽度优先遍历，层序遍历
        public List<Integer> rightSideView2(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<Integer> res = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    root = queue.poll();
                    if (i == size - 1) res.add(root.val);
                    if (root.left != null) queue.offer(root.left);
                    if (root.right != null) queue.offer(root.right);
                }
            }
            return res;
        }

    }

}
