package com.hyx2.basic_data_structure.class03_graph;

import java.util.*;

/**
 * @author hyx
 * @version 0.1
 * @className Code04_Kruskal
 * @date 2025/1/2 21:54
 * @description 只适用于有向图，无向图会出现少一侧的情况 Kruskal算法：从边的角度出发，使用并查集的技巧
 * @since jdk 11
 */
// undirected graph only
public class Code04_Kruskal {

    // Union-Find Set
    public static class UnionFind {
        public HashMap<Node, Node> fatherMap;
        public HashMap<Node, Integer> sizeMap;


        public UnionFind(HashMap<Node, Node> fatherMap, HashMap<Node, Integer> sizeMap) {
            this.fatherMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public UnionFind() {
        }

        // 初始化并查集
        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node n) {
            Stack<Node> stack = new Stack<>();
            while (n != fatherMap.get(n)) {
                stack.push(n);
                n = fatherMap.get(n);
            }
            // 此时的n就是father,进行扁平化
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            if (!fatherMap.containsKey(a) || !fatherMap.containsKey(b)) {
                return false;
            }
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            if (!fatherMap.containsKey(a) || !fatherMap.containsKey(b)) {
                return;
            }
            Node aHead = findFather(a);
            Node bHead = findFather(b);
            // 小集合 跟 大集合
            if (aHead != bHead) {
                if (sizeMap.get(a) > sizeMap.get(b)) {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, sizeMap.get(a) + sizeMap.get(b));
                    sizeMap.remove(bHead);
                } else {
                    fatherMap.put(aHead, bHead);
                    sizeMap.put(bHead, sizeMap.get(a) + sizeMap.get(b));
                    sizeMap.remove(aHead);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> KruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }


}
