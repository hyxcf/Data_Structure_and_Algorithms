package com.hyx.leetcode.treenode.ercha_search;

import java.util.LinkedList;

/**
 * @Author：hyx
 * @Date：2024/9/26 16:17
 * 求范围和（中序遍历非递归实现）
 */
public class E04Leetcode938_01 {

    // 思路：中序遍历非递归实现（左值右）
    public int rangeSumBST(TreeNode root, int low, int high) {
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        while (p != null || !stack.isEmpty()) {
            if (p != null){
                stack.push(p);
                p = p.left;
            }else {
                TreeNode pop = stack.pop();
                // 进行剪枝（优化）
                if (pop.val > high){
                    break;
                }
                if (pop.val >= low){
                    sum += pop.val;
                }
                p = pop.right;
            }
        }
        return sum;
    }

}
