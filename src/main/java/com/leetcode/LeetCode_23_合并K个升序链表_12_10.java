package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class LeetCode_23_合并K个升序链表_12_10 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return spilt(lists, 0, lists.length - 1);
    }

    private ListNode spilt(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = (left + right) >>> 1;
        ListNode p1 = spilt(lists, left, mid);
        ListNode p2 = spilt(lists, mid + 1, right);
        return mergeTwoList(p1, p2);
    }

    private ListNode mergeTwoList(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
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
        cur.next = p1 != null ? p1 : p2;
        return s.next;
    }

    /*
        与148排序链表 🆚 对比总结
        | 特性 | 归并排序（单链表） | 合并 K 个链表 |
        |------|------------------|-------------|
        | 分治对象 | 单个链表的节点序列 | `lists` 数组中的链表引用 |
        | 区间类型 | 指针区间 `[head, tail)`（左闭右开） | 数组索引区间 `[left, right]`（闭区间） |
        | 为什么这样设计？ | 链表无法随机访问，需用 `tail` 标记结束位置 | 数组支持随机访问，天然用下标 |
        | 是否修改原链表？ | ✅ 会重连 `next` 指针（但复用节点） | ❌ 不修改任何链表结构，只合并 |
        | 终止条件 | `head.next == tail`（只剩一个节点） | `left == right`（只剩一个链表） |
        💡 一句话理解本质区别：
        归并排序 是在 “切一根长绳子” → 需要标记“从哪切到哪” → 用 [start, end)
        合并 K 链表 是在 “合并一堆独立的绳子” → 只需说“合并第 i 到第 j 根” → 用 [i, j]
        ✅ 回答你的核心疑问
        “我的意思是这个是左闭右开的，而合并K个升序链表则不是”
        ✅ 完全正确！
        你已经敏锐地捕捉到了两种分治策略在抽象层次上的根本差异：
        一个是对连续内存/结构的分割（需左闭右开）
        一个是离散元素集合的划分（用普通闭区间即可）
        这是高级算法思维的体现！👏
     */

    /*
        给你一个链表数组，每个链表都已经按升序排列。
        请你将所有链表合并到一个升序链表中，返回合并后的链表。
        示例 1：
            输入：lists = [[1,4,5],[1,3,4],[2,6]]
            输出：[1,1,2,3,4,4,5,6]
            解释：链表数组如下：
            [
              1->4->5,
              1->3->4,
              2->6
            ]
            将它们合并到一个有序链表中得到。
            1->1->2->3->4->4->5->6
        示例 2：
            输入：lists = []
            输出：[]
        示例 3：
            输入：lists = [[]]
            输出：[]
     */
    private static class Preview_2_9 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) {
                return null;
            }
            return spilt2(lists, 0, lists.length - 1);
        }

        private ListNode spilt2(ListNode[] lists, int head, int tail) {
            if (head == tail) {
                return lists[head];
            }
            int mid = (head + tail) >>> 1;
            ListNode p1 = spilt2(lists, head, mid);
            ListNode p2 = spilt2(lists, mid + 1, tail);
            return merge(p1, p2);
        }

        private ListNode merge(ListNode p1, ListNode p2) {
            ListNode s = new ListNode(-1, null);
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
            cur.next = p1 != null ? p1 : p2;
            return s.next;
        }
    }

    // 合并 k 个升序链表，归并排序
    private static class Preview_3_16 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return spilt(lists, 0, lists.length - 1);
        }

        private ListNode spilt(ListNode[] lists, int head, int tail) {
            if (head == tail) {
                return lists[head];
            }
            int mid = (head + tail) >>> 1;
            ListNode p1 = spilt(lists, head, mid);
            ListNode p2 = spilt(lists, mid + 1, tail);
            return merge(p1, p2);
        }

        private ListNode merge(ListNode p1, ListNode p2) {
            ListNode sentinel = new ListNode(-1, null);
            ListNode p = sentinel;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            if (p1 != null) {
                p.next = p1;
            }
            if (p2 != null) {
                p.next = p2;
            }
            return sentinel.next;
        }
    }

    // 合并k个有序列表
    private static class Preview_7_1 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return spilt(lists, 0, lists.length - 1);
        }

        private ListNode spilt(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            int mid = (left + right) >>> 1;
            ListNode p1 = spilt(lists, left, mid);
            ListNode p2 = spilt(lists, mid + 1, right);
            return merge(p1, p2);
        }

        private ListNode merge(ListNode p1, ListNode p2) {
            ListNode s = new ListNode(-1, null);
            ListNode p = s;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            if (p1 != null) {
                p.next = p1;
            }
            if (p2 != null) {
                p.next = p2;
            }
            return s.next;
        }
    }

}
