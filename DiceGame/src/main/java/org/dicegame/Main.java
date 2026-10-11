package org.dicegame;

import org.dicegame.calculations.DiceProbabilityUtils;
import org.dicegame.model.*;
import org.dicegame.services.GameEngine;
import org.dicegame.strategies.BasicStrategy;
import org.dicegame.utils.DiceRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {




        List<Player> players = new ArrayList<>();
        players.add(AIPlayer.builder().name("Player 1").strategy(new BasicStrategy()).totalDice(5).build());
        players.add(AIPlayer.builder().name("Player 2").strategy(new BasicStrategy()).totalDice(5).build());

        GameState state = GameState.builder().players(players).isSpecialRound(false).currentRound(1).currentPlayerIndex(0).build();

        GameEngine gameLoop = GameEngine.builder().gameState(state).build();

        gameLoop.playGame();


        System.out.println(DiceProbabilityUtils.probabilityAtLeast(2,1,false));
        System.out.println(DiceProbabilityUtils.probabilityAtLeast(2,2,false));

        System.out.println(DiceProbabilityUtils.probabilityAtLeast(2,1,true));
        System.out.println(DiceProbabilityUtils.probabilityAtLeast(2,2,true));
    }

}