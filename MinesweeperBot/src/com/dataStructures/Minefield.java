package com.dataStructures;

import com.utilities.Constants;
import com.utilities.GameSize;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Data
public class Minefield {

    private Rectangle gameGrid;
    private Rectangle faceSquare;

    private GameSize gameSize;
    private Cell[][] minefield;

    public Minefield(GameSize gameSize, Position gameCorner) {
        this.gameSize = gameSize;
        initRectangles(gameCorner);
        initMinefield();
    }


    public java.util.List<Cell> getCellList() {
        java.util.List<Cell> cells = new ArrayList<>();
        for (Cell[] array : minefield) {
            cells.addAll(Arrays.asList(array));
        }
        //Dont return cells that don't have any useful information. (Their neighbours are solved)
        return cells.stream().filter(cell -> !cell.isSolvedNeighbours()).collect(Collectors.toList());
    }

    public Cell getCell(int row, int col) {
        return minefield[row][col];
    }

    private void initMinefield() {
        minefield = new Cell[gameSize.getRow()][gameSize.getCol()];

        for (int row = 0; row < gameSize.getRow(); row++) {
            for (int col = 0; col < gameSize.getCol(); col++) {
                minefield[row][col] = new Cell(row, col, gameGrid);
            }
        }

        for (int row = 0; row < gameSize.getRow(); row++) {
            for (int col = 0; col < gameSize.getCol(); col++) {
                //up left
                if (isValidCell(row - 1, col - 1)) {
                    minefield[row - 1][col - 1].getNeighbours().add(minefield[row][col]);
                }
                //up
                if (isValidCell(row, col - 1)) {
                    minefield[row][col - 1].getNeighbours().add(minefield[row][col]);
                }
                //up right
                if (isValidCell(row + 1, col - 1)) {
                    minefield[row + 1][col - 1].getNeighbours().add(minefield[row][col]);
                }
                //left
                if (isValidCell(row - 1, col)) {
                    minefield[row - 1][col].getNeighbours().add(minefield[row][col]);
                }
                //right
                if (isValidCell(row + 1, col)) {
                    minefield[row + 1][col].getNeighbours().add(minefield[row][col]);
                }
                //down left
                if (isValidCell(row - 1, col + 1)) {
                    minefield[row - 1][col + 1].getNeighbours().add(minefield[row][col]);
                }
                //down
                if (isValidCell(row, col + 1)) {
                    minefield[row][col + 1].getNeighbours().add(minefield[row][col]);
                }
                //down right
                if (isValidCell(row + 1, col + 1)) {
                    minefield[row + 1][col + 1].getNeighbours().add(minefield[row][col]);
                }
            }
        }
    }

    private void initRectangles(Position gameCorner) {
        gameGrid = new Rectangle(gameCorner.getCol() + Constants.MINEFIELD_RIGHT_OFFSET,
                gameCorner.getRow() + Constants.MINEFIELD_TOP_OFFSET,
                gameSize.getCol() * Constants.CELL_WIDTH,
                gameSize.getRow() * Constants.CELL_HEIGHT);

        switch (gameSize) {
            case BEGINNER:
                faceSquare = new Rectangle(gameCorner.getCol() + Constants.FACE_RIGHT_OFFSET_BEGINNER,
                        gameCorner.getRow() + Constants.FACE_TOP_OFFSET,
                        Constants.FACE_WIDTH,
                        Constants.FACE_HEIGHT);
                break;
            case INTERMEDIATE:
                faceSquare = new Rectangle(gameCorner.getCol() + Constants.FACE_RIGHT_OFFSET_INTERMEDIATE,
                        gameCorner.getRow() + Constants.FACE_TOP_OFFSET,
                        Constants.FACE_WIDTH,
                        Constants.FACE_HEIGHT);
                break;
            case EXPERT:
                faceSquare = new Rectangle(gameCorner.getCol() + Constants.FACE_RIGHT_OFFSET_EXPERT,
                        gameCorner.getRow() + Constants.FACE_TOP_OFFSET,
                        Constants.FACE_WIDTH,
                        Constants.FACE_HEIGHT);
                break;
        }
    }

    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < gameSize.getRow()
                && col >= 0 && col < gameSize.getCol();
    }
}