package com.leetcode;

/*
236. 二叉树的最近公共祖先
已解答
中等
相关标签
premium lock icon
相关企业
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
示例 1：
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 */

public class LeetCode_236_二叉树的最近公共祖先_12_22 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }

    /*
    fixme:
        递归函数的行为是：
        “我在我的地盘（子树）里找 p 和 q。
        如果我自己就是 p 或 q，我立刻上报（返回自己）；
        否则，我问左右孩子，再综合他们的答案上报。”
     */
    static class Preview_2_5 {
        // 二叉树的最近公共祖先
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (left == null && right == null) {
                return null;
            }
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            return root;
        }
    }

}

/*
        3
       / \
      5   1
     / \ / \
    6  2 0  8
   / \
  7   4
     
我们将分析 三种典型情况：
    1、p 和 q 在不同子树 → LCA 是分叉点（如 p=5, q=8）
    2、一个节点是另一个的祖先 → LCA 是祖先节点（如 p=5, q=4）
    3、p 或 q 就是根 → LCA 是根（如 p=3, q=4）
     
情况一：p = 5，q = 8（分属左右子树）
    预期结果：LCA = 3
    调用栈追踪（深度优先，后序遍历）：
    lca(3,5,8)
    ├─ 左递归：lca(5,5,8)
    │  ├─ lca(6,5,8)
    │  │  ├─ lca(7,5,8) → root=7, 非p/q → 左null,右null → return null
    │  │  ├─ lca(4,5,8) → root=4, 非p/q → 左null,右null → return null
    │  │  → left=null, right=null → return null
    │  ├─ lca(2,5,8)
    │  │  ├─ lca(7,5,8) → return null
    │  │  ├─ lca(4,5,8) → return null
    │  │  → left=null, right=null → return null
    │  → root=5==p → 直接return 5（跳过后续判断）
    │
    ├─ 右递归：lca(1,5,8)
    │  ├─ lca(0,5,8) → root=0, 非p/q → 左null,右null → return null
    │  ├─ lca(8,5,8) → root=8==q → 直接return 8
    │  → left=null, right=8 → return 8
    │
    → left=5, right=8 → both non-null → return root(3) ✅
    
情况二：p=5, q=4（祖先关系）
    预期结果：5
    lca(3,5,4)
    ├─ lca(5,5,4)
    │  ├─ lca(6,5,4)
    │  │  ├─ lca(7,5,4) → null
    │  │  ├─ lca(4,5,4) → root=4==q → return 4
    │  │  → left=null, right=4 → return 4
    │  ├─ lca(2,5,4)
    │  │  ├─ lca(7,5,4) → null
    │  │  ├─ lca(4,5,4) → return 4
    │  │  → left=null, right=4 → return 4
    │  → root=5==p → 直接return 5 ⭐
    │
    ├─ lca(1,5,4)
    │  ├─ lca(0,5,4) → null
    │  ├─ lca(8,5,4) → null
    │  → left=null, right=null → return null
    │
    → left=5, right=null → return 5 ✅
    
情况三：p=3, q=4（根节点是p）
    预期结果：3
    lca(3,3,4)
    → root=3==p → 直接return 3 ⭐

递归调用模式总结
    通用递归模式：
    lca(node)
    1. 如果node为null/p/q → 直接返回node
    2. 递归左子树 → left
    3. 递归右子树 → right
    4. 判断：
       - left和right都null → null
       - left为null → 返回right
       - right为null → 返回left
       - left和right都不null → 返回当前node（找到了LCA）
 */