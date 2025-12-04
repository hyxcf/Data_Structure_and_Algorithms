package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_141_环形链表_12_4 {

    /**
     * 判断链表中是否有环
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
    
}
