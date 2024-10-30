package com.hyx.leetcode.listnode;

/**
 * @version 0.1
 * @Author hyx
 * @className E04LeetCode027
 * @Date 2024/10/30  22:48
 * @description 回文链表
 * @since jdk 11
 */
public class E04LeetCode027 {
    /*
        给定一个链表的 头节点 head ，请判断其是否为回文链表。
        如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
        示例 1：
                1 -> 2 -> 3 -> 3 -> 2 -> 1
        输入: head = [1,2,3,3,2,1]
        输出: true
        示例 2：
                1 -> 2
        输入: head = [1,2]
        输出: false
     */
// 将方法1的步骤1和步骤2进行合并，在一次循环里既找到中间点，也把前半个链表进行反转。
// 然后进行步骤3 ，比较两个链表
    class Solution {
        public boolean isPalindrome(ListNode head) {
        /*
            步骤1:找中间点的同时反转前半个链表
            步骤2:反转的前半个链表 与 中间点开始的后半个链表 逐一比较
        */
            // 快慢指针法求得链表的中间位置
            ListNode p1 = head; // 慢
            ListNode p2 = head; // 快
            ListNode n1 = null; // 新链表的头
            ListNode o1 = head; // 旧头
            // ListNode o2 = null;
            while (p2 != null && p2.next != null) {
                p1 = p1.next;
                p2 = p2.next.next;
                // 在循环里进行前半部分的链表反转
                // o2 = o1.next; // 旧老二
                o1.next = n1;
                n1 = o1;
                o1 = p1; // 因为 o2 和 p1 走的步是一样的，可以简化
            }
            // 为什么要判断它是奇数节点，因为当它是奇数节点时，前面的链表长度 要小于 后面链表的长度
            // 怎么说明它是奇数节点，
        /*
                    p1
                                p2
        1     2     3     2     null            偶数的情况下 p2 最后指向null

                    p1
                                p2
        1     2     3     2     1    null       奇数的情况下 p2 最后指向 尾节点
        */
            if (p2 != null) { // 说明它是奇数节点
                p1 = p1.next;
            }

            // 步骤2:反转的前半个链表 与 中间点开始的后半个链表 逐一比较
            while (n1 != null) {
                if (n1.val != p1.val) {
                    return false;
                }
                n1 = n1.next;
                p1 = p1.next;
            }
            return true;
        }
    }
}
