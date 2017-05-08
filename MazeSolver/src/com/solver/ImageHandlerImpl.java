package com.solver;

import com.dataStructures.Maze;
import com.dataStructures.Node;
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
    private int nodeCount;

    private Maze maze;


    @Override
    public Maze parseImage(String name) throws IOException {
        loadImage(name);

        createGrid();
        markNodeLocations();
        connectNodes();

        return this.maze;


//        for (int[] ints : grid) {
//            for (int num : ints) {
//                System.out.print(num + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
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
    }

    private void markNodeLocations() {
        this.nodeCount = 0;
        for (int col = 1; col < this.cols - 1; col++) {
            if (this.grid[0][col] == Constants.PATH) {
                this.grid[0][col] = Constants.START;
                this.nodeCount++;
            }
        }

        for (int row = 1; row < this.rows - 1; row++) {
            for (int col = 1; col < this.cols - 1; col++) {
                if (this.grid[row][col] == Constants.WALL) {
                    continue;
                }
                //TODO ignores last node of a dead end.
                if (hasHorizontalPath(row, col) && hasVerticalPath(row, col)) {
                    this.grid[row][col] = Constants.NODE;
                    this.nodeCount++;
                }
            }
        }

        for (int col = 1; col < this.cols - 1; col++) {
            if (this.grid[this.rows - 1][col] == Constants.PATH) {
                this.grid[this.rows - 1][col] = Constants.END;
                this.nodeCount++;
            }
        }
    }

    private void connectNodes() {

        this.maze = new Maze(this.rows, this.cols, this.nodeCount);

        Node start = null;
        Node end = null;
        Node leftNode = null;
        Node topNode = null;
        Node curNode = null;

        //top row buffer
        Node[] topNodes = new Node[this.cols];

        //Initialize start node
        for (int col = 1; col < this.cols - 1; col++) {
            if (this.grid[0][col] == Constants.START) {
                start = new Node(0, col);
                topNodes[col] = start;
            }
        }
        this.maze.setStart(start);

        for (int row = 1; row < this.rows - 1; row++) {
            for (int col = 1; col < this.cols - 1; col++) {

                //If on a wall:
                // forget left node -> no node to the right will care about unreachable left node
                // forget topNode[] on this index-> no node below will care about unreachable top node
                if (this.grid[row][col] == Constants.WALL) {
                    leftNode = null;
                    topNodes[col] = null;
                    continue;
                }

                //If on node, try to connect currentNode to leftNode if exists and topNode if exists
                if (this.grid[row][col] == Constants.NODE) {
                    curNode = new Node(row, col);
                    if (leftNode != null) {
                        leftNode.neighbours[Constants.RIGHT] = curNode;
                        curNode.neighbours[Constants.LEFT] = leftNode;
                    }
                    leftNode = curNode;
                }

                //If on node, try to connect currentNode to TopNode if exists
                if (this.grid[row][col] == Constants.NODE) {
                    topNode = topNodes[col];
                    if (topNode != null) {
                        curNode.neighbours[Constants.UP] = topNode;
                        topNode.neighbours[Constants.DOWN] = curNode;
                    }

                    //If clear below, put this new node in the top row for the next connection
                    if (this.grid[row][col] != Constants.WALL) {
                        topNodes[col] = curNode;
                    }
                }
            }
        }

        //Initialize end node
        for (int col = 1; col < this.cols - 1; col++) {
            if (this.grid[this.rows - 1][col] == Constants.END) {
                end = new Node(this.rows - 1, col);

                topNode = topNodes[col];
                if (topNode != null) {
                    end.neighbours[Constants.UP] = topNode;
                    topNode.neighbours[Constants.DOWN] = end;
                }
            }
        }
        this.maze.setEnd(end);
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
