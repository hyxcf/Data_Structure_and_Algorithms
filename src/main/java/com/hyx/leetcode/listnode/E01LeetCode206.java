package com.hyx.leetcode.listnode;

/**
 * hyx
 * 反转链表-力扣206
 *
 * @author 32596
 */
public class E01LeetCode206 {


    public static void main(String[] args) {
        String str = "a";
        System.out.println(str == "a");
    }

    /**
     * 方法1：构造一个新链表，从旧链表依次拿到每个节点，创建新节点添加至新链表头部，完成后新链表即是倒序的
     */
    public ListNode reverseList1(ListNode o1) {
        // 创建一个新链表
        ListNode n1 = null;
        // 创建指针
        ListNode p = o1;
        while (p != null) {
            n1 = new ListNode(p.val, n1);
            p = p.next;
        }
        return n1;
    }

    /**
     * 方法2 ：与方法1类似，构造一个新链表，从旧链表头部移除节点，添加到新链表头部，完成后新链表即是倒序的，
     * 区别在于原题目未提供节点外层的容器类，这里提供一个，另外一个区别是并不去构造新节点
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        List list1 = new List(head);
        List list2 = new List(null);
        while (true) {
            ListNode first = list1.removeFirst();
            if (first == null) {
                break;
            }
            list2.addFirst(first);
        }
        return list2.head;
    }

    static class List {
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }
    }


    /**
     * 方法3：递归
     *
     * @param p
     */
    public ListNode reverseList3(ListNode p) {
        if (p == null || p.next == null) {
            return p; // 最后节点
        }
        ListNode newHead = reverseList3(p.next);
        p.next.next = p;
        p.next = null;
        return newHead;
    }

    /**
     * 方法4：双指针
     *
     * @param o1 旧头
     * @return n1
     * o1
     * 1 -> 2 -> 3 -> 4 -> 5 -> null
     * <p>
     * old1
     * new1
     */
    public ListNode reverseList4(ListNode o1) {
        // 1.空链表 || 只有一个元素
        if (o1 == null || o1.next == null) {
            return o1;
        }
        // 旧老二
        ListNode o2 = o1.next;
        // 新头
        ListNode n1 = o1;
        while (o2 != null) {
            // 2.将 o2 从链表中断开，将 o1 -> o3
            o1.next = o1.next.next;
            // 3.将 o2 添加到链表头部
            o2.next = n1;
            // 4.并用新头指向 o2
            n1 = o2;
            // 5.将 o2 重新指向 o1.next
            o2 = o1.next;
        }
        return n1;
    }

    /**86
         * 方法5：把链表分成两部分，思路就是不断从链表2的头，往链表1的头搬移
     *
     * @param o1
     * @return
     */
    public ListNode reverseList5(ListNode o1) {
        if (o1 == null || o1.next == null) {
            return o1;
        }
        // 1.n1指向null，代表新链表一开始没有元素，o1指向原链表的首节点
        ListNode n1 = null;
        // 2.开始循环，o2指向原链表的次节点
        while (o1 != null) {
            ListNode o2 = o1.next;
            // 3.搬移 将原链表的首节点搬到 新链表的首节点
            o1.next = n1;
            // 4.指针复位 n1 = o1 , o1 = o2
            n1 = o1;
            o1 = o2;
            // 5.重复 2~4 步
        }
        // 当 o1 = null 时退出循环
        return n1;
    }


//    public static void main(String[] args) {
//        ListNode o5 = new ListNode(5, null);
//        ListNode o4 = new ListNode(4, o5);
//        ListNode o3 = new ListNode(3, o4);
//        ListNode o2 = new ListNode(2, o3);
//        ListNode o1 = new ListNode(1, o2);
//        System.out.println(o1);
//        ListNode n1 = new E01LeetCode206().reverseList5(o1);
//        System.out.println(n1);
//    }

}
