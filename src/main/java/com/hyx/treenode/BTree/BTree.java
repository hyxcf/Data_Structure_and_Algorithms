package com.hyx.treenode.BTree;

/**
 * @author hyx
 * @Date 2024/10/3 16:21
 * @description B-树
 * <p>
 * B-树的特性
 * 1. 每个节点最多有m个孩子，其中m称为B-树的阶
 * 2. 除根节点和叶子节点，其他每个节点至少有ceil(m/2)个孩子
 * 3. 若根节点不是叶子节点，则至少有两个孩子
 * 4. 所有叶子节点都在同一层
 * 5. 每个非叶子节点由 n 个关键字和 n+1 个指针组成，其中 ceil(m/2)-1 <= n <= m-1
 * 6. 关键字按非降序怕排列，即节点中的第 i 个关键字大于等于第 i - 1 关键字
 * 7. 指针P[i]指向关键字位于第 i 个关键字和第 i+1 个关键字的之间的子树
 */
public class BTree {

    static class Node {
        int[] keys; // 关键字
        Node[] children; // 孩子
        int keyNumber; // 有效关键字数目
        boolean leaf = true; // 是否是叶子节点
        int t; // 最小度数（最小孩子数）

        public Node(int t) { // t >= 2
            this.t = t;
            this.children = new Node[2 * t];
        }


    }

}
