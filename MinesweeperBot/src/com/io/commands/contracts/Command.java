package com.io.commands.contracts;

import java.io.IOException;

public interface Command {

    String execute() throws IOException;
}
