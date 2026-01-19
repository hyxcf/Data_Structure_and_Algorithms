package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className Leetcode_90_子集II_1_18
 * @date 2026/1/18 23:19
 * @description
 * @since jdk 11
 */
public class Leetcode_90_子集II_1_18 {

    /*
        给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。
        解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
        示例 1：
            输入：nums = [1,2,2]
            输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
        示例 2：
            输入：nums = [0]
            输出：[[],[0]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(0, nums, path, res);
        return res;
    }

    private void backtracking(int startIndex, int[] nums, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = startIndex; i < nums.length; i++) {
            if (i != startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtracking(i + 1, nums, path, res);
            path.remove(path.size() - 1);
        }
    }


    static class Solution2 {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length == 0) {
                return res;
            }
            List<Integer> paths = new ArrayList<>();
            Arrays.sort(nums);
            backtrack(0, nums, paths, res);
            return res;
        }

        private void backtrack(int index, int[] nums, List<Integer> paths, List<List<Integer>> res) {
            res.add(new ArrayList<>(paths));
            for (int i = index; i < nums.length; i++) {
                if (i != index && nums[i] == nums[i - 1]) {
                    continue;
                }
                paths.add(nums[i]);
                backtrack(i + 1, nums, paths, res);
                paths.remove(paths.size() - 1);
            }
        }
    }

}
