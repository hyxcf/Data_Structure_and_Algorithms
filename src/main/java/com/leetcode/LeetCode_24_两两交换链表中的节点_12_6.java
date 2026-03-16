package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

public class LeetCode_24_两两交换链表中的节点_12_6 {

    /**
     * 24. 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     */
    public ListNode swapPairs(ListNode head) {
        ListNode s = new ListNode(-1, head);
        ListNode temp = s;
        while (temp.next != null && temp.next.next != null) {
            ListNode p1 = temp.next;
            ListNode p2 = temp.next.next;
            temp.next = p2;
            p1.next = p2.next;
            p2.next = p1;
            temp = p1;
        }
        return s.next;
    }

    private static class Preview_2_9 {
        public ListNode swapPairs(ListNode head) {
            ListNode s = new ListNode(-1, head);
            ListNode cur = s;
            while (cur.next != null && cur.next.next != null) {
                ListNode p1 = cur.next;
                ListNode p2 = cur.next.next;
                cur.next = p2;
                p1.next = p2.next;
                p2.next = p1;
                cur = p1;
            }
            return s.next;
        }
    }

    /**
     * 24. 两两交换链表中的节点
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     */
    private static class Preview_3_16 {
        public ListNode swapPairs(ListNode head) {
            ListNode s = new ListNode(-1, head);
            ListNode cur = s;
            while (cur.next != null && cur.next.next != null) {
                ListNode p1 = cur.next;
                ListNode p2 = cur.next.next;
                cur.next = p2;
                p1.next = p2.next;
                p2.next = p1;
                cur = p1;
            }
            return s.next;
        }
    }
}
