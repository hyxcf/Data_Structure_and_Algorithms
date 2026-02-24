package com.leetcode;

import java.util.HashMap;
import java.util.Map;

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
     * @param preIndex 当前子树的根节点在 preorder 数组中的下标（索引）
     * @param left     当前子树在 inorder 数组中的左边界（闭区间）
     * @param right    当前子树在 inorder 数组中的右边界（闭区间）
     * @return 重建的二叉树根节点
     * <p>
     * 💡 关键：preIndex 是 preorder 的索引，left/right 是 inorder 的索引范围。
     *  fixme：前序告诉我谁是根，中序告诉我左右子树有多长
     */
    TreeNode recur(int preIndex, int left, int right) {
        if (left > right) return null;                          // 递归终止：空子树
        TreeNode node = new TreeNode(preorder[preIndex]);          // 取 preorder[preIndex] 作为当前根值
        int i = dic.get(preorder[preIndex]);                       // 在 inorder 中找到根的位置 i
        // 左子树：
        //   - preorder 起始位置：preIndex + 1（紧接当前根之后）
        //   - inorder 范围：[left, i - 1]
        node.left = recur(preIndex + 1, left, i - 1);
        // 右子树：
        //   - preorder 起始位置：preIndex + 1 + (i - left)
        //     （跳过当前根 + 整个左子树的节点数）
        //   - inorder 范围：[i + 1, right]
        node.right = recur(preIndex + 1 + i - left, i + 1, right);
        return node;
    }


    static class Preview_2_5 {
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> map = new HashMap<>();
            // fixme:为什么这里放的是中序遍历的值，因为需要知道当前根距离左右子树的长度
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return recur(0, 0, preorder.length - 1, map, preorder);
        }

        private TreeNode recur(int preIndex, int left, int right, Map<Integer, Integer> map, int[] preorder) {
            if (left > right) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[preIndex]);
            // 根节点在中序数组中的下标位置
            Integer index = map.get(preorder[preIndex]);
            node.left = recur(preIndex + 1, left, index - 1, map, preorder);
            node.right = recur(preIndex + 1 + (index - left), index + 1, right, map, preorder);
            return node;
        }
    }

    private static class Preview_2_24 {
        // 从前序与中序遍历序列构造二叉树
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return recur(0, 0, preorder.length - 1, map, preorder);
        }

        private TreeNode recur(int preIndex, int left, int right, Map<Integer, Integer> map, int[] preorder) {
            if (left > right) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[preIndex]);
            // 根节点在中序数组中的下标位置
            Integer index = map.get(preorder[preIndex]);
            node.left = recur(preIndex + 1, left, index - 1, map, preorder);
            node.right = recur(preIndex + 1 + (index - left), index + 1, right, map, preorder);
            return node;
        }
    }

}
