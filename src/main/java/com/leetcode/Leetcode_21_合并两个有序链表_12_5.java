package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_21_合并两个有序链表_12_5 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                p.next = list2;
                list2 = list2.next;
            } else {
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return s.next;
    }

    private static class Preview_2_7 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode s = new ListNode(-1, null);
            ListNode p = s;
            while (list1 != null && list2 != null) {
                if (list1.val > list2.val) {
                    p.next = list2;
                    list2 = list2.next;
                } else {
                    p.next = list1;
                    list1 = list1.next;
                }
                p = p.next;
            }
            if (list1 != null) {
                p.next = list1;
            }
            if (list2 != null) {
                p.next = list2;
            }
            return s.next;
        }
    }

}
