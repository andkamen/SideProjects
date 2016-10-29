package com.core;

import com.core.contracts.StrategyFactory;
import com.exceptions.InvalidStrategyNameException;
import com.simulation.contracts.Strategy;
import com.simulation.models.strategies.*;

public class StrategyFactoryImpl implements StrategyFactory {

    public StrategyFactoryImpl() {
    }

    @Override
    public Strategy buildStrategy(String strategyType) {
        Strategy newStrategy = null;

        switch (strategyType) {
            case "AlwaysCooperate":
                newStrategy = new AlwaysCooperateStrategy();
                break;
            case "AlwaysDefect":
                newStrategy = new AlwaysDefectStrategy();
                break;
            case "Grudger":
                newStrategy = new GrudgerStrategy();
                break;
            case "Random":
                newStrategy = new RandomStrategy();
                break;
            case "SuspiciousTitForTat":
                newStrategy = new SuspiciousTitForTatStrategy();
                break;
            case "TitForTat":
                newStrategy = new TitForTatStrategy();
                break;
            case "TitForTwoTats":
                newStrategy = new TitForTwoTatsStrategy();
                break;
            case "TwoTitsForTat":
                newStrategy = new TwoTitsForTatStrategy();
                break;
            case "Gradual":
                newStrategy = new GradualStrategy();
                break;
            default:
                throw new InvalidStrategyNameException(strategyType);
        }
        //TitForTwoTatsStrategy
        return newStrategy;
    }
}
