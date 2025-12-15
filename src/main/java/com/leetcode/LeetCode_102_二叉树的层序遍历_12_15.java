package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode_102_二叉树的层序遍历_12_15 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

        if (root == null) {
            return lists;
        }

        // 使用 LinkedList 来模拟队列
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        int c1 = 1;// 设置第一层的节点为1
        // 判断队列内容不为空，取出元素并添加
        while (!list.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();// 当前层数的集合
            int c2 = 0;// 下次循环的次数
            for (int i = 0; i < c1; i++) {
                // 取出队列头部元素并移除
                TreeNode treeNode = list.removeFirst();
                level.add(treeNode.val);
                // 判断当前节点是否还有左右节点，有则添加
                // 还需确定节点的个数，以便于确定下次循环的次数
                if (treeNode.left != null) {
                    list.add(treeNode.left);
                    c2++;
                }
                if (treeNode.right != null) {
                    list.add(treeNode.right);
                    c2++;
                }
            }
            c1 = c2;
            lists.add(level);
        }
        return lists;
    }
}
