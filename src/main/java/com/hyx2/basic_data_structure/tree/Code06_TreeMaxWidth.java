package com.hyx2.basic_data_structure.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 0.1
 * @Author hyx
 * @className Code06_TreeMaxWidth
 * @Date 2024/11/1  21:37
 * @description 二叉树的最大宽度
 * @since jdk 11
 */
public class Code06_TreeMaxWidth {

    // 思路：在每一层遍历时，更新下一层的结束节点
    public static int maxWidthNoMap(TreeNode head){
        if (head == null){
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        TreeNode curEnd = head; // 当前层的节点
        TreeNode nextEnd = null; // 下一层的节点
        int max = 0;
        int curLevelNodes = 0; // 当前层的节点数
        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            if (cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd){
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

}
