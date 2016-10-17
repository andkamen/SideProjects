package com.simulation.contracts;

public interface Simulation extends Strategyable {

    String getName(); //TODO nameable interface?

    void run(int generationCount);
}
