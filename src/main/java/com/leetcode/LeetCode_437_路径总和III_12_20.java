package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode_437_路径总和III_12_20 {

    private int ans;

    public int pathSum(TreeNode root, int targetSum) {
        // 和 560 题一样套路，使用前缀和
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        dfs(root, 0L, targetSum, map);
        return ans;
    }

    private void dfs(TreeNode node, Long s, int targetSum, Map<Long, Integer> map) {
        if (node == null) {
            return;
        }
        s += node.val;
        ans += map.getOrDefault(s - targetSum, 0);
        map.merge(s, 1, Integer::sum); // map[s]++
        dfs(node.left, s, targetSum, map);
        dfs(node.right, s, targetSum, map);
        map.merge(s, -1, Integer::sum); // map[s]-- 恢复现场（撤销 cnt[s]++）
    }

}
