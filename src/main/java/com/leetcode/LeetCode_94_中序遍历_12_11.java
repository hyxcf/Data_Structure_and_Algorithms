package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode_94_中序遍历_12_11 {

    // 中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }


}
