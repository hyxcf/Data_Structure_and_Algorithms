package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

/**
 * 删除排序链表中的重复元素
 */
public class DeleteDuplicatesNodes {

    // 递归
    public ListNode deleteDuplicatesNode(ListNode p) {
        if (p == null || p.next == null) {
            return p;
        }
        // 如果重复的情况
        if (p.val == p.next.val) {
            ListNode x = p.next.next;
            while (x != null && x.val == p.val) {
                x = x.next;
            }
            return deleteDuplicatesNode(x);
        } else {
            // 不重复的情况
            p.next = deleteDuplicatesNode(p.next);
            return p;
        }
    }

    // 采用三个节点指针
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 定义一个哨兵节点
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel;
        ListNode p2, p3;
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            // 判断是否p2,p3相同
            if (p2.val == p3.val) {
                while ((p3 = p3.next) != null && p3.val == p2.val) {
                }
                // 找到了不重复的值
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }
        return sentinel.next;
    }
}
