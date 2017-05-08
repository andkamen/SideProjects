package com.dataStructures;

public class Maze {

    private int rows;
    private int cols;
    private int count;

    private Node start;
    private Node end;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public Maze(int rows, int cols, int count) {
        this(rows, cols);

        this.count = count;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }
}