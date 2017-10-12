package com.algorithms;

import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.dataStructures.Solution;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirst implements Algorithm {

    @Override
    public Solution solve(Maze maze) {

        Node start = maze.getStart();
        Node end = maze.getEnd();
        Node current = null;

        int width = maze.getCols();

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);

        //TODO nodes now have ID's. remove n*2 length arrays
        Node[] prev = new Node[maze.getEnd().id + 1];
        boolean[] visited = new boolean[maze.getEnd().id + 1];

        int nodesExplored = 0;
        boolean completed = false;

        visited[start.id] = true;

        while (!queue.isEmpty()) {
            nodesExplored++;
            current = queue.poll();

            if (current == end) {
                completed = true;
                break;
            }
            for (Node n : current.neighbours) {
                if (n != null) {
                    if (!visited[n.id]) {
                        queue.add(n);
                        visited[n.id] = true;
                        prev[n.id] = current;
                    }
                }
            }
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