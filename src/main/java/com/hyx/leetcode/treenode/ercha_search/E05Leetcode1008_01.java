package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 17:00
 * 前序遍历构造二叉搜索树 题目说明 preorder 长度 >= 1
 */
public class E05Leetcode1008_01 {

    /*
        8, 5, 1, 7, 10, 12

                8
               / \
              5  10
             / \  \
            1   7  12


    */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            insert(root, val);
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (root.val < val) {
            root.right = insert(root.right, val);
        }
        return root;
    }

}
