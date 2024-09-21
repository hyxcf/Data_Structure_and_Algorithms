package com.hyx;

import java.util.Arrays;

/**
 * 大顶堆
 * 三个比较重要的方法：上浮、下潜、堆化
 *
 * @Author：hyx
 * @Date：2024/9/21 10:51
 */

public class MaxHeap {
    int[] array;
    int size;

    // 初始化容量
    public MaxHeap(int capacity) {
        array = new int[capacity];
    }

    public MaxHeap(int[] array) {
        this.array = array;
        this.size = array.length;
        heapify();
    }

    // 建堆
    /*
        步骤：
            1.找到最后一个非叶子节点
            2.从后向前，对每个节点执行下潜
     */
    private void heapify() {
        // 找到最后一个非叶子节点 size / 2 - 1
        for (int i = size / 2 - 1; i >= 0; i--) {
            // 执行下潜操作
            down(i);
        }

    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("堆里的元素为空");
        }
        return array[0];
    }

    /**
     * 删除堆顶元素
     *
     * @return 堆顶元素
     */
    public int poll() {
        if (isEmpty()) {
            return -1;
        }
        int top = array[0];
        // 此处可以忽略 内存释放问题，因为此处采用的是 基本数据类型 而不是 引用数据类型
        swap(0, size - 1);
        size--;
        // 将交换上来的元素进行下潜
        down(0);
        return top;
    }

    /**
     * 删除指定索引处元素
     *
     * @param index 索引
     * @return 索引处对应的值
     */
    public int poll(int index) {
        if (isEmpty()) {
            return -1;
        }
        int indexValue = array[index];
        // 此处可以忽略 内存释放问题，因为此处采用的是 基本数据类型 而不是 引用数据类型
        // 交换索引处的值和最后一个值
        swap(index, array.length - 1);
        // 进行 size--，删除最后一个元素
        size--;
        // 将交换上去的元素进行下潜操作
        down(index);
        return indexValue;
    }

    /**
     * 替换堆顶元素
     *
     * @param replaced 新元素
     */
    public void replace(int replaced) {
        array[0] = replaced;
        // 进行下潜
        down(0);
    }

    /**
     * 堆的尾部添加元素
     *
     * @param offered 被添加元素值 n1
     *                被添加元素值的父元素 = (n1 - 1) / 2
     */
    public boolean offer(int offered) {
        if (isFull()) {
            return false;
        }
        up(offered);
        size++;
        return true;
    }

    // 将 insert 元素上浮：直至 offered 小于父元素或到堆顶
    private void up(int offered) {
        int child = size; // 要插入的索引值
        while (child > 0) {
            int parent = (child - 1) / 2; // 计算要插入索引值的父节点
            if (offered > array[parent]) {
                array[child] = array[parent];
            } else {
                break;
            }
            child = parent;
        }
        array[child] = offered;
    }

    // 将 parent 索引处的元素下潜：与两个孩子较大者交换，直至没孩子或孩子没它大
    private void down(int parent) {
        int left = parent * 2 + 1;// 左孩子 = parent * 2 + 1
        int right = left + 1;
        int max = parent;
        // 注意 left 和 right 的索引范围
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        // 判断是否被 left 或者 right 替换
        if (max != parent) {
            swap(max, parent);
            // 被替换了,递归调用
            down(max);
        }
    }

    // 交换两个索引处的元素
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    // 堆排序算法
    /*
        步骤：
            1、heapify 建立大顶堆
            2、将堆顶与堆底进行交换(最大元素交换到堆底)、缩小并下潜调整堆
            3、重复第二步直至堆里剩一个元素
     */
    public static void main(String[] args) {
        int[] array = {1, 33, 7, 2, 9, 0};
        MaxHeap heap = new MaxHeap(array);
        System.out.println(Arrays.toString(heap.array));
        while (heap.size > 1) {
            // 最大元素交换到堆底
            heap.swap(0, heap.size - 1);
            heap.size--;
            heap.down(0);
        }
        System.out.println(Arrays.toString(heap.array));

    }
}
