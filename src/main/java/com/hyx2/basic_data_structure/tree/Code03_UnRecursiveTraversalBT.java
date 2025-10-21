package com.hyx2.basic_data_structure.tree;


import java.util.Set;
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
    // 头左右
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
                    stack.push(head); // 整条左边界依次入栈
                    head = head.left;
                } else {
                    head = stack.pop(); // 上面条件不满足：弹出节点就打印。   右树继续上面的操作
                    System.out.println(head.val + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    // 左右头  （反过来就是 头右左，也就是先序遍历头左右【左右反过来】）
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

    // 后续遍历的另一种操作
    public static void pos2(TreeNode h) {
        System.out.println("pos-order: ");
        if (h != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(h);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.println(stack.pop().val + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

}
