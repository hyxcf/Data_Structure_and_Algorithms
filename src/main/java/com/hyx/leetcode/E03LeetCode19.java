package com.hyx.leetcode;

/**
 * hyx
 *
 * @author 32596
 */
public class E03LeetCode19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinel = new ListNode(-1,head);
        recursion(sentinel,n);
        return sentinel.next;
    }

    // 要的是下一个节点的倒数位置，以方便节点去判断下一个节点的位置，满足条件则删除
    // 这样的话代码有一个漏洞，因为判断的都是下一个节点的位置，第一个节点没人判断，可以引入哨兵节点，简化对边界的判断
    private int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        }
        int nth = recursion(p.next, n);// 下一个节点的倒数位置
//        System.out.println(p.val + " " + nth);
        if (nth == n){
            p.next = p.next.next;
        }
        return nth + 1;
    }

    public static void main(String[] args) {
        ListNode o5 = new ListNode(6, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        System.out.println(new E03LeetCode19()
                .removeNthFromEnd(o1, 5));
    }

}
