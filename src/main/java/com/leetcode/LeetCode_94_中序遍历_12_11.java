package com.leetcode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode_94_中序遍历_12_11 {

    // 中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }


    static class Preview_2_3 {
        // 二叉树的中序遍历
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    private static class Preview_2_24 {
        // 二叉树的中序遍历 左中右
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    private static class Preview_3_18 {
        // 二叉树的中序遍历
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> st = new Stack<>();
            while (!st.isEmpty() || root != null) {
                if (root != null) {
                    st.push(root);
                    root = root.left;
                } else {
                    root = st.pop();
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    private static class Preview_4_22 {
        // 二叉树的中序遍历
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> st = new Stack<>();
            while (!st.isEmpty() || root != null) {
                if (root != null) {
                    st.push(root);
                    root = root.left;
                } else {
                    root = st.pop();
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

    private static class Preview_7_14 {
        // 二叉树的中序遍历
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> st = new Stack<>();
            while (!st.isEmpty() || root != null) {
                if (root != null) {
                    st.push(root);
                    root = root.left;
                } else {
                    root = st.pop();
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }

}
