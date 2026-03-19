package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_142_环形链表2_12_4 {

    /**
     * 判断链表中是否有环，如果有环找到入环的第一个节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode l = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            l = l.next;
            f = f.next.next;
            if (f == l) {
                f = head;
                while (true) {
                    if (l == f) {
                        return l;
                    }
                    f = f.next;
                    l = l.next;
                }
            }
        }
        return null;
    }

    private static class Preview_2_7 {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return null;

            ListNode slow = head, fast = head;

            // 第一步：找相遇点
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    // 第二步：找入口
                    fast = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow;
                }
            }
            return null;
        }
    }

    private static class Preview_3_19 {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return null;
            ListNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    fast = head;
                    while (slow != fast) {
                        slow = slow.next;
                        fast = fast.next;
                    }
                    return slow;
                }
            }
            return null;
        }
    }
}
