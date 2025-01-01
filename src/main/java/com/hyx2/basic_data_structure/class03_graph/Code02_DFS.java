package com.hyx2.basic_data_structure.class03_graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author hyx
 * @version 0.1
 * @className Code02_DFS
 * @date 2025/1/1 22:24
 * @description
 * @since jdk 11
 */
public class Code02_DFS {
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(cur)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
