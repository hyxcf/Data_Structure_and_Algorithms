package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_160_相交链表_11_28 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int step = 0;
        while (curA.next != null) {
            curA = curA.next;
            step++;
        }
        while (curB.next != null) {
            curB = curB.next;
            step--;
        }
        if (curA != curB) {
            return null;
        }
        curA = step > 0 ? headA : headB;
        curB = curA == headA ? headB : headA;
        step = Math.abs(step);
        while (step != 0) {
            curA = curA.next;
            step--;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

    /*
        给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
        图示两个链表在节点 c1 开始相交：
        题目数据 保证 整个链式结构中不存在环。
        注意，函数返回结果后，链表必须 保持其原始结构 。
     */
    static class Preview_2_7 {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode curA = headA;
            ListNode curB = headB;
            int step = 0;
            while (curA.next != null) {
                curA = curA.next;
                step++;
            }
            while (curB.next != null) {
                curB = curB.next;
                step--;
            }
            if (curA != curB){
                return null;
            }
            curA = step > 0 ? headA : headB;
            curB = curA == headA ? headB : headA;
            step = Math.abs(step);
            while (step !=0){
                curA = curA.next;
                --step;
            }
            while (curA != curB){
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }
    }
}
