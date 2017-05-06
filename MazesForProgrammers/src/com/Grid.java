package com;

public class Grid {

    private static final String CORNER = "+";
    private static final String VERTICAL_WALL = "|";
    private static final String HORIZONTAL_WALL = "---";
    private static final String BODY = "   ";
    private static final String SPACE = " ";

    private int rows;
    private int cols;

    private Cell[][] grid;

    public Grid(int rows, int cols) {
        this.setRows(rows);
        this.setCols(cols);
        this.setGrid(new Cell[rows][cols]);

        initializeGrid();
    }

    public int size() {
        return this.cols * this.rows;
    }

    private void initializeGrid() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                grid[row][col] = new Cell(row, col);
            }
        }

        configureCells();
    }

    private void configureCells() {
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                Cell currentCell = grid[row][col];
                if (row > 0) {
                    currentCell.setNorth(grid[row - 1][col]);
                }
                if (row < this.rows - 1) {
                    currentCell.setSouth(grid[row + 1][col]);
                }
                if (col > 0) {
                    currentCell.setWest(grid[row][col - 1]);
                }
                if (col < this.cols - 1) {
                    currentCell.setEast(grid[row][col + 1]);
                }
            }
        }
    }


    public int getRows() {
        return rows;
    }

    private void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    private void setCols(int cols) {
        this.cols = cols;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(CORNER);
        output.append(createStr(HORIZONTAL_WALL + CORNER, this.cols));
        output.append(System.lineSeparator());

        for (Cell[] cells : grid) {
            output.append(VERTICAL_WALL);
            for (Cell cell : cells) {
                output.append(BODY);
                output.append(cell.isLinked(cell.getEast()) ? SPACE : VERTICAL_WALL);
            }
            output.append(System.lineSeparator());
            for (Cell cell : cells) {
                output.append(CORNER);
                output.append(cell.isLinked(cell.getSouth()) ? BODY : HORIZONTAL_WALL);
            }
            output.append(CORNER);
            output.append(System.lineSeparator());
        }

        return output.toString();
    }


    private String createStr(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}
