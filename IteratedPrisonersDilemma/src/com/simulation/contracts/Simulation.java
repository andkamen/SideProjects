package com.simulation.contracts;

public interface Simulation extends Strategyable {

    String getName(); //TODO nameable interface?

    int getGenerationSize();

    void run(int generationCount);

    String print(int genNum, boolean isVerboseMode);
}
