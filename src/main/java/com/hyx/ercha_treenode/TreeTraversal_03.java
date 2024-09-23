package com.hyx.ercha_treenode;

import com.hyx.stack.ArrayStack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @Author：hyx
 * @Date：2024/9/23 14:15
 * 将前序、中序、后序的代码进行整合
 */
@SuppressWarnings("all")
public class TreeTraversal_03 {
    /*
              1
             / \
            2   3
           /   / \
          4   5   6
          前：1 2 4 3 5 6
          中：4 2 1 5 3 6
          后：4 2 5 6 3 1

       */


    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );

        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pop = null;// 最新出栈的元素

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) { // 遍历左子树
                System.out.println("前：" + curr.val);
                stack.push(curr);// 压栈
                curr = curr.left;
            } else { // 遍历右子树
                TreeNode peek = stack.peek();
                // 没有右子树
                if (peek.right == null) {
                    pop = stack.pop();
                    System.out.println("中：" + pop.val);
                    System.out.println("后：" + pop.val);
                }
                // 右子树处理完成
                else if (peek.right == pop) {
                    pop = stack.pop();
                    System.out.println("后：" + pop.val);
                }
                // 待处理右子树
                else{
                    System.out.println("中：" + peek.val);
                    curr = peek.right;
                }
            }
        }

    }

}
