package com.leetcode;

import java.util.Stack;

public class LeetCode_98_验证二叉搜索树_12_16 {

    // 中序遍历验证是否是二叉搜索树，我只需要知道中序遍历，在遍历时 检查每一个新节点是否比前一个大 即可。
    public boolean isValidBST(TreeNode root) {
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        long prev = Long.MIN_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    long prev = Long.MIN_VALUE;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        boolean a = isValidBST(root.left);
        if (!a) return false;
        if (prev >= root.val) {
            return false;
        }
        prev = root.val;
        return isValidBST(root.right);
    }

    static class Preview_2_4 {
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            Stack<TreeNode> st = new Stack<>();
            long prev = Long.MIN_VALUE;
            while (!st.isEmpty() || root != null) {
                if (root != null) {
                    st.push(root);
                    root = root.left;
                } else {
                    TreeNode pop = st.pop();
                    if (pop.val <= prev) {
                        return false;
                    }
                    prev = pop.val;
                    root = pop.right;
                }
            }
            return true;
        }
    }

    private static class Preview_2_24 {
        // 验证二叉搜索树，左<中<右，中序遍历判断是否满足这个规律即可
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            Stack<TreeNode> st = new Stack<>();
            long prev = Long.MIN_VALUE;
            while (!st.isEmpty() || root != null) {
                if (root != null) {
                    st.push(root);
                    root = root.left;
                } else {
                    TreeNode pop = st.pop();
                    if (prev >= pop.val) {
                        return false;
                    }
                    prev = pop.val;
                    root = pop.right;
                }
            }
            return true;
        }
    }

}
/*                  [10]
                prev : 8
        10      3 5 7 8 10
       /
      5
     / \
    3   7
         \
          8
 */