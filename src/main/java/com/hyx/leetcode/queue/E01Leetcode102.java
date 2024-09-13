package com.hyx.leetcode.queue;

import com.hyx.queue.LinkedListQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层序遍历（用队列实现）
 *
 * @author hyx
 */
public class E01Leetcode102 {

    /**
     * 思路：先层序遍历出链表中的值，再根据当前节点下有多少个子节点，去确定下次循环的次数
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();

        if (root == null){
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
                    c2 ++;
                }
                if (treeNode.right != null) {
                    list.add(treeNode.right);
                    c2 ++;
                }
            }
            c1 = c2;
            lists.add(level);
        }

        return lists;
    }

    /*
            1
           / \
          2   3
         / \ / \
        4  5 6  7

        头[4 5 6 7]尾
        1 2 3
        输出结果：1 2 3 4 5 6 7

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
        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
      /*  LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);

        int c1 = 1;// 当前层的节点数
        while (!queue.isEmpty()) {
            int c2 = 0;// 下一层的节点数，因为下一层的节点个数不确定，所以需要去确定下一层的节点数
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.poll();
                System.out.print(n + " ");
                // 判断节点是否有孩子
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            System.out.println();
            c1 = c2;
        }*/
    }
}
