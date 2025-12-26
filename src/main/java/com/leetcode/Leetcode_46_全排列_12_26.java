package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_46_全排列_12_26 {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        LinkedList<Integer> list = new LinkedList<>();
        dfs(nums, len, 0, used, list, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, LinkedList<Integer> list, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                list.addLast(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, used, list, res);
                list.removeLast();
                used[i] = false;
            }
        }
    }

}
