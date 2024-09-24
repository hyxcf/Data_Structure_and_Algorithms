package com.hyx.leetcode.treenode.ercha;

import com.hyx.treenode.ercha_treenode.TreeNode;

import java.util.LinkedList;

/**
 * @Author：hyx
 * @Date：2024/9/23 17:39
 * 二叉树最大深度 使用后序遍历(非递归)求解
 */
@SuppressWarnings("all")
public class E02Leetcode104_1 {

    /*
        思路：
            使用非递归后序遍历，栈的最大高度即为最大深度

     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        System.out.println(maxDepth(root));
    }


    public static int maxDepth(TreeNode root) {
        return getDepth(root);
    }

    private static int getDepth(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;
        // 栈的最大高度
        int max = 0;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                // 遍历左子树
                stack.push(root);
                int size = stack.size();
                if (size > max) {
                    max = size;
                }
                root = root.left;

            } else {
                // 遍历右子树
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                } else {
                    root = peek.right;
                }
            }
        }
        return max;
    }
}
