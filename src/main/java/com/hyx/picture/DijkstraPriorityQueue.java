package com.hyx.picture;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 迪克斯特拉算法 改进 - 优先级队列

    1. 创建一个优先级队列，放入所有顶点（队列大小会达到边的数量）
    2. 为每个顶点分配一个临时距离值
       * 对于我们的初始顶点，将其设置为零
       * 对于所有其他顶点，将其设置为无穷大。
    3. 每次选择最小临时距离的未访问顶点，作为新的当前顶点
    4. 对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小，若距离更新需加入队列
       * 例如，1->6 的距离是 14，而1->3->6 的距离是11。这时将距离更新为 11
       * 否则，将保留上次距离值
    5. 当前顶点的邻居处理完成后，把它从队列中删除
 */

public class DijkstraPriorityQueue {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        dijkstra(graph, v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        // java 中的优先级队列默认是小顶堆
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist)); // 使用优先级队列代替 chooseMinDistVertex() 方法
        source.dist = 0;
        for (Vertex v : graph) {
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
            System.out.println(queue);
            // 3. 选取当前顶点
            Vertex curr = queue.peek(); // 顶部就是优先级最高的
            // 4. 更新当前顶点邻居距离
            if(!curr.visited) { // 如果 curr 没有被遍历
                updateNeighboursDist(curr, queue); // 修改 curr 邻居的权重，
                curr.visited = true;
            }
            // 5. 移除当前顶点
            queue.poll();
        }

        for (Vertex v : graph) {
            System.out.println(v.name + " " + v.dist + " " + (v.prev != null ? v.prev.name : "null"));
        }
    }

    private static void updateNeighboursDist(Vertex curr, PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            // 找到 curr 的邻居
            Vertex n = edge.linked;
            // 如果邻居没有被遍历过
            if (!n.visited) {
                // 算出距离 + curr的权重 与 邻居自身的权重进行比较，如果小于邻居自身的权重则替换。
                int dist = curr.dist + edge.weight;
                if (dist < n.dist) {
                    n.dist = dist;
                    n.prev = curr; // 记录上一步的位置
                    queue.offer(n);
                }
            }
        }
    }

}