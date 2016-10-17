package com.io;

import com.exceptions.InvalidInputException;
import com.exceptions.MissingCommandArgumentsException;
import com.io.commands.CreateCommand;
import com.io.commands.ManualFillCommand;
import com.io.commands.RunCommand;
import com.io.commands.contracts.Command;
import com.io.contracts.Interpreter;
import com.core.contracts.SimulationData;
import com.core.contracts.StrategyFactory;
import com.utilities.Constants;

public class CommandInterpreter implements Interpreter {

    private SimulationData data;
    private StrategyFactory strategyFactory;

    public CommandInterpreter(SimulationData data, StrategyFactory strategyFactory) {
        this.data = data;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public Command dispatchCommand(String commandName, String[] arguments) {
        Command command = null;
        switch (commandName.toLowerCase()) {
            case "create":
                command = new CreateCommand(this.data, this.strategyFactory, arguments);
                break;
            case "fill":
                if (arguments.length < 4) {
                    throw new MissingCommandArgumentsException();
                }
                String mode = arguments[2];
                //TODO add text init command
                switch (mode.toLowerCase()) {
                    case Constants.FILL_MODE_MANUAL:
                        command = new ManualFillCommand(this.data, this.strategyFactory, arguments);
                        break;
                    default:
                        throw new InvalidInputException(mode.toLowerCase());
                }
                break;
            case "run":
                command = new RunCommand(this.data, arguments);
                break;
            default:
                throw new InvalidInputException(commandName.toLowerCase());
        }

        return command;
    }
}
