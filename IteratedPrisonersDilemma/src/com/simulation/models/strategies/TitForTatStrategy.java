package com.simulation.models.strategies;


/**
 * Cooperates on the first move, then copies the opponent’s last move.
 */
public class TitForTatStrategy extends BaseStrategy {

    private boolean isFirstMove;
    private boolean lastOpponentMove;

    public TitForTatStrategy() {
        this.isFirstMove = true;
    }

    @Override
    public boolean makeMove() {
        if (this.isFirstMove) {
            this.isFirstMove = false;
            return true;
        }
        return lastOpponentMove;
    }

    @Override
    public void updateOpponentMove(boolean opponentMove) {
        this.lastOpponentMove = opponentMove;
    }

    @Override
    public void resetStrategy() {
        this.isFirstMove = true;
    }
}
