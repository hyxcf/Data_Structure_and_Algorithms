package com.hyx2.basic_data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 0.1
 * @Author hyx
 * @className Code05_SerializeAndReconstructTree
 * @Date 2024/11/1  22:23
 * @description 二叉树的序列化和反序列化
 * @since jdk 11
 */
public class Code05_SerializeAndReconstructTree {

    // 使用层序序列化
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    ans.add(String.valueOf(head.left.val));
                    queue.add(head.left);
                } else {
                    ans.add(null);
                }
                if (head.right != null) {
                    ans.add(String.valueOf(head.right.val));
                    queue.add(head.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.isEmpty()) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }


    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }


    // 使用前序遍历，来实现二叉树的序列化
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    private static void pres(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.val));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }

    // 使用前序遍历，来实现二叉树的反序列化
    public static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.isEmpty()) {
            return null;
        }
        return preb(preList);
    }

    private static Node preb(Queue<String> preList) {
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(int val) {
            this.val = val;
        }
    }

}
