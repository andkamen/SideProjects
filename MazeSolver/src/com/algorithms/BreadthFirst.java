package com.algorithms;

import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.dataStructures.Solution;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirst implements Algorithm {

    @Override
    public Solution solve(Maze maze) {
        int nodesExplored = 0;
        boolean isCompleted = false;
        Node current = null;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(maze.getStart());

        boolean[] visited = new boolean[maze.getEnd().id + 1];


        visited[maze.getStart().id] = true;

        while (!queue.isEmpty()) {
            nodesExplored++;
            current = queue.poll();

            if (current.equals(maze.getEnd())) {
                isCompleted = true;
                break;
            }
            for (Node n : current.neighbours) {
                if (n != null) {
                    if (!visited[n.id]) {
                        queue.add(n);
                        visited[n.id] = true;
                        n.parent = current;
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
            currentNode = currentNode.parent;
        }

        return new Solution(path, pathLength, nodesExplored, isCompleted);
    }

    private int calcDistance(Node from, Node to) {
        return Math.abs(from.col - to.col) + Math.abs(from.row - to.row);
    }
}