package com.leetcode;

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

}
