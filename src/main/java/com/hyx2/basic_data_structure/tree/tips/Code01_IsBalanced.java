package com.hyx2.basic_data_structure.tree.tips;

/**
 * @version 0.1
 * @Author hyx
 * @className Code01_IsBalanced
 * @Date 2024/11/4  19:34
 * @description 给定一棵二叉树的头节点head，返回这颗二叉树是不是平衡二叉树
 * @since jdk 11
 */
/*
二叉树的递归套路（重要）
- 可以解决面试中绝大多数的二叉树问题尤其是树型dp问题
  本质是利用递归遍历二叉树的便利性
1. 假设以X节点为头，假设可以向X左树和X右树要任何信息
2. 在上一步的假设下，讨论以X为头节点的树，得到答案的可能性(最重要)
3. 列出所有可能性后，确定到底需要向左树和右树要什么样的信息
4. 把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
5. 递归函数都返回S，每一棵子树都这么要求
6. 写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
 */
public class Code01_IsBalanced {


    /**
     * 判断是否平衡
     * @param head head
     * @return isBalanced
     */
    public static boolean isBalanced2(Node head) {
        return process2(head).isBalanced;
    }

    public static Info process2(Node node){
        if (node == null){
            return new Info(true,0);
        }
        Info left = process2(node.left);
        Info right = process2(node.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        return new Info(isBalanced,height);
    }


    public static class Info{
        private boolean isBalanced;
        private int height;

        public Info() {
        }

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static class Node {
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(Node left, Node right) {
            this.left = left;
            this.right = right;
        }
    }

}
