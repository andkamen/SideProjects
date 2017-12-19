package com.algorithms;

import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.dataStructures.Solution;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * g_cost -> distance from start to node
 * h_cost -> heuristic, distance from node to goal
 * f_cost -> g + h
 * <p>
 * open -> currently considered nodes. Priority queue, orders nodes by f, then by h
 * closed -> visited nodes
 */
public class AStar implements Algorithm {

    @Override
    public Solution solve(Maze maze) {
        int nodesExplored = 0;
        boolean isCompleted = false;
        Node current = null;

        Integer[] gCost = new Integer[maze.getEnd().id + 1];
        Integer[] hCost = new Integer[maze.getEnd().id + 1];

        //gCost can serve as a visited nodes check. If value is != null then it has been visited
        //Set<Node> closed = new HashSet<>();

        PriorityQueue<Node> open = new PriorityQueue<>(
                (n1, n2) -> {
                    int result = Integer.compare(gCost[n1.id] + hCost[n1.id], gCost[n2.id] + hCost[n2.id]);
                    //tiebreaker works well for more open grids. Doesn't do much/ makes more nodes get explored for dense mazes
//                    if (result == 0) {
//                        result = Integer.compare(hCost[n1.id], hCost[n2.id]);
//                    }
                    return result;
                });

        open.add(maze.getStart());
        gCost[maze.getStart().id] = 0;
        hCost[maze.getStart().id] = calcHCost(maze.getStart(), maze.getEnd());


        while (!open.isEmpty()) {
            current = open.poll();
            //closed.add(current);
            nodesExplored++;

            if (current.equals(maze.getEnd())) {
                break;
            }

            for (Node neighbour : current.neighbours) {
                if (neighbour == null) {
                    continue;
                }

                int newGCost = gCost[current.id] + calcDistance(current, neighbour);

                if (gCost[neighbour.id] == null || newGCost < gCost[neighbour.id]) {
                    gCost[neighbour.id] = newGCost;
                    hCost[neighbour.id] = calcHCost(neighbour, maze.getEnd());
                    neighbour.parent = current;

                    //membership is O(n). Faster to add the same node twice than to do the check each time.
                    // if (!open.contains(neighbour)) {
                    open.add(neighbour);
                    // }
                }
            }
        }


        // if (closed.contains(maze.getEnd())) {
        if (gCost[maze.getEnd().id] != null) {
            isCompleted = true;
        }

        Queue<Node> path = new ArrayDeque<>();
        Node currentNode = maze.getEnd();
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }

        return new Solution(path, gCost[maze.getEnd().id], nodesExplored, isCompleted);
    }

    private int calcDistance(Node from, Node to) {
        return Math.abs(from.col - to.col) + Math.abs(from.row - to.row);
    }

    private int calcHCost(Node node, Node goal) {
        return (Math.abs(node.col - goal.col) + Math.abs(node.row - goal.row));
    }
}
