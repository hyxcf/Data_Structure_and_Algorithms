package com.zuochengyun.class01;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        //        int[] arr = {1, 3, 4, 5, 7, 7, 3, 1};
        int[] arr = {1, 2, 5, 4, 1};
//        selectSort(arr);
//        bubbleSort(arr);
//        insertSort(arr);
//        eventOddTimers(arr);
//        eventOddTimersTwo(arr);
//        mergeSort(arr);
//        quickSort(arr);
//        heapSort(arr);
//        heapSort2(arr);
//        mergeSort2(arr);
//        quickSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 堆排序 时间复杂度：O(n logn) 空间复杂度：O(1) 不具有稳定性
    public static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            // 建堆
            heapify2(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) { // 拿最大的那个和最后的 --heapSize 进行交换
            swap(arr, 0, --heapSize);
            heapify2(arr, 0, heapSize);
        }
    }

    private static void heapify2(int[] arr, int parent, int heapSize) {
        int left = parent * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[parent] < arr[largest] ? largest : parent;
            if (largest == parent) {
                break;
            }
            swap(arr, largest, parent);
            parent = largest;
            left = parent * 2 + 1;
        }
    }

    /*
    1   1   3   4   4   7   5   7
                    L           R
            l       m           
    */
    // 快速排序 时间复杂度：O(n logn) 空间复杂度：O(logn) 不具有稳定性
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int L, int R) {
        if (L >= R) return;
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] p = partition(arr, L, R);
        quickSort(arr, L, p[0] - 1);
        quickSort(arr, p[1] + 1, R);
    }

    private static int[] partition(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    // 归并排序 时间复杂度：O(n logn) 空间复杂度：O(n) 有稳定性
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, R, mid);
    }

    private static void merge(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, L, help.length);
    }

    // 插入排序 , 将数组分为两部分，排序区域和未排序区域 时间：O(n²) 空间：O(1)
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                swap(arr, j + 1, j);
            }
        }
    }

    // 每轮选择出最大或者最小值的元素，并把它交换到合适的位置。时间：O(n²) 空间：O(1)
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                min = arr[j] < arr[min] ? j : min;
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
