package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_146_LRU缓存_3_23 {

    private static class LRUCache {

        Entry head, tail;
        int size;
        int capacity;
        Map<Integer, Entry> cache;

        public LRUCache(int capacity) {
            // 初始化链表
            initLinkedList();
            size = 0;
            this.capacity = capacity;
            cache = new HashMap<>(capacity + 2);
        }

        private void initLinkedList() {
            head = new Entry();
            tail = new Entry();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Entry node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 存在移动节点
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            Entry node = cache.get(key);
            if (node != null) {
                node.value = value;
                moveToHead(node);
                return;
            }
            // 不存在，先加进去，在移除尾结点
            // 此时容量已满 删除尾结点
            if (size == capacity) {
                Entry lastNode = tail.pre;
                deleteNode(lastNode);
                cache.remove(lastNode.key);
                size--;
            }
            // 加入头结点
            Entry newNode = new Entry();
            newNode.key = key;
            newNode.value = value;
            addNode(newNode);
            cache.put(newNode.key, newNode);
            size++;
        }

        private void addNode(Entry node) {
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }

        private void deleteNode(Entry node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void moveToHead(Entry node) {
            deleteNode(node);
            addNode(node);
        }

    }

    private static class Entry {
        private Entry pre;
        private Entry next;
        private int key;
        private int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Entry() {
        }
    }


    private static class Preview_3_24 {

        private static class LRUCache {

            int capacity;
            int size;
            Entry head, tail;
            Map<Integer, Entry> cache;

            public LRUCache(int capacity) {
                // 初始化链表
                initLinkedList();
                size = 0;
                this.capacity = capacity;
                cache = new HashMap<>(capacity + 2);
            }

            private void initLinkedList() {
                head = new Entry();
                tail = new Entry();
                head.next = tail;
                tail.pre = head;
            }

            public int get(int key) {
                Entry node = cache.get(key);
                if (node == null) {
                    return -1;
                }
                // 存在移动节点
                moveToHead(node);
                return node.value;
            }

            public void put(int key, int value) {
                Entry node = cache.get(key);
                if (node != null) {
                    node.value = value;
                    moveToHead(node);
                    return;
                }
                // 不存在，先加进去，空间满了就移除尾结点
                if (size == capacity) {
                    Entry lastNode = tail.pre;
                    deleteNode(lastNode);
                    cache.remove(lastNode.key);
                    size--;
                }
                Entry newNode = new Entry(key, value);
                addNode(newNode);
                cache.put(key, newNode);
                size++;
            }

            private void deleteNode(Entry node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            private void addNode(Entry node) {
                head.next.pre = node;
                node.next = head.next;
                head.next = node;
                node.pre = head;
            }

            private void moveToHead(Entry node) {
                deleteNode(node);
                addNode(node);
            }

            private static class Entry {
                private Entry pre;
                private Entry next;
                private int key;
                private int value;

                public Entry() {
                }

                public Entry(int key, int value) {
                    this.key = key;
                    this.value = value;
                }
            }
        }

    }


}
