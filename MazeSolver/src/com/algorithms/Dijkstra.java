package com.algorithms;

import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.dataStructures.Solution;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author kamen.petrov
 */
public class Dijkstra implements Algorithm {

    @Override
    public Solution solve(Maze maze) {

        Integer[] distance = new Integer[maze.getEnd().id + 1];
        Node[] prev = new Node[maze.getEnd().id + 1];
        boolean[] visited = new boolean[maze.getEnd().id + 1];

        int nodesExplored = 0;
        boolean completed = false;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> distance[n.id]));

        distance[maze.getStart().id] = 0;
        prev[maze.getStart().id] = null;
        pq.add(maze.getStart());

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            nodesExplored++;

            if (visited[node.id]) {
                continue;
            }
            visited[node.id] = true;

            for (Node neighbour : node.neighbours) {
                if (neighbour == null || visited[neighbour.id]) {
                    continue;
                }
                if (distance[neighbour.id] == null || distance[neighbour.id] > distance[node.id] + calcDistance(node, neighbour)) {
                    distance[neighbour.id] = distance[node.id] + calcDistance(node, neighbour);
                    prev[neighbour.id] = node;
                    pq.add(neighbour);
                }
            }
        }

        if (visited[maze.getEnd().id]) {
            completed = true;
        }

        Queue<Node> path = new ArrayDeque<>();
        Node currentNode = maze.getEnd();
        Node prevNode = maze.getEnd();
        int pathLength = 0;
        while (currentNode != null) {
            path.add(currentNode);

            pathLength += calcDistance(prevNode, currentNode);
            prevNode = currentNode;
            currentNode = prev[currentNode.id];
        }

        return new Solution(path, pathLength, nodesExplored, completed);
    }

    private int calcDistance(Node from, Node to) {
        return Math.abs(from.col - to.col) + Math.abs(from.row - to.row);
    }
}