package com.zuochengyun.class01;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
//        int[] arr = {1, 3, 4, 7, 7, 3, 1};
        int[] arr = {1, 3, 4, 5, 7, 7, 3, 1};
//        selectSort(arr);
//        bubbleSort(arr);
//        insertSort(arr);
//        eventOddTimers(arr);
        eventOddTimersTwo(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 2025-08-08 星期五 复习 选择排序，冒泡排序，插入排序， 异或的知识，对数器
     */
    // 选择排序 时间复杂度 O(n²)
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) { // fixme:这里需要进行判断，以避免不必要的交换
                swap(arr, i, min);
            }
        }
    }

    // 冒牌排序 时间复杂度 O(n²)
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    // 插入排序 时间复杂度  O(n²) 这个是随数据量的情况来决定时间复杂度的，比上面两个要好，上面是无论数据量怎样变化，都是 O(n²)
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                swap(arr, j + 1, j); // fixme:这个内部的for循环都是 j 相关的，别写成 i 了
            }
        }
    }
    
    // 异或问题：给你一堆数，只有一个是奇数，其余的都是偶数 
    // fixme：异或满足交换律和结合律。a ^ a = 0    a ^ 0 = a 这两个是最重要的  异或：相同为0 不同为1  
    public static void eventOddTimers(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
    
    // 异或问题：给你一堆数，只有两个是奇数，其余的都是偶数 
    public static void eventOddTimersTwo(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i]; // 此时eor = a ^ b
        }
        int rightOne = eor & (~eor + 1); // 这个公式是得到最右边的1的位置
        int onlyOne = 0;
        for (int i : arr) {
            if ((i & rightOne) == 1){
                onlyOne ^= i;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
