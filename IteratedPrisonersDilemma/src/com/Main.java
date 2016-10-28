package com;

import com.core.Engine;
import com.core.StrategyFactoryImpl;
import com.core.contracts.StrategyFactory;
import com.simulation.contracts.Simulation;
import com.simulation.contracts.Strategy;
import com.simulation.contracts.Generation;
import com.simulation.models.GenerationImpl;
import com.simulation.models.SimulationImpl;
import com.simulation.models.strategies.CooperativeStrategy;
import com.simulation.models.strategies.GrudgerStrategy;
import com.simulation.models.strategies.RandomStrategy;
import com.simulation.models.strategies.SelfishStrategy;
import com.utilities.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args)  {
        Engine engine = new Engine();
        engine.run();

        System.out.println();
    }
}
