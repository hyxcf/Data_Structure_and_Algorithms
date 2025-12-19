package com.leetcode;

import java.util.HashMap;

/**
 * 难理解
 */
public class LeetCode_105_从前序与中序遍历序列构造二叉树_12_19 {

    /*
    ✅ 关键性质：
        前序的第一个元素 一定是当前子树的根节点。
        在中序中找到这个根，左边就是左子树，右边就是右子树。
        pre: [3, 9, 20, 15, 7]
        in:  [9, 3, 15, 20, 7]
        
        递归执行流程
        buildTree()
            recur(0,0,4) // root == 3
                recur(1,0,0) // root == 9
                    recur(2,0,-1) -> null
                    recur(2,1,0)  -> null 
                    return TreeNode(9)
                recur(2,2,4) // root == 20
                    recur(3,2,2) // root == 15 -> 返回叶子
                    recur(4,4,4) // root = 7 -> 返回叶子
                    return TreeNode(20)
            return TreeNode(3)
     */

    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>(); // dic 的作用：O(1) 时间找到某个值在中序中的位置。

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            dic.put(inorder[i], i);
        }
        return recur(0, 0, inorder.length - 1);
    }

    /**
     * 通过前序遍历和中序遍历重建二叉树的递归函数
     *
     * @param root   当前子树的根节点在 preorder 数组中的下标（索引）
     * @param left   当前子树在 inorder 数组中的左边界（闭区间）
     * @param right  当前子树在 inorder 数组中的右边界（闭区间）
     * @return 重建的二叉树根节点
     *
     * 💡 关键：root 是 preorder 的索引，left/right 是 inorder 的索引范围。
     *  fixme：前序告诉我谁是根，中序告诉我左右子树有多长
     */
    TreeNode recur(int root, int left, int right) {
        if (left > right) return null;                          // 递归终止：空子树
        TreeNode node = new TreeNode(preorder[root]);          // 取 preorder[root] 作为当前根值
        int i = dic.get(preorder[root]);                       // 在 inorder 中找到根的位置 i
        // 左子树：
        //   - preorder 起始位置：root + 1（紧接当前根之后）
        //   - inorder 范围：[left, i - 1]
        node.left = recur(root + 1, left, i - 1);
        // 右子树：
        //   - preorder 起始位置：root + 1 + (i - left) 
        //     （跳过当前根 + 整个左子树的节点数）
        //   - inorder 范围：[i + 1, right]
        node.right = recur(root + 1 + i - left, i + 1, right);
        return node;
    }

}
