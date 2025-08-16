package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

/**
 * 反转链表的五种方法
 */
public class RevertListNodes {

    public static void main(String[] args) {

    }

    // 创建一个新链表，然后把这个链表头往新链表中进行插入
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;      // 新链表的头
        ListNode curr = head;      // 当前遍历的节点

        while (curr != null) {
            ListNode nextTempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTempNode;
        }
        return prev;  // 新的头节点
    }

    // 递归
    public static ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 双指针 n1 o1 n2
    public static ListNode reverseList3(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        ListNode n1 = o1;
        ListNode o2 = o1.next;
        while (o2 != null) {
            o1.next = o1.next.next;
            o2.next = n1;
            n1 = o2;
            o2 = o1.next;
        }
        return n1;
    }

    // 头插法
    public ListNode reverseList4(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        ListNode n1 = null;
        while (o1 != null) {
            ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = o2;
        }
        return n1;
    }
}
