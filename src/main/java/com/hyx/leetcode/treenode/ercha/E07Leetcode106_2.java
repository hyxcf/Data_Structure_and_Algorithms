package com.hyx.leetcode.treenode.ercha;

import com.hyx.treenode.ercha_treenode.TreeNode;

import java.util.HashMap;

/**
 * @Author：hyx
 * @Date：2024/9/24 13:57
 * 从中序与后序遍历序列构造二叉树
 */
public class E07Leetcode106_2 {

    /*
                     1
                   /   \
                  2     3
                 / \   / \
               4    5  6  7
                   / \  \
                  8   9 10
        中序遍历：左 值 右
        后序遍历：左 右 值
                          is             ri           ie
        中序：inorder  =  [4  2  8  5  9  1  6  10  3  7]
                          ps                     pe-1 pe
        后序：postorder = [4  8  9  5  2  10  6  7  3  1]
        后序的最后一个元素就是根节点  2:ps+ri-is-1  10:ps+ri-is
     */

    // 定义一个 hashmap 用于存放 下标和对应的值,以便于能找到 inorder 中的每个子节点的位置
    HashMap<Integer, Integer> memo = new HashMap<>();
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            memo.put(inorder[i], i); // 存放 inorder 中的值和下标。以便能够快速找到父节点的下标
        }
        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;

    }

    private TreeNode buildTree(int is, int ie, int ps, int pe) {
        if (ie < is || pe < ps) {
            return null;
        }
        int root = post[pe];
        int ri = memo.get(root); // 获得当前子树的根节点的下标

        TreeNode parent = new TreeNode(root);
        parent.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
        parent.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
        return parent;
    }

}























