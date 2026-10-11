package org.dicegame.model;

public record BullshitBet() implements PlayerBet {
    public static BullshitBet callBullshit() {
        return new BullshitBet();
    }
}
