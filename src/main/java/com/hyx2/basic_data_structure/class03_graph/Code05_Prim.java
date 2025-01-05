package com.hyx2.basic_data_structure.class03_graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author hyx
 * @version 0.1
 * @className Code05_Prim
 * @date 2025/1/2 23:07
 * @description Prim算法：从点的角度出发
 * @since jdk 11
 */
public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        // 这个集合的作用是 存放解锁的点集
        HashSet<Node> set = new HashSet<>();
        // 这个集合的作用是 已经考虑过的边不要重复考虑
        HashSet<Edge> edgeSet = new HashSet<>();
        HashSet<Edge> result = new HashSet<>(); // 依次挑选的边在result里
        // 这个for循环的目的是防止森林，可以不加
        for (Node node : graph.nodes.values()) { // 由一个点，解锁所有相连的边
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    if (!edgeSet.contains(edge)) {
                        edgeSet.add(edge);
                        priorityQueue.add(edge);
                    }
                }
                // 从边的小根堆中挑一个最小的边出来
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();// 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    if (!set.contains(toNode)) { // 不含有的时候，就是新的点
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            if (!edgeSet.contains(nextEdge)) {
                                edgeSet.add(nextEdge);
                                priorityQueue.add(nextEdge);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}




























