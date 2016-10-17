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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Engine engine = new Engine();
        engine.run();


        Generation generation = new GenerationImpl();
        StrategyFactory strategyFactory = new StrategyFactoryImpl();
        Simulation sim = new SimulationImpl("sim", strategyFactory);

        Strategy cooperative = new CooperativeStrategy();
        Strategy grudger = new GrudgerStrategy();
        Strategy selfish = new SelfishStrategy();
        Strategy random = new RandomStrategy();


        System.out.println(sim.addStrategy(selfish));
        System.out.println(sim.addStrategy(selfish));
        System.out.println(sim.addStrategy(selfish));
        System.out.println(sim.addStrategy(cooperative));
        System.out.println(sim.addStrategy(cooperative));
        System.out.println(sim.addStrategy(cooperative));
        System.out.println(sim.addStrategy(cooperative));
        System.out.println(sim.addStrategy(cooperative));
        System.out.println(sim.addStrategy(cooperative));
        System.out.println(sim.addStrategy(cooperative));
        // System.out.println(sim.removeStrategy("CooperativeStrategy"));

        sim.run(3);

        System.out.println();
    }
}
