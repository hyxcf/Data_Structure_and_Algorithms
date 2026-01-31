package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_46_全排列_12_26 {

    /*
        给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
        示例 1： 
            输入：nums = [1,2,3]
            输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        示例 2：  
            输入：nums = [0,1]
            输出：[[0,1],[1,0]]
        示例 3：       
            输入：nums = [1]
            输出：[[1]]
            
            
dfs(0) @ [1,2,3]
│
├─ i=0 → swap(0,0) → [1,2,3]
│   └─ dfs(1) @ [1,2,3]
│       │
│       ├─ i=1 → swap(1,1) → [1,2,3]
│       │   └─ dfs(2) @ [1,2,3] → x==2 (leaf) → add [1,2,3]
│       │
│       └─ i=2 → swap(1,2) → [1,3,2]
│           └─ dfs(2) @ [1,3,2] → leaf → add [1,3,2]
│               └─ backtrack: swap(1,2) → [1,2,3]
│
├─ i=1 → swap(0,1) → [2,1,3]
│   └─ dfs(1) @ [2,1,3]
│       │
│       ├─ i=1 → swap(1,1) → [2,1,3]
│       │   └─ dfs(2) @ [2,1,3] → leaf → add [2,1,3]
│       │
│       └─ i=2 → swap(1,2) → [2,3,1]
│           └─ dfs(2) @ [2,3,1] → leaf → add [2,3,1]
│               └─ backtrack: swap(1,2) → [2,1,3]
│   └─ backtrack: swap(0,1) → [1,2,3]
│
└─ i=2 → swap(0,2) → [3,2,1]
    └─ dfs(1) @ [3,2,1]
        │
        ├─ i=1 → swap(1,1) → [3,2,1]
        │   └─ dfs(2) @ [3,2,1] → leaf → add [3,2,1]
        │
        └─ i=2 → swap(1,2) → [3,1,2]
            └─ dfs(2) @ [3,1,2] → leaf → add [3,1,2]
                └─ backtrack: swap(1,2) → [3,2,1]
    └─ backtrack: swap(0,2) → [1,2,3]
     */

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

    static class Solution {
        List<List<Integer>> res;
        List<Integer> arr;

        public List<List<Integer>> permute(int[] nums) {
            res = new ArrayList<>();
            arr = new ArrayList<>();
            if (nums.length == 0) return res;
            for (int num : nums) {
                arr.add(num);
            }
            dfs(0);
            return res;
        }

        private void dfs(int x) {
            if (x == arr.size() - 1) {
                res.add(new ArrayList<>(arr));
                return;
            }
            for (int i = x; i < arr.size(); i++) {
                swap(x, i);
                dfs(x + 1);
                swap(x, i);
            }
        }

        void swap(int i, int j) {
            int temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }
    }

    private static class Preview_1_31 {
        // 回溯复习
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length == 0) {
                return res;
            }
            List<Integer> path = new ArrayList<>();
            for (int num : nums) {
                path.add(num);
            }
            dfs(0, path, res);
            return res;
        }

        private void dfs(int index, List<Integer> path, List<List<Integer>> res) {
            if (index == path.size() - 1) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int x = index; x < path.size(); x++) {
                swap(path, index, x);
                dfs(index + 1, path, res);
                swap(path, index, x);
            }
        }

        private void swap(List<Integer> arr, int i, int j) {
            int temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }
    }
}
