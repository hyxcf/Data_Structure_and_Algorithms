package com.hyx2.basic_data_structure.class03_graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author hyx
 * @version 0.1
 * @className Graph
 * @date 2025/1/1 21:31
 * @description
 * @since jdk 11
 */
public class Graph {

    // Integer:node.value    通过 node.value 找到 Node
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
