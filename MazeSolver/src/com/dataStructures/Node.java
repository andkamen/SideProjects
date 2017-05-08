package com.dataStructures;

public class Node {
    public int row;
    public int col;
    public Node[] neighbours;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.neighbours = new Node[4];
    }
}
