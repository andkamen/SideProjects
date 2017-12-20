package com.core;

import com.io.ConsoleIOImpl;
import com.io.ImageHandlerImpl;
import com.io.KeyboardIOImpl;
import com.io.MouseIOImpl;
import com.io.contracts.ConsoleIO;
import com.io.contracts.ImageHandler;
import com.io.contracts.KeyboardIO;
import com.io.contracts.MouseIO;
import com.solver.MinesweeperBot;
import com.utilities.Constants;
import com.utilities.GameSize;

import java.awt.*;
import java.util.Arrays;

public class Engine {

    private MouseIO mouseIO;
    private KeyboardIO keyboardIO;
    private ConsoleIO consoleIO;
    private ImageHandler imageHandler;
    private Robot robot;
    private MinesweeperBot bot;

    private Boolean isRunning;

    public Engine() throws AWTException {
        robot = new Robot();
        mouseIO = new MouseIOImpl(robot);
        keyboardIO = new KeyboardIOImpl(robot);
        consoleIO = new ConsoleIOImpl();
        imageHandler = new ImageHandlerImpl(robot);
        bot = new MinesweeperBot(mouseIO, keyboardIO, imageHandler);
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
            Thread.sleep(3000);

            bot.solve(GameSize.EXPERT);


        } catch (Exception e) {
            consoleIO.writeLine(e.getMessage());
            e.printStackTrace();
        }
    }
}
