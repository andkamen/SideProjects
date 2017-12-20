package com.solver;

import com.dataStructures.Cell;
import com.dataStructures.Minefield;
import com.io.contracts.ImageHandler;
import com.io.contracts.KeyboardIO;
import com.io.contracts.MouseIO;
import com.utilities.CellState;
import com.utilities.GameSize;
import com.utilities.GameState;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

public class MinesweeperBot implements Bot {
    private MouseIO mouseIO;
    private KeyboardIO keyboardIO;
    private ImageHandler imageHandler;


    public MinesweeperBot(MouseIO mouseIO, KeyboardIO keyboardIO, ImageHandler imageHandler) {
        this.mouseIO = mouseIO;
        this.keyboardIO = keyboardIO;
        this.imageHandler = imageHandler;
    }


    public void solve(GameSize gameSize) throws InterruptedException {
        Minefield minefield = new Minefield(gameSize, imageHandler.findCorner());

        keyboardIO.resetGame();

        //Make first move
        makeFirstMove(gameSize, minefield);

        Queue<Cell> queue = new ArrayDeque<>();
        boolean outOfMoves = false;
        while (true) {
            GameState gameState = imageHandler.updateGameState(minefield);
            if (gameState == null || !gameState.shouldContinue()) {
                if (GameState.WIN.equals(gameState)) {
                    System.out.println("SOLVED!");
                    break;
                }
                Thread.sleep(3000);
                keyboardIO.resetGame();
                minefield = new Minefield(gameSize, imageHandler.findCorner());
                makeFirstMove(gameSize, minefield);
            }

            imageHandler.updateMinefield(minefield);

            queue.addAll(minefield.getCellList().stream()
                    .filter(cell -> !CellState.BOMB.equals(cell.getCellState()) &&
                            !CellState.EMPTY.equals(cell.getCellState()) &&
                            !CellState.UNOPENED.equals(cell.getCellState()))
                    .collect(Collectors.toList()));


            outOfMoves = true;
            while (!queue.isEmpty()) {
                System.out.println("Polling cells. Queue size: " + queue.size());
                Cell cell = queue.poll();

                //Mark certain mines
                if ((cell.getUnopenedNeighboursCount() + cell.getFlaggedNeighboursCount()) == cell.getCellState().getMineCount()) {
                    for (Cell neighbour : cell.getUnopenedNeighbours()) {
                        Thread.sleep(5);
                        mouseIO.rightClick(neighbour.getX(), neighbour.getY());
                        neighbour.setCellState(CellState.BOMB);
                        outOfMoves = false;
                    }
                }

                //Open safe squares
                if (cell.getFlaggedNeighboursCount() == cell.getCellState().getMineCount()) {
                    for (Cell neighbour : cell.getUnopenedNeighbours()) {
                        Thread.sleep(5);
                        mouseIO.leftClick(neighbour.getX(), neighbour.getY());
                        neighbour.setClicked(true);
                        outOfMoves = false;
                    }
                }
            }

            if (outOfMoves) {
                System.out.println("Pick random cell!");

                imageHandler.updateMinefield(minefield);

                Optional<Cell> randomCell = minefield.getCellList().stream()
                        .filter(cell -> !CellState.BOMB.equals(cell.getCellState()) &&
                                !CellState.EMPTY.equals(cell.getCellState()) &&
                                !CellState.UNOPENED.equals(cell.getCellState())).findFirst();

                randomCell.ifPresent(cell1 -> cell1.getUnopenedNeighbours().stream().findFirst().ifPresent(cell -> mouseIO.leftClick(cell.getX(), cell.getY())));
            }
        }
    }

    private void makeRandomMove(Queue<Cell> queue, MouseIO mouseIO) {
        Cell cell = queue.peek();
        mouseIO.leftClick(cell.getX(), cell.getY());

    }

    private void makeFirstMove(GameSize gameSize, Minefield minefield) {
        Cell firstClickCell = minefield.getCell(gameSize.getRow() / 2, gameSize.getCol() / 2);
        mouseIO.leftClick(firstClickCell.getX(), firstClickCell.getY());
        firstClickCell.setClicked(true);
    }
}
