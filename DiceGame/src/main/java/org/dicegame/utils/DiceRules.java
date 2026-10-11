package org.dicegame.utils;

import org.dicegame.GameConstants;
import org.dicegame.model.DiceBet;

import java.util.HashMap;
import java.util.Random;

public class DiceRules {

    private static Random random = new Random();

    public static boolean isValid(DiceBet bet) {
        return (bet.count() > 0) && (bet.die() > 0 || bet.die() <= GameConstants.DIE_SIDES);
    }


    //TODO should not return a bet with more dice than are in play
    public static DiceBet getNextDiceBet(DiceBet bet, boolean isSpecialRound) {
        if (bet.die() < GameConstants.DIE_SIDES) {
            return DiceBet.bet(bet.count(), bet.die() + 1);
        }
        return DiceBet.bet(bet.count() + 1, isSpecialRound ? 1 : 2);
    }

    public static HashMap<Integer, Integer> rollDiceHand(int totalDice) {
        HashMap<Integer, Integer> diceHand = new HashMap<>();
        diceHand.put(1,0);
        diceHand.put(2,0);
        diceHand.put(3,0);
        diceHand.put(4,0);
        diceHand.put(5,0);
        diceHand.put(6,0);


        for (int i = 0; i < totalDice; i++) {
            int roll = random.nextInt(GameConstants.DIE_SIDES) + 1;
            diceHand.put(roll, diceHand.get(roll) + 1);
        }

        return diceHand;
    }

}
