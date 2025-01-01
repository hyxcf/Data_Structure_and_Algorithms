package com.hyx2.basic_data_structure.class03_graph;

/**
 * @author hyx
 * @version 0.1
 * @className Edge
 * @date 2025/1/1 21:29
 * @description
 * @since jdk 11
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
