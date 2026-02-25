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


    /*
        给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
        路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

        示例 1：
            输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
            输出：3
            解释：和等于 8 的路径有 3 条，如图所示。
        示例 2：
            输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
            输出：3
     */
    /*
        fixme:
            该方法将当前节点的前缀和加入 map，仅用于子树的计算；
            在计算当前节点自身作为路径终点的合法路径数时，map 中并不包含当前前缀和，而是只包含其祖先的前缀和；
            这样才能正确统计所有 从某个祖先到当前节点 的、和为 targetSum 的向下路径。
     */
    static class Preview_2_5 {
        Map<Long, Integer> cnt = new HashMap<>();

        public int pathSum(TreeNode root, int targetSum) {
            cnt.put(0L, 1);
            return dfs(root, 0, targetSum);
        }

        private int dfs(TreeNode root, long sum, int targetSum) {
            if (root == null)
                return 0;

            sum += root.val;
            int num = cnt.getOrDefault(sum, 0);
            cnt.put(sum, num + 1);
            int res = dfs(root.left, sum, targetSum) + dfs(root.right, sum, targetSum);
            cnt.put(sum, num);
            return res + cnt.getOrDefault(sum - targetSum, 0);
        }
    }

    private static class Preview_2_25 {
        /*
            给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
            路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

            示例 1：
                输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
                输出：3
                解释：和等于 8 的路径有 3 条，如图所示。
            示例 2：
                输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
                输出：3
         */
        public int pathSum(TreeNode root, int targetSum) {
            Map<Long, Integer> map = new HashMap<>();
            map.put(0L, 1);
            return dfs(root, 0, targetSum, map);
        }

        private int dfs(TreeNode root, long sum, int targetSum, Map<Long, Integer> map) {
            if (root == null) {
                return 0;
            }
            sum += root.val;
            Integer num = map.getOrDefault(sum, 0);
            map.put(sum, num + 1);
            int res = dfs(root.left, sum, targetSum, map) + dfs(root.right, sum, targetSum, map);
            map.put(sum, num);
            return res + map.getOrDefault(sum - targetSum, 0);
        }
    }

}
