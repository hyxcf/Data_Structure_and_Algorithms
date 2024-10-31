package com.hyx2.basic_data_structure.mianshiti.listnode;

import java.util.HashMap;

/**
 * @version 0.1
 * @Author hyx
 * @className CopyListWithRand
 * @Date 2024/10/31  23:45
 * @description
 * @since jdk 11
 */
/*
    rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
    给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
    【要求】时间复杂度O(N)，额外空间复杂度O(1)
 */

public class CopyListWithRand {
    public static class Node {
        int value;
        Node next;
        Node rand;

        Node(int val) {
            value = val;
        }
    }

    /**
     * 使用 hash 表解决
     *
     * @param head 头节点
     * @return Node
     */
    public static Node CopyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 将所有的节点都拷贝一份
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 将克隆出来的节点指针，分别指向 next 和 rand
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 核心：就是人为的将 节点对象 和 克隆节点 制造了对应关系

    /**
     * @param head 头节点
     * @return Node
     */
    public static Node CopyListWithRand2(Node head) {
        if (head == null) {
            return null;
        }
        // 将每一个节点进行克隆，并插入到被克隆节点的后面 1 -> 1' -> 2 -> 2'
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        // set copy node rand
        // 1 -> 1' -> 2 -> 2'
        // 先搞定克隆节点的 rand 指针
        while (cur != null) {
            // cur 老
            // cur.next 新 copy
            next = cur.next.next;
            // 将克隆的 rand 指针指向 被克隆节点的 rand 的克隆节点的 rand 指针
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null; // cur.rand.next : 这样就灵活的使用了对应关系
            cur = next;
        }
        // he
        Node res = head.next;
        cur = head;
        // split rand指针不用动，只需要分离 next 指针
        while (cur != null) {
            next = cur.next.next;

            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;

            cur = next;
        }
        return res;
    }
}























