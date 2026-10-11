package org.dicegame.services;

import lombok.Builder;
import org.dicegame.model.*;

@Builder
public class GameEngine {

    private GameState gameState;



    public void playGame() throws InterruptedException {
        while (!isGameOver()) {
            Player currentPlayer = gameState.getCurrentPlayer();
            PlayerBet playerBet = currentPlayer.takeTurn(gameState);

            handleBet(playerBet);

            gameState.setLastRoundBet(playerBet);

        }
    }


    private boolean isGameOver() {
        return gameState.getPlayers().size() == 1;
    }

    private void rollDiceHands() {
        for (Player player : gameState.getPlayers()) {
            player.
        }
    }

    private void handleBet(PlayerBet bet) throws InterruptedException {
        switch (bet) {
            case BullshitBet bb -> {
                System.out.println("Player calls bullshit!");
                Thread.sleep(2000);
                //TODO count total dice, determine loser and decrease his dice by one

            }
            case DiceBet db -> {
                System.out.println("Player bets " + db.count() + " | " + db.die());
                Thread.sleep(2000);
            }
        }
    }



}
