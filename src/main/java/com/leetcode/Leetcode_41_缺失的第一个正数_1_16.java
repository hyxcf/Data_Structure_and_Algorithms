package com.leetcode;

public class Leetcode_41_缺失的第一个正数_1_16 {

    public int firstMissingPositive(int[] nums) {
        // 使用 桶排序 + 原地标记 的思想
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 这里的条件是：只交换正整数 && 数组的数字小于等于数字长度（如果大于，肯定不是答案） && 判断数组位置是否正确
            while (nums[i] >= 1 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) { // todo 这里必须用while。以确保交换过来的值在正确的位置
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
