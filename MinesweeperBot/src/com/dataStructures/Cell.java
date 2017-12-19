package com.dataStructures;

import com.utilities.CellState;
import com.utilities.Constants;
import lombok.Data;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Cell {
    private int row;
    private int col;
    private int y;
    private int x;
    private boolean isClicked;
    private boolean solvedNeighbours;
    private CellState cellState;
    private Set<Cell> neighbours;

    public Cell(int row, int col, Rectangle gameGrid) {
        this.row = row;
        this.col = col;
        y = (int) gameGrid.getY() + Constants.CELL_HEIGHT * row + Constants.CELL_HEIGHT / 2;//points to middle of the cell
        x = (int) gameGrid.getX() + Constants.CELL_WIDTH * col + Constants.CELL_WIDTH / 2;
        neighbours = new HashSet<>();
        cellState = CellState.UNOPENED;
    }


    public int getFlaggedNeighboursCount() {
        return (int) neighbours.stream().filter(cell -> CellState.BOMB.equals(cell.getCellState())).count();
    }

    public int getUnopenedNeighboursCount() {
        return (int) neighbours.stream()
                .filter(cell -> CellState.UNOPENED.equals(cell.getCellState())
                        && !cell.isClicked()
                )
                .count();
    }

    public List<Cell> getUnopenedNeighbours() {
        return neighbours.stream()
                .filter(cell -> CellState.UNOPENED.equals(cell.getCellState())
                        && !cell.isClicked())
                .collect(Collectors.toList());

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Cell.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Cell other = (Cell) obj;
        return (row == other.row) && (col == other.col);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + row;
        result = 31 * result + col;
        return result;
    }
}
