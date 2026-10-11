package org.dicegame.model;

public record DiceBet(int count, int die) implements PlayerBet {

    public DiceBet {
        if (count < 1) {
            throw new IllegalArgumentException("Count must be >= 1");
        }
        if (die < 1 || die > 6) { // assume d6
            throw new IllegalArgumentException("Die must be between 1 and 6");
        }

    }

    public static DiceBet bet(int count, int die) {
        return new DiceBet(count, die);
    }
}
