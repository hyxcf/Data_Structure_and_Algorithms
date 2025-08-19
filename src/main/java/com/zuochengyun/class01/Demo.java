package com.zuochengyun.class01;

import java.util.Arrays;

public class Demo {

    /*
    不具备稳定性的排序：
        选择排序、快速排序、堆排序
    不具备稳定性的排序：
        冒泡排序、插入排序、归并排序、一切桶排序思想下的排序
    todo：复习排序算法的 时间、空间、稳定性
     */

    public static void main(String[] args) {
//        int[] arr = {1, 3, 4, 7, 7, 3, 1};
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
        System.out.println("数组小和为：" + arrSum(arr));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 2025-08-15 星期五 复习 这周所学的知识 堆排序 归并排序 快速排序 数组小和 逆序对问题 选择排序，冒泡排序，插入排序， 异或的知识，对数器
     */
    /**
     * 数组小和问题 : 求出左侧的数字比他小的 和  1 2 5 4 1      1+1+2+2+1 = 7
     * 求左数字比他小的，也就是从左往右看 有几个比他大的，比如说1右侧有三个比他大的，所以有3个1的小和
     */
    public static int arrSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processArrSum(arr, 0, arr.length - 1);
    }

    private static int processArrSum(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return processArrSum(arr, l, mid)
                + processArrSum(arr, mid + 1, r)
                + mergeArrSum(arr, l, mid, r);
    }

    private static int mergeArrSum(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        int sum = 0;
        while (p1 <= mid && p2 <= r) {
            if (arr[p1] < arr[p2]) {
                sum += (r - p2 + 1) * arr[p1];
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
            }
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, l, r - l + 1);
        return sum;
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort2(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition2(arr, l, r);
            quickSort2(arr, l, p[0] - 1);
            quickSort2(arr, p[1] + 1, r);
        }
    }

    private static int[] partition2(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r; // 这里不要写成 r + 1, 因为 r 已经被当成基准点了
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process2(arr, l, mid);
        process2(arr, mid + 1, r);
        merge2(arr, l, mid, r);
    }

    public static void merge2(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, l, help.length);
    }

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
            heapify(arr, 0, heapSize);
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


    /**
     * 2025-08-13 星期三 复习 堆排序 O(n * log(n))  O(1)
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 建堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        while (heapSize > 0) { // 拿最大的那个和最后的 --heapSize 进行交换
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] < arr[index] ? index : largest;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 2025-08-11 星期一 复习 归并排序 快速排序 数组小和 逆序对问题
     */
    // 快排 O(log(n)) 
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r); // fixme：因为有这个时间复杂度才为O(n * log(n))
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r; // 这里不要写成 r+1，是错误的
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r); // fixme:最后排完序要记得进行交换
        return new int[]{l, r};
    }


    // 归并排序 O(n * log(n)) 分治思想
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, l, help.length);
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
    public static void eventOddTimers(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // 异或问题：给你一堆数，只有两个是奇数，其余的都是偶数 
    public static void eventOddTimersTwo(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i]; // 此时eor = a ^ b
        }
        int rightOne = eor & (~eor + 1); // 这个公式是得到最右边的1的位置
        int onlyOne = 0;
        for (int i : arr) {
            if ((i & rightOne) == 1) {
                onlyOne ^= i;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
