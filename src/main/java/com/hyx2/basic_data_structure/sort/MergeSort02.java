package com.hyx2.basic_data_structure.sort;

import java.util.Arrays;

// 归并排序（非递归版）
public class MergeSort02 {

	public static void main(String[] args) {
		int[] arr = { 1, 4, 8, 324, 87, 2, 832, 0 };
		if (arr == null || arr.length < 2) {
			return;
		}
		int len = arr.length;
		int mergeSize = 1;// 当前有序的数组长度，左组长度，类似于希尔排序
		while (mergeSize < len) {
			int left = 0;
			while (left < len) {
				// L...M 左组(mergeSize)
				int m = left + mergeSize - 1;
				if (m >= len) {
					break;
				}
				// L...M M+1...R(mergeSize)
				int right = Math.min(m + mergeSize, len - 1);
				merge(arr, left, m, right);
				left = right + 1;
			}
			// 加这一句，是为了当 mergeSize > len / 2 时，提前终止，防止溢出。
			if (mergeSize > len / 2) {
				break;
			}
			mergeSize <<= 1;
		}
		System.out.println(Arrays.toString(arr));
	}

	private static void merge(int[] arr, int left, int m, int right) {
		int[] help = new int[right - left + 1];
		int k = 0; // 定义新数组指针
		int p1 = left;
		int p2 = m + 1;
		while (p1 <= m && p2 <= right) {
			help[k++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[k++] = arr[p1++];
		}
		while (p2 <= right) {
			help[k++] = arr[p2++];
		}
		// 合并数组
		for (int i = 0; i < help.length; i++) {
			arr[i + left] = help[i];
		}
	}
}
