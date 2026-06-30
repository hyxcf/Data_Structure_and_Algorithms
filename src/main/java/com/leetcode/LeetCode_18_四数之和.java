package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_18_四数之和 {

    /*
        给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
        0 <= a, b, c, d < n
        a、b、c 和 d 互不相同
        nums[a] + nums[b] + nums[c] + nums[d] == target
        你可以按 任意顺序 返回答案 。
        示例 1：
            输入：nums = [1,0,-1,0,-2,2], target = 0
            输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        示例 2：
            输入：nums = [2,2,2,2,2], target = 8
            输出：[[2,2,2,2]]
     */
    private static class Preview_6_30 {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums == null || nums.length < 4) {
                return res;
            }
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 3; i++) {
                // 第一层去重
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                long tempTarget = (long) target - nums[i];
                for (int j = i + 1; j < nums.length - 2; j++) {
                    // 第二层去重
                    if (j > i + 1 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    long finalTarget = (long) tempTarget - nums[j];
                    int l = j + 1;
                    int r = nums.length - 1;
                    while (l < r) {
                        long sum = (long) nums[l] + nums[r];
                        if (sum == finalTarget) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                            l++;
                            r--;
                            // 双指针去重
                            while (l < r && nums[l] == nums[l - 1]) {
                                l++;
                            }
                            while (l < r && nums[r] == nums[r + 1]) {
                                r--;
                            }
                        } else if (sum < finalTarget) {
                            l++;
                        } else {
                            r--;
                        }
                    }
                }
            }
            return res;
        }
    }

}
