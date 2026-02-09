package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;


public class LeetCode_148_排序链表_12_8 {

    /*
        ✅ 差异仅在于“如何分割链表”——一个物理断开，一个逻辑划分。
     */
    // 自上而下的归并排序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找到中间节点
        ListNode head2 = middleNode(head);
        head = sortList(head);
        head2 = sortList(head2);
        return mergeTwoLists(head, head2);
    }

    // 找到中间节点
    private ListNode middleNode(ListNode head) {
        ListNode pre = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            pre = slow; // 记录 slow 的前一个节点
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null; // 断开 slow 的前一个节点和 slow 的连接
        return slow;
    }

    // 合并两个有序列表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode s = new ListNode();
        ListNode cur = s;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;
        return s.next;
    }

    private static class Solution2 {
        // 自顶向下的归并排序
        public ListNode sortList(ListNode head) {
            return sortList(head, null);
        }

        // fixme：这种处理方式更优雅，它没有额外分配节点内存，只是重新连接原有的节点 —— 这是链表归并排序的优势。
        public ListNode sortList(ListNode head, ListNode tail) {
            if (head == null) {
                return head;
            }
            if (head.next == tail) {
                head.next = null;
                return head;
            }
            ListNode slow = head, fast = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
            ListNode mid = slow;
            ListNode list1 = sortList(head, mid);
            ListNode list2 = sortList(mid, tail);
            ListNode sorted = merge(list1, list2);
            return sorted;
        }

        public ListNode merge(ListNode head1, ListNode head2) {
            ListNode s = new ListNode(0);
            ListNode temp = s, temp1 = head1, temp2 = head2;
            while (temp1 != null && temp2 != null) {
                if (temp1.val <= temp2.val) {
                    temp.next = temp1;
                    temp1 = temp1.next;
                } else {
                    temp.next = temp2;
                    temp2 = temp2.next;
                }
                temp = temp.next;
            }
            if (temp1 != null) {
                temp.next = temp1;
            } else if (temp2 != null) {
                temp.next = temp2;
            }
            return s.next;
        }
    }


    /**
     * 计数排序
     */
    private static class Solution3 {
        public ListNode sortList(ListNode head) {
            // 初始化 min 为整数最大值，max 为整数最小值（方便后续更新）
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            //遍历指针，初始指向表头
            ListNode pre = head;
            // 第一次遍历：找链表中所有节点值的 min 和 max
            while (pre != null) {
                if (pre.val > max) {
                    max = pre.val;
                }
                if (pre.val < min) {
                    min = pre.val;
                }
                pre = pre.next;
            }
            // 计算数值范围：range = 最大值 - 最小值 + 1（包含 min 和 max 本身）
            int range = max - min + 1;
            // 计数数组，初始值全为 0
            int[] count = new int[range];
            // 重置指针，重新遍历链表
            pre = head;
            // 第二次遍历：统计每个数值的出现次数
            while (pre != null) {
                count[pre.val - min]++;
                pre = pre.next;
            }
            // 重置指针，指向链表头
            pre = head;
            // 遍历计数数组：从索引 0 到 range-1（对应数值从 min 到 max）
            for (int i = 0; i < range; i++) {
                // 当 count[i] > 0 时，说明数值（i+min）需要出现 count[i] 次
                while (count[i] != 0) {
                    count[i]--;
                    pre.val = i + min;
                    pre = pre.next;
                }
            }
            return head;
        }
    }

    private static class Preview_2_9 {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            return sortList(head, null);
        }

        private ListNode sortList(ListNode head, ListNode tail) {
            if (head == null) {
                return head;
            }
            if (head.next == tail) {
                head.next = null;
                return head;
            }
            ListNode slow = head, fast = head;
            while (fast != tail) {
                slow = slow.next;
                fast = fast.next;
                if (fast != tail) {
                    fast = fast.next;
                }
            }
            ListNode mid = slow;
            ListNode list1 = sortList(head, mid);
            ListNode list2 = sortList(mid, tail);
            ListNode sorted = merge(list1, list2);
            return sorted;
        }

        private ListNode merge(ListNode p1, ListNode p2) {
            ListNode s = new ListNode(-1);
            ListNode cur = s;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    cur.next = p1;
                    p1 = p1.next;
                } else {
                    cur.next = p2;
                    p2 = p2.next;
                }
                cur = cur.next;
            }
            if (p1 != null) {
                cur.next = p1;
            }
            if (p2 != null) {
                cur.next = p2;
            }
            return s.next;
        }
    }

}
