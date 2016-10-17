package com.core;

import com.core.contracts.StrategyFactory;
import com.exceptions.InvalidStrategyNameException;
import com.simulation.contracts.Strategy;
import com.simulation.models.strategies.CooperativeStrategy;
import com.simulation.models.strategies.GrudgerStrategy;
import com.simulation.models.strategies.RandomStrategy;
import com.simulation.models.strategies.SelfishStrategy;

public class StrategyFactoryImpl implements StrategyFactory {

    public StrategyFactoryImpl() {
    }

    @Override
    public Strategy buildStrategy(String strategyType) {
        Strategy newStrategy = null;

        switch (strategyType) {
            case "CooperativeStrategy":
                newStrategy = new CooperativeStrategy();
                break;
            case "GrudgerStrategy":
                newStrategy = new GrudgerStrategy();
                break;
            case "RandomStrategy":
                newStrategy = new RandomStrategy();
                break;
            case "SelfishStrategy":
                newStrategy = new SelfishStrategy();
                break;
            default:
                throw new InvalidStrategyNameException(strategyType.replaceAll("Strategy", ""));
        }

        return newStrategy;
    }
}
