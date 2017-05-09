package com.dataStructures;


import java.util.Queue;

public class Solution {

    private Queue<Node> path;
    private int pathLength;
    private int nodesExplored;
    private boolean completed;

    public Solution(Queue<Node> path, int pathLength, int nodesExplored, boolean completed) {
        this.path = path;
        this.pathLength = pathLength;
        this.nodesExplored = nodesExplored;
        this.completed = completed;
    }

    public Queue<Node> getPath() {
        return path;
    }

    public int getPathLength() {
        return pathLength;
    }

    public int getNodesExplored() {
        return nodesExplored;
    }

    public boolean isCompleted() {
        return completed;
    }

}
