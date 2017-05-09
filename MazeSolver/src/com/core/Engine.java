package com.core;

import com.algorithms.BreadthFirst;
import com.algorithms.DepthFirst;
import com.dataStructures.Maze;
import com.dataStructures.Node;
import com.exceptions.InvalidInputException;
import com.io.ConsoleIOImpl;
import com.io.contracts.ConsoleIO;
import com.solver.ImageHandler;
import com.solver.ImageHandlerImpl;
import com.utilities.Constants;

import java.util.Arrays;
import java.util.Queue;

public class Engine {

    private ConsoleIO consoleIO;
    private ImageHandler imageHandler;

    private Boolean isRunning;

    public Engine() {
        this.consoleIO = new ConsoleIOImpl();
        this.imageHandler = new ImageHandlerImpl();
    }

    public void run() {
        this.isRunning = true;

        while (this.isRunning) {
            String inputLine = this.consoleIO.readLine();

            this.processInput(inputLine);
        }
    }

    private void processInput(String input) {

        String[] splitArgs = input.split(Constants.SPACE_SPLIT_DELIMITER);

        String commandName = splitArgs[0];

        if (commandName.toLowerCase().equals(Constants.INPUT_TERMINATING_COMMAND)) {
            this.isRunning = false;
            return;
        }

        String[] filteredArgs = null;
        if (splitArgs.length > 1) {
            filteredArgs = Arrays.copyOfRange(splitArgs, 1, splitArgs.length);
        }
        try {
//            Command command = commandInterpreter.dispatchCommand(commandName, filteredArgs);
//            String commandResult = command.execute();
//            this.consoleIO.writeLine(commandResult);


            switch (commandName) {
                case "solve":
                    Maze maze = this.imageHandler.parseImage(filteredArgs[0]);
                    Queue<Node> path = new DepthFirst().solve(maze);
                    //Queue<Node> path = new BreadthFirst().solve(maze);

                    this.imageHandler.drawPath(path);

                    break;
                default:
                    throw new InvalidInputException(commandName.toLowerCase());
            }
        } catch (Exception e) {
            this.consoleIO.writeLine(e.getMessage());
        }
    }
}
