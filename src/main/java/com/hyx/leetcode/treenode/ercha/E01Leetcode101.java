package com.hyx.leetcode.treenode.ercha;

import com.hyx.ercha_treenode.TreeNode;

/**
 * @Author：hyx
 * @Date：2024/9/23 16:53
 * 对称二叉树
 */
public class E01Leetcode101 {

    public boolean isSymmetric(TreeNode root) {
        return check(root.left,root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right ==null){
            return true;
        }
        if (left == null || right == null){
            return false;
        }
        if (left.val != right.val){
            return false;
        }
        return check(left.left,right.right) && check(left.right,right.left);
    }

}
