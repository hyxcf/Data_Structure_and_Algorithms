package com.hyx.queue.deque;

/**
 * @author hyx
 */
public interface Deque<E> {
    /*
        deque
        double ended queue  双端队列
     */

    boolean offerFirst(E e);
    boolean offerLast(E e);
    E pollFirst();
    E pollLast();
    E peekFirst();
    E peekLast();
    boolean isEmpty();
    boolean isFull();
}
