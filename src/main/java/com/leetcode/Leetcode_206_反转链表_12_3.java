package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

public class Leetcode_206_反转链表_12_3 {

    // 方法1：新增一个链表
    public ListNode reverseListNode1(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode p = null;
        while (node != null) {
            ListNode tempNestListNode = node.next;
            node.next = p;
            p = node;
            node = tempNestListNode;
        }
        return p;
    }


    // 方法2：递归 尾插法
    public ListNode reverseListNode2(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode newHead = reverseListNode2(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    // 方法4：头插法
    public ListNode reverseListNode4(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode n1 = null;
        while (node != null) {
            ListNode o2 = node.next;
            node.next = n1;
            n1 = node;
            node = o2;
        }
        return n1;
    }

    static class Preview_2_7 {
        // 三种方法
        // 1.新增一个链表p
        public ListNode reverseListNode1(ListNode node) {
            if (node == null || node.next == null) {
                return node;
            }
            ListNode p = null;
            while (node != null) {
                ListNode tempNode = node.next;
                node.next = p;
                p = node;
                node = tempNode;
            }
            return p;
        }

        // 2、尾插法 递归
        public ListNode reverseListNode2(ListNode node) {
            if (node == null || node.next == null) {
                return node;
            }
            ListNode newHead = reverseListNode2(node.next);
            node.next.next = node;
            node.next = null;
            return newHead;
        }

        // 3、头插法
        public ListNode reverseListNode3(ListNode node) {
            if (node == null || node.next == null) {
                return node;
            }
            ListNode n1 = null;
            while (node != null) {
                ListNode o2 = node.next;
                node.next = n1;
                n1 = node;
                node = o2;
            }
            return n1;
        }
    }

}
