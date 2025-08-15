package com.zuochengyun.class01;


import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，
 * 每个元素移动的距离可以不超过k，并且k相对于数组来说比较小。
 * 请选择一个合适的排序算法针对这个数据进行排序。
 */
public class Code09_SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue();
        int index = 0;
        for (; index < Math.min(arr.length, k + 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }
    }

    public static void main(String[] args) {
        // 构造一个几乎有序的数组：每个元素距离其正确位置不超过 k
        int[] arr = {3, 2, 1, 5, 4, 7, 6, 9, 8};
        int k = 2; // 每个元素最多移动 2 个位置

        System.out.println("排序前: " + Arrays.toString(arr));
        sortArrayDistanceLessK(arr, k);
        System.out.println("排序后: " + Arrays.toString(arr));
    }
}
