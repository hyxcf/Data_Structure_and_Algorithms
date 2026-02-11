package com.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_15_三数之和_1_6 {
    
    /*
        给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
        注意：答案中不可以包含重复的三元组。
        示例 1：
            输入：nums = [-1,0,1,2,-1,-4]
            输出：[[-1,-1,2],[-1,0,1]]
        解释：
            nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
            nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
            nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
            不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
            注意，输出的顺序和三元组的顺序并不重要。
        示例 2：
            输入：nums = [0,1,1]
            输出：[]
        解释：唯一可能的三元组和不为 0 。
        示例 3：
            输入：nums = [0,0,0]
            输出：[[0,0,0]]
        解释：唯一可能的三元组和为 0 。
     */

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            int target = -nums[i];
            while (l < r) {
                int sum = nums[l] + nums[r];
                if (target == sum) {
                    res.add(Arrays.asList(nums[l], nums[r], nums[i]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (target < sum) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    // 三数之和
    private static class Preview_2_11 {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int l = i + 1;
                int r = nums.length - 1;
                int target = -nums[i];
                while (l < r) {
                    int sum = nums[l] + nums[r];
                    if (target == sum) {
                        res.add(Arrays.asList(nums[l], nums[r], nums[i]));
                        l++;
                        r--;
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    } else if (target < sum) {
                        r--;
                    } else {
                        l++;
                    }
                }
            }
            return res;
        }
    }

}
