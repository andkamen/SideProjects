package com.io;

import com.dataStructures.Cell;
import com.dataStructures.Minefield;
import com.dataStructures.Position;
import com.io.contracts.ImageHandler;
import com.utilities.CellState;
import com.utilities.Constants;
import com.utilities.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHandlerImpl implements ImageHandler {

    private Robot robot;

    public ImageHandlerImpl(Robot robot) {
        this.robot = robot;
    }

    @Override
    public Position findCorner() {
        BufferedImage screen = robot.createScreenCapture(new Rectangle(300, 300));

        int x = 0;
        //Go through the white pixels from left to right till a the border of the game is found
        for (int i = 0; i < 100; i++) {
            if (screen.getRGB(i, 200) == Constants.COLOUR_LIGHT_GREY) {
                x = i;
                break;
            }
        }
        int y = 0;
        //Go up along the border till a white pixel is found (Out of game window)
        for (int j = 200; j >= 0; j--) {
            if (screen.getRGB(x, j) == Constants.COLOUR_WHITE) {
                y = j + 1;
                break;
            }
        }
        return new Position(y, x);
    }

    @Override
    public GameState updateGameState(Minefield minefield) throws InterruptedException {
        Thread.sleep(100); //Allows time for game to update
        BufferedImage faceSquare = robot.createScreenCapture(minefield.getFaceSquare());

        try {
            ImageIO.write(faceSquare, "png", new File(Constants.OUTPUT_FOLDER_PATH + "faceSquare" + Constants.FILE_SUFFIX_PNG));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int topLeft = faceSquare.getRGB(6, 6);
        int bottomRight = faceSquare.getRGB(6, 7);
        System.out.println(String.format("top left: %s / bottomRight: %s .", topLeft, bottomRight));
        return GameState.parseState(topLeft, bottomRight);

    }

    @Override
    public void updateMinefield(Minefield minefield) throws InterruptedException {
        Thread.sleep(50);//Allows time for game to update
        BufferedImage gameGrid = robot.createScreenCapture(minefield.getGameGrid());

//        try {
//            ImageIO.write(gameGrid, "png", new File(Constants.OUTPUT_FOLDER_PATH + "gameGrid" + Constants.FILE_SUFFIX_PNG));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        for (int y = 0; y < minefield.getGameSize().getRow(); y++) {
            for (int x = 0; x < minefield.getGameSize().getCol(); x++) {

                int cornerPixel = gameGrid.getRGB(x * Constants.CELL_WIDTH + 1, y * Constants.CELL_HEIGHT + 1);
                int bottomPixel = gameGrid.getRGB(x * Constants.CELL_WIDTH + 9, y * Constants.CELL_HEIGHT + 11);

                CellState cellState = CellState.parseState(bottomPixel, cornerPixel);
                minefield.getCell(y, x).setCellState(cellState);
            }
        }

        for (Cell cell : minefield.getCellList()) {
            if (cell.getUnopenedNeighboursCount() == 0) {
                cell.setSolvedNeighbours(true);
            }
        }

    }
}
