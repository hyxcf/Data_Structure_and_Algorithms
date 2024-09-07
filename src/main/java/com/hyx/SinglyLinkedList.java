package com.hyx;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * hyx
 * 单向链表
 */
public class SinglyLinkedList implements Iterable<Integer> { // 整体


    // 哨兵节点
    private Node head = new Node(666,null); // 头指针

    /**
     * 向链表的头部添加
     *
     * @param value 待添加值
     */
    public void addFirst(int value) {
        insert(0,value);
    }

    /**
     * 找到尾节点的位置
     *
     * @return
     */
    private Node findLast() {
        Node p;
        for (p = head; p.next != null; p = p.next) {
        }
        return p;
    }

    /**
     * 向链表的尾部添加
     */
    public void addLast(int value) {
        Node last = findLast();
/*        if (last == null) {
            addFirst(value);
            return;
        } 加入哨兵节点，就可以省略此部分*/
        last.next = new Node(value, null);
    }

    /**
     * 遍历链表 - while
     */
    public void loop1(Consumer<Integer> consumer) {
        Node p = head.next;
        while (p != null) {
//            System.out.println(p.value);
            consumer.accept(p.value);
            p = p.next;

        }
    }

    /**
     * 遍历链表 - for
     */
    public void loop2(Consumer<Integer> consumer) {
        for (Node p = head.next; p != null; p = p.next) {
            consumer.accept(p.value);
        }
    }

    /**
     * 递归遍历
     */
    public void loop3(Consumer<Integer> before,
                      Consumer<Integer> after){
        recursion(head,before,after);
    }

    private void recursion(Node curr,
                           Consumer<Integer> before,
                           Consumer<Integer> after){ // 某个节点要进行的操作
        if (curr == null){
            return;
        }
        before.accept(curr.value);
        recursion(curr.next, before, after);
        after.accept(curr.value);
    }


    /**
     * 遍历链表 - Iterator
     *
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new NodeIterator();
    }

    // 什么时候加 static
    // 当内部类调用外部类的成员变量时不加 static
    // 当类中调用的都是自己的变量是可以加 static （还是对static的理解）
    // static 关键字只能调用 静态属性和静态方法
    private class NodeIterator implements Iterator<Integer> {
        Node p = head.next;

        @Override
        public boolean hasNext() {
            return p != null;
        }

        @Override
        public Integer next() {
            Integer value = p.value;
            p = p.next;
            return value;
        }
    }


    /**
     * 节点类
     */
    private static class Node {
        Integer value;
        Node next;

        public Node(Integer value, Node next) {
            this.value = value;
            this.next = next;
        }
    }


    private Node findNode(int index) {
        int i = -1;
        for (Node p = head.next; p != null; p = p.next, i++) {
            if (i == index) {
                return p;
            }
        }
        return null;
    }

    /**
     * 根据索引位置查找
     * @param index
     * @return
     */
    public int get(int index) {
        Node node = findNode(index);
        if (node == null) {
            // 抛异常
            throw illegalIndex(index);
        }
        return node.value;
    }

    private static IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(
                String.format("index [%d] 不合法%n", index)
        );
    }


    /**
     * 向索引位置插入
     */
    public void insert(int index, int value) {
        Node prev = findNode(index - 1);// 找到上一个节点
        if (prev == null){ // 找不到
            // 抛异常
            throw illegalIndex(index);
        }
        prev.next = new Node(value,prev.next);
    }

    /**
     * 删除节点（第一个）
     */
    public void removeFirst(){
        remove(0);
    }

    public void remove(int index){
        if (index == 0){
            removeFirst();
            return;
        }
        Node prev = findNode(index - 1); // 上一个节点
        if (prev == null){
            throw illegalIndex(index);
        }
        Node removed = prev.next;
        if (removed == null){
            throw illegalIndex(index);
        }
        prev.next = removed.next;

    }


}


