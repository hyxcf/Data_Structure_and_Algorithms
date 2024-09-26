package com.hyx.leetcode.treenode.ercha_search;

import java.util.LinkedList;

/**
 * @Author：hyx
 * @Date：2024/9/26 12:58
 * 验证二叉搜索树（迭代版）
 */
public class E04Leetcode98_01 {

    /**
     * 思路：使用中序遍历 来判断是否是有序数组。
     * 如果是，则满足二叉搜索树的特性
     * 如果不是，则不满足二叉搜索树的特性
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        // 中序遍历
        TreeNode p = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {// 遍历右子树
                TreeNode pop = stack.pop();
                // 处理值
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

}
