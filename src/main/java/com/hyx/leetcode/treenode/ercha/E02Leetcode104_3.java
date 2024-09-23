package com.hyx.leetcode.treenode.ercha;

import com.hyx.ercha_treenode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author：hyx
 * @Date：2024/9/23 19:13
 * 二叉树最大深度 - 使用层序遍历
 */
@SuppressWarnings("all")
public class E02Leetcode104_3 {

    /*
        思路：
            1.使用层序遍历，层数即最大深度
     */
    public static int maxDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(root);
        int layer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                System.out.print(poll.val + "\t");
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            layer++;
            System.out.println();
        }
        return layer;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        System.out.println(maxDepth(root));
    }

}
