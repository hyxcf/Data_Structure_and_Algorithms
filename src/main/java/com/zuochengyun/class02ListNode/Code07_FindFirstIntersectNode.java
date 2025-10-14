package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

/**
 * 判断两个链表是否相交，如果相交则找出他们相交的第一个节点
 */
public class Code07_FindFirstIntersectNode {


    public static ListNode getIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }

        return null;// 一个链表有环，一个链表无环，则注定不会相交
    }

    private static ListNode getLoopNode(ListNode head) {
        ListNode l = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            l = l.next;
            f = f.next.next;
            if (l == f) {
                f = head;
                while (l != f) { // 进到这里就说明已经有环了，不会出现死循环的情
                    l = l.next;
                    f = f.next;
                }
                return l;
            }
        }
        return null;
    }

    /**
     * 两个  有环链表  的相交问题
     * 有三种情况：
     * 1.两个链表都成环，但是不相交
     * 2.两个链表都成环，且入环节点是同一个
     * 3.两个链表都成环，且有两个入环节点
     */
    public static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        ListNode cur1 = null;
        ListNode cur2 = null;
        if (loop1 == loop2) {  // 这是第二种情况：两个链表都成环，且入环节点是同一个
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = loop1.next;
            while (cur1 != loop1) { // 这里是让一个链表继续绕着环跑，如果遇见了第二个环的入环节点，则是第三种情况，否则就是第一种情况
                if (cur1 == loop2) { // 第三种情况：两个链表都成环，且有两个入环节点
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null; // 第一种情况：两个链表都成环，但是不相交
        }
    }


    /**
     * 两个  无环链表  的相交问题
     * 这个方法的整体思路：
     * 1.两个链表一起走，统计走的步数
     * 2.如果两个链表走到最后，他们的内存地址不相等的话，则表明他们两个链表并不相交
     * 3.如果他们相交，则获取他们相差的链表长度，让长链表走差值步，然后两者同时走，相交的就是第一个节点
     */
    public static ListNode noLoop(ListNode p1, ListNode p2) {
        if (p1 == null || p2 == null) {
            return null;
        }
        ListNode cur1 = p1;
        ListNode cur2 = p2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null; // 如果两个链表走到最后，他们的内存地址不相等的话，则表明他们两个链表并不相交
        }
        // n：链表1长度减去链表2长度的值
        cur1 = n > 0 ? p1 : p2; // 谁长，谁的头变为cur1
        cur2 = cur1 == p1 ? p2 : p1;
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
}
