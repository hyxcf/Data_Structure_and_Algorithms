package com.leetcode;

import com.hyx.leetcode.listnode.ListNode;

public class Leetcode_19_删除链表的倒数第N个结点_12_5 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode s = new ListNode(-1,head);
        ListNode p1 = s;
        ListNode p2 = s;
        for (int i = 0; i < n + 1; i++){
            p1 = p1.next;
        }
        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return s.next;
    }
    
    /*
        🔍 核心思想：双指针 + 固定间距
        我们要删除倒数第 n 个节点，等价于：
        找到正数第 (L - n + 1) 个节点（L 是链表长度），然后删除它。  
        但如果我们不知道 L（链表长度），怎么办？
        
        💡 技巧：让两个指针保持 n+1 的距离。当快指针走到末尾（null）时，慢指针正好在要删除节点的前一个位置！
        
        🧩 为什么用虚拟头节点（哨兵）？
        考虑边界情况：删除头节点（比如 n = L）
        例如：[1,2], n = 2 → 要删除 1
        没有虚拟头时，你需要特殊处理“删除头节点”的逻辑。
        有了 s，p2 可以自然停在 s 上，直接 s.next = s.next.next 即可。
     */
}
