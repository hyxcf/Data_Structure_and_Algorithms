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

    
    /*
        给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
        解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
        示例 1：
            输入：nums = [1,2,3]
            输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        示例 2：     
            输入：nums = [0]
            输出：[[],[0]]
        
        dfs(0) @ temp = []
        │
        ├─ add [] to res → res = [[]]
        │
        ├─ i=0: temp.add(1) → [1]
        │   └─ dfs(1) @ [1]
        │       │
        │       ├─ add [1] → res = [[], [1]]
        │       │
        │       ├─ i=1: temp.add(2) → [1,2]
        │       │   └─ dfs(2) @ [1,2]
        │       │       │
        │       │       ├─ add [1,2] → res = [..., [1,2]]
        │       │       │
        │       │       ├─ i=2: temp.add(3) → [1,2,3]
        │       │       │   └─ dfs(3) @ [1,2,3]
        │       │       │       ├─ add [1,2,3]
        │       │       │       └─ for loop: i from 3 to 2 → skip
        │       │       │   └─ backtrack: remove 3 → [1,2]
        │       │       └─ backtrack: (no more i) → return
        │       │
        │       └─ i=2: temp.add(3) → [1,3]
        │           └─ dfs(3) @ [1,3]
        │               ├─ add [1,3]
        │               └─ return
        │           └─ backtrack: remove 3 → [1]
        │
        ├─ i=1: temp.add(2) → [2]
        │   └─ dfs(2) @ [2]
        │       │
        │       ├─ add [2]
        │       │
        │       └─ i=2: temp.add(3) → [2,3]
        │           └─ dfs(3) @ [2,3]
        │               ├─ add [2,3]
        │               └─ return
        │           └─ backtrack: remove 3 → [2]
        │
        └─ i=2: temp.add(3) → [3]
            └─ dfs(3) @ [3]
                ├─ add [3]
                └─ return
            └─ backtrack: remove 3 → []
            
    
     */

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


    static class Solution2 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length < 1) {
                return res;
            }
            List<Integer> paths = new ArrayList<>();
            dfs(0, nums, res, paths);
            return res;
        }

        private void dfs(int index, int[] nums, List<List<Integer>> res, List<Integer> paths) {
            res.add(paths);
            for (int i = index; i < nums.length; i++) {
                paths.add(nums[i]);
                dfs(index + 1, nums, res, paths);
                paths.remove(paths.size() - 1);
            }
        }
    }


    static class Preview_2_1 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            backtrack(0, nums, path, res);
            return res;
        }

        private void backtrack(int index, int[] nums, List<Integer> path, List<List<Integer>> res) {
            res.add(new ArrayList<>(path));
            for (int i = index; i < nums.length; i++) {
                path.add(nums[i]);
                backtrack(i + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private static class Preview_2_27 {
        public List<List<Integer>> subsets(int[] nums) {
            if (nums.length < 1) {
                return new ArrayList<>();
            }
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            backtrack(0, nums, path, res);
            return res;
        }

        private void backtrack(int index, int[] nums, List<Integer> path, List<List<Integer>> res) {
            res.add(new ArrayList<>(path));
            for (int i = index; i < nums.length; i++) {
                path.add(nums[i]);
                backtrack(i + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    /*
        给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
        解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
        示例 1：
            输入：nums = [1,2,3]
            输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        示例 2：
            输入：nums = [0]
            输出：[[],[0]]
    */
    private static class Preview_3_9 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            backtracking(0, nums, path, res);
            return res;
        }

        private void backtracking(int index, int[] nums, List<Integer> path, List<List<Integer>> res) {
            res.add(new ArrayList<>(path));
            for (int i = index; i < nums.length; i++) {
                path.add(nums[i]);
                backtracking(i + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private static class Preview_3_17 {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            backtrack(0, nums, path, res);
            return res;
        }

        private void backtrack(int index, int[] nums, List<Integer> path, List<List<Integer>> res) {
            res.add(new ArrayList<>(path));
            for (int i = index; i < nums.length; i++) {
                path.add(nums[i]);
                backtrack(i + 1, nums, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

}
