package com.hyx.queue;

import java.util.Iterator;

/**
 * 使用环形数组模拟队列
 *
 * @author hyx
 */
public class ArrayQueue1<E> implements Queue<E>, Iterable {

    private E[] array;
    private int head = 0; // 头指针
    private int tail = 0; // 尾指针,数组中的最后一块区域是存放尾指针的

    @SuppressWarnings("all")
    public ArrayQueue1(int capacity) {
        array = (E[]) new Object[capacity + 1]; // 数组中的最后一块区域是存放尾指针的
    }

    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % array.length;
        return value;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % array.length == head;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int p = head;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public Object next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }
}
