package com;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
//TODO: blah, everything is static
public class TerrainGenerator {
    public static class Tuple<X, Y> {
        private final X x;
        private final Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof Tuple)) {
                return false;
            }

            Tuple<X, Y> other_ = (Tuple<X, Y>) other;

            // this may cause NPE if nulls are valid values for x or y. The logic may be improved to handle nulls properly, if needed.
            return other_.x.equals(this.x) && other_.y.equals(this.y);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((x == null) ? 0 : x.hashCode());
            result = prime * result + ((y == null) ? 0 : y.hashCode());
            return result;
        }
    }

    private static final Random rng = new Random();
    private static int width = 20;
    private static int height = 20;
    private static int playerStartX;
    private static int playerStartY;

    private static final Integer[] allObjects = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 4};
    private static final Integer[] impassableObj = {3, 5};
    private static final Integer[] destructibleObj = {4};
    private static final Integer[] passableObj = {0, 1, 2}; //0 - ground ; 1 - Start; 2- Exit

    private static Integer[][] grid;
    private static Integer[][] gridTraced;
    private static HashSet<Tuple<Integer, Integer>> passableArea = new HashSet<>();

    public static int getPlayerStartX() {
        return playerStartX;
    }

    public static void setPlayerStartX(int startX) {
        playerStartX = startX;
    }

    public static int getPlayerStartY() {
        return playerStartY;
    }

    public static void setPlayerStartY(int startY) {
        playerStartY = startY;
    }

    public static String[] generateNewLevel() {
        generateGrid();
        while (!findConnectedAreas()) {
            generateGrid();
        }
        printLabyrinth();
        System.out.println("----------------------------------------------------------");
        printTracedLabyrinth();

        return generateLevelData();
    }

    public static Integer[] getPassableObj() {
        return passableObj;
    }

    public static HashSet<Tuple<Integer, Integer>> getPassableArea() {
        return passableArea;
    }

    private static Integer[][] copyGrid(Integer[][] grid) {
        Integer[][] copiedGrid = new Integer[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                copiedGrid[i][j] = grid[i][j];
            }
        }
        return copiedGrid;
    }

    private static boolean findConnectedAreas() {
        int exitRow = 0;
        int exitCol = 0;

        for (int row = 0; row < gridTraced.length; row++) {
            for (int col = 0; col < gridTraced[0].length; col++) {
                //Finds the area that is connected to the start cell
                if (gridTraced[row][col] == 1) {
                    setPlayerStartX(col);
                    setPlayerStartY(row);
                    tryDirection(row, col - 1, 'L');
                    tryDirection(row - 1, col, 'U');
                    tryDirection(row, col + 1, 'R');
                    tryDirection(row + 1, col, 'D');
                }
                if (grid[row][col] == 2) {
                    exitCol = col;
                    exitRow = row;
                }
            }
        }

        return passableArea.contains(new Tuple<>(exitRow, exitCol));
    }

    private static void tryDirection(int row, int col, char direction) {
        if (!inRange(row, col)) {
            return;
        }
        //TODO check if block is destructable
        if (Arrays.asList(passableObj).contains(gridTraced[row][col]) ||
                Arrays.asList(destructibleObj).contains(gridTraced[row][col])) {
            passableArea.add(new Tuple<>(row, col));

            //-1 is any number not a passable or impassable object so as not to cause
            // stack overflow or to terminate the search prematurely
            gridTraced[row][col] = -1;

            tryDirection(row, col - 1, 'L'); // left
            tryDirection(row - 1, col, 'U'); // up
            tryDirection(row, col + 1, 'R'); // right
            tryDirection(row + 1, col, 'D'); // down
        }
    }

    private static boolean inRange(int row, int col) {
        boolean rowInRange = row >= 0 && row < gridTraced.length;
        boolean colInRange = col >= 0 && col < gridTraced[0].length;

        return rowInRange && colInRange;
    }

    private static void printLabyrinth() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                System.out.printf("%d ", grid[row][col]);
            }
            System.out.println();
        }
    }

    private static void printTracedLabyrinth() {
        for (int row = 0; row < gridTraced.length; row++) {
            for (int col = 0; col < gridTraced[0].length; col++) {
                System.out.printf("%d ", gridTraced[row][col]);
            }
            System.out.println();
        }
    }

    private static void generateGrid() {
        grid = new Integer[height][width];

        for (int row = 1; row < grid.length - 1; row++) {
            for (int col = 1; col < grid[0].length - 1; col++) {
                grid[row][col] = allObjects[rng.nextInt(allObjects.length)];
            }
        }

        for (int col = 0; col < grid[0].length; col++) {
            grid[0][col] = 3;
            grid[height - 1][col] = 3;
        }
        for (int row = 0; row < grid.length; row++) {
            grid[row][0] = 3;
            grid[row][width - 1] = 3;
        }

        //place entry and exit point
        grid[1][1] = 1;
        grid[height / 2][width - 2] = 2;

        passableArea.clear();
        gridTraced = copyGrid(grid);
    }

    private static String[] generateLevelData() {
        String[] level = new String[height];

        for (int i = 0; i < height; i++) {
            StringBuilder line = new StringBuilder();
            for (Integer num : grid[i]) {
                line.append(num.toString());
            }
            level[i] = line.toString();
        }
        return level;
    }

}
