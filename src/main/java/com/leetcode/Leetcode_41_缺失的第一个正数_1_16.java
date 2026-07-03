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

    /*
        给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
        示例 1：
            输入：nums = [1,2,0]
            输出：3
            解释：范围 [1,2] 中的数字都在数组中。
        示例 2：
            输入：nums = [3,4,-1,1]
            输出：2
            解释：1 在数组中，但 2 没有。
        示例 3：
            输入：nums = [7,8,9,11,12]
            输出：1
            解释：最小的正数 1 没有出现。
     */
    private static class Preview_2_10 {
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                while (nums[i] >= 1 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
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

    /*
        给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
        示例 1：
            输入：nums = [1,2,0]
            输出：3
            解释：范围 [1,2] 中的数字都在数组中。
        示例 2：
            输入：nums = [3,4,-1,1]
            输出：2
            解释：1 在数组中，但 2 没有。
        示例 3：
            输入：nums = [7,8,9,11,12]
            输出：1
            解释：最小的正数 1 没有出现。
    */
    private static class Preview_3_16 {
        // 实际上将索引与数组中的元素一一对应，没有对应上的就是错误的
        public int firstMissingPositive(int[] nums) {
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                while (nums[i] >= 1 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
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

    // 缺失的第一个正数
    /*
        给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
        请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
        示例 1：
            输入：nums = [1,2,0]
            输出：3
            解释：范围 [1,2] 中的数字都在数组中。
        示例 2：
            输入：nums = [3,4,-1,1]
            输出：2
            解释：1 在数组中，但 2 没有。
        示例 3：
            输入：nums = [7,8,9,11,12]
            输出：1
            解释：最小的正数 1 没有出现。
    */
    private static class Preview_7_3 {
        public int firstMissingPositive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 1;
            }
            int length = nums.length;
            for (int i = 0; i < length; i++) {
                if (nums[i] >= 1 && nums[i] <= length && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, i, nums[i] - 1);
                }
            }
            for (int i = 0; i < length; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return length + 1;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
