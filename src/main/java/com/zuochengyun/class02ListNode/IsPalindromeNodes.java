package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

/**
 * 回文链表
 */
public class IsPalindromeNodes {

    // 方法2
    // 将方法1的步骤1和步骤2进行合并，在一次循环里既找到中间点，也把前半个链表进行反转。然后进行步骤3 ，比较两个链表
    public static boolean isPalindrome2(ListNode head){
        ListNode p1 = head; //慢指针
        ListNode p2 = head; //快指针
        ListNode n1 = null;
        ListNode o1 = head;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            o1.next = n1;
            n1 = o1;
            o1 = p1;
        }                                                                                        
        if (p2 != null){
            p1= p1.next;
        }
        while(n1 != null){
            if(n1.val != p1.val){
                return false;
            }
            n1 = n1.next;
            p1 = p1.next;
        }
        return true;
    }

    // 方法1：先找到中间节点，然后再反转半个链表，逐一进行比较
    public static boolean isPalindrome(ListNode p1) {
        if (p1 == null || p1.next == null) {
            return false;
        }
        ListNode mid = middle(p1);
        ListNode newHead = reverseListNode(mid);
        while (newHead != null) {
            if (newHead.val != p1.val) {
                return false;
            }
            p1 = p1.next;
            newHead = newHead.next;
        }
        return true;
    }

    // 反转链表
    private static ListNode reverseListNode(ListNode o1) {
        ListNode n1 = o1;
        ListNode o2 = o1.next;
        while (o2 != null) {
            o1.next = o1.next.next;
            o2.next = n1;
            n1 = o2;
            o2 = o1.next;
        }
        return n1;
    }

    private static ListNode middle(ListNode p1) {
        ListNode low = p1;
        ListNode fast = p1;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next;
        }
        return low;
    }

}
