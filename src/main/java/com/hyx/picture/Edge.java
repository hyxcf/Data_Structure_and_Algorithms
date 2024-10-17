package com.hyx.picture;

/**
 * @version 0.1
 * @Author hyx
 * @className Edge
 * @Date 2024/10/17  14:44
 * @description 边
 * @since jdk 11
 */
public class Edge {

    Vertex linked;
    int weight;

    public Edge(Vertex linked) {
        this.linked = linked;
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }

}
