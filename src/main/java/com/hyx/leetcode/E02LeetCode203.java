package com.hyx.leetcode;

import java.util.HexFormat;

/**
 * hyx
 * 根据值删除节点-力扣203
 *
 * @author 32596
 */
public class E02LeetCode203 {

    /**
     * 方法1 ：使用迭代方法
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 使用哨兵节点简化对条件的判断
        ListNode sentinel = new ListNode(-1, head);
        ListNode p1 = sentinel;
        ListNode p2 = sentinel.next;
        while (p2 != null) {
            // 找到要删除的元素
            if (p2.val == val) {
                p1.next = p2.next;
                p2 = p2.next;
            } else {
                // 没有的话，则逐步平移
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return sentinel.next;
    }


    /**
     * 方法2：递归方法
     * @param p
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode p, int val){
        if (p == null){
            return null;
        }
        // 1.若我与v相等，应该返回下一个节点递归结果
        if (p.val == val){
            return removeElements2(p.next,val);
        }else {
            // 2.若我与v不等，应该返回我，但我的next应该更新（让我能带上后续删过的节点)
            p.next = removeElements2(p.next,val);
            return p;
        }

    }


    public static void main(String[] args) {
        ListNode o5 = new ListNode(6, null);
        ListNode o4 = new ListNode(3, o5);
        ListNode o3 = new ListNode(6, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);

        System.out.println(o1);
        System.out.println(new E02LeetCode203().removeElements2(o1,6));

    }

}
