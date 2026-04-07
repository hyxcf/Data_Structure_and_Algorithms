package com.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_560_和为K的子数组_1_12 {

/*
    给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    子数组是数组中元素的连续非空序列。

    示例 1：
        输入：nums = [1,1,1], k = 2
        输出：2
    示例 2：
        输入：nums = [1,2,3], k = 3
        输出：2
 */

    public int subarraySum(int[] nums, int k) {
        return 0;
    }

    static class Solution {
        public int subarraySum(int[] nums, int k) {
            // 前缀和
            // 数组元素   0 1 1 1
            // 前缀和     0 1 2 3
            // 出现的次数 1 1 1 1
            // K=2 当 前缀和-k=前缀和的任意一项 res + 1
            // 使用map存储前缀和 和 它出现的次数
            int pre = 0;
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                pre += nums[i];
                // 满足条件的前缀和
                if (map.containsKey(pre - k)) {
                    count += map.get(pre - k);
                }
                if (map.containsKey(pre)) {
                    map.put(pre, map.get(pre) + 1);
                } else {
                    map.put(pre, 1);
                }
            }
            return count;
        }
    }


    /**
     * ❓ 为什么 cnt.put(0, 1) 很重要？
     * 考虑 nums = [2], k = 2：
     * <p>
     * 初始：s = 0
     * 加上 2：s = 2
     * s - k = 0
     * 如果没有 cnt.put(0,1)，就会认为“之前没出现过0”，答案为0 → 错误！
     * 但实际上，整个数组 [2] 的和就是 2，应该算1次。
     * <p>
     * 这个 s=0 对应的是 “从开头到当前位置” 的情况（即 i=0）。
     * <p>
     * ✅ s[0] = 0 是前缀和的起点，必须初始化！
     */
    static class Solution2 {
        public int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> cnt = new HashMap<>(nums.length + 1, 1); // 预分配空间
            cnt.put(0, 1); // s[0]=0 单独统计
            int s = 0;
            int ans = 0;
            for (int x : nums) {
                s += x;
                ans += cnt.getOrDefault(s - k, 0);
                cnt.merge(s, 1, Integer::sum); // cnt[s]++
            }
            return ans;
        }
    }

    static class PreviewSolution {
        public int subarraySum(int[] nums, int k) {
            int pre = 0;
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>(nums.length + 1);
            map.put(0, 1);
            for (int num : nums) {
                pre += num;
                count += map.getOrDefault(pre - k, 0);
                map.merge(pre, 1, Integer::sum);
            }
            return count;
        }
    }

    private static class Preview_2_11 {
        public int subarraySum(int[] nums, int k) {
            int pre = 0;
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>(nums.length - 1);
            map.put(0, 1);
            for (int num : nums) {
                pre += num;
                count += map.getOrDefault(pre - k, 0);
                map.merge(pre, 1, Integer::sum);
            }
            return count;
        }
    }

    private static class Preview_3_23 {
        public int subarraySum(int[] nums, int k) {
            int pre = 0;
            int count = 0;
            Map<Integer, Integer> map = new HashMap<>(nums.length - 1);
            map.put(0, 1);
            for (int num : nums) {
                pre += num;
                count += map.getOrDefault(pre - k, 0);
                map.merge(pre, 1, Integer::sum);
            }
            return count;
        }
    }

    private static class Preview_4_7 {
        public int subarraySum(int[] nums, int k) {
            int pre = 0;
            int res = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int num : nums) {
                pre += num;
                res += map.getOrDefault(pre - k, 0);
                map.merge(pre, 1, Integer::sum);
            }
            return res;
        }
    }

}

