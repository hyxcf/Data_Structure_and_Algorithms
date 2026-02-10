package com.leetcode;

import static com.google.common.primitives.Ints.reverse;

public class Leetcode_189_轮转数组_1_13 {

    /*
        给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
        示例 1: 
            输入: nums = [1,2,3,4,5,6,7], k = 3
            输出: [5,6,7,1,2,3,4]
            解释:
            向右轮转 1 步: [7,1,2,3,4,5,6]
            向右轮转 2 步: [6,7,1,2,3,4,5]
            向右轮转 3 步: [5,6,7,1,2,3,4]
        示例 2:        
            输入：nums = [-1,-100,3,99], k = 2
            输出：[3,99,-1,-100]
            解释:
            向右轮转 1 步: [99,-1,-100,3]
            向右轮转 2 步: [3,99,-1,-100]
     */
    static class Solution {
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start += 1;
                end -= 1;
            }
        }
    }

    /*
     * 为什么使用 k %= nums.length
     * 当你旋转数组的次数 k 等于数组长度 nums.length
     * 时，数组会回到其原始状态。因此，任何超过数组长度的旋转都可以简化为其等效的小于数组长度的旋转次数。换句话说，旋转 nums.length + x
     * 次的效果与仅旋转 x 次相同。
     *
     * 例子：如果数组长度是 7 (nums.length = 7) 并且你需要旋转 10 次 (k = 10)，实际上只需要旋转 10 % 7 = 3
     * 次，因为旋转 7 次会让数组回到初始状态，额外的 3 次旋转才是实际有效的旋转次数。
     */
    private static class Preview_2_10 {
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse2(nums, 0, nums.length - 1);
            reverse2(nums, 0, k - 1);
            reverse2(nums, k, nums.length - 1);
        }

        private void reverse2(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start++] = nums[end];
                nums[end--] = temp;
            }
        }
    }
}
