package com.hyx.leetcode.treenode.ercha;

import com.hyx.treenode.ercha_treenode.TreeNode;

/** 
 * @Author：hyx
 * @Date：2024/9/23 19:53
 * 二叉树最小深度（递归实现）
 */
@SuppressWarnings("all")
public class E03Leetcode111_1 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int i = minDepth(root.left);
        int j = minDepth(root.right);
        // 说明没有左子树，则 返回 右子树深度 + 1
        if (i == 0){
            return j + 1;
        }
        // 说明没有右子树，则 返回 左子树深度 + 1
        if (j == 0){
            return i + 1;
        }
        return Integer.min(i, j) + 1;
    }
}
