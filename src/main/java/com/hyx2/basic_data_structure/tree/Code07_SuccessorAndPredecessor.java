package com.hyx2.basic_data_structure.tree;

/**
 * @version 0.1
 * @Author hyx
 * @className Code07_SuccessorAndPredecessor
 * @Date 2024/11/4  16:19
 * @description 二叉树中的前驱节点和后继节点
 * @since jdk 11
 */
public class Code07_SuccessorAndPredecessor {

    public static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node parent;
    }

    /**
     * 获取二叉树 x 节点的前驱节点
     * 1、x 节点有左树，x 的前驱节点是左树中的最大节点
     * 2、x 节点没有左树，x 的后继节点是 （往上找，找到节点：该节点是父节点的右孩子，这个父节点就是 x 的前驱节点）
     *
     * @param node node
     * @return node
     */
    public Node successor(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            return getRightMost(node.left);
        } else {
            Node parent = node.parent;
            while (parent != null && node.right != node) { // 当前节点是父亲节点左孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private Node getRightMost(Node node) {
        if (node == null) return null;
        if (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 获取二叉树中 x 节点的后继节点
     * 1、x 节点有右树，x 的后继节点是右树中的最小节点
     * 2、x 节点没有右树，x 的后继节点是 （往上找，找到节点：该节点是父节点的左孩子，这个父节点就是 x 的后继节点）
     *
     * @param node node
     * @return node.next
     */
    public Node predecessor(Node node) {
        if (node == null) {
            return null;
        }
        // 1. x 节点有右树，x 的后继结点是右树中的最小节点
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            // 2、x 节点没有右数，x 的后继节点是 （往上找，找到节点：该节点是父节点的左孩子，这个父节点就是 x 的后继节点）
            Node parent = node.parent;
            while (parent != null && node.left != node) { // 当前节点是父亲节点右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private Node getLeftMost(Node node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
