package com.io.commands;

import com.io.commands.contracts.Command;

import java.io.IOException;

public abstract class BaseCommand implements Command {

    private String[] arguments;

    public BaseCommand(String[] arguments) {
        this.setArguments(arguments);
    }

    protected String[] getArguments() {
        return arguments;
    }

    protected void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public abstract String execute() throws IOException;
}
