package com.io.commands;

import com.core.contracts.SimulationData;
import com.core.contracts.StrategyFactory;
import com.exceptions.InvalidInputException;
import com.simulation.contracts.Strategy;
import com.utilities.Constants;

/**
 * Fills a tournament or simulation with strategies give via console input
 */
public class ManualFillCommand extends BaseCommand {

    private String[] arguments;
    private StrategyFactory strategyFactory;

    public ManualFillCommand(SimulationData data, StrategyFactory strategyFactory, String[] arguments) {
        super(data);
        this.strategyFactory = strategyFactory;
        this.arguments = arguments;
    }

    @Override
    public String execute() {

        StringBuilder consoleOutput = new StringBuilder();
        String simulationType = this.arguments[0];
        String simulationName = this.arguments[1];
        //TODO check here if name/sim are valid before proceeding

        String[] strategyInfo = this.arguments[3].split(Constants.STRATEGY_SPLIT_DELIMITER);

        for (String info : strategyInfo) {
            String[] args = info.split(Constants.STRATEGY_COUNT_SPLIT_DELIMITER);
            String strategyName = args[0] + "Strategy";
            int strategyCount = 1;
            if (args.length > 1) {
                strategyCount = Integer.parseInt(args[1]);
            }

            for (int i = 0; i < strategyCount; i++) {
                Strategy newStrategy = this.strategyFactory.buildStrategy(strategyName);

                switch (simulationType.toLowerCase()) {
                    case Constants.SIMULATION_TYPE_SIMULATION:
                        consoleOutput.append(this.getData().getSimulation(simulationName).addStrategy(newStrategy)).append(System.lineSeparator());
                        break;
                    case Constants.SIMULATION_TYPE_TOURNAMENT:
                        consoleOutput.append(this.getData().getTournament(simulationName).addStrategy(newStrategy)).append(System.lineSeparator());
                        break;
                    default:
                        throw new InvalidInputException(simulationType);
                }
            }
        }
        consoleOutput.setLength(consoleOutput.length() - 1);
        return consoleOutput.toString();
    }
}
