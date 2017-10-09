package com.dataStructures;

public class Node {
    public int id;
    public int row;
    public int col;
    public Node[] neighbours;

    public Node(int row, int col,int id) {
        this.row = row;
        this.col = col;
        this.id = id;
        this.neighbours = new Node[4];
    }
}
