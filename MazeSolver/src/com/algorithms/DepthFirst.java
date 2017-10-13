package com.algorithms;

import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.dataStructures.Solution;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class DepthFirst implements Algorithm {

    @Override
    public Solution solve(Maze maze) {

        Node start = maze.getStart();
        Node end = maze.getEnd();
        Node current = null;

        Stack<Node> stack = new Stack<>();
        stack.add(start);

        boolean[] visited = new boolean[maze.getEnd().id + 1];

        int nodesExplored = 0;
        boolean completed = false;

        visited[start.id] = true;

        while (!stack.isEmpty()) {
            nodesExplored++;
            current = stack.pop();

            if (current == end) {
                completed = true;
                break;
            }
            for (Node n : current.neighbours) {
                if (n != null) {
                    if (!visited[n.id]) {
                        stack.push(n);
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

        return new Solution(path, pathLength, nodesExplored, completed);
    }

    private int calcDistance(Node from, Node to) {
        return Math.abs(from.col - to.col) + Math.abs(from.row - to.row);
    }
}
