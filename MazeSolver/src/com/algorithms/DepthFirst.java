package com.algorithms;

import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.dataStructures.Solution;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class DepthFirst implements Algorithm{

    @Override
    public Solution solve(Maze maze) {

        Node start = maze.getStart();
        Node end = maze.getEnd();
        Node current = null;

        int width = maze.getCols();

        Stack<Node> stack = new Stack<>();
        stack.add(start);

        Node[] prev = new Node[maze.getCols() * maze.getRows()];
        boolean[] visited = new boolean[maze.getCols() * maze.getRows()];

        int count = 0;
        boolean completed = false;

        visited[start.row * width + start.col] = true;

        while (!stack.isEmpty()) {
            count++;
            current = stack.pop();

            if (current == end) {
                completed = true;
                break;
            }
            for (Node n : current.neighbours) {
                if (n != null) {
                    int npos = n.row * width + n.col;
                    if (!visited[npos]) {
                        stack.push(n);
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

        Solution solution = new Solution(path,path.size(),count,completed);
        return solution;
    }
}
