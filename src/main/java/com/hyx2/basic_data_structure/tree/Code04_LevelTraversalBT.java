package com.hyx2.basic_data_structure.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 0.1
 * @Author hyx
 * @className Code04_LevelTraversalBT
 * @Date 2024/11/1  21:32
 * @description 二叉树的宽度优先遍历
 * @since jdk 11
 */
public class Code04_LevelTraversalBT {

    // 使用队列
    public static void level(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

}
