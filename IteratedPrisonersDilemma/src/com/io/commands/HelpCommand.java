package com.io.commands;

import com.core.contracts.SimulationData;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class HelpCommand extends BaseCommand {


    public HelpCommand(String[] arguments) {
        super(null, arguments);
    }

    @Override
    public String execute() {
        String[] arguments = this.getArguments();
        if (arguments != null && arguments.length > 0 && arguments[0].toLowerCase().equals("verbose")) {
            return this.displayVerboseHelp();
        }

        return this.displayHelp();
    }


    private String displayHelp() {
        StringBuilder helpBuilder = new StringBuilder();

        helpBuilder.append("create simtype name - creates a simulation or tournament").append(System.lineSeparator());
        helpBuilder.append("fill simtype name mode data - fill an already created sim with strategies").append(System.lineSeparator());
        helpBuilder.append("run simtype runCount - run a simulation. Run count is optional parameter").append(System.lineSeparator());
        helpBuilder.append("help verbose - Verbose is optional parameter and displays more info").append(System.lineSeparator());
        return helpBuilder.toString();
    }

    private String displayVerboseHelp() {
        StringBuilder helpBuilder = new StringBuilder();
        helpBuilder.append("create simtype name - creates a simulation or tournament").append(System.lineSeparator());
        helpBuilder.append("fill simtype name mode data - fill an already created sim with strategies").append(System.lineSeparator());
        helpBuilder.append("run simtype runCount - run a simulation. Run count is optional parameter").append(System.lineSeparator());
        helpBuilder.append("help verbose - Verbose is optional parameter and displays more info").append(System.lineSeparator());
        helpBuilder.append("Legend:").append(System.lineSeparator());
        helpBuilder.append("simtype: sim / tournament  . Specifies the type of sim accessed/created/ran").append(System.lineSeparator());
        helpBuilder.append("name: name of sim you want to create/access etc").append(System.lineSeparator());
        helpBuilder.append("mode: manual/file . Manual mode fills a sim with manually input list of strategies." +
                " File mode accepts a textFileName.txt name of a file that will contain the desired strategies").append(System.lineSeparator());
        helpBuilder.append("data: The list of strategies being manually entered. \"Random_2|Cooperative_5|Grudger\"." +
                " If a number is not specified after the strategy an amount of 1 is assumed.").append(System.lineSeparator());
        return helpBuilder.toString();
    }

}
