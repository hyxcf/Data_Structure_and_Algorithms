package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_142_环形链表2_12_4 {

    /**
     * 判断链表中是否有环，如果有环找到入环的第一个节点
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
                while (true){
                    if (l == f){
                        return l;
                    }
                    f = f.next;
                    l = l.next;
                }
            }
        }
        return null;
    }
    
}
