package com.utilities;

public enum GameSize {

    BEGINNER(9, 9),
    INTERMEDIATE(16, 16),
    EXPERT(16, 30);

    private int row;
    private int col;

    GameSize(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public GameSize valueOF(String gameSize) {
        switch (gameSize.toUpperCase()) {
            case "BEGINNER":
                return BEGINNER;
            case "INTERMEDIATE":
                return INTERMEDIATE;
            case "EXPERT":
                return EXPERT;
            default:
                throw new IllegalArgumentException(String.format("%s is not a valid game size!", gameSize));
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
