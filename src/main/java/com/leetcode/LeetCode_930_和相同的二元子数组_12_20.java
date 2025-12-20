package com.leetcode;

public class LeetCode_930_和相同的二元子数组_12_20 {

    /*
    给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
    子数组 是数组的一段连续部分。
    
    示例 1：
        输入：nums = [1,0,1,0,1], goal = 2
        输出：4
        解释：
        有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
     */

    public int numSubarraysWithSum(int[] nums, int goal) {
        // 很妙，思路很好，get到了，但这种只适用于全都是非负数的情况下
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        int left = 0, ans = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > target) { // 这个 while 循环是为了确定‘和 ≤ target’的左边界  不断右移左指针 left，直到当前窗口 [left, i] 的和 sum 满足 sum ≤ target。
                sum -= nums[left];
                left++;
            }
            ans += i - left + 1;
        }
        return ans;
    }
}

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        // 以第一个例子为例goal=2 ，目标和小于等于2的数量减去目标和小于等于1的数量就是相加为2子数组数量
        /**
         因为：
         atMost(2) 包含了：和=0、1、2 的所有子数组（共14个）
         atMost(1) 包含了：和=0、1 的所有子数组（共10个）
         相减之后，只剩下和=2 的子数组 → 14 - 10 = 4 个 
         */
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int target) {
        if (target < 0) {
            return 0;
        }
        int left = 0;
        int ans = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > target) {
                sum -= nums[left];
                left++;
            }
            ans += i - left + 1;
        }
        return ans;
    }
}
