package com.hyx.leetcode.queue;

import com.hyx.leetcode.listnode.ListNode;

import java.util.List;

/**
 * 合并K个升序列表:分治+递归
 * @author hyx
 */
public class E01Leetcode23_01 {
    /**
     * 第一种方法，使用分治的思想，将一个大链表数组拆分成多个小链表，将两个链表进行合并
     *           使用递归返回
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 判断链表数组的长度不为空
        if(lists.length == 0){
            return null;
        }
        return spilt(lists,0,lists.length - 1);
    }
    // 分治思想
    // i和j分别代表数组的左右两边界
    private ListNode spilt(ListNode[] lists,int i,int j){
        // 当 i 和 j 重合时，代表只有一个链表
        if(i == j){
            return lists[i];
        }
        int m = (i + j) >>> 1;
        ListNode left = spilt(lists,i,m);
        ListNode right = spilt(lists,m+1,j);
        // 两个数组合并
        return mergeTwoLists(left,right);
    }

    // 新增一个新链表，将链表中的数据进行比较，放入新链表中
    private ListNode mergeTwoLists(ListNode p1, ListNode p2){
        ListNode s = new ListNode(-1,null);
        ListNode p = s;
        while (p1 != null &&p2 != null){
            if (p1.val > p2.val){
                p.next = p1;
                p1 = p1.next;
            }else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null){
            p.next = p1;
        }
        if (p2 != null){
            p.next = p2;
        }
        return s.next;


        /*ListNode s = new ListNode(-1,null);
        ListNode p = s;
        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if(p1 != null){
            p.next = p1;
        }
        if(p2 != null){
            p.next = p2;
        }
        return s.next;*/
    }
}
