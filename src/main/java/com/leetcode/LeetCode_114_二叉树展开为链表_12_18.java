package com.leetcode;

public class LeetCode_114_二叉树展开为链表_12_18 {

    /**
     * 核心思想就是，将当前节点的左子树挪到当前节点的右子树上，然后找到右子树的下一个节点，继续如此操作
     * 就是将右边最小的放到左边最大的下面，然后变成右子树，循环操作
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
                root = root.right;
            }
        }
    }

    /*
        给你一个二叉树的根节点 root，请你将它原地展开为一个单链表：
        展开后的单链表应该同样使用 TreeNode 类；
        单链表中的节点顺序应为 先序遍历（Pre-order Traversal） 的顺序；
        所有节点的 左子指针必须为 null；
        所有节点的 右子指针指向下一个节点（形成向右的链表）。
        ⚠️ “原地” 意味着：不能创建新节点，只能修改原有节点的指针。
        🌳 示例

            1
           / \
          2   5
         / \   \
        3   4   6
        先序遍历顺序：1 → 2 → 3 → 4 → 5 → 6
        输出（展开后）：
        1
         \
          2
           \
            3
             \
              4
               \
                5
                 \
                  6
        即：每个节点的 left = null，right 指向先序遍历的下一个节点。
     */

    /*
     * 核心思想就是，将当前节点的左子树挪到当前节点的右子树上，然后找到右子树的下一个节点，继续如此操作
     * 就是将右边最小的放到左边最大的下面，然后变成右子树，循环操作
     */
    static class Preview_2_4 {
        public void flatten(TreeNode root) {
            if (root == null) return;
            while (root != null) {
                if (root.left == null) {
                    root = root.right;
                } else {
                    TreeNode pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = root.right;
                    root.right = root.left;
                    root.left = null;
                    root = root.right;
                }
            }
        }
    }

    private static class Preview_2_24 {
        // 二叉树展开为链表
        public void flatten(TreeNode root) {
            if (root == null) return;
            while (root != null) {
                if (root.left == null) {
                    root = root.right;
                } else {
                    TreeNode pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = root.right;
                    root.right = root.left;
                    root.left = null;
                    root = root.right;
                }
            }
        }
    }

}
