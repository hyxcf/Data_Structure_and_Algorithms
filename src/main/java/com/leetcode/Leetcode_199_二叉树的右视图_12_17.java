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
        if (root == null){
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
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

}
