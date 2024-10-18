package com.hyx.picture;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 0.1
 * @Author hyx
 * @className FloydWarshall
 * @Date 2024/10/18  20:55
 * @description FloydWarshall 多源最短路径算法
 * @since jdk 11
 */
public class FloydWarshall {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, -2));
        v2.edges = List.of(new Edge(v1, 4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4, 2));
        v4.edges = List.of(new Edge(v2, -1));
        List<Vertex> graph = List.of(v1, v2, v3, v4);

        floydWarshall(graph);
    }

    private static void floydWarshall(List<Vertex> graph) {
        int size = graph.size();
        int[][] dist = new int[size][size];
        Vertex[][] prev = new Vertex[size][size];
        // 1) 初始化二维数组
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            for (int j = 0; j < size; j++) {
                Vertex u = graph.get(j);
                // 第一种情况，如果 v 和 u 是同一个顶点，那么距离是0
                if (v == u) {
                    dist[i][j] = 0;
                } else {
                    // 判断两个顶点是否连通，如果不连通则为∞，如果连通则为权重值
                    // 遍历顶点的整个 List 集合比较麻烦，可以使用 map 存储顶点之间的关系
                    dist[i][j] = map.getOrDefault(u, Integer.MAX_VALUE);
                    prev[i][j] = map.get(u) != null ? v : null;
                }
            }
        }

        // 2) 看能否借路到达其它顶点，需要经过四轮的借路处理，下面有详细步骤
        /*
            v2 -> v1         v1 -> v?
            dist[1][0]   +   dist[0][0]
            dist[1][0]   +   dist[0][1]
            dist[1][0]   +   dist[0][2]
            dist[1][0]   +   dist[0][3]
         */
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
//                    dist[i][k]   +   dist[k][j] // i行的顶点，借助 k 顶点，到达 j 列顶点
//                    dist[i][j] // 不借助顶点，直接从 i 顶点到 j 顶点
                    if (dist[i][k] != Integer.MAX_VALUE &&
                            dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[k][j];
                    }
                }
            }
            print(dist);
        }
        print(prev);
    }

    private static void print(int[][] dist) {
        System.out.println("--------------");
        for (int[] row : dist) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                    .map(s -> String.format("%2s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }
    private static void print(Vertex[][] prev) {
        System.out.println("----------------------");
        for (Vertex[] row : prev) {
            System.out.println(Arrays.stream(row)
                    .map(v -> v == null ? "null" : v.name)
                    .map(s -> String.format("%5s", s))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }
}
/*
    直接连通
    v1  v2  v3  v4
v1  0   ∞   -2  ∞
v2  4   0   3   ∞
v3  ∞   ∞   0   2
v4  ∞   -1  ∞   0

    k=0 借助v1到达其他顶点
    v1  v2  v3  v4
v1  0   ∞   -2  ∞
v2  4   0   2   ∞
v3  ∞   ∞   0   2
v4  ∞   -1  ∞   0

    k=1 借助v2到达其他顶点
    v1  v2  v3  v4
v1  0   ∞   -2  ∞
v2  4   0   2   ∞
v3  ∞   ∞   0   2
v4  3   -1  1   0

    k=2 借助v3到达其他顶点
    v1  v2  v3  v4
v1  0   ∞   -2  0
v2  4   0   2   4
v3  ∞   ∞   0   2
v4  3   -1  1   0

    k=3 借助v4到达其他顶点
    v1  v2  v3  v4
v1  0   ∞   -2  0
v2  4   0   2   4
v3  5   1   0   2
v4  3   -1  1   0

 */