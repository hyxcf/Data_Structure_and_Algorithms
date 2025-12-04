package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_234_回文链表_12_3 {

    // 方法2
    // 将方法1的步骤1和步骤2进行合并，在一次循环里既找到中间点，也把前半个链表进行反转。然后进行步骤3 ，比较两个链表
    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode low = head;
        ListNode fast = head;
        ListNode n1 = null;
        ListNode o1 = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
            o1.next = n1;
            n1 = o1;
            o1 = low;
        }
        if (fast != null) {
            low = low.next;
        }
        while (n1 != null) {
            if (n1.val != low.val) {
                return false;
            }
            low = low.next;
            n1 = n1.next;
        }
        return true;
    }

    public static boolean isPalindrome(ListNode p1) {
        if (p1 == null || p1.next == null) return false;
        // 1.先找到中间节点
        // 2.反转节点依次进行比较
        ListNode mid = middle(p1);
        ListNode newHead = reverseListNode(mid);
        while (newHead != null) {
            if (newHead.val != p1.val) {
                return false;
            }
            newHead = newHead.next;
            p1 = p1.next;
        }
        return true;
    }

    private static ListNode reverseListNode(ListNode o1) {
        ListNode n1 = null;
        while (o1 != null) {
            ListNode nextNode = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = nextNode;
        }
        return n1;
    }

    private static ListNode middle(ListNode p1) {
        ListNode low = p1;
        ListNode fast = p1;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }
}
