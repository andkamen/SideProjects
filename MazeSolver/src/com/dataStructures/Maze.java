package com.dataStructures;

import lombok.Data;

@Data
public class Maze {

    private int rows;
    private int cols;
    private int nodeCount;

    private Node start;
    private Node end;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public Maze(int rows, int cols, int nodeCount) {
        this(rows, cols);

        this.nodeCount = nodeCount;
    }
}