package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

/**
 * 合并k个升序链表
 */
public class mergeKListsNodes {

    // 采用递归 + 分治，将问题拆分为合并两个有序链表
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return split(lists, 0, lists.length - 1);

    }

    private static ListNode split(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        int mid = l + ((r - l) >> 1);
        ListNode left = split(lists, l, mid);
        ListNode right = split(lists, mid + 1, r);
        return mergeTwoList(left, right);
    }

    private static ListNode mergeTwoList(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return s.next;
    }

}
