package com.core;

import com.io.commands.contracts.Command;
import com.io.contracts.Interpreter;
import com.core.contracts.EngineInterface;
import com.core.contracts.SimulationData;
import com.core.contracts.StrategyFactory;
import com.io.CommandInterpreter;
import com.io.ConsoleReader;
import com.io.ConsoleWriter;
import com.io.contracts.InputReader;
import com.io.contracts.OutputWriter;
import com.utilities.Constants;

import java.util.Arrays;

public class Engine implements EngineInterface {

    private InputReader consoleReader;
    private OutputWriter consoleWriter;
    private StrategyFactory strategyFactory;
    private SimulationData data;
    private Interpreter commandInterpreter;

    private Boolean isRunning;


    public Engine() {
        this.consoleReader = new ConsoleReader();
        this.consoleWriter = new ConsoleWriter();
        this.strategyFactory = new StrategyFactoryImpl();
        this.data = new SimulationDataImpl();
        this.commandInterpreter = new CommandInterpreter(this.data, this.strategyFactory);
    }

    @Override
    public void run() {
        this.isRunning = true;

        while (this.isRunning) {
            String inputLine = this.consoleReader.readLine();

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
            Command command = commandInterpreter.dispatchCommand(commandName, filteredArgs);

            String commandResult = command.execute();
            this.consoleWriter.writeLine(commandResult);
        } catch (Exception e) {
            this.consoleWriter.writeLine(e.getMessage());
        }
    }
}
