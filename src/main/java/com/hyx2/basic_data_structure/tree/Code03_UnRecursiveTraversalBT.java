package com.hyx2.basic_data_structure.tree;


import java.util.Stack;

/**
 * @version 0.1
 * @Author hyx
 * @className Code03_UnRecursiveTraversalBT
 * @Date 2024/11/1  20:41
 * @description 非递归遍历实现二叉树前中后序遍历，深度优先遍历
 * @since jdk 11
 */
public class Code03_UnRecursiveTraversalBT {

    /*
        1、弹出就打印
        2、右孩子不为空，先压右
        3、左孩子不为空，再压左
     */
    // 左右头
    public static void pre(TreeNode head) {
        System.out.println("pre-order");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.val + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    // 左头右
    public static void in(TreeNode head) {
        System.out.println("in-order");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 左右头
    /*
             1
           /  \
          2    3
         / \  / \
        4  5 6   7
     */
    // 4 5 2 6 7 3 1
    public static void pos1(TreeNode head) {
        System.out.println("pos-order: ");
        if (head != null) {
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().val + " ");
            }
        }
        System.out.println();
    }
}
