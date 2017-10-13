package com.core;

import com.algorithms.Algorithm;
import com.dataStructures.Maze;
import com.dataStructures.Solution;
import com.exceptions.InvalidInputException;
import com.io.ConsoleIOImpl;
import com.io.contracts.ConsoleIO;
import com.solver.AlgorithmFactory;
import com.solver.ImageHandler;
import com.solver.ImageHandlerImpl;
import com.utilities.Constants;

import java.io.IOException;
import java.util.Arrays;

public class Engine {

    private String[] algorithms = {"DFS", "BFS", "Dijkstra", "AStar"};

    private ConsoleIO consoleIO;
    private ImageHandler imageHandler;
    private AlgorithmFactory algorithmFactory;

    private Boolean isRunning;

    public Engine() {
        consoleIO = new ConsoleIOImpl();
        imageHandler = new ImageHandlerImpl();
        algorithmFactory = new AlgorithmFactory();
    }

    public void run() {
        isRunning = true;

        while (isRunning) {
            String inputLine = consoleIO.readLine();

            processInput(inputLine);
        }
    }

    private void processInput(String input) {

        String[] splitArgs = input.split(Constants.SPACE_SPLIT_DELIMITER);

        String commandName = splitArgs[0];

        if (commandName.toLowerCase().equals(Constants.INPUT_TERMINATING_COMMAND)) {
            isRunning = false;
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
                    //Measure maze creation Time

                    Maze maze = imageHandler.parseImage(filteredArgs[0]);

                    for (String name : algorithms) {
                        System.out.println("------------- Solving " + name + "-------------");

                        long startTime = System.nanoTime();
                        Algorithm algorithm = algorithmFactory.getAlgorithm(name);
                        Solution solution = algorithm.solve(maze);
                        long endTime = System.nanoTime();

                        System.out.println("Nodes Explored: " + solution.getNodesExplored());
                        System.out.println(solution.isCompleted() ?
                                "Path found, length: " + solution.getPathLength() :
                                "No path found"
                        );
                        System.out.println("Time elapsed: " + (endTime - startTime) / 1_000_000 + " millis");

                        if (solution.isCompleted()) {
                            //   imageHandler.drawPath(solution.getPath(), name);
                        }
                    }
                    break;
                default:
                    throw new InvalidInputException(commandName.toLowerCase());
            }
        } catch (IOException e) {
            consoleIO.writeLine(e.getMessage());
        }
    }
}
