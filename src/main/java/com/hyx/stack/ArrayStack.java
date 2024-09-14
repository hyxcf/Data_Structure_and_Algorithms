package com.hyx.stack;

import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    private final E[] array;
    private int top = 0; // 栈顶指针

    /*
        底       顶
        0  1  2  3
     */

    @SuppressWarnings("all")
    public ArrayStack(int capacity) {
        this.array = (E[]) new Object[capacity];
    }

    @Override
    public boolean push(E value) {
        if (isFull()) {
            return false;
        }
        array[top++] = value;
        return true;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return array[--top]; // top实际上指的是最后元素的后一个位置
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[top - 1];
    }

    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    @Override
    public boolean isFull() {
        return top == array.length; // 当 top 指向数组长度时，栈满
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = top;
            @Override
            public boolean hasNext() {
                return p > 0;
            }
            @Override
            public E next() {
                return array[--p];
            }
        };
    }
}