package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 10:36
 * 删除二叉搜索树中的节点
 */

public class E01Leetcode450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode cur = root; // root:5 key:3 root:0 key:0
        TreeNode curParent = null;
        while (cur != null && cur.val != key) {
            if (cur.val < key) {
                curParent = cur;
                cur = cur.right;
            } else if (key < cur.val) {
                curParent = cur;
                cur = cur.left;
            }
        }
        // cur = 3, curParent = 5
        if (cur == null) {
            return root;
        }
        if (cur.left == null && cur.right == null) {
            return root;
        } else if (cur.left == null) {
            shift(curParent, cur, cur.right, cur);
        } else if (cur.right == null) {
            shift(curParent, cur, cur.left, cur);
        } else {
            // 找后继节点
            TreeNode s = cur.right; // s = 4
            TreeNode sParent = cur; // sParent = 3
            if (s.left != null) {
                sParent = s;
                s = s.left;
            }
            // 查看后继节点和待删除节点是否相邻
            if (cur != sParent) { // 说明不相邻
                // 进行托孤
                shift(sParent, s, s.right, cur);
                s.right = cur.right;
            }
            // 相邻
            shift(curParent, cur, s, cur);
            s.left = cur.left;
        }
        return root;
    }

    public void shift(TreeNode parent, TreeNode deleted, TreeNode child, TreeNode root) {
        // 如果删除的节点没有父节点
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) { // 判断删除的节点是父节点的左节点还是右节点
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

}
