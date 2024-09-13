package com.hyx.queue;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author hyx
 */

public class TestLinkedListQueue {
    @Test
    public void offer(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.print(next+" ");
        }
    }

    @Test
    public void peek(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());
        LinkedListQueue<Integer> queue2 = new LinkedListQueue<>();
        queue2.offer(4);
        queue2.offer(5);
        System.out.println(queue2.peek());
    }
    @Test
    public void poll(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        LinkedListQueue<Integer> queue2 = new LinkedListQueue<>();
        queue2.offer(4);
        queue2.offer(5);
        System.out.println(queue2.poll());
    }

    @Test
    public void offerLimit(){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        // 只能添加 3 个元素
        boolean offer = queue.offer(4);
        System.out.println(offer); // false
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(next + " ");
        }
    }

}
