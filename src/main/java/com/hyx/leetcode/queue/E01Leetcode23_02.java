package com.hyx.leetcode.queue;

import com.hyx.leetcode.listnode.ListNode;

/**
 * 合并K个升序列表：使用优先级队列中的小顶堆
 *
 * @author hyx
 */
public class E01Leetcode23_02 {

        public ListNode mergeKLists(ListNode[] lists) {
            MinHeap heap = new MinHeap(lists.length);
            // 1.遍历集合，得到头节点
            for (ListNode h : lists) {
                if (h != null) {
                    heap.offer(h);
                }
            }
            // 2. 不断从堆顶获取最小元素，加入新链表
            ListNode s = new ListNode(-1, null);
            ListNode t = s;
            // 判断堆顶中没有元素的话，则终止循环
            while (!heap.isEmpty()) {
                // 取出堆顶的元素，加入新链表中
                ListNode min = heap.poll();
                t.next = min;
                t = min;

                // 加入新元素到小顶堆中
                if (min.next != null){
                    heap.offer(min.next);
                }
            }
            return s.next;
        }

    /*
              p
        1->4->5->null

                  p
        1->3->4->null

           p
        2->6->null

        小顶堆
        空链表     1->1->2->3->4->4->5->6
     */


    public class MinHeap {
        ListNode[] array;
        int size;

        public MinHeap(int capacity) {
            array = new ListNode[capacity];
        }

        public boolean offer(ListNode offered) {
            if (isFull()) {
                return false;
            }
            // 在链表尾部添加节点，然后比较添加节点和父节点的优先级关系，
            // 由于是小顶堆，如果添加节点的优先级比父节点的优先级低，才会进行上浮
            int child = size++;
            int parent = (child - 1) / 2;
            while (child > 0 && offered.val < array[parent].val) {
                array[child] = array[parent];
                child = parent;
                parent = (child + 1) / 2;
            }
            array[child] = offered;
            return true;
        }

        /*
            和大顶堆的规则基本一样
            下潜
         */
        public ListNode poll() {
            if (isEmpty()) {
                return null;
            }
            // 交换第一个元素和最后一个元素的位置
            swap(0, size - 1);
            // 删除最后一个元素的位置，其实就是交换过来的第一个元素
            size--;
            ListNode e = array[size];
            array[size] = null; // help GC
            // 下潜
            down(0);
            return e;
        }

        private void down(int parent) {
            int left = 2 * parent + 1;
            int right = 2 * parent + 2;
            // 判断左右节点是否存在，比较父节点和两个节点的最大值
            int min = parent;
            if (left < size && array[left].val < array[min].val) {
                min = left;
            }
            if (right < size && array[right].val < array[min].val) {
                min = right;
            }
            // 判断父节点是否发生了变化
            if (parent != min) {
                swap(min, parent);
                // 递归调用
                down(min);
            }
        }

        private void swap(int i, int j) {
            ListNode temp = array[i];
            array[i] = array[j];
            array[j] = temp;

        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }

    }

}
