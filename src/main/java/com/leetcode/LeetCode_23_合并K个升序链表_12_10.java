package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class LeetCode_23_合并K个升序链表_12_10 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return spilt(lists, 0 ,lists.length -1 );
    }

    private ListNode spilt(ListNode[] lists, int left, int right) {
        if (left == right){
            return lists[left];
        }
        int mid = (left + right) >>> 1;
        ListNode p1 = spilt(lists, left, mid);
        ListNode p2 = spilt(lists, mid + 1,right);
        return mergeTwoList(p1,p2);
    }

    private ListNode mergeTwoList(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode cur = s;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }
        cur.next = p1 != null ? p1 : p2;
        return s.next;
    }

}
