package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

/**
 * 合并两个有序链表
 */
public class MergeTwoListsNodes {


    // 方法1：新增一个链表p
    public static ListNode mergeTwoLists(ListNode p1, ListNode p2) {
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

    /**
     * 1    ->  2   ->  4
     * 1    ->  3   ->  4
     */
    // 方法2：递归
    public static ListNode mergeTwoLists2(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }
        if (p1.val < p2.val) {
            p1.next = mergeTwoLists2(p1.next, p2);
            return p1; // return 是返回“从当前状态开始，合并后的新链表的头节点”
        } else {
            p2.next = mergeTwoLists2(p1, p2.next);
            return p2; // return 是返回“从当前状态开始，合并后的新链表的头节点”
        }
    }
}
