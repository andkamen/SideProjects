package com.solver;

import com.dataStructures.Cell;
import com.dataStructures.Minefield;
import com.io.contracts.ImageHandler;
import com.io.contracts.MouseIO;
import com.utilities.CellState;
import com.utilities.GameSize;
import com.utilities.GameState;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.Collectors;

public class MinesweeperBot implements Bot {
    private MouseIO mouseIO;
    private ImageHandler imageHandler;


    public MinesweeperBot(MouseIO mouseIO, ImageHandler imageHandler) {
        this.mouseIO = mouseIO;
        this.imageHandler = imageHandler;
    }


    public void solve(GameSize gameSize) throws InterruptedException {
        Minefield minefield = new Minefield(gameSize, imageHandler.findCorner());

        //Make first move
        Cell firstClickCell = minefield.getCell(gameSize.getRow() / 2, gameSize.getCol() / 2);
        mouseIO.leftClick(firstClickCell.getX(), firstClickCell.getY());
        firstClickCell.setClicked(true);

        Queue<Cell> queue = new ArrayDeque<>();

        while (true) {
            GameState gameState = imageHandler.updateGameState(minefield);
            if (!gameState.shouldContinue()) {
                break;
            }
            imageHandler.updateMinefield(minefield);

            queue.addAll(minefield.getCellList().stream()
                    .filter(cell -> !CellState.BOMB.equals(cell.getCellState()) &&
                            !CellState.EMPTY.equals(cell.getCellState()) &&
                            !CellState.UNOPENED.equals(cell.getCellState()))
                    .collect(Collectors.toList()));
            boolean outOfMoves = true;

            while (!queue.isEmpty()) {
                System.out.println("Polling cells. Queue size: " + queue.size());
                Cell cell = queue.poll();

                //Mark certain mines
                if ((cell.getUnopenedNeighboursCount() + cell.getFlaggedNeighboursCount()) == cell.getCellState().getMineCount()) {
                    for (Cell neighbour : cell.getUnopenedNeighbours()) {
                        Thread.sleep(200);
                        mouseIO.rightClick(neighbour.getX(), neighbour.getY());
                        neighbour.setCellState(CellState.BOMB);
                        outOfMoves = false;
                    }
                }

                //Open safe squares
                if (cell.getFlaggedNeighboursCount() == cell.getCellState().getMineCount()) {
                    for (Cell neighbour : cell.getUnopenedNeighbours()) {
                        Thread.sleep(200);
                        mouseIO.leftClick(neighbour.getX(), neighbour.getY());
                        neighbour.setClicked(true);
                        outOfMoves = false;
                    }
                }
            }

            if (outOfMoves) {
                System.out.println("Pick random cell!");
            }
        }
    }
}
