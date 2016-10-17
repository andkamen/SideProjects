package com.simulation.contracts;

public interface Strategyable {

    String addStrategy(Strategy strategy);

    String removeStrategy(String simpleName);
}
