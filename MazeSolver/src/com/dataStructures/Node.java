package com.dataStructures;

public class Node {
    public int id;
    public int row;
    public int col;
    public Node[] neighbours;
    public Node parent;

    public Node(int row, int col, int id) {
        this.row = row;
        this.col = col;
        this.id = id;
        this.neighbours = new Node[4];
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Node.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Node other = (Node) obj;
        return (this.row == other.row) && (this.col == other.col) && (this.id == other.id);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + id;
        result = 31 * result + row;
        result = 31 * result + col;

        return result;
    }
}
