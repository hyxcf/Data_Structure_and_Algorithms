package com.hyx2.basic_data_structure.mianshiti;

/**
 * @version 0.1
 * @Author hyx
 * @className MergeGetSum
 * @Date 2024/10/24  23:21
 * @description 数组小和
 * @since jdk 11
 */
public class MergeGetSum {
    /*
     在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和
        例子: [1,3,4,2,5]
        1左边比1小的数：没有
        3左边比3小的数：1
        4左边比4小的数：1、3
        2左边比2小的数：1
        5左边比5小的数：1、3、4、2
        所以数组的小和为1+1+3+1+1+3+4+2=16

        在合并时
        情况1：当左组的数 小于 右组的数，产生小和，除此之外不产生
        情况2：当左组的数 等于 右组的数，先拷贝右组的数，不产生小和
        情况3：当左组的数 大于 右组的数，直接拷贝右组的数，不产生小和
     */
    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        int result = getSmallSum(arr);
        System.out.println(result);
    }

    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int k = 0;
        int p1 = l;
        int p2 = mid + 1;
        int result = 0;
        while (p1 <= mid && p2 <= r) {
            // 在合并时
            // 情况1：当左组的数 小于 右组的数，产生小和，除此之外不产生
            // 情况2：当左组的数 等于 右组的数，先拷贝右组的数，不产生小和
            // 情况3：当左组的数 大于 右组的数，直接拷贝右组的数，不产生小和
            // 就是利用右边有序的特性。
            result += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0; // 左组和右组进行比较，右组是有序的，当左组的数小于右组的数时也一定小于右组的这个数往右的所有数
            help[k++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid) {
            help[k++] = arr[p1++];
        }
        while(p2 <= r) {
            help[k++] = arr[p2++];
        }
        // 将 help[] 中的元素刷回 arr[] 中
        // 易错点
        System.arraycopy(help, 0, arr, l, help.length);
        return result;
    }

}
