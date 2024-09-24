package com.hyx.leetcode.treenode.ercha;

import com.hyx.treenode.ercha_treenode.TreeNode;

/**
 * @Author：hyx
 * @Date：2024/9/23 17:39
 */
@SuppressWarnings("all")
public class E02Leetcode104_2 {

    /*
        思路：
            递归：得到左右子树深度，然后比较最大值，得到层数
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        System.out.println(maxDepth(root));
    }


    public static int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int d1 = maxDepth(node.left);
        int d2 = maxDepth(node.right);
        return Integer.max(d1, d2) + 1;
    }

}
