package com.io.commands;

import com.core.contracts.SimulationData;
import com.exceptions.InvalidInputException;
import com.utilities.Constants;
import com.utilities.Messages;

public class RunCommand extends BaseCommand {

    private String[] arguments;

    public RunCommand(SimulationData data, String[] arguments) {
        super(data);
        this.arguments = arguments;
    }

    @Override
    public String execute() {

        String simulationType = this.arguments[0];
        String simulationName = this.arguments[1];

        switch (simulationType.toLowerCase()) {
            case Constants.SIMULATION_TYPE_SIMULATION:
                int runCount = 1;
                if (this.arguments.length > 2) {
                    runCount = Integer.parseInt(this.arguments[2]);
                }
                this.getData().getSimulation(simulationName).run(runCount);
                return String.format(Messages.SUCCESSFULLY_RAN_SIMULATION, simulationName, runCount);
            case Constants.SIMULATION_TYPE_TOURNAMENT:
                this.getData().getTournament(simulationName).playOut();
                return String.format(Messages.SUCCESSFULLY_RAN_TOURNAMENT, simulationName);
            default:
                throw new InvalidInputException(simulationType.toLowerCase());
        }
    }
}
