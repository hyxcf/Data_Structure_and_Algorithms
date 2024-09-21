package com.hyx.queue.blocking_queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 双锁实现
 *
 * @author hyx
 */
@SuppressWarnings("all")
public class BlockingQueue2<E> implements BlockingQueue<E> {

    private final E[] array;
    private int head;
    private int tail;
    private AtomicInteger size = new AtomicInteger();

    public BlockingQueue2(int capacity) {
        array = (E[]) new Object[capacity];
    }

    private ReentrantLock tailLock = new ReentrantLock();
    private Condition tailWaits = tailLock.newCondition();

    private ReentrantLock headLock = new ReentrantLock();
    private Condition headWaits = headLock.newCondition();

    private boolean isEmpty() {
        return size.get() == 0;
    }

    private boolean isFull() {
        return size.get() == array.length;
    }

    @Override
    public void offer(E e) throws InterruptedException {
        tailLock.lockInterruptibly();
        int c; // 添加前元素个数
        try {
            while (isFull()) {
                tailWaits.await();
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            c = size.getAndIncrement(); // size++
        } finally {
            tailLock.unlock();
        }
        // 提高性能，减少加锁的次数,使用级联通知思想
        if (c == 0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
    }

    @Override
    public boolean offer(E e, long timeout) throws InterruptedException { // 毫秒
        tailLock.lockInterruptibly();
        int c; // 添加前元素个数
        TimeUnit.MILLISECONDS.toNanos(timeout);
        try {
            while (isFull()) {
                tailWaits.awaitNanos(timeout);
            }
            array[tail] = e;
            if (++tail == array.length) {
                tail = 0;
            }
            c = size.getAndIncrement();// size++
            /*
                size++ 存在线程安全问题,分为三步执行
                1.读取成员变量size的值
                2.自增
                3.结果写回成员变量
                可以使用原子变量
             */
        } finally {
            tailLock.unlock();
        }

        if (c == 0) {
            headLock.lock();
            try {
                headWaits.signal();
            } finally {
                headLock.unlock();
            }
        }
        return true;
    }

    @Override
    public E poll() throws InterruptedException {
        E e;
        headLock.lockInterruptibly();
        int c;// 取走前的元素个数
        try {
            while (isEmpty()) {
                headWaits.await();
            }
            e = array[head];
            array[head] = null; // help GC
            if (++head == array.length) {
                head = 0;
            }
            c = size.getAndDecrement(); // size--;
            if (c > 1) {
                headWaits.signal();
            }
        } finally {
            headLock.unlock();
        }
/*        tailLock.lock();
        try {
            // 因为 tailWaits 是由 tailLock 创建出来的，所以必须先有 tailLock
            tailWaits.signal();
        } finally {
            tailLock.unlock();
        }*/

        return e;
    }

}
