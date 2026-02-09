package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class LeetCode_25_K个一组翻转链表_12_6 {

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode s = new ListNode(-1, head);
        ListNode pre = s;
        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return s.next;
                }
            }
            ListNode tailNext = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = tailNext;
            pre = tail;
            head = tail.next;
        }
        return s.next;
    }

    // 反转链表
    private ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p1 = head;
        while (prev != tail) {
            ListNode tempNextNode = p1.next;
            p1.next = prev;
            prev = p1;
            p1 = tempNextNode;
        }
        return new ListNode[]{tail, head};
    }


    /*
        给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
        k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
        你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
        示例 1：
            输入：head = [1,2,3,4,5], k = 2
            输出：[2,1,4,3,5]
        示例 2：
            输入：head = [1,2,3,4,5], k = 3
            输出：[3,2,1,4,5]
     */
    private static class Preview_2_9 {
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode s = new ListNode(-1, head);
            ListNode pre = s;
            while (head != null) {
                ListNode tail = pre;
                for (int i = 0; i < k; i++) {
                    tail = tail.next;
                    if (tail == null) {
                        return s.next;
                    }
                }
                ListNode tailNext = tail.next;
                ListNode[] reversedArr = reverse(head, tail);
                head = reversedArr[0];
                tail = reversedArr[1];
                pre.next = head;
                tail.next = tailNext;
                pre = tail;
                head = tail.next;
            }
            return s.next;
        }

        private ListNode[] reverse(ListNode head, ListNode tail) {
            ListNode prev = tail.next;
            ListNode p1 = head;
            while (prev != tail) {
                ListNode tempNextNode = p1.next;
                p1.next = prev;
                prev = p1;
                p1 = tempNextNode;
            }
            return new ListNode[]{tail, head};
        }
    }

}
