package com.hyx.queue.priorityqueue;

import com.hyx.queue.Queue;

/**
 * 使用<b>大顶堆</b>实现优先级队列
 *
 * @author hyx
 */
public class PriorityQueue3<E extends Priority> implements Queue<E> {

    Priority[] array;
    int size;

    public PriorityQueue3(int capacity) {
        array = new Priority[capacity];
    }

    /*
        上浮
        1. 入堆新元素，加入到数组末尾（索引位置 child）
        2. 不断比较新加元素与它父节点（parent）优先级
            - 如果父节点优先级低，则向下移动，并找到下一个parent
            - 直至父节点优先级更高 或 child = 0 为止
     */
    @Override
    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        int child = size++;
        int parent = (child - 1) / 2;
        // 判断如果父节点的优先级比新加入的优先级低
        while (child > 0 && value.priority() > array[parent].priority()) {
            // 把 parent 的值赋给 child
            array[child] = array[parent];
            child = parent; // 将新加入的节点赋给父节点
            parent = (child - 1) / 2; // 找到父节点的父节点
        }
        array[child] = value;
        return true;
    }

    /*
        1. 交换堆顶和尾部元素，让尾部元素出列
        2. （下潜）
            - 从堆顶开始，将父元素与两个子元素中较大的子元素进行交换
            - 直到父元素大于两个孩子 或者 没有孩子为止
     */

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        // 交换索引
        swap(0, size - 1);
        size--;
        Priority e = array[size];
        array[size] = null; // help gc

        // 下潜
        down(0);

        return (E) e;
    }

    /*
            2. （下潜）
            - 从堆顶开始，将父元素与两个子元素中较大的子元素进行交换
            - 直到父元素大于两个孩子 或者 没有孩子为止
     */
    private void down(int parent) {
        int left = 2 * parent + 1;
        int right = left + 1;
        // 判断左右节点是否存在，比较父节点和两个节点的最大值
        int max = parent;
        if(left < size && array[left].priority() > array[max].priority()){
            max = left;
        }
        if(right < size && array[right].priority() > array[max].priority()){
            max = right;
        }
        // 判断父节点是否发生了变化
        if (max != parent){ // 发生了变化
            swap(max,parent);
            // 递归调用
            down(max);
        }
    }


    private void swap(int i, int j) {
        Priority temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return (E) array[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
