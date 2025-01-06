package com.hyx2.basic_data_structure.class03_graph;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author hyx
 * @version 0.1
 * @className Code01_BFS
 * @date 2025/1/1 22:15
 * @description
 * @since jdk 11
 */
public class Code01_BFS {

    // 从 node 出发，进行宽度优先遍历
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

}
