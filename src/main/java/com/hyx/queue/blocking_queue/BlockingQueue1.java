package com.hyx.queue.blocking_queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 单锁实现
 * @author hyx
 */
public class BlockingQueue1<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private int size;

    public BlockingQueue1(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition headWaits = lock.newCondition();
    private Condition tailWaits = lock.newCondition();

    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isFull()) { // 用 while 防止虚假唤醒
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException { // 毫秒
        lock.lockInterruptibly();
        try {
            long t = TimeUnit.MILLISECONDS.toNanos(timeout);
            while (isFull()) { // 用 while 防止虚假唤醒
                if (t <= 0){
                    return false;
                }
                t = tailWaits.awaitNanos(t);// 最多等待多少纳秒 返回值代表剩余时间
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            size++;
            headWaits.signal();
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (isEmpty()){
                headWaits.await();
            }
            E e = array[head];
            array[head] = null;//help GC
            if (++head == array.length){
                head = 0;
            }
            size--;
            tailWaits.signal();
            return e;
        } finally {
            lock.unlock();
        }
    }

}
