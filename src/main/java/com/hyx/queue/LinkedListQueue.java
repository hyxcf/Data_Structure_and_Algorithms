package com.hyx.queue;

import java.nio.file.Paths;
import java.util.Iterator;

/**
 * 以单向环形带哨兵链表方式来实现队列
 * @author hyx
 */
public class LinkedListQueue<E> implements Queue<E>, Iterable<E> {

    private static class Node<E>{
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head = new Node<>(null, null);
    private Node<E> tail = head;
    private int size; // 节点数
    private int capacity = Integer.MAX_VALUE; // 容量

    {
        tail.next = head;
    }

    // 设置带参构造，指定容量
    public LinkedListQueue(int capacity) {
        this.capacity = capacity;
    }

    public LinkedListQueue() {
    }

    @Override
    public boolean offer(E value) {
        // 判断是否需要进行扩容
        if (isFull()) {
            // 添加失败，需要进行扩容
            return false;
        }
        // 新节点的 next 指向 head
        Node<E> added = new Node<>(value, head);
        // 原来的尾巴指向新节点
        tail.next = added;
        // 将尾巴移动到新节点
        tail = added;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        Node<E> first = head.next;
        head.next = first.next;
        if (tail == first){
            tail = head;
        }
        size--;
        return first.value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.next.value;
    }
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;
            @Override
            public boolean hasNext() {
                return p != head;
            }
            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }

}
