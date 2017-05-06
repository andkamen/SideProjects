package com.solver;

import com.exceptions.InvalidImageFormat;
import com.utilities.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandlerImpl implements ImageHandler {

    private BufferedImage image;
    private int[][] grid;
    private int rows;
    private int cols;


    @Override
    public void parseImage(String name) throws IOException {
        loadImage(name);

        createGrid();

        placeNodes();

        for (int[] ints : grid) {
            for (int num : ints) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

    }

    @Override
    public void drawPath() {

    }


    private void loadImage(String name) throws IOException {
        this.image = ImageIO.read(new File(Constants.INPUT_FOLDER_PATH + name + Constants.FILE_SUFFIX_BMP));
        this.rows = this.image.getHeight();
        this.cols = this.image.getWidth();
    }

    private void createGrid() {

        this.grid = new int[this.rows][this.cols];

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                switch (this.image.getRGB(col, row)) {
                    case Constants.COLOR_BLACK:
                        this.grid[row][col] = Constants.WALL;
                        break;
                    case Constants.COLOR_WHITE:
                        this.grid[row][col] = Constants.PATH;
                        break;
                    default:
                        throw new InvalidImageFormat();
                }
            }
        }

//        for (int[] ints : grid) {
//            for (int num : ints) {
//                System.out.print(num+" ");
//            }
//            System.out.println();
//        }
    }

    private void placeNodes() {

        for (int col = 0; col < this.cols; col++) {
            if (this.grid[0][col] == Constants.PATH) {
                this.grid[0][col] = Constants.START;
            }
        }


        for (int row = 1; row < this.rows - 1; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (this.grid[row][col] == Constants.WALL) {
                    continue;
                }

                if (hasHorizontalPath(row, col) && hasVerticalPath(row, col)) {
                    this.grid[row][col] = Constants.NODE;
                }
            }
        }

        for (int col = 0; col < this.cols; col++) {
            if (this.grid[this.rows - 1][col] == Constants.PATH) {
                this.grid[this.rows - 1][col] = Constants.END;
            }
        }


    }

    private boolean hasHorizontalPath(int row, int col) {
        if (col - 1 >= 0) {
            if (this.grid[row][col - 1] != Constants.WALL) {
                return true;
            }
        }
        if (col + 1 < this.cols) {
            if (this.grid[row][col + 1] != Constants.WALL) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVerticalPath(int row, int col) {
        if (row - 1 >= 0) {
            if (this.grid[row - 1][col] != Constants.WALL) {
                return true;
            }
        }
        if (row + 1 < this.rows) {
            if (this.grid[row + 1][col] != Constants.WALL) {
                return true;
            }
        }

        return false;
    }
}
