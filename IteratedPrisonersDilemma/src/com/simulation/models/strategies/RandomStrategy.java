package com.simulation.models.strategies;

import java.util.Random;

/**
 * This Strategy will be random and unpredictable.
 */
public class RandomStrategy extends BaseStrategy {
    private Random rand;

    public RandomStrategy() {
        this.rand = new Random();
    }

    @Override
    public boolean makeMove() {
        return this.rand.nextBoolean();
    }

    @Override
    public void updateOpponentMove(boolean opponentMove) {
    }
}
