package com.hyx.leetcode.treenode.ercha_search;

/**
 * @Author：hyx
 * @Date：2024/9/26 19:49
 * 前序遍历构造二叉搜索树（分治法）
 */
public class E05Leetcode1008_03 {

    public TreeNode bstFromPreorder(int[] preorder) {
        return partition(preorder, 0, preorder.length - 1);
    }

    // [8,5,1,7,10,12]
    //  s           e
    // 首先获取根节点的值，然后与根节点进行比较，第一次比根节点大的到后面都是右子树，前面是左子树
    public TreeNode partition(int[] preorder, int start, int end) {
        if (start > end){
            return null;
        }
        TreeNode root = new TreeNode(preorder[start]);
        int index = start + 1;
        while (index <= end) {
            if (preorder[index] > preorder[start]) {
                break;
            }
            index++;
        }
        // index 就是右子树的起点
        root.left = partition(preorder, start + 1, index - 1);
        root.right = partition(preorder, index, end);
        return root;

    }

}
