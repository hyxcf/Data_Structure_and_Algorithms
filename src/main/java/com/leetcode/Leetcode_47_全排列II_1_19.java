package com.leetcode;

import java.util.*;

public class Leetcode_47_全排列II_1_19 {


    /*
        给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
        示例 1：
            输入：nums = [1,1,2]
            输出：
            [[1,1,2],
             [1,2,1],
             [2,1,1]]
        示例 2：
            输入：nums = [1,2,3]
            输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        backtrack(0, res, arr);
        return res;
    }

    private void backtrack(int index, List<List<Integer>> res, List<Integer> arr) {
        if (index == arr.size()) {
            res.add(new ArrayList<>(arr));
            return;
        }
        Set<Integer> seen = new HashSet<>();
        for (int i = index; i < arr.size(); i++) {
            if (seen.contains(arr.get(i))) {
                continue;
            }
            seen.add(arr.get(i));
            swap(arr, i, index);
            backtrack(index + 1, res, arr);
            swap(arr, i, index);
        }
    }

    private void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    /*
        给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
        示例 1：
            输入：nums = [1,1,2]
            输出：
            [[1,1,2],
             [1,2,1],
             [2,1,1]]
        示例 2：
            输入：nums = [1,2,3]
            输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    */
    private static class Preview_3_17 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            backtrack(0, path, res);
            return res;
        }

        private void backtrack(int index, List<Integer> path, List<List<Integer>> res) {
            if (index == path.size() - 1) {
                res.add(new ArrayList<>(path));
                return;
            }
            Set<Integer> seen = new HashSet<>();
            for (int i = index; i < path.size(); i++) {
                if (seen.contains(path.get(i))){
                    continue;
                }
                seen.add(path.get(i));
                swap(path, i, index);
                backtrack(index + 1, path, res);
                swap(path, i, index);
            }
        }

        private void swap(List<Integer> path, int i, int j) {
            int temp = path.get(i);
            path.set(i, path.get(j));
            path.set(j, temp);
        }
    }

}
