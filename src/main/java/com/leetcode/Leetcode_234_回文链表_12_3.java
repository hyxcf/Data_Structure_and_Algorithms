package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

public class Leetcode_234_回文链表_12_3 {

    // 方法2
    // 将方法1的步骤1和步骤2进行合并，在一次循环里既找到中间点，也把前半个链表进行反转。然后进行步骤3 ，比较两个链表
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode low = head;
        ListNode fast = head;
        ListNode n1 = null;
        ListNode o1 = head;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
            o1.next = n1;
            n1 = o1;
            o1 = low;
        }
        if (fast != null) {
            low = low.next;
        }
        while (n1 != null) {
            if (n1.val != low.val) {
                return false;
            }
            low = low.next;
            n1 = n1.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode p1) {
        if (p1 == null || p1.next == null) return true;
        // 1.先找到中间节点
        // 2.反转节点依次进行比较
        ListNode mid = middle(p1);
        ListNode newHead = reverseListNode(mid);
        while (newHead != null) {
            if (newHead.val != p1.val) {
                return false;
            }
            newHead = newHead.next;
            p1 = p1.next;
        }
        return true;
    }

    private ListNode reverseListNode(ListNode o1) {
        ListNode n1 = null;
        while (o1 != null) {
            ListNode nextNode = o1.next;
            o1.next = n1;
            n1 = o1;
            o1 = nextNode;
        }
        return n1;
    }

    private ListNode middle(ListNode p1) {
        ListNode low = p1;
        ListNode fast = p1;
        while (fast != null && fast.next != null) {
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }

    static class Preview_2_7 {
        public boolean isPalindrome(ListNode p1) {
            if (p1 == null || p1.next == null) {
                return true;
            }
            ListNode middle = middle(p1);
            ListNode newHead = reverseNode(middle);
            while (newHead != null) {
                if (newHead.val != p1.val) {
                    return false;
                }
                newHead = newHead.next;
                p1 = p1.next;
            }
            return true;
        }

        private ListNode reverseNode(ListNode node) {
            ListNode n1 = null;
            while (node != null) {
                ListNode tempNode = node.next;
                node.next = n1;
                n1 = node;
                node = tempNode;
            }
            return n1;
        }

        private ListNode middle(ListNode p1) {
            ListNode low = p1;
            ListNode fast = p1;
            while (fast != null && fast.next != null) {
                low = low.next;
                fast = fast.next.next;
            }
            return low;
        }

        public boolean isPalindrome2(ListNode p1) {
            // 在求中点的同时翻转链表
            if (p1 == null || p1.next == null) {
                return true;
            }
            ListNode fast = p1;
            ListNode low = p1;
            ListNode n1 = null;
            ListNode o1 = p1;
            while (fast != null && fast.next != null) {
                low = low.next;
                fast = fast.next.next;
                o1.next = n1;
                n1 = o1;
                o1 = low;
            }
            // 如果是奇数，则需往前进一位
            if (fast != null) {
                low = low.next;
            }
            while (n1 != null) {
                if (n1.val != low.val) {
                    return false;
                }
                low = low.next;
                n1 = n1.next;
            }
            return true;
        }
    }

    private static class Preview_3_20 {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            ListNode low = head;
            ListNode fast = head;
            ListNode n1 = null;
            ListNode o1 = head;
            while (fast != null && fast.next != null) {
                low = low.next;
                fast = fast.next.next;
                o1.next = n1;
                n1 = o1;
                o1 = low;
            }
            if (fast != null) {
                //说明是奇数长度，中心节点是 low， 被完美跳过，不参与比较。
                low = low.next;
            }
            while (n1 != null) {
                if (n1.val != low.val) {
                    return false;
                }
                low = low.next;
                n1 = n1.next;
            }
            return true;
        }
    }

    private static class Preview_4_7 {
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) return true;
            ListNode low = head;
            ListNode fast = head;
            ListNode n = null;
            ListNode o1 = head;
            while (fast != null && fast.next != null) {
                low = low.next;
                fast = fast.next.next;
                o1.next = n;
                n = o1;
                o1 = low;
            }
            if (fast != null) {
                low = low.next;
            }
            while (n != null) {
                if (n.val != low.val) {
                    return false;
                }
                n = n.next;
                low = low.next;
            }
            return true;
        }
    }
}
