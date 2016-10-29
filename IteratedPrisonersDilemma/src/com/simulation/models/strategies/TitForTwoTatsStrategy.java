package com.simulation.models.strategies;

/**
 * Cooperates on the first move, and defects only when the opponent defects two times.
 */
public class TitForTwoTatsStrategy extends BaseStrategy {

    private int opponentConsecutiveDefectCount;

    public TitForTwoTatsStrategy() {
        this.opponentConsecutiveDefectCount = 0;
    }

    @Override
    public boolean makeMove() {

        if (this.opponentConsecutiveDefectCount > 1) {
            return false;
        }

        return true;
    }

    @Override
    public void updateOpponentMove(boolean opponentMove) {
        if (!opponentMove) {
            this.opponentConsecutiveDefectCount++;
        } else {
            this.opponentConsecutiveDefectCount = 0;
        }
    }

    @Override
    public void resetStrategy() {
        this.opponentConsecutiveDefectCount = 0;
    }
}
