package com.hyx2.basic_data_structure.class03_graph;

import java.util.ArrayList;

/**
 * @author hyx
 * @version 0.1
 * @className Node
 * @date 2025/1/1 21:18
 * @description 有向图和无向图的节点（通用）
 * @since jdk 11
 */
public class Node {

    public int value;
    public int in;
    public int out;
    // 自己出发的直接邻居
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
