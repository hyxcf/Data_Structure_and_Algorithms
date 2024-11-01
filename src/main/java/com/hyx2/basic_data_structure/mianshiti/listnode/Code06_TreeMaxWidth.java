package com.hyx2.basic_data_structure.mianshiti.listnode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 0.1
 * @Author hyx
 * @className Code06_TreeMaxWidth
 * @Date 2024/11/1  21:37
 * @description 二叉树的最大宽度
 * @since jdk 11
 */
public class Code06_TreeMaxWidth {

    /**
     * 获取二叉树的最大宽度
     *
     * @param head head
     * @return 最大宽度
     */
    public static int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int max = 0;
        int levelNodes = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            levelNodes++;
            if (cur == curEnd) {
                // 判断获取层数最大节点数
                max = Math.max(max, levelNodes);
                levelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }


    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}





















