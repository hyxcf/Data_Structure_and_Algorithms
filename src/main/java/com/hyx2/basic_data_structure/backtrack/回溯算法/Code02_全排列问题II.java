package com.hyx2.basic_data_structure.backtrack.回溯算法;

import java.util.*;

public class Code02_全排列问题II {


    // 全排列问题，学到两种经典做法：1、路径选择法  2、交换法

    // 交换法  递归树：permuteUnique([1, 1, 2])
    /*
                                    dfs(0)
                                      │
                    ┌─────────────────┼──────────────────┐
                    │                 │                  │
           i=0: val=1 (new)   i=1: val=1 (seen!)   i=2: val=2 (new)
                    │                 ❌                 │
             swap(0,0) → [1,1,2]                swap(2,0) → [2,1,1]
                    │                                    │
                  dfs(1)                              dfs(1)
                    │                                    │
        ┌───────────┼────────────┐            ┌──────────┼───────────┐
        │           │            │            │          │           │
i=1:1 (new)  i=2:2 (new)   (i<1 skip)   i=1:1 (new)  i=2:1 (seen!)  ...
        │           │                         │          ❌
 swap(1,1)→[1,1,2]  swap(2,1)→[1,2,1]    swap(1,1)→[2,1,1]
        │           │                         │
      dfs(2)      dfs(2)                   dfs(2)
        │           │                         │
   ✅ add [1,1,2]  ✅ add [1,2,1]         ✅ add [2,1,1]
   
   
    dfs(0)
    ├── swap(0,0) -> [1, 1, 2]
    │   ├── dfs(1)
    │   │   ├── swap(1,1) -> [1, 1, 2]
    │   │   │   ├── dfs(2)
    │   │   │   │   └── add [1, 1, 2]
    │   │   │   └── swap back(1,1) -> [1, 1, 2]
    │   │   ├── swap(2,1) -> [1, 2, 1]
    │   │   │   ├── dfs(2)
    │   │   │   │   └── add [1, 2, 1]
    │   │   │   └── swap back(2,1) -> [1, 1, 2]
    │   └── swap back(0,0) -> [1, 1, 2]
    ├── swap(2,0) -> [2, 1, 1]
    │   ├── dfs(1)
    │   │   ├── swap(1,1) -> [2, 1, 1]
    │   │   │   ├── dfs(2)
    │   │   │   │   └── add [2, 1, 1]
    │   │   │   └── swap back(1,1) -> [2, 1, 1]
    │   └── swap back(2,0) -> [1, 1, 2]
     */
    private static class Solution1 {
        List<Integer> nums;
        List<List<Integer>> res;

        public List<List<Integer>> permuteUnique(int[] nums) {
            this.res = new ArrayList<>();
            this.nums = new ArrayList<>();
            for (int num : nums) {
                this.nums.add(num);
            }
            dfs(0);
            return res;
        }
        
        void dfs(int x) {
            if (x == nums.size() - 1) {
                res.add(new ArrayList<>(nums));  // 添加排列方案
                return;
            }
            HashSet<Integer> set = new HashSet<>();
            for (int i = x; i < nums.size(); i++) {
                if (set.contains(nums.get(i)))
                    continue;            // 重复，因此剪枝
                set.add(nums.get(i));
                swap(i, x);              // 交换，将 nums[i] 固定在第 x 位
                dfs(x + 1);           // 开启固定第 x + 1 位元素
                swap(i, x);              // 恢复交换
            }
        }

        void swap(int a, int b) {
            int tmp = nums.get(a);
            nums.set(a, nums.get(b));
            nums.set(b, tmp);
        }

    }

    // 路径选择法
    private static class Solution2 {
        public List<List<Integer>> permuteUnique(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length == 0) {
                return res;
            }
            Arrays.sort(nums); // 👈 关键：排序使重复元素相邻
            boolean[] used = new boolean[len];
            LinkedList<Integer> list = new LinkedList<>();
            dfs(nums, 0, len, used, res, list);
            return res;
        }

        private void dfs(int[] nums, int depth, int len, boolean[] used, List<List<Integer>> res, LinkedList<Integer> list) {
            if (len == depth) {
                res.add(new ArrayList<>(list));
                return;
            }
            for (int i = 0; i < len; i++) {
                if (used[i]) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                list.addLast(nums[i]);
                used[i] = true;
                dfs(nums, depth + 1, len, used, res, list);
                list.removeLast();
                used[i] = false;
            }
        }
    }
}
