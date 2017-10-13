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
        int nodesExplored = 0;
        boolean completed = false;

        Integer[] distance = new Integer[maze.getEnd().id + 1];
        boolean[] visited = new boolean[maze.getEnd().id + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> distance[n.id]));

        distance[maze.getStart().id] = 0;
        maze.getStart().parent = null;
        pq.add(maze.getStart());

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            nodesExplored++;

            if (visited[current.id]) {
                continue;
            }
            visited[current.id] = true;

            if (maze.getEnd().equals(current)) {
                break;
            }

            for (Node neighbour : current.neighbours) {
                if (neighbour == null || visited[neighbour.id]) {
                    continue;
                }
                if (distance[neighbour.id] == null || distance[neighbour.id] > distance[current.id] + calcDistance(current, neighbour)) {
                    distance[neighbour.id] = distance[current.id] + calcDistance(current, neighbour);
                    neighbour.parent = current;
                    pq.add(neighbour);
                }
            }
        }

        if (visited[maze.getEnd().id]) {
            completed = true;
        }

        Queue<Node> path = new ArrayDeque<>();
        Node currentNode = maze.getEnd();
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        return new Solution(path, distance[maze.getEnd().id], nodesExplored, completed);
    }

    private int calcDistance(Node from, Node to) {
        return Math.abs(from.col - to.col) + Math.abs(from.row - to.row);
    }
}
