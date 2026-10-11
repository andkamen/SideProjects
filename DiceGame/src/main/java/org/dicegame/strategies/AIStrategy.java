package org.dicegame.strategies;

import org.dicegame.model.AIPlayer;
import org.dicegame.model.GameState;
import org.dicegame.model.PlayerBet;

public interface AIStrategy {
    PlayerBet makeMove(GameState state, AIPlayer self);

}
