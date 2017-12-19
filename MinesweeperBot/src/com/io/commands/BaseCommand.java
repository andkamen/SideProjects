package com.io.commands;

import com.io.commands.contracts.Command;
import lombok.Data;

import java.io.IOException;

@Data
public abstract class BaseCommand implements Command {

    private String[] arguments;

    public BaseCommand(String[] arguments) {
        this.setArguments(arguments);
    }

    @Override
    public abstract String execute() throws IOException;
}
