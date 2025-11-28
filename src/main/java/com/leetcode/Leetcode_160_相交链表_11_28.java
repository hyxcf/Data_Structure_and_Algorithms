package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_160_相交链表_11_28 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int step  = 0;
        while (curA.next != null){
            curA = curA.next;
            step++;
        }
        while (curB.next != null){
            curB = curB.next;
            step--;
        }
        if (curA != curB){
            return null;
        }
        curA = step > 0 ? headA : headB;
        curB = curA == headA ? headB : headA;
        step = Math.abs(step);
        while (step != 0){
            curA = curA.next;
            step --;
        }
        while (curA != curB){
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }

}
