package com.Olympiad2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D_Flood {

    private static class Edge implements Comparable<Edge> {

        private int startNode;
        private int endNode;
        private int weight;
        private boolean isFlooded;

        public Edge(int startNode, int endNode, int weight) {
            this.startNode = startNode;
            this.endNode = endNode;
            this.weight = weight;
            this.isFlooded = false;
        }

        public boolean isFlooded() {
            return isFlooded;
        }

        public void setFlooded(boolean flooded) {
            isFlooded = flooded;
        }

        public int getStartNode() {
            return startNode;
        }

        public void setStartNode(int startNode) {
            this.startNode = startNode;
        }

        public int getEndNode() {
            return endNode;
        }

        public void setEndNode(int endNode) {
            this.endNode = endNode;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("{%d, %d} -> %d", this.startNode, this.endNode, this.weight);
        }

        @Override
        public int compareTo(Edge other) {

            if (!this.isFlooded && other.isFlooded) {
                return -1;
            } else if (this.isFlooded && !other.isFlooded) {
                return 1;
            } else {
                return Integer.compare(this.weight, other.weight);

            }
        }
    }


    private static List<Edge> kruskal(int verticesCount, List<Edge> edges) {

        edges.sort(Edge::compareTo);
        int[] parent = new int[verticesCount];
        for (int i = 0; i < verticesCount; i++) {
            parent[i] = i;
        }

        List<Edge> spanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int rootStartNode = findRoot(edge.startNode, parent);
            int rootEndNode = findRoot(edge.endNode, parent);
            if (rootStartNode != rootEndNode) {
                spanningTree.add(edge);
                parent[rootStartNode] = rootEndNode;
            }
        }

        return spanningTree;
    }

    private static int findRoot(int node, int[] parent) {
        int root = node;
        while (parent[root] != root) {
            root = parent[root];
        }

        while (node != root) {
            int oldParent = parent[node];
            parent[node] = root;
            node = oldParent;
        }

        return root;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        for (int i = 0; i < count; i++) {
            int[] townInfo = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int nodeCount = townInfo[0];
            int allStreetsCount = townInfo[1];
            int floodedStreetsCount = townInfo[2];

            List<Edge> edges = new ArrayList<>();

            for (int j = 0; j < allStreetsCount; j++) {
                int[] streetInfo = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Edge edge = new Edge(streetInfo[0], streetInfo[1], streetInfo[2]);

                edges.add(edge);
            }
            int[] floodedStreets = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int floodedStreet : floodedStreets) {
                edges.get(floodedStreet - 1).setFlooded(true);
            }

            int result = 0;
            List<Edge> spanningTree = kruskal(nodeCount, edges);
            for (Edge edge : spanningTree) {
                if (edge.isFlooded) {
                    result += edge.getWeight();
                }
            }
            System.out.println(result);
        }
    }
}
