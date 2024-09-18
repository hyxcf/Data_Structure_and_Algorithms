package com.hyx.leetcode.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树Z字层序遍历（用队列实现）
 *
 * @author hyx
 */
public class E01Leetcode103 {

    /**
     * 只需要在层序遍历的基础上，对循环的次数进行奇偶判断
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        // 判断根节点为空的情况
        if (root == null) {
            return result;
        }
        // 使用队列来存储数据
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int c1 = 1; // 第一层的节点数
        boolean odd = true; // 奇数层
        // 判断队列是否为空，不为空的话，取出队列中的元素并移除
        while (!queue.isEmpty()) {
            // 构建一个队列模拟集合用来存放这一层的数据
            LinkedList<Integer> level = new LinkedList<>();
            int c2 = 0;// 判断有多少个子节点
            // 遍历节点，将队列中的元素添加到集合中
            for (int i = 0; i < c1; i++) {
                // 添加节点并移除
                TreeNode treeNode = queue.poll();
                // （要判断是从队列头部添加，还是队列尾部添加）
                if (odd) {
                    // 奇数层，从队列尾部插入
                    level.offerLast(treeNode.val);
                } else {
                    // 偶数层，从队列头部插入
                    level.offerFirst(treeNode.val);
                }
                // 判断当前节点是否还有左节点
                if (treeNode.left != null) {
                    // 添加节点到队列
                    queue.offer(treeNode.left);
                    c2++;
                }
                // 判断当前节点是否还有右节点
                if (treeNode.right != null) {
                    // 添加节点到队列
                    queue.offer(treeNode.right);
                    c2++;
                }
            }
            odd = !odd;
            // 将每层遍历的结果添加到结果集中
            result.add(level);
            c1 = c2;

        }
        return result;
    }

    /*
            1
           / \
          2   3
         / \ / \
        4  5 6  7
       / \
      8  9
        输出结果：1
                3 2
                4 5 6 7
                9 8

     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4),
                        2,
                        new TreeNode(5)
                ),
                1,
                new TreeNode(
                        new TreeNode(6),
                        3,
                        new TreeNode(7)
                )
        );
        List<List<Integer>> lists = zigzagLevelOrder(root);
        lists.forEach(System.out::println);
    }
}
