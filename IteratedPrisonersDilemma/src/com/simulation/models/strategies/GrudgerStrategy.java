package com.simulation.models.strategies;

/**
 * This Strategy will cooperate until it's "betrayed". After that it will always be selfish.
 */
public class GrudgerStrategy extends BaseStrategy {

    private boolean wasBetrayed;

    public GrudgerStrategy() {
        wasBetrayed = false;
    }

    @Override
    public boolean makeMove() {
        return !wasBetrayed;
    }

    @Override
    public void updateOpponentMove(boolean opponentMove) {
        if (!opponentMove) {
            this.wasBetrayed = true;
        }
    }


}
