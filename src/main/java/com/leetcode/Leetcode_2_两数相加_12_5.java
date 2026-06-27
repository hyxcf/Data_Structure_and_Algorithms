package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

public class Leetcode_2_两数相加_12_5 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode s = new ListNode(0);
        ListNode cur = s;
        int num = 0; // 进位数
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            // 要考虑进位的情况
            int sum = x + y + num;
            // 计算进位数
            num = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 如果最后两个数相加的时候有进位数，就将进位数赋予链表的新节点
        // 两个数相加最多小于20，所以进位数的值最多为1
        if (num == 1) {
            cur.next = new ListNode(num);
        }
        return s.next;
    }

    private static class Preview_2_7 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode s = new ListNode(0);
            ListNode cur = s;
            int num = 0;
            while (l1 != null || l2 != null) {
                int x = l1 != null ? l1.val : 0;
                int y = l2 != null ? l2.val : 0;
                // 要考虑进位的情况
                int sum = x + y + num;
                num = sum / 10;
                sum = sum % 10;
                cur.next = new ListNode(sum);
                cur = cur.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (num == 1) {
                cur.next = new ListNode(num);
            }
            return s.next;
        }
    }

    private static class Preview_3_14 {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode node = new ListNode(0);
            ListNode cur = node;
            int num = 0;
            while (l1 != null || l2 != null) {
                int x = l1 != null ? l1.val : 0;
                int y = l2 != null ? l2.val : 0;
                // 要考虑进位的问题
                int sum = x + y + num;
                num = sum / 10;
                sum = sum % 10;
                cur.next = new ListNode(sum);
                cur = cur.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (num == 1) {
                cur.next = new ListNode(num);
            }
            return node.next;
        }
    }

    private static class Preview_6_27 {
        // 两数相加
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode node = new ListNode(0);
            ListNode cur = node;
            int num = 0; // 临时变量用于存储上一步的进位数
            while (l1 != null || l2 != null) {
                int x = l1 != null ? l1.val : 0;
                int y = l2 != null ? l2.val : 0;
                int tempSum = x + y + num;
                num = tempSum / 10;
                tempSum = tempSum % 10;
                cur.next = new ListNode(tempSum);
                cur = cur.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            if (num == 1) {
                cur.next = new ListNode(num);
            }
            return node.next;
        }
    }

}
