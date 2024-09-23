package com.hyx.ercha_treenode;

import java.util.LinkedList;

/**
 * @Author：hyx
 * @Date：2024/9/23 9:27
 * 深度优先遍历：分为前序遍历、中序遍历、后序遍历
 * 使用非递归方法遍历(使用栈的数据结构来记住来时的路)
 */
@SuppressWarnings("all")
public class TreeTraversal_02 {
    /*
            1
           / \
          2   3
         /   / \
        4   5   6
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );

        TreeNode curr = root;
        // 使用栈结构来记住来时的路
        /*
            去的路线是前序遍历
            回的路线是中序遍历
         */
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curr != null || !stack.isEmpty()) { // 1 2 4 3 5 6
            if (curr != null) {
                System.out.println("去："+curr.val); // 1 2 4 3 5 6
                // 压栈
                stack.push(curr); //  1 2 4 3 5 6
                curr = curr.left; // curr: 2 4 5
            } else {
                // 弹栈
                TreeNode pop = stack.pop();         // 4 2 1 5 3 6
                System.out.println("回："+pop.val); // 4 2 1 5 3 6
                // 判断右子树是否有元素
                curr = pop.right; // 3 6
            }
        }

        // 后序遍历
/*        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode pop = null; // 最近一次弹栈的元素

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
//                System.out.println("去：" + curr.val);
                // 压栈
                stack.push(curr);
                curr = curr.left;
            } else {
                // 判断栈顶的元素是否遍历过右子树
                TreeNode peek = stack.peek();
                // if (peek.right == null) { // 如果是这样写有一个问题，叶子节点是没有左右子树的，但是非叶子节点是可能有左右子树的，对于这种情况，这一个条件是不够的
                // 可以使用一个变量，来保存最近一次弹栈的元素
                if (peek.right == null || peek.right == pop) {
                    // 没有右子树或没有遍历过右子树，进行弹栈
                    pop = stack.pop();
                    System.out.println("回：" + pop); // 6 3
                } else {
                    curr = peek.right;
                }
            }
        }*/
    }
}
