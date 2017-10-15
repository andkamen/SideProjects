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
        int nodesExplored = 0;
        boolean isCompleted = false;
        Node current = null;

        Stack<Node> stack = new Stack<>();
        stack.add(maze.getStart());

        boolean[] visited = new boolean[maze.getEnd().id + 1];


        visited[maze.getStart().id] = true;

        while (!stack.isEmpty()) {
            nodesExplored++;
            current = stack.pop();

            if (current.equals(maze.getEnd())) {
                isCompleted = true;
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

        return new Solution(path, pathLength, nodesExplored, isCompleted);
    }

    private int calcDistance(Node from, Node to) {
        return Math.abs(from.col - to.col) + Math.abs(from.row - to.row);
    }
}
