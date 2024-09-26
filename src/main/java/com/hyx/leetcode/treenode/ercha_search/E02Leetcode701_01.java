package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 11:23
 * 二叉搜索树中的插入操作（迭代操作）
 */
public class E02Leetcode701_01 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 1.val存在，更新
        // 2.val不存在，新增
        TreeNode p = root;
        TreeNode parent = null;
        while (p != null) {
            parent = p;
            if (val < p.val) {
                p = p.left;
            } else if (p.val < val) {
                p = p.right;
            } else {
                return root;
            }
        }
        TreeNode newTreeNode = new TreeNode(val);
        if (parent == null){
            root = newTreeNode;
            return  root;
        }
        if (parent.val < val){
            parent.right = newTreeNode;
        }else{
            parent.left = newTreeNode;
        }
        return root;
    }
}
