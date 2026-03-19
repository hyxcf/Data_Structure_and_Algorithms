package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_141_环形链表_12_4 {

    /**
     * 判断链表中是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode l = head;
        ListNode f = head;
        while (f != null && f.next != null) {
            l = l.next;
            f = f.next.next;
            if (f == l) {
                return true;
            }
        }
        return false;
    }

    private static class Preview_2_7 {
        public boolean hasCycle(ListNode head) {
            ListNode low = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                low = low.next;
                fast = fast.next.next;
                if (low == fast) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class Preview_3_19 {
        public boolean hasCycle(ListNode head) {
            ListNode low = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                low = low.next;
                fast = fast.next.next;
                if (low == fast) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasCycle2(ListNode head) {
            Set<ListNode> visited = new HashSet<>();
            ListNode current = head;
            while (current != null) {
                // 如果添加失败（说明元素已存在），则有环
                if (!visited.add(current)) {
                    return true;
                }
                current = current.next;
            }

            return false;
        }
    }

}
