package com.hyx2.basic_data_structure.tree.tips;

/**
 * @version 0.1
 * @Author hyx
 * @className Code02_MaxDistance
 * @Date 2024/11/4  20:06
 * @description 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 * @since jdk 11
 */
public class Code02_MaxDistance {

    /*
         给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离
         两种情况：
             1. 过 x 点，返回左树的高度和左树的高度
             2. 不过 x 点，返回左树和右树中的最高高度
     */

    public static int maxDistance2(Node head){
        return process(head).maxDistance;
    }

    public static Info process(Node x) {
        if (x == null) return new Info(0, 0);
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(
                Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(maxDistance, height);
    }

    public static class Info {
        private int maxDistance;
        private int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
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
