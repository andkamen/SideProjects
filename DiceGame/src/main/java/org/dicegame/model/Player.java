package org.dicegame.model;

import java.util.HashMap;

public interface Player {
    PlayerBet takeTurn(GameState state);
}