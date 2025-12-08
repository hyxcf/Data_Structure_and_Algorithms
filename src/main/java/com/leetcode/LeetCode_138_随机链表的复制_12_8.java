package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_138_随机链表的复制_12_8 {

    private static class Node {
        private Node next;
        private Node random;
        private int val;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node next, Node random, int val) {
            this.next = next;
            this.random = random;
            this.val = val;
        }
    }

    /**
     * 链表的深拷贝
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> nodeMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            nodeMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            nodeMap.get(cur).next = nodeMap.get(cur.next);
            nodeMap.get(cur).random = nodeMap.get(cur.random);
            cur = cur.next;
        }
        return nodeMap.get(head);
    }
}
