package com;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private int row;
    private int col;
    private Cell north;
    private Cell south;
    private Cell east;
    private Cell west;

    private Set<Cell> links;

    public Cell(int row, int col) {
        this.setRow(row);
        this.setCol(col);

        this.links = new HashSet<>();
    }


    public void link(Cell cell) {
        this.link(cell, true);
    }

    //TODO maybe make public
    private void link(Cell cell, Boolean bidirectional) {
        this.links.add(cell);
        if (bidirectional) {
            cell.link(this, false);
        }
    }

    public void unlink(Cell cell) {
        this.unlink(cell, true);
    }

    private void unlink(Cell cell, Boolean bidirectional) {
        this.links.remove(cell);
        if (bidirectional) {
            cell.unlink(this, false);
        }
    }

    public Set<Cell> getNeighbours() {
        Set<Cell> neighbours = new HashSet<>();
        if (this.north != null) {
            neighbours.add(north);
        }
        if (this.south != null) {
            neighbours.add(south);
        }
        if (this.east != null) {
            neighbours.add(east);
        }
        if (this.west != null) {
            neighbours.add(west);
        }

        return neighbours;
    }

    public boolean isLinked(Cell cell) {
        return this.links.contains(cell);
    }


    public int getRow() {
        return row;
    }

    private void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    private void setCol(int col) {
        this.col = col;
    }

    public Cell getNorth() {
        return north;
    }

    public void setNorth(Cell north) {
        this.north = north;
    }

    public Cell getSouth() {
        return south;
    }

    public void setSouth(Cell south) {
        this.south = south;
    }

    public Cell getEast() {
        return east;
    }

    public void setEast(Cell east) {
        this.east = east;
    }

    public Cell getWest() {
        return west;
    }

    public void setWest(Cell west) {
        this.west = west;
    }

    public Set<Cell> getLinks() {
        return links;
    }

    public void setLinks(Set<Cell> links) {
        this.links = links;
    }


}
