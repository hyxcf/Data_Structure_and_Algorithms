package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hyx
 * @version 0.1
 * @className q
 * @date 2026/1/3 22:21
 * @description
 * @since jdk 11
 */
public class Leetcode_39_组合总和_1_3 {

/*
    candidates = [2, 3, 6, 7]
    target = 7

    backtrack(state=[], target=7, start=0)
    │
    ├─ i=0, choose 2 → state=[2], target=5, start=0
    │  │
    │  ├─ i=0, choose 2 → state=[2,2], target=3, start=0
    │  │  │
    │  │  ├─ i=0, choose 2 → state=[2,2,2], target=1, start=0
    │  │  │   │
    │  │  │   ├─ i=0: 1-2=-1 < 0 → ❌ 剪枝一（break，不再试 3,6,7）
    │  │  │   └─ return
    │  │  │
    │  │  ├─ i=1, choose 3 → state=[2,2,3], target=0 → ✅ 加入 res: [[2,2,3]]
    │  │  │
    │  │  ├─ i=2: target=3, 3-6=-3 < 0 → ❌ 剪枝一（break）
    │  │  └─ return
    │  │
    │  ├─ i=1, choose 3 → state=[2,3], target=2, start=1
    │  │   │
    │  │   ├─ i=1: 2-3=-1 < 0 → ❌ 剪枝一（break，因为 choices[1]=3 > 2）
    │  │   └─ return
    │  │
    │  ├─ i=2: target=5, 5-6=-1 < 0 → ❌ 剪枝一（break）
    │  └─ return
    │
    ├─ i=1, choose 3 → state=[3], target=4, start=1
    │  │
    │  ├─ i=1, choose 3 → state=[3,3], target=1, start=1
    │  │   │
    │  │   ├─ i=1: 1-3=-2 < 0 → ❌ 剪枝一（break）
    │  │   └─ return
    │  │
    │  ├─ i=2: 4-6=-2 < 0 → ❌ 剪枝一（break）
    │  └─ return
    │
    ├─ i=2, choose 6 → state=[6], target=1, start=2
    │  │
    │  └─ i=2: 1-6=-5 < 0 → ❌ 剪枝一（break）
    │
    └─ i=3, choose 7 → state=[7], target=0 → ✅ 加入 res: [[2,2,3], [7]]
*/

    /*
        给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
        candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
        对于给定的输入，保证和为 target 的不同组合数少于 150 个。
        示例 1：
            输入：candidates = [2,3,6,7], target = 7
            输出：[[2,2,3],[7]]
            解释：
            2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
            7 也是一个候选， 7 = 7 。
            仅有这两种组合。
        示例 2：
            输入: candidates = [2,3,5], target = 8
            输出: [[2,2,2,2],[2,3,3],[3,5]]
        示例 3：
            输入: candidates = [2], target = 1
            输出: []
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        List<Integer> temp = new ArrayList<>();
        backtracking(0, target, candidates, temp, res);
        return res;
    }

    private void backtracking(int index, int target, int[] candidates, List<Integer> temp, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int choice = candidates[i];
            if (target - choice < 0) {
                break; // 剪枝
            }
            temp.add(choice);
            backtracking(i, target - choice, candidates, temp, res); // fixme:这里的值是可以重复的，因此这里传入的是i
            temp.remove(temp.size() - 1);
        }
    }

    static class Preview_2_1 {
        /*
            给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
            candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
            对于给定的输入，保证和为 target 的不同组合数少于 150 个。
            示例 1：
                输入：candidates = [2,3,6,7], target = 7
                输出：[[2,2,3],[7]]
                解释：
                2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
                7 也是一个候选， 7 = 7 。
                仅有这两种组合。
            示例 2：
                输入: candidates = [2,3,5], target = 8
                输出: [[2,2,2,2],[2,3,3],[3,5]]
            示例 3：
                输入: candidates = [2], target = 1
                输出: []
         */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            if (candidates == null) {
                return res;
            }
            if (candidates.length < 2 && candidates[0] < target) {
                return res;
            }
            List<Integer> path = new ArrayList<>();
            backtrack(0, target, candidates, path, res);
            return res;
        }

        private void backtrack(int index, int target, int[] candidates, List<Integer> path, List<List<Integer>> res) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < candidates.length; i++) {
                int choice = candidates[i];
                if (target - choice < 0) {
                    continue;
                }
                path.add(choice);
                backtrack(i, target - choice, candidates, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    /*
        给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
        candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
        对于给定的输入，保证和为 target 的不同组合数少于 150 个。
        示例 1：
            输入：candidates = [2,3,6,7], target = 7
            输出：[[2,2,3],[7]]
            解释：
            2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
            7 也是一个候选， 7 = 7 。
            仅有这两种组合。
        示例 2：
            输入: candidates = [2,3,5], target = 8
            输出: [[2,2,2,2],[2,3,3],[3,5]]
        示例 3：
            输入: candidates = [2], target = 1
            输出: []
    */
    private static class Preview_2_27 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            if (candidates == null) {
                return res;
            }
            if (candidates.length < 2 && candidates[0] != target) {
                return res;
            }
            List<Integer> path = new ArrayList<>();
            backtracking(0, candidates, target, path, res);
            return res;
        }

        private void backtracking(int index, int[] candidates, int target, List<Integer> path, List<List<Integer>> res) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = index; i < candidates.length; i++) {
                int choice = candidates[i];
                if (target - choice < 0) {
                    continue;
                }
                path.add(choice);
                backtracking(i, candidates, target - choice, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

}
