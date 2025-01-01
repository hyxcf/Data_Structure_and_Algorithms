package com.hyx2.basic_data_structure.class03_graph;

import java.util.*;

/**
 * @author hyx
 * @version 0.1
 * @className Code03_TopologySort
 * @date 2025/1/1 23:20
 * @description 图的拓扑排序算法（前提：有向无环图）
 * @since jdk 11
 */
public class Code03_TopologySort {

    // directed graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        // key：某一个node
        // value：剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 剩余入度为0的点，才能进这个队列
        Queue<Node> zeroQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }
        // 拓扑排序的结果，依次加入 result
        List<Node> result = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node cur = zeroQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return result;
    }

}

