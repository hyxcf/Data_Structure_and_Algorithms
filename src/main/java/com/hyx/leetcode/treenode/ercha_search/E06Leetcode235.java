package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 20:30
 * 求二叉搜索树最近公共祖先（祖先也包括自己）
 * 前提：
 * - 1.节点值唯一
 * - 2.p和q都存在
 */
public class E06Leetcode235 {
    /*
          - 6 -
         /     \
        2       8
       / \     / \
      0   4   7   9
         / \
        3   5

        规律：待查找的节点 p   在某一节点的两侧，那么此节点就是最近的公共祖先
     */

    // 1.p和q节点在树的一边
    // 2.p和q节点在树的两边

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 核心：就是公共祖先是在待查找元素节点的两侧，或者等于待查找节点
        TreeNode a = root;
        while (a.val < p.val && a.val < q.val || p.val < a.val && q.val < a.val) {
            if (p.val < a.val) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return p;
    }

}
