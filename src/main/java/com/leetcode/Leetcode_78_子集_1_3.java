package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className Leetcode_78_子集_1_3
 * @date 2026/1/3 17:34
 * @description
 * @since jdk 11
 */
public class Leetcode_78_子集_1_3 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, nums, res, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int index, int[] nums, List<List<Integer>> res, ArrayList<Integer> temp) {
        res.add(new ArrayList<>(temp));
        for (int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(i + 1, nums, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

}
