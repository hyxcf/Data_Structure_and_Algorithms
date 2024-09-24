package com.hyx.leetcode.treenode.ercha;

import com.hyx.ercha_treenode.TreeNode;

import java.util.Arrays;

/**
 * @Author：hyx
 * @Date：2024/9/24 13:31
 * 从中序与后序遍历序列构造二叉树
 */
public class E07Leetcode106 {

    /*
        中序遍历：左 值 右
        后序遍历：左 右 值
        中序：inorder  =  [4,2,1,6,3,7]
        后序：postorder = [4,2,6,7,3,1]
        后序的最后一个元素就是根节点
     */

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0){
            return null;
        }
        int rootValue = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootValue);
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) { // i = 2
                // inorder的左子树数组
                int[] inLeft = Arrays.copyOfRange(inorder, 0, i);// [4,2]
                // inorder的右子树数组
                int[] inRight = Arrays.copyOfRange(inorder, i + 1, inorder.length);// [6,3,7]

                // postorder的左子树数组
                int[] postLeft = Arrays.copyOfRange(postorder, 0, i); // [4,2]
                // postorder的右子树数组
                int[] postRight = Arrays.copyOfRange(postorder, i, postorder.length - 1);// [6,7,3]

                root.left = buildTree(inLeft, postLeft);
                root.right = buildTree(inRight,postRight);
                break;
            }
        }
        return root;
    }

}
