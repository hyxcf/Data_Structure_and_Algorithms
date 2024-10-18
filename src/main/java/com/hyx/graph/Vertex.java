package com.hyx.graph;

import java.util.List;
import java.util.Objects;

/**
 * @version 0.1
 * @Author hyx
 * @className Vertex
 * @Date 2024/10/17  14:43
 * @description 顶点
 * @since jdk 11
 */
public class Vertex {

    String name;
    List<Edge> edges;
    boolean visited; // 是否被访问过,用在 BFS 和 DFS
    int inDegree; // 入度，用在拓扑排序
    int status; // 状态 0-未访问 1-访问中 2-访问过，用在拓扑排序
    int dist = INF; // 用于 迪克斯特拉 算法表示距离
    static final Integer INF = Integer.MAX_VALUE;
    Vertex prev = null;


    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static void main(String[] args) {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");

        a.edges = List.of(new Edge(b), new Edge(c));
        b.edges = List.of(new Edge(d));
        c.edges = List.of(new Edge(d));
        d.edges = List.of();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "name='" + name + '\'' +
                ", dist=" + dist +


                '}';
    }
}

