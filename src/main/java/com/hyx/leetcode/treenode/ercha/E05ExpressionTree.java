package com.hyx.leetcode.treenode.ercha;

import java.util.LinkedList;

/**
 * @Author：hyx
 * @Date：2024/9/24 10:21
 * 根据后缀表达式构造表达树
 */
public class E05ExpressionTree {

    /*
        中缀表达式           (2-1)*3
        后缀（逆波兰）表达式   21-3*

        表达式树
            *
           / \
          -   3
         / \
        2   1

        1.遇到数字入栈
        2.遇到运算符出栈，建立节点关系，再入栈
     */

    public TreeNode constructExpressionTree(String[] tokens) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        for (String t : tokens) {
            switch (t) {
                case "+", "-", "*", "/" -> {
                    TreeNode right = stack.pop();
                    TreeNode left = stack.pop();
                    TreeNode parent = new TreeNode(t);
                    parent.left = left;
                    parent.right = right;
                    stack.push(parent);
                }
                // 是数字，直接入栈
                default -> {
                    stack.push(new TreeNode(t));
                }
            }
        }
        return stack.peek();
    }

    static class TreeNode {
        public String val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(String val) {
            this.val = val;
        }

        public TreeNode(String val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val='" + val + '\'' +
                    '}';
        }
    }

}
