package com.hyx2.basic_data_structure;

/**
 * @version 0.1
 * @Author hyx
 * @className DoubleEndsQueueToStackAndQueue
 * @Date 2024/10/23  22:09
 * @description
 * @since jdk 11
 */
public class DoubleEndsQueueToStackAndQueue {

    // 双向链表的节点类型
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    // 定义方法
    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        // 从头部加节点
        public void addFromHead(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        // 从尾部加节点
        public void addFromBottom(T value) {
            Node<T> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        // 从头部弹出节点
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        // 从尾部弹出节点
        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        public boolean isEmpty(){
            return head == null;
        }
    }

    // 借助上面定义的方法 头部进，头部出实现栈结构
    public static class MyStack<T>{
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value){
            queue.addFromHead(value);
        }

        public T pop(){
            return queue.popFromHead();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }

    // 借助上面定义的方法 头部进，尾部出实现队列结构
    public static class MyQueue<T>{
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value){
            queue.addFromHead(value);
        }

        public T poll(){
            return queue.popFromBottom();
        }

        public boolean isEmpty(){
            return queue.isEmpty();
        }
    }



}
