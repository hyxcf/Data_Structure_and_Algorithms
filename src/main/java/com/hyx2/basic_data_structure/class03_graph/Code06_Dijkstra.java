package com.hyx2.basic_data_structure.class03_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author hyx
 * @version 0.1
 * @className Code06_Dijkstra
 * @date 2025/1/5 19:01
 * @description
 * @since jdk 11
 */
public class Code06_Dijkstra {

    // 方法1：时间复杂度 O(N)
    public static HashMap<Node, Integer> dijkstra1(Node from) {
        // 从head出发到所有点的最小距离
        // key : 从head出发到达key
        // value : 从head出发到达key的最小距离
        // 如果在表中，没有T的记录，含义是从head出发到T这个点的距离为正无穷
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 已经求过最小距离的节点，存在 selectedNodes 中，以后再也不碰
        HashSet<Node> selectedNodes = new HashSet<>();
        // 找到符合条件的权重最小的边中的节点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        // from 0
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!selectedNodes.contains(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * 遍历distanceMap找与最小距离且selectedNodes中没有的Node节点返回 不足之处：每次都要遍历 distanceMap 选择最小点
     *
     * @param distanceMap   distanceMap
     * @param selectedNodes selectedNodes
     * @return Node
     */
    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


    // 对方法1的改进 方法2：时间复杂度 O(logN)使用小根堆
    public static HashMap<Node, Integer> dijkstra2(Node from, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.AddOrUpdateOrIgnored(from, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.AddOrUpdateOrIgnored(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        private HashMap<Node, Integer> heapIndexMap; // key 某一个node，value 上面数组中的位置 作用：防止已记录最小的node节点再次参加记录
        private HashMap<Node, Integer> distanceMap;  // key 某一个节点，value 从源节点出发到该节点的目前最小距离
        private int size;// 堆上有多少个点

        public NodeHeap(int size) {
            this.nodes = new Node[size];
            this.heapIndexMap = new HashMap<>();
            this.distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean inHeap(Node node) {
            return heapIndexMap.containsKey(node) && heapIndexMap.get(node) != -1;
        }

        public boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private void AddOrUpdateOrIgnored(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
            // 剩下的就是 -1 加入黑名单中忽略的，已经当上最小节点的
        }

        // 在 pop 的时候，再次调整为小顶堆
        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            // 交换堆顶
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        public void insertHeapify(Node node, Integer index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 下沉 Actual value of parameter 'index' is always '0' 总是与堆顶元素进行交换
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
            smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;
            if (index == smallest) {
                return;
            }
            swap(smallest, index);
            index = smallest;
            left = index * 2 + 1;
        }

        private void swap(Integer index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node temp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = temp;
        }
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

}





















