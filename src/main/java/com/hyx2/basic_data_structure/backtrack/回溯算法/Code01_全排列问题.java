package com.hyx2.basic_data_structure.backtrack.回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Code01_全排列问题 {

    // 方法二：交换法
    /*
    dfs(0)
    ├─ i=0:
    │   swap(0,0) → arr = [1,2,3]
    │   ├─ dfs(1)
    │   │  ├─ i=1:
    │   │  │   swap(1,1) → [1,2,3]
    │   │  │   ├─ dfs(2) → x == 3-1 → add [1,2,3]
    │   │  │   └─ swap(1,1) → [1,2,3]  // 回溯
    │   │  └─ i=2:
    │   │      swap(1,2) → [1,3,2]
    │   │      ├─ dfs(2) → add [1,3,2]
    │   │      └─ swap(1,2) → [1,2,3]  // 回溯
    │   └─ swap(0,0) → [1,2,3]  // 回溯
    
    ├─ i=1:
    │   swap(0,1) → [2,1,3]
    │   ├─ dfs(1)
    │   │  ├─ i=1:
    │   │  │   swap(1,1) → [2,1,3]
    │   │  │   ├─ dfs(2) → add [2,1,3]
    │   │  │   └─ swap(1,1) → [2,1,3]
    │   │  └─ i=2:
    │   │      swap(1,2) → [2,3,1]
    │   │      ├─ dfs(2) → add [2,3,1]
    │   │      └─ swap(1,2) → [2,1,3]  // 回溯
    │   └─ swap(0,1) → [1,2,3]  // 回溯
    
    └─ i=2:
        swap(0,2) → [3,2,1]
        ├─ dfs(1)
        │  ├─ i=1:
        │  │   swap(1,1) → [3,2,1]
        │  │   ├─ dfs(2) → add [3,2,1]
        │  │   └─ swap(1,1) → [3,2,1]
        │  └─ i=2:
        │      swap(1,2) → [3,1,2]
        │      ├─ dfs(2) → add [3,1,2]
        │      └─ swap(1,2) → [3,2,1]  // 回溯
        └─ swap(0,2) → [1,2,3]  // 最终回溯，恢复原数组
     */
    
    private static class Solution2 {
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

    // 方法一：路径选择法
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        boolean[] used = new boolean[len];
        LinkedList<Integer> path = new LinkedList<>();
        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, LinkedList<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (len == depth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                System.out.println("递归之前：" + path);
                dfs(nums, len, depth + 1, path, used, res);
                used[i] = false;
                path.removeLast();
                System.out.println("递归之后：" + path);
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Code01_全排列问题 solution = new Code01_全排列问题();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }
}
