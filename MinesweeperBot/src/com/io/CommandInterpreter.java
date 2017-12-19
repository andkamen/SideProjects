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

//        switch (commandName.toLowerCase()) {
//            case "run":
//                command = new RunCommand(this.data, arguments);
//                break;
//            default:
//                throw new InvalidInputException(commandName.toLowerCase());
//        }

        return command;
    }
}
