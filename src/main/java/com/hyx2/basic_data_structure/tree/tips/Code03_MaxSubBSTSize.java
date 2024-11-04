package com.hyx2.basic_data_structure.tree.tips;

/**
 * @version 0.1
 * @Author hyx
 * @className Code03_MaxSubBSTSize
 * @Date 2024/11/4  20:22
 * @description 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的个数
 * @since jdk 11
 */
public class Code03_MaxSubBSTSize {

    /*
        给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的个数
        情况：
            1、与头节点无关，返回左右子树中二叉搜索树的大小中的最大者
            2、与头节点有关，判断左右两个子树是否是二叉搜索树，左子树的 max < head.val 右子树的 min > head.val
        需要的变量；1、是否是二叉搜索树 2、二叉搜索树的个数 3、子树中的最大值 4、子树中的最小值
     */
    public static int getBSTSize(Node head) {
        return process(head).maxSubBSISize;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null; // 这里判空，后面也要做出相应非空的判断
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int maxSubBSISize = 0;
        // 1、与头节点无关，返回左右子树中二叉搜索树的大小中的最大者
        if (leftInfo != null) {
            maxSubBSISize = leftInfo.maxSubBSISize;
        }
        if (rightInfo != null) {
            maxSubBSISize = Math.max(maxSubBSISize, rightInfo.maxSubBSISize);
        }

        boolean isAllBST = false;
        // 如果可能性2成立，
        // maxSubBSISize = 以 x 为头节点的节点树     isAllBST = true
        if (
            // 左右整体需要是搜索二叉树
                (leftInfo == null ? true : leftInfo.isAllBST) && (rightInfo == null ? true : rightInfo.isAllBST) &&
                        // 左子树的 max < head.val 右子树的 min > head.val
                        (leftInfo == null ? true : leftInfo.max < x.val) && (rightInfo == null ? true : rightInfo.min > x.val)
        ) {
            maxSubBSISize = (leftInfo == null ? 0 : leftInfo.maxSubBSISize) + (rightInfo == null ? 0 : rightInfo.maxSubBSISize) + 1;
            isAllBST = true;
        }

        int min = x.val;
        int max = x.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        return new Info(isAllBST, maxSubBSISize, min, max);
    }

    public static class Info {
        public boolean isAllBST;
        public int maxSubBSISize;
        public int min;
        public int max;

        public Info(boolean isAllBST, int maxSubBSISize, int min, int max) {
            this.isAllBST = isAllBST;
            this.maxSubBSISize = maxSubBSISize;
            this.min = min;
            this.max = max;
        }
    }

    public static class Node {
        private int val;
        private Node left;
        private Node right;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
