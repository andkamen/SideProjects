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

        Node[] prev = new Node[maze.getCols() * maze.getRows()];
        boolean[] visited = new boolean[maze.getCols() * maze.getRows()];

        int count = 0;
        boolean completed = false;

        visited[start.row * width + start.col] = true;

        while (!queue.isEmpty()) {
            count++;
            current = queue.poll();

            if (current == end) {
                completed = true;
                break;
            }
            for (Node n : current.neighbours) {
                if (n != null) {
                    int npos = n.row * width + n.col;
                    if (!visited[npos]) {
                        queue.add(n);
                        visited[npos] = true;
                        prev[npos] = current;
                    }
                }
            }
        }

        Queue<Node> path = new ArrayDeque<>();
        current = end;
        while (current != null) {
            path.add(current);
            current = prev[current.row * width + current.col];
        }

        return new Solution(path, path.size(), count, completed);

    }
}
