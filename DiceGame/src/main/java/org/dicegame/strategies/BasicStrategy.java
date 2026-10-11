package org.dicegame.strategies;

import org.dicegame.model.*;
import org.dicegame.utils.DiceRules;

public class BasicStrategy implements AIStrategy {

    private double riskThreshold;

    @Override
    public PlayerBet makeMove(GameState state, AIPlayer self) {
        if (state.getLastRoundBet() instanceof BullshitBet || state.getLastRoundBet() == null) {
            return DiceBet.bet(1, 2);
        }

        return DiceRules.getNextDiceBet((DiceBet) state.getLastRoundBet(), state.isSpecialRound());

    }
}
