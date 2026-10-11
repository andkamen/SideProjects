package org.dicegame.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GameState {

    private List<Player> players;
    private boolean isSpecialRound = false;
    private int currentRound = 1;
    private int currentPlayerIndex = 0;
    private PlayerBet lastRoundBet;


    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public void removePlayer(Player player) {
        int removedIndex = players.indexOf(player);
        players.remove(removedIndex);

        if (removedIndex <= currentPlayerIndex) {
            // Adjust index so we don't skip the next player
            currentPlayerIndex--;
        }

        // Clamp index to valid range
        if (currentPlayerIndex < 0) {
            currentPlayerIndex = 0;
        }
    }

}