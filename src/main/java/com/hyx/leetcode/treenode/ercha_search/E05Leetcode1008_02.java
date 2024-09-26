package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 19:16
 * 前序遍历构造二叉搜索树 - 上限法（没听懂！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！）
 */
public class E05Leetcode1008_02 {

    public TreeNode bstFromPreorder(int[] preorder) {
        return insert(preorder, Integer.MAX_VALUE);
    }

    /*
        依次处理 preorder 中每个值，返回创建好的节点 或 null
        1.如果超过上限，返回 null 作为孩子返回
        2.如果没超过上限，创建节点，并设置其左右孩子
            左右孩子完整后返回
     */
    int i = 0;

    public TreeNode insert(int[] preorder, int max) {
        if (i == preorder.length) {
            return null;
        }
        int val = preorder[i];
        if (val > max) { // 题目要求没有重复的值
            return null; // 超过上限
        }
        TreeNode node = new TreeNode(val);
        i++;
        node.left = insert(preorder, val);
        node.right = insert(preorder, max);
        return node;
    }
}
