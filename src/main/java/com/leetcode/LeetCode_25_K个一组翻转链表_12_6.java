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

}
