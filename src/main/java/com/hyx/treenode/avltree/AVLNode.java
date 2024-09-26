package com.hyx.treenode.avltree;

/**
 * @Author：hyx
 * @Date：2024/9/26 21:18
 * AVL树节点
 */
public class AVLNode {

    int key;
    Object value;
    AVLNode left;
    AVLNode right;
    int height = 1; // 高度为1

    public AVLNode(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    public AVLNode(int key) {
        this.key = key;
    }

    public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
