package com.hyx.treenode.ercha_treenode;

/**
 * @Author：hyx
 * @Date：2024/9/23 9:27
 * 深度优先遍历：分为前序遍历、中序遍历、后序遍历
 * 使用递归实现
 */
public class TreeTraversal {
    /*
            1
           / \
          2   3
         /   / \
        4   5   6
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    /**
     * 前序遍历
     *
     * @param node 节点
     */
    static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        // 1.得到子节点的值
        System.out.print(node + "\t");
        // 2.得到左子树的值
        preOrder(node.left);
        // 3.得到右子树的值
        preOrder(node.right);
    }

    /**
     * 中序遍历
     *
     * @param node 节点
     */
    static void inOrder(TreeNode node) {
        if (node == null){
            return;
        }
        // 1.得到左子树的值
        inOrder(node.left);
        // 2.得到子节点的值
        System.out.print(node.val + "\t");
        // 3.得到右节点的值
        inOrder(node.right);
    }

    /**
     * 后序遍历
     *
     * @param node 节点
     */
    static void postOrder(TreeNode node) {
        if (node == null){
            return;
        }
        // 1.得到左子树的值
        postOrder(node.left);
        // 2.得到右子树的值
        postOrder(node.right);
        // 3.得到子节点的值
        System.out.print(node.val + "\t");
    }
}
