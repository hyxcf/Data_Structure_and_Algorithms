package com.hyx.queue.deque;

import java.util.Iterator;

/**
 * 基于循环数组实现，特点：
 * - tail 停下来的位置不存储，会浪费一个位置存放尾指针
 *
 * @author hyx
 */
public class ArrayDeque1<E> implements Deque<E>, Iterable {

    E[] array;
    int head;
    int tail;

    @SuppressWarnings("all")
    public ArrayDeque1(int capacity) {
        array = (E[]) new Object[capacity + 1];
    }

    // 确保数组中的索引不会越界
    static int inc(int i, int length) {
        if (i + 1 >= length) {
            return 0;
        }
        return i + 1;
    }

    static int dec(int i, int length) {
        if (i - 1 < 0) {
            return length - 1;
        }
        return i - 1;
    }

    /*
        h - head 头指针
        t - tail 尾指针

              h
            t
        0 1 2 3
        a b   c

        offerLast(a):先添加元素,tail++
        offerLast(b)
        offerFirst(a):先 head--,再添加元素

        head == tail 空
        head ~ tail == 数组长度 - 1 满

        pollFirst():先获取要移除的值,head++
        pollLast():先 tail--,再获取要移除的值,再移除
     */

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }
        head = dec(head, array.length);
        array[head] = e;
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (isFull()) {
            return false;
        }
        array[tail] = e;
        tail = inc(tail, array.length);
        return true;
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        // 考虑数据释放的问题，当使用的是基本数据类型可以不用置为null，当是引用数据类型时，需要置为null，以便进行垃圾回收
        array[head] = null; // help gc
        head = inc(head, array.length);
        return value;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = dec(tail, array.length);
        E e = array[tail];
        array[tail] = null; // help gc ，垃圾回收问题
        return e;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return array[dec(tail, array.length)];  // tail 是不指向元素的，tail - 1 才是最后一个元素
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /*
        h
                    t
        0   1   2   3
        a   b   c
        当 tail > head
        3-0==array.length-1
     */
    /*
            h
        t
        0   1   2   3
            c   b   a
        当 tail < head
        head - tail == 1
     */

    @Override
    public boolean isFull() {
        // 分两种情况
        if (tail > head) {
            return tail - head == array.length - 1;
        }
        if (tail < head) {
            return head - tail == 1;
        }
        return false;
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
                E e = array[p];
                p = inc(p,array.length);
                return e;
            }
        };
    }


}
