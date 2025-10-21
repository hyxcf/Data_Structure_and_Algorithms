package com.zuochengyun.class02ListNode;

import com.hyx.leetcode.listnode.ListNode;

/**
 * 常见面试题
 * 能不能不给单链表的头节点，只给想要删除的节点，就能做到在链表上把这个点删掉
 */
public class Test07 {


    public static void main(String[] args) {
        ListNode c = new ListNode(3, null);
        ListNode b = new ListNode(2, c);
        ListNode a = new ListNode(1, b);
        
        c = null; // 这种做法是不对的，因为 a b c 存堆中，链表的引用也是在堆上，c = null，只是取消了c指向堆的引用，实际上堆中还是链表
    }

}
