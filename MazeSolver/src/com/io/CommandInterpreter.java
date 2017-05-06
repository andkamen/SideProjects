package com.io;

import com.io.commands.contracts.Command;
import com.io.contracts.FileIO;
import com.io.contracts.Interpreter;

public class CommandInterpreter implements Interpreter {

    private FileIO fileIO;

    public CommandInterpreter(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    @Override
    public Command dispatchCommand(String commandName, String[] arguments) {
        Command command = null;

        //TODO check arguments length. ! number req should throw
//        switch (commandName.toLowerCase()) {
//            case "create":
//                command = new CreateCommand(this.data, this.strategyFactory, arguments);
//                break;
//            case "fill":
//                if (arguments.length < 4) {
//                    throw new MissingCommandArgumentsException();
//                }
//                String mode = arguments[2];
//                switch (mode.toLowerCase()) {
//                    case Constants.FILL_MODE_MANUAL:
//                        command = new ManualFillCommand(this.data, this.strategyFactory, arguments);
//                        break;
//                    case Constants.FILL_MODE_FILE:
//                        command = new FileFillCommand(this.data, this.strategyFactory, arguments);
//                        break;
//                    default:
//                        throw new InvalidInputException(mode.toLowerCase());
//                }
//                break;
//            case "run":
//                command = new RunCommand(this.data, arguments);
//                break;
//            case "print":
//                command = new PrintCommand(this.data, arguments, this.fileIO);
//                break;
//            case "help":
//                command = new HelpCommand(arguments);
//                break;
//            default:
//                throw new InvalidInputException(commandName.toLowerCase());
//        }

        return command;
    }
}
