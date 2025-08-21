package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

public class CycleNodes {

    public static void main(String[] args) {

    }

    // 环形链表，快慢指针法
    public static boolean hasCycle(ListNode head) {
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

    // 寻找环形链表的第一个节点
    public ListNode findCycleNode(ListNode node) {
        ListNode l = node;
        ListNode f = node;
        while (f != null && f.next != null) {
            l = l.next;
            f = f.next.next;
            if (f == l) {
                l = node;
                while (true) {
                    if (l == f) {
                        return l;
                    }
                    l = l.next;
                    f = f.next;
                }
            }
        }
        return null;
    }
}
