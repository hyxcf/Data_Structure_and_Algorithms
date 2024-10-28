package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

/**
 * @version 0.1
 * @Author hyx
 * @className Summary
 * @Date 2024/10/28  21:52
 * @description 几种常见的排序算法
 * @since jdk 11
 */
public class Summary {


    public static void main(String[] args) {
        int[] arr = {4, 2, 3, 1, 9, 8, 3};
        // sort01(arr);
//		sort02(arr);
//		sort03(arr);
//		sort04(arr);
//		sort05(arr);
        sort06(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort06(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("快速排序,时间复杂度为O(N*logN),额外空间复杂度为O(N*logN),无稳定性");
        partition(arr, 0, arr.length - 1);
    }

    // 进行分区
    private static void partition(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 求等于基准点的边界值
        int[] equalArea = netherlandsFlag(arr, left, right);
        partition(arr, left, equalArea[0] - 1);
        partition(arr, equalArea[1] + 1, right);
    }

    // 荷兰国旗问题
    private static int[] netherlandsFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        int less = left - 1; // less 代表小于基准点的右边界
        int more = right; // more 代表大于基准点的左边界
        int index = left;
        // 将最右侧节点作为基准点
        while (index < more) {
            if (arr[index] < arr[right]) {
                swap(arr, index++, ++less);
            } else if (arr[index] > arr[right]) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
        swap(arr, right, more);
        return new int[]{less + 1, more};
    }

    private static void sort05(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("堆排序,时间复杂度为O(N*logN),额外空间复杂度为O(N*logN),无稳定性");
        // 建堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        // 进行排序操作
        int heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void sort04(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("归并排序,时间复杂度为O(N*logN),额外空间复杂度为O(N*logN),有稳定性");
        split(arr, 0, arr.length - 1);
    }

    private static void split(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        split(arr, left, mid);
        split(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int k = 0; // 定义新指针
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[k++] = arr[p1++];
        }
        while (p2 <= right) {
            help[k++] = arr[p2++];
        }
        // 将 help 的数组元素返回给 arr 数组
        for (int i = 0; i < help.length; i++) {
            arr[i + left] = help[i];
        }
    }

    private static void sort03(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("插入排序,时间复杂度为O(N^2),额外空间复杂度为O(1),有稳定性");
        for (int low = 1; low < arr.length; low++) {
            int t = arr[low];// 待插入元素
            int i = low - 1;
            while (i >= 0 && arr[i] > t) {
                arr[i + 1] = arr[i];// 向后移一位
                i--;
            }
            if (i != low - 1) {
                arr[i + 1] = t;
            }
        }
    }

    // 每次都找到它最大或者最小的元素，将其插入到合适的位置
    private static void sort02(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("选择排序,时间复杂度为O(N^2),额外空间复杂度为O(1),无稳定性");
        for (int left = 0; left < arr.length; left++) {
            int min = left;
            for (int i = left + 1; i < arr.length; i++) {
                if (arr[i] < arr[min]) {
                    min = i;
                }
            }
            if (min != left) {
                swap(arr, min, left);
            }
        }
    }

    private static void sort01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        System.out.println("冒泡排序,时间复杂度为O(N^2),额外空间复杂度为O(1),有稳定性");
        int j = arr.length - 1;
        while (j != 0) {
            int x = 0;
            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    x = i;
                }
            }
            j = x;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

