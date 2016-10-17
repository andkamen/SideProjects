package com.io.commands;

import com.core.contracts.SimulationData;
import com.core.contracts.StrategyFactory;
import com.exceptions.InvalidInputException;
import com.simulation.models.GenerationImpl;
import com.simulation.models.SimulationImpl;
import com.utilities.Constants;

public class CreateCommand extends BaseCommand {

    private String[] arguments;
    private StrategyFactory strategyFactory;

    public CreateCommand(SimulationData data, StrategyFactory strategyFactory, String[] arguments) {
        super(data);
        this.strategyFactory = strategyFactory;
        this.arguments = arguments;
    }

    @Override
    public String execute() {
        String simulationType = this.arguments[0];
        String name = this.arguments[1];

        switch (simulationType.toLowerCase()) {
            case Constants.SIMULATION_TYPE_SIMULATION:
                return this.getData().addSimulation(new SimulationImpl(name, this.strategyFactory));
            case Constants.SIMULATION_TYPE_TOURNAMENT:
                return this.getData().addTournament(new GenerationImpl(name));
            default:
                throw new InvalidInputException(simulationType.toLowerCase());
        }
    }
}
