package org.dicegame.model;

import lombok.*;
import org.dicegame.strategies.AIStrategy;

import java.util.HashMap;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AIPlayer implements Player {
    private final String name;
    private final AIStrategy strategy;
    private int totalDice;
    private HashMap<Integer, Integer> diceHand;

    public AIPlayer(String name, AIStrategy strategy, int startingDice) {
        this.name = name;
        this.strategy = strategy;
        this.totalDice = startingDice;
        this.diceHand = new HashMap<>();
    }

    @Override
    public PlayerBet takeTurn(GameState state) {
       return strategy.makeMove(state, this);
    }
}